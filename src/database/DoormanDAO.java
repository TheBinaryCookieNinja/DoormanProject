package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import model.Doorman;


public class DoormanDAO {
	
	private static final String findAllQ =
			"select Employee.employeeId, f_name, l_name, phone, email, addressId, passcode, hourlyRate from Doorman as d"
			+ "left join Employee on Employee.employeeId = d.employeeId";
	private static final String findByIdQ = 
			findAllQ + "where employeeId = ?";
	private static final String createDoormanQ =
			"insert into Doorman (employeeId, f_name, l_name, phone, email, addressId, passcode, hourlyRate) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String updateQ = 
			"update Doorman set employeeId = ?, hourlyRate = ?";
	private static final String deleteDoormanQ =
			"delete * from Doorman where employeeId = ?";
	private static final String getAvailableDoormenForShiftQ = 
			"select Employee.employeeId, f_name, l_name, phone, email, addressId, passcode, hourlyRate from Doorman as d left join Employee on Employee.employeeId = d.employeeId left join AvailableDates on AvailableDates.employeeId = d.employeeId left join DoormanWishlist on (DoormanWishList.employeeId = d.employeeId and DoormanWishlist.BarId = ?) left join DoormanBlacklist on (DoormanBlacklist.employeeId = d.employeeId and DoormanBlacklist.BarId = ?) where AvailableDates.calenderDate = ? and DoormanBlacklist.BarId is null order by DoormanWishlist.employeeId desc";
	
	private PreparedStatement findAll, findById, createDoorman, update, deleteDoorman, getAvailableDoormenForShift;
			
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
				.prepareStatement(deleteDoormanQ);
		getAvailableDoormenForShift = DBConnection.getInstance().getConnection()
				.prepareStatement(getAvailableDoormenForShiftQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
			}
		}
		public List<Doorman> findAll() throws DataAccessException {
			ResultSet rs;
			try {
				rs = findAll.executeQuery();
				List<Doorman> res = buildObjects(rs);
				return res;
			} catch (SQLException e) {
				throw new DataAccessException(e, "Could not retrieve all doormen");
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
		createDoorman.setString(2, doorman.getF_name());
		createDoorman.setString(3, doorman.getL_name());
		createDoorman.setString(4, doorman.getPhone());
		createDoorman.setString(5, doorman.getEmail());
		createDoorman.setInt(6, doorman.getAddressId());
		createDoorman.setString(7, doorman.getPasscode());
		createDoorman.setDouble(8, doorman.getHourlyRate());
		createDoorman.execute();
		
	}
	
	public void update(Doorman d) throws DataAccessException {
		final int employeeId = d.getEmployeeId();
		final String f_name = d.getF_name();
		final String l_name = d.getL_name();
		final String phone = d.getPhone();
		final String email = d.getEmail();
		final int address = d.getAddressId();
		final String passcode = d.getPasscode();
		final double hourlyRate = d.getHourlyRate();
		
		try {
			//"update Doorman set 
			//employeeId = ?, f_name = ?, l_name = ?, phone = ?,
			//email = ?, address = ?, passcode = ?, hourlyRate = ?
			//where employeeId = ?"
			update.setInt(1, employeeId);
			update.setString(2, f_name);
			update.setString(3, l_name);
			update.setString(4, phone);
			update.setString(5, email);
			update.setInt(6, address);
			update.setString(7, passcode);
			update.setDouble(8, hourlyRate);
			
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update employee where id = " + employeeId);
		}

	}
	
	public List<Doorman> getAvailableDoormenForShift(LocalDate date, int barId) throws DataAccessException {
		ResultSet rs;
		try {
			getAvailableDoormenForShift.setInt(1, barId);
			getAvailableDoormenForShift.setInt(2, barId);
			getAvailableDoormenForShift.setDate(3, Date.valueOf(date));
			rs = getAvailableDoormenForShift.executeQuery();
			List<Doorman> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not retrieve all persons");
		}
	}
	
	private Doorman buildObject(ResultSet rs) throws SQLException {
		int employeeId = rs.getInt("employeeId");
		String f_name = rs.getString("f_name");
		String l_name = rs.getString("l_name");
		String phone = rs.getString("phone");
		String email = rs.getString("email");
		int addressId = rs.getInt("addressId");
		String passcode = rs.getString("passcode");
		Double hourlyRate = rs.getDouble("hourlyRate");
		
		return new Doorman(employeeId, f_name, l_name, phone, email, addressId, passcode, hourlyRate);
		
	}
	
	private List<Doorman> buildObjects(ResultSet rs) throws SQLException {
		List<Doorman> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

}