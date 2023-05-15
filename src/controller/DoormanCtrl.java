package controller;

<<<<<<< Updated upstream
import java.util.Date;
import java.util.List;

import database.DataAccessException;
=======
import java.sql.Date;
import java.util.List;
import database.DataAccessException;

>>>>>>> Stashed changes
import database.DoormanDAO;
import model.Doorman;

public class DoormanCtrl {
<<<<<<< Updated upstream
	 private DoormanDAO doormanDAO;

	    public DoormanCtrl() throws DataAccessException {
	        try {
	        	doormanDAO = new DoormanDAO();
	        } catch (Exception e) {
	        	throw new DataAccessException(e, "Unable to create doormanDAO");
	    }
=======
		private DoormanDAO doormanDAO;

	    public DoormanCtrl() throws DataAccessException {
	       try {
	    	   doormanDAO = new DoormanDAO();
	       } catch (Exception e) {
	    	   throw new DataAccessException(e, "Unable to create doormanDAO");
	       }
>>>>>>> Stashed changes
	    }

	    public List<Doorman> getAvailableDoormenForShift(Date date, int barId) {
	        return doormanDAO.findAvailableDoormenForShift(date, barId);
	    }

	    public Doorman getDoormanByDoormanId(int doormanId) throws DataAccessException {
	        return doormanDAO.findById(doormanId);
	    }
	    
	    public List<Doorman> findAll() throws DataAccessException{
<<<<<<< Updated upstream
	    	return doormanDAO.findAll();	    	
	    }
=======
	    	return doormanDAO.findAll();
	    }
	     
>>>>>>> Stashed changes
	}

