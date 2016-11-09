package th.ac.kmutt.chart.builder;

import java.util.List;

import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import th.ac.kmutt.chart.constant.ServiceConstant;


public class HorizontalBullet extends CommonChart implements Chart {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	
	List<Object[]> data;
	public HorizontalBullet(){
		super("HorizontalBullet");  
	}
	@Override
	public void setData(List<Object[]> data){
		this.data = data;
	}
	@Override
	public String build(){
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject		
		try{			
			//chartJson.put("value", data.get(0));
			for( Object[] resultRow : this.data){
				chartJson.put("target", resultRow[0] );
				chartJson.put("value",resultRow[1] );
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return chartJson.toString();
	}
}
