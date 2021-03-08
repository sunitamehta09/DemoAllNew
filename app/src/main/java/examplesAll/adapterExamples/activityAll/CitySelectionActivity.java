package examplesAll.adapterExamples.activityAll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import controllerAll.utilsAll.AppUtils;
import controllerAll.AppUtilsAll.MyLogger;
import controllerAll.StringUtilsAll.StringUtils;
import controllerAll.keyboardUtilsAll.KeyBoardCheck;
import controllerAll.keyboardUtilsAll.KeyboardFunctions;
import controllerAll.keyboardUtilsAll.callBacksAll.OnKeyboardVisibilityListener;
import examplesAll.adapterExamples.UtilsAll.CityUtils;
import examplesAll.adapterExamples.adapterAll.CityListAdapter;
import examplesAll.adapterExamples.adapterAll.SelectedCityListAdapter;
import examplesAll.adapterExamples.callBacksAll.CityClickListener;
import examplesAll.adapterExamples.viewModelsAll.State;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityCitySelectionBinding;

import java.util.ArrayList;

public class CitySelectionActivity extends AppCompatActivity {
    private ActivityCitySelectionBinding viewBinding;
    private ArrayList<State> states = new ArrayList<>();
    private ArrayList<State> selectedStates = new ArrayList<>();
    private CityListAdapter cityadapter;
    private SelectedCityListAdapter selectedCityListAdapter;
    private boolean isChange = false;
    private OnKeyboardVisibilityListener onKeyboardVisibilityListener;
    private CityClickListener cityClickListener;
    private static String TAG = "CitySelectionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(CitySelectionActivity.this, R.layout.activity_city_selection);

        states = CityUtils.getStatesData(CitySelectionActivity.this);
        MyLogger.e(TAG, "CitySelectionActivity size:" +states.size());

        for(int i=0; i<states.size();i++){
            if(states.get(i).getIs_check().equals("Yes"))
                selectedStates.add(states.get(i));
        }

        cityClickListener = new CityClickListener() {
            @Override
            public void clickCity(String type, String id, String isCheck) {
                if (type.equals("delete")) {
                    for (int i = 0; i < selectedStates.size(); i++) {
                        if (id == selectedStates.get(i).getId()) {
                            selectedStates.remove(i);
                            selectedCityListAdapter.dataChange(selectedStates);
                        }
                    }
                    for (int i = 0; i < states.size(); i++) {
                        if (states.get(i) == null)
                            continue;
                        if (states.get(i).getId().equals(id)) {
                            if (isCheck.equalsIgnoreCase("No"))
                                states.get(i).setIs_check("Yes");
                            else
                                states.get(i).setIs_check("No");
                        }
                    }
                    cityadapter.dataChange(states);
                } else if (type.equals("select")) {
                    viewBinding.selectedCities.requestFocus();
                    for (int i = 0; i < states.size(); i++) {
                        if (states.get(i) == null)
                            continue;
                        if (states.get(i).getId().equals(id)) {
                            if (isCheck.equalsIgnoreCase("No")) {
                                states.get(i).setIs_check("Yes");
                                selectedStates.add(0, states.get(i));
                            } else {
                                states.get(i).setIs_check("No");
                                for (int j = 0; j < selectedStates.size(); j++) {
                                    if (selectedStates.get(j).getId().equals(id)) {
                                        selectedStates.remove(j);
                                    }
                                }
                            }
                            selectedCityListAdapter.dataChange(selectedStates);
                            cityadapter.dataChange(states);
                        }
                    }
                }
            }
        };

        onKeyboardVisibilityListener = new OnKeyboardVisibilityListener() {
            @Override
            public void onVisibilityChanged(boolean visible) {
                if (visible) {
                    viewBinding.searchtext.requestFocus();
                } else {
                    viewBinding.selectedCities.requestFocus();
                }
            }
        };

        new KeyBoardCheck().setKeyboardListener(onKeyboardVisibilityListener, viewBinding.contentCitySelection);
        viewBinding.selectedCities.requestFocus();

        viewBinding.searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().toLowerCase();
                if (str.equalsIgnoreCase(""))
                    viewBinding.imgSearchClose.setImageDrawable(getResources().getDrawable(R.drawable.city_search));
                else
                    viewBinding.imgSearchClose.setImageDrawable(getResources().getDrawable(R.drawable.city_cross_btn));

                ArrayList<State> states2 = new ArrayList<>();
                for (int i = 0; i < states.size(); i++) {
                    if (str.isEmpty()) {
                        states2.add(states.get(i));
                    } else {
                        if (states.get(i) == null)
                            continue;
                        if (states.get(i).getName().toLowerCase().startsWith(str)) {
                            states2.add(states.get(i));
                        }
                    }
                }

                if (states2.size() > 0) {
                    viewBinding.txtNoResult.setVisibility(View.GONE);
                    viewBinding.citylist.setVisibility(View.VISIBLE);
                } else {
                    viewBinding.txtNoResult.setVisibility(View.VISIBLE);
                    viewBinding.citylist.setVisibility(View.GONE);
                }
                cityadapter.dataChange(states2);
            }
        });

        viewBinding.imgSearchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardFunctions.hideKeyboard(CitySelectionActivity.this, viewBinding.searchtext);
                viewBinding.selectedCities.requestFocus();
                viewBinding.searchtext.setText("");
                cityadapter.dataChange(states);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        viewBinding.citylist.setLayoutManager(layoutManager);
        cityadapter = new CityListAdapter(this, states, cityClickListener);
        viewBinding.citylist.setAdapter(cityadapter);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        viewBinding.selectedCities.setLayoutManager(layoutManager1);
        selectedCityListAdapter = new SelectedCityListAdapter(this, selectedStates, cityClickListener);
        viewBinding.selectedCities.setAdapter(selectedCityListAdapter);

        viewBinding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.showToast(CitySelectionActivity.this, "Selected Cities: "+StringUtils.getSelectedCity(selectedStates));
            }
        });
    }

    private void setSelectedCities() {
    }
}