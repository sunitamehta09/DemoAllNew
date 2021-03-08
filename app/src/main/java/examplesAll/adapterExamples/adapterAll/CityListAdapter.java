package examplesAll.adapterExamples.adapterAll;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.self.demoaall.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import controllerAll.AppUtilsAll.MyLogger;
import controllerAll.FontUtilsAll.FontCache;
import examplesAll.adapterExamples.callBacksAll.CityClickListener;
import examplesAll.adapterExamples.viewModelsAll.State;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.MyViewHolder> {
    private ArrayList<State> states = new ArrayList<>();
    private Context context;
    private CityClickListener cityClickListener;

    public CityListAdapter(Context context, ArrayList<State> states, CityClickListener listener) {
        this.context = context;
        this.states = states;
        this.cityClickListener = listener;
        MyLogger.e("cityselection CityListAdapter: ", "size:" +states.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_city_selection, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        State models = states.get(position);

        if (models == null) {
            holder.blueline.setVisibility(View.GONE);
            holder.name.setVisibility(View.GONE);
            return;
        }
        holder.blueline.setVisibility(View.GONE);
        holder.name.setVisibility(View.VISIBLE);

        holder.name.setText(models.getName());
        if (models.getIs_check().equalsIgnoreCase("Yes")) {
            holder.name.setChecked(true);
            holder.name.setTextColor(Color.parseColor("#BD2A71"));
            Typeface customFont = FontCache.getTypeface("fontMuliBlack.ttf", context);
            holder.name.setTypeface(customFont);
        } else {
            holder.name.setChecked(false);
            holder.name.setTextColor(Color.parseColor("#000000"));
            Typeface customFont = FontCache.getTypeface("fontMuliSemiBold.ttf", context);
            holder.name.setTypeface(customFont);
        }

        holder.name.setTag(position);

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView t = (CheckedTextView) v;
                int position = Integer.parseInt(v.getTag().toString());
                String city = states.get(position).getName();
                cityClickListener.clickCity("select", states.get(position).getId(), states.get(position).getIs_check());
            }
        });
    }

    public void dataChange(ArrayList<State> states) {
        this.states = states;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckedTextView name;
        View blueline;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (CheckedTextView) itemView.findViewById(R.id.name);
            blueline = itemView.findViewById(R.id.blueline);
        }
    }
}
