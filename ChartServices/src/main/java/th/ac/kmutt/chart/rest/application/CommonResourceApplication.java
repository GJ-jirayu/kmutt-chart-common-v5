package th.ac.kmutt.chart.rest.application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.restlet.Application;
import org.restlet.Restlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.model.SystemM;
import th.ac.kmutt.chart.service.impl.DatasourceService;

/**
 * @author Chatchai Pimtun (Admin)
 *
 */
public class CommonResourceApplication extends Application {


    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("datasourceService")
    private DatasourceService dataService;
	
	
   @Override
    public synchronized Restlet createInboundRoot(){    
	  
    //createRoot() {
        // Create a router Restlet that defines routes.
    	final   ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(
                 new String[] {
                		 "config/applicationContext-common.xml",
                		 "config/applicationContext-hibernate.xml",
						 "config/applicationContext-chart-common-resource.xml",
                		 "config/applicationContext-root-router.xml"});
    	
        org.restlet.ext.spring.SpringRouter router = (org.restlet.ext.spring.SpringRouter)springContext.getBean("root");
        
        springContext.close();

        return router;
        
    } 
   //###############

}
