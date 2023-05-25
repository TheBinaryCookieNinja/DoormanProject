package controller;

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

		public List<Bar> getAll() throws DataAccessException {
			return barDAO.getAll();
		}
}
