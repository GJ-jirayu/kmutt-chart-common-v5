package th.ac.kmutt.chart.builder;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class RealTimeAngular extends CommonChart implements Chart {
	List<Object[]> data;
	public RealTimeAngular(){
		super("RealTimeAngular");
	}
	@Override
	public void setData(List<Object[]> data){
		this.data = data; 
	}
	@Override
	public String build() {
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject
		//convert handle values
		Object value = data.size()>0? data.get(0)  : "0" ;
		try{
			JSONObject dials = chartJson.getJSONObject("dials");
			JSONArray da = dials.getJSONArray("dial");
			((JSONObject)da.get(0)).put("value", value);
		}catch(IndexOutOfBoundsException ex){
			System.out.println("#Exception of RealTimeAngular buider [ Nodata ]");
		}catch(Exception ex){
			System.out.println("#Exception of RealTimeAngular buider => "+ex.getMessage());
		}
		return chartJson.toString();
	}
}
