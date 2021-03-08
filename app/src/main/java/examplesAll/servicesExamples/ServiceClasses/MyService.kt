package examplesAll.servicesExamples.ServiceClasses

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService : Service(){
    private val mStartMode // indicates how to behave if the service is killed
            = 0
    private val mBinder //    interface for clients that bind
            : IBinder? = null
    private val mAllowRebind //   indicates whether onRebind should be used
            = false

    /*
    Regardless of whether you want your service to be binded
    or not you should always implement onBind. You should return null if you
    dont want it to bind
    */

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    /** Called when the service is being created.  */
    override fun onCreate() {
        Toast.makeText(this, "onCreate Called", Toast.LENGTH_LONG).show()
    }

    /** The service is starting, due to a call to startService()  */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "onStartCommand Called", Toast.LENGTH_LONG).show()
        return mStartMode
    }

    /** Called when all clients have unbound with unbindService()  */
    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "onUnbind Called", Toast.LENGTH_LONG).show()
        return mAllowRebind
    }

    /** Called when a client is binding to the service with bindService() */
    override fun onRebind(intent: Intent?) {
        Toast.makeText(this, "onRebind Called", Toast.LENGTH_LONG).show()
    }

    /** Called when The service is no longer used and is being destroyed  */
    override fun onDestroy() {
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_LONG).show()
    }


}