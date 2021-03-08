package examplesAll.fragmentsExamples.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.self.demoaall.R
import com.self.demoaall.databinding.FragmentCountryDescriptionBinding
import controllerAll.utilsAll.AppConstants

class CountryDescriptionFragment : Fragment() {
    lateinit var viewBinding: FragmentCountryDescriptionBinding
    var countryName: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_description, container, false)

        if (arguments != null) {
            countryName = arguments!!.getString(AppConstants.KEY_SELECTED_COUNTRY, "India");
            viewBinding!!.textViewCountryDescription.text = countryName
        }
        return viewBinding!!.root
    }
}