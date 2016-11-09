package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("FilterInstanceM")
public class FilterInstanceM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private String instanceId;
    private Integer filterId;
    private String value;
    private FilterM filterM;
    private List<FilterM> filterList;
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }
    public String getValue() {
        return value;
    }
    public FilterM getFilterM() {
        return filterM;
    }
    public void setFilterM(FilterM filterM) {
        this.filterM = filterM;
    }
    public void setValue(String value) {
        this.value = value;
    }
	public List<FilterM> getFilterList() {
		return filterList;
	}
	public void setFilterList(List<FilterM> filterList) {
		this.filterList = filterList;
	}
}
