package examplesAll.servicesExamples.ServiceClasses

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*


class MyBindingService : Service() {
    var mRandomNumber: Int = 0
    var mIsRandomGenerate: Boolean = false
    val MIN: Int = 0
    val MAX: Int = 100
    val TAG: String = "MyBindingService"
    private val mBinder: IBinder = MyServiceBinder()

    inner class MyServiceBinder : Binder() {
       public fun getService(): MyBindingService {
            return this@MyBindingService
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
        Log.e(TAG, "Service Bind")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.e(TAG, "Service Rebind")
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRandomGenratorNumber()
        Log.e(TAG, "Destroyed")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "Service Unbind")
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand Threadid: " + Thread.currentThread().id)

        mIsRandomGenerate = true
        Thread { startRandomGeneratorNumber() }.start()
        return START_STICKY
    }

    private fun startRandomGeneratorNumber() {
        while (mIsRandomGenerate) {
            try {
                Thread.sleep(1000)
                if (mIsRandomGenerate) {
                    mRandomNumber = Random().nextInt(MAX) + MIN
                    Log.e(TAG, "Thread id: " + Thread.currentThread().id + ", Random Number: " + mRandomNumber)
                }
            } catch (e: InterruptedException) {
                Log.e(TAG, "Thread Interrupted")
            }
        }
    }


    private fun stopRandomGenratorNumber() {
        mIsRandomGenerate = false
    }

    fun getRandomNumber(): Int {
        return mRandomNumber
    }

}