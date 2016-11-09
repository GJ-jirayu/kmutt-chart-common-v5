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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.model.SystemM;
import th.ac.kmutt.chart.service.impl.DatasourceService;

public class Loader {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
   
    @Autowired
    @Qualifier("datasourceService")
    private DatasourceService dataService;

	public Loader(){
		loadConns();
	}
	private void loadConns(){
		dataService.loadEm();
	}
    private void loadConnsByUrl(){
        logger.info("ChartPortlet Loading Connections ...");
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
        logger.info("finish loaded ChartPortlet Connections");
    
    }

    private void systemConfig(SystemM param) {
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
