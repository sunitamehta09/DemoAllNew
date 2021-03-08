package com.self.demoaall

import android.content.Intent
import android.os.Handler
import com.self.demoaall.databinding.ActivitySplashBinding
import controllerAll.utilsAll.BaseActivity

class SplashActivity : BaseActivity() {
    lateinit var activityMainBinding: ActivitySplashBinding
    override fun initView() {
        activityMainBinding = viewBaseBinding as ActivitySplashBinding

        Handler().postDelayed(Runnable {
            val intent: Intent = Intent(this@SplashActivity, ShowOptions::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }, 1500)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
}