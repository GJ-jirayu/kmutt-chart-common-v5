package th.ac.kmutt.chart.builder;

import java.util.List;

import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import th.ac.kmutt.chart.constant.ServiceConstant;


public class HorizontalLED extends CommonChart implements Chart {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	
	List<Object[]> data;
	public HorizontalLED(){
		super("HorizontalLED");  
	}
	@Override
	public void setData(List<Object[]> data){
		this.data = data;
	}
	@Override
	public String build(){
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject		
		try{			
			chartJson.put("value", data.get(0));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return chartJson.toString();
	}
}
