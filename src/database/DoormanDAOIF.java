package database;

import java.util.List;

import model.Doorman;

public interface DoormanDAOIF {
	List<Doorman> findAll() throws DataAccessException;
	Doorman findById(int id) throws DataAccessException;


}

