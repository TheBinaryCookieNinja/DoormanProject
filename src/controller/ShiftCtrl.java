package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import database.DBConnection;
import database.DataAccessException;
import database.ShiftDAO;
import gui.ShiftCalendarPanel;
import model.Bar;
import model.Doorman;
import model.Shift;

public class ShiftCtrl  {
	private ShiftDAO shiftDAO;
	private BarCtrl barCtrl;
	private DoormanCtrl doormanCtrl;
	private ShiftCalendarPanel shiftCalendarPanel;
	
	
	
	public ShiftCtrl() throws DataAccessException {
		try {
			shiftDAO = new ShiftDAO();
		} catch (Exception e) {
			throw new DataAccessException(e, "Can't create shiftDAO");
		}
		barCtrl = new BarCtrl();
		doormanCtrl = new DoormanCtrl();
	}

	 /**
     * Get shifts by date.
     *
     * @param date the date to get shifts for
     * @return list of shifts for the specified date
	 * @throws SQLException 
     */
	public List<Shift> getShiftsByDate(LocalDate date) throws DataAccessException, SQLException {
		List<Shift> shifts = null;
		try {
			DBConnection.getInstance().startTransaction();
			shifts = shiftDAO.getShiftsByDate(date);
			DBConnection.getInstance().commitTransaction();

		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return shifts;
	}
	
	public List<Shift> findAll() throws DataAccessException, SQLException{
		List<Shift> shifts = null;
		try {
			DBConnection.getInstance().startTransaction();
			shifts = shiftDAO.findAll();
			DBConnection.getInstance().commitTransaction();

		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return shifts;
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
