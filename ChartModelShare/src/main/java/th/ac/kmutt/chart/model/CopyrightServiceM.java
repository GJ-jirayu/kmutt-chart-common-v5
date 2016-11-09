package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("CopyrightServiceM")
public class CopyrightServiceM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer type;
    private Integer year;
    private Integer month;
    private Integer value;
    private String monthDesc;
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMonthDesc() {
        return monthDesc;
    }

    public void setMonthDesc(String monthDesc) {
        this.monthDesc = monthDesc;
    }
    
}
