package th.ac.kmutt.chart.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "FILTER_VALUE", schema = "", catalog = "CHART_DB")
public class FilterValueEntity  implements Serializable {
    private Integer filterValueId;
    private Integer filterId;
    private String value;
    private String description;
    private FilterEntity filterEntity;
    
    @Id
    @Column(name = "FILTER_VALUE_ID")
	public Integer getFilterValueId() {
		return filterValueId;
	}
	public void setFilterValueId(Integer filterValueId) {
		this.filterValueId = filterValueId;
	}
	 @Basic
	    @Column(name = "FILTER_NAME")
	public Integer getFilterId() {
		return filterId;
	}
	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}
	 @Basic
	    @Column(name = "VALUE_KEY")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	 @Basic
	   @Column(name = "DESCRPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public FilterEntity getFilterEntity() {
		return filterEntity;
	}
	public void setFilterEntity(FilterEntity filterEntity) {
		this.filterEntity = filterEntity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((filterId == null) ? 0 : filterId.hashCode());
		result = prime * result
				+ ((filterValueId == null) ? 0 : filterValueId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		FilterValueEntity other = (FilterValueEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (filterId == null) {
			if (other.filterId != null)
				return false;
		} else if (!filterId.equals(other.filterId))
			return false;
		if (filterValueId == null) {
			if (other.filterValueId != null)
				return false;
		} else if (!filterValueId.equals(other.filterValueId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
    

}
