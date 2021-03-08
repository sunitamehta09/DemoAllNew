package examplesAll.servicesExamples.ServiceClasses

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class ShowCurrentDateService : Service(){
    val mBinder : IBinder = MyCurrentDateService()
    val TAG : String = "ShowCurrentDateService"

    inner class MyCurrentDateService : Binder(){
        public fun getService(): ShowCurrentDateService{
            return this@ShowCurrentDateService
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind Threadid: " + Thread.currentThread().id)
        return mBinder
    }

    fun showCurrentDate(): Date? {
        return Calendar.getInstance().getTime();
    }

}

