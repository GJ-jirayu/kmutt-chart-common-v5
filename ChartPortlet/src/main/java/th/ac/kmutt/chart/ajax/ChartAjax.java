package th.ac.kmutt.chart.ajax;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import th.ac.kmutt.chart.model.ChartM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.FilterValueM;
import th.ac.kmutt.chart.model.ServiceFilterMappingM;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChartAjax {
    /*
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("config");
    }
*/
    //private String ATTACH_PATH=bundle.getString("downloadPath");
    // 'เลือกตามปี'
    private static final String[] YEAR_FILTER_KEY={"2010","2011","2012","2013","2014","2015"};
    private static final String[] YEAR_FILTER_VALUE={"2010","2011","2012","2013","2014","2015"};

    // ''เลือกตามเดือน''
    private static final String[] MONTH_FILTER_KEY={"1","2","3","4","5","6","7","8","9","10","11","12"};
    private static final String[] MONTH_FILTER_VALUE={"ม.ค.","ก.พ.","มี.ค.","เม.ย.","พ.ค.","มิ.ย.","ก.ค.","ส.ค.","ก.ย.","ต.ค.","พ.ย.","ธ.ค."};

    //'แหล่งที่ได้รับการเผยแพร่'
    private static final String[] PUBLISH_RESOURCE_FILTER_KEY={"1","2","3","4"};
    private static final String[] PUBLISH_RESOURCE_FILTER_VALUE={"วารสารนานาชาติ","ประชุมนานาชาติ","วารสารในประเทศ","ประชุมระดับประเทศ"};

    //'แหล่งเงินทุน'
    private static final String[] FUNDING_RESOURCE_FILTER_KEY={"1","2","3"};
    private static final String[] FUNDING_RESOURCE_FILTER_VALUE={"เงินรายได้ มจธ.","รัฐ ว.1","แหล่งทุนภายนอก"};
    private ChartService chartService;

    public ChartAjax() {
        WebContext ctx = WebContextFactory.get();
        ServletContext servletContext = ctx.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        chartService = (ChartService) wac.getBean("chartServiceWSImpl");
    }
    public ChartM findChartById(Integer chartId){
        return  chartService.findChartById(chartId);
    }
    public List<ChartM> listChart(ChartM chartM){
        return  chartService.listChart(chartM);
    }
    public List listServiceFilterMapping(th.ac.kmutt.chart.model.ServiceFilterMappingM param){
        List<ServiceFilterMappingM> serviceFilterMappingMList=chartService.listServiceFilterMapping(param);
        if(serviceFilterMappingMList!=null && serviceFilterMappingMList.size()>0){
            for (int i=0;i<serviceFilterMappingMList.size();i++){
                FilterM filterM=serviceFilterMappingMList.get(i).getFilterM();
                Integer filterId=filterM.getFilterId();
                List<FilterValueM> filterValues=null;
                if(filterId.intValue()==1){//เลือกตามปี
                    filterValues=new ArrayList<FilterValueM>(YEAR_FILTER_KEY.length);
                    for (int j=0;j<YEAR_FILTER_KEY.length;j++){
                        FilterValueM filterValueM=new FilterValueM();
                        filterValueM.setKeyMapping(YEAR_FILTER_KEY[j]);
                        filterValueM.setValueMapping(YEAR_FILTER_VALUE[j]);
                        filterValues.add(filterValueM);
                    }

                }else if(filterId.intValue()==2){//แหล่งที่ได้รับการเผยแพร่
                    filterValues=new ArrayList<FilterValueM>(PUBLISH_RESOURCE_FILTER_KEY.length);
                    for (int j=0;j<PUBLISH_RESOURCE_FILTER_KEY.length;j++){
                        FilterValueM filterValueM=new FilterValueM();
                        filterValueM.setKeyMapping(PUBLISH_RESOURCE_FILTER_KEY[j]);
                        filterValueM.setValueMapping(PUBLISH_RESOURCE_FILTER_VALUE[j]);
                        filterValues.add(filterValueM);
                    }
                }
                else if(filterId.intValue()==3){//แหล่งเงินทุน
                    filterValues=new ArrayList<FilterValueM>(FUNDING_RESOURCE_FILTER_KEY.length);
                    for (int j=0;j<FUNDING_RESOURCE_FILTER_KEY.length;j++){
                        FilterValueM filterValueM=new FilterValueM();
                        filterValueM.setKeyMapping(FUNDING_RESOURCE_FILTER_KEY[j]);
                        filterValueM.setValueMapping(FUNDING_RESOURCE_FILTER_VALUE[j]);
                        filterValues.add(filterValueM);
                    }
                }
                else if(filterId.intValue()==4){//เลือกตามเดือน
                    filterValues=new ArrayList<FilterValueM>(MONTH_FILTER_KEY.length);
                    for (int j=0;j<MONTH_FILTER_KEY.length;j++){
                        FilterValueM filterValueM=new FilterValueM();
                        filterValueM.setKeyMapping(MONTH_FILTER_KEY[j]);
                        filterValueM.setValueMapping(MONTH_FILTER_VALUE[j]);
                        filterValues.add(filterValueM);
                    }
                }
                filterM.setFilterValues(filterValues);
            }
        }
        return serviceFilterMappingMList;
    }
    public List testCall(FilterM filterM) {
    	/*TitleM titleM =new TitleM();
    	titleM.setKeySearch(queyr);*/
        return chartService.listFilter(filterM);
		/*List<FundingResourceTypeM> fundingResourceTypes=researchService.searchFundingResourceTypeM(fundingResourceTypeM);
		return fundingResourceTypes;*/
    }
}
