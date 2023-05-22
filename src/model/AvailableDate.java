package model;

public class AvailableDate {
	  private int availableDateId;
	  private String calendarDate;
	  private int employeeId;

	    public AvailableDate(int availableDateId, String calendarDate, int employeeId) {
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

		public String getCalendarDate() {
			return calendarDate;
		}

		public void setCalendarDate(String calendarDate) {
			this.calendarDate = calendarDate;
		}

		public int getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}

	    
	    
	}

