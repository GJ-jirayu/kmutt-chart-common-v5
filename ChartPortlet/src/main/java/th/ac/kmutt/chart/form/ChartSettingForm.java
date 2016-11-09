package th.ac.kmutt.chart.form;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imake on 13/09/2015.
 */
public class ChartSettingForm extends CommonForm implements Serializable {
    private String chartType;
    private String dataSource;
    private String jsonStr;
    private String chartInstance;
    private String chartJson;
    private String dataAdhoc;
    private String advProp;
    private String chartHeight="300";
    private String comment;
    private String dataSourceType;
    private String filterRole;
    private String linkTo;
    private String subFromFilter;
    private String showFilter;
    private String chartTitle;
    private String chartSubTitle;
    private String titleFromFilter;
    private String globalFilterString; // manual
    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getChartInstance() {
        return chartInstance;
    }

    public void setChartInstance(String chartInstance) {
        this.chartInstance = chartInstance;
    }

    public String getChartJson() {
        return chartJson;
    }

    public void setChartJson(String chartJson) {
        this.chartJson = chartJson;
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

    public String getChartHeight() {
        return chartHeight;
    }

    public void setChartHeight(String chartHeight) {
        this.chartHeight = chartHeight;
    }

    public String getComment() {
        return comment;
    }
    private List<String> filterValue;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
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

    public String getDataSourceType() {
        return dataSourceType;
    }

    public String getSubFromFilter() {
        return subFromFilter;
    }

    public void setSubFromFilter(String subFromFilter) {
        this.subFromFilter = subFromFilter;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
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

    public String getTitleFromFilter() {
        return titleFromFilter;
    }

    public void setTitleFromFilter(String titleFromFilter) {
        this.titleFromFilter = titleFromFilter;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
	public String getGlobalFilterString() {
		return globalFilterString;
	}
	public void setGlobalFilterString(String globalFilterString) {
		this.globalFilterString = globalFilterString;
	}
}
