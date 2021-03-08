package controllerAll.utilsAll

import android.content.Context
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class AppUtils {
    companion object{
        var gson: Gson? = null

        @JvmStatic
        fun showToast(context: Context, message: String?) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        @JvmStatic
        fun snackBarShow(context: Context, coordinatorLayout: CoordinatorLayout, message: String?) {
            val snackbar = Snackbar
                    .make(coordinatorLayout, message.toString(), Snackbar.LENGTH_LONG)
            snackbar.show()
        }

        @JvmStatic
        fun getGsonInstance(): Gson? {
            if (gson == null) gson = Gson()
            return gson
        }

    }
}