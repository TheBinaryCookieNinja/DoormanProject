package test;

import java.sql.SQLException;

import database.DBConnection;

public class DBCleanup {
	public static void main(String[] args) throws SQLException {
		cleanDB(); // call to the utility class that resets the database
		System.out.println("cleaned");
	}
	public static void cleanDB() throws SQLException {
		e("drop table BarBlacklist");
		e("drop table BarWishlist");
		e("drop table UnavailabilityRequest");
		e("drop table Shiftt");
		e("drop table AvailableDates");
		e("drop table Bar");
		e("drop table Doorman");
		e("drop table Boss");
		e("drop table Employee");
		e("drop table Address");
		e("drop table Zipcode");
		for(int i = 0 ; i < script.length; i++) {
			e(script[i]);
		}
	}

	private static void e(String sql) throws SQLException {
		DBConnection.getInstance().getConnection().createStatement().executeUpdate(sql);
	}
	
	private static final String[] script = {


	};
}
