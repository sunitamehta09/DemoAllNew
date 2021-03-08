package examplesAll.servicesExamples.activityAll

import android.content.Intent
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityServiceLifecycleBinding
import controllerAll.utilsAll.BaseActivity
import examplesAll.servicesExamples.ServiceClasses.MyService

class ServiceLifeCycleExample : BaseActivity() {
    lateinit var viewBinding: ActivityServiceLifecycleBinding

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityServiceLifecycleBinding

        viewBinding.layoutStartService.setOnClickListener {
            /*  Since every service needs to be started from some other Android component.
                         Starting the service is similar to starting any other activity. You just need to
                         create an Intent object specifying the service and then call startService() .*/
            intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        viewBinding.layoutStopService.setOnClickListener {
            intent = Intent(this, MyService::class.java)
            stopService(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_service_lifecycle
    }

}