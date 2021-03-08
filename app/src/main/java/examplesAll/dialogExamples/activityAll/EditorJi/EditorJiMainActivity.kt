package examplesAll.dialogExamples.activityAll.EditorJi

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityEditorjiMainBinding
import controllerAll.AppUtilsAll.MyLogger
import examplesAll.dialogExamples.activityAll.EditorJi.callBacksAll.DialogMessageListener
import examplesAll.dialogExamples.activityAll.EditorJi.dialogAll.ReportDialog
import examplesAll.dialogExamples.activityAll.EditorJi.utilsAll.ReportUtils
import examplesAll.dialogExamples.activityAll.EditorJi.viewModelsAll.InitialData
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class EditorJiMainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityEditorjiMainBinding
    var initialDataArray = ArrayList<InitialData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_editorji_main)

        viewBinding.txtOpen.setOnClickListener {
        ReportDialog.create(this, object : DialogMessageListener{
            override
            fun getMessage(value: String?) {
                val myPopupView: View = layoutInflater.inflate(R.layout.layout_emailreport, null)
                val exitPopup = PopupWindow(myPopupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                exitPopup.showAtLocation(myPopupView, Gravity.CENTER, 0, 0)
                val imgClose = myPopupView.findViewById<ImageView>(R.id.imgClose)
                imgClose.setOnClickListener { exitPopup.dismiss() }
            }
            override
            fun onCancel() {
            }
        }).show(supportFragmentManager, "")
    }
    }
}