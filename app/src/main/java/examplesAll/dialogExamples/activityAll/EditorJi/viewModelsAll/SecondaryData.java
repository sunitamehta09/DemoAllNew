package examplesAll.dialogExamples.activityAll.EditorJi.viewModelsAll;

public class SecondaryData {
    public int id;
    public String reportText;
    public boolean showSecondary;
    public int select = 0;

    public boolean isShowSecondary() {
        return showSecondary;
    }

    public void setShowSecondary(boolean showSecondary) {
        this.showSecondary = showSecondary;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }
}
