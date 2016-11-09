package th.ac.kmutt.chart.form;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import th.ac.kmutt.chart.model.ConnectionM;
import th.ac.kmutt.chart.model.FilterM;

public class FilterForm extends CommonForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8940426018312326000L;
	private String filterName;
	private String label;
	private List<FilterM> filters;
	private Integer filterId;
	private Set<String> types;
	private String type;
	private boolean global;
	private boolean system;
	private boolean autoFill;
    private String sqlString;
    private String defaultValue;
    private Set<FilterM> filterList;
    private Set<FilterM> filterUsedList;
	private Set<FilterM> initialFilterList;
    private String sqlMessage;
    private Set<ConnectionM> connections;
    private Integer selectedConnection;
    
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public List<FilterM> getFilters() {
		return filters;
	}
	public void setFilters(List<FilterM> filters) {
		this.filters = filters;
	}
	public Integer getFilterId() {
		return filterId;
	}
	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}
	public Set<String> getTypes() {
		return types;
	}
	public void setTypes(Set<String> types) {
		this.types = types;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isGlobal() {
		return global;
	}
	public void setGlobal(boolean global) {
		this.global = global;
	}
	public boolean isSystem() {
		return system;
	}
	public void setSystem(boolean system) {
		this.system = system;
	}
	public String getSqlString() {
		return sqlString;
	}
	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public Set<FilterM> getFilterList() {
		return filterList;
	}
	public void setFilterList(Set<FilterM> filterList) {
		this.filterList = filterList;
	}
	public Set<FilterM> getFilterUsedList() {
		return filterUsedList;
	}
	public void setFilterUsedList(Set<FilterM> filterUsedList) {
		this.filterUsedList = filterUsedList;
	}
	public Set<FilterM> getInitialFilterList() {
		return initialFilterList;
	}
	public void setInitialFilterList(Set<FilterM> initialFilterList) {
		this.initialFilterList = initialFilterList;
	}
	public String getSqlMessage() {
		return sqlMessage;
	}
	public void setSqlMessage(String sqlMessage) {
		this.sqlMessage = sqlMessage;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Set<ConnectionM> getConnections() {
		return connections;
	}
	public void setConnections(Set<ConnectionM> connections) {
		this.connections = connections;
	}
	public Integer getSelectedConnection() {
		return selectedConnection;
	}
	public void setSelectedConnection(Integer selectedConnection) {
		this.selectedConnection = selectedConnection;
	}
	public boolean isAutoFill() {
		return autoFill;
	}
	public void setAutoFill(boolean autoFill) {
		this.autoFill = autoFill;
	}
	
}
