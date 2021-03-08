package examplesAll.servicesExamples.activityAll

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityBindingExampleBinding
import controllerAll.utilsAll.BaseActivity
import examplesAll.servicesExamples.ServiceClasses.MyBindingService

class GenerateRandomNumberBindingExample : BaseActivity() {
    lateinit var viewBinding : ActivityBindingExampleBinding
    var count = 0
    private var myBindingService: MyBindingService? = null
    private var isServiceBound = false
    private var serviceConnection: ServiceConnection? = null
    private var serviceIntent: Intent? = null

    private var mStopLoop = false

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityBindingExampleBinding
        serviceIntent = Intent(this, MyBindingService::class.java)

        viewBinding.buttonThreadStarter.setOnClickListener {
            mStopLoop = true;
            startService(serviceIntent)
        }

        viewBinding.buttonStopthread.setOnClickListener {
            stopService(serviceIntent)
        }

        viewBinding.buttonBindService.setOnClickListener {
            bindService()
        }

        viewBinding.buttonUnBindService.setOnClickListener {
            unbindService()
        }

        viewBinding.buttonGetRandomNumber.setOnClickListener {
            generateRandomNumber()
        }
    }


    private fun bindService() {
        if (serviceConnection == null) {
            serviceConnection = object : ServiceConnection {
                override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                    val myServiceBinder: MyBindingService.MyServiceBinder = iBinder as MyBindingService.MyServiceBinder
                    myBindingService = myServiceBinder.getService()
                    isServiceBound = true
                }

                override fun onServiceDisconnected(componentName: ComponentName) {
                    isServiceBound = false
                }
            }
        }

        bindService(serviceIntent, serviceConnection!!, Context.BIND_AUTO_CREATE)
    }

    private fun unbindService() {
        if(isServiceBound){
            unbindService(serviceConnection!!);
            isServiceBound=false;
        }
    }

    private fun generateRandomNumber() {
        if(isServiceBound){
            viewBinding!!.textViewthreadCount.setText("Random number: "+myBindingService!!.getRandomNumber());
        }else{
            viewBinding!!.textViewthreadCount.setText("Service not bound");
        }}


    override fun getLayoutId(): Int {
        return R.layout.activity_binding_example
    }

}