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
@Table(name="DimFaculty")
public class DimFaculty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FACULTY_KEY")
	private Integer facultyKey;
	
	@Column(name="FACULTY_CODE")
	private String facultyCode;
	
	@Column(name="FACULTY_NAME")
	private String facultyName;
	
	
	@Column(name="FACULTY_NAME_INITIAL")
	private String facultyShortName;
	
	public DimFaculty() {
		
	}
	
	public Integer getFacultyKey() {
		return facultyKey;
	}

	public void setFacultyKey(Integer facultyKey) {
		this.facultyKey = facultyKey;
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