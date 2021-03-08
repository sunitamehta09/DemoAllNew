package apiServiceAll.ApiClientEj;

import android.content.Context;


import com.google.gson.JsonObject;

import controllerAll.utilsAll.AppConstants;
import controllerAll.AppSharedPreferencesAll.AppSharedPreferenceEj;
import controllerAll.AppUtilsAll.MyLogger;
import controllerAll.validations.InternetValidations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiErrorEj {

    Context mContext;

    public ApiErrorEj(Context mContext, String api, String error) {
        this.mContext = mContext;
        JsonObject object = new JsonObject();
        object.addProperty("message", error);
        object.addProperty("api", api);
        object.addProperty("platform", "Android");
        sendError(object.toString());
        MyLogger.e("checkapp ApiError: ",api+" error: "+  object.toString());
    }

    private void sendError(String errorJson) {
        if (InternetValidations.Companion.isNetWorkStatusAvialable(mContext)){
            MyLogger.e("checkapp", "sendError url: " + ApiControllerEj.getErrorUrl() +"error-log");
            Call<JsonObject> call = ApiClientEj.Companion.getClient(mContext).create(ApiInterfaceEj.class)
                    .sendError(AppSharedPreferenceEj.Companion.getStringPreferences(mContext, AppConstants.PREF_ACCESSTOKEN_EJ),errorJson);
            MyLogger.e("checkapp", "sendError param: " + errorJson + " - "
                    +AppSharedPreferenceEj.Companion.getStringPreferences(mContext, AppConstants.PREF_ACCESSTOKEN_EJ));
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    MyLogger.e("checkapp", "sendError res: " + response.body());
               }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    call.cancel();
                    MyLogger.e("checkapp", "sendError res: " + "on failure");
                    MyLogger.e("checkapp", "onFailure: " + t.getMessage() + " - "+t.toString());
                    MyLogger.e("checkapp", "oncall: " + call.toString());
                }
            });
        }
    }
}
