package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import database.BarDAO;
import database.DataAccessException;
import model.Bar;

public class BarCtrl {
	private BarDAO barDAO;
	
	public BarCtrl() throws DataAccessException  {
		try {
			barDAO = new BarDAO();
		} catch (Exception e) {
			throw new DataAccessException(e, "Can't create BarDAO");
		}
	}
	
	public List<Bar> getBarListByDate(Date date) throws DataAccessException {
		// Convert java.util.Date to java.time.LocalDate
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return barDAO.getBarListByDate(localDate);
		
	}
		
		public List<Bar> findAll() throws DataAccessException{
			return barDAO.findAll();
	}
		
		public Bar findById(int barId) throws DataAccessException {
			return barDAO.findById(barId);
		}

}
