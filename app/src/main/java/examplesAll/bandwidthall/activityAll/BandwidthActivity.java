package examplesAll.bandwidthall.activityAll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import examplesAll.bandwidthall.callBacksAll.SpeedListener;
import examplesAll.bandwidthall.classesAll.CheckInternetSpped;

import android.os.Bundle;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityBandwidthBinding;

// https://medium.com/android-news/designing-android-apps-to-handle-slow-network-speed-dedc04119aac
public class BandwidthActivity extends AppCompatActivity implements SpeedListener {
    ActivityBandwidthBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(BandwidthActivity.this, R.layout.activity_bandwidth);

        viewBinding.txtFindNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CheckInternetSpped(BandwidthActivity.this).checkNetworkSpeed();
                viewBinding.txtNetwork.setText(new CheckInternetSpped(BandwidthActivity.this).checkInternetSource());
            }
        });
    }

    @Override
    public void okSpeed(int kilobytePerSec) {
        updateStatus("ok", kilobytePerSec);
    }

    private void updateStatus(final String value, final int bandwidth) {
        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              viewBinding.txtSpeedValue.setText("Network speed is "+value);
                              viewBinding.txtBandWidthValue.setText(bandwidth +"kb/s");
                          }
                      }
        );
   }

    @Override
    public void slowSpeed(int kilobytePerSec) {
        updateStatus("slow", kilobytePerSec);
    }
}