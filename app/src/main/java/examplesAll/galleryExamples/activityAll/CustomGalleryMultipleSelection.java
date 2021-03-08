package examplesAll.galleryExamples.activityAll;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityCustomGalleryBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import callBacksAll.PermissionCallBack;
import callBacksAll.RecyclerViewCallBack;
import controllerAll.utilsAll.AppUtils;
import controllerAll.utilsAll.CheckPermissions;
import examplesAll.galleryExamples.adapterAll.GalleryAdapter;
import examplesAll.galleryExamples.utilsAll.GalleryUtils;
import io.reactivex.functions.Consumer;
import me.shaohui.advancedluban.Luban;

public class CustomGalleryMultipleSelection extends CheckPermissions {
    private ArrayList<HashMap<String, Object>> data = new ArrayList<>(), selectImages = new ArrayList<>(),
            compressPhotos = new ArrayList<>(), amazonData = new ArrayList<>();
    private HashMap<String, Object> dataSub, getDataSub, dataPass, mDataGetter, amazonDataSub;
    private String selectedImagePath, isSelected;
    private GalleryAdapter galleryAdapter;
    private RecyclerViewCallBack recyclerViewCallBack;
    private ActivityCustomGalleryBinding viewBinding;
    private final static String TAG = "CustomGallery";
    private File Sourcefile, AfterCompressSourceFile;
    private String fileSource = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(CustomGalleryMultipleSelection.this, R.layout.activity_custom_gallery);

