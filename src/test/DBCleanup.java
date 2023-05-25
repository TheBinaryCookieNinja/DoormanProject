package test;

import java.sql.SQLException;

import database.DBConnection;
/**
 * Utility class used by all the @Before methods in the test suite
 * that access the database. All tables are dropped, re-created and 
 * filled with data. By dropping the table, we reset the auto-generated
 * indices s.t. IDENTITY(1,1) starts on 1 and we can also count on the
 * ids of the records to be the same in all cases.
 * @author FBRM
 * @version 2023-05-21
 *
 */
public class DBCleanup {
	public static void main(String[] args) throws SQLException {
		cleanDB(); // call to the utility class that resets the database
		System.out.println("cleaned");
	}
	
	public static void cleanDB() throws SQLException {	
		e("drop table DoormanBlackList");
		e("drop table DoormanWishList");
		e("drop table UnavailabilityRequest");
		e("drop table Shiftt");
		e("drop table Signaturee");
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
			"CREATE TABLE Zipcode(\r\n"
			+ "  zipcode int PRIMARY KEY,\r\n"
			+ "  city VARCHAR(255) NOT NULL,\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE Addresss (\r\n"
			+ "  addressId int PRIMARY KEY,\r\n"
			+ "  zipcode int NOT NULL,\r\n"
			+ "  street VARCHAR(255) NOT NULL\r\n"
			+ "  FOREIGN KEY (zipcode) REFERENCES Zipcode(zipcode) \r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE Employee (\r\n"
			+ "  employeeId INT PRIMARY KEY,\r\n"
			+ "  f_name VARCHAR(255) NOT NULL,\r\n"
			+ "  l_name VARCHAR(255) NOT NULL,\r\n"
			+ "  phone VARCHAR(20) NOT NULL UNIQUE,\r\n"
			+ "  email VARCHAR(255) NOT NULL UNIQUE,\r\n"
			+ "  passcode VARCHAR(255) NOT NULL,\r\n"
			+ "  employeeType VARCHAR(7) NOT NULL CHECK(employeeType IN('Doorman', 'Boss')),\r\n"
			+ "  addressId INT NOT NULL,\r\n"
			+ "  FOREIGN KEY (addressId) REFERENCES Addresss(addressId) ON DELETE CASCADE\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE Boss (\r\n"
			+ "  employeeId INT PRIMARY KEY,\r\n"
			+ "  salary DECIMAL(10, 2) NOT NULL,\r\n"
			+ "  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE Doorman (\r\n"
			+ "  employeeId INT PRIMARY KEY,\r\n"
			+ "  hourlyRate DECIMAL(10, 2) NOT NULL,\r\n"
			+ "  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE Bar (\r\n"
			+ "  barId INT PRIMARY KEY,\r\n"
			+ "  namee VARCHAR(255) NOT NULL,\r\n"
			+ "  phone VARCHAR(20) NOT NULL,\r\n"
			+ "  email VARCHAR(255) NOT NULL UNIQUE,\r\n"
			+ "  cvr VARCHAR(255) NOT NULL UNIQUE,\r\n"
			+ "  addressId INT,\r\n"
			+ "  FOREIGN KEY (addressId) REFERENCES Addresss(addressId)\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE AvailableDates (\r\n"
			+ "  availableDatesId INT NOT NULL IDENTITY,\r\n"
			+ "  calenderDate DATE NOT NULL,\r\n"
			+ "  employeeId INT,\r\n"
			+ "  PRIMARY KEY (availableDatesId, calenderDate),\r\n"
			+ "  FOREIGN KEY (employeeId) REFERENCES Doorman(employeeId)\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE Signaturee (\r\n"
			+ "  signatureId INT PRIMARY KEY,\r\n"
			+ "  barManagerName VARCHAR(255) NOT NULL\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE Shiftt (\r\n"
			+ "  shiftId INT PRIMARY KEY,\r\n"
			+ "  shiftDate DATE NOT NULL,\r\n"
			+ "  checkInTime VARCHAR(8) NOT NULL,\r\n"
			+ "  checkOutTime VARCHAR(8) NOT NULL,\r\n"
			+ "  barId INT,\r\n"
			+ "  doormanId INT,\r\n"
			+ "  signatureId INT\r\n"
			+ "  FOREIGN KEY (signatureId) REFERENCES Signaturee(signatureId),\r\n"
			+ "  FOREIGN KEY (barId) REFERENCES Bar(barId),\r\n"
			+ "  FOREIGN KEY (doormanId) REFERENCES Doorman(employeeId),\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE UnavailabilityRequest (\r\n"
			+ "  uRequestId INT PRIMARY KEY,\r\n"
			+ "  reason VARCHAR(255) NOT NULL,\r\n"
			+ "  employeeId INT,\r\n"
			+ "  shiftId INT,\r\n"
			+ "  FOREIGN KEY (employeeId) REFERENCES Doorman(employeeId),\r\n"
			+ "  FOREIGN KEY (shiftId) REFERENCES Shiftt(shiftId)\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE DoormanWishlist (\r\n"
			+ "  BarId INT,\r\n"
			+ "  employeeId INT,\r\n"
			+ "  PRIMARY KEY (BarId, employeeId),\r\n"
			+ "  FOREIGN KEY (BarId) REFERENCES Bar(barId),\r\n"
			+ "  FOREIGN KEY (employeeId) REFERENCES Doorman(employeeId)\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "CREATE TABLE DoormanBlacklist (\r\n"
			+ "  BarId INT,\r\n"
			+ "  description VARCHAR(300) NOT NULL,\r\n"
			+ "  employeeId INT,\r\n"
			+ "  PRIMARY KEY (BarId, employeeId),\r\n"
			+ "  FOREIGN KEY (BarId) REFERENCES Bar(barId),\r\n"
			+ "  FOREIGN KEY (employeeId) REFERENCES Doorman(employeeId)\r\n"
			+ ");\r\n"
			+ "\r\n"
			+ "INSERT INTO Zipcode (zipcode, city) VALUES\r\n"
			+ "(1000, 'Copenhagen'),\r\n"
			+ "(2000, 'Frederiksberg'),\r\n"
			+ "(2100, 'Copenhagen'),\r\n"
			+ "(9000, 'Aalborg');\r\n"
			+ "\r\n"
			+ "INSERT INTO Addresss (addressId, zipcode, street) VALUES\r\n"
			+ "(1, 9000, 'Jomfru Ane Gade 12'),\r\n"
			+ "(2, 9000, 'Jomfru Ane Gade 16'),\r\n"
			+ "(3, 9000, 'Jomfru Ane Gade 23'),\r\n"
			+ "(4, 9000, 'Jomfru Ane Gade 5'),\r\n"
			+ "(5, 9000, 'Jomfru Ane Gade 12'),\r\n"
			+ "(6, 9000, 'Jomfru Ane Gade 42'),\r\n"
			+ "(7, 9000, 'Jomfru Ane Gade 15'),\r\n"
			+ "(8, 9000, 'Jomfru Ane Gade 14'),\r\n"
			+ "(9, 9000, 'Jomfru Ane Gade 11'),\r\n"
			+ "(10, 9000, 'Jomfru Ane Gade 7'),\r\n"
			+ "(11, 9000, 'Jomfru Ane Gade 6'),\r\n"
			+ "(12, 2100, 'Mathiasgade 10');\r\n"
			+ "\r\n"
			+ "INSERT INTO Employee (employeeId, f_name, l_name, phone, email, passcode, employeeType, addressId) VALUES\r\n"
			+ "(1, 'John', 'Doe', '12345678', 'john.doe@example.com', 'password', 'Doorman', 1),\r\n"
			+ "(2, 'Jane', 'Doe', '87654321', 'jane.doe@example.com', 'password', 'Boss', 2),\r\n"
			+ "(3, 'Alice', 'Smith', '23456789', 'alice.smith@example.com', 'password', 'Doorman', 3),\r\n"
			+ "(4, 'Bob', 'Johnson', '98765432', 'bob.johnson@example.com', 'password', 'Doorman', 4),\r\n"
			+ "(5, 'Bo', 'Son', '98765422', 'bob.johnson@ample.com', 'password', 'Doorman', 4),\r\n"
			+ "(6, 'Hans', 'Hansen', '25691962', 'hans.hansen@example.com', 'password', 'Doorman', 5),\r\n"
			+ "(7, 'Peter', 'Petersen', '12361265', 'peter.petersen@example.com', 'password', 'Doorman', 6),\r\n"
			+ "(8, 'Jakob', 'Jakobsen', '64285294', 'jakob.jakobsen@example.com', 'password', 'Doorman', 7),\r\n"
			+ "(9, 'Axel', 'Axelsen', '15930630', 'axel.axelsen@example.com', 'password', 'Doorman', 8),\r\n"
			+ "(10, 'Oliver', 'Oliversen', '12692030', 'oliver.oliversen@example.com', 'password', 'Doorman', 9),\r\n"
			+ "(11, 'Frederik', 'Frederiksen', '20952932', 'frederik.frederiksen@example.com', 'password', 'Doorman', 10),\r\n"
			+ "(12, 'Mathias', 'Mathiasen', '19657243', 'mathias.mathiasen@example.com', 'password', 'Doorman', 11);\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "INSERT INTO Doorman (employeeId, hourlyRate) VALUES\r\n"
			+ "(1, 120.00),\r\n"
			+ "(3, 50000.00),\r\n"
			+ "(4, 110.00),\r\n"
			+ "(5, 100.00),\r\n"
			+ "(6, 100.00),\r\n"
			+ "(7, 110.00),\r\n"
			+ "(8, 100.00),\r\n"
			+ "(9, 100.00),\r\n"
			+ "(10, 110.00),\r\n"
			+ "(11, 100.00),\r\n"
			+ "(12, 100.00);\r\n"
			+ "\r\n"
			+ "INSERT INTO Bar (barId, namee, phone, email, cvr, addressId) VALUES\r\n"
			+ "(1, 'Fabrikken', '11111111', 'bar.one@example.com', '11111111', 1),\r\n"
			+ "(2, 'Zurf', '22222222', 'bar.two@example.com', '22222222', 2),\r\n"
			+ "(3, 'El Mundo', '33333333', 'bar.three@example.com', '33333333', 3),\r\n"
			+ "(4, 'Sprutten', '44444444', 'bar.four@example.com', '444444444', 4),\r\n"
			+ "(5, 'Zwei grosse', '55555555', 'bar.five@example.com', '55555555', 5),\r\n"
			+ "(6, 'Wunderbar', '66666666', 'bar.six@example.com', '666666', 6),\r\n"
			+ "(7, 'Ambassade', '77777777', 'bar.seven@example.com', '77777777', 7),\r\n"
			+ "(8, 'Flamingo', '88888888', 'bar.eight@example.com', '88888888', 8),\r\n"
			+ "(9, 'Club Wolf', '999999999', 'bar.nine@example.com', '999999999', 9),\r\n"
			+ "(10, 'Under buret', '10101010', 'bar.ten@example.com', '10101010', 10),\r\n"
			+ "(11, 'Proud Mary', '11101110', 'bar.eleven@example.com', '11101110', 11),\r\n"
			+ "(12, 'Karaoke Bar', '12121212', 'bar.twelve@example.com', '12121212', 12);\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "INSERT INTO AvailableDates (calenderDate, employeeId) VALUES\r\n"
			+ "('2023-05-15', 1),\r\n"
			+ "('2023-05-15', 4),\r\n"
			+ "('2023-05-15', 5),\r\n"
			+ "('2023-05-15', 6),\r\n"
			+ "('2023-05-15', 7),\r\n"
			+ "('2023-05-15', 8),\r\n"
			+ "('2023-05-15', 9),\r\n"
			+ "('2023-05-15', 10),\r\n"
			+ "('2023-05-15', 11),\r\n"
			+ "('2023-05-15', 12),\r\n"
			+ "('2023-05-16', 1),\r\n"
			+ "('2023-05-16', 3),\r\n"
			+ "('2023-05-16', 4),\r\n"
			+ "('2023-05-16', 7),\r\n"
			+ "('2023-05-16', 9),\r\n"
			+ "('2023-05-16', 11),\r\n"
			+ "('2023-05-16', 12);\r\n"
			+ "\r\n"
			+ "INSERT INTO Shiftt (shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId) VALUES\r\n"
			+ "(1, '2023-05-15', '18:00', '23:00', 1, NULL),\r\n"
			+ "(2, '2023-05-15', '23:30', '03:00', 2, NULL),\r\n"
			+ "(3, '2023-05-15', '18:00', '23:00', 3, NULL),\r\n"
			+ "(4, '2023-05-15', '19:30', '01:00', 4, NULL),\r\n"
			+ "(5, '2023-05-15', '19:00', '23:30', 5, NULL),\r\n"
			+ "(6, '2023-05-15', '21:00', '03:00', 6, NULL),\r\n"
			+ "(7, '2023-05-15', '18:00', '23:00', 7, NULL),\r\n"
			+ "(8, '2023-05-15', '18:00', '23:30', 8, NULL),\r\n"
			+ "(9, '2023-05-15', '18:00', '23:00', 9, NULL),\r\n"
			+ "(10, '2023-05-15', '20:00', '01:00', 10, NULL),\r\n"
			+ "(11, '2023-05-15', '18:00', '01:00', 11, NULL),\r\n"
			+ "(12, '2023-05-16', '22:00', '03:00', 12, NULL),\r\n"
			+ "(13, '2023-05-16', '18:00', '01:00', 1, NULL),\r\n"
			+ "(14, '2023-05-16', '18:00', '01:00', 2, NULL),\r\n"
			+ "(15, '2023-05-16', '18:00', '01:00', 3, NULL),\r\n"
			+ "(16, '2023-05-16', '18:00', '01:00', 4, NULL),\r\n"
			+ "(17, '2023-05-16', '22:00', '03:00', 5, NULL),\r\n"
			+ "(18, '2023-05-16', '18:00', '01:00', 6, NULL),\r\n"
			+ "(19, '2023-05-16', '22:00', '03:00', 7, NULL),\r\n"
			+ "(20, '2023-05-16', '18:00', '01:00', 8, NULL),\r\n"
			+ "(21, '2023-05-16', '18:00', '01:00', 9, NULL),\r\n"
			+ "(22, '2023-05-16', '18:00', '01:00', 10, NULL),\r\n"
			+ "(23, '2023-05-16', '18:00', '01:00', 11, NULL),\r\n"
			+ "(24, '2023-05-16', '18:00', '01:00', 12, NULL);\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "INSERT INTO DoormanWishlist (BarId, employeeId) VALUES \r\n"
			+ "(1, 1),\r\n"
			+ "(1, 5),\r\n"
			+ "(1, 3),\r\n"
			+ "(2, 3),\r\n"
			+ "(3, 4),\r\n"
			+ "(4, 5),\r\n"
			+ "(5, 6),\r\n"
			+ "(6, 7),\r\n"
			+ "(7, 10),\r\n"
			+ "(8, 11),\r\n"
			+ "(9, 6),\r\n"
			+ "(10, 12),\r\n"
			+ "(11, 9),\r\n"
			+ "(12, 8);\r\n"
			+ "\r\n"
			+ "INSERT INTO DoormanBlacklist (BarId, description, employeeId) VALUES \r\n"
			+ "(1, 'Rude Towards Staff', 4),\r\n"
			+ "(2, 'Poor Service', 4),\r\n"
			+ "(3, 'Hostile Attitude', 3),\r\n"
			+ "(4, 'Not a good fit for venue', 4),\r\n"
			+ "(5, 'Hostile towards manager', 5);\r\n"
			+ "\r\n"
			+ "\r\n"
	};
}

