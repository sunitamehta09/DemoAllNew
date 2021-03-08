package examplesAll.imagesExamples.cropImage.activityAll;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import callBacksAll.PermissionCallBack;
import controllerAll.AppUtilsAll.MyLogger;
import controllerAll.utilsAll.CheckPermissions;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.self.demoaall.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CropImageActivity extends CheckPermissions {
    ImageView imageView;
    Button buttonCamera, buttonGallery;
    File file;
    Uri uri;
    boolean camera = false;
    Intent CamIntent, GalIntent, CropIntent;
    public static final int RequestPermissionCode = 1;
    private int ACTION_IMAGE_CAPTURE = 5;
    DisplayMetrics displayMetrics;
    int width, height;
    String imageFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);

        imageView = (ImageView) findViewById(R.id.imageview);
        buttonCamera = (Button) findViewById(R.id.button2);
        buttonGallery = (Button) findViewById(R.id.button1);

//        CheckPermission();

        //  EnableRuntimePermission();

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera = true;
                CheckPermission();
                //  ClickImageFromCamera();
            }
        });

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera = false;
                getImageFromGallery();
            }
        });
    }

    private void CheckPermission() {
        requestAllPermissions(CropImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.read_external_contacts), 1, new PermissionCallBack() {
            @Override
            public void getPermissionResults(Object... args) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (camera)
                            CheckCameraPermission();
                        else
                            getImageFromGallery();
                    }
                }).start();
            }
        });
    }

    private void CheckCameraPermission() {
        requestAllPermissions(CropImageActivity.this, Manifest.permission.CAMERA, getString(R.string.accessCamera), 3, new PermissionCallBack() {
            @Override
            public void getPermissionResults(Object... args) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        clickImageFromCamera();
                    }
                }).start();
            }
        });
    }

    public void clickImageFromCamera() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;                        //Create a file to store the image
            try {
                photoFile = createImageFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(CropImageActivity.this, getApplicationContext()
                        .getPackageName() + ".provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent, 3);
            }
        }

       /* CamIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        file = new File(Environment.getExternalStorageDirectory(),
                "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        uri = Uri.fromFile(file);

        CamIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);

        CamIntent.putExtra("return-data", true);

        startActivityForResult(CamIntent, ACTION_IMAGE_CAPTURE);*/

    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }



    public void getImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogger.e("checkapp", "details: "+ "request: "+requestCode + "result"+resultCode);
        Bitmap bitmap = null;
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                Intent intent = new Intent(this, CropperActivity.class);
                intent.fillIn(data, Intent.FILL_IN_DATA);
                intent.putExtra("gallery", true);
                startActivityForResult(intent, 4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, CropperActivity.class);
            intent.putExtra("gallery", false);
            intent.putExtra("path", imageFilePath);
            startActivityForResult(intent, 4);
        }
        if (requestCode == 4 && resultCode == RESULT_OK) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .circleCrop()
                    .placeholder(R.drawable.icon_profile_pic)
                    .error(R.drawable.icon_profile_pic);
            Glide.with(this).load(data.getStringExtra("image")).apply(options)
                    .into(imageView);
            /*if (!TextUtils.isEmpty(prefs.getString("profile_pic", ""))) {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .circleCrop()
                        .placeholder(R.drawable.icon_profile_pic)
                        .error(R.drawable.icon_profile_pic);
                Glide.with(this).load(prefs.getString("profile_pic", "")).apply(options)
                        .into(image);
            }*/
        }
    }


    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");

            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }
    //Image Crop Code End Here

    public void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(CropImageActivity.this,
                Manifest.permission.CAMERA)) {
            Toast.makeText(CropImageActivity.this, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(CropImageActivity.this, new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
        switch (RC) {
            case RequestPermissionCode:
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(CropImageActivity.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(CropImageActivity.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}
