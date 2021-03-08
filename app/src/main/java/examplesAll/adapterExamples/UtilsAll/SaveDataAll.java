package examplesAll.adapterExamples.UtilsAll;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import controllerAll.utilsAll.AppConstants;
import controllerAll.AppSharedPreferencesAll.AppSharedPreferenceEj;
import controllerAll.utilsAll.AppUtils;

public class SaveDataAll {

    public static ArrayList<String> getReadNotificationData(Context context) {
        ArrayList<String> rList;
        String readNotificationList = AppSharedPreferenceEj.getStringPreferences(context, AppConstants.PREF_READNOTIFICATIONLIST);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        if (TextUtils.isEmpty(readNotificationList))
            rList = new ArrayList<String>();
        else
            rList = AppUtils.getGsonInstance().fromJson(readNotificationList, type);
        return rList;
    }
}
