package examplesAll.intentExamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityStartForResultBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

//  http://tutorials.jenkov.com/java-regex/pattern.html
//  http://www.mkyong.com/regular-expressions/10-java-regular-expression-examples-you-should-know/

/*
The Java Pattern class can be used in two ways. You can use the Pattern.matches()
 method to quickly check if a text (String) matches a given regular expression.
 Or you can compile a Pattern instance using Pattern.compile() which can be used
 multiple times to match the regular expression against multiple texts.
 */

public class StartActivityForResult extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private int send_data = 1;
    private static final String TAG = "StartActivityForResult";
    private ActivityStartForResultBinding viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(StartActivityForResult.this, R.layout.activity_start_for_result);
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
                        String passwordTxt = viewBinding.editText.getText().toString().trim();
                        viewBinding.editText.setText("");
                        Intent intent = new Intent(StartActivityForResult.this, SecondActivity.class);
                        intent.putExtra("value", passwordTxt);
                        startActivityForResult(intent, send_data);
                    } else {
                        Toast.makeText(StartActivityForResult.this, "Please enter password having length more than 8", Toast.LENGTH_LONG).show();
                    }
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
            if (s.length() > 7 && s.length() < 16) {
                Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
                Matcher m = pattern.matcher(s);
                boolean b = m.find();
                viewBinding.statusTxt.setVisibility(View.VISIBLE);
                if (b == true) {
                    viewBinding.statusTxt.setText("Strong");
                    viewBinding.statusTxt.setTextColor(ContextCompat.getColor(StartActivityForResult.this, R.color.colorAccent));
                    Toast.makeText(StartActivityForResult.this, "There is special character in the password", Toast.LENGTH_LONG).show();
                } else {
                    Pattern pattern1 = Pattern.compile("[^0-9]");
                    Matcher matcher = pattern1.matcher(s);
                    boolean b1 = matcher.find();
                    if (b1 == true) {
                        viewBinding.statusTxt.setText("Normal");
                        viewBinding.statusTxt.setTextColor(ContextCompat.getColor(StartActivityForResult.this, R.color.colorOrange));
                        Toast.makeText(StartActivityForResult.this, "There is an alphabet in the password", Toast.LENGTH_LONG).show();
                    } else {
                        viewBinding.statusTxt.setText("Weak");
                        viewBinding.statusTxt.setTextColor(ContextCompat.getColor(StartActivityForResult.this, R.color.colorOrange));
                        Toast.makeText(StartActivityForResult.this, "Please use special characters or alphabets in the password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Log.e(TAG, data.toString());
            if(resultCode == Activity.RESULT_OK){
                Log.e(TAG, String.valueOf(resultCode));
                if(requestCode == send_data){
                    Log.e(TAG, String.valueOf(requestCode));
                    String msg = data.getStringExtra("message");
                    Toast.makeText(StartActivityForResult.this,msg, Toast.LENGTH_LONG).show();
                }
            }else if(resultCode == Activity.RESULT_CANCELED){
                Log.e(TAG, String.valueOf(resultCode));
                Toast.makeText(StartActivityForResult.this,"cancel msg", Toast.LENGTH_LONG).show();
            }
        }
    }
}
