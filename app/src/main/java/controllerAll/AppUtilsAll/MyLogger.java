package controllerAll.AppUtilsAll;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.self.demoaall.BuildConfig;

/**
 * Custom Logger class
 */
public class MyLogger {
    public static final String ERROR = "Error";
    /**
     * Flag to show logs in debugging mode
     */
    private static boolean DEBUG = BuildConfig.DEBUG;

    public static void i(String tag, String msg) {
        if (DEBUG)
            if (msg != null)
                Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            if (msg != null)
                Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            if (msg != null)
                Log.d(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG)
            if (msg != null)
                Log.w(tag, msg);
    }

    /**
     * Static method to show a toast
     *
     * @param mContext Context of the calling class
     * @param text     Text to show in toast
     */
    public static void showToast(Context mContext, String text) {
        if (DEBUG)
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}
