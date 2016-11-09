package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "CHART", schema = "", catalog = "CHART_DB")
public class ChartEntity implements Serializable {
    @Id
    @Column(name = "CHART_ID")
    private Integer chartId;

    @Basic
    @Column(name = "CHART_NAME")
    private String chartName;

    @Basic
    @Column(name = "CHART_TYPE")
    private String chartType;

    @Basic
    @Column(name = "CHART_MODEL")
    private String chartModel;

    @Basic
    @Column(name = "CHART_XML")
    private String chartXml;

    @Basic
    @Column(name = "CHART_JSON")
    private String chartJson;

    @Basic
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;

    @Column(name = "DATA_JSON")
    private String dataJson;


    @Column(name = "ADV_PROP")
    private String advProp;

    @OneToOne(mappedBy = "chartByChartId")
    private ChartFeatureEntity chartFeatureByChartId;
    /*
    private Collection<ChartFeatureInstanceEntity> chartFeatureInstancesByChartId;
    private Collection<ChartFeatureMappigEntity> chartFeatureMappigsByChartId;
    private Collection<ChartInstanceEntity> chartInstancesByChartId;
    private Collection<ServiceChartMappingEntity> serviceChartMappingsByChartId;
*/

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

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartEntity that = (ChartEntity) o;

        if (chartId != null ? !chartId.equals(that.chartId) : that.chartId != null) return false;
        if (chartName != null ? !chartName.equals(that.chartName) : that.chartName != null) return false;
        if (chartType != null ? !chartType.equals(that.chartType) : that.chartType != null) return false;
        if (chartModel != null ? !chartModel.equals(that.chartModel) : that.chartModel != null) return false;
        if (chartXml != null ? !chartXml.equals(that.chartXml) : that.chartXml != null) return false;
        if (chartJson != null ? !chartJson.equals(that.chartJson) : that.chartJson != null) return false;
        if (activeFlag != null ? !activeFlag.equals(that.activeFlag) : that.activeFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chartId != null ? chartId.hashCode() : 0;
        result = 31 * result + (chartName != null ? chartName.hashCode() : 0);
        result = 31 * result + (chartType != null ? chartType.hashCode() : 0);
        result = 31 * result + (chartModel != null ? chartModel.hashCode() : 0);
        result = 31 * result + (chartXml != null ? chartXml.hashCode() : 0);
        result = 31 * result + (chartJson != null ? chartJson.hashCode() : 0);
        result = 31 * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        return result;
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


    public ChartFeatureEntity getChartFeatureByChartId() {
        return chartFeatureByChartId;
    }

    public void setChartFeatureByChartId(ChartFeatureEntity chartFeatureByChartId) {
        this.chartFeatureByChartId = chartFeatureByChartId;
    }


}
