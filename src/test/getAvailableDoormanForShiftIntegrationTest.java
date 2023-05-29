package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DataAccessException;
import database.DoormanDAO;
import model.Doorman;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
public class getAvailableDoormanForShiftIntegrationTest {

	private DoormanDAO doormanDAO;

	@BeforeEach
	public void setup() throws DataAccessException, SQLException {
		doormanDAO = new DoormanDAO();
		DBCleanup.main(null);
	    }
	
    @Test
    public void testGetAvailableDoormenForShiftNotEmptyAndObjectsNotEmpty() {
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
        for (Doorman doorman : availableDoormen) {
            Assertions.assertNotNull(doorman.getF_name());
            Assertions.assertNotNull(doorman.getL_name());
            Assertions.assertNotNull(doorman.getEmployeeId());
            Assertions.assertNotNull(doorman.getEmail());
            Assertions.assertNotNull(doorman.getPhone());
            Assertions.assertNotNull(doorman.getPasscode());
            Assertions.assertNotNull(doorman.getAddress());
            Assertions.assertNotNull(doorman.getHourlyRate());
        }
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

