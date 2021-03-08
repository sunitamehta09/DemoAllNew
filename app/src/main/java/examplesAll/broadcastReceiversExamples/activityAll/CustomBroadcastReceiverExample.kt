package examplesAll.broadcastReceiversExamples.activityAll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityCustomBroadcastReceiverExampleBinding
import controllerAll.utilsAll.BaseActivity

class CustomBroadcastReceiverExample : BaseActivity() {
    lateinit var viewBinding: ActivityCustomBroadcastReceiverExampleBinding
    var broadcastReceiver: CustomBroadcastReceiver? = CustomBroadcastReceiver()
    private var msgSent = 0

    inner class CustomBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var message = intent!!.getStringExtra("send_message")
            viewBinding!!.txtmessage.text = message + msgSent + " times"
        }
    }

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityCustomBroadcastReceiverExampleBinding

        viewBinding!!.txtSendBroadcast.setOnClickListener {
            val intent = Intent("custom_receiver_action")
            msgSent = msgSent + 1
            intent.putExtra("send_message", "Broadcast received")
            sendBroadcast(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        val intent = IntentFilter("custom_receiver_action")
        registerReceiver(broadcastReceiver, intent)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_custom_broadcast_receiver_example
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

}