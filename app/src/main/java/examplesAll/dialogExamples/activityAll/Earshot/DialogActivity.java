package examplesAll.dialogExamples.activityAll.Earshot;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.self.demoaall.R;
import com.self.demoaall.databinding.LayoutDialogPopupBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import controllerAll.utilsAll.AppConstants;
import controllerAll.utilsAll.AppUtils;
import controllerAll.AppUtilsAll.MyLogger;
import examplesAll.dialogExamples.adapterAll.LanguageAdapter;
import examplesAll.dialogExamples.adapterAll.StatesAdapter;
import examplesAll.dialogExamples.callBacks.LanguageClickListener;
import examplesAll.dialogExamples.callBacks.RecycleCallBacks;
import examplesAll.dialogExamples.utilsAll.LanguageUtils;
import examplesAll.dialogExamples.viewModelsAll.LanguageOptionsData;
import examplesAll.dialogExamples.viewModelsAll.StatesOptionData;

public class DialogActivity extends AppCompatActivity {
    ArrayList<StatesOptionData> stateDataList;
    ArrayList<LanguageOptionsData> languageDataList;
    private Dialog customDialog;
    private LayoutDialogPopupBinding dialogPopupBinding;
    private ArrayList<String> langDataList = new ArrayList<String>();
    private ArrayList<String> statesDataList = new ArrayList<String>();
    private LanguageAdapter languageAdapter;
    private StatesAdapter statesAdapter;
    private ArrayList<String> savedLanguageData = new ArrayList<String>();
    private ArrayList<String> savedStatesData = new ArrayList<String>();
    private static String TAG = "DialogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getStringExtra(AppConstants.EXTRA_SCREENTYPE);

