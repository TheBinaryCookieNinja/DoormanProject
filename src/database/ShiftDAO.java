package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Shift;

public class ShiftDAO {
	private static final String findAllQ = "select shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId, signatureId from Shiftt";
	private static final String findByIdQ = findAllQ + " where shiftId = ?";
	private static final String createShiftQ = "insert into Shiftt (shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId, signatureId) VALUES (?,?,?,?,?,?,?,?)";
	private static final String updateQ = "update Shiftt set shiftId = ?, shiftDate = ?, checkInTime = ?, checkOutTime = ?, barId = ?, doormanId = ?, signatureId =?";
	private static final String deleteShiftQ = "delete from Shiftt where shiftId = ?";

	private static final String findByDateQ = findAllQ + " where shiftDate = ?";

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
		createShift.setTime(3, Time.valueOf(shift.getCheckInTime()));
		createShift.setTime(4, Time.valueOf(shift.getCheckOutTime()));
		createShift.setInt(5, shift.getBarId());
		createShift.setInt(6, shift.getDoormanId());
		createShift.execute();

	}

	public void update(Shift s) throws DataAccessException {
		final int shiftId = s.getShiftId();
		final String shiftDate = s.getShiftDate();
		final LocalTime checkInTime = s.getCheckInTime();
		final LocalTime checkOutTime = s.getCheckOutTime();
		final int barId = s.getBarId();
		final int doormanId = s.getDoormanId();

		try {
			// "update Shift set
			// shiftId = ?, shiftDate = ?, checkInTime = ?,
			// checkOutTime = ?, barId = ?, doormanId = ? where shiftId = ?"
			//
			update.setInt(1, shiftId);
			update.setString(2, shiftDate);
			update.setTime(3, Time.valueOf(checkInTime));
			update.setTime(4, Time.valueOf(checkOutTime));
			update.setInt(5, barId);
			update.setInt(6, doormanId);

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
<<<<<<< HEAD
	    int shiftId = rs.getInt("shiftId");
	    String shiftDate = rs.getString("shiftDate");
	    LocalTime checkInTime = LocalTime.parse(rs.getString("checkInTime"));
	    LocalTime checkOutTime = LocalTime.parse(rs.getString("checkOutTime"));
	    int barId = rs.getInt("barId");
	    int doormanId = rs.getInt("doormanId");

	    Shift s = new Shift(shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId);
	    return s;
=======
		Shift s = new Shift(
				rs.getInt("shiftId"), 
				rs.getString("shiftDate"), 
				rs.getString("checkInTime"),
				rs.getString("checkOutTime"), 
				rs.getInt("barId"), 
				rs.getInt("doormanId"));
		return s;
>>>>>>> 34f7843e90b7b34cf367005f0ba826109a87c73e
	}


	private List<Shift> buildObjects(ResultSet rs) throws SQLException {
		List<Shift> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;

	}
}
