package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("ChartFeatureM")
public class ChartFeatureM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer chartId;
    private String feature1;
    private String feature2;
    private String feature3;
    private ChartM chart;
    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    public String getFeature1() {
        return feature1;
    }

    public void setFeature1(String feature1) {
        this.feature1 = feature1;
    }

    public String getFeature2() {
        return feature2;
    }

    public void setFeature2(String feature2) {
        this.feature2 = feature2;
    }

    public String getFeature3() {
        return feature3;
    }

    public ChartM getChart() {
        return chart;
    }

    public void setChart(ChartM chart) {
        this.chart = chart;
    }

    public void setFeature3(String feature3) {
        this.feature3 = feature3;
    }
}
