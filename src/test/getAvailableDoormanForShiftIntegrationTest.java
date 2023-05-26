package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBConnection;
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

	@BeforeEach
	public void setup() throws DataAccessException {
		doormanDAO = new DoormanDAO();
	    }
	
    @Test
    public void testGetAvailableDoormenForShiftNotEmpty() {
        // Arrange
        LocalDate date = LocalDate.of(2023, 05, 15);
        int barId = 1;

        // Act
        List<Doorman> availableDoormen;
        try {
            availableDoormen = doormanDAO.getAvailableDoormenForShift(date, barId);
        } catch (DataAccessException e) {
            // Handle any exceptions thrown during the method call
            e.printStackTrace();
            Assertions.fail("Failed to retrieve available doormen for shift");
            return;
        }

        // Assert
        Assertions.assertNotNull(availableDoormen);
        Assertions.assertFalse(availableDoormen.isEmpty());
        
    }
    
    @Test
    public void testGetAvailableDoormenForShiftEmpty() {
        // Arrange
        LocalDate date = LocalDate.of(2022, 05, 15);
        int barId = 1;

        // Act
        List<Doorman> availableDoormen;
        try {
            availableDoormen = doormanDAO.getAvailableDoormenForShift(date, barId);
        } catch (DataAccessException e) {
            // Handle any exceptions thrown during the method call
            e.printStackTrace();
            Assertions.fail("Failed to retrieve available doormen for shift");
            return;
        }

        // Assert
        Assertions.assertNotNull(availableDoormen);
        Assertions.assertFalse(availableDoormen.size()>0);        
    }
}

