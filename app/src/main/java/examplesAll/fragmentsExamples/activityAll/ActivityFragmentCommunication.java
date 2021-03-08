package examplesAll.fragmentsExamples.activityAll;

// https://www.journaldev.com/14207/android-passing-data-between-fragments
// https://www.youtube.com/watch?v=i22INe14JUc

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityFragmentCommunicationBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import examplesAll.fragmentsExamples.callBacks.ReceiverMessageListener;
import examplesAll.fragmentsExamples.callBacks.SendMessageListener;
import examplesAll.fragmentsExamples.fragments.ReceiveMessageFragment;
import examplesAll.fragmentsExamples.fragments.SendMessageFragment;

public class ActivityFragmentCommunication extends AppCompatActivity
        implements View.OnClickListener, SendMessageListener, ReceiverMessageListener {
    private SendMessageFragment sendMessageFragment;
    private ActivityFragmentCommunicationBinding viewBinding;
    private ReceiveMessageFragment receiveMessageFragment;
    private static final String TAG = "ActivityFragCommunicate";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(ActivityFragmentCommunication.this, R.layout.activity_fragment_communication);
        initView();
    }

    private void initView() {
        addFragments();
        viewBinding.clickBtn.setOnClickListener(this);
    }

    private void addFragments() {
        sendMessageFragment = new SendMessageFragment();
        receiveMessageFragment = new ReceiveMessageFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutOne, sendMessageFragment)
                .replace(R.id.frameLayoutTwo, receiveMessageFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickBtn:
                String text = viewBinding.typeMessage.getText().toString();
                sendMessageFragment.receiveMsgFromActivity(text);
                receiveMessageFragment.receiveMsgFromActivity(text);
        }
    }

    @Override
    public void sendMessageToB(Object... args) {
        try {
            String val = (String) args[0];
            Log.e(TAG, "val: " + val);
            receiveMessageFragment.receiveMsgFromA(val);
            viewBinding.showMessageOfFragmentA.setText(val);
        } catch (Exception e) {
            Log.e(TAG, "ex: " + e.toString());
        }
    }

    @Override
    public void sendMessageToA(Object... args) {
        try {
            String val = (String) args[0];
            Log.e(TAG, "val: " + val);
            sendMessageFragment.receiveMsgFromB(val);
            viewBinding.showMessageOfFragmentB.setText(val);
        } catch (Exception e) {
            Log.e(TAG, "ex: " + e.toString());
        }
    }
}
