package th.ac.kmutt.chart.dwh.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="FACT_INBOUND_OUTBOUND_STUDENT")
public class InboundOutboundStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACADEMIC_YEAR")
	private Integer academicYear;

	@Column(name="PROGRAM_KEY")
	private Integer programKey;
	
	@Column(name="FIELD_KEY")
	private Integer fieldKey;

	@Column(name="NO_OF_STUDENT")
	private BigDecimal noOf;
	
	@Column(name="FACULTY_KEY")
	private Integer facultyKey;
	
	public InboundOutboundStudent() {
		
	}
	
	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	public Integer getProgramKey() {
		return programKey;
	}

	public void setProgramKey(Integer programKey) {
		this.programKey = programKey;
	}

	public Integer getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(Integer fieldKey) {
		this.fieldKey = fieldKey;
	}

	public BigDecimal getNoOf() {
		return noOf;
	}

	public void setNoOf(BigDecimal noOf) {
		this.noOf = noOf;
	}

	public Integer getFacultyKey() {
		return facultyKey;
	}

	public void setFacultyKey(Integer facultyKey) {
		this.facultyKey = facultyKey;
	}


	
}