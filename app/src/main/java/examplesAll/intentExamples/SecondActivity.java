package examplesAll.intentExamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.self.demoaall.R;
import com.self.demoaall.databinding.LayoutSecondBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import controllerAll.utilsAll.AppUtils;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private LayoutSecondBinding viewBinding;
    private TextView clickBtn;
    private Intent intent;
    private String value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(SecondActivity.this, R.layout.layout_second);
        initView();
    }

    private void initView() {
        value = getIntent().getStringExtra("value");
        AppUtils.Companion.showToast(SecondActivity.this, value);

        clickBtn = findViewById(R.id.clickBtn);
        clickBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickBtn:
                Log.e("SecondActivity", value);
                intent = new Intent();
                intent.putExtra("message", "hello");
//                setResult(1,intent); // Or we can write
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Log.e("SecondActivity", "onBackPressed");
        intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
