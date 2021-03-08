package examplesAll.broadcastReceiversExamples.activityAll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityFirstBinding
import controllerAll.utilsAll.BaseActivity

class FirstActivity : BaseActivity() {
    lateinit var viewBinding: ActivityFirstBinding
    lateinit var finishreceiver: BroadcastReceiver

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityFirstBinding

        finishreceiver  = object : BroadcastReceiver(){
            override fun onReceive(context: Context, intent: Intent) {
                var message = intent!!.getStringExtra("send_message")
                viewBinding!!.txtmessage.text = message
            }
        }

        viewBinding.txtGo.setOnClickListener {
            startActivity(Intent(this, SecondActivity:: class.java))
        }

        registerReceiver(finishreceiver, IntentFilter("sendmessage"))
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_first
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(finishreceiver)
    }

}