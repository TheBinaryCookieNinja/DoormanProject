package database;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import model.AvailableDate;

public class AvailableDateDAO {

	private static final String findByIdQ = 
			"select * from AvailableDates where availableDatesId = ?";
	private static final String createAvailableDateQ = 
			"insert into AvailableDates (calenderDate, employeeId) values (?,?)";
	private static final String deleteAvailableDateQ = 
			"delete from AvailableDates where employeeId = ? and calenderDate = ?";
	private static final String findByDoormanIdAndDateQ = 
	        "SELECT * FROM AvailableDates WHERE employeeId = ? AND calenderDate = ?";

	private PreparedStatement findById, createAvailableDate, deleteAvailableDate, findByDoormanIdAndDate;
	
	public AvailableDateDAO() throws DataAccessException {
		try {
			findById = DBConnection.getInstance().getConnection()
					.prepareStatement(findByIdQ); 
			createAvailableDate = DBConnection.getInstance().getConnection()
					.prepareStatement(createAvailableDateQ);
			deleteAvailableDate = DBConnection.getInstance().getConnection()
					.prepareStatement(deleteAvailableDateQ);
			findByDoormanIdAndDate = DBConnection.getInstance().getConnection()
					.prepareStatement(findByDoormanIdAndDateQ);	        
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	public AvailableDate findById(int availableDateId) throws DataAccessException  {
		int lockIndex = calculateLockIndex(availableDateId);
		try {
            findById.setInt(1, availableDateId);
            ResultSet rs = findById.executeQuery();
            AvailableDate p = null;
            if (rs.next()) {
                p = buildObject(rs);
            }
            return p;
        } catch (SQLException e) {
            throw new DataAccessException(e, "Could not find by id = " + availableDateId);
        } 
	}
	
	public void createAvailableDate(AvailableDate availableDate) throws SQLException, DataAccessException {
		try {
            createAvailableDate.setDate(1, availableDate.getCalendarDate());
            createAvailableDate.setInt(2, availableDate.getEmployeeId());
            createAvailableDate.execute();
        } catch (SQLException e) {
            throw new DataAccessException(e, "Error in registering the date");
        } 
        
	}
	
	public void deleteAvailableDate(int doormanId, LocalDate date) throws SQLException {
            deleteAvailableDate.setInt(1, doormanId);
            deleteAvailableDate.setDate(2, Date.valueOf(date));
            deleteAvailableDate.execute();
	}
	
	private AvailableDate buildObject(ResultSet rs) throws SQLException {
		AvailableDate availableDate = new AvailableDate(
				rs.getInt("availableDatesId"),
				rs.getDate("calenderDate"),
				rs.getInt("employeeId")
				);
		return availableDate;
	}
	
	public AvailableDate findByDoormanIdAndDate(int doormanId, LocalDate date) throws SQLException {
	        findByDoormanIdAndDate.setInt(1, doormanId);
	        findByDoormanIdAndDate.setDate(2, java.sql.Date.valueOf(date));
	        ResultSet rs = findByDoormanIdAndDate.executeQuery();
	        AvailableDate availableDate = null;
	        if (rs.next()) {
	            availableDate = buildObject(rs);
	        }
	        return availableDate;
	}
}
