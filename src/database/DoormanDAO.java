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
			"select Employee.employeeId, f_name, l_name, phone, email, employee.addressId, street, addresss.zipcode, city, passcode, hourlyRate from Doorman as d "
			+ "left join Employee on Employee.employeeId = d.employeeId left join Addresss on (Addresss.addressId = employee.addressId) left join Zipcode on (zipcode.zipcode = addresss.zipcode)";
	private static final String findByIdQ = 
			"select Employee.employeeId, f_name, l_name, phone, email, employee.addressId, street, addresss.zipcode, city, passcode, hourlyRate from Doorman as d left join Employee on Employee.employeeId = d.employeeId left join Addresss on (Addresss.addressId = employee.addressId) left join Zipcode on (zipcode.zipcode = addresss.zipcode)where employee.employeeId = ?";
	private static final String getAvailableDoormenForShiftQ = 
			"select Employee.employeeId, f_name, l_name, phone, email, employee.addressId, street, addresss.zipcode, city, passcode, hourlyRate from Doorman as d left join Employee on Employee.employeeId = d.employeeId left join Addresss on (Addresss.addressId = employee.addressId) left join Zipcode on (zipcode.zipcode = addresss.zipcode) left join AvailableDates on AvailableDates.employeeId = d.employeeId left join DoormanWishlist on (DoormanWishList.employeeId = d.employeeId and DoormanWishlist.BarId = ?) left join DoormanBlacklist on (DoormanBlacklist.employeeId = d.employeeId and DoormanBlacklist.BarId = ?) left join shiftt on (shiftt.doormanId = d.employeeId) where AvailableDates.calenderDate = ? and DoormanBlacklist.BarId is null order by shiftt.doormanId asc, DoormanWishlist.employeeId desc";
	private static final String isDoormanOnWishlistQ =
			"select Employee.employeeId, f_name, l_name, phone, email, employee.addressId, street, addresss.zipcode, city, passcode, hourlyRate from Doorman as d left join Employee on Employee.employeeId = d.employeeId left join Addresss on (Addresss.addressId = employee.addressId) left join Zipcode on (zipcode.zipcode = addresss.zipcode) left join DoormanWishList on (DoormanWishList.employeeId = d.employeeId) where barId = ?";
	private static final String isDoormanOnAnotherShiftQ =
			"select Employee.employeeId, f_name, l_name, phone, email, employee.addressId, street, addresss.zipcode, city, passcode, hourlyRate from Doorman as d left join Employee on Employee.employeeId = d.employeeId left join Addresss on (Addresss.addressId = employee.addressId) left join Zipcode on (zipcode.zipcode = addresss.zipcode)  join shiftt on (shiftt.doormanId = d.employeeId) where shiftt.doormanId is not null";
	
	private PreparedStatement findById, getAvailableDoormenForShift, isDoormanOnWishlist, isDoormanOnAnotherShift;
			
	public DoormanDAO() throws DataAccessException {
		try {
		findById = DBConnection.getInstance().getConnection()
				.prepareStatement(findByIdQ);
		getAvailableDoormenForShift = DBConnection.getInstance().getConnection()
				.prepareStatement(getAvailableDoormenForShiftQ);
		isDoormanOnWishlist = DBConnection.getInstance().getConnection()
				.prepareStatement(isDoormanOnWishlistQ);
		isDoormanOnAnotherShift = DBConnection.getInstance().getConnection()
				.prepareStatement(isDoormanOnAnotherShiftQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
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
	
	public List<Doorman> isDoormanOnWishlist(int barId) throws DataAccessException {
		ResultSet rs;
		try {
			isDoormanOnWishlist.setInt(1, barId);
			rs = isDoormanOnWishlist.executeQuery();
			List<Doorman> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not retrieve all persons");
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
	
	public List<Doorman> isDoormanOnAnotherShift() throws DataAccessException {
		ResultSet rs;
		try {
			rs = isDoormanOnAnotherShift.executeQuery();
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
		String address = rs.getString("street") + ", " + rs.getString("city") + ", " + rs.getString("zipcode");
		String passcode = rs.getString("passcode");
		Double hourlyRate = rs.getDouble("hourlyRate");
		
		return new Doorman(employeeId, f_name, l_name, phone, email, address, passcode, hourlyRate);
	}
	
	private List<Doorman> buildObjects(ResultSet rs) throws SQLException {
		List<Doorman> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
}