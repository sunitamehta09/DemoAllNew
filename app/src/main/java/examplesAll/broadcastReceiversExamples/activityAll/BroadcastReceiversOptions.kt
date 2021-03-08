package examplesAll.broadcastReceiversExamples.activityAll

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

class BroadcastReceiversOptions : BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding
        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.BROADCAST_RECEIVERS_EXAMPLS)

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
            AppConstants.STATIC_BR_EXAMPLE -> {
                startActivity(Intent(this, StaticBroadcastReceiverExample::class.java))
            }

            AppConstants.DYNAMIC_BR_EXAMPLE -> {
                startActivity(Intent(this, DynamicBroadcastReceiverExample::class.java))
            }

            AppConstants.CUSTOM_BR_EXAMPLE -> {
                startActivity(Intent(this, CustomBroadcastReceiverExample::class.java))
            }

            AppConstants.INTERNET_CONNECTOR_RECEIVER_EXAMPLE -> {
                startActivity(Intent(this, InternetConnectorReceiverExample::class.java))
            }

            AppConstants.LOCAL_BROADCAST_MANAGER_EXAMPLE -> {
                startActivity(Intent(this, LocalBroadCastManagerExample::class.java))
            }

            AppConstants.ORDER_BR_EXAMPLE -> {
                startActivity(Intent(this, OrderedBroadcastReceiverExample::class.java))
            }

            AppConstants.FIREBASE_NOTIFICATION_BR_EXAMPLE -> {
            }

            AppConstants.CALL_ACTIVITY_METHOD_FROM_ANOTHER_BR_EXAMPLE -> {
                startActivity(Intent(this, FirstActivity::class.java))
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }

}