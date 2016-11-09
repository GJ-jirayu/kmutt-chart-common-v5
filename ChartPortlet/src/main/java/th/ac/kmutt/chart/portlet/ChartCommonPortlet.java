package th.ac.kmutt.chart.portlet;

import com.google.gson.Gson;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.EventMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import th.ac.kmutt.chart.form.ChartSettingForm;
import th.ac.kmutt.chart.model.*;
import th.ac.kmutt.chart.service.ChartService;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Controller("chartCommonController")
@RequestMapping("VIEW")
@SessionAttributes({"chartSettingForm"})
public class ChartCommonPortlet {

    /*private static final Logger logger = LogManager
            .getLogger(ChartCommonPortlet.class);
            */
    private static final Logger logger = Logger
            .getLogger(ChartCommonPortlet.class);

    @Autowired
    @Qualifier("chartServiceWSImpl")
    private ChartService chartService;

   /* @Autowired
    @Qualifier("mscolumn2d")
    private MultiSeriesColumn2D mscolumn2d;*/
    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

        final String[] ALLOWED_FIELDS = {"researchGroupM.researchGroupId", "researchGroupM.createdBy", "researchGroupM.createdDate",
                "researchGroupM.groupCode", "researchGroupM.permissions", "researchGroupM.updatedBy",
                "researchGroupM.updatedDate", "researchGroupM.groupTh", "researchGroupM.groupEng", "mode", "command",
                "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter","chartType","jsonStr",
                "chartInstance","chartJson","dataAdhoc","advProp","chartHeight","comment","dataSourceType","aoe_internal","filterRole"
        ,"linkTo","subFromFilter","dataSource","aoe_internal","chartTitle","chartSubTitle","showFilter","titleFromFilter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }
   /* @RequestMapping("VIEW")
    public void iniPage(PortletRequest request, Model model) {
    	logger.info("chart=>default");
    }*/
    @RequestMapping("VIEW")
    @RenderMapping
    public String displayChart(PortletRequest request, Model model) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        

        //get user
        String userId = "";
        User currentUser = null;
		try {
			currentUser = PortalUtil.getUser(request);
		} catch (PortalException e) {
		} catch (SystemException e) {
		}
		userId = String.valueOf(currentUser.getScreenName());
		
        PortletSession portletSession = request.getPortletSession();
        portletSession.setAttribute("userId", userId, PortletSession.APPLICATION_SCOPE);
        
        
        ChartInstanceM chartInstanceM = null;
        String instanceId = "";
        try{
        instanceId=themeDisplay.getPortletDisplay().getInstanceId();
        // get chartInstance prop
        chartInstanceM=chartService.findChartInstanceById(instanceId);
        logger.info("request instance "+instanceId + " _ result chartId = "+chartInstanceM.getChartId());
    	}catch (Exception e){
    		//handle  new chartInstance error
    	}
        String chartType="";
        String chartHeight="300";
        String comment="";
        String dataSourceType="2";
        String subFromFilter="0";
        String titleFromFilter="0";
        String showFilter="0";
        String linkTo="";
        if(chartInstanceM!=null){
            chartType=chartInstanceM.getChartType();
            chartHeight=chartInstanceM.getChartHeight();
            if(chartInstanceM.getComment()!=null && chartInstanceM.getComment().getComment()!=null)
                comment=chartInstanceM.getComment().getComment();
            dataSourceType=chartInstanceM.getDataSourceType();
            subFromFilter=chartInstanceM.getSubFromFilter();
            titleFromFilter=chartInstanceM.getTitleFromFilter();
            showFilter=chartInstanceM.getShowFilter();
            linkTo=chartInstanceM.getLinkTo();
        }
        
        // chart Setting
        ChartSettingForm chartSettingForm = null;
        if (!model.containsAttribute("chartSettingForm")) {
            chartSettingForm = new ChartSettingForm();
        } else {
            chartSettingForm = (ChartSettingForm) model.asMap().get("chartSettingForm");
        }
        //setting form chart 
        chartSettingForm.setChartInstance(instanceId);
        chartSettingForm.setChartType(chartType);
        chartSettingForm.setChartHeight(chartHeight);
        chartSettingForm.setDataSourceType(dataSourceType);
        chartSettingForm.setSubFromFilter(subFromFilter);
        chartSettingForm.setTitleFromFilter(titleFromFilter);
        chartSettingForm.setShowFilter(showFilter);
        chartSettingForm.setLinkTo(linkTo);
        //retrive comment
        try{
        CommentM commentM = chartService.findCommentById(instanceId);
        if(commentM!=null)
        chartSettingForm.setComment(commentM.getComment());
        }catch(Exception e){
        	//handle commecnt exption
        }
        
        if(chartInstanceM!=null){
        	//get chart object
	        List<FilterM> allFilter = new ArrayList<FilterM>();
	        
	        //retrive global filter 
	        List<FilterM> globalFilter = new ArrayList<FilterM>();
	        if (model.containsAttribute("globalFilter")) {  //retrive from  global submit
	            FilterInstanceM filterIns = (FilterInstanceM) model.asMap().get("globalFilter");
	            globalFilter = filterIns.getFilterList();
	        }else{  // default all  global filter
	//        	globalFilter = chartService.getGlobalFilter(); 
	        }
	        chartSettingForm.setGlobalFilterString(encrptGlobalFilterString(globalFilter));//  store global filter
	        
	        //retrive auto filter
	        FilterM paramServicefilter = new FilterM();
	   		String userIdno = (String)request.getPortletSession().getAttribute("userId", PortletSession.APPLICATION_SCOPE);
	   		paramServicefilter.setUserid(userId);
	   		paramServicefilter.setServiceId(Integer.valueOf(chartInstanceM.getServiceId()));
	   		List<FilterM> serviceFilters = chartService.getFilterService(paramServicefilter);
	        

	        
	        // retrive internal filter 
	        List<FilterM> internalFilter = new ArrayList<FilterM>(); // retrive from previous cycle Run
	        if (model.containsAttribute("submitFilter")) {
	        	internalFilter = (ArrayList<FilterM>)model.asMap().get("submitFilter");
	        }else{ // get default FilterInstance
	            FilterInstanceM filterInstance = new FilterInstanceM();
	            filterInstance.setInstanceId(instanceId);
	            filterInstance.setFilterList(globalFilter);
	            filterInstance = chartService.getFilterInstance(filterInstance);
	            internalFilter = filterInstance.getFilterList();
	        }
            
	        
	        allFilter.addAll(serviceFilters);
	        mergeFiltersValue(allFilter,globalFilter);
	        mergeFiltersValue(allFilter,internalFilter);
	        
	        FusionChartM fusionChart = new FusionChartM();
	        fusionChart.setInstanceId(instanceId);
	        fusionChart.setFilters(allFilter);  // 
	        fusionChart.setUserid(userId);
	        fusionChart = chartService.getFusionChart(fusionChart);
	 
	        // setting chart & title
	        String ChartJsonString = "";
	        try{
	            if(chartInstanceM!=null){
		        	if(!chartInstanceM.getChartType().equalsIgnoreCase("table")){
					        JSONObject ChartJson = new JSONObject(fusionChart.getChartJson());
				        	JSONObject chart = (JSONObject) ChartJson.get("chart");
					        if(chartInstanceM.getChartTitle()!=null && ! chartInstanceM.getChartTitle().trim().equals("")){
					        	String newTitle =  generateChartTitle(chartInstanceM.getChartTitle(),fusionChart.getFilters(),chartSettingForm.getTitleFromFilter());      
					        	chart.put("caption", newTitle);
					        }
					        if(chartInstanceM.getChartSubTitle()!=null && !chartInstanceM.getChartSubTitle().trim().equals("")){
					        	String newSub = generateChartSubTitle(chartInstanceM.getChartSubTitle(),fusionChart.getFilters(),chartSettingForm.getSubFromFilter());
					        	chart.put("subCaption", newSub);
					        }
					        ChartJsonString = ChartJson.toString();    
		        	}else if(chartInstanceM.getChartType().equalsIgnoreCase("table")){
		        		logger.info("table size filter:"+fusionChart.getFilters().size());
		        		ChartJsonString = replaceParamString(fusionChart.getChartJson(),fusionChart.getFilters());
		        	}
		        }
	        }catch(Exception ex){
	        	  logger.info("WARNING! Exception Instance title generator : ["+chartInstanceM.getInstanceId()+"] \r\n reason => "+ex.getMessage()); 
	        	  ChartJsonString = fusionChart.getChartJson();
	        }
	        // set chart object 
	        chartSettingForm.setJsonStr( ChartJsonString );
	        
	        // add filter into view
	        model.addAttribute("filters",internalFilter);
        }
        model.addAttribute("chartSettingForm", chartSettingForm);
        
        return "chart/showChart";
    }
    @RequestMapping(params = "action=doSubmit") // action phase
    public void doSubmit(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                         @ModelAttribute("chartSettingForm") ChartSettingForm chartSettingForm,
                         BindingResult result, Model model) {
    	//logger.info("chart=>submit");
    	String instanceId = chartSettingForm.getChartInstance();
    	//retrive global Filter from view
    	String globalString = chartSettingForm.getGlobalFilterString();
    	FilterInstanceM globalFilter = new FilterInstanceM();
    	if(globalString!=null){
	    	if(!globalString.equals("")){
	    		globalFilter.setFilterList(decodeGlobalFilterString(globalString));
	    		model.addAttribute("globalFilter", globalFilter);
    		}
    	}
    	//retrive Internal Filter  from view
        FilterInstanceM filterInstance = new FilterInstanceM();
        filterInstance.setInstanceId(instanceId);
     //   filterInstance.setFilterList(globalFilter.getFilterList());
     //   List<FilterInstanceM> filters = chartService.getFilterInstanceWithItem(filterInstance);
        FilterInstanceM fin = chartService.getFilterInstance(filterInstance);
        
        for(FilterM filter : fin.getFilterList()){
    		String val = request.getParameter("filter_"+instanceId+"_"+filter.getFilterId());
    		if(val!=null){
    			filter.setSelectedValue(val);
    		}// end exist value
    	}
        model.addAttribute("submitFilter", fin.getFilterList());
    }
    
