package examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.fragmentAll;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.activitiesAll.HorizontalViewPagerExample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.FragmentTerminalBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TerminalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TerminalFragment extends Fragment {
    private FragmentTerminalBinding viewBinding;

    public TerminalFragment() {
        // Required empty public constructor
    }

    public static TerminalFragment newInstance(String param1, String param2) {
        TerminalFragment fragment = new TerminalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_terminal, container, false);
        viewBinding.txtStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HorizontalViewPagerExample)getContext()).checkAddPosition_Remove_ADD();
                ((HorizontalViewPagerExample)getContext()).addFragmentView(getString(R.string.student_fragment), new StudentsNameFragment());
                ((HorizontalViewPagerExample)getContext()).changeColorStyle(R.color.colorWhite, R.color.colorBlack, R.color.colorBlack, R.color.colorBlack);
            }
        });
        return viewBinding.getRoot();
    }
}