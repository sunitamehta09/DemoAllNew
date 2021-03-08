package examplesAll.fragmentsExamples.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.self.demoaall.R
import com.self.demoaall.databinding.FragmentSampleBinding

class FragmentOne : Fragment(){
    var viewBinding: FragmentSampleBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_sample, container, false)

        viewBinding!!.txtName.text = "Fragment One"
        return viewBinding!!.root
    }
}