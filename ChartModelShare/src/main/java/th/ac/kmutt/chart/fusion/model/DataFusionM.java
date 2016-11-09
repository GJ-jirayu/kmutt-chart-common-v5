package th.ac.kmutt.chart.fusion.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by imake on 19/10/2015.
 */
@XStreamAlias("data")
public class DataFusionM  implements Serializable {
    private String label;
    private String value;
    private String tooltext;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTooltext() {
        return tooltext;
    }

    public void setTooltext(String tooltext) {
        this.tooltext = tooltext;
    }
}
