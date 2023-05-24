package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import model.AvailableDate;

public class AvailableDateDAO {

	private static final String findByIdQ = 
			"select * from AvailableDates where availableDatesId = ?";
	private static final String createAvailableDateQ = 
			"insert into AvailableDates (calenderDate, employeeId) values (?,?)";
	private static final String deleteAvailableDateQ = 
			"delete from AvailableDates where employeeId = ?";
	
	private PreparedStatement findById, createAvailableDate, deleteAvailableDate;
	private static final int NUM_LOCKS = 8; // Number of locks to use
    private Lock[] locks; // Array of locks for lock striping
	
	public AvailableDateDAO() throws DataAccessException {
		try {
			findById = DBConnection.getInstance().getConnection()
					.prepareStatement(findByIdQ); 
			createAvailableDate = DBConnection.getInstance().getConnection()
					.prepareStatement(createAvailableDateQ);
			deleteAvailableDate = DBConnection.getInstance().getConnection()
					.prepareStatement(deleteAvailableDateQ);
			
			locks = new ReentrantLock[NUM_LOCKS];
	        for (int i = 0; i < NUM_LOCKS; i++) {
	            locks[i] = new ReentrantLock(true); // Initializes each lock as a fair lock
	        }
	        
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	public AvailableDate findById(int availableDatesId) throws DataAccessException  {
		int lockIndex = calculateLockIndex(availableDatesId);
		try {
			locks[lockIndex].lock(); // Acquire the specific lock
            findById.setInt(1, availableDatesId);
            ResultSet rs = findById.executeQuery();
            AvailableDate p = null;
            if (rs.next()) {
                p = buildObject(rs);
            }
            return p;
        } catch (SQLException e) {
            throw new DataAccessException(e, "Could not find by id = " + availableDatesId);
        } finally {
        	locks[lockIndex].unlock(); // Acquire the specific lock
        }
	}
	
	public void createAvailableDate(AvailableDate availableDate) throws SQLException {
		int lockIndex = calculateLockIndex(availableDate.getEmployeeId());
		try {
			locks[lockIndex].lock(); // Acquire the specific lock

            createAvailableDate.setDate(1, availableDate.getCalendarDate());
            createAvailableDate.setInt(2, availableDate.getEmployeeId());
            createAvailableDate.execute();
        } finally {
        	locks[lockIndex].unlock(); // Acquire the specific lock
        }
	}
	
	public void deleteAvailableDate(int doormanId) throws SQLException {
		int lockIndex = calculateLockIndex(doormanId);
		try {
			locks[lockIndex].lock(); // Acquire the specific lock

            deleteAvailableDate.setInt(1, doormanId);
            deleteAvailableDate.execute();
        } finally {
        	locks[lockIndex].unlock(); // Release the specific lock in the finally block
        }
	}
	
	private AvailableDate buildObject(ResultSet rs) throws SQLException {
		AvailableDate availableDate = new AvailableDate(
				rs.getInt("availableDatesId"),
				rs.getDate("calendarDate"),
				rs.getInt("employeeId")
				);
		return availableDate;
	}
	
	private int calculateLockIndex(int id) {
	    int hash = id ^ (id >>> 16); // Apply a hashing function to the ID
	    return Math.abs(hash) % NUM_LOCKS; // Calculate the lock index based on the hashed value
	}

}
