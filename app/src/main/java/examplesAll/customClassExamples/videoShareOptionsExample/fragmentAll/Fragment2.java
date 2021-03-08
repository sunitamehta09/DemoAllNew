package examplesAll.customClassExamples.videoShareOptionsExample.fragmentAll;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import callBacksAll.OptionsClickListener;
import examplesAll.customClassExamples.videoShareOptionsExample.callbacksAll.SocialOptionsClickListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.LayoutFragment2Binding;

public class Fragment2 extends Fragment {
    LayoutFragment2Binding viewBinding;
    SocialOptionsClickListener optionsClickListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.layout_fragment2,container,false);

        optionsClickListener = (SocialOptionsClickListener)getActivity();
        viewBinding.shareView.setOptionsClickListener(optionsClickListener);
        viewBinding.shareView.setIsFragment1(false);

        return viewBinding.getRoot();
    }
}