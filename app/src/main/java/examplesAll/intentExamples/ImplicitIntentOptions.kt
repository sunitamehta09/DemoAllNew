package examplesAll.intentExamples

import adapterAll.CentralizeOptionsAdapter
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import callBacksAll.OptionsClickListener
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityShowOptionsBinding
import controllerAll.utilsAll.AppConstants
import controllerAll.utilsAll.BaseActivity
import controllerAll.utilsAll.SetDataAll
import java.util.*

class ImplicitIntentOptions : BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding
        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.IMPLICIT_INTENT_EXAMPLS)

        centralizeOptionsAdapter = CentralizeOptionsAdapter(this@ImplicitIntentOptions, AppConstants.showSecondaryOptions,
                R.layout.adapter_secondary_options, data!!, object : OptionsClickListener {
            override fun onClickOptions(type: String, value: String) {
                if (value.equals(AppConstants.shareByEMailWhatAppMessage)) {
                    intent = Intent(Intent.ACTION_SEND)
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Share a Link")
                    intent.putExtra(Intent.EXTRA_TEXT, "For Testing")
                    intent.type = "text/plain"
                    startActivity(Intent.createChooser(intent, "Send Link"))
                } else if (value.equals(AppConstants.shareByEMail)) {
                    intent = Intent(Intent.ACTION_SEND)
                    intent.data = Uri.parse("mailto:")
                    intent.putExtra(Intent.EXTRA_EMAIL, "sunita@gmail.com")
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Share a Link")
                    intent.putExtra(Intent.EXTRA_TEXT, "For Testing")
                    intent.type = "message/rfc822"
                    intent.setPackage("com.google.android.gm")
                    startActivity(Intent.createChooser(intent, "Send mail"))
                } else if (value.equals(AppConstants.shareOnWhatApp)) {
                    intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.setPackage("com.whatsapp")
                    intent.putExtra(Intent.EXTRA_TEXT, "Share link for testing")
                    startActivity(intent)
                } else if (value.equals(AppConstants.shareByMessage)) {
                    val mblNumVar = "+919582647098;+918076052602"
                    intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("sms:$mblNumVar")
                    intent.putExtra("sms_body", "Share link for testing")
                    startActivity(intent)
                } else if (value.equals(AppConstants.calling)) {

                    intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:" + "+919582647098")
                    startActivity(intent)
                } else if (value.equals(AppConstants.openWebView)) {
                    startActivity(Intent(this@ImplicitIntentOptions, CentralizedWebView::class.java))
                }
            }
        })

        viewBinding.recyclerView.layoutManager = GridLayoutManager(this@ImplicitIntentOptions, 3)
        viewBinding!!.recyclerView.adapter = centralizeOptionsAdapter
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }

}
