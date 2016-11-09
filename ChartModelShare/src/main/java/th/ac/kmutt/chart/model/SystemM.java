package th.ac.kmutt.chart.model;

import java.io.Serializable;

import th.ac.kmutt.chart.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("SystemM")
public class SystemM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    String name = "echo SystemM";
    String conName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
    
}
