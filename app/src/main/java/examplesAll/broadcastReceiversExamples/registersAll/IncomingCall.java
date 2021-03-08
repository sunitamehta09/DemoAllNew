package examplesAll.broadcastReceiversExamples.registersAll;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class IncomingCall extends BroadcastReceiver {
    private android.content.Context Context;

    @Override
    public void onReceive(android.content.Context Context, Intent intent) {
        try {
            String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if (phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(Context, "Telephone EXTRA_STATE_RINGING", Toast.LENGTH_LONG).show();
            }

            if (phoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Toast.makeText(Context, "Telephone EXTRA_STATE_OFFHOOK", Toast.LENGTH_LONG).show();
            }

            if (phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(Context, "Telephone EXTRA_STATE_IDLE", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Incoming call", "Exception:" + e.toString());
        }


    }
}
