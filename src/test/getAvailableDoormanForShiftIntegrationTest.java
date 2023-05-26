package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DataAccessException;
import database.DoormanDAO;
import model.Doorman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
public class getAvailableDoormanForShiftIntegrationTest {

	private DoormanDAO doormanDAO;
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "DMA-CSD-S221_10463670";
	private static final String serverAddress = "hildur.ucn.dk";
	private static final int    serverPort = 1433;
	private static final String userName = "DMA-CSD-S221_10463670";
	private static final String password = "Password1!";

	@BeforeEach
	public void setup() {
			// Set up your database connection
	        String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
	        String username = "username";
	        String password = "password";
	        try {
	            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
	            doormanRepository = new DoormanRepository(connection);
	        } catch (SQLException e) {
	            // Handle any setup exceptions
	            e.printStackTrace();
	        }
	    }
	
    @Test
    public void testGetAvailableDoormenForShift() {
        // Arrange
        LocalDate date = LocalDate.of(2023, 05, 15);
        int barId = 1;

        // Act
        List<Doorman> availableDoormen;
        try {
            availableDoormen = doormanRepository.getAvailableDoormenForShift(date, barId);
        } catch (DataAccessException e) {
            // Handle any exceptions thrown during the method call
            e.printStackTrace();
            Assertions.fail("Failed to retrieve available doormen for shift");
            return;
        }

        // Assert
        Assertions.assertNotNull(availableDoormen);
        Assertions.assertFalse(availableDoormen.isEmpty());
        // Add more assertions as needed to verify the results
    }
}
}
