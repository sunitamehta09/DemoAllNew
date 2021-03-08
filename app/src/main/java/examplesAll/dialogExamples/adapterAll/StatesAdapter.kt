package examplesAll.dialogExamples.adapterAll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.self.demoaall.R
import com.self.demoaall.databinding.LayoutLanguageItemBinding
import examplesAll.dialogExamples.callBacks.LanguageClickListener
import examplesAll.dialogExamples.callBacks.RecycleCallBacks
import examplesAll.dialogExamples.viewModelsAll.LanguageOptionsData
import examplesAll.dialogExamples.viewModelsAll.StatesOptionData
import java.util.ArrayList

class StatesAdapter (
        private val context: Context,
        private var stateList: ArrayList<StatesOptionData>,
        private val recycleListener: RecycleCallBacks,
        private var savedStateList: ArrayList<String>
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
        holder.viewBinding.txtName.text = stateList[position].label.capitalize()

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

        if (savedStateList.contains(stateList[position].label))
            holder.viewBinding.imgCheck.visibility = View.VISIBLE
        else
            holder.viewBinding.imgCheck.visibility = View.GONE


        holder.viewBinding.cvCategoryMain.setOnClickListener {
            recycleListener.onRecycleClick(
                    position, stateList[position].label!!
            )
        }
    }

    override fun getItemCount(): Int {
        return stateList.size
    }

    inner class MyViewHolder(var viewBinding: LayoutLanguageItemBinding) : RecyclerView.ViewHolder(
            viewBinding.root
    )
}