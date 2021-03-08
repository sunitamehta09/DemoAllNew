package controllerAll.AppUtilsAll

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager

class ScreenUtils {

    companion object{
        @JvmStatic
        fun getScreenSize(c: Context): Point? {
            var display: Display? = null
            if (c.getSystemService(Context.WINDOW_SERVICE) != null) {
                display =
                        (c.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            }
            val size = Point()
            display?.getSize(size)
            return size
        }

    }
}