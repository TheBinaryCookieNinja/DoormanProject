package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bar;

public class BarDAO {

	private static final String findAllQ = 
			"select barId, namee, phone, email, cvr, street, addresss.zipcode, city  from Bar as b left join Addresss on (Addresss.addressId = b.addressId) left join Zipcode on (zipcode.zipcode = addresss.zipcode)";
	
	private PreparedStatement findAll;
	
	public BarDAO() throws DataAccessException {
		try {
			findAll = DBConnection.getInstance().getConnection()
					.prepareStatement(findAllQ);
			} catch (SQLException e) {
				throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	
	public List<Bar> getAll() throws DataAccessException {
	ResultSet rs;
	try {
		rs = findAll.executeQuery();
		List<Bar> res = buildObjects(rs);
		return res;
	} catch (SQLException e) {
		throw new DataAccessException(e, "Could not retrieve all bars");
		}
	}
	
	private Bar buildObject(ResultSet rs) throws SQLException {
		Bar bar = new Bar(rs.getInt("barId"), rs.getString("namee"), rs.getString("phone"), rs.getString("email"), rs.getString("street") + ", " + rs.getString("city") + ", " + rs.getString("zipcode"), rs.getString("cvr"));
				
		System.out.println();
		return bar;
	}
	
	private List<Bar> buildObjects(ResultSet rs) throws SQLException {
		List<Bar> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
}
