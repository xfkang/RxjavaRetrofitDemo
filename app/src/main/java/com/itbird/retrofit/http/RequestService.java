package com.itbird.retrofit.http;


import com.itbird.retrofit.entity.HttpRequestResult;
import com.itbird.retrofit.entity.PatientList;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 * Created by itbird on 16/3/9.
 */
public interface RequestService {

    @GET("/DPost/PatientManage/Index")
    Observable<HttpRequestResult<List<PatientList>>> getPatientList(@Query("HospitalId") int doctorid);
}
