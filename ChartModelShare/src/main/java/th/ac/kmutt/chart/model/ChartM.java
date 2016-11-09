package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */

@XStreamAlias("ChartM")
public class ChartM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer chartId;
    private String chartName;
    private String chartType;
    private String chartModel;
    private String chartXml;
    private String chartJson;
    private String activeFlag;
    private String dataJson;
    private String advProp;
    private ChartFeatureM chartFeature;
    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public String getChartModel() {
        return chartModel;
    }

    public void setChartModel(String chartModel) {
        this.chartModel = chartModel;
    }

    public String getChartXml() {
        return chartXml;
    }

    public void setChartXml(String chartXml) {
        this.chartXml = chartXml;
    }

    public String getChartJson() {
        return chartJson;
    }

    public void setChartJson(String chartJson) {
        this.chartJson = chartJson;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public ChartFeatureM getChartFeature() {
        return chartFeature;
    }

    public void setChartFeature(ChartFeatureM chartFeature) {
        this.chartFeature = chartFeature;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getAdvProp() {
        return advProp;
    }

    public void setAdvProp(String advProp) {
        this.advProp = advProp;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }
}
