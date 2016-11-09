package th.ac.kmutt.chart.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import th.ac.kmutt.chart.constant.DefaultConstant;
import th.ac.kmutt.chart.form.ChartDatasourceForm;
import th.ac.kmutt.chart.form.FilterForm;
import th.ac.kmutt.chart.model.ConnectionM;
import th.ac.kmutt.chart.model.FilterInstanceM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.FilterValueM;
import th.ac.kmutt.chart.model.ServiceM;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.util.ValueUtils;

import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import java.io.IOException;
import java.security.Provider.Service;
import java.util.*;

@Controller("filterController")
@RequestMapping("VIEW")
public class FilterController {

    private static final Logger logger = Logger.getLogger(FilterController.class);


    @Autowired
    @Qualifier("chartServiceWSImpl")
    private ChartService chartService;
    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        //String a[] = new String[]{"ntcfaq.nfaqName"};
        final String[] ALLOWED_FIELDS = {""};
        binder.setAllowedFields(ALLOWED_FIELDS);
    }


    @RequestMapping("VIEW") 
    @RenderMapping
    public String iniPage(PortletRequest request, Model model) {
  
    	FilterForm filterForm = null;
        if (!model.containsAttribute("filterForm")) {
        	filterForm = new FilterForm();
            //default
            filterForm.setMode(DefaultConstant.modeNewData);
        } else {
        	filterForm = (FilterForm) model.asMap().get("filterForm");
        }
        FilterM f = new FilterM();
        List<FilterM> filters = chartService.listFilter(f);
        Set<String> types = new HashSet<String>(DefaultConstant.filterTypeList);
        filterForm.setTypes(types);
        filterForm.setFilters(filters);
        filterForm.setFilterList(new HashSet<FilterM>(filters));
        filterForm.setInitialFilterList(new HashSet<FilterM>());
     //   filterForm.setFilterUsedList(filterUsedList);
        
        List<ConnectionM> cons  = chartService.listConnection();
        filterForm.setConnections(new  HashSet<ConnectionM>(cons));
        
        model.addAttribute("filterForm",filterForm);
        return "configuration/filter";
    }

    @RequestMapping(params = "action=doSubmit")
    public void doSubmitForm(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("filterForm") FilterForm filterForm,
                             BindingResult result, Model model) {

		String mode = request.getParameter("mode");
    	try{
    		
    		
	    	 FilterM filter = new FilterM();
	    	 filter.setFilterName(  request.getParameter("filterName") );
	    	 filter.setSqlQuery( request.getParameter("sqlString")  );
	    	 filter.setValueType( request.getParameter("type") );
	    	 filter.setDefaultValue( request.getParameter("defaultValue") );
	    	 filter.setSelectedValue( request.getParameter("defaultValue") );
	    	 filter.setTitle( request.getParameter("label") );
	    	 String global =  request.getParameter("global")!=null ?  "1" : "0";
	    	 filter.setGlobalFlag(global);
	    	 String system = request.getParameter("system")!=null ?  "1" : "0";
	    	 filter.setSystemFlag(system);  
	    	 String auto = request.getParameter("autoFill")!=null ? "1" : "0";
	    	 filter.setAutoFill(auto);
	    	 filter.setConnId(	 Integer.parseInt( 	request.getParameter("selectedConnection") ) );
	    	 filter.setSqlFlag("1");
	    	 filter.setActiveFlag("1");
	    	 
	    	 //filter map
	 		String[] selectedFilter = request.getParameterValues("filterUsedList") != null ? request.getParameterValues("filterUsedList") : new String[0];
			List<String> usedFilterId = selectedFilter.length > 0 ? Arrays.asList(selectedFilter) : new ArrayList<String>();
			List<FilterM> listSelectedFilter = new ArrayList<FilterM>();
			for(String filterId : usedFilterId ){
				FilterM fm = new FilterM();
				fm.setFilterId(Integer.parseInt(filterId));
				listSelectedFilter.add(fm);
			}
			filter.setFilterList(listSelectedFilter); 
			
			// case action
	    	 if( DefaultConstant.modeNewData.equals( mode)  ){
	    		 filter.setFilterId(null);
	    		 Integer newId = chartService.saveFilter(filter);
	    		  filterForm.setMessage(" Save as filter success ");
	    	 }else if ( DefaultConstant.modeEditData.equals( mode ) ){
	        	 filter.setFilterId(  Integer.parseInt(request.getParameter("filterId") ) );
	    		 Integer status = chartService.updateFilter(filter);
	    		  filterForm.setMessage(" Update filter success ");
	         }else if( DefaultConstant.modeDeleteData.equals(mode)){
	        	 	filter.setFilterId(  Integer.parseInt(request.getParameter("filterId") ) );
	        	 	Integer totalDelete = chartService.deleteFilter(filter);
	        	 	filterForm.setMessage(" Delete filter success ");
	         }else{
	        		filterForm.setMessage(" No Action ["+mode+"]");
	         }
    	}catch(Exception e){
    		logger.error(" Filter mode"+mode+" exception "+e);
    		if( DefaultConstant.modeNewData.equals( mode)  ){
	    		  filterForm.setMessage(" Save as filter fail ");
	    	 }else if ( DefaultConstant.modeEditData.equals( mode ) ){
	        	  filterForm.setMessage(" Update filter fail ");
	         }else if( DefaultConstant.modeDeleteData.equals(mode)){
	        	 	filterForm.setMessage(" Delete filter fail ");
	         }
    	}
    	 model.addAttribute("filterForm", filterForm);
    }
    
    @ResourceMapping(value="selectedFilter")
  	@ResponseBody 
  	public void detailFilter(ResourceRequest request,ResourceResponse response) throws IOException{
      	JSONObject json = JSONFactoryUtil.createJSONObject();
      	JSONObject head = JSONFactoryUtil.createJSONObject();
      	JSONObject content = JSONFactoryUtil.createJSONObject();
          
      	HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
  		HttpServletRequest normalRequest	=	PortalUtil.getOriginalServletRequest(httpReq);
   
  	//	try{ 
  			// filter detail
  	    	FilterM filter = new FilterM();
  	    	filter.setFilterId(Integer.parseInt( normalRequest.getParameter( "filterId" ) ) ) ;
  	    	filter = chartService.detailFilter(filter) ;   // get detail
  	    	content.put("fid",filter.getFilterId() );
  	    	content.put("fname", filter.getFilterName());
  	    	content.put("sql", filter.getSqlQuery());
  	    	content.put("valueType", filter.getValueType());
  	    	content.put("global",filter.getGlobalFlag());
  	    	content.put("system", filter.getSystemFlag());
  	    	content.put("auto",filter.getAutoFill());
  	    	content.put("defaultValue", filter.getDefaultValue() );
  	    	content.put("label", filter.getTitle());
  	    	content.put("connId", ValueUtils.toJson( filter.getConnId() ) );
  	    	// filter param (subfilter)
  	    	JSONArray fs =  JSONFactoryUtil.createJSONArray();
  	    	for( FilterM usedFilter : filter.getFilterList() ){
  	    		JSONObject usedJson = JSONFactoryUtil.createJSONObject();
  	    		usedJson.put("fid",usedFilter.getFilterId() );
  	    		usedJson.put("fname",  usedFilter.getFilterName() );
  	    		usedJson.put("flabel",usedFilter.getTitle() );
  	    		fs.put(usedJson);
  	    	}
  	    	content.put("fusedlist",fs);
  	    
  	    	head.put("success","1");
  	    	head.put("msg", "success");
  	//	}catch(Exception e){
  	//		logger.info("exp:"+e.getMessage());
  	//		head.put("success","0");
  	//		head.put("msg", e.getMessage() );
  //		}
      	json.put("content", content);
      	json.put("header",head);
  		response.getWriter().write(json.toString());
      }
}
