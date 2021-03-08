package examplesAll.dialogExamples.activityAll

import examplesAll.dialogExamples.callBacks.SaveChangesListener
import examplesAll.dialogExamples.fragments.AlertDialogFragment
import examplesAll.dialogExamples.fragments.OpenDialogCreate
import examplesAll.dialogExamples.fragments.OpenDialogNewInstance
import adapterAll.CentralizeOptionsAdapter
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import callBacksAll.OptionsClickListener
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityShowOptionsBinding
import controllerAll.utilsAll.AppConstants
import controllerAll.utilsAll.AppUtils
import controllerAll.utilsAll.BaseActivity
import controllerAll.utilsAll.SetDataAll
import examplesAll.dialogExamples.activityAll.Earshot.HomeActivity
import examplesAll.dialogExamples.activityAll.EditorJi.EditorJiMainActivity
import java.util.*

class DialogFragmentOptions : BaseActivity() {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding
        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.DIALOG_FRAGMENTS_EXAMPLS)
        centralizeOptionsAdapter = CentralizeOptionsAdapter(this, AppConstants.showSecondaryOptions,
                R.layout.adapter_secondary_options, data!!, object : OptionsClickListener {
            override fun onClickOptions(type: String, value: String) {
                openDialog(value)
            }
        })

        viewBinding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        viewBinding!!.recyclerView.adapter = centralizeOptionsAdapter

    }

    private fun openDialog(value: String) {
        when (value) {
            AppConstants.Open_Dialog_by_Create -> {
                OpenDialogCreate.create("Hello", object : SaveChangesListener {
                    override fun doneChanges(value: String) {
                        AppUtils.showToast(this@DialogFragmentOptions, value)
                    }
                }).show(supportFragmentManager, "")
            }

            AppConstants.Open_Dialog_With_Param_by_Create -> {
                OpenDialogCreate.create("ShowMessage", "Hello", object : SaveChangesListener {
                    override fun doneChanges(value: String) {
                        AppUtils.showToast(this@DialogFragmentOptions, value)
                    }
                }).show(supportFragmentManager, "")
            }

            AppConstants.Open_Dialog_by_New_Instance -> {
                val openDialogNewInstance: OpenDialogNewInstance =
                        OpenDialogNewInstance.newInstance("ShowMessage", "Hello",
                                object : SaveChangesListener {
                                    override fun doneChanges(value: String) {
                                        AppUtils.showToast(this@DialogFragmentOptions, value)
                                    }
                                })
                openDialogNewInstance.show(supportFragmentManager, "fragment_edit_name")


                OpenDialogCreate.create("ShowMessage", "Hello", object : SaveChangesListener {
                    override fun doneChanges(value: String) {
                        AppUtils.showToast(this@DialogFragmentOptions, value)
                    }
                }).show(supportFragmentManager, "")

            }
            AppConstants.Dialog_Activity -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }

            AppConstants.Alert_Dialog_Frgament -> {
                val alertDialogFragment: AlertDialogFragment =
                        AlertDialogFragment.newInstance("Hello",
                                object : SaveChangesListener {
                                    override fun doneChanges(value: String) {
                                        AppUtils.showToast(this@DialogFragmentOptions, value)
                                    }
                                })
                alertDialogFragment.show(supportFragmentManager, "")
            }

            AppConstants.Multiple_Open_Dialog_With_Param_by_Create -> {
                startActivity(Intent(this, EditorJiMainActivity::class.java))
            }

        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }
}