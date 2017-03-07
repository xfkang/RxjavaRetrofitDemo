package com.itbird.retrofit.http;

import com.itbird.retrofit.entity.HttpRequestResult;
import com.itbird.retrofit.entity.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 * Created by xfkang on 16/3/9.
 */
public interface RequestService {

    @GET("top250")
    Observable<HttpRequestResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
