package examplesAll.broadcastReceiversExamples.registersAll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import controllerAll.utilsAll.AppUtils

class PowerConnectedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_POWER_CONNECTED.equals(intent!!.getAction())) {
            AppUtils.showToast(context!!, "Power Connected")
        }
        if (Intent.ACTION_POWER_DISCONNECTED.equals(intent!!.getAction())) {
            AppUtils.showToast(context!!, "Power Disconnected")
        }
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            AppUtils.showToast(context!!, "Connectivity changed")
        }
    }

}