package examplesAll.servicesExamples.activityAll

import android.content.Intent
import androidx.core.content.ContextCompat
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityForegroundServiceExampleBinding
import controllerAll.utilsAll.BaseActivity
import examplesAll.servicesExamples.ServiceClasses.MyForegroundSevice

class ForegroundServiceExample : BaseActivity() {
    lateinit var viewBinding: ActivityForegroundServiceExampleBinding
    var serviceIntent: Intent ? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityForegroundServiceExampleBinding

        serviceIntent = Intent(this, MyForegroundSevice::class.java)

        viewBinding.buttonThreadStarter.setOnClickListener {
            ContextCompat.startForegroundService(this, serviceIntent!!)
        }

        viewBinding.buttonStopthread.setOnClickListener {
            stopService(serviceIntent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_foreground_service_example
    }

}