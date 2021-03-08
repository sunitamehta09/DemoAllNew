package apiServiceAll.ApiClientEj;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceEj {
    @FormUrlEncoded
    @POST("/error-log")
    Call<JsonObject> sendError(@Field("accessToken") String accessToken,
                               @Field("json") String lang);

    @FormUrlEncoded
    @POST("/notification")
    Call<JsonObject> getNotifications(@Field("lang_code") String lang_code,
                                      @Field("page_number") String page_number, @Field("record") String record);


}
