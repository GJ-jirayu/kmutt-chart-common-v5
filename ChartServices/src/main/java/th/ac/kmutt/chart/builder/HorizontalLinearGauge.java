package th.ac.kmutt.chart.builder;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class HorizontalLinearGauge extends CommonChart implements Chart {
	List<Object[]> data;
	public HorizontalLinearGauge(){
		super("HLinearGauge");
	}
	@Override
	public void setData(List<Object[]> data){
		this.data = data; 
	}
	@Override
	public String build() {
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject
		try{
			JSONObject dials = chartJson.getJSONObject("pointers");
			JSONArray da = dials.getJSONArray("pointer");
			((JSONObject)da.get(0)).put("value", data.get(0));
		}catch(Exception ex){
		}
		return chartJson.toString();
	}
}
