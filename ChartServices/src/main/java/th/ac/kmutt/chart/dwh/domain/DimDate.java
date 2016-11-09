package th.ac.kmutt.chart.dwh.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="DimDate")
public class DimDate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DATE_KEY")
	private Integer dateKey;
	
	@Column(name="CALENDAR_DATE")
	private Date calendarDate;
	
	@Column(name="CALENDAR_YEAR")
	private String calendarYear;
	
	@Column(name="CALENDAR_QUARTER")
	private String calenderQuarter;
	
	@Column(name="CALENDAR_MONTH_NO")
	private String calendarMonthNo;
	
	@Column(name="CALENDAR_TH_MONTH_NAME")
	private String calendarThMonthName;
	
	@Column(name="FISCAL_YEAR")
	private String fiscalYear;
	
	@Column(name="FISCAL_MONTH_NO")
	private String fiscalMonthNo;
	
	@Column(name="ACADAMIC_YEAR")
	private String academicYear;
	
	@Column(name="ACADAMIC_MONTH_NO")
	private String academicMonthNo;
	
	public DimDate() {
		
	}
	
	public Integer getDateKey() {
		return dateKey;
	}
	public void setDateKey(Integer dateKey) {
		this.dateKey = dateKey;
	}

	
	public Date getCalendarDate() {
		return calendarDate;
	}
	public void setCalendarDate(Date calendarDate) {
		this.calendarDate = calendarDate;
	}
	
	
	public String getcalendarYear() {
		return calendarYear;
	}
	public void setCalendarYear(String calendarYear) {
		this.calendarYear = calendarYear;
	}
	
	
	public String getCalenderQuarter() {
		return calenderQuarter;
	}

	public void setCalenderQuarter(String calenderQuarter) {
		this.calenderQuarter = calenderQuarter;
	}
	
	
	public String getCalendarMonthNo() {
		return calendarMonthNo;
	}

	public void setCalendarMonthNo(String calendarMonthNo) {
		this.calendarMonthNo = calendarMonthNo;
	}
	
	
	public String getCalendarThMonthName() {
		return calendarThMonthName;
	}
	public void setCalendarThMonthName(String calendarThMonthName) {
		this.calendarThMonthName = calendarThMonthName;
	}
	
	
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	
	
	public String getfiscalMonthNo() {
		return fiscalMonthNo;
	}
	public void setfiscalMonthNo(String fiscalMonthNo) {
		this.fiscalMonthNo = fiscalMonthNo;
	}
	
	
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	
	
	public String getAcademicMonthNo() {
		return academicMonthNo;
	}
	public void setAcademicMonthNo(String academicMonthNo) {
		this.academicMonthNo = academicMonthNo;
	}
	
}