package examplesAll.fragmentsExamples.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.self.demoaall.R
import com.self.demoaall.databinding.FragmentShowNameBinding
import controllerAll.utilsAll.AppConstants

class ShowNameFragment : Fragment() {
    var viewBinding: FragmentShowNameBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_show_name, container, false)

        if(arguments !=null) {
            if (arguments!!.containsKey(AppConstants.EXTRAS_STUDENT_NAME))
                viewBinding!!.txtName.text = arguments!!.getString(AppConstants.EXTRAS_STUDENT_NAME)
        }
        return viewBinding!!.root
    }

}