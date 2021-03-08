package examplesAll.selectUnselectExamples.activitiesAll;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityLayoutRadioGroupBinding;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import controllerAll.utilsAll.AppConstants;
import controllerAll.utilsAll.AppUtils;
import controllerAll.AppUtilsAll.MyLogger;
import controllerAll.StringUtilsAll.StringUtils;
import examplesAll.selectUnselectExamples.viewModelsAll.CategoryModel;

public class RadioGroupExample extends AppCompatActivity {
    private ActivityLayoutRadioGroupBinding viewBinding;
    private String localNewsPriorityValue, nationalNewsPriorityValue, internationalNewsPriorityValue = "";
    private ArrayList<CategoryModel> categoryModelsList = new ArrayList<>();
    private static String TAG = "RadioGroupExample";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(RadioGroupExample.this, R.layout.activity_layout_radio_group);

        viewBinding.nationalnews.setText(AppConstants.NATIONAL);
        viewBinding.internationalnews.setText(AppConstants.INTERNATIONAL);
        viewBinding.localnews.setText(AppConstants.LOCAL);

        for (int i = 0; i < 3; i++) {
            CategoryModel cardModels = new CategoryModel();
            if (i == 0) {
                cardModels.setCategoryName("National");
                cardModels.setPriority("0");
                setData("National", "0");
                viewBinding.nationalNewsType.setTag(categoryModelsList.size());
            } else if (i == 1) {
                cardModels.setCategoryName("International");
                cardModels.setPriority("1");
                setData("International", "1");
                viewBinding.internationalNewsType.setTag(categoryModelsList.size());
            } else if (i == 2) {
                cardModels.setCategoryName("Local");
                cardModels.setPriority("2");
                setData("Local", "2");
                viewBinding.localNewsType.setTag(categoryModelsList.size());
            }
            categoryModelsList.add(cardModels);
        }

        viewBinding.nationalNewsType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.national_little) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.nationalNewsType, "0");
                        viewBinding.imgNational.setImageResource(R.drawable.national_little);
                        nationalNewsPriorityValue = "0";
                    }
                } else if (checkedId == R.id.national_more) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.nationalNewsType, "1");
                        viewBinding.imgNational.setImageResource(R.drawable.national_more);
                        nationalNewsPriorityValue = "1";
                    }
                } else if (checkedId == R.id.national_lots) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.nationalNewsType, "2");
                        viewBinding.imgNational.setImageResource(R.drawable.national_lots);
                        nationalNewsPriorityValue = "2";
                    }
                }
            }
        });

        viewBinding.internationalNewsType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.international_little) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.internationalNewsType, "0");
                        viewBinding.imgInternational.setImageResource(R.drawable.international_little);
                        internationalNewsPriorityValue = "0";
                    }
                } else if (checkedId == R.id.international_more) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.internationalNewsType, "1");
                        viewBinding.imgInternational.setImageResource(R.drawable.international_more);
                        internationalNewsPriorityValue = "1";
                    }
                } else if (checkedId == R.id.international_lots) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.internationalNewsType, "2");
                        viewBinding.imgInternational.setImageResource(R.drawable.international_lots);
                        internationalNewsPriorityValue = "2";
                    }
                }
            }
        });

        viewBinding.localNewsType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.local_little) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.localNewsType, "0");
                        viewBinding.imgLocal.setImageResource(R.drawable.local_little);
                        localNewsPriorityValue = "0";
                    }
                } else if (checkedId == R.id.local_more) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.localNewsType, "1");
                        viewBinding.imgLocal.setImageResource(R.drawable.local_more);
                        localNewsPriorityValue = "1";
                    }
                } else if (checkedId == R.id.local_lots) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        updateValue(viewBinding.localNewsType, "2");
                        viewBinding.imgLocal.setImageResource(R.drawable.local_lots);
                        localNewsPriorityValue = "2";
                    }
                }
            }
        });

        viewBinding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.showToast(RadioGroupExample.this, "Result: " +
                        StringUtils.getCategoryResult(categoryModelsList));
                MyLogger.e(TAG, AppUtils.getGsonInstance().toJson(categoryModelsList));
            }
        });
    }

    private void setData(String name, String priority) {
        if (name.equals(AppConstants.NATIONAL)) {
            if (priority.equals("0")) {
                viewBinding.imgNational.setImageResource(R.drawable.national_little);
                nationalNewsPriorityValue = "0";
                viewBinding.nationalNewsType.check(R.id.national_little);
            } else if (priority.equals("1")) {
                viewBinding.imgNational.setImageResource(R.drawable.national_more);
                nationalNewsPriorityValue = "0";
                viewBinding.nationalNewsType.check(R.id.national_more);
            } else if (priority.equals("2")) {
                viewBinding.imgNational.setImageResource(R.drawable.national_lots);
                nationalNewsPriorityValue = "0";
                viewBinding.nationalNewsType.check(R.id.national_lots);
            }
        } else if (name.equals(AppConstants.INTERNATIONAL)) {
            if (priority.equals("0")) {
                viewBinding.imgInternational.setImageResource(R.drawable.international_little);
                internationalNewsPriorityValue = "0";
                viewBinding.internationalNewsType.check(R.id.international_little);
            } else if (priority.equals("1")) {
                viewBinding.imgInternational.setImageResource(R.drawable.international_more);
                internationalNewsPriorityValue = "0";
                viewBinding.internationalNewsType.check(R.id.international_more);
            } else if (priority.equals("2")) {
                viewBinding.imgInternational.setImageResource(R.drawable.international_lots);
                internationalNewsPriorityValue = "0";
                viewBinding.internationalNewsType.check(R.id.international_lots);
            }
        } else if (name.equals(AppConstants.LOCAL)) {
            if (priority.equals("0")) {
                viewBinding.imgLocal.setImageResource(R.drawable.local_little);
                localNewsPriorityValue = "0";
                viewBinding.localNewsType.check(R.id.local_little);
            } else if (priority.equals("1")) {
                viewBinding.imgLocal.setImageResource(R.drawable.local_more);
                localNewsPriorityValue = "0";
                viewBinding.localNewsType.check(R.id.local_more);
            } else if (priority.equals("2")) {
                viewBinding.imgLocal.setImageResource(R.drawable.local_lots);
                localNewsPriorityValue = "0";
                viewBinding.localNewsType.check(R.id.local_lots);
            }
        }
    }

    private void updateValue(RadioGroup radioGroup, String value) {
        try {
            categoryModelsList.get(Integer.parseInt(radioGroup.getTag().toString())).setPriority(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
