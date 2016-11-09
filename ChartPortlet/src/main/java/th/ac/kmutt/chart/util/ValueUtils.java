package th.ac.kmutt.chart.util;

public class ValueUtils {
	
		public static String  toJson(String value){
			if(value!=null && value.isEmpty()){
				return value;
			}else{
				return "";
			}
		}
		public static String  toJson(Integer value){
			if(value!=null ){
				return String.valueOf(value);
			}else{
				return "";
			}
		}
		
}
