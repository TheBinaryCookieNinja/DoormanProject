package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import database.DBConnection;
import database.DataAccessException;
import database.DoormanDAO;
import model.Doorman;

public class DoormanCtrl {
	 private DoormanDAO doormanDAO;

	    public DoormanCtrl() throws DataAccessException {
	        try {
	        	doormanDAO = new DoormanDAO();
	        } catch (Exception e) {
	        	throw new DataAccessException(e, "Unable to create doormanDAO");
	        }
	    }
	    
	    public boolean getDoormanByDoormanId(int doormanId) throws DataAccessException, SQLException {
	       DBConnection.getInstance().startTransaction();
	       try {
	    	   Doorman doorman = doormanDAO.findById(doormanId);
	    	   DBConnection.getInstance().commitTransaction();
	    		return doorman != null;
	       } catch (SQLException e) {
	    	   DBConnection.getInstance().rollbackTransaction();
	    	   throw new DataAccessException(e, "Unable to find doorman by ID. Please try another ID.");
	       }
	    	
	    }
	    
	    public List<Doorman> getAvailableDoormenForShift(LocalDate localDate, int barId) throws DataAccessException {
	        return doormanDAO.getAvailableDoormenForShift(localDate, barId);
	    }
	    
	    public List<Doorman> isDoormanOnOnWishList(int barId)throws DataAccessException {
	        return doormanDAO.isDoormanOnWishlist(barId);
	    }
	    
	    public List<Doorman> isDoormanOnAnotherShift()throws DataAccessException {
	        return doormanDAO.isDoormanOnAnotherShift();
	    }
	}

