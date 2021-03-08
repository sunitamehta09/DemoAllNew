package com.app.dialog.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
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
import org.json.JSONException

class ReportDetailsDialog : DialogFragment {
    lateinit var dialogMessageListener: DialogMessageListener

    constructor() : super() {}
    constructor(dialogMessageListener: DialogMessageListener) : super() {
        this.dialogMessageListener = dialogMessageListener
    }

    companion object {
        var dialogView: LayoutReportdialogBinding? = null
        var reportListAdapter: ReportListAdapter? = null
        var selectedReportId: Int? = 0
        var selectedReportText: String? = ""
        var videoId: String? = ""
        var mContext: Context? = null
        var initialData: ArrayList<InitialData>? = ArrayList()
        var secondaryData: ArrayList<SecondaryData>? = ArrayList()

        @JvmStatic
        fun create(context: Context, listener: DialogMessageListener): ReportDetailsDialog {
            mContext = context
            val dialog = ReportDetailsDialog(listener)
            val args = Bundle()
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogView = DataBindingUtil.inflate(
                LayoutInflater.from(requireActivity()), R.layout.layout_reportdialog, null, true
        )

        dialogView!!.txtHeader.setText(context!!.getString(R.string.why_are_you_reporting_this_post))

        dialogView!!.txtOk.setOnClickListener {
            if (selectedReportId != 0) {
                dialogMessageListener.getMessage(selectedReportText)
                dialog!!.dismiss()
            } else
                AppUtils.showToast(requireContext(), requireContext().getString(R.string.plaese_select_one_option));
        }

        dialogView!!.txtcancel.setOnClickListener {
            dialogMessageListener.onCancel()
            dialog!!.dismiss()
        }

        try {
            initialData = ReportUtils.getInitialData(mContext!!)
            secondaryData = ReportUtils.getSecondaryData(mContext!!)
            val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            reportListAdapter = ReportListAdapter(requireContext(),
                    initialData, secondaryData,
                    AppConstants.SECONDARY,
                    object : ReportListClickListener {
                        override fun onReportClick(id: Int, text: String?, isSecondary: Boolean) {
                            for (i in secondaryData!!.indices) {
                                if (id == secondaryData!!.get(i).id)
                                    secondaryData!!.get(i).select = 1
                                else
                                    secondaryData!!.get(i).select = 0
                            }
                            reportListAdapter!!.notifyDataSetChanged()
                            selectedReportId = id
                            selectedReportText = text
                        }
                    })
            dialogView!!.rvlist.layoutManager = linearLayoutManager
            dialogView!!.rvlist.adapter = reportListAdapter
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return dialogView!!.root
    }

    override fun getTheme() = R.style.RoundedCornersDialog
}