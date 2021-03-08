package examplesAll.adapterExamples.activityAll

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
import java.util.ArrayList
import java.util.HashMap

class AdapterExamples : BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding

        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.ADAPTER_EXAMPLS)

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
            AppConstants.PAGINATION_ADAPTER -> {
                startActivity(Intent(this, PaginationExample::class.java))
            }
            AppConstants.MULTIPLE_UI_RECYCLEVIEW -> {
                startActivity(Intent(this, ManageMultipleUI::class.java))
            }
            AppConstants.CHIPS_LAYOUT_MANAGER -> {
                startActivity(Intent(this, CitySelectionActivity::class.java))
            }
            AppConstants.SELECT_UNSELECT_OPTIONS -> {
                startActivity(Intent(this, SelectUnselectOptions::class.java))
            }

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }
}