package examplesAll.broadcastReceiversExamples.activityAll

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityInternetConnectorReceiverExampleBinding
import controllerAll.utilsAll.AppUtils
import controllerAll.utilsAll.BaseActivity
import controllerAll.utilsAll.ControllerKotlin.Companion.activityPaused
import controllerAll.utilsAll.ControllerKotlin.Companion.activityResumed
import examplesAll.broadcastReceiversExamples.registersAll.InternetConnectedReceiver

class InternetConnectorReceiverExample : BaseActivity() {
    lateinit var viewBinding: ActivityInternetConnectorReceiverExampleBinding
    var internetConnectedReceiver: InternetConnectedReceiver = InternetConnectedReceiver()

    companion object {
        fun changeSnackBar(isConnected: Boolean, context: Context) {
            Log.e("checkapp ", "changeSnackBar : $isConnected")
            if (isConnected)
                AppUtils.showToast(context, "Internet Connected")
            else
                AppUtils.showToast(context, "Internet Disconnected")
        }
    }

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityInternetConnectorReceiverExampleBinding
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_internet_connector_receiver_example
    }

    override fun onPause() {
        super.onPause()
        activityPaused() // On Pause notify the Application
    }

    override fun onResume() {
        super.onResume()
        activityResumed() // On Resume notify the Application
    }

    override fun onStart() {
        super.onStart()
        val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(internetConnectedReceiver!!, intent)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(internetConnectedReceiver!!)
    }

}