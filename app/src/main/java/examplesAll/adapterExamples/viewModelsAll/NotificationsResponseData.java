package examplesAll.adapterExamples.viewModelsAll;

import java.util.ArrayList;

public class NotificationsResponseData {
    public ArrayList<NotificationDataModel> data;

    public ArrayList<NotificationDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<NotificationDataModel> data) {
        this.data = data;
    }
    public String message,status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
