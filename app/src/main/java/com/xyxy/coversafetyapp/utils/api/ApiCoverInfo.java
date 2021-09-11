package com.xyxy.coversafetyapp.utils.api;

import com.xyxy.coversafetyapp.entity.request.CoverInfoEntity;
import com.xyxy.coversafetyapp.entity.respond.AllCoverBean;
import com.xyxy.coversafetyapp.entity.respond.GetCoverByUidBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCoverInfo {

    @GET("work/coverinfo/list")
    Call<AllCoverBean> allCover(@Query("limit") int limit, @Query("page") int page);

    @POST("work/coverinfo/update")
    Call<Map<String, Object>> updateCoverInfo(@Body CoverInfoEntity coverInfoEntity);


    @GET("/work/coverinfo/info/uuid")
    Observable<GetCoverByUidBean> getCoverByUid(@Query("uuid") String uuid);

}
