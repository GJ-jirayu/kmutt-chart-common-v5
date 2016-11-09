package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("InBoundOutBoundServiceM")
public class FilterInBoundOutBoundServiceM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private String filterCode;
    private String filterName;

    public FilterInBoundOutBoundServiceM (){
    	
    }
    
	public String getFilterCode() {
		return filterCode;
	}
	public void setFilterCode(String filterCode) {
		this.filterCode = filterCode;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	


}
