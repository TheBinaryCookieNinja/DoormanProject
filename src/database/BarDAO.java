package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Bar;
import model.Shift;

public class BarDAO {
	private LocalDate localDate;
	
	private static final String findAllQ = 
			"select barId, name, phone, email, address, cvr from Bar";
	private static final String findByIdQ = 
		findAllQ + "where barId = ?";
	private static final String createBarQ =
			"insert into Bar (barId, name, phone, email, address, cvr) VALUES (?,?,?,?,?,?)";
	private static final String updateQ = 
			"update Bar set barId = ?, name = ?, phone = ?, email = ?, address = ?, cvr = ?";
	private static final String deleteBarQ = 
			"delete * from Bar where barId = ?";
	private static final String findByDateQ = findAllQ + "FROM Bar b " + "INNER JOIN Shiftt s ON b.barId = s.barId " + "WHERE s.shiftDate = ?";
	
	private PreparedStatement findAll, findById, createBar, update, deleteBar, findByDate;
	
	public BarDAO() throws DataAccessException {
		try {
			findAll = DBConnection.getInstance().getConnection()
					.prepareStatement(findAllQ);
			findById = DBConnection.getInstance().getConnection()
					.prepareStatement(findByIdQ);
			createBar = DBConnection.getInstance().getConnection()
					.prepareStatement(createBarQ);
			update = DBConnection.getInstance().getConnection()
					.prepareStatement(updateQ);
			deleteBar = DBConnection.getInstance().getConnection()
					.prepareStatement(deleteBarQ);
			findByDate = DBConnection.getInstance().getConnection()
					.prepareStatement(findByDateQ);
			findByDate.setDate(1, java.sql.Date.valueOf(localDate));
			} catch (SQLException e) {
				throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	public List<Bar> findAll() throws DataAccessException {
	ResultSet rs;
	try {
		rs = findAll.executeQuery();
		List<Bar> res = buildObjects(rs);
		return res;
	} catch (SQLException e) {
		throw new DataAccessException(e, "Could not retrieve all Bars");
		}
	}
	public Bar findById(int barId) throws DataAccessException {
		try {
			findById.setInt(1, barId);
			ResultSet rs = findById.executeQuery();
			Bar b = null;
			if(rs.next()) {
				b = buildObject(rs);
			}
			return b;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by id = " + barId);
		}
	}
	
	
	public List<Bar> getBarListByDate(LocalDate localDate) throws DataAccessException{
		try {
			findByDate.setString(1, localDate.toString()); // converts the localDate to a String, since the Date in the
															// database schema is a String
			ResultSet rs = findByDate.executeQuery();
			List<Bar> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find shifts by date = " + localDate);
		}
		
		
	}
	
	public void createBar (Bar bar) throws SQLException {
		createBar.setInt(1, bar.getBarId());
		createBar.setString(2, bar.getName());
		createBar.setString(3, bar.getPhone());
		createBar.setString(4, bar.getEmail());
		createBar.setString(5, bar.getAddress());
		createBar.setString(6, bar.getCvr());
		createBar.execute();
		
	}
	
	public void update(Bar b) throws DataAccessException {
		final int barId = b.getBarId();
		final String name = b.getName();
		final String phone = b.getPhone();
		final String email = b.getEmail();
		final String address = b.getAddress();
		final String cvr = b.getCvr();
				
		try {
			//update Bar set 
			//barId = ?, name = ?, phone = ?
			//email = ?, address = ?, cvr = ? where barId = ?
			
			update.setInt(1, barId);
			update.setString(2, name);
			update.setString(3, phone);
			update.setString(4, email);
			update.setString(5, address);
			update.setString(6, cvr);
			
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update shift where id = " + barId);
		}
	}
	
	public void deleteBar(int barId) throws SQLException {
		deleteBar.setInt(1, barId);
		deleteBar.execute();
	}
	
	private Bar buildObject(ResultSet rs) throws SQLException {
		Bar b = new Bar(
				rs.getInt("barId"),
				rs.getString("name"),
				rs.getString("phone"),
				rs.getString("email"),
				rs.getString("address"),
				rs.getString("cvr")
				);
		return b;
	}
	
	private List<Bar> buildObjects(ResultSet rs) throws SQLException {
		List<Bar> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
