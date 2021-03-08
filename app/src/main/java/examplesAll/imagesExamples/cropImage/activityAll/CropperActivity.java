package examplesAll.imagesExamples.cropImage.activityAll;

import androidx.appcompat.app.AppCompatActivity;
import examplesAll.imagesExamples.cropImage.classesAll.CropImageView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityCropperBinding;

import java.io.File;
import java.io.IOException;

public class CropperActivity extends AppCompatActivity {
    protected Bitmap croppedImage;
    private ProgressDialog pd;
    ImageView back;
    private TextView crop;
    private CropImageView cropimageview;
    private ActivityCropperBinding viewBinding;
    private File f;
    private String savedfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropper);
    }

         /*   final Intent intent = getIntent();
            if (intent.getBooleanExtra("gallery", true)) {
                Uri uri = intent.getData();
                viewBinding.cropView.setImageUri(uri);
            } else {
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(intent.getStringExtra("path"), bitmapOptions);
                ExifInterface ei = null;
                try {
                    ei = new ExifInterface(intent.getStringExtra("path"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);

                Bitmap rotatedBitmap = null;
                switch (orientation) {

                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotatedBitmap = rotateImage(bitmap, 90);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotatedBitmap = rotateImage(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotatedBitmap = rotateImage(bitmap, 270);
                        break;

                    case ExifInterface.ORIENTATION_NORMAL:
                    default:
                        rotatedBitmap = bitmap;
                }
                viewBinding.cropView.setImageBitmap(rotatedBitmap);
            }

            viewBinding.cropView.setFixedAspectRatio(true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewBinding.cropView.setAspectRatio(7, 7);
                }
            }, 500);
            crop = (TextView) findViewById(R.id.crop);
            crop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    croppedImage = viewBinding.cropView.getCroppedImage();
                    try {
                        if (croppedImage.getHeight() > 500) {
                            croppedImage = Bitmap.createScaledBitmap(croppedImage, 500, 500, true);
                        }
                        Intent i = new Intent();
                        i.putExtra(" image", croppedImage);
                        setResult(4, i);
                        finish();

                        //   new uploadPhoto().execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    private Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }*/


}