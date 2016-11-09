package th.ac.kmutt.chart.form;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import th.ac.kmutt.chart.model.ConnectionM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.ServiceM;

public class ConnectionForm extends CommonForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7699215072795034119L;
	private ConnectionM model;
	private Set<ConnectionM> conns;
	public ConnectionM getModel() {
		return model;
	}
	public void setModel(ConnectionM model) {
		this.model = model;
	}
	public Set<ConnectionM> getConns() {
		return conns;
	}
	public void setConns(Set<ConnectionM> conns) {
		this.conns = conns;
	}

	
	
}
