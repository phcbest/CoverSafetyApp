package com.xyxy.coversafetyapp.utils.api;

import com.xyxy.coversafetyapp.entity.respond.GetAllCoverErrorBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCoverError {

    @GET("/work/covererror/list")
    Observable<GetAllCoverErrorBean> getAllCoverError(@Query("limit") int limit, @Query("page") int page);

}
