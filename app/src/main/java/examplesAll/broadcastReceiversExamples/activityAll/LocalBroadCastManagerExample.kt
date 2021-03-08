package examplesAll.broadcastReceiversExamples.activityAll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityLocalBroadCastManagerBinding
import controllerAll.utilsAll.BaseActivity

class LocalBroadCastManagerExample : BaseActivity() {
    lateinit var viewBinding: ActivityLocalBroadCastManagerBinding
    lateinit var localBroadCastManager: LocalBroadcastManager
    lateinit var broadcastReceiver: BroadcastReceiver

    private var msgSent = 0

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityLocalBroadCastManagerBinding
        localBroadCastManager = LocalBroadcastManager.getInstance(this@LocalBroadCastManagerExample)

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                var message = intent!!.getStringExtra("send_message")
                viewBinding!!.txtmessage.text = message + msgSent + " times"
            }
        }

        viewBinding!!.txtSendBroadcast.setOnClickListener {
            val intent = Intent("REFRESH")
            msgSent = msgSent + 1
            intent.putExtra("send_message", "Broadcast received")
            localBroadCastManager.sendBroadcast(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_local_broad_cast_manager
    }

    override fun onStart() {
        super.onStart()
        localBroadCastManager.registerReceiver(broadcastReceiver, IntentFilter("REFRESH"))
    }

    override fun onStop() {
        super.onStop()
        localBroadCastManager.unregisterReceiver(broadcastReceiver)
    }

}