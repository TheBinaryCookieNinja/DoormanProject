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
	    
	    public Doorman getDoormanByDoormanId(int doormanId) throws DataAccessException, SQLException {
	       DBConnection.getInstance().startTransaction();
	       try {
	    	   Doorman doorman = doormanDAO.findById(doormanId);
	    	   DBConnection.getInstance().commitTransaction();
	    		return doorman;
	       } catch (SQLException e) {
	    	   DBConnection.getInstance().rollbackTransaction();
	    	   throw new DataAccessException(e, "Unable to find doorman by ID. Please try another ID.");
	       }
	    	
	    }
	    
	    public List<Doorman> getAvailableDoormenForShift(LocalDate localDate, int barId) throws DataAccessException, SQLException {
	        DBConnection.getInstance().startTransaction();
	        try {
	        	List<Doorman> lDoorman = doormanDAO.getAvailableDoormenForShift(localDate, barId);
	        	DBConnection.getInstance().commitTransaction();
	        	return lDoorman;
	        } catch (SQLException e) {
	        	DBConnection.getInstance().rollbackTransaction();
	        	throw new DataAccessException(e, "Can't find any available Doormen for shift.");
	        }
	    	
	    }
	    
	    public List<Doorman> isDoormanOnOnWishList(int barId)throws DataAccessException, SQLException {
	    	DBConnection.getInstance().startTransaction();
	    	try {
	    		List<Doorman> lDoorman = doormanDAO.isDoormanOnWishlist(barId);
	    		DBConnection.getInstance().commitTransaction();
	    		return lDoorman;
	    	} catch (SQLException e) {
	    		DBConnection.getInstance().rollbackTransaction();
	    		throw new DataAccessException(e, "Cant find the given doorman on a wishlist");
	    	}
	    }
	    
	    public List<Doorman> isDoormanOnAnotherShift()throws DataAccessException, SQLException {
	        DBConnection.getInstance().startTransaction();
	        try {
	        	List<Doorman> lDoorman = doormanDAO.isDoormanOnAnotherShift();
	        	DBConnection.getInstance().commitTransaction();
	        	return lDoorman;
	        } catch (SQLException e) {
	        	DBConnection.getInstance().rollbackTransaction();
	        	throw new DataAccessException(e, "Can't find doorman on another shift.");
	        }
	    }
	}