    //@javax.portlet.ProcessEvent(qname = "{http://liferay.com}paramOverride")
    @RequestMapping("VIEW")
    @EventMapping(value ="{http://liferay.com/events}paramOverride")
    public void receiveEvent(EventRequest request, EventResponse response, ModelMap map)
    {
        Event event = request.getEvent();
        FilterInstanceM globalFilter = (FilterInstanceM) event.getValue();
        map.addAttribute("globalFilter", globalFilter);
    }
    
    public String findFilterValueSelected(List<FilterValueM> filterValues,String Selected){
    	String a = "";
    	for( FilterValueM  fv : filterValues){
    		if(fv.getKeyMapping().equals(Selected)){
    			a = fv.getValueMapping();
    			break;
    		}
    	}
    	return a;
    }
    public String generateChartTitle(String titleString , List<FilterM> filters,String flag){
    	 String newTitle = titleString;
    	 if(flag!=null ){
    		 if( flag.equals("1")){
		    	 for( FilterM filter : filters ){
		    		 CharSequence  check = "_"+filter.getFilterName()+"_";
		    		 if(    newTitle.contains(check)    ){
		    			 newTitle = newTitle.replaceAll(check.toString(), findFilterValueSelected(filter.getFilterValues() , filter.getSelectedValue()) );
		    		 }
		    	 }
    		 }
    	 }//end check null
    	  return newTitle;
    }
    public String generateChartSubTitle(String subTitleString , List<FilterM> filters,String flag ){
    	String newSub = subTitleString;
    	if(flag!=null ){
    		if(flag.equals("1")){
	    		for( FilterM filter : filters ){
		    		 CharSequence  check = "_"+filter.getFilterName()+"_";
		    		 if(    newSub.contains(check)    ){
		    			 newSub = newSub.replaceAll(check.toString(), findFilterValueSelected(filter.getFilterValues() , filter.getSelectedValue()) );
		    		 }
		    	}
    		}//end check enable
    	 }// end check null
    	return newSub;
    }
    public String encrptGlobalFilterString(List<FilterM> globalFilter){
    	String filterString = "";
    	String nextLimit = ":next:";
    	String valLimit = ":val:";
    	for(FilterM gm : globalFilter){
    		filterString  = filterString+nextLimit+gm.getFilterName()+valLimit+gm.getSelectedValue();
    	}
    	//example  paramYear:val:2560:next:paramType:val:1
    	return filterString;
    }
    public List<FilterM> decodeGlobalFilterString(String filerString){
    	List<FilterM> globalFilter = new ArrayList<FilterM>();
    	String nextLimit = ":next:";
    	String valLimit = ":val:";
    	String[] gs = filerString.split(nextLimit);
    	for( String g : gs){
    		try{
	    		String[] gkv = g.split(valLimit);  // [0] = key , [1] = value
	    		FilterM gm = new FilterM();
	    		gm.setFilterName(gkv[0]);
	    		gm.setSelectedValue(gkv[1]);
	    		globalFilter.add(gm);
    		}catch(Exception ex){
    		}
    	} // end loop
    	return globalFilter;
    }
    //cascade ajax
    @ResourceMapping(value="cascadeInternalFilter")
   	@ResponseBody 
   	public void cascadeInternalFilter(ResourceRequest request,ResourceResponse response) throws IOException{
       
    	com.liferay.portal.kernel.json.JSONObject json = JSONFactoryUtil.createJSONObject();
    	com.liferay.portal.kernel.json.JSONObject header = JSONFactoryUtil.createJSONObject();
		com.liferay.portal.kernel.json.JSONArray content = JSONFactoryUtil.createJSONArray();
       	
		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
   		HttpServletRequest normalRequest	=	PortalUtil.getOriginalServletRequest(httpReq);
       	String cascadeRequest = normalRequest.getParameter("cascade");
       	
        try {
    		org.codehaus.jettison.json.JSONObject filterObj = new JSONObject(cascadeRequest); // convert
			String instanceId = filterObj.getString("instance");  
			String causeId = filterObj.getString("cause");  
			org.codehaus.jettison.json.JSONArray filters = filterObj.getJSONArray("filters");
			List<FilterM> newFilters = new ArrayList<FilterM>();
			for(int i =0;i< filters.length();i++){
				org.codehaus.jettison.json.JSONObject filter = filters.getJSONObject(i);
				FilterM f = new FilterM();
				f.setFilterId( Integer.parseInt( filter.get("id").toString() ) );
				f.setFilterName(  filter.get("param").toString() );
				f.setSelectedValue(  filter.get("value").toString() );
				f.setValueType( filter.get("type").toString() );
				
				f.setActiveFlag("1");
				if(  filter.get("id").toString().equals(causeId)){
 	    			f.setActiveFlag("0"); // important to mark ,this is cause filter 
 	    		}
				newFilters.add(f);
			}
	       	FilterInstanceM fin = new FilterInstanceM();  
	       	fin.setInstanceId(instanceId);
	       	fin.setFilterList( newFilters );
	       	List<FilterM> changeFilter = chartService.cascadeFilterItems(fin); //
	       	for(FilterM filter : changeFilter){
	       		if(!filter.getFilterId().equals(causeId)){  // Don't change cause Id
	       			com.liferay.portal.kernel.json.JSONObject fo = JSONFactoryUtil.createJSONObject();
	   	    		fo.put("id", filter.getFilterId());
	   	    		fo.put("value", filter.getSelectedValue());
	   	    		JSONArray foItem = JSONFactoryUtil.createJSONArray();
	   	    		for(FilterValueM fv : filter.getFilterValues()){
	   	    			com.liferay.portal.kernel.json.JSONObject fov = JSONFactoryUtil.createJSONObject();
	   	    			fov.put("key",fv.getKeyMapping());
	   	    			fov.put("desc", fv.getValueMapping());
	   	    			foItem.put(fov);
	   	    		}
	   	    		fo.put("item", foItem);
	   	        	content.put(fo);
	       		}
	       	}
	       	header.put("success","1");
        }catch(Exception e){
        	header.put("success","0");
        }
    	json.put("header",header);
       	json.put("content", content);
   		response.getWriter().write(json.toString());
    }
    private List<FilterM> decriptCascadeString(String cascadeString,String causeFilterId){
   	 List<FilterM> filters = new ArrayList<FilterM>();
   	 // example string =  "filterId::filterValue||filterId::filterValue"

   	 
   	String filterLimit = ":#:";
    	String seperate = ":&:";
    	
    	String[] gs = cascadeString.split(filterLimit);
    	for( String g : gs){
	    		String[] gkv = g.split(seperate);  // [0] = filterId , [1] = value
	    			FilterM gm = new FilterM();
	 	    		gm.setFilterId( Integer.parseInt( (String)gkv[0] ) );
	 	    		try{
	 	    			gm.setSelectedValue( (String)gkv[1] );
	 	    		}catch(Exception ex){
	 	    			gm.setSelectedValue(null);
	 	    		}
	 	    		gm.setActiveFlag("1");
	 	    		if( ((String)gkv[0].toString()).equals(causeFilterId)){
	 	    			gm.setActiveFlag("0"); // important to mark ,this is cause filter 
	 	    		}
	 	    		filters.add(gm);
    	} // end loop
   	 return filters;
   }
    
