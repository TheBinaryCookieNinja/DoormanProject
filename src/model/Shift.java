package model;

import java.time.LocalTime;

public class Shift {
	  private int shiftId;
	    private String shiftDate;
	    private LocalTime checkInTime;
	    private LocalTime checkOutTime;
		private int barId;
		private int doormanId;

	    public Shift(int shiftId, String shiftDate, LocalTime checkInTime, LocalTime checkOutTime, int barId, int doormanId) {
	        this.shiftId = shiftId;
	        this.shiftDate = shiftDate;
	        this.checkInTime = checkInTime;
	        this.checkOutTime = checkOutTime;
	        this.barId = barId;
	        this.doormanId = doormanId;
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

	 
	    public LocalTime getCheckInTime() {
	        return checkInTime;
	    }

	    public void setCheckInTime(LocalTime checkInTime) {
	        this.checkInTime = checkInTime;
	    }

	 
	    public LocalTime getCheckOutTime() {
	        return checkOutTime;
	    }

	    public void setCheckOutTime(LocalTime checkOutTime) {
	        this.checkOutTime = checkOutTime;
	    }
	    
	    public int getBarId() {
	    	return barId;
	    }
	    
	    public void setBarId(int barId) {
	    	this.barId = barId;
	    }
	    
	    public int getDoormanId() {
	    	return doormanId;
	    }
	    
	    public void setDoormanId(int doormanId) {
	    	this.doormanId = doormanId;
	    }
	}

