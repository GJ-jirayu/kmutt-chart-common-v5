package th.ac.kmutt.chart.domain;

import java.io.Serializable;

public class FilterMappingPK implements Serializable{
    
	private static final long serialVersionUID = 7551040786329571140L;

	private Integer filterId;
    	private Integer paramFilterId;
	  public FilterMappingPK(){
	    }
	public Integer getFilterId() {
		return filterId;
	}
	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}
	public Integer getParamFilterId() {
		return paramFilterId;
	}
	public void setParamFilterId(Integer paramFilterId) {
		this.paramFilterId = paramFilterId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filterId == null) ? 0 : filterId.hashCode());
		result = prime * result
				+ ((paramFilterId == null) ? 0 : paramFilterId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilterMappingPK other = (FilterMappingPK) obj;
		if (filterId == null) {
			if (other.filterId != null)
				return false;
		} else if (!filterId.equals(other.filterId))
			return false;
		if (paramFilterId == null) {
			if (other.paramFilterId != null)
				return false;
		} else if (!paramFilterId.equals(other.paramFilterId))
			return false;
		return true;
	}
	  
}
