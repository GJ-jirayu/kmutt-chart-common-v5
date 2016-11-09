package th.ac.kmutt.chart.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "FILTER", schema = "", catalog = "CHART_DB")
public class FilterEntity  implements Serializable {
    private Integer filterId;
    private String filterName;
    private String title;
    private String valueType;
    private String dataType;
    private String defaultValue;
    private String sqlQuery;
    private String sqlFlag;
    private String globalFlag;
    private String activeFlag;
    private Integer connId;
    private String systemFlag;
    private String autoFill;
    
    /*
    private Collection<ChartFilterInstanceEntity> chartFilterInstancesByFilterId;
    private Collection<FilterInstanceEntity> filterInstancesByFilterId;
    private Collection<ServiceFilterMappingEntity> serviceFilterMappingsByFilterId;
    private Collection<FilterValueEntity> filterValueEntity;*/
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "FILTER_ID")
    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    @Basic
    @Column(name = "FILTER_NAME")
    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "VALUE_TYPE")
    public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	 @Basic
    @Column(name = "DATA_TYPE")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	 @Basic
    @Column(name = "SUBSTITUTE_DEFAULT")
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	 @Basic
    @Column(name = "SQL_QUERY")
	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	 @Basic
    @Column(name = "SQL_FLAG")
	public String getSqlFlag() {
		return sqlFlag;
	}

	public void setSqlFlag(String sqlFlag) {
		this.sqlFlag = sqlFlag;
	}

	 @Basic
    @Column(name = "GLOBAL_FLAG")
	public String getGlobalFlag() {
		return globalFlag;
	}

	public void setGlobalFlag(String globalFlag) {
		this.globalFlag = globalFlag;
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
    @Column(name = "CONNECTION_ID")
	public Integer getConnId() {
		return connId;
	}

	public void setConnId(Integer connId) {
		this.connId = connId;
	}

	 @Basic
	    @Column(name = "SYSTEM_FLAG")
    public String getSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	@Basic
    @Column(name = "AUTO_FILL_FLAG")
	public String getAutoFill() {
		return autoFill;
	}

	public void setAutoFill(String autoFill) {
		this.autoFill = autoFill;
	}
}
