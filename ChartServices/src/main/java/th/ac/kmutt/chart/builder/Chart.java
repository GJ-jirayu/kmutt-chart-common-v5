package th.ac.kmutt.chart.builder;

import java.util.List;

import org.json.JSONException;

public interface Chart {
	public void setData(List<Object[]> data);
	public String build();
}
