package controller;

import java.sql.Connection;
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
	
	public List<Shift> findAll() throws DataAccessException{
		return shiftDAO.findAll();
	}
	
	public List<Bar> getAllBars() throws DataAccessException {
		return barCtrl.getAll();
	}
	
	public List<Doorman> getAvailableDoormenForShift(LocalDate localDate, int barId) throws DataAccessException {
		return doormanCtrl.getAvailableDoormenForShift(localDate, barId);
	}
	  
	public boolean confirmShift(int doormanId, int shiftId, LocalDate date) throws DataAccessException, SQLException {
		boolean confirmation = false;
		try {
			
			DBConnection con = DBConnection.getInstance();
			
			con.startTransaction();
			con.setIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED);
			
			shiftDAO.updateDoormanId(shiftId, doormanId);
			availableDateCtrl.deleteAvailableDate(doormanId, date);
			DBConnection.getInstance().commitTransaction();
			confirmation = true;
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return confirmation;
	}
}
