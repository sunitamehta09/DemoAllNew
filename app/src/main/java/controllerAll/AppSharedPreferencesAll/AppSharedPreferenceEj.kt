package controllerAll.AppSharedPreferencesAll

import android.content.Context
import android.content.SharedPreferences
import controllerAll.utilsAll.AppConstants

class AppSharedPreferenceEj {
        companion object {
            const val APP_PREFERENCE_FILE_NAME = "Ej_App_Preferences"

            @JvmStatic
            fun setStringPreferences(context: Context, keyName: String, value: String) {
                val sp: SharedPreferences = context.getSharedPreferences(
                        APP_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE
                )
                val e = sp.edit()
                e.putString(keyName, value)
                e.commit()
            }

            @JvmStatic
            fun getStringPreferences(context: Context, keyName: String) : String {
                val sp: SharedPreferences = context.getSharedPreferences(
                        APP_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE
                )
                var value : String? = sp.getString(keyName, "")
                if (value!=null)
                    return value
                return ""
            }

            @JvmStatic
            fun clearSharedPreferences(context: Context) {
                val sp: SharedPreferences = context.getSharedPreferences(
                        APP_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE
                )
                val e = sp.edit()
                e.clear()
                e.commit()
            }

            @JvmStatic
            fun removeSharedPreferences(context: Context, keyName: String) {
                val sp: SharedPreferences = context.getSharedPreferences(
                        APP_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE
                )
                val e = sp.edit()
                e.remove(keyName).commit()
            }

            @JvmStatic
            fun clearAllSharedPreferences(context: Context){
                setStringPreferences(
                        context, AppConstants.PREF_ACCESSTOKEN_EJ, ""
                )
                setStringPreferences(
                        context, AppConstants.USER_NAME_EJ, ""
                )
         }
    }
}