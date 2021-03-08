package examplesAll.broadcastReceiversExamples.activityAll

import android.content.IntentFilter
import android.net.ConnectivityManager
import com.self.demoaall.R
import controllerAll.utilsAll.BaseActivity
import examplesAll.broadcastReceiversExamples.registersAll.DynamicReceiver

// https://codinginflow.com/tutorials/android/broadcastreceiver/part-2-dynamic-broadcastreceiver
class DynamicBroadcastReceiverExample : BaseActivity(){
    var dynamicReceiver: DynamicReceiver?= DynamicReceiver()

    override fun initView() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_dynamic_register
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(dynamicReceiver!!, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(dynamicReceiver!!)
    }

}