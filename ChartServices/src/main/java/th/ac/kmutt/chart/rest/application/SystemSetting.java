package th.ac.kmutt.chart.rest.application;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

public class SystemSetting {
		public static final String echo = "Echo ChartServiceSystemSetting";
		public static final String serviceNewConns = "createConnection";
		public static final String serviceDeleteConns = "deleteConnection";
		public static final String serviceStatConns = "statConnection";
		public static final String serviceGetConns = "getConnection";
		public static final String serviceLoadConns = "loadConnection";
		
	    public static Map<String, EntityManager> connects = new HashMap<String, EntityManager>();
	    static {
	    }
	    public static boolean existConnects(){
	    	if(connects!=null && connects.size()>=1){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }
	    public static String statConnects(){
	    	StringBuffer message = new StringBuffer();
	    	message.append("size: "+connects.size());
	    	return message.toString();
	    }
	    public static void addConnects(String refKey,EntityManager em){
	    	connects.put(refKey, em);
	    }
	    public static EntityManager getConnects(String refKey) {
	    	return connects.get(refKey);
	    }
	    //############## WARNING SECTION ############/
	    /* becarefull make sure all data be save and not run this while running application*/
	    public static void cleanConnects(){
	    	connects = new HashMap<String, EntityManager>();
	    }
	    public static void deleteConnects(String refKey){
	    	connects.remove(refKey);
	    }
	    // ################# END WARNING SECTION ###############
}
