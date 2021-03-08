package examplesAll.dialogExamples.activityAll.EditorJi.dialogAll

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dialog.adapter.ReportDetailsDialog
import com.self.demoaall.R
import com.self.demoaall.databinding.LayoutReportdialogBinding
import controllerAll.utilsAll.AppConstants
import controllerAll.utilsAll.AppUtils
import examplesAll.dialogExamples.activityAll.EditorJi.adapterAll.ReportListAdapter
import examplesAll.dialogExamples.activityAll.EditorJi.callBacksAll.DialogMessageListener
import examplesAll.dialogExamples.activityAll.EditorJi.callBacksAll.ReportListClickListener
import examplesAll.dialogExamples.activityAll.EditorJi.utilsAll.ReportUtils
import examplesAll.dialogExamples.activityAll.EditorJi.viewModelsAll.InitialData
import examplesAll.dialogExamples.activityAll.EditorJi.viewModelsAll.SecondaryData

class ReportDialog : DialogFragment {
    lateinit var dialogMessageListener: DialogMessageListener

    constructor() : super() {}
    constructor(dialogMessageListener: DialogMessageListener) : super() {
        this.dialogMessageListener = dialogMessageListener
    }

    companion object {
        var dialogView: LayoutReportdialogBinding? = null
        var reportListAdapter: ReportListAdapter? = null
        var initialData: ArrayList<InitialData>? = null
        var secondaryData: ArrayList<SecondaryData>? = null
        var selectedReportText: String? = ""
        var selectedReportId: Int? = 0
        var showSecondary: Boolean? = false
        var videoId: String? = ""
        var mContext: Context? = null

        @JvmStatic
        fun create(context: Context, listener: DialogMessageListener): ReportDialog {
            this.videoId = videoId
            mContext = context
            val dialog = ReportDialog(listener)
            val args = Bundle()
            dialog.arguments = args
            dialog.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogView = DataBindingUtil.inflate(LayoutInflater.from(requireActivity()), R.layout.layout_reportdialog, null, true)

        dialogView!!.txtOk.setOnClickListener {
            if (initialData!!.size > 0) {
                if (!selectedReportText!!.isEmpty()) {
                    if (!showSecondary!!) {
                        dialog!!.dismiss()
                        dialogMessageListener.getMessage(selectedReportText)
                    } else {
                        dialog!!.dismiss()
                        ReportDetailsDialog.create(mContext!!,dialogMessageListener)
                                .show(requireActivity().supportFragmentManager, "")
                    }
                }else
                    AppUtils.showToast(requireContext(), requireContext().getString(R.string.please_select_one_option));
            }
        }

        dialogView!!.txtcancel.setOnClickListener {
            dialogMessageListener.onCancel()
            dialog!!.dismiss()
        }

        initialData =  ReportUtils.getInitialData(mContext!!)
        secondaryData =  ReportUtils.getSecondaryData(mContext!!)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        reportListAdapter = ReportListAdapter(requireContext(),
                initialData, secondaryData, AppConstants.INITIAL,
                object : ReportListClickListener {
                    override fun onReportClick(id: Int, text: String?, isSecondary: Boolean) {
                        for (i in initialData!!.indices) {
                            if (id == initialData!!.get(i).id)
                                initialData!!.get(i).select = 1
                            else
                                initialData!!.get(i).select = 0
                        }
                        reportListAdapter!!.notifyDataSetChanged()
                        selectedReportText = text
                        selectedReportId = id
                        showSecondary = isSecondary
                    }
                })
        dialogView!!.rvlist.layoutManager = linearLayoutManager
        dialogView!!.rvlist.adapter = reportListAdapter

        return dialogView!!.root
    }

    override fun getTheme() = R.style.RoundedCornersDialog
}