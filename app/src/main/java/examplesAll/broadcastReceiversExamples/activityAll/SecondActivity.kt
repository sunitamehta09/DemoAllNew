package examplesAll.broadcastReceiversExamples.activityAll

import android.content.BroadcastReceiver
import android.content.Intent
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivitySecondBinding
import controllerAll.utilsAll.BaseActivity

class SecondActivity : BaseActivity() {
    lateinit var viewBinding: ActivitySecondBinding
    lateinit var finishreceiver: BroadcastReceiver

    override fun initView() {
        viewBinding = viewBaseBinding as ActivitySecondBinding

        viewBinding.txtSendBroadcast.setOnClickListener {
            val intent = Intent()
            intent.putExtra("send_message", "Hi Hello")
            intent.action = "sendmessage"
            sendBroadcast(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_second
    }
}