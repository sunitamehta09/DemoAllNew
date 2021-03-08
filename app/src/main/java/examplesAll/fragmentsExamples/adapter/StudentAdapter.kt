package examplesAll.fragmentsExamples.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.self.demoaall.databinding.AdapterShowOptionsBinding
import examplesAll.fragmentsExamples.callBacks.StudentClickListener
import java.util.ArrayList
import java.util.HashMap

class StudentAdapter(val context: Context, val activity: Activity, val layout: Int, val data: ArrayList<HashMap<String, Any>>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var getDataSub: HashMap<String, Any>? = null
    private var studentClickListener: StudentClickListener = activity as StudentClickListener

    inner class AdapterShowOptionsViewHolder(var adapterShowBindingBinding: AdapterShowOptionsBinding) :
            RecyclerView.ViewHolder(adapterShowBindingBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapterShowBindingBinding = DataBindingUtil.inflate<AdapterShowOptionsBinding>(
                LayoutInflater.from(context),
                layout, parent, false
        )
        return AdapterShowOptionsViewHolder(adapterShowBindingBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val optionsHolder = holder as AdapterShowOptionsViewHolder

        getDataSub = data.get(position)
        optionsHolder.adapterShowBindingBinding.optionTxt.setText(getDataSub!!.get("name").toString())

        optionsHolder.adapterShowBindingBinding.layoutParent.setOnClickListener(View.OnClickListener {
            getDataSub = data.get(position)
            studentClickListener.onStudentClick(getDataSub!!.get("name").toString())
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }
}