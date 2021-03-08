package examplesAll.servicesExamples.activityAll

import android.content.Intent
import android.util.Log
import android.view.View
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityBindingExampleBinding
import controllerAll.utilsAll.BaseActivity
import examplesAll.servicesExamples.ServiceClasses.MyIntentService

class IntentServiceExample : BaseActivity() {
    lateinit var viewBinding : ActivityBindingExampleBinding
    private var serviceIntent: Intent? = null

    private var mStopLoop = false
    val TAG : String = "IntentServiceExample"

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityBindingExampleBinding

        Log.e(TAG, "main Threadid: " + Thread.currentThread().id)

        serviceIntent = Intent(this, MyIntentService::class.java)

        viewBinding.buttonBindService.visibility = View.GONE
        viewBinding.buttonUnBindService.visibility = View.GONE
        viewBinding.buttonGetRandomNumber.visibility = View.GONE
        viewBinding.textViewthreadCount.visibility = View.GONE

        viewBinding.buttonThreadStarter.setOnClickListener {
            mStopLoop = true;
            startService(serviceIntent)
        }

        viewBinding.buttonStopthread.setOnClickListener {
            stopService(serviceIntent)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_binding_example
    }

}