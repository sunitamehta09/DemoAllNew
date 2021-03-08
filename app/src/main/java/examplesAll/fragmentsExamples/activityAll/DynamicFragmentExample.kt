package examplesAll.fragmentsExamples.activityAll

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityDynamicFragmentBinding
import controllerAll.utilsAll.AppConstants
import controllerAll.utilsAll.BaseActivity
import examplesAll.fragmentsExamples.callBacks.StudentClickListener
import examplesAll.fragmentsExamples.fragments.ShowNameFragment
import examplesAll.fragmentsExamples.fragments.StudentsNameFragment

class DynamicFragmentExample : BaseActivity(), StudentClickListener {
    lateinit var viewBinding: ActivityDynamicFragmentBinding
    var fragment: Fragment? = null
    var fragmentManager: FragmentManager? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityDynamicFragmentBinding

        setupDrawerToggle()

        viewBinding.layoutHeader.rlImage.setOnClickListener {
            viewBinding.drawerLayout.openDrawer(GravityCompat.START)
        }

        viewBinding.txtShow.setOnClickListener {
            if (fragment !is ShowNameFragment || !fragment!!.isVisible) {
                addFragment(0)
            }
        }

        viewBinding.txtStudent.setOnClickListener {
            if (fragment !is StudentsNameFragment || !fragment!!.isVisible) {
                addFragment(1)
            }
        }

        viewBinding.leftDrawerMenu.txtShow.setOnClickListener {
            viewBinding.drawerLayout.closeDrawer(GravityCompat.START)
            if (fragment !is ShowNameFragment || !fragment!!.isVisible) {
                addFragment(0)
            }
        }

        viewBinding.leftDrawerMenu.txtStudent.setOnClickListener {
            viewBinding.drawerLayout.closeDrawer(GravityCompat.START)
            if (fragment !is StudentsNameFragment || !fragment!!.isVisible) {
                addFragment(1)
            }
        }
    }

    override fun onBackPressed() {
        if (fragment != null) {
            if (fragmentManager!!.backStackEntryCount == 0) {
                finish()
            } else {
                fragmentManager!!.popBackStack()
            }
        } else
            finish()
    }

    private fun addFragment(value: Int) {
        if (value == 0)
            fragment = ShowNameFragment()
        else
            fragment = StudentsNameFragment()
        if (fragment != null) {
            fragmentManager = supportFragmentManager
            fragmentManager!!.beginTransaction().replace(R.id.container, fragment!!)
                    .addToBackStack(null).commit()
        }
    }

    private fun setupDrawerToggle() {
        val actionBarDrawerToggle = ActionBarDrawerToggle(
                this, viewBinding.drawerLayout,
                R.string.openDrawer, R.string.closedDrawer
        )
        actionBarDrawerToggle.getDrawerArrowDrawable()
                .setColor(getResources().getColor(R.color.black));

        actionBarDrawerToggle.syncState()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_dynamic_fragment
    }

    override fun onStudentClick(name: String) {
        val bundle = Bundle()
        fragment = ShowNameFragment()
        bundle.putString(AppConstants.EXTRAS_STUDENT_NAME, name)
        if (fragment != null) {
            fragmentManager = supportFragmentManager
            fragment!!.arguments = bundle
            fragmentManager!!.beginTransaction()
                    .replace(R.id.container, fragment!!)
                    .addToBackStack(null).commit()
        }
    }
}