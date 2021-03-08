package examplesAll.adapterExamples.adapterAll;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.self.demoaall.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import controllerAll.AppUtilsAll.MyLogger;
import examplesAll.adapterExamples.callBacksAll.RecycleCallBacks;
import examplesAll.adapterExamples.viewModelsAll.NotificationDataModel;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<NotificationDataModel> notificationsList;
    private RecycleCallBacks recycleListener;

    public PaginationAdapter(Context context, ArrayList<NotificationDataModel> notificationsList, RecycleCallBacks recycleListener) {
        this.context = context;
        this.notificationsList = notificationsList;
        this.recycleListener = recycleListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txt_notification_title.setText(notificationsList.get(position).getTitle());
        try {
            holder.txt_notification_duration.setText(new SimpleDateFormat("hh:mmaa, dd MMM", Locale.ENGLISH)
                    .format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(notificationsList.get(position).getCreated_On())).toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!TextUtils.isEmpty(notificationsList.get(position).getThumbimage())) {
            Glide.with(context).load(notificationsList.get(position).getThumbimage()).into(holder.thumbnail);
        }

        if (notificationsList.get(position).getStatus().equals("read")) {
            MyLogger.e("checkapp", "read notification Title: " + notificationsList.get(position).getTitle());
            holder.rlMain.setAlpha(0.5f);
        }

        holder.rlThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycleListener.onRecycleClick(position, notificationsList.get(position).title);
                notificationsList.get(position).setStatus("read");
                notifyDataSetChanged();
            }
        });
    }

    public void updateNotificationData(ArrayList<NotificationDataModel> list) {
        this.notificationsList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_notification_title, txt_notification_duration;
        public RelativeLayout rlThumbnail, rlMain;
        public LinearLayout ln_share;
        public ImageView thumbnail;
        public View blurView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_notification_title = (TextView) itemView.findViewById(R.id.txt_notification_title);
            txt_notification_duration = (TextView) itemView.findViewById(R.id.txt_notification_duration);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            rlThumbnail = (RelativeLayout) itemView.findViewById(R.id.rlThumbnail);
            rlMain = (RelativeLayout) itemView.findViewById(R.id.rlMain);
            blurView = (View) itemView.findViewById(R.id.blurView);
        }
    }
}
