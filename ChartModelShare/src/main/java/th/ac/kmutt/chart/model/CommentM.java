package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("CommentM")
public class CommentM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private String instanceId;
    private String comment;
    private ChartInstanceM chartInstance;
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getComment() {
        return comment;
    }

    public ChartInstanceM getChartInstance() {
        return chartInstance;
    }

    public void setChartInstance(ChartInstanceM chartInstance) {
        this.chartInstance = chartInstance;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
