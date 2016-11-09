package th.ac.kmutt.chart.portlet;


import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import th.ac.kmutt.chart.constant.DefaultConstant;
import th.ac.kmutt.chart.form.GlobalFilterForm;
import th.ac.kmutt.chart.model.CommentM;
import th.ac.kmutt.chart.model.FilterInstanceM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.FilterValueM;
import th.ac.kmutt.chart.service.ChartService;

import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import java.io.IOException;
import java.util.*;

@Controller("globalFilterController")
@RequestMapping("VIEW")
@SessionAttributes({"globalFilterForm"})
public class GlobalFilterController {

    private static final Logger logger = Logger.getLogger(GlobalFilterController.class);

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
                "researchGroupM.updatedDate", "researchGroupM.groupTh", "researchGroupM.groupEng", "mode",
                "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter","instanceId","filterGlobals","aoe_global"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping("VIEW") 
    public String showFilter(PortletRequest request, Model model) {
    	ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String instanceId=themeDisplay.getPortletDisplay().getInstanceId();
        PortletSession portletSession = request.getPortletSession();  
        
        portletSession.setAttribute("instanceId" , instanceId,PortletSession.PORTLET_SCOPE);
        GlobalFilterForm filterForm = null;
        if (!model.containsAttribute("globalFilterForm")) {
            filterForm = new GlobalFilterForm();
        } else {
            filterForm = (GlobalFilterForm) model.asMap().get("globalFilterForm");
        }
        //
        CommentM commentM = chartService.findCommentById(instanceId);
        if(commentM!=null)
        filterForm.setComment(commentM.getComment());
        
        // retrive submit global filter value
        List<FilterM> globalFilter = new ArrayList<FilterM>();
        if (model.containsAttribute("FilterMList")) {
        	globalFilter = (ArrayList<FilterM>) model.asMap().get("FilterMList");
        }
        else{ // visit first  No submit action
        	FilterInstanceM fim = new FilterInstanceM();
        	fim.setInstanceId(instanceId);
        	List<FilterInstanceM>  fins = chartService.getFilterInstanceWithItem(fim);
        	if(fins!=null){
	        	for(FilterInstanceM fin : fins){
	        		globalFilter.add(fin.getFilterM());
	        	}
        	} // have fins
        }
        model.addAttribute("globalFilterForm",filterForm);
        model.addAttribute("filterList",globalFilter);
        model.addAttribute("textType",DefaultConstant.filterTypeList.get(0)); // input text
        model.addAttribute("selectType",DefaultConstant.filterTypeList.get(1)); // select
        model.addAttribute("multipleType",DefaultConstant.filterTypeList.get(2)); // multiple
        return "filter/showFilter";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void doSubmit(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("globalFilterForm") GlobalFilterForm filterForm,
                             BindingResult result, Model model) {

       // get initial filter instance for compare
       FilterInstanceM fim = new FilterInstanceM();
       PortletSession session = request.getPortletSession();
       String instanceId = (String) session.getAttribute("instanceId",PortletSession.PORTLET_SCOPE);
       fim.setInstanceId(instanceId);
   		List<FilterInstanceM>  fins = chartService.getFilterInstanceWithItem(fim);
       List<FilterM> gFilters = new ArrayList<FilterM>(); // new for return & send to common
       logger.info("global submit filter size ="+fins.size() +" id="+instanceId);
       for(FilterInstanceM fin : fins){ 
    	   FilterM f = fin.getFilterM();
    	   String val = null;
    	   if( f.getValueType().equals(DefaultConstant.filterTypeList.get(0) ) ){
        	    val = request.getParameter("g_filter_"+f.getFilterId());
        	   f.setSelectedValue(val);
    	   }else if( f.getValueType().equals(DefaultConstant.filterTypeList.get(1) ) ){
        	    val = request.getParameter("g_filter_"+f.getFilterId());
        	   f.setSelectedValue(val);
    	   }else if ( f.getValueType().equals(DefaultConstant.filterTypeList.get(2) ) ){
    		   String[] vals = request.getParameterValues("g_filter_"+f.getFilterId());
    		    val = StringUtils.join(vals, ",");
    		   f.setSelectedValue(val);
    	   }
    	   gFilters.add(f);
       }
       
       /*
       for(int i = 0 ; i<gFilters.size();i++){
    	   // map  name select in view showfilter.jsp  format  g_filter_+filterM.filterId
    	   String val = request.getParameter("g_filter_"+gFilters.get(i).getFilterId());
    	   if(val!=null ){
    		   if(!val.trim().equals("")){
    			   gFilters.get(i).setSelectedValue(val);
    		   		reRenFilters.add(gFilters.get(i));
    		   }
    	   }
       	}*/
       // portlet to portlet  require configuration  portlet.xml
        FilterInstanceM globalFilterIns = new FilterInstanceM();
        globalFilterIns.setFilterList(gFilters);
       
       	QName qname = new QName("http://liferay.com/events","paramOverride","x");
       	response.setEvent(qname, globalFilterIns); 
        
        //re show 
       model.addAttribute("FilterMList", gFilters);
    }
    @ResourceMapping(value="cascadeGlobalFilter")
	@ResponseBody 
	public void cascadeGlobalFilter(ResourceRequest request,ResourceResponse response) throws IOException{
    	//for cascade parameter
    	JSONObject json = JSONFactoryUtil.createJSONObject();
    	JSONArray header = JSONFactoryUtil.createJSONArray();
    	JSONArray content = JSONFactoryUtil.createJSONArray();
       	
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	String instanceId=themeDisplay.getPortletDisplay().getInstanceId();
    	
    	HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
		HttpServletRequest normalRequest	=	PortalUtil.getOriginalServletRequest(httpReq);
    	String causeFilterId = normalRequest.getParameter("filterId");
    	String factorString = normalRequest.getParameter("factor");
    	// format factorStrung  =  "filterId::filterValue||filterId::filterValue"
    	FilterInstanceM fin = new FilterInstanceM();  
    	fin.setInstanceId(instanceId);
    	fin.setFilterList( decriptCascadeString(factorString,causeFilterId) );
    	List<FilterM> filters = chartService.cascadeFilterItems(fin); //
    	for(FilterM filter : filters){
    		if(!filter.getFilterId().equals(causeFilterId)){  // only change element to insert
	    		JSONObject fo = JSONFactoryUtil.createJSONObject();
	    		fo.put("id", filter.getFilterId());
	    		fo.put("value", filter.getSelectedValue());
	    		JSONArray foItem = JSONFactoryUtil.createJSONArray();
	    		for(FilterValueM fv : filter.getFilterValues()){
	    			JSONObject fov = JSONFactoryUtil.createJSONObject();
	    			fov.put("key",fv.getKeyMapping());
	    			fov.put("desc", fv.getValueMapping());
	    			foItem.put(fov);
	    		}
	    		fo.put("item", foItem);
	        	content.put(fo);
    		}
    	}
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
}
