package examplesAll.galleryExamples.activityAll;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import callBacksAll.RecyclerViewCallBack;
import examplesAll.galleryExamples.adapterAll.GalleryAdapter;
import examplesAll.galleryExamples.utilsAll.GalleryUtils;
import examplesAll.permissionExamples.activityAll.FetchContacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityPostImagesBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class PostImagesActivity extends AppCompatActivity {
    private ActivityPostImagesBinding viewBinding;
    private GalleryAdapter galleryAdapter;
    private ArrayList<HashMap<String, Object>> data = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> galleryImages = new ArrayList<>();
    private HashMap<String, Object> mDataSub = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(PostImagesActivity.this, R.layout.activity_post_images);
        initView();
    }

    private void initView() {
        viewBinding.layoutToolbar.header.setBackgroundColor(ContextCompat.getColor(PostImagesActivity.this, R.color.colorGrey));
        viewBinding.layoutToolbar.titleTxt.setText(getString(R.string.post_images));

        setDummyData();

        galleryAdapter = new GalleryAdapter(PostImagesActivity.this, getString(R.string.post_images),
                R.layout.layout_adapter_post_images, data, new RecyclerViewCallBack() {
            @Override
            public void recycleCallBack(Object... args) {
                String recycleCallBack = (String)args[0];
                Log.e("postyourads", "recycleCallBack- " + recycleCallBack);
                if (recycleCallBack.equals(getString(R.string.delete_image))) {
                    for (int i = data.size(); i <= 5; i++) {
                        HashMap<String, Object> dataSub = new HashMap<>();
                        dataSub.put("image", "no image");
                        data.add(dataSub);
                    }
                }else if (recycleCallBack.equals(getString(R.string.upload_image))) {
                    ArrayList<HashMap<String, Object>> noUrlData = new ArrayList<>();
                    for (int i=0; i<6; i++) {
                        if (data.get(i).get("image").equals("no image")) {
                            HashMap<String, Object> dataSub = new HashMap<>();
                            dataSub.put("image", "no image");
                            noUrlData.add(dataSub);
                        } else {

                        }
                    }
                    Log.e("updateyourad", "noUrlImages_count" + noUrlData.size());
                    Intent intent = new Intent(PostImagesActivity.this, CustomGalleryMultipleSelection.class);
                    intent.putExtra("max photos", noUrlData.size());
                    startActivityForResult(intent, 123);
                }

                galleryAdapter.updateData(data);
                galleryAdapter.notifyDataSetChanged();
            }
        });

        viewBinding.rvImages.setLayoutManager(new GridLayoutManager(PostImagesActivity.this, 2));
        viewBinding.rvImages.setAdapter(galleryAdapter);
    }

    private void setDummyData() {
        HashMap<String, Object> dataSub = new HashMap<>();
        dataSub.put("image", "no image");
        data.add(0, dataSub);

        dataSub = new HashMap<>();
        dataSub.put("image", "no image");
        data.add(1, dataSub);

        dataSub = new HashMap<>();
        dataSub.put("image", "no image");
        data.add(2, dataSub);

        dataSub = new HashMap<>();
        dataSub.put("image", "no image");
        data.add(3, dataSub);

        dataSub = new HashMap<>();
        dataSub.put("image", "no image");
        data.add(4, dataSub);

        dataSub = new HashMap<>();
        dataSub.put("image", "no image");
        data.add(5, dataSub);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (intent != null) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    if (requestCode == 123) {
                        try {
                            Log.e("postyourads", "123- " + "call");
                            galleryImages = GalleryUtils.Companion.getImageArrayData();
                            Log.e("postyourads", "galleryImages- " + galleryImages);
                            for (int i=0;i<data.size();i++) {
                                mDataSub = new HashMap();
                                mDataSub = data.get(i);
                                if (mDataSub.get("image").equals("no image")) {
                                    Log.e("postyourads", "url- " + mDataSub.get("image").toString());
                                    mDataSub.put("image", galleryImages.get(i).get("path").toString());
                                    data.set(i, mDataSub);
                                    galleryAdapter.notifyDataSetChanged();
                                    Log.e("postyourads", "now data- " + data.toString());
                                }
                            }
                        } catch (Exception e) {
                            Log.e("postyourads", "ex- " + e.toString());
                        }
                    }
                } catch (Exception e) {
                    Log.e("postyourads", "onactivityresult " + e.toString());
                }
            }
        } else {
            if (resultCode == Activity.RESULT_OK) {

            }
        }
    }
}