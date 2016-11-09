package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("ServiceFilterMappingM")
public class ServiceFilterMappingM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer serviceId;
    private Integer filterId;
    private FilterM filterM;
    private ServiceM service;
    public Integer getServiceId() {
        return serviceId;
    }

    public FilterM getFilterM() {
        return filterM;
    }
    
    public void setFilterM(FilterM filterM) {
        this.filterM = filterM;
    }

    public ServiceM getService() {
        return service;
    }

    public void setService(ServiceM service) {
        this.service = service;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }
}
