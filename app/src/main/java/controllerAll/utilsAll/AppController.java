package controllerAll.utilsAll;

import androidx.multidex.MultiDexApplication;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.MessageFragment;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.MyPlaceFragment;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.SearchFragment;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.TerminalFragment;

public class AppController extends MultiDexApplication {
    private boolean activityVisible = false;
    private TerminalFragment terminalFragment;
    private MessageFragment message_fragment;
    private MyPlaceFragment myPlaceFragment;
    private SearchFragment searchFragment;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mInstance.initFragment();
    }

    public void initFragment() {
        terminalFragment = new TerminalFragment();
        message_fragment = new MessageFragment();
        myPlaceFragment = new MyPlaceFragment();
        searchFragment = new SearchFragment();
    }

    public boolean isActivityVisible() {
        return activityVisible;// return true or false
    }

    public void activityResumed() {
        activityVisible = true; // this will set true when activity resumed
    }

    public void activityPaused() {
        activityVisible = false; // this will set false when activity paused
    }

    public static synchronized AppController getInstance() {
        if (mInstance == null)
            mInstance = new AppController();
        return mInstance;
    }

    public Object getFragmentInstance(int type) {
        switch (type) {
            case 0:
                return terminalFragment;
            case 1:
                return message_fragment;
            case 2:
                return myPlaceFragment;
            case 3:
                return searchFragment;
            default:
                return terminalFragment;
        }
    }


}
