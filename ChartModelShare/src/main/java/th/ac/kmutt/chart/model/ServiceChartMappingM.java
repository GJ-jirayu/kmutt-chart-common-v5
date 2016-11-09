package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("ServiceChartMappingM")
public class ServiceChartMappingM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer serviceId;
    private Integer chartId;
    private ChartM chart;
    private ServiceM service;
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getChartId() {
        return chartId;
    }

    public ChartM getChart() {
        return chart;
    }

    public void setChart(ChartM chart) {
        this.chart = chart;
    }

    public ServiceM getService() {
        return service;
    }

    public void setService(ServiceM service) {
        this.service = service;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }
}
