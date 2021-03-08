package examplesAll.imagesExamples.aspectRatio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import controllerAll.validations.InternetValidations;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityAspectRatioImageViewBinding;

public class AspectRatioImageViewActivity extends AppCompatActivity {
    private ActivityAspectRatioImageViewBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(AspectRatioImageViewActivity.this,
                R.layout.activity_aspect_ratio_image_view);

        if (InternetValidations.Companion.isNetWorkStatusAvialable(AspectRatioImageViewActivity.this))
            Glide.with(AspectRatioImageViewActivity.this).load("https://cdnmedia.earshot.in/series/5f8b299ef485cf39165b5e80/1603046140765_thumb-asm.jpg")
                    .into(viewBinding.imgBanner);
        else
            Glide.with(AspectRatioImageViewActivity.this).load(R.drawable.bollywoodstar)
                    .into(viewBinding.imgBanner);

    }
}