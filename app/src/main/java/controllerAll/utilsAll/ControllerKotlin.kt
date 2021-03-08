package controllerAll.utilsAll

import android.app.Application
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.MessageFragment
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.MyPlaceFragment
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.SearchFragment
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.TerminalFragment

class ControllerKotlin : Application(){

    companion object{
        var instance: ControllerKotlin? = null
        var activityVisible = false
        private val terminalFragment: TerminalFragment? = null
        private val message_fragment: MessageFragment? = null
        private val myPlaceFragment: MyPlaceFragment? = null
        private val searchFragment: SearchFragment? = null

        internal fun getContext(): ControllerKotlin {
            return instance!!
        }

        fun isActivityVisible(): Boolean {
            return activityVisible // return true or false
        }

        fun activityResumed() {
            activityVisible = true // this will set true when activity resumed
        }

        fun activityPaused() {
            activityVisible = false // this will set false when activity paused
        }

        fun getFragmentInstance(type: Int): Any? {
            return when (type) {
                0 -> terminalFragment
                1 -> message_fragment
                2 -> myPlaceFragment
                3 -> searchFragment
                else -> terminalFragment
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}