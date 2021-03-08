package examplesAll.galleryExamples.activityAll;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import callBacksAll.PermissionCallBack;
import callBacksAll.RecyclerViewCallBack;
import controllerAll.utilsAll.CheckPermissions;
import examplesAll.galleryExamples.adapterAll.GalleryAdapter;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityCustomGalleryBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomGallerySingleSelection extends CheckPermissions {
    private ArrayList<HashMap<String, Object>> data = new ArrayList<>(), getData;
    private HashMap<String, Object> dataSub, getDataSub;
    private String selectedImagePath, isSelected;
    private GalleryAdapter galleryAdapter;
    private RecyclerViewCallBack recyclerViewCallBack;
    private ActivityCustomGalleryBinding viewBinding;
    private final static String TAG = "CustomGallery";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(CustomGallerySingleSelection.this, R.layout.activity_custom_gallery);

        callBacks();
        initView();
    }

    private void callBacks() {
        recyclerViewCallBack = new RecyclerViewCallBack() {
            @Override
            public void recycleCallBack(Object... args) {
                String recycleCallBackValue = (String) args[0];
                if (recycleCallBackValue.equals("selectImage")) {
                    getData = (ArrayList<HashMap<String, Object>>) args[2];
                    selectedImagePath = (String) args[3];
                    isSelected = (String) args[4];
                    Log.e(TAG, "selectedImagePath: " + selectedImagePath);
                    Log.e(TAG, "isSelected: " + isSelected);

                    for (int i = 0; i < getData.size(); i++) {
                        getDataSub = new HashMap<>();
                        getDataSub = getData.get(i);
                        if (getDataSub.get("path").toString().equals(selectedImagePath))
                                getDataSub.put("isSelected", "true");
                        else
                            getDataSub.put("isSelected", "false");
                    }
                    galleryAdapter.notifyDataSetChanged();
                }
            }
        };
    }

    private void initView() {
        CheckPermission();
        galleryAdapter = new GalleryAdapter(CustomGallerySingleSelection.this, getString(R.string.custom_gallery_single_selection), R.layout.adapterlayout_customgallery, data, recyclerViewCallBack);
        viewBinding.recyclerView.setLayoutManager(new GridLayoutManager(CustomGallerySingleSelection.this, 3));
        viewBinding.recyclerView.setAdapter(galleryAdapter);
    }

    private void CheckPermission() {
        requestAllPermissions(CustomGallerySingleSelection.this, Manifest.permission.READ_EXTERNAL_STORAGE, getString(R.string.read_external_contacts), 1, new PermissionCallBack() {
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
        requestAllPermissions(CustomGallerySingleSelection.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.write_external_contacts), 1, new PermissionCallBack() {
            @Override
            public void getPermissionResults(Object... args) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadingPhoneGalleryData(CustomGallerySingleSelection.this);
                    }
                }).start();
            }
        }); }

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
