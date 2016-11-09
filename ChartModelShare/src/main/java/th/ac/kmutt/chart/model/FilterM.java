package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;
import java.util.List;

@XStreamAlias("FilterM")
public class FilterM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer filterId;
    private String filterName;
    private String valueType;
    private String dataType;
    private String title;
    private String sqlQuery;
    private String sqlFlag;
    private String globalFlag;
    private String activeFlag;
    private String systemFlag;
    private String autoFill;
    private String defaultValue;
    private Integer connId;
    private List<FilterM> filterList;
    private List<FilterValueM> filterValues;
    private String selectedValue;
    private Integer serviceId; //ref
    
	public Integer getFilterId() {
		return filterId;
	}
	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSqlQuery() {
		return sqlQuery;
	}
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	public String getSqlFlag() {
		return sqlFlag;
	}
	public void setSqlFlag(String sqlFlag) {
		this.sqlFlag = sqlFlag;
	}
	public String getGlobalFlag() {
		return globalFlag;
	}
	public void setGlobalFlag(String globalFlag) {
		this.globalFlag = globalFlag;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Integer getConnId() {
		return connId;
	}
	public void setConnId(Integer connId) {
		this.connId = connId;
	}
	public List<FilterM> getFilterList() {
		return filterList;
	}
	public void setFilterList(List<FilterM> filterList) {
		this.filterList = filterList;
	}
	public List<FilterValueM> getFilterValues() {
		return filterValues;
	}
	public void setFilterValues(List<FilterValueM> filterValues) {
		this.filterValues = filterValues;
	}
	public String getSelectedValue() {
		return selectedValue;
	}
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getSystemFlag() {
		return systemFlag;
	}
	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getAutoFill() {
		return autoFill;
	}
	public void setAutoFill(String autoFill) {
		this.autoFill = autoFill;
	}
}
