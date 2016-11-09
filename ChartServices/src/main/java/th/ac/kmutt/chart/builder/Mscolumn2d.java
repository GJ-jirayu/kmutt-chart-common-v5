package th.ac.kmutt.chart.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;


public class Mscolumn2d extends CommonChart implements Chart {
	List<Object[]> data;
	public Mscolumn2d(){
		super("Mscolumn2d");  
	}
	@Override
	public void setData(List<Object[]> data){
		this.data = data;
	}
	@Override
	public String build() {
	// format	's1', 'A,B,C,D,E', '45,200,2,2,2'
	//	's2', 'A,B,C', '10,20,30'
		List<Object[]> data = this.data;
		HashSet<String> categorySet = new LinkedHashSet<String>();
		Set<String> seriesSet = new   LinkedHashSet<String>();
		List<String> categorys = new ArrayList<>();
		List<String> series = new ArrayList<>();
		Map<String,Map<String,String>> valueMapper = new HashMap<String,Map<String,String>>();
		try{
			// Step 1.build list category and series   
			for(  Object[] row : data ){
				seriesSet.add((String)row[0]); 	//create unique series  ** no reorder **
				String[] lineCates =  ((String)row[1]).split(","); 	//create unique categorys    ** no reorder **
				for( String lineCate : lineCates){
					categorySet.add(lineCate);
				}
			}
			categorys = new ArrayList<String>(categorySet);
			series = new ArrayList<String>(seriesSet);
			// Step 2. build valueList Map with category and series default value 0
			for(  Object[] row : data ){
				Map<String,String> mapValue = new HashMap<String,String>();
				List<String> cateItem = new ArrayList<String>(  Arrays.asList( ((String)row[1]).split(","))  ) ; 
				List<String> valueItem = new ArrayList<String>(  Arrays.asList( ((String)row[2]).split(","))  ) ; 
				for( String category : categorys){
					Integer index = cateItem.indexOf(category); // min 0  not exist return -1
					String vitem = index>=0 && index<cateItem.size() ?  valueItem.get(index)  :"0"  ;
					mapValue.put(category, vitem);
				}
				valueMapper.put((String)row[0], mapValue);
			}
			//Step 3. build Chart Json
			JSONObject chartJson = super.getChartJson();
			//create categorys json
			JSONArray categoryJson = new JSONArray();
			JSONObject category = new JSONObject();
			JSONArray categoryList = new JSONArray();
			for(String readCategory : categorys){
				JSONObject categoryProp = new JSONObject();
				categoryProp.put("label",readCategory );
				categoryList.put(categoryProp);
			}
			category.put("category",categoryList);
			categoryJson.put(category);
			chartJson.put("categorys", categoryJson);
			// create dataset json
			JSONArray datasets = new JSONArray();
			for( String readSeries : series ){
				JSONObject dataset = new JSONObject();
				dataset.put("seriesname", readSeries);
				JSONArray datas = new JSONArray();
				Map<String,String>  serieEntry =  valueMapper.get(readSeries);
				for(  String readCategory : categorys){
 					JSONObject dataJson = new JSONObject();
					dataJson.put("value", serieEntry.get(readCategory));
					datas.put(dataJson);
				}
				dataset.put("data", datas);
				datasets.put(dataset);
			}
			chartJson.put("dataset", datasets);
			return chartJson.toString();		
		}catch(Exception ex){
			return "";
		}
	}
}
