package examplesAll.fragmentsExamples.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.LayoutFragmentOneBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import examplesAll.fragmentsExamples.callBacks.SendMessageListener;

public class SendMessageFragment extends Fragment implements View.OnClickListener {
    SendMessageListener sendMessage;
    LayoutFragmentOneBinding viewBinding;

/*    The Custom Interface namely SendMessage is initialised in the onAttach method above.
     This interface would be implemented in the MainActivity.java that we’ll be seeing shortly.
     */
    @Override
    public void onAttach(android.content.Context Context) {
        super.onAttach(Context);
        try {
            if (Context instanceof SendMessageListener) {
                sendMessage = (SendMessageListener) Context;
            }
        } catch (Exception e) {
            Log.e("send message", e.toString());
        }
    }

    public SendMessageFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_one, container, false);
        viewBinding.clickBtn.setOnClickListener(this);
        return viewBinding.getRoot();
    }

    public void receiveMsgFromB(String text){
        viewBinding.showMessageFromFragmentB.setText(text);
    }

    public void receiveMsgFromActivity(String text){
        viewBinding.showMessageFromActivity.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickBtn:
                try {
                    String message = viewBinding.typeMessage.getText().toString();
                    Log.e("SendMessageFragment", "sendmsg: " + message);
                    sendMessage.sendMessageToB(message);
                } catch (Exception e) {
                    Log.e("SendMessageFragment", "ex: " + e.toString());
                }
                break;
        }
    }

}
