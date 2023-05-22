package Test;

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
			"create table Zipcode(zipcode int primary key, city VARCHAR(255) NOT NULL,)",
			"create table Addresss addressId int PRIMARY KEY, zipcode int NOT NULL, street VARCHAR(255) NOT NULL FOREIGN KEY (zipcode) REFERENCES Zipcode(zipcode)",
			"create table Employee (employeeId INT PRIMARY KEY, f_name VARCHAR(255) NOT NULL, l_name VARCHAR(255) NOT NULL, phone VARCHAR(20) NOT NULL UNIQUE, email VARCHAR(255) NOT NULL UNIQUE, passcode VARCHAR(255) NOT NULL, employeeType VARCHAR(7) NOT NULL CHECK(employeeType IN('Doorman', 'Boss')), addressId INT NOT NULL, FOREIGN KEY (addressId) REFERENCES Addresss(addressId) ON DELETE CASCADE",
			"create table Boss ( employeeId INT PRIMARY KEY, salary DECIMAL(10, 2) NOT NULL, FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE",
			"create table Doorman (employeeId INT PRIMARY KEY, hourlyRate DECIMAL(10, 2) NOT NULL, FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE",
			"create table Bar (barId INT PRIMARY KEY, namee VARCHAR(255) NOT NULL, phone VARCHAR(20) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE, cvr VARCHAR(255) NOT NULL UNIQUE, addressId INT, FOREIGN KEY (addressId) REFERENCES Addresss(addressId)",
			"create table AvailableDates (availableDatesId INT NOT NULL, calenderDate DATE NOT NULL, employeeId INT, PRIMARY KEY (availableDatesId, calenderDate), FOREIGN KEY (employeeId) REFERENCES Doorman(employeeId)",
			"create table Signaturee (signatureId INT PRIMARY KEY, barManagerName VARCHAR(255) NOT NULL",
			"create table Shiftt (shiftId INT PRIMARY KEY, shiftDate DATE NOT NULL, checkInTime TIME NOT NULL, checkOutTime TIME NOT NULL, barId INT, doormanId INT, signatureId INT, FOREIGN KEY (signatureId) REFERENCES Signaturee(signatureId), FOREIGN KEY (barId) REFERENCES Bar(barId), FOREIGN KEY (doormanId) REFERENCES Doorman(employeeId)",
			"create table UnavailabilityRequest (uRequestId INT PRIMARY KEY, reason VARCHAR(255) NOT NULL, employeeId INT, shiftId INT, FOREIGN KEY (employeeId) REFERENCES Doorman(employeeId), FOREIGN KEY (shiftId) REFERENCES Shiftt(shiftId)",
			"create table DoormanWishList (BarId INT, employeeId INT, PRIMARY KEY (BarId, employeeId), FOREIGN KEY (BarId) REFERENCES Bar(barId), FOREIGN KEY (employeeId) REFERENCES Doorman(employeeId)",
			"create table DoormanBlackList (BarId INT, description VARCHAR(300) NOT NULL, employeeId INT, PRIMARY KEY (BarId, employeeId), FOREIGN KEY (BarId) REFERENCES Bar(barId), FOREIGN KEY (employeeId) REFERENCES Doorman(employeeId)",
			"insert into Zipcode values (1000, 'Copenhagen'), (2000, 'Frederiksberg'), (2100, 'Copenhagen'), (2200, 'Copenhagen')",
			"insert into Addresss values (1, 1000, 'Vesterbrogade 10'), (2, 2000, 'Falkoner Alle 20'), (3, 2100, 'Østerbrogade 30'), (4, 2200, 'Nørrebrogade 40'), (5, 2300, 'Islandsgade 10'), (6, 2400, 'Petersgade 10'), (7, 2500, 'Jakobsgade 10'), (8, 2600, 'Axelsgade 10'), (9, 2700, 'Oliversgade 10'), (10, 2800, 'Frederiksgade 10'), (11, 2900, 'Mathiasgade 10');",
			"insert into Employee values (1, 'John', 'Doe', '12345678', 'john.doe@example.com', 'password', 'Doorman', 1), (2, 'Jane', 'Doe', '87654321', 'jane.doe@example.com', 'password', 'Boss', 2), (3, 'Alice', 'Smith', '23456789', 'alice.smith@example.com', 'password', 'Doorman', 3), (4, 'Bob', 'Johnson', '98765432', 'bob.johnson@example.com', 'password', 'Doorman', 4), (5, 'Bo', 'Son', '98765422', 'bob.johnson@ample.com', 'password', 'Doorman', 4), (6, 'Hans', 'Hansen', '25691962', 'hans.hansen@example.com', 'Doorman', 5), (7, 'Peter', 'Petersen', '12361265', 'peter.petersen@example.com', 'Doorman', 6), (8, 'Jakob', 'Jakobsen', '64285294', 'jakob.jakobsen@example.com', 'Doorman', 7), (9, 'Axel', 'Axelsen', '15930630', 'axel.axelsen@example.com', 'Doorman', 8), (10, 'Oliver', 'Oliversen', '12692030', 'oliver.oliversen@example.com', 'Doorman', 9), (11, 'Frederik', 'Frederiksen', '20952932', 'frederik.frederiksen@example.com', 'Doorman', 10), (12, 'Mathias', 'Mathiasen', '19657243', 'mathias.mathiasen@example.com', 'Doorman' , 11);",
			"insert into Doorman values (1, 120.00), (2, 50000.00), (3, 110.00), (4, 100.00), (5, 100.00);",
			"insert into Bar values ((1, 'Fabrikken', '11111111', 'bar.one@example.com', '11111111', 1), (2, 'Zurf', '22222222', 'bar.two@example.com', '22222222', 1), (3, 'El Mundo', '33333333', 'bar.three@example.com', '33333333', 1), (4, 'Sprutten', '44444444', 'bar.four@example.com', '444444444', 1), (5, 'Zwei grosse', '55555555', 'bar.five@example.com', '55555555', 2), (6, 'Wunderbar', '66666666', 'bar.six@example.com', '666666', 2), (7, 'Ambassade', '77777777', 'bar.seven@example.com', '77777777', 2), (8, 'Flamingo', '88888888', 'bar.eight@example.com', '88888888', 2), (9, 'Club Wolf', '999999999', 'bar.nine@example.com', '999999999', 3), (10, 'Under buret', '10101010', 'bar.ten@example.com', '10101010', 3), (11, 'Proud Mary', '11101110', 'bar.eleven@example.com', '11101110', 3), (12, 'Karaoke Bar', '12121212', 'bar.twelve@example.com', '12121212', 3);",
			"insert into AvailableDates values (1, '2023-05-15', 1), (2, '2023-05-15', 2), (3, '2023-05-15', 3), (4, '2023-05-15', 4), (5, '2023-05-15', 5), (6, '2023-05-15', 6), (7, '2023-05-15', 7), (8, '2023-05-15', 8), (9, '2023-05-15', 9), (10, '2023-05-15', 10), (11, '2023-05-15', 11), (12, '2023-05-15', 12), (13, '2023-05-16', 1), (14, '2023-05-15', 2), (15, '2023-05-15', 3), (16, '2023-05-15', 4), (17, '2023-05-15', 7), (18, '2023-05-15', 9), (19, '2023-05-15', 11), (20, '2023-05-15', 12);",
			"insert into Shiftt values (1, '2023-05-15', '18:00', '23:00', 1, NULL), (2, '2023-05-15', '20:00', '01:00', 2, NULL), (3, '2023-05-15', '19:00', '00:00', 3, NULL), (4, '2023-05-15', '21:00', '05.45', 4, NULL), (5, '2023-05-15', '22:00', '05.45', 5, NULL), (6, '2023-05-15', '22:30', '05.45', 6, NULL), (7, '2023-05-15', '23:00', '05.00', 7, NULL), (8, '2023-05-15', '20:00', '05.00', 8, NULL), (9, '2023-05-15', '18:00', '23:00', 9, NULL), (10, '2023-05-15', '20:00', '01:00', 10, NULL), (11, '2023-05-15', '19:00', '00:00', 11, NULL), (12, '2023-05-16', '21:00', '05.45', 12, NULL), (13, '2023-05-16', '22:00', '05.45', 1, NULL), (14, '2023-05-16', '22:30', '05.45', 2, NULL), (15, '2023-05-16', '23:00', '05.00', 3, NULL), (16, '2023-05-16', '20:00', '05.30', 4, NULL), (17, '2023-05-16', '20:00', '04.00', 5, NULL), (18, '2023-05-16', '20:00', '03.00', 6, NULL), (19, '2023-05-16', '20:00', '05.00', 7, NULL), (20, '2023-05-16', '20:00', '02.00', 8, NULL), (21, '2023-05-16', '20:00', '05.45', 9, NULL), (22, '2023-05-16', '20:00', '01.00', 10, NULL), (23, '2023-05-16', '20:00', '05.45', 11, NULL), (24, '2023-05-16', '20:00', '05.15', 12, NULL);",
			"insert into DoormanWishlist values (1, 1), (1, 2), (1, 3), (2, 2), (3, 3), (4, 4), (5, 5), (6, 7), (7, 10), (8, 11), (9, 6), (10, 12), (11, 9), (12, 8);",
			"insert into DoormanBlackList values (1, 'Rude Towards Staff', 4), (2, 'Poor Service', 2), (3, 'Hostile Attitude', 3), (4, 'Not a good fit for venue', 4), (5, 'Hostile towards manager', 5);"
	};
}

