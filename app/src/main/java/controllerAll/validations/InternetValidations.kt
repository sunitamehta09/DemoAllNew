package controllerAll.validations

import android.content.Context
import android.net.ConnectivityManager


class InternetValidations{
    companion object{

        //Check internet connectivity
        @JvmStatic
        fun isNetWorkStatusAvialable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo != null) {
                    if (networkInfo.isConnected) return true
                }
            }
            return false
        }
    }
}