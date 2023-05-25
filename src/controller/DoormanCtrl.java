package controller;

import java.time.LocalDate;
import java.util.List;

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
	    
	    public Doorman getDoormanByDoormanId(int doormanId) throws DataAccessException {
	        return doormanDAO.findById(doormanId);
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

