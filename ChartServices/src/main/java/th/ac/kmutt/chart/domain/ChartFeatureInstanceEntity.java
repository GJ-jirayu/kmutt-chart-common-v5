package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "CHART_FEATURE_INSTANCE", schema = "", catalog = "CHART_DB")
public class ChartFeatureInstanceEntity  implements Serializable {
    private String instanceId;
    private Integer chartId;
    private Integer featureId;
    private ChartEntity chartByChartId;
    private FeatureEntity featureByFeatureId;

    @Id
    @Column(name = "INSTANCE_ID")
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Basic
    @Column(name = "CHART_ID",insertable = false,updatable = false)
    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    @Basic
    @Column(name = "FEATURE_ID",insertable = false,updatable = false)
    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartFeatureInstanceEntity that = (ChartFeatureInstanceEntity) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (chartId != null ? !chartId.equals(that.chartId) : that.chartId != null) return false;
        if (featureId != null ? !featureId.equals(that.featureId) : that.featureId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (chartId != null ? chartId.hashCode() : 0);
        result = 31 * result + (featureId != null ? featureId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CHART_ID", referencedColumnName = "CHART_ID")
    public ChartEntity getChartByChartId() {
        return chartByChartId;
    }

    public void setChartByChartId(ChartEntity chartByChartId) {
        this.chartByChartId = chartByChartId;
    }

    @ManyToOne
    @JoinColumn(name = "FEATURE_ID", referencedColumnName = "FEATURE_ID")
    public FeatureEntity getFeatureByFeatureId() {
        return featureByFeatureId;
    }

    public void setFeatureByFeatureId(FeatureEntity featureByFeatureId) {
        this.featureByFeatureId = featureByFeatureId;
    }
}
