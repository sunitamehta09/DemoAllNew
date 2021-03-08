package examplesAll.validationExamples;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import controllerAll.utilsAll.AppUtils;
import controllerAll.validations.PasswordValidations;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityPasswordValidationBinding;

public class PasswordValidation extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private ActivityPasswordValidationBinding viewBinding;
    private static final String TAG = "PasswordValidation";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(PasswordValidation.this, R.layout.activity_password_validation);
        initView();
    }

    private void initView() {
        viewBinding.clickBtn.setOnClickListener(this);
        viewBinding.editText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickBtn:
                if (!(viewBinding.editText.getText().toString().equals(""))) {
                    if (viewBinding.editText.getText().toString().length() > 8) {
                        AppUtils.Companion.showToast(PasswordValidation.this, "Your password is valid");
                    } else
                        AppUtils.Companion.showToast(PasswordValidation.this, "Please enter password having length more than 8");
                }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (viewBinding.editText.length() < 8) {
            viewBinding.statusTxt.setVisibility(View.INVISIBLE);
        } else {
            if (PasswordValidations.Companion.checkPasswordLength(viewBinding.editText, 7, 16)) {
                viewBinding.statusTxt.setVisibility(View.VISIBLE);
                if (PasswordValidations.Companion.isPasswordContainSpecialCharacters(viewBinding.editText.getText().toString().trim())) {
                    viewBinding.statusTxt.setText("Strong");
                    viewBinding.statusTxt.setTextColor(ContextCompat.getColor(PasswordValidation.this, R.color.colorRed));
                    AppUtils.Companion.showToast(PasswordValidation.this, "There is special character in the password");
                } else {
                    if (PasswordValidations.Companion.isPasswordContainAlphabet(viewBinding.editText.getText().toString().trim())) {
                        viewBinding.statusTxt.setText("Normal");
                        viewBinding.statusTxt.setTextColor(ContextCompat.getColor(PasswordValidation.this, R.color.colorOrange));
                        AppUtils.Companion.showToast(PasswordValidation.this, "There is an alphabet in the password");
                    } else {
                        viewBinding.statusTxt.setText("Weak");
                        viewBinding.statusTxt.setTextColor(ContextCompat.getColor(PasswordValidation.this, R.color.colorLightViolet));
                        AppUtils.Companion.showToast(PasswordValidation.this, "Please use special characters or alphabets in the password");
                    }
                }
            } else
                viewBinding.statusTxt.setVisibility(View.INVISIBLE);
        }
    }

}