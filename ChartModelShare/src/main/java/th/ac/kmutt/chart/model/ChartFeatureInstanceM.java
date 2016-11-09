package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015
 */
@XStreamAlias("ChartFeatureInstanceM")
public class ChartFeatureInstanceM  extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private String instanceId;
    private Integer chartId;
    private Integer featureId;
    private ChartM chart;
    private FeatureM feature;
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId)
    {
        this.featureId = featureId;
    }

    public ChartM getChart() {
        return chart;
    }

    public void setChart(ChartM chart) {
        this.chart = chart;
    }

    public FeatureM getFeature() {
        return feature;
    }

    public void setFeature(FeatureM feature) {
        this.feature = feature;
    }
}
