package th.ac.kmutt.chart.portlet;

/**
 * Created by imake on 13/09/2015.
 */

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import th.ac.kmutt.chart.form.ChartSettingForm;
import th.ac.kmutt.chart.model.*;
import th.ac.kmutt.chart.service.ChartService;

import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller("chartCommonSettingController")
@RequestMapping("EDIT")
@SessionAttributes({"chartSettingForm"})
public class ChartCommonSettingController {

    private static final Logger logger = Logger
            .getLogger(ChartCommonSettingController.class);

    @Autowired
    @Qualifier("chartServiceWSImpl")
    private ChartService chartService;
    
    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        //String a[] = new String[]{"ntcfaq.nfaqName"};
        final String[] ALLOWED_FIELDS = {"researchGroupM.researchGroupId", "researchGroupM.createdBy", "researchGroupM.createdDate",
                "researchGroupM.groupCode", "researchGroupM.permissions", "researchGroupM.updatedBy",
                "researchGroupM.updatedDate", "researchGroupM.groupTh", "researchGroupM.groupEng", "mode", "command",
                "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "chartType","jsonStr","chartInstance",
                "chartHeight","chartJson","dataAdhoc","advProp","comment","dataSourceType","aoe_internal","filterRole"
        ,"linkTo","subFromFilter","dataSource","aoe_internal","chartTitle","chartSubTitle","showFilter","titleFromFilter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @SuppressWarnings("unchecked")
	@RequestMapping
    // default (action=list)
    public String showSetting(PortletRequest request, Model model) {
        ChartSettingForm chartSettingForm = null;
        if (!model.containsAttribute("chartSettingForm")) {
            chartSettingForm = new ChartSettingForm();
            model.addAttribute("chartSettingForm",
                    chartSettingForm);
        } else {
            chartSettingForm = (ChartSettingForm) model.asMap().get("chartSettingForm");
        }
        //get liferay interface
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
       // logger.info("themeDisplay->"+themeDisplay);
        String userId = "";
        try {
			User currentUser = PortalUtil.getUser(request);
			userId = String.valueOf(currentUser.getScreenName());
		} catch (PortalException | SystemException e) {
		}catch (Exception e){
		}
        PortletSession portletSession = request.getPortletSession();
        portletSession.setAttribute("userId", userId, PortletSession.APPLICATION_SCOPE);

        List<ServiceM> listServices = null;
        ServiceM  serviceM=new ServiceM();
        serviceM.setType("chart");
        
        String instanceId=themeDisplay.getPortletDisplay().getInstanceId(); 
        ChartInstanceM chartInstanceM=chartService.findChartInstanceById(instanceId);
        List<ChartFilterInstanceM>  chartFilterInstance = new ArrayList<ChartFilterInstanceM>();
        if(chartInstanceM!=null ){
            chartSettingForm.setChartInstance(instanceId);
            chartSettingForm.setAdvProp(chartInstanceM.getAdvProp());
            chartSettingForm.setDataAdhoc(chartInstanceM.getDataAdhoc());
            chartSettingForm.setChartType(chartInstanceM.getChartType());
            chartSettingForm.setChartJson(chartInstanceM.getChartJson());
            chartSettingForm.setJsonStr(chartInstanceM.getChartJson());
            chartSettingForm.setDataSourceType(chartInstanceM.getDataSourceType());
            chartSettingForm.setLinkTo(chartInstanceM.getLinkTo());
            chartSettingForm.setFilterRole(chartInstanceM.getFilterRole());
            chartSettingForm.setSubFromFilter(chartInstanceM.getSubFromFilter());
            chartSettingForm.setTitleFromFilter(chartInstanceM.getTitleFromFilter());
            chartSettingForm.setDataSource(chartInstanceM.getServiceId()+"");
            chartSettingForm.setChartTitle(chartInstanceM.getChartTitle());
            chartSettingForm.setChartSubTitle(chartInstanceM.getChartSubTitle());
            chartSettingForm.setShowFilter(chartInstanceM.getShowFilter());
            if(chartInstanceM.getComment()!=null)
            chartSettingForm.setComment(chartInstanceM.getComment().getComment());
            // chart filter instance
            ChartFilterInstanceM cfi = new ChartFilterInstanceM();
            cfi.setInstanceId(instanceId);
            cfi.setServiceId(chartInstanceM.getServiceId());
            chartFilterInstance = chartService.getChartFilterInstance(cfi);
            
            serviceM.setChartType(chartInstanceM.getChartType());
            serviceM.setUserId(userId);
           listServices=  chartService.listServiceByChart(serviceM);
        }else{

    		listServices=  chartService.listService(serviceM);
        }
        model.addAttribute("chartFilterInstance",chartFilterInstance);
        model.addAttribute("chartSettingForm", chartSettingForm);
        model.addAttribute("serviceList",listServices);      
        ChartM chartM=new ChartM();
        chartM.setActiveFlag("1");
        List<ChartM> chartList= chartService.listChart(chartM);
        model.addAttribute("chartList", chartList);
        
        //model.addAttribute("serviceFilterMappingMList",serviceFilterMappingMList);
        return "chart/settingChart";
    }
    @RequestMapping(params = "action=doSubmit") // action phase
    public void doSubmit(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("chartSettingForm") ChartSettingForm chartSettingForm,
                             BindingResult result, Model model) {
        //logger.info("into do submit instance =>"+chartSettingForm.getChartInstance()+" ,new json "+chartSettingForm.getJsonStr());
        boolean isNew=false;
        ChartInstanceM chartInstanceM=chartService.findChartInstanceById(chartSettingForm.getChartInstance());
        if(chartInstanceM==null) { // check new instance or not
            chartInstanceM = new ChartInstanceM();
            isNew=true;
        }
        chartInstanceM.setInstanceId(chartSettingForm.getChartInstance());
        chartInstanceM.setChartType(chartSettingForm.getChartType());
        chartInstanceM.setDataSourceType(chartSettingForm.getDataSourceType());
        
        if(chartSettingForm.getDataSourceType().equals("1")){ // choose use datasource
            chartInstanceM.setServiceId(Integer.valueOf(chartSettingForm.getDataSource()));
        }
        chartInstanceM.setChartJson(chartSettingForm.getChartJson());
        chartInstanceM.setAdvProp(chartSettingForm.getAdvProp());
        chartInstanceM.setDataAdhoc(chartSettingForm.getDataAdhoc());
        chartInstanceM.setChartHeight(chartSettingForm.getChartHeight());
        chartInstanceM.setLinkTo(chartSettingForm.getLinkTo());
        chartInstanceM.setFilterRole(chartSettingForm.getFilterRole());
        chartInstanceM.setSubFromFilter(chartSettingForm.getSubFromFilter());
        chartInstanceM.setTitleFromFilter(chartSettingForm.getTitleFromFilter());
        chartInstanceM.setShowFilter(chartSettingForm.getShowFilter());
        chartInstanceM.setChartTitle(chartSettingForm.getChartTitle());
        chartInstanceM.setChartSubTitle(chartSettingForm.getChartSubTitle());
        if(chartSettingForm.getComment()!=null){
            CommentM commentM=new CommentM();
            commentM.setInstanceId(chartInstanceM.getInstanceId());
            commentM.setComment(chartSettingForm.getComment());
            chartService.saveComment(commentM);
           // chartInstanceM.setComment(commentM);
        }
        //  insertNew or update ChartInstance
        if(isNew)
            chartService.saveChartInstance(chartInstanceM);
        else
            chartService.updateChartInstance(chartInstanceM);

        // save FilterInstance
        ServiceFilterMappingM sfm = new ServiceFilterMappingM();
        sfm.setServiceId(Integer.valueOf(chartSettingForm.getDataSource()));
        @SuppressWarnings("unchecked")
		List<ServiceFilterMappingM> sfmList = chartService.listServiceFilterMapping(sfm);
        List<FilterM> saveFilterList = new ArrayList<FilterM>();
        if(sfmList!=null){
	        for( ServiceFilterMappingM sfmItem : sfmList){
	        	FilterM newFilter = sfmItem.getFilterM();
	        	String filterActiveFlag = request.getParameter("filter_active_"+newFilter.getFilterId());
	        	String filterSelectValue  = request.getParameter("filter_selection_"+newFilter.getFilterId());
	        	if(filterActiveFlag!=null ){ // insert checked only
	        		if(filterActiveFlag.equals("1")){
	        			newFilter.setActiveFlag(filterActiveFlag);
	    	        	if(filterSelectValue!=null ){ // insert checked only
	    	        		newFilter.setSelectedValue(filterSelectValue);
	    	        	}	// end have value
	    	        	saveFilterList.add(newFilter);
	        		}// end  active = 1
	        	} // end have active
	        } // end serviceMappingList
        }
        FilterInstanceM fim = new FilterInstanceM();
        fim.setInstanceId(chartSettingForm.getChartInstance());
        fim.setFilterList(saveFilterList);
        chartService.saveFilterInstance(fim);
        // end save Filter Instance
        try {
            response.setPortletMode(PortletMode.VIEW);
            response.setRenderParameter("action", "list");
        } catch (PortletModeException e) {
            e.printStackTrace();
        }
    }
    //
    @ResourceMapping(value="loadChartProp")
	@ResponseBody 
	public void loadChartProp(ResourceRequest request,ResourceResponse response) throws IOException{
		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
		HttpServletRequest normalRequest	=	PortalUtil.getOriginalServletRequest(httpReq);
		String chartType = normalRequest.getParameter("chartType");
		com.liferay.portal.kernel.json.JSONObject json = JSONFactoryUtil.createJSONObject();
		com.liferay.portal.kernel.json.JSONObject header = JSONFactoryUtil.createJSONObject();
		
		ChartM param = new ChartM();
		param.setChartType(chartType);
		@SuppressWarnings("unchecked")
		List<ChartM> charts = chartService.listChart(param);
		if( charts!=null && charts.size()>0 ){
			ChartM chart = charts.get(0);
			json.put("content", chart.getChartJson());
			header.put("success","1" );
		}else{

			header.put("success","0" );
		}
		json.put("header",header);
		response.getWriter().write(json.toString());
    }
    @ResourceMapping(value="loadServiceFilter")
   	@ResponseBody 
   	public void loadSeriveFilter(ResourceRequest request,ResourceResponse response) throws IOException{
   		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
   		HttpServletRequest normalRequest	=	PortalUtil.getOriginalServletRequest(httpReq);
   		String serviceId = normalRequest.getParameter("serviceId");
   		com.liferay.portal.kernel.json.JSONObject json = JSONFactoryUtil.createJSONObject();
   		com.liferay.portal.kernel.json.JSONObject header = JSONFactoryUtil.createJSONObject();
   		com.liferay.portal.kernel.json.JSONArray data = JSONFactoryUtil.createJSONArray();
   		header.put("serviceId", serviceId);
   		
   		FilterM filter = new FilterM();
   		String userId = (String)request.getPortletSession().getAttribute("userId", PortletSession.APPLICATION_SCOPE);
   		filter.setUserid(userId);
   		
   		filter.setServiceId(Integer.valueOf(serviceId));
   		List<FilterM> filters = chartService.getFilterService(filter);
   		if( filters!=null && filters.size()>0 ){
   			header.put("success","1" );
   			for( FilterM readFilter : filters){
   				com.liferay.portal.kernel.json.JSONObject row = JSONFactoryUtil.createJSONObject();
   				row.put("id", readFilter.getFilterId() );
   				row.put("name", readFilter.getTitle());
   				row.put("param", readFilter.getFilterName() );
   				com.liferay.portal.kernel.json.JSONArray items = JSONFactoryUtil.createJSONArray();
   				if(readFilter.getFilterValues() !=null){
	   				for( FilterValueM fv : readFilter.getFilterValues() ){
	   					com.liferay.portal.kernel.json.JSONObject item = JSONFactoryUtil.createJSONObject();
	   					item.put("value", fv.getKeyMapping());
	   					item.put("display",fv.getValueMapping() );
	   					items.put(item);
	   				}
   				}
   				row.put("item", items);
   	   			data.put(row);
   			}
   		}else{
   			header.put("success","0" );
   		}
   		json.put("header",header);
   		json.put("data",data);
   		response.getWriter().write(json.toString());
       }
      //
    
    @ResourceMapping(value="regetDatasource")
	@ResponseBody 
	public void regetDatasource(ResourceRequest request,ResourceResponse response) throws IOException{
		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
		HttpServletRequest normalRequest	=	PortalUtil.getOriginalServletRequest(httpReq);
		String chartType = normalRequest.getParameter("chartType");
		com.liferay.portal.kernel.json.JSONObject json = JSONFactoryUtil.createJSONObject();
		com.liferay.portal.kernel.json.JSONObject header = JSONFactoryUtil.createJSONObject();
		com.liferay.portal.kernel.json.JSONArray datalist = JSONFactoryUtil.createJSONArray();
		
		try{
			  String userId = "";
		        
			  User currentUser = PortalUtil.getUser(request);
			  userId = String.valueOf(currentUser.getScreenName());
			

			ServiceM param = new ServiceM();
			param.setChartType(chartType);
			param.setUserId(userId);
			@SuppressWarnings("unchecked")
			List<ServiceM> services = chartService.listServiceByChart(param);
			
			for(ServiceM s  : services){
				com.liferay.portal.kernel.json.JSONObject data = JSONFactoryUtil.createJSONObject();
				data.put("desc", s.getDatasourceName());
				data.put("value", s.getServiceId());
				datalist.put(data);
			}
			com.liferay.portal.kernel.json.JSONObject data = JSONFactoryUtil.createJSONObject();
			data.put("data",datalist);
			json.put("content", data);
			header.put("success","1" );
		}catch (PortalException | SystemException e) {
			
		}	catch(Exception e){
				header.put("success","0" );
		}
		json.put("header",header);
		response.getWriter().write(json.toString());
    }
}
