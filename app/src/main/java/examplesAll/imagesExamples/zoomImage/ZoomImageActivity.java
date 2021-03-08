package examplesAll.imagesExamples.zoomImage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import examplesAll.imagesExamples.circularImage.CircularImageViewActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityZoomImageBinding;

public class ZoomImageActivity extends AppCompatActivity {
    private ActivityZoomImageBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(ZoomImageActivity.this, R.layout.activity_zoom_image);

        Glide.with(ZoomImageActivity.this).load(R.drawable.bollywoodstar)
                .into(viewBinding.imgBanner);

    }
}