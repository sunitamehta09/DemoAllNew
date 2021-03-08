package examplesAll.fragmentsExamples.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.self.demoaall.R
import com.self.demoaall.databinding.FragmentCountriesBinding
import controllerAll.utilsAll.AppConstants
import examplesAll.fragmentsExamples.activityAll.FragmentRetainingStateExample
import examplesAll.fragmentsExamples.callBacks.FragmentActionListener

class CountriesFragment : Fragment() {
    var countryNamesAdapter: ArrayAdapter<String>? = null
    lateinit var countries: Array<String>
    lateinit var viewBinding:FragmentCountriesBinding
    private var fragmentActionListener:FragmentActionListener ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)

        countries = getResources().getStringArray(R.array.countries);
        countryNamesAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, countries)
        viewBinding!!.listViewCountries.setAdapter(countryNamesAdapter)

        viewBinding!!.listViewCountries.setOnItemClickListener(OnItemClickListener { adapterView, view, i, l ->
            if (fragmentActionListener != null) {
                val bundle = Bundle()
                bundle.putInt(AppConstants.ACTION_KEY, AppConstants.ACTION_VALUE_COUNTRY_SELECTED)
                bundle.putString(AppConstants.KEY_SELECTED_COUNTRY, countries[i])
                fragmentActionListener!!.onActionPerformed(bundle)
            }
        })
        return viewBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(savedInstanceState !=null)
            this.fragmentActionListener = activity as FragmentRetainingStateExample
    }

    fun setFragmentActionListener(fragmentActionListener: FragmentRetainingStateExample) {
        this.fragmentActionListener = fragmentActionListener
    }

}