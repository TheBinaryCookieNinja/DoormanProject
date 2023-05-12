package controller;

import java.sql.Date;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import database.DataAccessException;
import database.ShiftDAO;
import model.Doorman;
import model.Shift;

public class ShiftCtrl  {
	private ShiftDAO shiftDAO;
	
	
	
	
	public ShiftCtrl() throws DataAccessException {
		try {
			shiftDAO = new ShiftDAO();
		} catch (Exception e) {
			throw new DataAccessException(e, "Can't create shiftDAO");
		}
	}

	 /**
     * Get shifts by date.
     *
     * @param date the date to get shifts for
     * @return list of shifts for the specified date
     */
	public List<Shift> getShiftsByDate(java.util.Date date) throws DataAccessException {
		// Convert java.util.Date to java.time.LocalDate
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return shiftDAO.getShiftsByDate(localDate);
		
	}
	
	public Shift getShiftById(int shiftId) throws DataAccessException{
		return shiftDAO.findById(shiftId);
	}
	
	
	
	public List<Shift> findAll() throws DataAccessException{
		return shiftDAO.findAll();
	}
}
