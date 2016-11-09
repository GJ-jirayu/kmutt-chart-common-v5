package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("InBoundOutBoundServiceM")
public class InBoundOutBoundServiceM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer fieldKey;
    private Integer programKey;
    private Integer semesterKey;
    private BigDecimal noOf;
    private Integer noOfInter;
    private Integer noOfAll;
    private Integer academicYear;
    private Integer facultyKey;
    private Integer empKey;
    private Integer departmentKey;
    private Integer nationalityKey;
    private Integer monthKey;
    private String facultyCode;
    private String departmentCode;
    private String shortfaculty ;
    public InBoundOutBoundServiceM (){
    	
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
	
	public String getShortFaculty() {
		return shortfaculty;
	}
	public void setShortFaculty(String shortfaculty) {
		this.shortfaculty = shortfaculty;
	}
	
	public BigDecimal getNoOf() {
		return noOf;
	}
	public void setNoOf(BigDecimal BigDecimal) {
		this.noOf = BigDecimal;
	}
	
	public Integer noOfInter() {
		return noOfInter;
	}
	public void setnoOfInter(Integer Integer) {
		this.noOfInter = Integer;
	}
	
	public Integer noOfAll() {
		return noOfAll;
	}
	public void setnoOfAll(Integer Integer) {
		this.noOfAll = Integer;
	}
	
	public Integer getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}
	public Integer getFacultyKey() {
		return facultyKey;
	}
	public void setFacultyKey(Integer facultyKey) {
		this.facultyKey = facultyKey;
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
	
	public String getFacultyCode() {
		return facultyCode;
	}
	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}
	
	public String getDepartmentCode() {
		return departmentCode;
	}	
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}


}
