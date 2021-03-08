package examplesAll.fragmentsExamples.activityAll

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.self.demoaall.R
import com.self.demoaall.databinding.LayoutRemovePopupstackBinding
import controllerAll.utilsAll.BaseActivity
import examplesAll.fragmentsExamples.fragments.FragmentOne
import examplesAll.fragmentsExamples.fragments.FragmentTwo
import examplesAll.fragmentsExamples.fragments.SampleFragment


class FragmentRemovePopUpBackStack : BaseActivity() {
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null
    lateinit var viewBinding: LayoutRemovePopupstackBinding
    var entry: FragmentManager.BackStackEntry? = null
    private val TAG = "Frag_Remove_PopBackStack"

    override fun initView() {
        viewBinding = viewBaseBinding as LayoutRemovePopupstackBinding

        fragmentManager = supportFragmentManager

        fragmentManager!!.addOnBackStackChangedListener {
            viewBinding.txtCount.setText("Fragment count in back stack: " + fragmentManager!!.backStackEntryCount)
        }

        viewBinding.txtCount.setText("Fragment count in back stack: " + fragmentManager!!.backStackEntryCount)

        fragmentManager!!.addOnBackStackChangedListener {
            viewBinding.txtCount.setText(
                    "Fragment count in back stack: " + fragmentManager!!.backStackEntryCount)

            val stringBuilder = StringBuilder("""
    Current status of transaction back stack: ${fragmentManager!!.backStackEntryCount}
    
    """.trimIndent())
            for (i in fragmentManager!!.backStackEntryCount - 1 downTo 0) {
                val backStackEntry = fragmentManager!!.getBackStackEntryAt(i)
                stringBuilder.append("""
    ${backStackEntry.name}
    
    """.trimIndent())
            }

            Log.e(TAG, stringBuilder.toString())
        }

        Log.e(TAG, "Initial BackStackEntryCount: " + fragmentManager!!.backStackEntryCount)

        viewBinding.btnAdd.setOnClickListener {
            val fragment: Fragment?
            if (fragmentManager!!.backStackEntryCount > 0) {
                when (fragmentManager!!.backStackEntryCount) {
                    0 -> loadFragment(0)
                    1 -> loadFragment(1)
                    2 -> loadFragment(2)
                    else -> loadFragment(0)
                }
            } else {
                fragment = fragmentManager!!.findFragmentById(R.id.fragmentContainer)
                if (fragment is SampleFragment) {
                    loadFragment(0)
                } else if (fragment is FragmentOne) {
                    loadFragment(1)
                } else if (fragment is FragmentTwo) {
                    loadFragment(2)
                } else {
                    loadFragment(0)
                }
            }
        }

        viewBinding.btnPop.setOnClickListener {
//            fragmentManager!!.popBackStack("SampleFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager!!.popBackStack(0, 1);
        }

        viewBinding.btnRemove.setOnClickListener {
            fragmentTransaction = fragmentManager!!.beginTransaction()
            val fragment = fragmentManager!!.findFragmentById(R.id.fragmentContainer)
            if (fragment != null) {
                fragmentTransaction!!.remove(fragment)
                fragmentTransaction!!.addToBackStack("Remove $fragment")
                fragmentTransaction!!.commit()
            } else {
                Toast.makeText(this, "No Fragment to remove", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadFragment(i: Int) {
        var fragment: Fragment? 
        fragmentTransaction = fragmentManager!!.beginTransaction()
        when (i) {
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
        fragmentTransaction!!.replace(R.id.fragmentContainer, fragment!!, "");
        fragmentTransaction!!.addToBackStack(fragment.toString());
        fragmentTransaction!!.commit();
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_remove_popupstack
    }

}