        callBacks();
        initView();
    }

    private void callBacks() {
        recyclerViewCallBack = new RecyclerViewCallBack() {
            @Override
            public void recycleCallBack(Object... args) {
                String recycleCallBackValue = (String) args[0];
                if (recycleCallBackValue.equals("selectImage")) {
                    ArrayList<HashMap<String, Object>> getData = (ArrayList<HashMap<String, Object>>) args[2];
                    selectedImagePath = (String) args[3];
                    isSelected = (String) args[4];
                    //Log.e(TAG, "selectedImagePath: " + selectedImagePath);

                    for (int i = 0; i < getData.size(); i++) {
                        getDataSub = new HashMap<>();
                        getDataSub = getData.get(i);
                        if (getDataSub.get("path").toString().equals(selectedImagePath)) {
                            if (isSelected.equals("true")) {
                                getDataSub.put("isSelected", "false");
                            } else if (isSelected.equals("false")) {
                                getDataSub.put("isSelected", "true");
                            }
                            getData.set(i, getDataSub);
                            galleryAdapter.notifyDataSetChanged();
                            break;
                        }
                    }

                    selectImages = new ArrayList<>();
                    for (int j = 0; j < data.size(); j++) {
                        dataPass = new HashMap<>();
                        dataPass = data.get(j);
                        if (dataPass.get("isSelected").equals("true")) {
                            selectImages.add(dataPass);
                        }
                    }

                    //Log.e(TAG, "selectphotos: " + selectImages.size());

                    if (selectImages.size() > 5) {
                        viewBinding.txtOptions.setVisibility(View.VISIBLE);
                        viewBinding.txtOptions.setText("DONE");
                    } else
                        viewBinding.txtOptions.setVisibility(View.GONE);
                }
            }
        };
    }

    private void initView() {
        viewBinding.layoutToolbar.header.setBackgroundColor(ContextCompat.getColor(CustomGalleryMultipleSelection.this, R.color.colorGrey));
        viewBinding.layoutToolbar.titleTxt.setText("Gallery");

        CheckPermission();
        galleryAdapter = new GalleryAdapter(CustomGalleryMultipleSelection.this, getString(R.string.custom_gallery_multiple_selection), R.layout.adapterlayout_customgallery, data, recyclerViewCallBack);
        viewBinding.recyclerView.setLayoutManager(new GridLayoutManager(CustomGalleryMultipleSelection.this, 3));
        viewBinding.recyclerView.setAdapter(galleryAdapter);

        viewBinding.txtOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectImages.size() > 6) {
                    AppUtils.Companion.showToast(CustomGalleryMultipleSelection.this, "You can't upload more than 6 photos");
                } else {
                    dummyCompressImage(selectImages);

                    //   Code to compress images one by one and upload images amazon and get url of that images.
                    //  compressImage(selectImages);
                }
            }
        });
    }

    private void dummyCompressImage(ArrayList<HashMap<String, Object>> selectImages) {
        Log.e(TAG, "dummyCompressImage selectImages size: " + selectImages.size());

        if (selectImages.size() > 0) {
            Log.e(TAG, "dummyCompressPhotoImage call");
            dummyCompressPhotoImage(selectImages.get(0).get("path").toString(), selectImages.size(), 0);
        } else {
            Log.e(TAG, "selectphotos_size<0: " + selectImages.size());
        }
    }

    private void dummyCompressPhotoImage(String filePath, final int selectphotos_size, final int position) {
        try {
            Log.e(TAG, "dummyCompressPhotoImage param- " + filePath + " - " + selectphotos_size + " - " + position);
            mDataGetter = new HashMap();
            mDataGetter.put("path", "https://cdn.editorji.com/60178238b99c8_car-bike-16.png");
            compressPhotos.add(mDataGetter);
            Log.e(TAG, "compressPhotos data- " + compressPhotos);
            Log.e(TAG, "dummyAgainCompress call");
            dummyAgainCompress(position, selectphotos_size - 1);
        } catch (Exception e) {
            Log.e(TAG, "compress image ex: " + e.toString());
        }
    }

    private void dummyAgainCompress(int position, int size) {
        if (position < size) {
            position++;
            dummyCompressPhotoImage(selectImages.get(position).get("path").toString(), selectImages.size(), position);
        } else {
            Log.e(TAG, "compressphotos size: " + compressPhotos.size());
            GalleryUtils.Companion.savedImageArrayData(compressPhotos);
            Intent intent = new Intent();
            intent.putExtra("images", "by gallery");
            setResult(Activity.RESULT_OK, intent);
            finish();
            //   uploadImageAWS(compressPhotos);
        }
    }

    private void compressImage(ArrayList<HashMap<String, Object>> selectImages) {
        Log.e(TAG, "initial size selectphotos: " + selectImages.size());

        if (selectImages.size() > 0) {
            Log.e(TAG, "selectphotos_size>0: " + selectImages.size());
            Log.e(TAG, "compressPhotoImage call");
            compressPhotoImage(selectImages.get(0).get("path").toString(), selectImages.size(), 0);
        } else {
            Log.e(TAG, "selectphotos_size<0: " + selectImages.size());
        }
    }

    private void compressPhotoImage(String filePath, final int selectphotos_size, final int position) {
        try {
            Log.e(TAG, "compressPhotoImage param- " + filePath + " - " + selectphotos_size + " - " + position);
            Sourcefile = new File(filePath);
            Luban.compress(CustomGalleryMultipleSelection.this, Sourcefile)
                    .setMaxSize(500)
                    .setMaxHeight(1920)             // limit image height
                    .setMaxWidth(1080)              // limit image width
                    .putGear(Luban.CUSTOM_GEAR)     // use CUSTOM GEAR compression mode
                    .asObservable()
                    .subscribe(new io.reactivex.functions.Consumer<File>() {
                        @Override
                        public void accept(File file) throws Exception {
                            Sourcefile = new File(String.valueOf(file));
                            fileSource = String.valueOf(Sourcefile);
                            mDataGetter = new HashMap();
                            mDataGetter.put("filePath", String.valueOf(Sourcefile));
                            compressPhotos.add(mDataGetter);
                            Log.e(TAG, "compressPhotos data- " + compressPhotos);

                            againCompress(position, selectphotos_size - 1);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "compress image ex: " + e.toString());
        }
    }

    private void againCompress(int position, int max_size) {
        Log.e(TAG, "againCompress: " + "call");
        Log.e(TAG, "againCompress param: " + position + " - " + max_size);

        amazonDataSub = new HashMap<>();
        amazonDataSub.put("image_url", "https://cdn.editorji.com/60178238b99c8_car-bike-16.png");
        amazonData.add(amazonDataSub);

        if (position < 6) {
            position++;
            Log.e(TAG, "compressPhotoImage: pos less than " + position + " - " + max_size);
            compressPhotoImage(selectImages.get(position).get("path").toString(), selectImages.size(), position);
        } else {
            Log.e(TAG, "intent call: pos size greater " + position + " - " + max_size);
            Log.e(TAG, "compressphotos: " + compressPhotos.size());
            GalleryUtils.Companion.savedImageArrayData(amazonData);
            Log.e(TAG, "amazonData:string " + amazonData.size());
            Intent intent = new Intent();
            intent.putExtra("images", "by gallery");
            setResult(Activity.RESULT_OK, intent);
            finish();
            //   uploadImageAWS(compressPhotos);
        }
    }

    private void uploadImageAWS(ArrayList<HashMap<String, Object>> compressPhotos) {
        if (compressPhotos.size() > 0) {
            Log.e(TAG, "starting uploadImageAWS: " + compressPhotos.size());
            HashMap<String, String> mDataGetter = new HashMap();
            mDataGetter.put("fileName", compressPhotos.get(0).get("fileName").toString());
            mDataGetter.put("filePath", compressPhotos.get(0).get("filePath").toString());
            //    controller.DataMaker(context, Config.IMAGE_ARRAY_AMAZON, parentApiCallback, mDataGetter, compressPhotos.size() - 1, 0);
        } else {
            Log.e(TAG, "compressImage no size: " + compressPhotos.size());
        }
    }

    private void CheckPermission() {
        requestAllPermissions(CustomGalleryMultipleSelection.this, Manifest.permission.READ_EXTERNAL_STORAGE, getString(R.string.read_external_contacts), 1, new PermissionCallBack() {
            @Override
            public void getPermissionResults(Object... args) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CheckWritePermission();
                    }
                }).start();
            }
        });
    }

    private void CheckWritePermission() {
        requestAllPermissions(CustomGalleryMultipleSelection.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.write_external_contacts), 1, new PermissionCallBack() {
            @Override
            public void getPermissionResults(Object... args) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadingPhoneGalleryData(CustomGalleryMultipleSelection.this);
                    }
                }).start();
            }
        });
    }

    private void loadingPhoneGalleryData(Context Context) {
        Log.e(TAG, "loadingPhoneGalleryData: " + "call");
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = Context.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            dataSub = new HashMap<>();
            dataSub.put("isSelected", "false");
            dataSub.put("path", absolutePathOfImage);
            data.add(dataSub);
        }
    }
}
