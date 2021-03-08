package examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.adapterAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ItemLanguageBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.viewModelsAll.LanguageOptionsData;
import examplesAll.viewPagerExamples.horizontalViewPagerTrajilis.callBacks.PlayListClickListener;

public class LanguageHorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    PlayListClickListener listener;
    public ArrayList<LanguageOptionsData> playList;

    public LanguageHorizontalAdapter(Context context, PlayListClickListener listener, ArrayList<LanguageOptionsData> playList) {
        this.playList = playList;
        this.listener = listener;
        this.context = context;
    }

    public class LanguageHolder extends RecyclerView.ViewHolder {
        ItemLanguageBinding languageBinding;

        public LanguageHolder(@NonNull ItemLanguageBinding binding) {
            super(binding.getRoot());
            languageBinding = binding;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemLanguageBinding itemStoriesBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_language, viewGroup, false);
        return new LanguageHolder(itemStoriesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        LanguageHolder languageHolder = (LanguageHolder) viewHolder;
        languageHolder.languageBinding.txtMainPlaylistTitle.setText(playList.get(position).getLabel());

        languageHolder.languageBinding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position, "Language");
            }
        });

     }

    @Override
    public int getItemCount() {
        return playList.size();
    }

    public void dataChange(ArrayList<LanguageOptionsData> playList) {
        this.playList = playList;
    }

    public ArrayList<LanguageOptionsData> getPlayList() {
        return playList;
    }

}
