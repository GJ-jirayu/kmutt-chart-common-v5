package th.ac.kmutt.chart.builder;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Bar extends CommonChart implements Chart {
	List<Object[]> data;
	public Bar(){
		super("Bar");  
	}
	@Override
	public void setData(List<Object[]> data){
		this.data = data;
	}
	@Override
	public String build() {
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject
		try{
			JSONArray dataJson = new  JSONArray();
			for( Object[] resultRow : this.data){
				JSONObject attr = new JSONObject();
				attr.put("label", resultRow[0] );
				attr.put("value",resultRow[1] );
				dataJson.put(attr);
			}
			chartJson.put("data", dataJson);
		}catch(JSONException ex){
			System.out.println("found error at:"+ex.getMessage());
		}
		return chartJson.toString();
	}
}
