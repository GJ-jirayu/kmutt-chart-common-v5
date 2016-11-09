package th.ac.kmutt.chart.fusion.model;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by imake on 21/10/2015.
 */
public class DatasetFusionM {
    private String seriesname;
    @SerializedName("data")
    @XStreamAlias("data")
    private List<DataFusionM> data;

    public List<DataFusionM> getData() {
        return data;
    }

    public void setData(List<DataFusionM> data) {
        this.data = data;
    }

    public String getSeriesname() {
        return seriesname;
    }

    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }
}
