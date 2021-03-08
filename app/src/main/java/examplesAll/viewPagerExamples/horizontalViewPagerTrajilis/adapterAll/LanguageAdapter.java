package examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.adapterAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.AdapterLanguageBinding;
import com.self.demoaall.databinding.AdapterStatesBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import controllerAll.utilsAll.AppUtils;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.activitiesAll.HorizontalViewPagerExample;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.viewModelsAll.LanguageOptionsData;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.viewModelsAll.StatesOptionData;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.callBacks.PlayListClickListener;

public class LanguageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<LanguageOptionsData> languageArray;
    private ArrayList<StatesOptionData> statesArray;
    private Context context;
    final int VIEW_TYPE_LANGUAGE = 0;
    final int VIEW_TYPE_STATES = 1;

    public LanguageAdapter(Context context, ArrayList<LanguageOptionsData> languageArray, ArrayList<StatesOptionData> statesArray) {
        this.context = context;
        this.languageArray = languageArray;
        this.statesArray = statesArray;
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder {
        AdapterLanguageBinding languageBinding;

        public LanguageViewHolder(@NonNull AdapterLanguageBinding viewBinding) {
            super(viewBinding.getRoot());
            this.languageBinding = viewBinding;
        }
    }

    public class StatesViewHolder extends RecyclerView.ViewHolder {
        AdapterStatesBinding statesBinding;

        public StatesViewHolder(@NonNull AdapterStatesBinding viewBinding) {
            super(viewBinding.getRoot());
            this.statesBinding = viewBinding;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_LANGUAGE) {
            AdapterLanguageBinding adapterLanguageBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                            R.layout.adapter_language, viewGroup, false);
            return new LanguageViewHolder(adapterLanguageBinding);
        } else if (i == VIEW_TYPE_STATES) {
            AdapterStatesBinding adapterStatesBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                            R.layout.adapter_states, viewGroup, false);
            return new StatesViewHolder(adapterStatesBinding);
        } else
            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int viewType) {
        if (viewType == VIEW_TYPE_LANGUAGE) {
            LanguageViewHolder languageViewHolder = (LanguageViewHolder) viewHolder;
            LanguageHorizontalAdapter languageHorizontalAdapter = new LanguageHorizontalAdapter(context, new PlayListClickListener() {
                @Override
                public void onItemClick(int position, String type) {
                    AppUtils.Companion.showToast(context, statesArray.get(position).label);
                }
            }, languageArray);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            languageViewHolder.languageBinding.rvLanguage.setLayoutManager(layoutManager);
            languageViewHolder.languageBinding.rvLanguage.setAdapter(languageHorizontalAdapter);

            languageViewHolder.languageBinding.rvLanguage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()){
                        case MotionEvent.ACTION_MOVE:
                            ((HorizontalViewPagerExample)context).viewPagerSwipe(false);
                            break;
                        case MotionEvent.ACTION_UP:
                            ((HorizontalViewPagerExample)context).viewPagerSwipe(true);
                            break;
                    }
                    return false;
                }
            });
        } else if (viewType == VIEW_TYPE_STATES) {
            StatesViewHolder statesViewHolder = (StatesViewHolder) viewHolder;
            StatesAdapter statesAdapter = new StatesAdapter(context, new PlayListClickListener() {
                @Override
                public void onItemClick(int position, String type) {
                    AppUtils.Companion.showToast(context, statesArray.get(position).label);
                }
            }, statesArray);
            GridLayoutManager statesManager = new GridLayoutManager(context, 3);
            statesViewHolder.statesBinding.rvStates.setLayoutManager(statesManager);
            statesViewHolder.statesBinding.rvStates.setAdapter(statesAdapter);
            /*LinearLayoutManager statesManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            statesViewHolder.statesBinding.rvStates.setLayoutManager(statesManager);
            statesViewHolder.statesBinding.rvStates.setAdapter(statesAdapter);*/
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return VIEW_TYPE_LANGUAGE;
        else if (position == 1)
            return VIEW_TYPE_STATES;
        return -1;
    }


}
