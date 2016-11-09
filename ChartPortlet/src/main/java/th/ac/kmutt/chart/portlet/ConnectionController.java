package th.ac.kmutt.chart.portlet;


import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.WebKeys;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import th.ac.kmutt.chart.constant.DefaultConstant;
import th.ac.kmutt.chart.form.ChartDatasourceForm;
import th.ac.kmutt.chart.form.ConnectionForm;
import th.ac.kmutt.chart.form.GlobalFilterForm;
import th.ac.kmutt.chart.model.ConnectionM;
import th.ac.kmutt.chart.model.FilterInstanceM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.FilterValueM;
import th.ac.kmutt.chart.model.ServiceM;
import th.ac.kmutt.chart.service.ChartService;

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

@Controller("connectionController")
@RequestMapping("VIEW")
public class ConnectionController {

    private static final Logger logger = Logger.getLogger(ConnectionController.class);


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
    public String iniPage(PortletRequest request, Model model) {
    	ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        
    	ConnectionForm connForm = null;
        if (!model.containsAttribute("connectionForm")) {
        	connForm = new ConnectionForm();
        } else {
        	connForm = (ConnectionForm) model.asMap().get("connectionForm");
        }
        
        List<ConnectionM> conns = chartService.listConnection();
        connForm.setConns(new HashSet<ConnectionM>(conns));
       
        model.addAttribute("connectionForm",connForm);
        return "configuration/connection";
    }

    @RequestMapping(params = "action=doSave") // action phase
    public void doSave(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("connectionForm") ConnectionForm connForm,
                             BindingResult result, Model model) {
        String mode = request.getParameter("mode");
        ConnectionM conn = new ConnectionM();
        conn.setConnName( request.getParameter("model.connName") );
        conn.setConnString(request.getParameter("model.connString") );
        conn.setDriverClass(request.getParameter("model.driverClass") );
        conn.setDialect(request.getParameter("model.dialect") );
        conn.setUsername(request.getParameter("model.username") );
        conn.setPassword(request.getParameter("model.password") );
        
    	if( DefaultConstant.modeNewData.equalsIgnoreCase(  mode ) ){
    		try{
    			  conn.setConnId(null);
    			chartService.newConnection(conn);
    			connForm.setMessage("Save as connection success");	
    		}catch(Exception e){
    			connForm.setMessage("Save as connection fail");
    			logger.error("Connection new exception reason "+e);
    		}
    	}else if( DefaultConstant.modeEditData.equalsIgnoreCase( mode ) ){
    		try{
    	        conn.setConnId( Integer.parseInt( request.getParameter("model.connId") ) );
	    		chartService.updateConnection(conn);
	    		connForm.setMessage("Update connection success");
	    	}catch(Exception e){
				connForm.setMessage("Update connection fail");
				logger.error("Connection edit exception reason "+e);
			}
    	}else if( DefaultConstant.modeDeleteData.equalsIgnoreCase( mode ) ){
    		
    		try{
    	        conn.setConnId( Integer.parseInt( request.getParameter("model.connId") ) );
	    		chartService.deleteConnection(conn);
	    		connForm.setMessage("Delete connection success");
	    	}catch(Exception e){
				connForm.setMessage("Delete connection fail");
				logger.error("Connection delete exception reason "+e);
			}
    	}else{
    		connForm.setMessage("Invalid action name ["+mode+"]");
    	}
    	model.addAttribute("connectionForm", connForm);
    }
    
    //
    
    @ResourceMapping(value="getConnection")
  	@ResponseBody 
  	public void getConnectionDetail(ResourceRequest request,ResourceResponse response) throws Exception{
      	JSONObject json = JSONFactoryUtil.createJSONObject();
      	JSONObject head = JSONFactoryUtil.createJSONObject();
      	JSONObject content = JSONFactoryUtil.createJSONObject();
          
      	HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
  		HttpServletRequest normalRequest	=	PortalUtil.getOriginalServletRequest(httpReq);
  		 
  		ConnectionM model = chartService.findConnectionById(Integer.parseInt(normalRequest.getParameter( "connId" )));
  		content.put("connName", model.getConnName());
  		content.put("connString", model.getConnString());
  		content.put("driverClass", model.getDriverClass());
  		content.put("dialect", model.getDialect());
  		content.put("username", model.getUsername());
  		content.put("password",model.getPassword());
  		
  		head.put("success","1");
      	json.put("content", content);
      	json.put("header",head);
  		response.getWriter().write(json.toString());
      }
}
