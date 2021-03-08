package examplesAll.fragmentsExamples.activityAll

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

class FragmentsOptions : BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding
        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.FRAGMENTS_EXAMPLS)

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
            AppConstants.Static_Fragment_Communication -> {
               startActivity(Intent(this, StaticFragmentExample::class.java))
            }

            AppConstants.Dynamic_Fragment_Communication -> {
               startActivity(Intent(this, DynamicFragmentExample::class.java))
            }

            AppConstants.Activity_Fragment_Communication -> {
                startActivity(Intent(this, ActivityFragmentCommunication::class.java))
            }

            AppConstants.FRAGMENT_ADD_REMOVE_REPLACE -> {
                startActivity(Intent(this, FragmentAddRemoveReplace::class.java))
            }

            AppConstants.FRAGMENT_ADD_REMOVE_POPBACKSTACK -> {
               startActivity(Intent(this, FragmentRemovePopUpBackStack::class.java))
            }

            AppConstants.FRAGMENT_RETAINING_STATE_EXAMPLE -> {
                startActivity(Intent(this, FragmentRetainingStateExample::class.java))
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }

}