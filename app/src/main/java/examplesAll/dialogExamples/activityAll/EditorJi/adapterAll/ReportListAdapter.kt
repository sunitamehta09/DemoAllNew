package examplesAll.dialogExamples.activityAll.EditorJi.adapterAll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.self.demoaall.R
import com.self.demoaall.databinding.LayoutReportlistBinding
import controllerAll.utilsAll.AppConstants
import examplesAll.dialogExamples.activityAll.EditorJi.callBacksAll.ReportListClickListener
import examplesAll.dialogExamples.activityAll.EditorJi.viewModelsAll.InitialData
import examplesAll.dialogExamples.activityAll.EditorJi.viewModelsAll.SecondaryData

public class ReportListAdapter(private var context: Context, private var initialData: ArrayList<InitialData>?,
                               var secondaryData: ArrayList<SecondaryData>?, private var type: String, private var listener: ReportListClickListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding: LayoutReportlistBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.layout_reportlist, parent, false)
        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val mHolder = viewHolder as MyViewHolder
        if (type.equals(AppConstants.INITIAL)) {
            mHolder.viewBinding.txtInfo.setText(initialData!!.get(position).reportText)

            if (initialData!!.get(position).select == 1)
                mHolder.viewBinding.radiobtn.setChecked(true)
            else
                mHolder.viewBinding.radiobtn.setChecked(false)

            mHolder.viewBinding.radiobtn.setOnClickListener(View.OnClickListener {
                listener.onReportClick(initialData!!.get(position).id, initialData!!.get(position).reportText,
                        initialData!!.get(position).showSecondary)
            })

            mHolder.viewBinding.rlMain.setOnClickListener(View.OnClickListener {
                listener.onReportClick(initialData!!.get(position).id, initialData!!.get(position).reportText,
                        initialData!!.get(position).showSecondary)
            })
        } else if (type.equals(AppConstants.SECONDARY)) {
            mHolder.viewBinding.txtInfo.setText(secondaryData!!.get(position).reportText)
            if (secondaryData!!.get(position).select == 1)
                mHolder.viewBinding.radiobtn.setChecked(true)
            else
                mHolder.viewBinding.radiobtn.setChecked(false)

            mHolder.viewBinding.radiobtn.setOnClickListener(View.OnClickListener {
                listener.onReportClick(secondaryData!!.get(position).id,
                        secondaryData!!.get(position).reportText, secondaryData!!.get(position).showSecondary)
            })
            mHolder.viewBinding.rlMain.setOnClickListener(View.OnClickListener {
                listener.onReportClick(secondaryData!!.get(position).id, secondaryData!!.get(position).reportText,
                        secondaryData!!.get(position).showSecondary)
            })
        }
    }

    override fun getItemCount(): Int {
        if (type.equals(AppConstants.INITIAL))
            return initialData!!.size
        else
            return secondaryData!!.size

    }

    inner class MyViewHolder(var viewBinding: LayoutReportlistBinding)
        : RecyclerView.ViewHolder(viewBinding.root)
}