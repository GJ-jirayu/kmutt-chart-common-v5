package th.ac.kmutt.chart.builder;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Table extends CommonChart implements Chart {
	List<Object[]> data;
	public Table(){
		super("TableWithGroup");  
	}
	@Override
	public void setData(List<Object[]> data){
		this.data = data;
	}
	@Override
	public String build() {
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject
		List<Object[]> dat = this.data;
		try{
			JSONArray dataSetJson = new  JSONArray();
			if(dat!=null){
				for( Object[] resultRow : dat){
					JSONObject dataSetObj = new JSONObject();
					JSONArray datas =new JSONArray();
					for(Object cell : resultRow){
						JSONObject attr = new JSONObject();
						attr.put("value", cell.toString());
						datas.put(attr);
					}
					dataSetObj.put("data", datas);
					dataSetJson.put(dataSetObj);
				}
				chartJson.put("dataset", dataSetJson);
			}
		}catch(Exception ex){
			System.out.println("exception build table :"+ex.toString());
		}
		return chartJson.toString();
	}
}
