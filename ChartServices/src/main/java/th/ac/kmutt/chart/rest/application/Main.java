/**
 * Copyright 2005-2008 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of the following open
 * source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.sun.com/cddl/cddl.html
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royaltee free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package th.ac.kmutt.chart.rest.application;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.restlet.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.model.SystemM;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.service.impl.DatasourceService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;
import th.ac.kmutt.chart.xstream.common.ImakeXML;
import th.ac.kmutt.chart.xstream.common.Paging;

/**
 * Simple demo application that can be run either as a standalone application
 * (http://localhost:3000/v1/) or inside a servlet container
 * (http://localhost:8080/v1/).
 * 
 */
public class Main {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    
//    public static void main(String... args) throws Exception {
	public static void main(String[] args) throws Exception {
        // Load the Spring application context
        final ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(
                new String[] {
                		 "config/applicationContext-common.xml",
                		 "config/applicationContext-hibernate.xml",
						"config/applicationContext-chart-common-resource.xml",
                		 "config/applicationContext-root-router.xml"  ,
                		 "config/applicationContext-server.xml"});

        // Obtain the Restlet component from the Spring context and start it
        ((Component) springContext.getBean("top")).start();
        springContext.close();
        //initCon();
     }
	private void initCon(){
        // #####  after initial  do load connections ############
        System.out.println("ChartPortlet service echo=>"+SystemSetting.echo+"");
        System.out.println("ChartPortlet loading  Connections... ");
        SystemM sys = new SystemM();
        sys.setServiceName(SystemSetting.serviceLoadConns);
        systemConfig(sys);
        
        sys.setServiceName(SystemSetting.serviceStatConns);
        systemConfig(sys);

        /*sys.setServiceName(SystemSetting.serviceDeleteConns);
        sys.setConName("dev");
        systemConfig(sys);
        
        sys.setServiceName(SystemSetting.serviceStatConns);
        systemConfig(sys);
        */
        System.out.println("finish loaded ChartPortlet Connections");
   
	}
	 private static void systemConfig(SystemM param) {
	      SystemM sys = param;
	      String url = "system";
		  HttpPost httppost = new HttpPost("http://localhost:8081/ChartServices/rest/"+url);
	       XStream xstream = new XStream(new Dom4JDriver());
	       Class c = null;
	        try {
	            c = Class.forName(sys.getClass().getName());
	        } catch (ClassNotFoundException e2) {
	            e2.printStackTrace();
	        }
	        xstream.processAnnotations(c);

	        ByteArrayEntity entity = null;
	        
	        String xString = xstream.toXML(sys);
	        try {
				entity = new ByteArrayEntity(xString.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
	        
	        httppost.setEntity(entity); 
	 	     
	          CloseableHttpClient httpclient = HttpClientBuilder.create().build();
	        HttpResponse response = null;
	        HttpEntity resEntity = null;
	        try {
	            response = httpclient.execute(httppost);
	            resEntity = response.getEntity();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        	
	        //httpclient.getConnectionManager().shutdown();
	        try {
	            httpclient.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
