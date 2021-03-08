package examplesAll.adapterExamples.adapterAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ItemStatesBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.callBacks.PlayListClickListener;
import examplesAll.adapterExamples.viewModelsAll.StatesOptionData;

public class StatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    PlayListClickListener listener;
    public ArrayList<StatesOptionData> playList;

    public StatesAdapter(Context context, PlayListClickListener listener, ArrayList<StatesOptionData> playList) {
        this.playList = playList;
        this.listener = listener;
        this.context = context;
    }

    public class StatesViewHolder extends RecyclerView.ViewHolder {
        ItemStatesBinding statesBinding;

        public StatesViewHolder(@NonNull ItemStatesBinding binding) {
            super(binding.getRoot());
            statesBinding = binding;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemStatesBinding statesBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_states, viewGroup, false);
        return new StatesViewHolder(statesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        StatesViewHolder statesViewHolder = (StatesViewHolder) viewHolder;
        statesViewHolder.statesBinding.txtMainPlaylistTitle.setText(playList.get(position).getLabel());

        statesViewHolder.statesBinding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position, "states");
            }
        });
    }

    @Override
    public int getItemCount() {
        return playList.size();
    }

    public void dataChange(ArrayList<StatesOptionData> playList) {
        this.playList = playList;
    }
}

