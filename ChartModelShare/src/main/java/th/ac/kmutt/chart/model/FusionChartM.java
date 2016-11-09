package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GJ
 */

@XStreamAlias("FusionChartM")
public class FusionChartM extends ImakeXML implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1505773202110049549L;
	private String chartJson;
    private String chartType;
    private String InstanceId;
    private Integer serviceId;  // datasource
    private List<FilterM> filters;
	public String getChartJson() {
		return chartJson;
	}
	public void setChartJson(String chartJson) {
		this.chartJson = chartJson;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getInstanceId() {
		return InstanceId;
	}
	public void setInstanceId(String instanceId) {
		InstanceId = instanceId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public List<FilterM> getFilters() {
		return filters;
	}
	public void setFilters(List<FilterM> filters) {
		this.filters = filters;
	}
}
