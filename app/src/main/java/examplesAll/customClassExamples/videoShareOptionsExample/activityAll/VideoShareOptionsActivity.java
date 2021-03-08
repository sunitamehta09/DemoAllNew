package examplesAll.customClassExamples.videoShareOptionsExample.activityAll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import controllerAll.utilsAll.AppUtils;
import examplesAll.customClassExamples.videoShareOptionsExample.adapterAll.ViewPagerAdapter;
import examplesAll.customClassExamples.videoShareOptionsExample.callbacksAll.SocialOptionsClickListener;
import examplesAll.customClassExamples.videoShareOptionsExample.fragmentAll.Fragment1;
import examplesAll.customClassExamples.videoShareOptionsExample.fragmentAll.Fragment2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityVideoShareOptionsBinding;

import java.util.ArrayList;

public class VideoShareOptionsActivity extends AppCompatActivity implements SocialOptionsClickListener {
    private ActivityVideoShareOptionsBinding viewBinding;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> fragmentTitleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(VideoShareOptionsActivity.this, R.layout.activity_video_share_options);

        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentTitleList.add("Fragment1");
        fragmentTitleList.add("Fragment2");

        viewBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewBinding.textSignIn.setTextColor(Color.parseColor("#808080"));
                viewBinding.textSignUp.setTextColor(Color.parseColor("#808080"));
                if (position == 0) {
                    viewBinding.textSignIn.setTextColor(Color.parseColor("#42eed0"));
                    viewBinding.ind1.setVisibility(View.INVISIBLE);
                    viewBinding.ind2.setVisibility(View.VISIBLE);
                } else {
                    viewBinding.textSignUp.setTextColor(Color.parseColor("#42eed0"));
                    viewBinding.ind2.setVisibility(View.INVISIBLE);
                    viewBinding.ind1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, fragmentTitleList);
        viewBinding.viewPager.setAdapter(viewPagerAdapter);

        viewBinding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBinding.viewPager.setCurrentItem(0);
            }
        });

        viewBinding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBinding.viewPager.setCurrentItem(1);
            }
        });

    }

    @Override
    public void onOptionsClick(boolean type, String value) {
        if (type)
            AppUtils.showToast(VideoShareOptionsActivity.this, "Login " + value);
        else
            AppUtils.showToast(VideoShareOptionsActivity.this, "Signup " + value);
    }
}