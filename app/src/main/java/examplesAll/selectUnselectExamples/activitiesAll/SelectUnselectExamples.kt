package examplesAll.selectUnselectExamples.activitiesAll

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

class SelectUnselectExamples : BaseActivity() {
        lateinit var viewBinding: ActivityShowOptionsBinding
        var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
        private var data: ArrayList<HashMap<String, Any>>? = null

        override fun initView() {
            viewBinding = viewBaseBinding as ActivityShowOptionsBinding

            viewBinding.layoutSearch.visibility = View.GONE

            data = SetDataAll.getOptionsAll(this, AppConstants.SELECT_UNSELECT_EXAMPLS)

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
                AppConstants.BUTTON_SELECT_UNSELECT -> {
                    startActivity(Intent(this, ButtonSelectUnselect::class.java))
                }
                AppConstants.RADIOBUTTON_IN_RADIOGROUP_SELECT_UNSELECT -> {
                    startActivity(Intent(this, RadioGroupExample::class.java))
                }

            }
        }

        override fun getLayoutId(): Int {
            return R.layout.activity_show_options
        }
    }