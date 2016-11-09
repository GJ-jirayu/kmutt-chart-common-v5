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
@Table(name="FACT_ALL_STUDENT")
public class AllStudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FIELD_KEY")
	private Integer fieldKey;

	@Column(name="PROGRAM_KEY")
	private Integer programKey;
	
	@Column(name="SEMESTER_KEY")
	private Integer semesterKey;

	@Column(name="NO_OF_STUDENT")
	private Integer noOfStudent;
	
	
	public AllStudentEntity() {
		
	}


	public Integer getFieldKey() {
		return fieldKey;
	}


	public void setFieldKey(Integer fieldKey) {
		this.fieldKey = fieldKey;
	}


	public Integer getProgramKey() {
		return programKey;
	}


	public void setProgramKey(Integer programKey) {
		this.programKey = programKey;
	}


	public Integer getSemesterKey() {
		return semesterKey;
	}


	public void setSemesterKey(Integer semesterKey) {
		this.semesterKey = semesterKey;
	}


	public Integer getNoOfStudent() {
		return noOfStudent;
	}


	public void setNoOfStudent(Integer noOfStudent) {
		this.noOfStudent = noOfStudent;
	}
	
}