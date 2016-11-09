package th.ac.kmutt.chart.model;

import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FilterValueM extends ImakeXML implements Serializable {
    private String keyMapping;
    private String valueMapping;
    private Map<String,String> values;
    private List<String> valueList;
    private String type;
    private Integer refFilterId;
    
	public String getKeyMapping() {
		return keyMapping;
	}
	public void setKeyMapping(String keyMapping) {
		this.keyMapping = keyMapping;
	}
	public String getValueMapping() {
		return valueMapping;
	}
	public void setValueMapping(String valueMapping) {
		this.valueMapping = valueMapping;
	}
	public Map<String, String> getValues() {
		return values;
	}
	public void setValues(Map<String, String> values) {
		this.values = values;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getRefFilterId() {
		return refFilterId;
	}
	public void setRefFilterId(Integer refFilterId) {
		this.refFilterId = refFilterId;
	}
	public List<String> getValueList() {
		return valueList;
	}
	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}
}
