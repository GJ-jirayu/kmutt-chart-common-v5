package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "CHART_FEATURE_MAPPING", schema = "", catalog = "CHART_DB")
// @IdClass(ChartFeatureMappingEntityPK.class)
public class ChartFeatureMappingEntity  implements Serializable {
   /* private Integer featureId;
    private Integer chartId;
    */
    private ChartEntity chartByChartId;
    private FeatureEntity featureByFeatureId;
    @EmbeddedId
    private ChartFeatureMappingEntityPK id;
/*
    @Id
    @Column(name = "FEATURE_ID")
    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    @Id
    @Column(name = "CHART_ID")
    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }
*/

    @ManyToOne
    @JoinColumn(name = "CHART_ID", referencedColumnName = "CHART_ID", nullable = false)
    public ChartEntity getChartByChartId() {
        return chartByChartId;
    }

    public void setChartByChartId(ChartEntity chartByChartId) {
        this.chartByChartId = chartByChartId;
    }

    @ManyToOne
    @JoinColumn(name = "FEATURE_ID", referencedColumnName = "FEATURE_ID", nullable = false)
    public FeatureEntity getFeatureByFeatureId() {
        return featureByFeatureId;
    }

    public void setFeatureByFeatureId(FeatureEntity featureByFeatureId) {
        this.featureByFeatureId = featureByFeatureId;
    }

    public ChartFeatureMappingEntityPK getId() {
        return id;
    }

    public void setId(ChartFeatureMappingEntityPK id) {
        this.id = id;
    }
}
