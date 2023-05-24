package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Shift;

public class ShiftDAO {
	private static final String findAllQ = "select shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId, signatureId from Shiftt";
	private static final String findByIdQ = findAllQ + " where shiftId = ?";
	private static final String createShiftQ = "insert into Shiftt (shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId, signatureId) VALUES (?,?,?,?,?,?,?,?)";
	private static final String updateQ = "update Shiftt set doormanId = ? where shiftId = ?";
	private static final String deleteShiftQ = "delete from Shiftt where shiftId = ?";
	private static final String findByDateQ = findAllQ + " where shiftDate = ? and where employeeId is null";

	private PreparedStatement findAll, findById, createShift, update, deleteShift, findByDate;

	public ShiftDAO() throws DataAccessException {
		try {
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			createShift = DBConnection.getInstance().getConnection().prepareStatement(createShiftQ);
			update = DBConnection.getInstance().getConnection().prepareStatement(updateQ);
			deleteShift = DBConnection.getInstance().getConnection().prepareStatement(deleteShiftQ);
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

	public Shift findById(int shiftId) throws DataAccessException {
		try {
			findById.setInt(1, shiftId);
			ResultSet rs = findById.executeQuery();
			Shift s = null;
			if (rs.next()) {
				s = buildObject(rs);
			}
			return s;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by id = " + shiftId);
		}
	}

	public void createShift(Shift shift) throws SQLException {
		createShift.setInt(1, shift.getShiftId());
		createShift.setString(2, shift.getShiftDate());
		createShift.setString(3, shift.getCheckInTime());
		createShift.setString(4, shift.getCheckOutTime());
		createShift.setInt(5, shift.getBarId());
		createShift.setInt(6, shift.getDoormanId());
		createShift.execute();

	}

	public void updateDoormanId(int shiftId, int doormanId) throws DataAccessException {
		try {
			update.setInt(1, doormanId);
			update.setInt(2, shiftId);
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update shift where id = " + shiftId);
		}
	}

	public void deleteShift(int shiftId) throws SQLException {
		deleteShift.setInt(1, shiftId);
		deleteShift.execute();
	}

	public List<Shift> getShiftsByDate(LocalDate localDate) throws DataAccessException {
		 try {
		        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate); // Convert LocalDate to java.sql.Date
		        findByDate.setDate(1, sqlDate);
		        ResultSet rs = findByDate.executeQuery();
		        List<Shift> res = buildObjects(rs);
		        return res;
		    } catch (SQLException e) {
		        throw new DataAccessException(e, "Could not find shifts by date = " + localDate);
		    }

	}

	private Shift buildObject(ResultSet rs) throws SQLException {
	    int shiftId = rs.getInt("shiftId");
	    String shiftDate = rs.getString("shiftDate");
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