    @ResourceMapping(value="buildChartAjax")
    @ResponseBody
	public void onSubmitAjax( ResourceRequest request,ResourceResponse response) throws IOException{
    	//request.getParameterMap(); 
      	com.liferay.portal.kernel.json.JSONObject json = JSONFactoryUtil.createJSONObject();

		com.liferay.portal.kernel.json.JSONObject header = JSONFactoryUtil.createJSONObject();
		com.liferay.portal.kernel.json.JSONObject content = JSONFactoryUtil.createJSONObject();	
       	
		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
   		HttpServletRequest normalRequest	=	PortalUtil.getOriginalServletRequest(httpReq);
       	String filterRequest = normalRequest.getParameter("filter");
       	filterRequest = filterRequest.replace('"','\"');
    	List<FilterM> filterList = new ArrayList<FilterM>();
    	  try {
    		org.codehaus.jettison.json.JSONObject filterObj = new JSONObject(filterRequest); // convert
			String instanceId = filterObj.getString("instance");
    		org.codehaus.jettison.json.JSONArray filters = filterObj.getJSONArray("filters");
			for(int i =0;i< filters.length();i++){
				org.codehaus.jettison.json.JSONObject filter = filters.getJSONObject(i);
				FilterM f = new FilterM();
				f.setFilterId( Integer.parseInt( filter.get("id").toString() ) );
				f.setFilterName(  filter.get("param").toString() );
				f.setSelectedValue(  filter.get("value").toString() );
				f.setValueType( filter.get("type").toString() );
				filterList.add(f);
			} //   retrive filerList from ajax
			//call service
			
			FusionChartM chartObj = requestChartObject(instanceId,filterList);
			content.put("chartType", chartObj.getChartType());
			content.put("chartJson", chartObj.getChartJson() );
			header.put("success","1" );
		} catch (JSONException e) {
			e.printStackTrace();
			header.put("success","0" );
		}
    	json.put("content", content);
    	json.put("header",header );
   		response.getWriter().write(json.toString());
    }
    private FusionChartM requestChartObject(String instanceId,List<FilterM> paramFilter){
    	 List<FilterM> allFilter = new ArrayList<FilterM>();
         allFilter.addAll(paramFilter);
         FusionChartM fusionChart = new FusionChartM();
         fusionChart.setInstanceId(instanceId);
         fusionChart.setFilters(allFilter);  // 
         fusionChart = chartService.getFusionChart(fusionChart);
		return fusionChart;
    }
    private void mergeFiltersValue(List<FilterM> mains,List<FilterM> inserts){
    	
    	boolean merged = false;
		for(FilterM insert : inserts){
			for(FilterM main : mains){
				logger.info( " merge fileter :"+ insert.getFilterId() + " _ " +main.getFilterId() );
				if(main.getFilterId().equals(insert.getFilterId())){
					main.setSelectedValue(insert.getSelectedValue());
					merged = true;
					break; //  match once
				}
			}
			if(!merged){
				mains.add(insert);
			}
			logger.info(  " flag merge = "+merged);
			merged = false;
		}
    }
    private String replaceParamString(String chartJson,List<FilterM> filters){
    	String newChartJson = chartJson;
    	for(FilterM filter : filters){
	    	 CharSequence  check = "_"+filter.getFilterName()+"_";
			 if(    newChartJson.contains(check)    ){
				 newChartJson = newChartJson.replaceAll(check.toString(), findFilterValueSelected(filter.getFilterValues() , filter.getSelectedValue()) );
			 }else{ // not found remove _filer_  from display
				 newChartJson = newChartJson.replaceAll(check.toString(), "" );
			 }
    	}
    	return newChartJson;
    }
}
