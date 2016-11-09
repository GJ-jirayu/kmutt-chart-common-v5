package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "CHART_FILTER_INSTANCE", schema = "", catalog = "CHART_DB")
public class ChartFilterInstanceEntity  implements Serializable {

    @EmbeddedId
    private ChartFilterInstanceEntityPK id;
    @Basic
    @Column(name = "SERVICE_ID")
    private Integer serviceId;

    @Basic
    @Column(name = "VALUE")
    private String value;

    @ManyToOne
    @JoinColumn(name = "FILTER_ID", referencedColumnName = "FILTER_ID",insertable = false,updatable = false)
    private FilterEntity filterByFilterId;

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID",insertable = false,updatable = false)
    private ServiceEntity serviceByServiceId;



    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartFilterInstanceEntity that = (ChartFilterInstanceEntity) o;

        if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) return false;

        return true;
    }
*/

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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

    public ChartFilterInstanceEntityPK getId() {
        return id;
    }

    public void setId(ChartFilterInstanceEntityPK id) {
        this.id = id;
    }
}
