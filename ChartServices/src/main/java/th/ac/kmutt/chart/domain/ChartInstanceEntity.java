package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "CHART_INSTANCE", schema = "", catalog = "CHART_DB")
public class ChartInstanceEntity  implements Serializable {
    @Id
    @Column(name = "INSTANCE_ID")
    private String instanceId;

    @Basic
    @Column(name = "CHART_ID",insertable = false,updatable = false)
    private Integer chartId;

    @Basic
    @Column(name = "SERVICE_ID",insertable = true,updatable = true)
    private Integer serviceId;

    @Basic
    @Column(name = "CHART_TITLE")
    private String chartTitle;
    @Basic
    @Column(name = "CHART_SUB_TITLE")
    private String chartSubTitle;
    @Basic
    @Column(name = "CHART_WIDTH")
    private String chartWidth;
    @Basic
    @Column(name = "CHART_HEIGHT")
    private String chartHeight;
    @Basic
    @Column(name = "CHART_JSON")
    private String chartJson;

    @Basic
    @Column(name = "DATA_AD_HOC")
    private String dataAdhoc;

    @Column(name = "ADV_PROP")
    private String advProp;

    @Basic
    @Column(name = "CHART_TYPE")
    private String chartType;

    @Basic
    @Column(name = "DATASOURCE_TYPE")
    private String dataSourceType;

    @Basic
    @Column(name = "FILTER_ROLE")
    private String filterRole;

    @Basic
    @Column(name = "LINK_TO")
    private String linkTo;
    @Basic
    @Column(name = "SUB_FROM_FILTER")
    private String subFromFilter;

    @Basic
    @Column(name = "SHOW_FILTER")
    private String showFilter;

    @Basic
    @Column(name = "TITLE_FROM_FILTER")
    private String titleFromFilter;
    @ManyToOne
    @JoinColumn(name = "CHART_ID", referencedColumnName = "CHART_ID")
    private ChartEntity chartByChartId;
/*
    @ManyToOne
    @JoinColumn(name = "SERVICE_ID")
    private ServiceEntity serviceByServiceId;

    @OneToOne(mappedBy = "chartInstanceByInstanceId")
    private CommentEntity commentByInstanceId; */


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

    public void setChartHeight(String chartHeight) {
        this.chartHeight = chartHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartInstanceEntity that = (ChartInstanceEntity) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (chartId != null ? !chartId.equals(that.chartId) : that.chartId != null) return false;
        if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) return false;
        if (chartTitle != null ? !chartTitle.equals(that.chartTitle) : that.chartTitle != null) return false;
        if (chartWidth != null ? !chartWidth.equals(that.chartWidth) : that.chartWidth != null) return false;
        if (chartHeight != null ? !chartHeight.equals(that.chartHeight) : that.chartHeight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (chartId != null ? chartId.hashCode() : 0);
        result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
        result = 31 * result + (chartTitle != null ? chartTitle.hashCode() : 0);
        result = 31 * result + (chartWidth != null ? chartWidth.hashCode() : 0);
        result = 31 * result + (chartHeight != null ? chartHeight.hashCode() : 0);
        return result;
    }


    public ChartEntity getChartByChartId() {
        return chartByChartId;
    }

    public void setChartByChartId(ChartEntity chartByChartId) {
        this.chartByChartId = chartByChartId;
    }

/*
    public ServiceEntity getServiceByServiceId() {
        return serviceByServiceId;
    }

    public void setServiceByServiceId(ServiceEntity serviceByServiceId) {
        this.serviceByServiceId = serviceByServiceId;
    }

    public CommentEntity getCommentByInstanceId() {
        return commentByInstanceId;
    }

    public void setCommentByInstanceId(CommentEntity commentByInstanceId) {
        this.commentByInstanceId = commentByInstanceId;
    }*/

    public String getChartJson() {
        return chartJson;
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

    public String getFilterRole() {
        return filterRole;
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

    public String getSubFromFilter() {
        return subFromFilter;
    }

    public void setSubFromFilter(String subFromFilter) {
        this.subFromFilter = subFromFilter;
    }

    public String getTitleFromFilter() {
        return titleFromFilter;
    }

    public void setTitleFromFilter(String titleFromFilter) {
        this.titleFromFilter = titleFromFilter;
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

    public String getDataSourceType() {
        return dataSourceType;
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

    public void setShowFilter(String showFilter) {
        this.showFilter = showFilter;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
