package examplesAll.servicesExamples.ServiceClasses

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

class MyIntentService : IntentService {
    constructor() : super(null) {}

    var mRandomNumber: Int = 0
    var mIsRandomGenerate: Boolean = false
    val MIN: Int = 0
    val MAX: Int = 100
    val TAG: String = "MyIntentService"

    override fun onBind(intent: Intent?): IBinder? {
        return null
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

    override fun onHandleIntent(p0: Intent?) {
        Log.e(TAG, "onHandleIntent Threadid: " + Thread.currentThread().id)
        mIsRandomGenerate = true
        startRandomGeneratorNumber()
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