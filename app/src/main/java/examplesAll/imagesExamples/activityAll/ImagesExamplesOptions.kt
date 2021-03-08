package examplesAll.imagesExamples.activityAll

import adapterAll.CentralizeOptionsAdapter
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import callBacksAll.OptionsClickListener
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityShowOptionsBinding
import controllerAll.utilsAll.AppConstants
import controllerAll.utilsAll.BaseActivity
import controllerAll.utilsAll.SetDataAll
import examplesAll.imagesExamples.aspectRatio.AspectRatioImageViewActivity
import examplesAll.imagesExamples.circularImage.CircularImageViewActivity
import examplesAll.imagesExamples.cropImage.activityAll.CropImageActivity
import examplesAll.imagesExamples.cropImageCustomization.CropImageMainActivity
import examplesAll.imagesExamples.zoomImage.ZoomImageActivity
import java.util.*

class ImagesExamplesOptions :  BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding

        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.IMAGES_EXAMPLES)

        centralizeOptionsAdapter = CentralizeOptionsAdapter(this, AppConstants.showSecondaryOptions,
                R.layout.adapter_secondary_options, data!!, object : OptionsClickListener {
            override fun onClickOptions(type: String, value: String) {
                ShowActivity(value)
            }
        })

        viewBinding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        viewBinding!!.recyclerView.adapter = centralizeOptionsAdapter
    }

    private fun ShowActivity(value: String) {
        when (value) {
            AppConstants.ASPECT_RATIO_EXAMPLES -> {
                startActivity(Intent(this, AspectRatioImageViewActivity::class.java))
            }
            AppConstants.ROUNDED_IMAGE_VIEW -> {
                startActivity(Intent(this, CircularImageViewActivity::class.java))
            }
            AppConstants.ROUND_CORNERS_IMAGE_VIEW -> {
                startActivity(Intent(this, RoundCornerImageView::class.java))
            }
            AppConstants.ZOOM_IMAGE_VIEW -> {
                startActivity(Intent(this, ZoomImageActivity::class.java))
            }
            AppConstants.CROP_IMAGE_VIEW -> {
                startActivity(Intent(this, CropImageActivity::class.java))
            }
            AppConstants.CROP_IMAGE_VIEW_CUSTOMIZE -> {
                startActivity(Intent(this, CropImageMainActivity::class.java))
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }
}