package examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.adapterAll.LanguageAdapter;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.utilsAll.LanguageUtils;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.viewModelsAll.LanguageOptionsData;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.viewModelsAll.StatesOptionData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.FragmentMessageBinding;

import java.util.ArrayList;

public class MessageFragment extends Fragment {
    private ArrayList<LanguageOptionsData> languageData = new ArrayList<>();
    private ArrayList<StatesOptionData> statesData = new ArrayList<>();
    private FragmentMessageBinding viewBinding;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false);

        languageData = LanguageUtils.getLanguageData(requireContext());
        statesData = LanguageUtils.getStatesData(requireContext());

        LanguageAdapter languageAdapter = new LanguageAdapter(requireContext(), languageData, statesData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        viewBinding.recyclerView.setLayoutManager(layoutManager);
        viewBinding.recyclerView.setAdapter(languageAdapter);

        return viewBinding.getRoot();


    }
}