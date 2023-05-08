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
	private static final String createDoormanQ =
			"insert into Doorman (employeeId, name, phone, email, address, passcode, hourlyRate) VALUES (?,?,?,?,?,?,?,?)";
	private static final String updateQ = 
			"update Doorman set employeeId = ?, hourlyRate = ?";
	private static final String deleteDoormanQ =
			"delete * from Doorman where employeeId = ?";
	
	private PreparedStatement findAll, findById, createDoorman, update, deleteDoorman;
			
	public DoormanDAO() throws DataAccessException {
		try {
		findAll = DBConnection.getInstance().getConnection()
				.prepareStatement(findAllQ);
		findById = DBConnection.getInstance().getConnection()
				.prepareStatement(findByIdQ);
		createDoorman = DBConnection.getInstance().getConnection()
				.prepareStatement(createDoormanQ);
		update = DBConnection.getInstance().getConnection()
				.prepareStatement(updateQ);
		deleteDoorman = DBConnection.getInstance().getConnection()
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
	
	public void createDoorman(Doorman doorman) throws SQLException {
		createDoorman.setInt(1, doorman.getEmployeeId());
		createDoorman.setString(2, doorman.getName());
		createDoorman.setString(3, doorman.getPhone());
		createDoorman.setString(4, doorman.getEmail());
		createDoorman.setString(5, doorman.getEmail());
		createDoorman.setString(6, doorman.getAddress());
		createDoorman.setString(7, doorman.getPasscode());
		createDoorman.setDouble(8, doorman.getHourlyRate());
		createDoorman.execute();
		
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
	
	public void deleteDoorman(int employeeId) throws SQLException {
		deleteDoorman.setInt(1, employeeId);
		deleteDoorman.execute();
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