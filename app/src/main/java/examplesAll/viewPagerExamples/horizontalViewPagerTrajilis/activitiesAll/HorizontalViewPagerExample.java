package examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.activitiesAll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import controllerAll.utilsAll.AppConstants;
import controllerAll.utilsAll.AppController;
import controllerAll.AppUtilsAll.MyLogger;
import controllerAll.utilsAll.HorizontalViewPager;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.adapterAll.ViewPagerAdapter;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.callBacks.RecycleClickListener;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.MessageFragment;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.MyPlaceFragment;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.SearchFragment;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.ShowNameFragment;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll.TerminalFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityHorizontalViewPagerExampleBinding;

import java.util.ArrayList;

public class HorizontalViewPagerExample extends AppCompatActivity implements View.OnClickListener, RecycleClickListener {
    private ActivityHorizontalViewPagerExampleBinding viewBinding;
    private TerminalFragment terminalFragment;
    private MessageFragment message_fragment;
    private MyPlaceFragment myPlaceFragment;
    private SearchFragment searchFragment;
    private AppController controller;
    private ViewPagerAdapter viewPagerAdapter;
    private boolean fragmentPopulate = false;
    public static ArrayList<Integer> viewPagerPositionData = new ArrayList<>();
    public static ArrayList<String> backStackFragmentData = new ArrayList<>();
    private static final String TAG = "HorizonViewPagerEx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(HorizontalViewPagerExample.this, R.layout.activity_horizontal_view_pager_example);
        controller = (AppController) getApplicationContext();

        viewBinding.bottomtabs.terminalParent.setOnClickListener(this);
        viewBinding.bottomtabs.messageParent.setOnClickListener(this);
        viewBinding.bottomtabs.myPlaceParent.setOnClickListener(this);
        viewBinding.bottomtabs.searchParent.setOnClickListener(this);

