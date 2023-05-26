package controller;

import java.sql.SQLException;
import java.util.List;

import database.BarDAO;
import database.DBConnection;
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

		public List<Bar> getAll() throws DataAccessException, SQLException {
			DBConnection.getInstance().startTransaction();
			try {
				List<Bar> lBar = barDAO.getAll();
				DBConnection.getInstance().commitTransaction();
				return lBar;
			}catch (SQLException e) {
				DBConnection.getInstance().rollbackTransaction();
				throw new DataAccessException(e, "Can't find any bars");
			}
			
		}
}
