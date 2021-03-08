package examplesAll.fragmentsExamples.activityAll

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityFragmentAddRemoveReplaceBinding
import controllerAll.utilsAll.BaseActivity
import examplesAll.fragmentsExamples.fragments.FragmentOne
import examplesAll.fragmentsExamples.fragments.FragmentTwo
import examplesAll.fragmentsExamples.fragments.SampleFragment
import java.lang.StringBuilder


//   This program show backstack contains only fragment transactions not contains fragment

class FragmentAddRemoveReplace : BaseActivity() {
    var fragmentManager: FragmentManager? = null
    var entry: FragmentManager.BackStackEntry? = null
    var fragmentTransaction: FragmentTransaction? = null
    var addBackStack: Boolean = false
    private val TAG = FragmentAddRemoveReplace::class.java.getSimpleName()

    lateinit var viewBinding: ActivityFragmentAddRemoveReplaceBinding

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityFragmentAddRemoveReplaceBinding

        fragmentManager = supportFragmentManager

        fragmentManager!!.addOnBackStackChangedListener {
            viewBinding.txtCount.setText("Fragment count in back stack: " + fragmentManager!!.backStackEntryCount)
        }

        viewBinding.txtCount.setText("Fragment count in back stack: " + fragmentManager!!.backStackEntryCount)

        fragmentManager!!.addOnBackStackChangedListener {
            viewBinding.txtCount.setText(
                    "Fragment count in back stack: " + fragmentManager!!.backStackEntryCount)

            var backStackEntryMessage: StringBuilder = StringBuilder("Current status of Fragment Transaction Back Stack:"
                    + fragmentManager!!.backStackEntryCount)
            for (i in fragmentManager!!.backStackEntryCount - 1 downTo 0) {
                entry = fragmentManager!!.getBackStackEntryAt(i)
                backStackEntryMessage.append(entry!!.name)
            }
            Log.i(TAG, backStackEntryMessage.toString())

        }

        Log.i(TAG, "Initial BackStackEntryCount: " + fragmentManager!!.backStackEntryCount)


        viewBinding.btnAdd.setOnClickListener {
            addToBackStack()
            //    notToAddBackStack()
        }
    }

    private fun notToAddBackStack() {
        var fragment: Fragment?
        fragment = fragmentManager!!.findFragmentById(R.id.fragmentContainer)
        if (fragment is SampleFragment) {
            fragment = FragmentOne()
        } else if (fragment is FragmentOne) {
            fragment = FragmentTwo()
        } else if (fragment is FragmentTwo) {
            fragment = SampleFragment()
        } else {
            fragment = SampleFragment()
        }
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.fragmentContainer, fragment, "demofragment")
        // fragmentTransaction!!.addToBackStack(null)
        fragmentTransaction!!.commit()
    }

    private fun addToBackStack() {
        addBackStack = true
        var fragment: Fragment?
        when (fragmentManager!!.backStackEntryCount) {
            0 -> {
                fragment = SampleFragment()
            }
            1 -> {
                fragment = FragmentOne()
            }
            2 -> {
                fragment = FragmentTwo()
            }
            else -> {
                fragment = SampleFragment()
            }
        }

        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.fragmentContainer, fragment, "demofragment")
        fragmentTransaction!!.addToBackStack("Add "+ fragment.toString())
        fragmentTransaction!!.commit()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_fragment_add_remove_replace
    }

    override fun onBackPressed() {
        fragmentTransaction = fragmentManager!!.beginTransaction()
        val fragment: Fragment? = fragmentManager!!.findFragmentById(R.id.fragmentContainer)
        if (fragment != null) {
            fragmentTransaction!!.remove(fragment)
            if (addBackStack)
                fragmentTransaction!!.addToBackStack("Remove" + fragment.toString())
            fragmentTransaction!!.commit()
        } else {
            super.onBackPressed()
        }
    }

}