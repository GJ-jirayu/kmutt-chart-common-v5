package th.ac.kmutt.chart.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import th.ac.kmutt.chart.constant.ServiceConstant;


public class BoxAndWhisker2DTemporary extends CommonChart implements Chart {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	
	List<Object[]> data;
	public BoxAndWhisker2DTemporary(){
		super("BoxAndWhisker2DTemporary");  
	}
	@Override
	public void setData(List<Object[]> data){
		this.data = data;
	}
	
	@Override
	public String build(){
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject	
		try{			
			/* Generate new category */
			JSONArray categoryJson = new  JSONArray();
			List<String> makeCategories = new ArrayList<String>();
			for( Object[] resultRow : this.data){
				makeCategories.add(resultRow[0].toString());
			}
			Set<String> setUniqueCategory = new TreeSet<String>(makeCategories);
			List<Object> UniqueCategory = new ArrayList<Object>(setUniqueCategory);
			Object[] objUniqueCategory = UniqueCategory.toArray();
			
			JSONObject jsonObjCategory = new  JSONObject();
			JSONArray jsonArrCategory = new JSONArray();
			for( Object resultRow : objUniqueCategory){
				JSONObject attr = new JSONObject();
				attr.put("label", resultRow);
				categoryJson.put(attr);
			}
			jsonObjCategory = jsonObjCategory.put("category", categoryJson);
			jsonArrCategory = jsonArrCategory.put(jsonObjCategory);
			
			
			/* Generate new Series */
			List<String> makeSeries = new ArrayList<String>();
			for( Object[] resultRow : this.data){
				makeSeries.add(resultRow[1].toString());
			}
			Set<String> setUniqueSeries= new TreeSet<String>(makeSeries);
			List<Object> UniqueSeries = new ArrayList<Object>(setUniqueSeries);
			Object[] objUniqueSeries = UniqueSeries.toArray();
			
			
			/* Generate Data set */
			JSONObject tempDataSet = new JSONObject();	
			
			JSONArray objectIndex = new JSONArray();
			for( Object resultUniqueSeries : objUniqueSeries){
				
				/* Put seriesname to JSON */
				JSONObject objectseries = new JSONObject();
				objectseries.put("seriesname", resultUniqueSeries);
				//objectseries.put("lowerboxcolor", "#0075c2");	
				//objectseries.put("upperboxcolor", "#1aaf5d");	

				/*Put Data set to JSON*/
				JSONArray objectData = new JSONArray();
				for( Object[] resultData : this.data){									
					if(resultData[1].equals(resultUniqueSeries)){
						JSONObject attr = new JSONObject();	
						attr.put("value",resultData[2] );
						objectData.put(attr);
					}					
				}
				objectseries.put("data", objectData);
							
				
				objectIndex.put(objectseries);
			}
			
			//tempDataSet = tempDataSet.put("dataset", objectIndex);		
			
			chartJson = chartJson.put("categories", jsonArrCategory).put("dataset", objectIndex);
			logger.info("\n"+chartJson+"\n");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return chartJson.toString();
	}
}
