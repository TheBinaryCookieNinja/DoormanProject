package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Bar;

public class BarDAO {

	private static final String findByIdQ = 
			"select barId, namee, phone, email, addressId, cvr from Bar where barId = ?";

	
	private PreparedStatement findById;
	
	public BarDAO() throws DataAccessException {
		try {
			findById = DBConnection.getInstance().getConnection()
					.prepareStatement(findByIdQ);
			} catch (SQLException e) {
				throw new DataAccessException(e, "Could not prepare statement");
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
	
	private Bar buildObject(ResultSet rs) throws SQLException {
		Bar bar = new Bar(rs.getInt("barId"), rs.getString("namee"), rs.getString("phone"), rs.getString("email"), rs.getInt("addressId"), rs.getString("cvr"));
				
		System.out.println();
		return bar;
	}
}
