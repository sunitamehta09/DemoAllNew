package examplesAll.broadcastReceiversExamples.registersAll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import controllerAll.utilsAll.AppUtils


class DynamicReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent!!.action) {
            val noConnectivity = intent!!.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            )
            if (noConnectivity)
                AppUtils.showToast(context!!, "Disconnected")
            else
                AppUtils.showToast(context!!, "Connected")
        }

    }
}