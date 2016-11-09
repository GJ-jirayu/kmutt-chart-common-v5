package th.ac.kmutt.chart.dwh.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.Spring;
@Entity
@Table(name="DimField")
public class DimField implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FIELD_KEY")
	private Integer fieldKey;
	
	@Column(name="FIELD_CODE")
	private String fieldCode;
	
	@Column(name="FIELD_NAME")
	private String fieldName;
	
	@Column(name="DEPARTMENT_CODE")
	private String departmenCode;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmenName;
	
	@Column(name="FACULTY_CODE")
	private String facultyCode;
	
	@Column(name="FACULTY_NAME")
	private String facultyName;
	
	
	public DimField() {
		
	}
	
	public Integer getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(Integer fieldKey) {
		this.fieldKey = fieldKey;
	}

	
	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDepartmenCode() {
		return departmenCode;
	}

	public void setDepartmenCode(String departmenCode) {
		this.departmenCode = departmenCode;
	}
	public String getDepartmenName() {
		return departmenName;
	}

	public void setDepartmenName(String departmenName) {
		this.departmenName = departmenName;
	}
	
	public String getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}
	
	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	
}