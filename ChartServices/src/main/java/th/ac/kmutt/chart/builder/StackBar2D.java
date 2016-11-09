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

public class StackBar2D extends CommonChart implements Chart {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);

	List<Object[]> data;

	public StackBar2D() {
		super("StackBar2D");
	}

	@Override
	public void setData(List<Object[]> data) {
		this.data = data;
	}

	@Override
	public String build() {
		JSONObject chartJson = super.getChartJson(); // retriveJSONObject
		try {

			/*
			 * 1.Generate json category ทำการสรา้ง JSON ในส่วนของ Category
			 * และสร้าง Unique Category โดยการเอาข้อมูลทั้งหมดมา loop ใส่ list
			 * และดู loop รอบถัด ๆ ไปว่ามีซำกับ list หรือเปล่า ถ้าไม่มีก็ put
			 * ใส่ list ถ้ามีก็ข้ามไป
			 */
			JSONArray categoryJson = new JSONArray();
			ArrayList<Object> categoryList = new ArrayList<Object>();
			ArrayList<Object> uniqueCategoryList = new ArrayList<Object>();
			for (Object[] resultRow : this.data) {
				if (!categoryList.contains(resultRow[0])) {
					uniqueCategoryList.add(resultRow[0]);
					JSONObject attr = new JSONObject();
					attr.put("label", resultRow[0]);
					categoryJson.put(attr);
				}
				categoryList.add(resultRow[0]);
			}
			JSONObject jsonObjCategory = new JSONObject().put("category", categoryJson);
			JSONArray jsonArrCategory = new JSONArray().put(jsonObjCategory);
			// logger.info("\n -- jsonArrCategory --> "+jsonArrCategory+"\n");

			/* 2. Generate json Series object */
			JSONArray seriesJson = new JSONArray();
			ArrayList<Object> seriesList = new ArrayList<Object>();
			ArrayList<Object> uniqueSeriesList = new ArrayList<Object>();
			for (Object[] resultRow : this.data) {
				if (!seriesList.contains(resultRow[1])) {
					uniqueSeriesList.add(resultRow[1]);
					JSONObject attr = new JSONObject();
					attr.put("seriesname", resultRow[1]);
					seriesJson.put(attr);
				}
				seriesList.add(resultRow[1]);
			}
			// logger.info("\n -- seriesJson --> "+seriesJson+"\n");

			/*
			 * 3. Generate dataset - data ที่ต้องการจะมันจะแบ่ง object
			 * ตามข้อมุลของ series ตาม category เลยต้องเอา series มาวนลูบ
			 * เลยทำการวนลูบ series และตามด้วย category และสร้าง object value
			 * ของแต่ละ series, category นั้น ๆ - เนืองจาก data ของ category
			 * อาจจะไม่มีทุก category เลยต้องทำการสร้าง dummy value ให้กับ
			 * object เพื่อไม่ให้มันมีข้อมูลว่าง
			 */
			int index = 0;
			for (Object resultUniqueSeries : uniqueSeriesList) {
				JSONArray objectData = new JSONArray();
				for (Object uniqueCategory : uniqueCategoryList) {
					Boolean checkNoneValue = Boolean.FALSE;
					for (Object[] resultData : this.data) {
						if (resultData[1].equals(resultUniqueSeries) 
								&& resultData[0].equals(uniqueCategory)) {
							JSONObject attr = new JSONObject();
							attr.put("value", resultData[2]);
							objectData.put(attr);

							checkNoneValue = Boolean.TRUE;
						}
					}

					if (checkNoneValue.equals(Boolean.FALSE)) {
						JSONObject attr = new JSONObject();
						attr.put("value", "0");
						objectData.put(attr);
					}
				}

				seriesJson.getJSONObject(index).put("data", objectData);
				index++;
			}

			/* ทำการ put ข้อมูล json ที่ build ได้ใส่ลงไปใน main json */
			chartJson = chartJson.put("categories", jsonArrCategory).put("dataset", seriesJson);
			logger.info("\n" + chartJson + "\n");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return chartJson.toString();
	}
}
