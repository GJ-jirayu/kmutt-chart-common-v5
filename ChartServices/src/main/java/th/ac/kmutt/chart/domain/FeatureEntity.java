package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "FEATURE", schema = "", catalog = "CHART_DB")
public class FeatureEntity  implements Serializable {
    private Integer featureId;
    private String featureName;
    private String featureValue;
    private String type;
    /*
    private Collection<ChartFeatureInstanceEntity> chartFeatureInstancesByFeatureId;
    private Collection<ChartFeatureMappigEntity> chartFeatureMappigsByFeatureId;
*/
    @Id
    @Column(name = "FEATURE_ID")
    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    @Basic
    @Column(name = "FEATURE_NAME")
    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @Basic
    @Column(name = "FEATURE_VALUE")
    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureEntity that = (FeatureEntity) o;

        if (featureId != null ? !featureId.equals(that.featureId) : that.featureId != null) return false;
        if (featureName != null ? !featureName.equals(that.featureName) : that.featureName != null) return false;
        if (featureValue != null ? !featureValue.equals(that.featureValue) : that.featureValue != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = featureId != null ? featureId.hashCode() : 0;
        result = 31 * result + (featureName != null ? featureName.hashCode() : 0);
        result = 31 * result + (featureValue != null ? featureValue.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
/*
    @OneToMany(mappedBy = "featureByFeatureId")
    public Collection<ChartFeatureInstanceEntity> getChartFeatureInstancesByFeatureId() {
        return chartFeatureInstancesByFeatureId;
    }

    public void setChartFeatureInstancesByFeatureId(Collection<ChartFeatureInstanceEntity> chartFeatureInstancesByFeatureId) {
        this.chartFeatureInstancesByFeatureId = chartFeatureInstancesByFeatureId;
    }

    @OneToMany(mappedBy = "featureByFeatureId")
    public Collection<ChartFeatureMappigEntity> getChartFeatureMappigsByFeatureId() {
        return chartFeatureMappigsByFeatureId;
    }

    public void setChartFeatureMappigsByFeatureId(Collection<ChartFeatureMappigEntity> chartFeatureMappigsByFeatureId) {
        this.chartFeatureMappigsByFeatureId = chartFeatureMappigsByFeatureId;
    }
    */
}
