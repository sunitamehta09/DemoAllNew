package examplesAll.viewPagerExamples.activitiesAll

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
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.activitiesAll.HorizontalViewPagerExample
import examplesAll.validationExamples.PasswordValidation
import java.util.ArrayList
import java.util.HashMap

class ViewPagerExamples : BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding

        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.VIEWPAGER_EXAMPLS)

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
            AppConstants.HORIZONTAL_VIEW_PAGER -> {
                startActivity(Intent(this, HorizontalViewPagerExample::class.java))
            }
            AppConstants.VERTICAL_VIEW_PAGER -> {
                startActivity(Intent(this, PasswordValidation::class.java))
            }

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }
}