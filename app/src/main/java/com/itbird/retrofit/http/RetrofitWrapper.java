package com.itbird.retrofit.http;

import com.itbird.retrofit.entity.HttpRequestResult;
import com.itbird.retrofit.entity.Subject;
import com.itbird.retrofit.log.HttpLoggingInterceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 对请求方法与请求过程进行封装
 * Created by xfkang on 17/3/1.
 */
public class RetrofitWrapper {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private RequestService mRequestService;
    private static RetrofitWrapper instance;
    private static final boolean DEBUG = true;

    //构造方法私有
    private RetrofitWrapper() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        if (DEBUG) {
            builder.addNetworkInterceptor(new HttpLoggingInterceptor());
        }
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
//                .addConverterFactory(GsonConverterFactory.create())
                //对http请求结果进行统一的预处理 GosnResponseBodyConvert
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mRequestService = mRetrofit.create(RequestService.class);
    }

    //获取单例
    public static RetrofitWrapper getInstance(){
        if (instance == null) {
            synchronized (RetrofitWrapper.class) {
                if (instance == null) {
                    instance = new RetrofitWrapper();
                }
            }
        }
        return instance;
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     * @param subscriber  由调用者传过来的观察者对象
     * @param start 起始位置
     * @param count 获取长度
     */
    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count){
        Observable observable = mRequestService.getTopMovie(start, count)
                .map(new HttpResultFunc<List<Subject>>());
        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
         o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpRequestResult<T>, T> {

        @Override
        public T call(HttpRequestResult<T> httpResult) {
            if (httpResult.getResultCode() != 0) {
                throw new ApiException(100);
            }
            return httpResult.getData();
        }
    }

}
