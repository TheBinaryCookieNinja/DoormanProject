package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.SwingUtilities;

import database.DBConnection;
import database.DataAccessException;
import database.ShiftDAO;
import gui.ShiftCalendarPanel;
import model.AvailableDate;
import model.Bar;
import model.Doorman;
import model.Shift;

public class ShiftCtrl  {
	private ShiftDAO shiftDAO;
	private BarCtrl barCtrl;
	private DoormanCtrl doormanCtrl;
	private AvailableDateCtrl availableDateCtrl;
	private ShiftCalendarPanel shiftCalendarPanel;
	
	
	
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
	
	public List<Bar> getAllBars() throws DataAccessException, SQLException {
		return barCtrl.getAll();
	}
	
	public List<Doorman> getAvailableDoormenForShift(LocalDate localDate, int barId) throws DataAccessException, SQLException {
		return doormanCtrl.getAvailableDoormenForShift(localDate, barId);
	}
	  
	
	 public void setShiftCalendarPanel(ShiftCalendarPanel shiftCalendarPanel) {
	        this.shiftCalendarPanel = shiftCalendarPanel;
	    }

	    public ShiftCalendarPanel getShiftCalendarPanel() {
	        return shiftCalendarPanel;
	    }
	    
	    public int getShiftCountForDate(LocalDate date) throws DataAccessException {
	        try {
	            ShiftDAO shiftDAO = new ShiftDAO();
	            List<Shift> shifts = shiftDAO.getShiftsByDate(date);
	            return shifts.size();
	        } catch (DataAccessException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }
	    
	public boolean confirmShift(int doormanId, int shiftId) throws DataAccessException, SQLException {
		boolean confirmation = false;
		try {
			DBConnection.getInstance().startTransaction();
			shiftDAO.updateDoormanId(shiftId, doormanId);
			DBConnection.getInstance().commitTransaction();
			confirmation = true;
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return confirmation;
	}
	
	public List<Doorman> isDoormanOnAnotherShift()throws DataAccessException, SQLException {
        return doormanCtrl.isDoormanOnAnotherShift();
    }
}
