package examplesAll.imagesExamples.circularImage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import examplesAll.imagesExamples.aspectRatio.AspectRatioImageViewActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityRoundedImageViewBinding;

public class CircularImageViewActivity extends AppCompatActivity {
private ActivityRoundedImageViewBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(CircularImageViewActivity.this,
                R.layout.activity_rounded_image_view);

        Glide.with(CircularImageViewActivity.this).load(R.drawable.bollywoodstar)
                .into(viewBinding.imgProfilePic);
    }
}