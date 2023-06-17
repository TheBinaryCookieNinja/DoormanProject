package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Shift;

public class ShiftDAO {
	private static final String findAllQ = "select shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId, signatureId from Shiftt";
	private static final String updateQ = "update Shiftt set doormanId = ? where shiftId = ?";
	private static final String findByDateQ = findAllQ + " where shiftDate = ?";

	private PreparedStatement findAll, update, findByDate;
	
	public ShiftDAO() throws DataAccessException {
		try {
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			update = DBConnection.getInstance().getConnection().prepareStatement(updateQ);
			findByDate = DBConnection.getInstance().getConnection().prepareStatement(findByDateQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}

	public List<Shift> findAll() throws DataAccessException {
		ResultSet rs;
		try {
			rs = findAll.executeQuery();
			List<Shift> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not retrieve all shifts");
		}
	}

	public void updateDoormanId(int shiftId, int doormanId) throws DataAccessException, SQLException {
		try {
			update.setInt(1, doormanId);
			update.setInt(2, shiftId);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update shift where id = " + shiftId);
		}
	}

	public List<Shift> getShiftsByDate(LocalDate localDate) throws DataAccessException {
		 try {
		        findByDate.setDate(1, Date.valueOf(localDate));
		        ResultSet rs = findByDate.executeQuery();
		        List<Shift> res = buildObjects(rs);
		        return res;
		    } catch (SQLException e) {
		        throw new DataAccessException(e, "Could not find shifts by date = " + localDate);
		    }
	}

	private Shift buildObject(ResultSet rs) throws SQLException {
	    int shiftId = rs.getInt("shiftId");
	    Date shiftDate = rs.getDate("shiftDate");
	    String checkInTime = rs.getString("checkInTime");
	    String checkOutTime = rs.getString("checkOutTime");
	    int barId = rs.getInt("barId");
	    int doormanId = rs.getInt("doormanId");

	    Shift s = new Shift(shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId);
	    return s;
	}
	
	private List<Shift> buildObjects(ResultSet rs) throws SQLException {
		List<Shift> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
}
