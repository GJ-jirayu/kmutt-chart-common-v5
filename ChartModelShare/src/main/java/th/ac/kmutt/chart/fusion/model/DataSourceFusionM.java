package th.ac.kmutt.chart.fusion.model;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imake on 21/10/2015.
 */
public class DataSourceFusionM implements Serializable {
    @SerializedName("chart")
    @XStreamAlias("chart")
    private ChartFusionM chartFusionM;

    @SerializedName("categories")
    @XStreamAlias("categories")
    private List<CategoriesFusionM> categories;

    @SerializedName("dataset")
    @XStreamAlias("dataset")
    private List<DatasetFusionM> dataset;

    public ChartFusionM getChartFusionM() {
        return chartFusionM;
    }

    public void setChartFusionM(ChartFusionM chartFusionM) {
        this.chartFusionM = chartFusionM;
    }

    public List<CategoriesFusionM> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesFusionM> categories) {
        this.categories = categories;
    }

    public List<DatasetFusionM> getDataset() {
        return dataset;
    }

    public void setDataset(List<DatasetFusionM> dataset) {
        this.dataset = dataset;
    }
}
