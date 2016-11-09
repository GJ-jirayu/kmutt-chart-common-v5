package th.ac.kmutt.chart.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONNECTION_DATASOURCE", schema = "", catalog = "CHART_DB")
public class DatasourceConnectionEntity {
	
	private Integer connId;
    private String connName;
    private String connString;
    private String username;
    private String password;
    private String dialect;
    private String driverClass;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CONN_ID")
	public Integer getConnId() {
		return connId;
	}
	public void setConnId(Integer connId) {
		this.connId = connId;
	}
	@Basic
	 @Column(name = "CONN_NAME")
	public String getConnName() {
		return connName;
	}
	public void setConnName(String connName) {
		this.connName = connName;
	}
	
	 @Basic
	 @Column(name = "CONN_URL")
	public String getConnString() {
		return connString;
	}
	public void setConnString(String cstring) {
		this.connString = cstring;
	}
	
	 @Basic
	 @Column(name = "CONN_USERNAME")
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	 @Basic
	 @Column(name = "CONN_PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 @Basic
	 @Column(name = "CONN_HIBERNATE_DIALECT")
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	} 
	 @Basic
	 @Column(name = "DRIVER_CLASS")
    public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
   
}
