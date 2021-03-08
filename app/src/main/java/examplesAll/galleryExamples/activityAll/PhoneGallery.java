package examplesAll.galleryExamples.activityAll;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityPhoneGalleryBinding;

import java.io.File;

import androidx.databinding.DataBindingUtil;
import controllerAll.utilsAll.CheckPermissions;

// http://smartandroidians.blogspot.com/2011/11/open-gallery-and-get-selected-image-in_16.html
public class PhoneGallery extends CheckPermissions implements View.OnClickListener{
    private Bitmap currentImage;
    private Intent intent;
    private String photoPath;
    private File source;
    private ActivityPhoneGalleryBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(PhoneGallery.this, R.layout.activity_phone_gallery);

        viewBinding.optionOne.setOnClickListener(this);
        viewBinding.optionTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.optionOne:
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1 );
                break;
            case R.id.optionTwo:
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 2);
                break;
       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == RESULT_OK) {
                if(requestCode == 1) {
                    Uri photoUri = data.getData();
                    if (photoUri != null) {
                        try {
                            currentImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                            viewBinding.selectedImage.setImageBitmap(currentImage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else if( requestCode == 2){
                    Uri selectedImageUri = data.getData();
                    String finalFile = getRealPathFromURI (selectedImageUri, PhoneGallery.this);
                    photoPath = finalFile.toString();
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.diskCacheStrategy
                            (DiskCacheStrategy.RESOURCE).skipMemoryCache(false).dontAnimate().downsample(DownsampleStrategy.AT_MOST);
                    Glide.with(PhoneGallery.this).load(photoPath).into(viewBinding.selectedImage);
                   // Compress Image
//                    compressImage (photoPath);
                }
            }
        }

       }

//    private void compressImage(String photoPath){
//        source = new File(photoPath);
//        Luban.compress(this, source)
//                .setMaxSize(500)
//                .setMaxHeight(1920)
//                .setMaxWidth(1080)
//                .clearCache()
//                .putGear(Luban.CUSTOM_GEAR)
//                .asObservable()
//                .subscribe(new Consumer<File>() {
//                    @Override
//                    public void accept(File file) throws Exception {
//                        source = new File(file.toString());
//                         Log.i("TAG", file.getAbsolutePath());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        throwable.printStackTrace();
//                    }
//                });
//    }

    private String getRealPathFromURI(Uri selectedImageUri, Context con) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = con.getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    /*
    The catch here is if we open the gallery for first time from the applicaiotn, the applicaiton will work fine.
     But if we open gallery for second time and try to get the selected image, we will get an exception OutOfMemory Exception

This is because we are not free the bitmap memory or we need to recycle the bitmap.
 The solution for this is, every time when we open gallery from our application, onPause() and onStop()
  of the Activity lifecycle methods will get called. So in either onPause() or onStop(),
  we need to add the code snippet to recycle the bitmap and to free the memory.
     */

    @Override
    protected void onPause() {
        super.onPause();
        if (currentImage != null) {
            currentImage.recycle();
            currentImage = null;
            System.gc();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (currentImage != null) {
            currentImage.recycle();
            currentImage = null;
            System.gc();
        }
    }
}
