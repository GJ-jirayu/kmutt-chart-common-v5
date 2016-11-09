package th.ac.kmutt.chart.dwh.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="HR_FACT_EMPLOYEE")
public class HrEmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EMP_KEY")
	private Integer empKey;
	
	@Column(name="DEPARTMENT_KEY")
	private Integer departmentKey;

	@Column(name="NATIONALITY_KEY")
	private Integer nationalityKey;
	
	@Column(name="MONTH_KEY")
	private Integer monthKey;
	
	
	public HrEmployeeEntity() {
		
	}


	public Integer getEmpKey() {
		return empKey;
	}


	public void setEmpKey(Integer empKey) {
		this.empKey = empKey;
	}


	public Integer getDepartmentKey() {
		return departmentKey;
	}


	public void setDepartmentKey(Integer departmentKey) {
		this.departmentKey = departmentKey;
	}


	public Integer getNationalityKey() {
		return nationalityKey;
	}


	public void setNationalityKey(Integer nationalityKey) {
		this.nationalityKey = nationalityKey;
	}


	public Integer getMonthKey() {
		return monthKey;
	}


	public void setMonthKey(Integer monthKey) {
		this.monthKey = monthKey;
	}
	
}