package database;

import java.util.List;

import model.Shift;

public interface ShiftDAOIF {
	List<Shift> findAll() throws DataAccessException;
	Shift findById(int id) throws DataAccessException;


}

