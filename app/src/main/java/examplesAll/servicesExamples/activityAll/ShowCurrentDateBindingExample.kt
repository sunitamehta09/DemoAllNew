package examplesAll.servicesExamples.activityAll

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityShowCurrentDateBindingExampleBinding
import controllerAll.utilsAll.AppUtils
import controllerAll.utilsAll.BaseActivity
import examplesAll.servicesExamples.ServiceClasses.ShowCurrentDateService

class ShowCurrentDateBindingExample : BaseActivity() {
    lateinit var viewBinding: ActivityShowCurrentDateBindingExampleBinding
    var connection: ServiceConnection? = null
    var isBound: Boolean = false
    var myDateService: ShowCurrentDateService ?= null
    val TAG : String = "ShowCurrentDateActivity"

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowCurrentDateBindingExampleBinding

        viewBinding.btnDate.setOnClickListener {
            showDate()
        }

    }

    private fun showDate() {
        if(isBound)
            AppUtils.showToast(this, myDateService!!.showCurrentDate().toString())
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_current_date_binding_example
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart Threadid: " + Thread.currentThread().id)

        connection = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
                val myBinder : ShowCurrentDateService.MyCurrentDateService = iBinder as ShowCurrentDateService.MyCurrentDateService
                myDateService = myBinder.getService()
                isBound = true
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                isBound = false
            }
        }
        val intent = Intent(this, ShowCurrentDateService::class.java)
        bindService(intent, connection!!, Context.BIND_AUTO_CREATE)
        AppUtils.showToast(this, "Service Bind")
    }

    override fun onStop() {
        super.onStop()
        if(isBound){
            unbindService(connection!!)
            isBound = false
            AppUtils.showToast(this, "Service Unbind")
        }
    }
}