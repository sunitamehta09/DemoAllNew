package adapterAll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import callBacksAll.OptionsClickListener
import com.self.demoaall.databinding.AdapterSecondaryOptionsBinding
import com.self.demoaall.databinding.AdapterShowOptionsBinding
import controllerAll.utilsAll.AppConstants
import java.util.*

class CentralizeOptionsAdapter(private val context: Context, private val type: String, private val layout: Int,
                               private var data: ArrayList<HashMap<String, Any>>, private val optionListener: OptionsClickListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var getDataSub: HashMap<String, Any>? = null
    private var getData: ArrayList<HashMap<String, Any>>? = null

    init {
        getData = data
    }

    inner class AdapterShowOptionsViewHolder(var adapterShowBindingBinding: AdapterShowOptionsBinding) :
            RecyclerView.ViewHolder(adapterShowBindingBinding.root)

    inner class AdapterShowSecondaryOptionsViewHolder(var adapterSecondaryOptionsBinding: AdapterSecondaryOptionsBinding) :
            RecyclerView.ViewHolder(adapterSecondaryOptionsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(type.equals(AppConstants.showOptions)) {
            val adapterShowBindingBinding = DataBindingUtil.inflate<AdapterShowOptionsBinding>(
                    LayoutInflater.from(context),
                    layout, parent, false
            )
            return AdapterShowOptionsViewHolder(adapterShowBindingBinding)
        }
        else{
            val adapterSecondaryOptionsBinding = DataBindingUtil.inflate<AdapterSecondaryOptionsBinding>(
                    LayoutInflater.from(context),
                    layout, parent, false
            )
            return AdapterShowSecondaryOptionsViewHolder(adapterSecondaryOptionsBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(type.equals(AppConstants.showOptions)) {
            val optionsHolder = holder as AdapterShowOptionsViewHolder

            getDataSub = getData!!.get(position)
            optionsHolder.adapterShowBindingBinding.optionTxt.setText(getDataSub!!.get("option").toString())

            optionsHolder.adapterShowBindingBinding.layoutParent.setOnClickListener(View.OnClickListener {
                getDataSub = getData!!.get(position)
                optionListener.onClickOptions(AppConstants.showOptions, getDataSub!!.get("option").toString())
            })
        }else if(type.equals(AppConstants.showSecondaryOptions)) {
            val optionsHolder = holder as AdapterShowSecondaryOptionsViewHolder

            getDataSub = getData!!.get(position)
            optionsHolder.adapterSecondaryOptionsBinding.optionTxt.setText(getDataSub!!.get("option").toString())

            optionsHolder.adapterSecondaryOptionsBinding.layoutParent.setOnClickListener(View.OnClickListener {
                getDataSub = getData!!.get(position)
                optionListener.onClickOptions(AppConstants.showOptions, getDataSub!!.get("option").toString())
            })
        }
    }

    override fun getItemCount(): Int {
        return getData!!.size
    }

    fun updateData(data: ArrayList<HashMap<String, Any>>) {
        getData = data
        notifyDataSetChanged()
    }

}