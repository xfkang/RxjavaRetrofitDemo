package com.itbird.retrofit.http;

import android.content.Context;
import android.util.Log;

import com.itbird.retrofit.entity.Doctor;
import com.itbird.retrofit.entity.HttpRequestResult;
import com.itbird.retrofit.log.HttpLoggingInterceptor;
import com.itbird.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    private static final String TAG = RetrofitWrapper.class.getSimpleName();
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;
    private static final int DEFAULT_HTTP_CACHE_SIZE = 10 * 1024 * 1024;
    private Retrofit mRetrofit;
    private RequestService mRequestService;

    //构造方法私有
    public RetrofitWrapper(Context context, boolean isAllowCache, boolean cacheMethod) {
        mRetrofit = new Retrofit.Builder()
                .client(getOkHttpClient(context, isAllowCache, cacheMethod))
//                .addConverterFactory(GsonConverterFactory.create())
                //对http请求结果进行统一的预处理 GosnResponseBodyConvert
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mRequestService = mRetrofit.create(RequestService.class);
    }


    /**
     * @param subscriber 由调用者传过来的观察者对象
     * @param start      起始位置
     * @param count      获取长度
     */
    public void getDoctorList(Subscriber<List<Doctor>> subscriber, int start, int count) {
        Observable observable = mRequestService.getDoctorList(start, count)
                .map(new HttpResultFunc<List<Doctor>>());
        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
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

    /**
     * 缓存策略
     *
     * @param context
     * @param isAllowCache 是否允许使用缓存策略
     * @param cacheMethod  false:有网和没有网都是先读缓存 true:离线可以缓存，在线就获取最新数据 default=false
     * @return
     */
    private OkHttpClient getOkHttpClient(final Context context, boolean isAllowCache, boolean cacheMethod) {
        /**
         * 获取缓存
         */
        Interceptor baseInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isNetworkAvailable(context)) {
                    /**
                     * 离线缓存控制  总的缓存时间=在线缓存时间+设置离线缓存时间
                     */
                    int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周,单位:秒
                    CacheControl tempCacheControl = new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(maxStale, TimeUnit.SECONDS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(tempCacheControl)
                            .build();
                    Log.i(TAG, "intercept:no network ");
                }
                return chain.proceed(request);
            }
        };
        //只有 网络拦截器环节 才会写入缓存写入缓存,在有网络的时候 设置缓存时间
        HttpLoggingInterceptor rewriteCacheControlInterceptor = new HttpLoggingInterceptor(isAllowCache);
        //设置缓存路径 内置存储
        //File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        //外部存储
        File httpCacheDirectory = new File(context.getExternalCacheDir(), "responses");
        //设置缓存 10M
        int cacheSize = DEFAULT_HTTP_CACHE_SIZE;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        if (isAllowCache && cacheMethod) {
            builder.addInterceptor(baseInterceptor);
        }
        builder.addNetworkInterceptor(rewriteCacheControlInterceptor);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

}
