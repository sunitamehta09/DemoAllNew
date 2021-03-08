package examplesAll.selectUnselectExamples.viewModelsAll;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("category_name")
    public String categoryName;
    @SerializedName("category_id")
    public String categoryId;
    @SerializedName("priority")
    public String priority;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
