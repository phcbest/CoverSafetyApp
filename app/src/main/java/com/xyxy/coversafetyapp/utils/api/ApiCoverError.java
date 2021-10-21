package com.xyxy.coversafetyapp.utils.api;

import com.xyxy.coversafetyapp.entity.respond.GetAllCoverErrorBean;

import java.io.File;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCoverError {

    @GET("/work/covererror/list")
    Observable<GetAllCoverErrorBean> getAllCoverError(@Query("limit") int limit, @Query("page") int page);

    @Multipart
    @POST("/work/covererror/postFile/{euid}")
    Observable<Map<String, String>> putFile(@Path("euid") String euid, @Part MultipartBody.Part file);

}
