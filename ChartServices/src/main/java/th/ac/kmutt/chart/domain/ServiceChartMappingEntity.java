package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "SERVICE_CHART_MAPPING", schema = "", catalog = "CHART_DB")
// @IdClass(ServiceChartMappingEntityPK.class)
public class ServiceChartMappingEntity implements Serializable

    {
   /* private Integer serviceId;
    private Integer chartId;
    */
//    private ChartEntity chartByChartId;
 //   private ServiceEntity serviceByServiceId;
    @EmbeddedId
    private ServiceChartMappingEntityPK id;
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
    @Column(name = "CHART_ID")
    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }
*/

/*
    @ManyToOne
    @JoinColumn(name = "CHART_ID", referencedColumnName = "CHART_ID", nullable = false)
    public ChartEntity getChartByChartId() {
        return chartByChartId;
    }

    public void setChartByChartId(ChartEntity chartByChartId) {
        this.chartByChartId = chartByChartId;
    }

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID", nullable = false)
    public ServiceEntity getServiceByServiceId() {
        return serviceByServiceId;
    }

    public void setServiceByServiceId(ServiceEntity serviceByServiceId) {
        this.serviceByServiceId = serviceByServiceId;
    }
*/
    public ServiceChartMappingEntityPK getId() {
        return id;
    }

    public void setId(ServiceChartMappingEntityPK id) {
        this.id = id;
    }
}