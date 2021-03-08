package examplesAll.adapterExamples.activityAll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import controllerAll.utilsAll.AppConstants;
import controllerAll.AppSharedPreferencesAll.AppSharedPreferenceEj;
import controllerAll.utilsAll.AppUtils;
import controllerAll.AppUtilsAll.MyLogger;
import controllerAll.validations.InternetValidations;
import examplesAll.adapterExamples.IteratorAll.ApiIterator;
import examplesAll.adapterExamples.callBacksAll.RecycleCallBacks;
import examplesAll.adapterExamples.UtilsAll.SaveDataAll;
import examplesAll.adapterExamples.adapterAll.PaginationAdapter;
import examplesAll.adapterExamples.viewModelsAll.NotificationDataModel;
import examplesAll.adapterExamples.viewModelsAll.NotificationsResponseData;

import android.os.Bundle;
import android.view.View;

import com.earshot.apiservice.RetrofitApiCallBack;
import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityPaginationAdapterBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PaginationExample extends AppCompatActivity {
    private ActivityPaginationAdapterBinding viewBinding;
    private ArrayList<String> readNotificationList;
    private ArrayList<NotificationDataModel> notificationsList = new ArrayList<>();
    private PaginationAdapter adapter;
    private static String TAG = "PaginationExample";
    private int pageNo = 1;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(PaginationExample.this, R.layout.activity_pagination_adapter);

        readNotificationList = SaveDataAll.getReadNotificationData(PaginationExample.this);
        MyLogger.e(TAG, "readNotificationList: " + readNotificationList);

        adapter = new PaginationAdapter(PaginationExample.this, notificationsList, new RecycleCallBacks() {
            @Override
            public void onRecycleClick(int position, String value) {
                if (!readNotificationList.contains(notificationsList.get(position).getNotification_id())) {
                    readNotificationList.add(notificationsList.get(position).getNotification_id());
                    AppSharedPreferenceEj.setStringPreferences(
                            PaginationExample.this, AppConstants.PREF_READNOTIFICATIONLIST,
                            AppUtils.getGsonInstance().toJson(readNotificationList));
                }
            }
        });

        linearLayoutManager = new LinearLayoutManager(PaginationExample.this, LinearLayoutManager.VERTICAL, false);
        viewBinding.notificationslist.setLayoutManager(linearLayoutManager);
        viewBinding.notificationslist.setAdapter(adapter);

        viewBinding.notificationslist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (notificationsList.size() > 0 && viewBinding.moreloader.getVisibility() == View.GONE
                            && lastVisibleItem == notificationsList.size() - 1) {
                        if (InternetValidations.Companion.isNetWorkStatusAvialable(PaginationExample.this))
                            viewBinding.moreloader.setVisibility(View.VISIBLE);
                        callNotificationApi(pageNo);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        callNotificationApi(pageNo);
    }

    private void callNotificationApi(final int pageNumber) {
        ApiIterator.callNotificationsApi(PaginationExample.this, AppConstants.ACCESS_TOKEN,
                String.valueOf(pageNumber), "10", new RetrofitApiCallBack<NotificationsResponseData>() {
                    @Override
                    public void onApiSuccess(NotificationsResponseData childList) {
                        viewBinding.moreloader.setVisibility(View.GONE);
                        if (pageNumber == 1) {
                            for (int i = 0; i < childList.getData().size(); i++) {
                                if (readNotificationList.contains(childList.getData().get(i).getNotification_id()))
                                    childList.getData().get(i).setStatus("read");
                                else
                                    childList.getData().get(i).setStatus("unread");
                            }
                            readNotificationList.clear();
                            for (int i = 0; i < childList.getData().size(); i++) {
                                if (!childList.getData().get(i).getNotification_id().equalsIgnoreCase(""))
                                    readNotificationList.add(childList.getData().get(i).getNotification_id());
                            }
                            AppSharedPreferenceEj.removeSharedPreferences(
                                    PaginationExample.this, AppConstants.PREF_READNOTIFICATIONLIST);
                            AppSharedPreferenceEj.setStringPreferences(
                                    PaginationExample.this, AppConstants.PREF_READNOTIFICATIONLIST,
                                    AppUtils.getGsonInstance().toJson(readNotificationList));

                        }
                        if (pageNumber == 1)
                            notificationsList.clear();
                        for (int j = 0; j < childList.getData().size(); j++) {
                            notificationsList.add(childList.getData().get(j));
                        }
                        if (notificationsList.size() > 0) {
                            if (adapter != null)
                                adapter.notifyDataSetChanged();
                            pageNo++;
                        } else
                            AppUtils.showToast(PaginationExample.this, getString(R.string.no_more_data));
                    }

                    @Override
                    public void onApiError(@NotNull String errorMsg, int errorCode) {
                        viewBinding.moreloader.setVisibility(View.GONE);
                    }

                    @Override
                    public void showProgress(boolean showProgress) {

                    }
                });
    }


}