package examplesAll.servicesExamples.activityAll

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

class ServiceOptions : BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding
        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.SERVICES_EXAMPLES)

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
            AppConstants.SERVICES_LIFECYCLE_EXAMPLE -> {
                startActivity(Intent(this, ServiceLifeCycleExample::class.java))
            }

            AppConstants.PLAYSONG_EXAMPLE -> {
                startActivity(Intent(this, PlaySong::class.java))
            }

            AppConstants.BINDING_SERVICE_EXAMPLE -> {
                startActivity(Intent(this, GenerateRandomNumberBindingExample::class.java))
            }

            AppConstants.CURRENT_DATE_SERVICE_EXAMPLE -> {
                startActivity(Intent(this, ShowCurrentDateBindingExample::class.java))
            }

            AppConstants.INTENT_SERVICE_EXAMPLE -> {
                startActivity(Intent(this, IntentServiceExample::class.java))
            }

            AppConstants.FOREGROUND_SERVICE_EXAMPLE -> {
                startActivity(Intent(this, ForegroundServiceExample::class.java))
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }


}