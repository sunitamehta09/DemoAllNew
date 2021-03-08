package examplesAll.adapterExamples.adapterAll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.self.demoaall.R
import com.self.demoaall.databinding.LayoutLanguageItemBinding
import examplesAll.adapterExamples.callBacksAll.LanguageClickListener
import examplesAll.adapterExamples.viewModelsAll.LanguageOptionsData
import java.util.*

class LanguageSelectUnselectAdapter(
        private val context: Context,
        private var languageList: ArrayList<LanguageOptionsData>,
        private val languageDialogClickListener: LanguageClickListener,
        private var savedLanguageList: ArrayList<String>
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding: LayoutLanguageItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_language_item, parent, false
        )
        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as MyViewHolder
        holder.viewBinding.txtName.text = languageList[position].label.capitalize()

        if (position == 0)
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_one);
        else if (position == 1)
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_two);
        else if (position == 2)
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_three);
        else if (position == 3)
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_four);
        else if (position == 4)
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_five);
        else if (position == 5)
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_six);
        else if (position == 6)
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_seven);
        else if (position == 7)
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_eight);
        else
            holder.viewBinding.rlMain.setBackgroundResource(R.drawable.image_one);

        if (savedLanguageList.contains(languageList[position].value))
            holder.viewBinding.imgCheck.visibility = View.VISIBLE
        else
            holder.viewBinding.imgCheck.visibility = View.GONE


        holder.viewBinding.cvCategoryMain.setOnClickListener {
            languageDialogClickListener.onLanguageSelectUnselect(
                    position, languageList[position].value!!, languageList[position].label!!
            )
        }
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    inner class MyViewHolder(var viewBinding: LayoutLanguageItemBinding) : RecyclerView.ViewHolder(
            viewBinding.root
    )
}