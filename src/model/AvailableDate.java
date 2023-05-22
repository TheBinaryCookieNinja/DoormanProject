package model;

public class AvailableDate {
	  private static int localDate;
	  private static String calendarDate;
	  private static int employeeId;

	    public AvailableDate(int availableDateId, String calendarDate, int employeeId) {
	    }

	    public static int getLocalDate() {
	        return localDate;
	    }

	    public void setLocalDate(int localDate) {
	        this.localDate = localDate;
	    }
	    public static String getCalendarDate() {
			return calendarDate;
	    }

	    public void setCalendarDate(String calenderDate) {
	        this.calendarDate = calendarDate;
	    }
	    
	    public static int getEmployeeId() {
	    	return employeeId;
	    }
	    
	    public void setEmployeeId() {
	    	this.employeeId = employeeId;
	    }
	    
	}

