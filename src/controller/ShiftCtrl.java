package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import database.DBConnection;
import database.DataAccessException;
import database.ShiftDAO;
import model.Bar;
import model.Doorman;
import model.Shift;

public class ShiftCtrl  {
	private ShiftDAO shiftDAO;
	private BarController barCtrl;
	private DoormanCtrl doormanCtrl;
	private Shift shift;
	private int barId;
	
	
	
	public ShiftCtrl() throws DataAccessException {
		try {
			shiftDAO = new ShiftDAO();
		} catch (Exception e) {
			throw new DataAccessException(e, "Can't create shiftDAO");
		}
		barCtrl = new BarController();
		doormanCtrl = new DoormanCtrl();
	}

	 /**
     * Get shifts by date.
     *
     * @param date the date to get shifts for
     * @return list of shifts for the specified date
     */
	public List<Shift> getShiftsByDate(LocalDate date) throws DataAccessException {
//		// Convert java.util.Date to java.time.LocalDate
//		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
	
	public List<Doorman> findAllDoormen() throws DataAccessException {
		return doormanCtrl.findAll();
	}
	
	public List<Doorman> getAvailableDoormenForShift(LocalDate localDate, int barId) throws DataAccessException {
	   // return doormanCtrl.getAvailableDoormenForShift(LocalDate.of(2023, 05, 15), 1);
		return doormanCtrl.getAvailableDoormenForShift(localDate, barId);
	}
	  
	public boolean confirmShift(int doormanId) throws DataAccessException, SQLException {
		boolean confirmation = false;
		try {
			DBConnection.getInstance().startTransaction();
			shiftDAO.updateDoormanId(shift, doormanId);
			doormanCtrl.deleteDoorman(doormanId);
			DBConnection.getInstance().commitTransaction();
			confirmation = true;
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return confirmation;
	}
	
	public void setBarId(int barId) {
		this.barId = barId;
	}
}
