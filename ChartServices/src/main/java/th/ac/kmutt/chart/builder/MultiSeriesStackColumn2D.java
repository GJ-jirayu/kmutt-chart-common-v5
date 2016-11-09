package th.ac.kmutt.chart.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.taglibs.standard.functions.Functions;
import org.json.JSONArray;
import org.json.JSONObject;

import th.ac.kmutt.chart.constant.ServiceConstant;


public class MultiSeriesStackColumn2D extends CommonChart implements Chart {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	
	List<Object[]> data;
	public MultiSeriesStackColumn2D(){
		super("MultiSeriesStackColumn2D"); 
	}
	
	@Override
	public void setData(List<Object[]> data){
		this.data = data;
	}
	
	@Override
	public String build(){
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject	
		try{
			/* 1.Generate json category 
			 * ทำการสรา้ง JSON ในส่วนของ Category และสร้าง Unique Category 
			 * โดยการเอาข้อมูลทั้งหมดมา loop ใส่ list และดู loop รอบถัด ๆ ไปว่ามีซำกับ list หรือเปล่า
			 * ถ้าไม่มีก็ put ใส่ list ถ้ามีก็ข้ามไป
			 * */	
			JSONArray categoryJson = new JSONArray();			
			ArrayList<String> categoryList = new ArrayList<String>();
			ArrayList<String> uniqueCategoryList = new ArrayList<String>();
			for( Object[] resultRow : this.data){
				if(!categoryList.contains(resultRow[0].toString())){ 
					uniqueCategoryList.add(resultRow[0].toString());
					JSONObject attr = new JSONObject();
					attr.put("label", resultRow[0]);
					categoryJson.put(attr);
				}
				categoryList.add(resultRow[0].toString());
			}
			JSONObject jsonObjCategory = new JSONObject().put("category", categoryJson);
			JSONArray jsonArrCategory = new JSONArray().put(jsonObjCategory);
			
			
			/* 2.Generate json sub category 
			 * ทำการสรา้ง JSON ในส่วนของ Sub Category และสร้าง Unique Sub Category 
			 * */			
			ArrayList<String> subCategoryList = new ArrayList<String>();
			ArrayList<String> uniqueSubCategoryList = new ArrayList<String>();
			for( Object[] resultRow : this.data){
				if(!subCategoryList.contains(resultRow[1].toString())){ 
					uniqueSubCategoryList.add(resultRow[1].toString());
				}
				subCategoryList.add(resultRow[1].toString());
			}
			
			
			/* 3. Generate json Series object */
			ArrayList<String> seriesList = new ArrayList<String>();
			ArrayList<String> uniqueSeriesList = new ArrayList<String>();
			for( Object[] resultRow : this.data){
				if(!seriesList.contains(resultRow[2].toString())){ 
					uniqueSeriesList.add(resultRow[2].toString());
				}
				seriesList.add(resultRow[2].toString());
			}
			
			
			/* 4. สร้าง Object โดยสร้างตาม Sub Category เพื่อรอรับข้อมูล dataset ที่จะสร้างขึ้นภายหลัง*/
			JSONArray arrSubCategory = new JSONArray();
			for( Object resultUniqueSubCategoryList : uniqueSubCategoryList){
				JSONObject objSubCategory = new JSONObject();
				
				JSONArray datasetArray = new JSONArray();
				for(Object resultUniqueSeries : uniqueSeriesList){
					JSONObject seriesData = new JSONObject();
					seriesData.put("seriesname", resultUniqueSubCategoryList+"-"+resultUniqueSeries);
					JSONArray objectData = new JSONArray();
					
					for (Object uniqueCategory : uniqueCategoryList) {
						Boolean checkNoneValue = Boolean.FALSE;
						for (Object[] resultData : this.data) {
							if (resultData[2].equals(resultUniqueSeries) 
									&& resultData[1].equals(resultUniqueSubCategoryList)
									&& resultData[0].equals(uniqueCategory)) {
								JSONObject attr = new JSONObject();
								attr.put("value", resultData[3]);
								objectData.put(attr);

								checkNoneValue = Boolean.TRUE;
							}
						}
						
						if(checkNoneValue.equals(Boolean.FALSE)){
							JSONObject attr = new JSONObject();
							attr.put("value", 0);
							objectData.put(attr);
						}
					}
					seriesData.put("data", objectData);
					
					datasetArray.put(seriesData);
				}
				
				objSubCategory.put("dataset", datasetArray);		
				
				arrSubCategory.put(objSubCategory);
			}
			chartJson.put("categories", jsonArrCategory);
			chartJson = chartJson.put("dataset", arrSubCategory);
			
			//logger.info("\n -- chartJson --> "+chartJson+"\n");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return chartJson.toString();
	}
	
}
