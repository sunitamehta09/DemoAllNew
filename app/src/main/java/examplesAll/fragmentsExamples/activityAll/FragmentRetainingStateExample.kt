package examplesAll.fragmentsExamples.activityAll

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityFragmentRetainingStateExampleBinding
import controllerAll.utilsAll.AppConstants
import examplesAll.fragmentsExamples.callBacks.FragmentActionListener
import examplesAll.fragmentsExamples.fragments.CountriesFragment
import examplesAll.fragmentsExamples.fragments.CountryDescriptionFragment

class FragmentRetainingStateExample : AppCompatActivity(), FragmentActionListener{
    lateinit var viewBinding: ActivityFragmentRetainingStateExampleBinding
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null
    val currentApiVersion = Build.VERSION.SDK_INT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_fragment_retaining_state_example)

        if (currentApiVersion >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null)
            addCountriesFragment();
    }

    private fun addCountriesFragment() {
        fragmentTransaction = fragmentManager!!.beginTransaction()
        var countryListFragment = CountriesFragment()
        countryListFragment.setFragmentActionListener(this)
    //    fragmentTransaction!!.add(R.id.fragmentContainer, countryListFragment).commit()
        fragmentManager!!.beginTransaction().replace(R.id.fragmentContainer, countryListFragment!!)
                .addToBackStack(null).commit()
    }

    private fun addCountryDescriptionFragment(bundle: Bundle) {
        fragmentTransaction = fragmentManager!!.beginTransaction()
        val countryDescriptionFragment = CountryDescriptionFragment()
        countryDescriptionFragment.arguments = bundle
        fragmentTransaction!!.replace(R.id.fragmentContainer, countryDescriptionFragment)
        fragmentTransaction!!.addToBackStack(null)
        fragmentTransaction!!.commit()
    }

    override fun onActionPerformed(bundle: Bundle?) {
        val actionPerformed = bundle!!.getInt(AppConstants.ACTION_KEY)
        when (actionPerformed) {
            AppConstants.ACTION_VALUE_COUNTRY_SELECTED -> addCountryDescriptionFragment(bundle!!)
        }
    }
}
