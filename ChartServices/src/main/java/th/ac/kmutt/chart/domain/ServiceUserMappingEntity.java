package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SERVICE_USER_MAPPING", schema = "", catalog = "CHART_DB")
//@IdClass(ServiceFilterMappingEntityPK.class)
public class ServiceUserMappingEntity implements Serializable {
    /*
	private Integer serviceId;
    private Integer filterId;
    
   @ManyToOne
   @JoinColumn(name = "FILTER_ID", referencedColumnName = "FILTER_ID", nullable = false ,insertable = false,updatable = false)
    private FilterEntity filterByFilterId;

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID", nullable = false,insertable = false,updatable = false)
    private ServiceEntity serviceByServiceId;*/
    @EmbeddedId
    private ServiceUserMappingEntityPK id;
   /*
    @Id
    @Column(name = "SERVICE_ID")
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Id
    @Column(name = "FILTER_ID")
    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }
*/

/*

    public FilterEntity getFilterByFilterId() {
        return filterByFilterId;
    }

    public void setFilterByFilterId(FilterEntity filterByFilterId) {
        this.filterByFilterId = filterByFilterId;
    }


    public ServiceEntity getServiceByServiceId() {
        return serviceByServiceId;
    }

    public void setServiceByServiceId(ServiceEntity serviceByServiceId) {
        this.serviceByServiceId = serviceByServiceId;
    }
*/
    public ServiceUserMappingEntityPK getId() {
        return id;
    }

    public void setId(ServiceUserMappingEntityPK id) {
        this.id = id;
    }
}
