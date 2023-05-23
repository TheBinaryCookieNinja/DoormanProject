package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.AvailableDateDAO;
import database.DBConnection;
import database.DataAccessException;
import model.AvailableDate;

public class AvailableDateCtrl {
	private AvailableDateDAO adDAO;

	public AvailableDateCtrl() throws DataAccessException{
	    try {
	    	adDAO = new AvailableDateDAO();
	    } catch (Exception e) {
	    	throw new DataAccessException(e, "Unable to create availableDateDAO");
	    }
	}
	
	public AvailableDate createAvailableDates(LocalDate localdate, int doormanId) throws DataAccessException {
		String localDateString = localdate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		return new AvailableDate(0, localDateString, doormanId);
		
	}
	
	public boolean confirmAvailability(AvailableDate availableDate) throws DataAccessException, SQLException {
		boolean succes = false;
		DBConnection.getInstance().startTransaction();
		try {
			adDAO.createAvailableDate(availableDate);
			DBConnection.getInstance().commitTransaction();
			succes = true;
		}catch(Exception e) {
			DBConnection.getInstance().rollbackTransaction();
		}
		return succes;
	}
	
	public void deleteAvailableDate(int doormandId) throws SQLException {
		adDAO.deleteAvailableDate(doormandId);
	}


}