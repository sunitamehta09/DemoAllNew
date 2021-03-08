package examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;

public class MyPlaceFragment extends Fragment {
    public MyPlaceFragment() {
        // Required empty public constructor
    }

    public static MyPlaceFragment newInstance(String param1, String param2) {
        MyPlaceFragment fragment = new MyPlaceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_place, container, false);
    }
}