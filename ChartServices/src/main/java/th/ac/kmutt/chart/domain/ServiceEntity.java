package th.ac.kmutt.chart.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;


/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "SERVICE", schema = "", catalog = "CHART_DB")
public class ServiceEntity  implements Serializable {
    private Integer serviceId;
    private String serviceName;
    private String endPoint;
    private String modelName;
    private String type;
    private String activeFlag;
    private String sqlString;
    private Integer connId;
    /*
    @OneToMany(targetEntity=ChartInstanceEntity.class, mappedBy="serviceByServiceId")
    private Collection<ChartInstanceEntity> chartInstanceEntitys;
    
    public Collection<ChartInstanceEntity> getChartInstanceEntitys() {
        return chartInstanceEntitys;
    }
    public void setChartInstanceEntitys(Collection<ChartInstanceEntity> chartInstanceEntitys) {
		this.chartInstanceEntitys = chartInstanceEntitys;
	}
    
    private Collection<ChartFilterInstanceEntity> chartFilterInstancesByServiceId;
    private Collection<ChartInstanceEntity> chartInstancesByServiceId;
    private Collection<ServiceChartMappingEntity> serviceChartMappingsByServiceId;
    private Collection<ServiceFilterMappingEntity> serviceFilterMappingsByServiceId;
*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SERVICE_ID")
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "SERVICE_NAME")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Basic
    @Column(name = "END_POINT")
    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Basic
    @Column(name = "MODEL_NAME")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "ACTIVE_FLAG")
    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }
    @Basic
    @Column(name = "SQL_QUERY")
    public String getSqlString() {
		return sqlString;
	}

	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
	}
	  @Basic
	    @Column(name = "CONNECTION_ID")
	public Integer getConnId() {
		return connId;
	}

	public void setConnId(Integer connId) {
		this.connId = connId;
	}

/*
    @OneToMany(mappedBy = "serviceByServiceId")
    public Collection<ChartFilterInstanceEntity> getChartFilterInstancesByServiceId() {
        return chartFilterInstancesByServiceId;
    }

    public void setChartFilterInstancesByServiceId(Collection<ChartFilterInstanceEntity> chartFilterInstancesByServiceId) {
        this.chartFilterInstancesByServiceId = chartFilterInstancesByServiceId;
    }

    @OneToMany(mappedBy = "serviceByServiceId")
    */
/*
    public void setChartInstancesByServiceId(Collection<ChartInstanceEntity> chartInstancesByServiceId) {
        this.chartInstancesByServiceId = chartInstancesByServiceId;
    }

    @OneToMany(mappedBy = "serviceByServiceId")
    public Collection<ServiceChartMappingEntity> getServiceChartMappingsByServiceId() {
        return serviceChartMappingsByServiceId;
    }

    public void setServiceChartMappingsByServiceId(Collection<ServiceChartMappingEntity> serviceChartMappingsByServiceId) {
        this.serviceChartMappingsByServiceId = serviceChartMappingsByServiceId;
    }

    @OneToMany(mappedBy = "serviceByServiceId")
    public Collection<ServiceFilterMappingEntity> getServiceFilterMappingsByServiceId() {
        return serviceFilterMappingsByServiceId;
    }

    public void setServiceFilterMappingsByServiceId(Collection<ServiceFilterMappingEntity> serviceFilterMappingsByServiceId) {
        this.serviceFilterMappingsByServiceId = serviceFilterMappingsByServiceId;
    }
    */
}
