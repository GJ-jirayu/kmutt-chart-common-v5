package th.ac.kmutt.chart.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@Embeddable
public class ChartFeatureMappingEntityPK implements Serializable {
    @Column(name = "FEATURE_ID",insertable = false,updatable = false)
    private Integer featureId;

    @Column(name = "CHART_ID",insertable = false,updatable = false)
    private Integer chartId;


    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }


    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartFeatureMappingEntityPK that = (ChartFeatureMappingEntityPK) o;

        if (featureId != null ? !featureId.equals(that.featureId) : that.featureId != null) return false;
        if (chartId != null ? !chartId.equals(that.chartId) : that.chartId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = featureId != null ? featureId.hashCode() : 0;
        result = 31 * result + (chartId != null ? chartId.hashCode() : 0);
        return result;
    }
}
