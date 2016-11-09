package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("ChartFilterInstanceM")
public class ChartFilterInstanceM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private String instanceId;
    private Integer serviceId;
    private String value;
    private Integer filterId;
    private FilterM filterM;
    private ServiceM service;
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getFilterId() {
        return filterId;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }
}
