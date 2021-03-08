package examplesAll.validationExamples;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityLoginFormValidationsBinding;

import controllerAll.utilsAll.AppUtils;
import controllerAll.AppUtilsAll.TelephoneUtils;
import controllerAll.utilsAll.BaseActivityJava;
import controllerAll.keyboardUtilsAll.KeyboardFunctions;
import controllerAll.validations.EmailValidations;
import controllerAll.validations.PasswordValidations;
import controllerAll.validations.TextValidations;

public class LoginFormValidations extends BaseActivityJava implements TextWatcher {
    private ActivityLoginFormValidationsBinding viewBinding;
    private boolean isValidAllFields = false;
    private static final String TAG = "LoginFormValidations";

    @Override
    protected int getLayoutById() {
        return R.layout.activity_login_form_validations;
    }

    @Override
    protected void initView() {
        viewBinding = (ActivityLoginFormValidationsBinding) viewDataBinding;

        viewBinding.fullName.addTextChangedListener(this);
        viewBinding.signupusername.addTextChangedListener(this);
        viewBinding.email.addTextChangedListener(this);
        viewBinding.password.addTextChangedListener(this);
        viewBinding.confirmPassword.addTextChangedListener(this);

        viewBinding.countryCode.setText("+"+TelephoneUtils.getCountryZipCode(LoginFormValidations.this));

        // Apply length filter on Edittext
       /* InputFilter[] fa= new InputFilter[1];
        fa[0] = new InputFilter.LengthFilter(8);
        viewBinding.description.setFilters(fa);*/

        viewBinding.description.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });

        viewBinding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyboardFunctions.hideKeyboard(LoginFormValidations.this, viewBinding.fullName);
                if (TextValidations.Companion.emptyCheckEditText(viewBinding.fullName)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.empty_full_name));
                }else if (TextValidations.Companion.IsLengthRangeValid(viewBinding.fullName.getText().toString(), 5, 20)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.length_full_name));
                }else if (TextValidations.Companion.isTextContainSpecialCharacters(viewBinding.fullName.getText().toString())) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.full_name_contain_special_characters));
                }else if (TextValidations.Companion.emptyCheckEditText(viewBinding.signupusername)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.empty_user_name_only));
                }else if (TextValidations.Companion.IsLengthRangeValid(viewBinding.signupusername.getText().toString(), 3, 30)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.length_user_name));
                }else if (TextValidations.Companion.isTextContainSpecialCharacters(viewBinding.signupusername.getText().toString())) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.user_name_contain_special_characters));
                } else if (TextValidations.Companion.emptyCheckEditText(viewBinding.email)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.enter_email_id));
                } else if (EmailValidations.Companion.isEmailValids(viewBinding.email.getText().toString())) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.valid_email));
                } else if (TextValidations.Companion.emptyCheckEditText(viewBinding.password)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.enter_password));
                }else if (PasswordValidations.Companion.checkPasswordLength(viewBinding.password, 8, 20)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.length_password));
                }else if (PasswordValidations.Companion.isPasswordContainSpecialCharacters(viewBinding.password.getText().toString().trim())) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.password_contain_special_characters));
                } else if (TextValidations.Companion.emptyCheckEditText(viewBinding.confirmPassword)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.enter_confirm_password));
                } else if (TextValidations.Companion.emptyCheckEditText(viewBinding.confirmPassword)) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.enter_confirm_password));
                } else if (PasswordValidations.Companion.isPasswordMatch(
                        viewBinding.password.getText().toString(), viewBinding.confirmPassword.getText().toString())) {
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.password_confirm_password));
                }else
                    AppUtils.Companion.snackBarShow(LoginFormValidations.this, viewBinding.coordinatorLayout, getString(R.string.all_fields_contain_valid_data));
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) { //gunnu
        if (viewBinding.fullName.getText().hashCode() == s.hashCode()) {
            if (!TextValidations.Companion.emptyCheckEditText(viewBinding.fullName)) {
                if (TextValidations.Companion.IsLengthRangeValid(viewBinding.fullName.getText().toString(), 5, 40)) {
                    if (!TextValidations.Companion.isTextContainSpecialCharacters(viewBinding.fullName.getText().toString().trim())) {
                        visibilityShow(viewBinding.checkFullName);
                    }else
                        visibilityHide(viewBinding.checkFullName);
                }else
                    visibilityHide(viewBinding.checkFullName);
            }else
                visibilityHide(viewBinding.checkFullName);
        } else if (viewBinding.signupusername.getText().hashCode() == s.hashCode()) {
            if (!TextValidations.Companion.emptyCheckEditText(viewBinding.signupusername)) {
            if (TextValidations.Companion.IsLengthRangeValid(viewBinding.signupusername.getText().toString(), 3, 30)) {
                    if (!TextValidations.Companion.isTextContainSpecialCharacters(viewBinding.signupusername.getText().toString().trim())) {
                        visibilityShow(viewBinding.checkUser);
                    }else
                        visibilityHide(viewBinding.checkUser);
                } else
                    visibilityHide(viewBinding.checkUser);
            }else
                visibilityHide(viewBinding.checkUser);
        } else if (viewBinding.email.getText().hashCode() == s.hashCode()) {
            if (!TextValidations.Companion.emptyCheckEditText(viewBinding.email)) {
                if (EmailValidations.Companion.isEmailValids(viewBinding.email.getText().toString())) {
                    visibilityShow(viewBinding.checkEmail);
                }else
                    visibilityHide(viewBinding.checkEmail);
            }else
                visibilityHide(viewBinding.checkEmail);
        } else if (viewBinding.password.getText().hashCode() == s.hashCode()) {
            if (!TextValidations.Companion.emptyCheckEditText(viewBinding.password)) {
                if (PasswordValidations.Companion.checkPasswordLength(viewBinding.password, 8, 15)) {
                    if (PasswordValidations.Companion.isPasswordContainSpecialCharacters(viewBinding.password.getText().toString().trim())) {
                        visibilityShow(viewBinding.checkPassword);
                    }else
                        visibilityHide(viewBinding.checkPassword);
                }else
                    visibilityHide(viewBinding.checkPassword);
            }else
                visibilityHide(viewBinding.checkPassword);
        } else if (viewBinding.confirmPassword.getText().hashCode() == s.hashCode()) {
            if (!TextValidations.Companion.emptyCheckEditText(viewBinding.confirmPassword)) {
                Log.e(TAG, ": "+ PasswordValidations.Companion.isPasswordMatch(
                        viewBinding.password.getText().toString(), viewBinding.confirmPassword.getText().toString()));
                if (PasswordValidations.Companion.isPasswordMatch(
                        viewBinding.password.getText().toString(), viewBinding.confirmPassword.getText().toString())) {
                    visibilityShow(viewBinding.checkConfirmPassword);
                }else
                    visibilityHide(viewBinding.checkConfirmPassword);
            }else
                visibilityHide(viewBinding.checkConfirmPassword);
        }
    }

    private void visibilityShow(ImageView view) {
        view.setVisibility(View.VISIBLE);
        isValidAllFields = true;
    }

    private void visibilityHide(ImageView view) {
        view.setVisibility(View.INVISIBLE);
        isValidAllFields = false;
    }
}