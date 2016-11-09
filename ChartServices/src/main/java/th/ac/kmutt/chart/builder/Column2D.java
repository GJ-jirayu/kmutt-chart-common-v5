package th.ac.kmutt.chart.builder;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Column2D extends CommonChart implements Chart {
	List<Object[]> data;
	public Column2D(){
		super("Column2D");  
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
		}catch(Exception ex){
			
		}
		return chartJson.toString();
	}
}
