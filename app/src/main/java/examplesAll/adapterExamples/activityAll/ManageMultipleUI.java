package examplesAll.adapterExamples.activityAll;

import android.os.Bundle;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityManageMultipleBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import examplesAll.adapterExamples.UtilsAll.LanguageUtils;
import examplesAll.adapterExamples.adapterAll.LanguageAdapter;
import examplesAll.adapterExamples.viewModelsAll.LanguageOptionsData;
import examplesAll.adapterExamples.viewModelsAll.StatesOptionData;

public class ManageMultipleUI extends AppCompatActivity {
    private ArrayList<LanguageOptionsData> languageData = new ArrayList<>();
    private ArrayList<StatesOptionData> statesData = new ArrayList<>();
    private ActivityManageMultipleBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(ManageMultipleUI.this,R.layout.activity_manage_multiple);

        languageData = LanguageUtils.getLanguageData(ManageMultipleUI.this);
        statesData = LanguageUtils.getStatesData(ManageMultipleUI.this);

        LanguageAdapter languageAdapter = new LanguageAdapter(ManageMultipleUI.this, languageData, statesData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ManageMultipleUI.this, LinearLayoutManager.VERTICAL, false);
        viewBinding.recyclerView.setLayoutManager(layoutManager);
        viewBinding.recyclerView.setAdapter(languageAdapter);

    }
}