package examplesAll.adapterExamples.viewModelsAll;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class NotificationDataModel {
    @SerializedName("notification_id")
    public String notification_id = "";
    @SerializedName("title")
    public String title;
    @SerializedName("header_title")
    public String header_title;
    @SerializedName("type")
    public String type;
    @SerializedName("duration")
    public String duration;
    @SerializedName("thumbimage")
    public String thumbimage;
    @SerializedName("lang_code")
    public String lang_code;
    @SerializedName("share_url")
    public String share_url;
    @SerializedName("created_On")
    public String created_On;
    @SerializedName("is_breaking")
    public String is_breaking;
    public  String status = "unread";
    @SerializedName("description")
    public String description;
    @SerializedName("video_id")
    public String video_id;

    public String getNotification_id() {
        if (TextUtils.isEmpty(notification_id))
            return "";
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThumbimage() {
        return thumbimage;
    }

    public void setThumbimage(String thumbimage) {
        this.thumbimage = thumbimage;
    }

    public String getLang_code() {
        return lang_code;
    }

    public void setLang_code(String lang_code) {
        this.lang_code = lang_code;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getCreated_On() {
        return created_On;
    }

    public void setCreated_On(String created_On) {
        this.created_On = created_On;
    }

    public String getIs_breaking() {
        return is_breaking;
    }

    public void setIs_breaking(String is_breaking) {
        this.is_breaking = is_breaking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getHeader_title() {
        return header_title;
    }

    public void setHeader_title(String header_title) {
        this.header_title = header_title;
    }
}
