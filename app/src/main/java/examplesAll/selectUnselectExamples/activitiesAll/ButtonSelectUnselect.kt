package examplesAll.selectUnselectExamples.activitiesAll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityButtonSelectUnselectBinding
import controllerAll.utilsAll.AppUtils

class ButtonSelectUnselect : AppCompatActivity() {
    lateinit var viewBinding: ActivityButtonSelectUnselectBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this@ButtonSelectUnselect, R.layout.activity_button_select_unselect)

        viewBinding.imgFavorite.setOnClickListener {
            if (viewBinding.imgFavorite.isSelected) {
                viewBinding.imgFavorite.setSelected(false)
                AppUtils.showToast(
                        this@ButtonSelectUnselect,
                        getString(R.string.you_select_unliked)
                )
            } else {
                viewBinding.imgFavorite.setSelected(true)
                AppUtils.showToast(
                        this@ButtonSelectUnselect,
                        getString(R.string.you_select_liked)
                )
            }
        }

        viewBinding.radiobutton5mint.setOnClickListener {
            setDurationValue("5")
        }

        viewBinding.radiobutton10mint.setOnClickListener {
            setDurationValue("10")
        }

        viewBinding.radiobutton15mint.setOnClickListener {
            setDurationValue("15")
        }

    }

    private fun setDurationValue(time: String) {
        AppUtils.showToast(this, time)
        when (time) {
            "5" -> {
                viewBinding.radiobutton5mint.isChecked = true
                viewBinding.radiobutton10mint.isChecked = false
                viewBinding.radiobutton15mint.isChecked = false
                viewBinding.view5min.setBackgroundResource(R.drawable.blue_gradient)
                viewBinding.view10min.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey))
                viewBinding.view15min.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey))
            }
            "10" -> {
                viewBinding.radiobutton5mint.isChecked = false
                viewBinding.radiobutton10mint.isChecked = true
                viewBinding.radiobutton15mint.isChecked = false
                viewBinding.view5min.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey))
                viewBinding.view10min.setBackgroundResource(R.drawable.blue_gradient)
                viewBinding.view15min.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey))
            }
            "15" -> {
                viewBinding.radiobutton5mint.isChecked = false
                viewBinding.radiobutton10mint.isChecked = false
                viewBinding.radiobutton15mint.isChecked = true
                viewBinding.view5min.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey))
                viewBinding.view10min.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey))
                viewBinding.view15min.setBackgroundResource(R.drawable.blue_gradient)
            }
        }
    }

}