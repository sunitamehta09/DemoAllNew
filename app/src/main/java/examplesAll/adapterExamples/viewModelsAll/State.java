package examplesAll.adapterExamples.viewModelsAll;

import com.google.gson.annotations.SerializedName;

public class State {
    @SerializedName("state_id")
    String id;
    @SerializedName("state_country_id")
    String country;
    @SerializedName("state_name")
    String name;
    @SerializedName("is_check")
    String is_check;
    @SerializedName("is_top_state")
    String is_Top;

    public String getIs_check() {
        return is_check;
    }

    public void setIs_check(String is_check) {
        this.is_check = is_check;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_Top() {
        return is_Top;
    }

    public void setIs_Top(String is_Top) {
        this.is_Top = is_Top;
    }
}
