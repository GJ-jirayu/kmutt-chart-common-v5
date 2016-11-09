package th.ac.kmutt.chart.portlet;

/**
 * Created by imake on 07/09/2015.
 */

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;

import th.ac.kmutt.chart.form.ChartSettingForm;
import th.ac.kmutt.chart.form.GlobalFilterForm;
import th.ac.kmutt.chart.model.ChartInstanceM;
import th.ac.kmutt.chart.model.CommentM;
import th.ac.kmutt.chart.model.FilterInstanceM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.service.ChartService;

import javax.portlet.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller("globalFilterSettingController")
@RequestMapping("EDIT")
@SessionAttributes({"globalFilterForm"})
public class GlobalFilterSettingController {

    private static final Logger logger = Logger
            .getLogger(GlobalFilterSettingController.class);
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

    @RequestMapping
    // default (action=list)
    public String showSettingFilter(PortletRequest request,PortletResponse response, Model model) {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String instanceId=themeDisplay.getPortletDisplay().getInstanceId();
        
        List<FilterM> filterList= chartService.getGlobalFilter();
    //    model.addAttribute("filterList",filterList);
        GlobalFilterForm  globalFilterForm = null;
        if (!model.containsAttribute("globalFilterForm")) {
            globalFilterForm = new GlobalFilterForm();
            model.addAttribute("globalFilterForm",globalFilterForm);
        } else {
        	globalFilterForm = (GlobalFilterForm) model.asMap().get("globalFilterForm");
        }
        CommentM commentM = chartService.findCommentById(instanceId);
        if(commentM!=null)
        	globalFilterForm.setComment(commentM.getComment());
        
        FilterInstanceM filterInstanceM=new FilterInstanceM();
        filterInstanceM.setInstanceId(instanceId);

        List<String> mark = new ArrayList<String>();
        List<FilterInstanceM> filterInstanceList= chartService.listFilterInstance(filterInstanceM);
        if(filterInstanceList!=null){
	        for(FilterInstanceM filterInstance : filterInstanceList){
	        	mark.add( filterInstance.getFilterM().getFilterId().toString());
	        }
        }
        String[] filterGlobals = mark.toArray(new String[0]);
        globalFilterForm.setInstanceId(instanceId);
        globalFilterForm.setFilterGlobals(filterGlobals);
        globalFilterForm.setFilterList(filterList);
        model.addAttribute("globalFilterForm", globalFilterForm);
        return "filter/editFilter";
    }
    @RequestMapping(params = "action=doSubmit") // action phase
    public void doSubmit(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("globalFilterForm") GlobalFilterForm globalFilterForm,
                             BindingResult result, Model model) {
     
        String[] ids=globalFilterForm.getFilterGlobals();
        List<String> filterIds = Arrays.asList(ids);
        List<FilterM> filterList = new ArrayList<FilterM>();
        
        for(String filterId : filterIds){
        	FilterM filter = new FilterM();
        	filter.setFilterId(Integer.valueOf(filterId));
        	filterList.add(filter);
        }
        // fix bug  form can't get textarea box
        String comment = request.getParameter("comment");
        if(comment!=null){
        	if(!comment.equals("")){
        		CommentM commentM = new CommentM();
        		commentM.setComment(comment);
        		commentM.setInstanceId(globalFilterForm.getInstanceId());
        		chartService.saveComment(commentM);
        	}
        }
        
        FilterInstanceM fim= new FilterInstanceM();
        fim.setInstanceId(globalFilterForm.getInstanceId());
        fim.setFilterList(filterList);
        chartService.saveFilterInstance(fim);

        try {
            response.setPortletMode(PortletMode.VIEW);
            response.setRenderParameter("action", "list");
        } catch (PortletModeException e) {
            e.printStackTrace();
        }
        // logger.info("chartType -->"+chartSettingForm.getChartType());
    }
}
