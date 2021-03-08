package examplesAll.galleryExamples.activityAll

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
import java.util.*

class GalleryExamples : BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding

        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.GALLERY_EXAMPLES)

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
            AppConstants.PHONE_GALLERY -> {
                startActivity(Intent(this, PhoneGallery::class.java))
            }
            AppConstants.CUSTOM_GALLERY_MULTIPLE_SELECTION -> {
                startActivity(Intent(this, CustomGalleryMultipleSelection::class.java))
            }
            AppConstants.CUSTOM_GALLERY_SINGLE_SELECTION -> {
                startActivity(Intent(this, CustomGallerySingleSelection::class.java))
            }
            AppConstants.GALLERY_IMAGES_SHOW_IN_GRID_VIEW -> {
                startActivity(Intent(this, PostImagesActivity::class.java))
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }
}