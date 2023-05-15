package Test;

import java.sql.SQLException;

import database.DBConnection;
/**
 * Utility class used by all the @Before methods in the test suite
 * that access the database. All tables are dropped, re-created and 
 * filled with data. By dropping the table, we reset the auto-generated
 * indices s.t. IDENTITY(1,1) starts on 1 and we can also count on the
 * ids of the records to be the same in all cases.
 * @author knol
 * @version 2017-02-20
 *
 */
public class DBCleanup {
	public static void main(String[] args) throws SQLException {
		cleanDB(); // call to the utility class that resets the database
		System.out.println("cleaned");
	}
	public static void cleanDB() throws SQLException {
		e("drop table Signaturee");
		e("drop table DoormanBlackList");
		e("drop table DoormanWishList");
		e("drop table UnavailabilityRequest");
		e("drop table Shiftt");
		e("drop table AvailableDates");
		e("drop table Bar");
		e("drop table Doorman");
		e("drop table Boss");
		e("drop table Employee");
		e("drop table Addresss");
		e("drop table Zipcode");
		for(int i = 0 ; i < script.length; i++) {
			e(script[i]);
		}
	}

	private static void e(String sql) throws SQLException {
		database.DBConnection.getInstance().getConnection().createStatement().executeUpdate(sql);
	}
	
	private static final String[] script = {


	};
}
