package model;

import java.sql.Date;

public class AvailableDate {
	private int availableDateId;
	private Date calendarDate;
	private int employeeId;
	private int version;

	public AvailableDate(int availableDateId, Date calendarDate, int employeeId) {
		this.availableDateId = availableDateId;
		this.calendarDate = calendarDate;
		this.employeeId = employeeId;
	}

	public int getAvailableDateId() {
		return availableDateId;
	}

	public void setAvailableDateId(int availableDateId) {
		this.availableDateId = availableDateId;
	}

	public Date getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarDate(Date calendarDate) {
		this.calendarDate = calendarDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
