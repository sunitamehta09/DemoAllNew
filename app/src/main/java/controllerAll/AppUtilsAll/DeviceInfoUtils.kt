package controllerAll.AppUtilsAll

import android.content.ClipData
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.text.ClipboardManager
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class DeviceInfoUtils {
    companion object {

        @JvmStatic
        fun copyToClipboad(context: Context, token: String?) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.text = token
            } else {
                val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = ClipData
                        .newPlainText("message", token)
                clipboard.setPrimaryClip(clip)
            }
        }


        /** Returns the consumer friendly device name  */
        @JvmStatic
        fun getDeviceName(): String{
            val manufacturer: String = Build.MANUFACTURER
            val model: String = Build.MODEL
            return model
        }

        @JvmStatic
        fun getDeviceID(context: Context): String {
// 2 compute DEVICE ID
            val m_szDevIDShort = ("35"
                    + // we make this look like a valid IMEI
                    Build.BOARD.length % 10 + Build.BRAND.length % 10 + Build.CPU_ABI.length % 10 + Build.DEVICE.length % 10 + Build.DISPLAY.length % 10 + Build.HOST.length % 10 + Build.ID.length % 10 + Build.MANUFACTURER.length % 10 + Build.MODEL.length % 10 + Build.PRODUCT.length % 10 + Build.TAGS.length % 10 + Build.TYPE.length % 10 + Build.USER.length % 10) // 13 digits
            // 3 android ID - unreliable
            val m_szAndroidID = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
            )
            // 4 wifi manager, read MAC address - requires
// android.permission.ACCESS_WIFI_STATE or comes as null
            val wm =
                    context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val m_szWLANMAC = wm.connectionInfo.macAddress


// 6 SUM THE IDs
            val m_szLongID = m_szDevIDShort + m_szAndroidID + m_szWLANMAC
            println("m_szLongID $m_szLongID")
            var m: MessageDigest? = null
            try {
                m = MessageDigest.getInstance("MD5")
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            m!!.update(m_szLongID.toByteArray(), 0, m_szLongID.length)
            val p_md5Data = m.digest()
            var m_szUniqueID = String()
            for (i in p_md5Data.indices) {
                val b = 0xFF and p_md5Data[i].toInt()
                // if it is a single digit, make sure it have 0 in front (proper
// padding)
                if (b <= 0xF) m_szUniqueID += "0"
                // add number to string
                m_szUniqueID += Integer.toHexString(b)
            }
            m_szUniqueID = m_szUniqueID.toUpperCase()
            Log.i("DeviceID", m_szUniqueID)
            MyLogger.e("DeviceIdCheck", "DeviceId that generated MPreferenceActivity:$m_szUniqueID")
            return m_szUniqueID
        }

    }


}