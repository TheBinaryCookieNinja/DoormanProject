package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AvailableDate;

public class AvailableDateDAO {

	private static final String findByIdQ = 
			"select * from AvailableDates where availableDatesId = ?";
	private static final String createAvailableDateQ = 
			"insert into AvailableDates (availableDatesId, calenderDate, employeeId) values (?,?,?)";
	
	private PreparedStatement findById, createAvailableDate;
	
	public AvailableDateDAO() throws DataAccessException {
		try {
			findById = DBConnection.getInstance().getConnection()
					.prepareStatement(findByIdQ); 
			createAvailableDate = DBConnection.getInstance().getConnection()
					.prepareStatement(createAvailableDateQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	public AvailableDate findById(int AvailableDatesId) throws DataAccessException  {
		try {
			findById.setInt(1, AvailableDatesId);
			ResultSet rs = findById.executeQuery();
			AvailableDate p = null;
			if(rs.next()) {
				p = buildObject(rs);
			}
			return p;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by id = " + AvailableDatesId);
		}
	}
	
	public void createAvailableDate(AvailableDate availableDate) throws SQLException {
		createAvailableDate.setInt(1, AvailableDate.getLocalDate());
		createAvailableDate.setString(2, AvailableDate.getCalendarDate());
		createAvailableDate.setInt(3, AvailableDate.getEmployeeId());
	}
	
	private AvailableDate buildObject(ResultSet rs) throws SQLException {
		AvailableDate aD = new AvailableDate(
				rs.getInt("availableDatesId"),
				rs.getString("calendarDate"),
				rs.getInt("employeeId")
				);
		return aD;
	}
}
