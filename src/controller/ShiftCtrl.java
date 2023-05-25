package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import database.DBConnection;
import database.DataAccessException;
import database.ShiftDAO;
import model.AvailableDate;
import model.Bar;
import model.Doorman;
import model.Shift;

public class ShiftCtrl  {
	private ShiftDAO shiftDAO;
	private BarCtrl barCtrl;
	private DoormanCtrl doormanCtrl;
	private AvailableDateCtrl availableDateCtrl;
	
	
	
	public ShiftCtrl() throws DataAccessException {
		try {
			shiftDAO = new ShiftDAO();
		} catch (Exception e) {
			throw new DataAccessException(e, "Can't create shiftDAO");
		}
		barCtrl = new BarCtrl();
		doormanCtrl = new DoormanCtrl();
		availableDateCtrl = new AvailableDateCtrl();
	}

	 /**
     * Get shifts by date.
     *
     * @param date the date to get shifts for
     * @return list of shifts for the specified date
     */
	public List<Shift> getShiftsByDate(LocalDate date) throws DataAccessException {
//		// Convert java.util.Date to java.time.LocalDate
        return shiftDAO.getShiftsByDate(date);
	}
	
	public Shift getShiftById(int shiftId) throws DataAccessException{
		return shiftDAO.findById(shiftId);
	}
	
	public List<Shift> findAll() throws DataAccessException{
		return shiftDAO.findAll();
	}
	
	public Bar findBarById(int barId) throws DataAccessException {
		return barCtrl.findById(barId);
	}
	
	public List<Doorman> getAvailableDoormenForShift(LocalDate localDate, int barId) throws DataAccessException {
		return doormanCtrl.getAvailableDoormenForShift(localDate, barId);
	}
	  
	public boolean confirmShift(int doormanId, int shiftId) throws DataAccessException, SQLException {
		boolean confirmation = false;
		try {
			DBConnection.getInstance().startTransaction();
			shiftDAO.updateDoormanId(shiftId, doormanId);
			availableDateCtrl.deleteAvailableDate(doormanId);
			DBConnection.getInstance().commitTransaction();
			confirmation = true;
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return confirmation;
	}
}
