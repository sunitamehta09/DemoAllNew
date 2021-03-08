package examplesAll.adapterExamples.adapterAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.self.demoaall.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import controllerAll.AppUtilsAll.MyLogger;
import examplesAll.adapterExamples.callBacksAll.CityClickListener;
import examplesAll.adapterExamples.viewModelsAll.State;

public class SelectedCityListAdapter extends RecyclerView.Adapter<SelectedCityListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<State> selectedStates;
    private CityClickListener cityClickListener;

    public SelectedCityListAdapter(Context context, ArrayList<State> states, CityClickListener cityClickListener) {
        this.context = context;
        this.selectedStates = states;
        this.cityClickListener = cityClickListener;
        for (int i = 0; i < selectedStates.size(); i++) {
            MyLogger.e("cityselection SelectedCityListAdapter: ", "id: " + selectedStates.get(i).getId() + "name: " + selectedStates.get(i).getName());
        }
    }

    public void dataChange(ArrayList<State> states) {
        selectedStates = states;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_selected_city, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        State models = selectedStates.get(position);
        holder.name.setText(models.getName());
        holder.delete.setTag(position);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(v.getTag().toString());
                MyLogger.e("cities adapter", "on deleted=" + selectedStates.get(position).getName());
                cityClickListener.clickCity("delete", selectedStates.get(position).getId(), selectedStates.get(position).getIs_check());
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedStates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView delete;
        public TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            name = (TextView) itemView.findViewById(R.id.name);
        }

    }
}
