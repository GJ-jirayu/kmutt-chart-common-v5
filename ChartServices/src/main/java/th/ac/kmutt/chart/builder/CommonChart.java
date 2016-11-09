package th.ac.kmutt.chart.builder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.liferay.portal.kernel.json.JSON;


public class CommonChart implements Chart {
	protected String structureFileName = "StructureFileName";
	protected String chartJsonString;
	protected JSONObject chartJson;
	
	public CommonChart(){
		loadResource();
		createJson();
	}
	public CommonChart(String resourceName){
		this.structureFileName = resourceName;
		loadResource();
		createJson();
	}
	public JSONObject getChartJson(){
		return this.chartJson;
	}
	public String getChartJsonString(){
		return this.chartJsonString;
	}
	public void setTemplate(String ChartJsonString){
		 this.chartJsonString = ChartJsonString;
		 this.createJson();
	}
	public boolean loadResource() { // set Json String from filte
		try{
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(this.structureFileName), "UTF-8"));
			for (int c = br.read(); c != -1; c = br.read()){ 
				sb.append((char)c);
			}
			this.chartJsonString = sb.toString();
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	public void createJson() {
		JSONObject chartJson = new JSONObject();
		try {
			chartJson = new JSONObject(this.chartJsonString);
		} catch (Exception e) {
			chartJson  = new JSONObject();
		}
		this.chartJson = chartJson; 
	}
	@Override
	public void setData(List<Object[]> data) {
		// TODO Auto-generated method stub
	}
	@Override
	public String build() {
		// TODO Auto-generated method stub
		return null;
	}
}
