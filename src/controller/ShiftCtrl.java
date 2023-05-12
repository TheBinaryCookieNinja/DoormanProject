package controller;

import java.time.LocalDate;
import java.util.List;

import database.DataAccessException;
import database.ShiftDAO;
import model.Bar;
import model.Shift;

public class ShiftCtrl  {
	private ShiftDAO shiftDAO;
	private BarCtrl bCtrl;
	private DoormanCtrl dCtrl;
	private LocalDate date;
	
	
	
	
	public ShiftCtrl() throws DataAccessException {
		try {
			shiftDAO = new ShiftDAO();
			bCtrl = new BarCtrl();
			dCtrl = new DoormanCtrl();
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
	public List<Shift> getShiftsByDate() throws DataAccessException {
		this.date = LocalDate.parse("2023-05-15");
        return shiftDAO.getShiftsByDate(date);
	}
	
	public Shift findById(int shiftId) throws DataAccessException{
		return shiftDAO.findById(shiftId);
	}
	
	public List<Shift> findAll() throws DataAccessException{
		return shiftDAO.findAll();
	}
	
	public Bar findBarById(int barId) throws DataAccessException {
		return bCtrl.findById(barId);
	}
	
	public List<Bar> findAllBars() throws DataAccessException{
		return bCtrl.findAll();
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