        setLayout(type);
    }

    private void setLayout(String type) {
        customDialog = new Dialog(DialogActivity.this);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (type.equals(AppConstants.SHOW_LANGUAGE_POPUP)) {
            dialogPopupBinding = (LayoutDialogPopupBinding) DataBindingUtil.inflate(LayoutInflater.from(DialogActivity.this), R.layout.layout_dialog_popup, null, false);
            customDialog.setContentView(dialogPopupBinding.getRoot());
        } else if (type.equals(AppConstants.SHOW_STATES_POPUP)) {
            dialogPopupBinding = (LayoutDialogPopupBinding) DataBindingUtil.inflate(LayoutInflater.from(DialogActivity.this), R.layout.layout_dialog_popup, null, false);
            customDialog.setContentView(dialogPopupBinding.getRoot());
        }

        customDialog.setCanceledOnTouchOutside(false);
        customDialog.setCancelable(true);
        customDialog.show();

        callViewType(type, customDialog);
    }

    private void callViewType(String type, final Dialog customDialog) {
        switch (type) {
            case AppConstants.SHOW_LANGUAGE_POPUP:
                dialogPopupBinding.txtHeader.setText(getString(R.string.choose_a_language));

                languageDataList = LanguageUtils.getLanguageData(DialogActivity.this);

                for (int i = 0; i < languageDataList.size(); i++) {
                    if (!languageDataList.get(i).value.equals(" "))
                        langDataList.add(languageDataList.get(i).value);
                    if (languageDataList.get(i).value.equals(" "))
                        languageDataList.get(i).value = "0";
                }
                MyLogger.e(TAG, "languageDataList:" + languageDataList.toString());
                MyLogger.e(TAG, "langDataList:" + langDataList.toString());

                SpannableString skipSpannable = new SpannableString(getString(R.string.done));
                skipSpannable.setSpan(new UnderlineSpan(), 0, skipSpannable.length(), 0);
                dialogPopupBinding.txtDone.setText(skipSpannable);

                GridLayoutManager rvLayoutManager = new GridLayoutManager(this, 2);
                dialogPopupBinding.rvLanguages.setLayoutManager(rvLayoutManager);
                languageAdapter = new LanguageAdapter(
                        this, languageDataList, new LanguageClickListener() {
                    @Override
                    public void onLanguageSelectUnselect(int position, @NotNull String id, @NotNull String name) {
                        if (id.equals("0")) {
                            if (savedLanguageData.contains("0"))
                                savedLanguageData.clear();
                            else {
                                savedLanguageData.clear();
                                for (int i = 0; i < languageDataList.size(); i++) {
                                    savedLanguageData.add(languageDataList.get(i).value);
                                }
                                MyLogger.e(TAG, "savedLanguageData0: " + savedLanguageData.toString());
                            }
                        } else {
                            if (savedLanguageData.contains("0"))
                                savedLanguageData.remove("0");
                            if (savedLanguageData.contains(id))
                                savedLanguageData.remove(id);
                            else
                                savedLanguageData.add(id);
                            if (savedLanguageData.size() == langDataList.size() && savedLanguageData.containsAll(langDataList)) {
                                savedLanguageData.clear();
                                for (int i = 0; i < languageDataList.size(); i++) {
                                    savedLanguageData.add(languageDataList.get(i).value);
                                }
                            }
                            MyLogger.e(TAG, "savedLanguageDataelse0: " + savedLanguageData.toString());
                        }
                        languageAdapter.notifyDataSetChanged();
                    }
                }, savedLanguageData);
                dialogPopupBinding.rvLanguages.setAdapter(languageAdapter);

                dialogPopupBinding.rlDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (savedLanguageData.contains("0"))
                            savedLanguageData.remove("0");
                        AppUtils.showToast(DialogActivity.this, "Selected languages: " + savedLanguageData.toString());
                        disableDialog();
                        setLayout(AppConstants.SHOW_STATES_POPUP);
                    }
                });

                break;

            case AppConstants.SHOW_STATES_POPUP:
                dialogPopupBinding.txtHeader.setText(getString(R.string.choose_a_state));

                stateDataList = LanguageUtils.getStatesData(DialogActivity.this);
                for (int i = 0; i < stateDataList.size(); i++) {
                    if (!stateDataList.get(i).label.equals("All"))
                        statesDataList.add(stateDataList.get(i).label);
                }
                MyLogger.e(TAG, "statesDataList:" + statesDataList.toString());
                SpannableString skipSpannable1 = new SpannableString(getString(R.string.done));
                skipSpannable1.setSpan(new UnderlineSpan(), 0, skipSpannable1.length(), 0);
                dialogPopupBinding.txtDone.setText(skipSpannable1);

                GridLayoutManager rvLayoutManager1 = new GridLayoutManager(this, 2);
                dialogPopupBinding.rvLanguages.setLayoutManager(rvLayoutManager1);
                statesAdapter = new StatesAdapter(
                        this, stateDataList, new RecycleCallBacks() {
                    @Override
                    public void onRecycleClick(int position, String value) {
                        if (value.equals("All")) {
                            if (savedStatesData.contains("All")) savedStatesData.clear();
                            else {
                                savedStatesData.clear();
                                for (int i = 0; i < stateDataList.size(); i++) {
                                    savedStatesData.add(stateDataList.get(i).label);
                                }
                                MyLogger.e(TAG, "savedStatesData0: " + savedStatesData.toString());
                            }
                        } else {
                            if (savedStatesData.contains("All")) savedStatesData.remove("All");
                            if (savedStatesData.contains(value))
                                savedStatesData.remove(value);
                            else
                                savedStatesData.add(value);
                            if (savedStatesData.size() == statesDataList.size() && savedStatesData.containsAll(statesDataList)) {
                                savedStatesData.clear();
                                for (int i = 0; i < stateDataList.size(); i++) {
                                    savedStatesData.add(stateDataList.get(i).label);
                                }
                            }
                            MyLogger.e(TAG, "savedStatesDatalse0: " + savedStatesData.toString());
                        }
                        statesAdapter.notifyDataSetChanged();
                    }
                }, savedStatesData);
                dialogPopupBinding.rvLanguages.setAdapter(statesAdapter);

                dialogPopupBinding.rlDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (savedStatesData.contains("All"))
                            savedStatesData.remove("All");
                        AppUtils.showToast(DialogActivity.this, "Selected States: " + savedStatesData.toString());
                        disableDialog();
                        finish();
                    }
                });
                break;
        }
    }

    private void disableDialog() {
        if (customDialog != null)
            customDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        disableDialog();
        finish();
    }
}