package th.ac.kmutt.chart.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */

@Embeddable
public class ServiceFilterMappingEntityPK implements Serializable {
    @Column(name = "SERVICE_ID")
    private Integer serviceId;

    @Column(name = "FILTER_ID")
    private Integer filterId;


    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }


    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceFilterMappingEntityPK that = (ServiceFilterMappingEntityPK) o;

        if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) return false;
        if (filterId != null ? !filterId.equals(that.filterId) : that.filterId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (filterId != null ? filterId.hashCode() : 0);
        return result;
    }
}
