package examplesAll.broadcastReceiversExamples.activityAll

import android.content.IntentFilter
import com.self.demoaall.R
import controllerAll.utilsAll.BaseActivity
import examplesAll.broadcastReceiversExamples.registersAll.PowerConnectedReceiver

/*

When we use custom broadcast receiver and statically define in manifest file
before oreo version it will be works fine.
But in Oreo or after oreo version it is compulsary to register broadcast receiver dynamically.
 */

class StaticBroadcastReceiverExample : BaseActivity() {
    var powerConnectedReceiver: PowerConnectedReceiver?= PowerConnectedReceiver()
    var powerConnectedReceiver1: PowerConnectedReceiver?= PowerConnectedReceiver()

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_static_broadcast_receiver_example
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter("android.intent.action.ACTION_POWER_CONNECTED")
        val filter1 = IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED")
        registerReceiver(powerConnectedReceiver,filter)
        registerReceiver(powerConnectedReceiver1,filter1)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(powerConnectedReceiver)
        unregisterReceiver(powerConnectedReceiver1)
    }

}