package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("ChartInstanceM")
public class ChartInstanceM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private String instanceId;
    private Integer chartId;
    private Integer serviceId;
    private String chartWidth;
    private String chartHeight;
    private String chartJson;
    private String dataAdhoc;
    private String advProp;
    private String chartType;
    private String dataSourceType;
    private String filterRole;
    private String linkTo;
    private String subFromFilter;
    private String showFilter;
    private String chartTitle;
    private String chartSubTitle;
    private String titleFromFilter;
    private ChartM chart;
    private ServiceM service;
    private CommentM comment;
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public String getChartWidth() {
        return chartWidth;
    }

    public void setChartWidth(String chartWidth) {
        this.chartWidth = chartWidth;
    }

    public String getChartHeight() {
        return chartHeight;
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

    public CommentM getComment() {
        return comment;
    }

    public void setComment(CommentM comment) {
        this.comment = comment;
    }

    public String getChartJson() {
        return chartJson;
    }

    public String getDataAdhoc() {
        return dataAdhoc;
    }

    public void setDataAdhoc(String dataAdhoc) {
        this.dataAdhoc = dataAdhoc;
    }

    public String getAdvProp() {
        return advProp;
    }

    public void setAdvProp(String advProp) {
        this.advProp = advProp;
    }

    public void setChartJson(String chartJson) {
        this.chartJson = chartJson;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public void setChartHeight(String chartHeight) {
        this.chartHeight = chartHeight;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public String getFilterRole() {
        return filterRole;
    }

    public String getSubFromFilter() {
        return subFromFilter;
    }

    public void setSubFromFilter(String subFromFilter) {
        this.subFromFilter = subFromFilter;
    }

    public void setFilterRole(String filterRole) {
        this.filterRole = filterRole;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }

    public String getChartSubTitle() {
        return chartSubTitle;
    }

    public void setChartSubTitle(String chartSubTitle) {
        this.chartSubTitle = chartSubTitle;
    }

    public String getShowFilter() {
        return showFilter;
    }

    public String getTitleFromFilter() {
        return titleFromFilter;
    }

    public void setTitleFromFilter(String titleFromFilter) {
        this.titleFromFilter = titleFromFilter;
    }

    public void setShowFilter(String showFilter) {
        this.showFilter = showFilter;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
