package controller;

import java.util.Date;
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

	    public List<Doorman> getAvailableDoormenForShift(Date date, int barId) {
	        return doormanDAO.findAvailableDoormenForShift(date, barId);
	    }

	    public Doorman getDoormanByDoormanId(int doormanId) throws DataAccessException {
	        return doormanDAO.findById(doormanId);
	    }
	    
	    public List<Doorman> findAll() throws DataAccessException{
	    	return doormanDAO.findAll();	    	
	    }
	}

