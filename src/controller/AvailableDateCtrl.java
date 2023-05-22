package controller;

import java.time.LocalDate;

import database.AvailableDateDAO;
import database.DataAccessException;

public class AvailableDateCtrl {
	private AvailableDateDAO availabledatedao;
	private LocalDate availableDate;
	private boolean isAvailable;

	public AvailableDateCtrl() throws DataAccessException{
	    try {
	    	availabledatedao = new AvailableDateDAO();
	    } catch (Exception e) {
	    	throw new DataAccessException(e, "Unable to create availableDateDAO");
	    }
	}
	
	public LocalDate createAvailableDates(LocalDate localdate, int doormanId) throws DataAccessException {
		return new AvailableDate(localDate, doormanId);;
		
	}
	
	public boolean confirmAvailability(LocalDate availableDate) throws DataAccessException {
		return isAvailable;
		
	}


}