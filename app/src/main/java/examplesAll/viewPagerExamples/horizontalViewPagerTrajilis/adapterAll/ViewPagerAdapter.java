package examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.adapterAll;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class ViewPagerAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer{
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public void addFrag(Fragment fragment, Bundle data) {
        mFragmentList.add(fragment);
        fragment.setArguments(data);
    }

    public void addFrag(Fragment fragment, String title, Bundle data) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        fragment.setArguments(data);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }


//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        if (position >= getCount()) {
//            FragmentManager manager = ((Fragment) object).getFragmentManager();
//            FragmentTransaction trans = manager.beginTransaction();
//            trans.remove((Fragment) object);
//            trans.commit();
//            notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public Parcelable saveState() {
//        return null;
//    }
//
//    @Override
//    public void restoreState(Parcelable state, ClassLoader loader) {
//
//    }

    @Override
    public void transformPage(View page, float position) {
//        if (position < -1) {
//            // This page is way off-screen to the left
//            page.setAlpha(0);
//        } else if (position <= 1) {
//            page.setAlpha(1);
//
//            // Counteract the default slide transition
//            page.setTranslationX(page.getWidth() * -position);
//
//            // set Y position to swipe in from top
//            float yPosition = position * page.getHeight();
//            page.setTranslationY(yPosition);
//        } else {
//            // This page is way off screen to the right
//            page.setAlpha(0);
//        }
    }
 }
