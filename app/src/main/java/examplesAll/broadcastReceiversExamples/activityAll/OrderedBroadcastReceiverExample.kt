package examplesAll.broadcastReceiversExamples.activityAll

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityOrderedBroadcastReceiverExampleBinding
import controllerAll.utilsAll.AppUtils
import controllerAll.utilsAll.BaseActivity
import examplesAll.broadcastReceiversExamples.registersAll.BroadcastReceiver1
import examplesAll.broadcastReceiversExamples.registersAll.BroadcastReceiver2

class OrderedBroadcastReceiverExample : BaseActivity() {
    lateinit var viewBinding: ActivityOrderedBroadcastReceiverExampleBinding
    val TAG = "OrderedBroadCastReceiver Activity"
    var broadcastReceiver1: BroadcastReceiver1? = BroadcastReceiver1()
    var broadcastReceiver2: BroadcastReceiver2? = BroadcastReceiver2()

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityOrderedBroadcastReceiverExampleBinding

        //  When there are many broadcast and you have not mentioned any priority value in manifest then Broadcast triggered  in that order in which they declare in manifest file.
        viewBinding.cvSendbroadcast.setOnClickListener {
            val intent = Intent()
            intent.action = "com.br1"
            //    intent.addCategory(Intent.CATEGORY_DEFAULT)
            sendBroadcast(intent)
        }

        //  When there are many broadcast and you have mentioned priority in manifest then
        //  they are triggered based on priority value.
        viewBinding.cvRegisterbroadcastpriority.setOnClickListener {
            val intent = Intent()
            intent.action = "com.br1"
            //  intent.addCategory(Intent.CATEGORY_DEFAULT)
            sendBroadcast(intent)
        }

        //  In previous you see that every broadcast receiver triggered  independently not an order way.
        //  to run broadcasr receiver in ordered way we use sendOrderedBroadcast().
        viewBinding.cvRegisterbroadcastordered.setOnClickListener {
            val intent = Intent()
            intent.action = "com.br1"
            //   intent.addCategory(Intent.CATEGORY_DEFAULT)
            sendOrderedBroadcast(intent, null)
        }

        // if you want to come back in activity
        viewBinding.cvRegisterbroadcastorderedBacktoactivity.setOnClickListener {
            val intent = Intent()
            intent.action = "com.br1"
            sendOrderedBroadcast(intent, null, object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val bundle = getResultExtras(true)
                    var str = bundle.getString("Type")
                    str = str + "->" + TAG
                   // snackBarShow(this@OrderedBroadcastReceiverExample, viewBinding.coordinatorLayout, str)
                    AppUtils.showToast(context!!,"OrderedBroadCastReceiver Activity: $str" )
                    Log.e("checkapp", "OrderedBroadCastReceiver Activity: $str")
                }
            }, null, Activity.RESULT_OK, null, null)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_ordered_broadcast_receiver_example
    }

    /*
    Higher the value of priority preference first that register
     */

    override fun onStart() {
        super.onStart()
        val intent = IntentFilter("com.br1")
        intent.priority = 1
        registerReceiver(broadcastReceiver1, intent)
        val intent1 = IntentFilter("com.br1")
        intent1.priority = 2
        registerReceiver(broadcastReceiver2, intent1)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver1)
        unregisterReceiver(broadcastReceiver2)
    }
}

