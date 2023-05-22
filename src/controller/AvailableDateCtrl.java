package controller;

import java.time.LocalDate;

import database.DataAccessException;
import database.DoormanDAO;

public class AvailableDateCtrl {
private AvailableDateDAO availabledatedao;
private LocalDate availableDate;
private boolean isAvailable;



public AvailableDateCtrl() throws DataAccessException {
    try {
    	availabledatedao = new AvailableDateDAO();
    } catch (Exception e) {
    	throw new DataAccessException(e, "Unable to create availabledateDAO");
}
    
    
}
private LocalDate createAvailableDates(LocalDate localdate, int doormanId) throws DataAccessException {
	return availableDate;
	
}
public boolean confirmAvailability(LocalDate availableDate) throws DataAccessException {
	return isAvailable;
	
}


}