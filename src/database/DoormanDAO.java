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

public class DoormanDAO {
	private static final String findAllQ = 
			"select employeeId, hourlyRate from Doorman";
	private static final String findByIdQ = 
		findAllQ + "where employeeId = ?";
	
	private static final String updateQ = 
			"update Doorman set employeeId = ?, hourlyRate = ?";
	
	private PreparedStatement findAll, findById, update;
			
	public DoormanDAO() throws DataAccessException {
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
		public List <Doorman> findAll() throws DataAccessException {
			ResultSet rs;
			try {
				rs = findAll.executeQuery();
				List<Doorman> res = buildObjects(rs);
				return res;
			} catch (SQLException e) {
				throw new DataAccessException(e, "Could not retrieve all persons");
			}
		}
	
	public Doorman findById(int employeeId) throws DataAccessException {
		try {
			findById.setInt(1, employeeId);
			ResultSet rs = findById.executeQuery();
			Doorman p = null;
			if(rs.next()) {
				p = buildObject(rs);
			}
			return p;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by id = " + employeeId);
		}
	}
	
	public void update(Doorman d) throws DataAccessException {
		final int employeeId = d.getEmployeeId();
		final String name = d.getName();
		final String phone = d.getPhone();
		final String email = d.getEmail();
		final String address = d.getAddress();
		final String passcode = d.getPasscode();
		final double hourlyRate = d.getHourlyRate();
		
		try {
			//update person set 
			//name = ?, email = ?, phone = ? , 
			//birth_date = ?, groups_id = ? where id = ?"
			update.setInt(1, employeeId);
			update.setString(2, name);
			update.setString(3, phone);
			update.setString(4, email);
			update.setString(5, address);
			update.setString(6, passcode);
			update.setDouble(7, hourlyRate);
			
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update employee where id = " + employeeId);
		}

	}
	
	private Doorman buildObject(ResultSet rs) throws SQLException {
		Doorman d = new Doorman(
				rs.getInt("employeeId"),
				rs.getString("name"),
				rs.getString("phone"),
				rs.getString("email"),
				rs.getString("address"),
				rs.getString("passcode"),
				rs.getDouble("hourlyRate")
				);
		return d;
	}
	
	private List<Doorman> buildObjects(ResultSet rs) throws SQLException {
		List<Doorman> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

}