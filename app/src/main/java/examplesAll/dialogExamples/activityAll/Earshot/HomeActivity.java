package examplesAll.dialogExamples.activityAll.Earshot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityHomeBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import controllerAll.utilsAll.AppConstants;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding viewBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);
        initView();
    }

    private void initView() {
        viewBinding.txtOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this, DialogActivity.class);
                intent.putExtra(AppConstants.EXTRA_SCREENTYPE, AppConstants.SHOW_LANGUAGE_POPUP);
                startActivity(intent);
            }
        });


    }
}