package examplesAll.fragmentsExamples.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.LayoutFragmentTwoBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import examplesAll.fragmentsExamples.callBacks.ReceiverMessageListener;

public class ReceiveMessageFragment extends Fragment implements View.OnClickListener{
    ReceiverMessageListener receiverMessage;
    private LayoutFragmentTwoBinding viewBinding;
    private static final String TAG = "ReceiveMessageFragment";

    public ReceiveMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(android.content.Context Context) {
        super.onAttach(Context);
        try {
            if (Context instanceof ReceiverMessageListener) {
                receiverMessage = (ReceiverMessageListener) Context;
            }
        }catch (Exception e){
            Log.e(TAG, "onAttach: "+e.toString());
        }
    }

    public void receiveMsgFromA(String text)
    {
        viewBinding.showMessageFromFragmentA.setText(text);
    }

   public void receiveMsgFromActivity(String text)
    {
        viewBinding.showMessageFromActivity.setText(text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_two, container, false);
        viewBinding.clickBtn.setOnClickListener(this);
        return viewBinding.getRoot();
    }

   @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clickBtn:
                try {
                    String message = viewBinding.typeMessage.getText().toString();
                    Log.e(TAG, "sendmsg: " + message);
                    receiverMessage.sendMessageToA(message);
                } catch (Exception e) {
                    Log.e(TAG, "ex: " + e.toString());
                }

                break;
        }
    }

}
