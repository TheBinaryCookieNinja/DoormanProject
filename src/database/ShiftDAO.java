package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.DoormanCtrl;
import controller.ShiftCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.Doorman;
import model.Shift;

public class ShiftDAO {
	private static final String findAllQ = 
			"select shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId, signatureId from Shift";
	private static final String findByIdQ = 
		findAllQ + "where shiftId = ?";
	
	private static final String updateQ = 
			"update Shift set shiftId = ?, shiftDate = ?, checkInTime = ?, checkOutTime = ?, barId = ?, doormanId = ?, signatureId = ?";
	
	private PreparedStatement findAll, findById, update;
			
	public ShiftDAO() throws DataAccessException {
		try {
		findAll = DBConnection.getInstance().getConnection()
				.prepareStatement(findAllQ);
		findById = DBConnection.getInstance().getConnection()
				.prepareStatement(findByIdQ);
		update = DBConnection.getInstance().getConnection()
				.prepareStatement(updateQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	public List <Shift> findAll() throws DataAccessException {
		ResultSet rs;
		try {
			rs = findAll.executeQuery();
			List<Shift> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not retrieve all persons");
		}
	}
	
	public Shift findById(int shiftId) throws DataAccessException {
		try {
			findById.setInt(1, shiftId);
			ResultSet rs = findById.executeQuery();
			Shift s = null;
			if(rs.next()) {
				s = buildObject(rs);
			}
			return s;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by id = " + shiftId);
		}
	}
	
	public void update(Shift s) throws DataAccessException {
		
		try {
			//update person set 
			//name = ?, email = ?, phone = ? , 
			//birth_date = ?, groups_id = ? where id = ?"
			update.setString(1, name);
			
			
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update shift where id = " + shiftId);
		}

	}
	private Shift buildObject(ResultSet rs) throws SQLException {
		Shift s = new Shift(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getString("email"),
				rs.getString("phone"),
				rs.getDate("birth_date").toLocalDate(),
				new Shift(rs.getInt("groups_id"), null, null)
				);
		return s;
	}
	
	private List<Shift> buildObjects(ResultSet rs) throws SQLException {
		List<Shift> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

}

