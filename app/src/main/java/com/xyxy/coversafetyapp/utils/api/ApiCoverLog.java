package com.xyxy.coversafetyapp.utils.api;

import com.xyxy.coversafetyapp.entity.request.CoverLogEntity;
import com.xyxy.coversafetyapp.entity.respond.AllCoverLogBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCoverLog {

    @POST("work/coverlog/save")
    Observable<Map<String, Object>> saveCoverLog(@Body CoverLogEntity coverLogEntity);

    @GET("work/coverlog/list")
    Observable<AllCoverLogBean> selectAllLog(@Query("limit") int limit, @Query("page") int page);

    @POST("work/coverlog/delete")
    Observable<Map<String, Object>> deleteLogById(@Body Integer[] ids);
}
