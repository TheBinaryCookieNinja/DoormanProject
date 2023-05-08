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
import model.Group;
import model.Person;
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
	
		try {
			//update person set 
			//name = ?, email = ?, phone = ? , 
			//birth_date = ?, groups_id = ? where id = ?"
			update.setString(1, name);
			
			
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update person where id = " + id);
		}

	}
	
	private Doorman buildObject(ResultSet rs) throws SQLException {
		Doorman d = new Doorman(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getString("email"),
				rs.getString("phone"),
				rs.getDate("birth_date").toLocalDate(),
				new Shift(rs.getInt("groups_id"), null, null)
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