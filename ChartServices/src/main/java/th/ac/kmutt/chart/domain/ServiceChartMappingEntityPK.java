package th.ac.kmutt.chart.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */

@Embeddable
public class ServiceChartMappingEntityPK implements Serializable {
    @Column(name = "SERVICE_ID",insertable = false,updatable = false)
    private Integer serviceId;

    @Column(name = "CHART_ID",insertable = false,updatable = false)
    private Integer chartId;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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

        ServiceChartMappingEntityPK that = (ServiceChartMappingEntityPK) o;

        if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) return false;
        if (chartId != null ? !chartId.equals(that.chartId) : that.chartId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (chartId != null ? chartId.hashCode() : 0);
        return result;
    }
}
