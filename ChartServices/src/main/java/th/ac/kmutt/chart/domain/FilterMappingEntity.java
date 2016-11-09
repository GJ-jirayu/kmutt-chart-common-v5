package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FILTER_MAPPING", schema = "", catalog = "CHART_DB")
@IdClass(value=FilterMappingPK.class)
public class FilterMappingEntity implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 2089674648953527010L;

	@Id
    @Column(name = "FILTER_ID") 
	private Integer filterId;

   @Id
   @Column(name = "PARAM_FILTER_ID")  
   private Integer paramFilterId;

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
   
}
