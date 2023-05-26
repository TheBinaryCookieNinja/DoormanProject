package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.AvailableDateDAO;
import database.DBConnection;
import database.DataAccessException;
import model.AvailableDate;

public class AvailableDateCtrl {
	private AvailableDateDAO availableDateDAO;
	

	public AvailableDateCtrl() throws DataAccessException {
		try {
			availableDateDAO = new AvailableDateDAO();
		} catch (Exception e) {
			throw new DataAccessException(e, "Unable to create availableDateDAO");
		}
	}

	public boolean confirmAvailability(LocalDate localdate, int doormanId) throws DataAccessException, SQLException {
		AvailableDate availableDate = new AvailableDate(0, Date.valueOf(localdate), doormanId);
		boolean succes = false;
		DBConnection.getInstance().startTransaction();
		try {
			availableDateDAO.createAvailableDate(availableDate);
			DBConnection.getInstance().commitTransaction();
			succes = true;
		} catch (Exception e) {
			DBConnection.getInstance().rollbackTransaction();
		}
		return succes;
	}

//	public boolean isAvailabilityRegistered(int doormanId, LocalDate selectedDate) throws DataAccessException, SQLException {
//		AvailableDate availableDate = adDAO.findById(doormanId);
//		if(availableDate != null) {
//			LocalDate registeredDate = availableDate.getCalendarDate().toLocalDate();
//			return registeredDate.isEqual(selectedDate);
//		}
//		return false;
//	}
	
	public boolean isAvailabilityRegistered(int doormanId, LocalDate selectedDate) throws DataAccessException, SQLException {
		DBConnection.getInstance().startTransaction();
		try {
	        AvailableDate availableDate = availableDateDAO.findByDoormanIdAndDate(doormanId, selectedDate);
	        DBConnection.getInstance().commitTransaction();
	        return availableDate != null;
	    } catch (SQLException e) {
	    	DBConnection.getInstance().rollbackTransaction();
	        throw new DataAccessException(e, "Can't register on a date that you are already registered on - please try another date 😀");
	    }
	}

}
