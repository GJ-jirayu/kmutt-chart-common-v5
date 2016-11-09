package th.ac.kmutt.chart.form;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import th.ac.kmutt.chart.model.ChartM;
import th.ac.kmutt.chart.model.ConnectionM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.ServiceM;
import th.ac.kmutt.chart.model.UserM;

public class ChartDatasourceForm extends CommonForm implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8926189943620440470L;
	private String datasourceName;
	private List<ServiceM> datasources;
	private Integer datasourceId;
    private String sqlString;
    private Set<FilterM> filterList;
    private Set<FilterM> filterUsedList;
	private Set<FilterM> initialFilterList;

    private Set<ChartM> chartList;
    private Set<ChartM> chartUsedList;
	private Set<ChartM> chartInitList;
    private Set<UserM> userList;
    private Set<UserM> userUsedList;
	private Set<UserM> userInitList;
	
	private Set<ConnectionM> connections;
	private Integer selectedConnId;
	
    private String sqlMessage;
    
	public String getDatasourceName() {
		return datasourceName;
	}
	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}
	public List<ServiceM> getDatasources() {
		return datasources;
	}
	public void setDatasources(List<ServiceM> datasources) {
		this.datasources = datasources;
	}
	public String getSqlString() {
		return sqlString;
	}
	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
	}
	public Set<FilterM> getFilterList() {
		return filterList;
	}
	public void setFilterList(Set<FilterM> filterList) {
		this.filterList = filterList;
	}
	public String getSqlMessage() {
		return sqlMessage;
	}
	public void setSqlMessage(String sqlMessage) {
		this.sqlMessage = sqlMessage;
	}
	public Integer getDatasourceId() {
		return datasourceId;
	}
	public void setDatasourceId(Integer datasourceId) {
		this.datasourceId = datasourceId;
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
	public Set<ChartM> getChartList() {
		return chartList;
	}
	public void setChartList(Set<ChartM> chartList) {
		this.chartList = chartList;
	}
	public Set<ChartM> getChartUsedList() {
		return chartUsedList;
	}
	public void setChartUsedList(Set<ChartM> chartUsedList) {
		this.chartUsedList = chartUsedList;
	}
	public Set<ChartM> getChartInitList() {
		return chartInitList;
	}
	public void setChartInitList(Set<ChartM> chartInitialList) {
		this.chartInitList = chartInitialList;
	}
	public Set<UserM> getUserList() {
		return userList;
	}
	public void setUserList(Set<UserM> userList) {
		this.userList = userList;
	}
	public Set<UserM> getUserUsedList() {
		return userUsedList;
	}
	public void setUserUsedList(Set<UserM> userUsedList) {
		this.userUsedList = userUsedList;
	}
	public Set<UserM> getUserInitList() {
		return userInitList;
	}
	public void setUserInitList(Set<UserM> userInitList) {
		this.userInitList = userInitList;
	}
	public Set<ConnectionM> getConnections() {
		return connections;
	}
	public void setConnections(Set<ConnectionM> connections) {
		this.connections = connections;
	}
	public Integer getSelectedConnId() {
		return selectedConnId;
	}
	public void setSelectedConnId(Integer selectedConnId) {
		this.selectedConnId = selectedConnId;
	}
	
}
