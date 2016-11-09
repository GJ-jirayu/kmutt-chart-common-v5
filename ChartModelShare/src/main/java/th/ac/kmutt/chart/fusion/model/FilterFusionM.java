package th.ac.kmutt.chart.fusion.model;

import java.io.Serializable;
import java.util.List;

import th.ac.kmutt.chart.model.FilterM;

/**
 * Created by imake on 27/10/2015.
 */
public class FilterFusionM  implements Serializable {
    private String[] filters; // key_value
    private List<FilterM> filterMList;
    public String[] getFilters() {
        return filters;
    }

    public void setFilters(String[] filters) {
        this.filters = filters;
    }

	public List<FilterM> getFilterMList() {
		return filterMList;
	}

	public void setFilterMList(List<FilterM> filterMList) {
		this.filterMList = filterMList;
	}
}
