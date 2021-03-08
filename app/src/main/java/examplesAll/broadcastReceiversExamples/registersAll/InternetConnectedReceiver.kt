package examplesAll.broadcastReceiversExamples.registersAll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import controllerAll.utilsAll.ControllerKotlin
import examplesAll.broadcastReceiversExamples.activityAll.InternetConnectorReceiverExample

class InternetConnectedReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            val isVisible: Boolean = ControllerKotlin.isActivityVisible() // Check if
            // activity  is visible or not
            Log.e("checkapp ", "Is activity visible : $isVisible")
            // If it is visible then trigger the task else do nothing
            if (isVisible == true) {
                val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                // Check internet connection and accrding to state change the text of activity by calling method
                if (networkInfo != null && networkInfo.isConnected) {
                    InternetConnectorReceiverExample.changeSnackBar(true, context!!)
                } else {
                    InternetConnectorReceiverExample.changeSnackBar(false, context!!)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}