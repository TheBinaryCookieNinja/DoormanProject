package model;

public class Shift {
	  private int shiftId;
	    private String shiftDate;
	    private String checkInTime;
	    private String checkOutTime;

	    public Shift(int shiftId, String shiftDate, String checkInTime, String checkOutTime) {
	        this.shiftId = shiftId;
	        this.shiftDate = shiftDate;
	        this.checkInTime = checkInTime;
	        this.checkOutTime = checkOutTime;
	    }

	   
	    public int getShiftId() {
	        return shiftId;
	    }

	    public void setShiftId(int shiftId) {
	        this.shiftId = shiftId;
	    }

	
	    public String getShiftDate() {
	        return shiftDate;
	    }

	    public void setShiftDate(String shiftDate) {
	        this.shiftDate = shiftDate;
	    }

	 
	    public String getCheckInTime() {
	        return checkInTime;
	    }

	    public void setCheckInTime(String checkInTime) {
	        this.checkInTime = checkInTime;
	    }

	 
	    public String getCheckOutTime() {
	        return checkOutTime;
	    }

	    public void setCheckOutTime(String checkOutTime) {
	        this.checkOutTime = checkOutTime;
	    }
	}

