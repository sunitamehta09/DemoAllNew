package examplesAll.broadcastReceiversExamples.registersAll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import controllerAll.utilsAll.AppUtils

class BroadcastReceiver2 : BroadcastReceiver() {
    val TAG = "BroadcastReceiver2"
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = getResultExtras(true)
        var str = bundle.getString("Type")
        str = if (str == null) "Start -> $TAG" else "$str->$TAG"
        bundle.putString("Type", str)
       // Log.e("checkapp", "BroadcastReceiver2 triggered");
        // For Ordered Broadcast Receiver
        AppUtils.showToast(context!!,"BroadcastReceiver2 triggered: $str" )
        Log.e("checkapp", "BroadcastReceiver2 triggered: $str")
    }

}