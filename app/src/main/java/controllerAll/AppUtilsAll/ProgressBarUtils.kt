package controllerAll.AppUtilsAll

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.WindowManager
import com.self.demoaall.R

class ProgressBarUtils {
    companion object{
        private var pDialog: ProgressDialog? = null

        fun pdStart(context: Context?) {
            if (pDialog != null)
                pdStop()
            pDialog = ProgressDialog(context)
            try {
                pDialog!!.show()
            } catch (e: WindowManager.BadTokenException) {
                e.printStackTrace()
            }
            try {
                pDialog!!.setCancelable(false)
                pDialog!!.window!!.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
                pDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                pDialog!!.setContentView(R.layout.progress_dialog)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        fun pdStop() {
            if (pDialog != null) {
                pDialog!!.dismiss()
            }
        }

    }
}