package examplesAll.imagesExamples.activityAll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import examplesAll.imagesExamples.circularImage.CircularImageViewActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityRoundCornerImageViewBinding;
import com.self.demoaall.databinding.ActivityRoundedImageViewBinding;

public class RoundCornerImageView extends AppCompatActivity {
private ActivityRoundCornerImageViewBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(RoundCornerImageView.this, R.layout.activity_round_corner_image_view);

        Glide.with(RoundCornerImageView.this).load(R.drawable.bollywoodstar)
                .into(viewBinding.imgThumb);
    }
}