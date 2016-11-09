package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "CHART_FEATURE", schema = "", catalog = "CHART_DB")
public class ChartFeatureEntity  implements Serializable

    {
    private Integer chartId;
    private String feature1;
    private String feature2;
    private String feature3;
    private ChartEntity chartByChartId;

    @Id
    @Column(name = "CHART_ID")
    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    @Basic
    @Column(name = "FEATURE_1")
    public String getFeature1() {
        return feature1;
    }

    public void setFeature1(String feature1) {
        this.feature1 = feature1;
    }

    @Basic
    @Column(name = "FEATURE_2")
    public String getFeature2() {
        return feature2;
    }

    public void setFeature2(String feature2) {
        this.feature2 = feature2;
    }

    @Basic
    @Column(name = "FEATURE_3")
    public String getFeature3() {
        return feature3;
    }

    public void setFeature3(String feature3) {
        this.feature3 = feature3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartFeatureEntity that = (ChartFeatureEntity) o;

        if (chartId != null ? !chartId.equals(that.chartId) : that.chartId != null) return false;
        if (feature1 != null ? !feature1.equals(that.feature1) : that.feature1 != null) return false;
        if (feature2 != null ? !feature2.equals(that.feature2) : that.feature2 != null) return false;
        if (feature3 != null ? !feature3.equals(that.feature3) : that.feature3 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chartId != null ? chartId.hashCode() : 0;
        result = 31 * result + (feature1 != null ? feature1.hashCode() : 0);
        result = 31 * result + (feature2 != null ? feature2.hashCode() : 0);
        result = 31 * result + (feature3 != null ? feature3.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "CHART_ID", referencedColumnName = "CHART_ID", nullable = false)
    public ChartEntity getChartByChartId() {
        return chartByChartId;
    }

    public void setChartByChartId(ChartEntity chartByChartId) {
        this.chartByChartId = chartByChartId;
    }
}
