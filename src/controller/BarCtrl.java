package controller;

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

		public Bar findById(int barId) throws DataAccessException {
			return barDAO.findById(barId);
		}
}
