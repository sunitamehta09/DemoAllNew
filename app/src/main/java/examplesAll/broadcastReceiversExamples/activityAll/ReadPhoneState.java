package examplesAll.broadcastReceiversExamples.activityAll;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.self.demoaall.R;

public class ReadPhoneState extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_phone_state);
        Log.e("screen", "ReadPhoneState open");

    }
}
