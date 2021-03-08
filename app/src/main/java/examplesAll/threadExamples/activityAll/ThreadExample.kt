package examplesAll.threadExamples.activityAll

import android.util.Log
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityThreadExampleBinding
import controllerAll.utilsAll.BaseActivity

class ThreadExample : BaseActivity() {
    lateinit var viewBinding: ActivityThreadExampleBinding
    var startLoop: Boolean = false
    var startCustomLoop: Boolean = false

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityThreadExampleBinding

        viewBinding.txtStartThread.setOnClickListener {
            startLoop = true
            while (startLoop)
                Log.e("checkapp", "id:" + Thread.currentThread().id)
        }

        viewBinding.txtStopThread.setOnClickListener {
            startLoop = false
        }

        viewBinding.txtAnotherStartThread.setOnClickListener {
            startCustomLoop = true
            Log.e("checkapp", "txtAnotherStartThread click")

          //  Thread { while (startCustomLoop) Log.e("checkapp", "id") }.start()

            Thread{
                while (startCustomLoop)
                    Log.e("checkapp", "custom thread id:" + Thread.currentThread().id)
            }.start()
        }

        viewBinding.txtAnotherStopThread.setOnClickListener {
            Log.e("checkapp", "txtAnotherStopThread click")
            startCustomLoop = false
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_thread_example
    }
}