        setupViewPager(viewBinding.viewPager);
    }

    private void setupViewPager(final HorizontalViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putString("type", "screen");

        terminalFragment = (TerminalFragment) controller.getFragmentInstance(0);
        message_fragment = (MessageFragment) controller.getFragmentInstance(1);
        myPlaceFragment = (MyPlaceFragment) controller.getFragmentInstance(2);
        searchFragment = (SearchFragment) controller.getFragmentInstance(3);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(terminalFragment, bundle);
        viewPagerAdapter.addFrag(message_fragment, bundle);
        viewPagerAdapter.addFrag(myPlaceFragment, bundle);
        viewPagerAdapter.addFrag(searchFragment, bundle);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(viewPagerAdapter);

        viewBinding.bottomtabs.terminalParent.setOnClickListener(this);
        viewBinding.bottomtabs.messageParent.setOnClickListener(this);
        viewBinding.bottomtabs.myPlaceParent.setOnClickListener(this);
        viewBinding.bottomtabs.searchParent.setOnClickListener(this);

        if (viewPagerPositionData.size() > 0) {
            Log.e(TAG, "setup viewPagerPosData1- " + viewPagerPositionData.size());
            if (backStackFragmentData.size() > 0) {
                Log.e(TAG, "backStack size1- " + backStackFragmentData.size());
                fragmentPopulate = true;
                setVisibility(0);
                backToFragmentViews(backStackFragmentData.get(backStackFragmentData.size() - 1));
            } else {
                Log.e(TAG, "setup viewPagerPosData1 else- " + viewPagerPositionData.size());
                fragmentPopulate = false;
                if (viewPagerPositionData.size() != 1) {
                    setVisibility(1);
                    viewPager.setCurrentItem(viewPagerPositionData.get(viewPagerPositionData.size() - 1), true);
                    SelectedTab(viewPagerPositionData.get(viewPagerPositionData.size() - 1));
                } else {
                    viewPager.setCurrentItem(0, true);
                    viewPagerPositionData.add(0);
                    SelectedTab(0);
                }
            }
        } else {
            Log.e(TAG, "setup viewPagerPosData2 else- " + viewPagerPositionData.size());
            if (backStackFragmentData.size() > 0) {
                fragmentPopulate = true;
                setVisibility(0);
                backToFragmentViews(backStackFragmentData.get(backStackFragmentData.size() - 1));
            } else {
                Log.e(TAG, "setup backStackData2 else- " + backStackFragmentData.size());
                fragmentPopulate = false;
                viewPager.setCurrentItem(0, true);
                viewPagerPositionData.add(0);
                SelectedTab(0);
                setVisibility(1);
            }
        }/*
        }else{
            viewPager.setCurrentItem(0, true);
            checkAddPosition();
            SelectedTab(0);
        }*/

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        checkAddPosition();
                        SelectedTab(0);
                        break;

                    case 1:
                        checkAddPosition();
                        SelectedTab(1);
                        break;

                    case 2:
                        checkAddPosition();
                        SelectedTab(2);
                        break;

                    case 3:
                        checkAddPosition();
                        SelectedTab(3);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void addFragmentView(Object... args) {
        String type = (String) args[0];
        Fragment fragment = (Fragment) args[1];
        backStackFragmentData.add(fragment.getClass().getName());
        Log.e(TAG, "addFragmentView " + backStackFragmentData.toString());
        Bundle bundle = new Bundle();
        if (type.equals(getString(R.string.student_fragment))) {
            bundle.putString("type", type);
        } else if (type.equals(getString(R.string.show_fragment))) {
            bundle.putString(AppConstants.EXTRAS_STUDENT_NAME, (String) args[2]);
        }
        fragment.setArguments(bundle);

        setVisibility(0);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Log.e("hit", "transaction: " + System.currentTimeMillis());
        if (type.equals(getString(R.string.student_fragment))) {
            //  transaction.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_exit);
        } else if (type.equals(getString(R.string.show_fragment))) {
            //   transaction.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_exit);
        }
        transaction.replace(R.id.replaceFragment, fragment);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();
    }

    private void setVisibility(int i) {
        Log.e(TAG, "setVisibility value- " + i);
        if (i == 0) {
            if (viewBinding.replaceFragment.getVisibility() == View.GONE) {
                viewBinding.replaceFragment.setVisibility(View.VISIBLE);
            }

            if (viewBinding.viewPager.getVisibility() == View.VISIBLE) {
                viewBinding.viewPager.setVisibility(View.GONE);
            }
        } else if (i == 1) {
            if (viewBinding.replaceFragment.getVisibility() == View.VISIBLE) {
                viewBinding.replaceFragment.setVisibility(View.GONE);
            }

            if (viewBinding.viewPager.getVisibility() == View.GONE) {
                viewBinding.viewPager.setVisibility(View.VISIBLE);
            }
            try {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(TAG, "setVisibility1 " + backStackFragmentData.toString());
        }
    }

    public void backToFragmentViews(String fragmentName) {
        getSupportFragmentManager().popBackStackImmediate(fragmentName, 0);
        Log.e("TAG", "backToFragmentViews- " + fragmentName);
    }

    @Override
    public void onBackPressed() {
        Log.e("TAG", "onbackpressed- backStackFragmentData info" + backStackFragmentData.size() + "- " + backStackFragmentData.toString());

        try {
            if (backStackFragmentData.size() > 1) {
                Log.e("TAG", "backStackFragmentData>1");
                setVisibility(0);
                backStackFragmentData.remove(backStackFragmentData.size() - 1);
                backToFragmentViews(backStackFragmentData.get(backStackFragmentData.size() - 1));
            } else if (backStackFragmentData.size() == 1) {
                Log.e("TAG", "backStackFragmentData == 1");
                if (viewPagerPositionData.size() != 1) {
                    Log.e("TAG", "viewPagerPositionData != 1");
                    Log.e("TAG", "dashboard_onbkpressed2- " + viewPagerPositionData.size() + "- " + viewPagerPositionData.toString());
                    setVisibility(1);
                    if (viewPagerPositionData.size() == 0) {
                        Log.e("TAG", "viewPagerPositionData == 0");
                        SelectedTab(0);
                        viewBinding.viewPager.setCurrentItem(0, true);
                    } else {
                        Log.e("TAG", "viewPagerPositionData != 0");
                        SelectedTab(viewPagerPositionData.get(viewPagerPositionData.size() - 1));
                        viewBinding.viewPager.setCurrentItem(viewPagerPositionData.get(viewPagerPositionData.size() - 1), true);
                        if (viewBinding.viewPager.getVisibility() == View.VISIBLE) {
                            viewPagerPositionData.remove(viewPagerPositionData.size() - 1);
                        }
                    }
                } else {
                    Log.e("TAG", "viewPagerPositionData >1");
                    setVisibility(1);
                    SelectedTab(viewPagerPositionData.get(viewPagerPositionData.size() - 1));
                    viewBinding.viewPager.setCurrentItem(viewPagerPositionData.get(viewPagerPositionData.size() - 1), true);
                    if (viewBinding.viewPager.getVisibility() == View.VISIBLE) {
                        try {
                            viewPagerPositionData.remove(viewPagerPositionData.size() - 1);
                        } catch (Exception e) {
                        }
                    }
                    backStackFragmentData = new ArrayList<>();
                }
            } else {
                Log.e("TAG", "backStackFragmentData <1");
                if (viewPagerPositionData.size() != 0) {
                    Log.e("TAG", "viewPagerPositionData != 0");
                    Log.e("TAG", "viewPager size: " + viewPagerPositionData.size() + "- " + viewPagerPositionData.toString());
                    Log.e("TAG", "view pager current pos- " + viewPagerPositionData.get(viewPagerPositionData.size() - 1));
                    setVisibility(1);
                    if (viewBinding.viewPager.getVisibility() == View.VISIBLE) {
                        viewPagerPositionData.remove(viewPagerPositionData.size() - 1);
                        Log.e("TAG", "after remove viewPager size- " + (viewPagerPositionData));
                    }
                    if (viewPagerPositionData.size() != 0) {
                        viewBinding.viewPager.setCurrentItem(viewPagerPositionData.get(viewPagerPositionData.size() - 1), true);
                    } else {
                        viewBinding.viewPager.setCurrentItem(0);
                    }
                    /*if (viewBinding.viewPager.getCurrentItem() == 0) {
                        terminal_home.callTerminalApi();
                    }*/
                } else {
                    if (viewBinding.viewPager.getCurrentItem() == 0) {
                        Log.e("TAG", "after remove viewPager size- " + (viewPagerPositionData));
                        finish();
                        // controller.Animation_backward(context);
                    } else {
                        SelectedTab(0);
                        viewBinding.viewPager.setCurrentItem(0, false);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("TAG", "Exception: " + e.toString());
        }
    }

    public void checkAddPosition() {
        int position = viewBinding.viewPager.getCurrentItem();
        Log.e("TAG", "checkAddPosition- " + position);
        new backgroundOperation(0, position).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void checkAddPosition_Remove_ADD() {
        int position = viewBinding.viewPager.getCurrentItem();
        Log.e(TAG, "checkAddPosition_Remove_ADD- " + position);
        new backgroundOperation(1, position).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public static class backgroundOperation extends AsyncTask<Void, Void, Void> {
        private int value;
        private int position;

        backgroundOperation(int value, int position) {
            this.value = value;
            this.position = position;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (value == 0) {
                if (position == 0) {
                    viewPagerPositionData = new ArrayList<Integer>();
                } else {
                    for (int i = 0; i < viewPagerPositionData.size(); i++) {
                        if (position == viewPagerPositionData.get(i)) {
                            viewPagerPositionData.remove(i);
                            break;
                        }
                    }
                    viewPagerPositionData.add(position);
                    Log.e("TAG", "checkAddPosition0 viewPagerPositionData- " + viewPagerPositionData.toString());
                }
            } else if (value == 1) {
                if (position == 0) {
                    viewPagerPositionData = new ArrayList<Integer>();
                } else {
                    for (int i = 0; i < viewPagerPositionData.size(); i++) {
                        if (position == viewPagerPositionData.get(i)) {
                            viewPagerPositionData.remove(i);
                        }
                    }
                    viewPagerPositionData.add(position);
                    Log.e("TAG", "checkAddPosition1 viewPagerPositionData- " + viewPagerPositionData.toString());
                }
            }
            return null;
        }
    }

    public void SelectedTab(int position) {
        switch (position) {
            case 0:
                if (!fragmentPopulate) {
                    backStackFragmentData = new ArrayList<>();
                    if (viewBinding.viewPager.getVisibility() == View.GONE) {
                        setVisibility(1);
                    }
                    changeColorStyle(R.color.colorWhite, R.color.colorBlack, R.color.colorBlack, R.color.colorBlack);
                }
                break;

            case 1:
                if (!fragmentPopulate) {
                    backStackFragmentData = new ArrayList<>();
                    if (viewBinding.viewPager.getVisibility() == View.GONE) {
                        setVisibility(1);
                    }
                    changeColorStyle(R.color.colorBlack, R.color.colorWhite, R.color.colorBlack, R.color.colorBlack);
                }
                break;

            case 2:
                if (!fragmentPopulate) {
                    backStackFragmentData = new ArrayList<>();
                    if (viewBinding.viewPager.getVisibility() == View.GONE) {
                        setVisibility(1);
                    }
                    changeColorStyle(R.color.colorBlack, R.color.colorBlack, R.color.colorWhite, R.color.colorBlack);
                }
                break;

            case 3:
                if (!fragmentPopulate) {
                    backStackFragmentData = new ArrayList<>();
                    if (viewBinding.viewPager.getVisibility() == View.GONE) {
                        setVisibility(1);
                    }
                    changeColorStyle(R.color.colorBlack, R.color.colorBlack, R.color.colorBlack, R.color.colorWhite);
                }
                break;
        }
    }

    public void changeColorStyle(int color1, int color2, int color3, int color4) {
        viewBinding.bottomtabs.terminalBottomImg.setColorFilter(ContextCompat.getColor(this, color1),
                android.graphics.PorterDuff.Mode.SRC_IN);
        viewBinding.bottomtabs.terminalBottomTxt.setTextColor(getResources().getColor(color1));

        viewBinding.bottomtabs.messageBottomImg.setColorFilter(ContextCompat.getColor(this, color2),
                android.graphics.PorterDuff.Mode.SRC_IN);
        viewBinding.bottomtabs.messageBottomTxt.setTextColor(getResources().getColor(color2));

        viewBinding.bottomtabs.myPlaceBottomImg.setColorFilter(ContextCompat.getColor(this, color3),
                android.graphics.PorterDuff.Mode.SRC_IN);
        viewBinding.bottomtabs.myPlaceBottomTxt.setTextColor(getResources().getColor(color3));

        viewBinding.bottomtabs.searchBottomImg.setColorFilter(ContextCompat.getColor(this, color4),
                android.graphics.PorterDuff.Mode.SRC_IN);
        viewBinding.bottomtabs.searchBottomTxt.setTextColor(getResources().getColor(color4));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.terminal_parent:
                if (viewBinding.viewPager.getCurrentItem() == 0) {
                    fragmentPopulate = false;
                    viewBinding.viewPager.setCurrentItem(0, false);
                    if (viewBinding.viewPager.getVisibility() == View.VISIBLE)
                        SelectedTab(0);
                } else {
                    fragmentPopulate = false;
                    viewBinding.viewPager.setCurrentItem(0, false);
                    if (viewBinding.viewPager.getVisibility() == View.VISIBLE)
                        SelectedTab(0);
                }
                break;
            case R.id.message_parent:
                fragmentPopulate = false;
                viewBinding.viewPager.setCurrentItem(1, false);
                if (viewBinding.viewPager.getCurrentItem() == 1) {
                    if (viewBinding.viewPager.getVisibility() == View.VISIBLE)
                        SelectedTab(1);
                }
                break;
            case R.id.my_place_parent:
                fragmentPopulate = false;
                viewBinding.viewPager.setCurrentItem(2, false);
                if (viewBinding.viewPager.getCurrentItem() == 2) {
                    if (viewBinding.viewPager.getVisibility() == View.VISIBLE)
                        SelectedTab(2);
                }
                break;
            case R.id.search_parent:
                fragmentPopulate = false;
                viewBinding.viewPager.setCurrentItem(3, false);
                if (viewBinding.viewPager.getCurrentItem() == 3) {
                    if (viewBinding.viewPager.getVisibility() == View.VISIBLE)
                        SelectedTab(3);
                }
                break;
        }
    }

    public void viewPagerSwipe(boolean isSwipe) {
        MyLogger.e("checkapp", "viewPagerSwipe isSwipe: " + isSwipe);
        if (isSwipe) {
            MyLogger.e("checkapp", "viewPagerSwipe setPagingEnabled(true)");
            viewBinding.viewPager.setPagingEnabled(true);
        } else {
            MyLogger.e("checkapp", "viewPagerSwipe setPagingEnabled(false)");
            viewBinding.viewPager.setPagingEnabled(false);
        }
    }

    @Override
    public void onRecycleClick(int position, String value) {
        checkAddPosition_Remove_ADD();
        addFragmentView(getString(R.string.show_fragment), new ShowNameFragment(), value);
        changeColorStyle(R.color.colorWhite, R.color.colorBlack, R.color.colorBlack, R.color.colorBlack);
    }

}

