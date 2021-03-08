package examplesAll.permissionExamples.adapterAll

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import callBacksAll.RecyclerViewCallBack
import com.self.demoaall.R
import com.self.demoaall.databinding.LayoutAdapterFetchContactsBinding
import java.util.*

class FetchContactAdapter(private val context: Context, private val type: String, private val layout: Int,
                          private var data: ArrayList<HashMap<String, Any>>, private val recycleListener: RecyclerViewCallBack) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var getDataSub: HashMap<String, Any>? = null
    private var getData: ArrayList<HashMap<String, Any>>? = null

    init {
        getData = data
    }
    inner class AdapterFetchContactsViewHolder(var adapterFetchContactsBinding: LayoutAdapterFetchContactsBinding) :
            RecyclerView.ViewHolder(adapterFetchContactsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapterFetchContactsBinding = DataBindingUtil.inflate<LayoutAdapterFetchContactsBinding>(
                LayoutInflater.from(context),
                layout, parent, false
        )
        return AdapterFetchContactsViewHolder(adapterFetchContactsBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val optionsHolder = holder as AdapterFetchContactsViewHolder

        getDataSub = getData!!.get(position)
        optionsHolder.adapterFetchContactsBinding.tvName.setVisibility(View.VISIBLE)
        optionsHolder.adapterFetchContactsBinding.tvName.setText(getDataSub!!["name"].toString()[0].toString())

        try {
            val nametxt = getDataSub!!["name"].toString()
            val nametxtLength = nametxt.length
            if (nametxtLength > 16) {
                val afterTruncatenametxt = nametxt.substring(0, Math.min(nametxtLength, 16))
                optionsHolder.adapterFetchContactsBinding.tvContactname.setText("$afterTruncatenametxt...")
            } else {
                optionsHolder.adapterFetchContactsBinding.tvContactname.setText(getDataSub!!["name"].toString())
            }
        } catch (e: Exception) {
            Log.e("truncate", "ad case4- $e")
        }

        optionsHolder.adapterFetchContactsBinding.tvContactname.setText(getDataSub!!["number"].toString())

        if (getDataSub!!["invited"] == "false") {
            optionsHolder.adapterFetchContactsBinding.tvInvite.setVisibility(View.VISIBLE)
            optionsHolder.adapterFetchContactsBinding.imgSendicon.setVisibility(View.GONE)
        } else {
            optionsHolder.adapterFetchContactsBinding.tvInvite.setVisibility(View.GONE)
            optionsHolder.adapterFetchContactsBinding.imgSendicon.setVisibility(View.VISIBLE)
        }

        optionsHolder.adapterFetchContactsBinding.tvInvite.setOnClickListener(View.OnClickListener {
            getDataSub = getData!![position]
            if (getDataSub!!["invited"] == "false") {
                recycleListener.recycleCallBack(context.getString(R.string.invite_user), position.toString(), getDataSub!!["number"].toString())
            } else {
                recycleListener.recycleCallBack(context.getString(R.string.already_invite))
            }
        })
      }

    override fun getItemCount(): Int {
        return getData!!.size
    }

    fun updateData(data: ArrayList<HashMap<String, Any>>) {
        getData = data
        notifyDataSetChanged()
    }

}