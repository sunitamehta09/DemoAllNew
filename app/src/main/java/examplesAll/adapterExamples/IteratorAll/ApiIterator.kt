package examplesAll.adapterExamples.IteratorAll

import android.content.Context
import apiServiceAll.ApiClientEj.ApiClientEj
import apiServiceAll.ApiClientEj.ApiInterfaceEj
import com.earshot.apiservice.RetrofitApiCallBack
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.self.demoaall.R
import controllerAll.utilsAll.AppUtils
import controllerAll.AppUtilsAll.MyLogger
import controllerAll.validations.InternetValidations
import examplesAll.adapterExamples.viewModelsAll.NotificationsResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiIterator {
    companion object {
        @JvmStatic
        fun callNotificationsApi(
                context: Context,
                accessToken: String,
                pageNumber: String,
                offset: String,
                apiListener: RetrofitApiCallBack<NotificationsResponseData>) {
            if (InternetValidations.Companion.isNetWorkStatusAvialable(context)) {
                apiListener.showProgress(true)
                val call: Call<JsonObject> =
                        ApiClientEj.getClient(context)!!.create(ApiInterfaceEj::class.java)
                                .getNotifications("en", pageNumber, offset);

                call.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        apiListener.showProgress(false)
                        MyLogger.e("checkapp NotificationsResponseData>>", response.body().toString());
                        var notificationsData: NotificationsResponseData =
                                Gson().fromJson(response.body().toString(), NotificationsResponseData::class.java)
                        if (notificationsData.message.equals("success")) {
                            apiListener.onApiSuccess(notificationsData)
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        call.cancel()
                        apiListener.showProgress(false)
                        apiListener.onApiError(t.message!!, 400)
                    }
                })
            } else {
                AppUtils.Companion.showToast(context, context.getString(R.string.alert_no_internet))
            }
        }
    }
}