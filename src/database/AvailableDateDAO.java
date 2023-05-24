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
	private Lock mutex;
	
	public AvailableDateDAO() throws DataAccessException {
		try {
			findById = DBConnection.getInstance().getConnection()
					.prepareStatement(findByIdQ); 
			createAvailableDate = DBConnection.getInstance().getConnection()
					.prepareStatement(createAvailableDateQ);
			deleteAvailableDate = DBConnection.getInstance().getConnection()
					.prepareStatement(deleteAvailableDateQ);
			mutex = new ReentrantLock(true); //initializes the reentrantLock as a fair lock
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	public AvailableDate findById(int availableDatesId) throws DataAccessException  {
		try {
            mutex.lock(); // Acquire the lock

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
            mutex.unlock(); // Release the lock in the finally block
        }
	}
	
	public void createAvailableDate(AvailableDate availableDate) throws SQLException {
		try {
            mutex.lock(); // Acquire the lock

            createAvailableDate.setDate(1, availableDate.getCalendarDate());
            createAvailableDate.setInt(2, availableDate.getEmployeeId());
            createAvailableDate.execute();
        } finally {
            mutex.unlock(); // Release the lock in the finally block
        }
	}
	
	public void deleteAvailableDate(int doormanId) throws SQLException {
		try {
            mutex.lock(); // Acquire the lock

            deleteAvailableDate.setInt(1, doormanId);
            deleteAvailableDate.execute();
        } finally {
            mutex.unlock(); // Release the lock in the finally block
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
}
