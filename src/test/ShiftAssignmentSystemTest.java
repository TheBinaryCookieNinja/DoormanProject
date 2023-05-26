package test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import controller.BarCtrl;
import controller.DoormanCtrl;
import controller.ShiftCtrl;
import database.DataAccessException;
import model.Bar;
import model.Doorman;
import model.Shift;
import database.ShiftDAO;
public class ShiftAssignmentSystemTest {

    @Test
    @DisplayName("Assign a shift to a doorman")
    public void testShiftAssignment() throws DataAccessException, SQLException {
        // Arrange
    	ShiftCtrl shiftCtrl = new ShiftCtrl();
    	 
        LocalDate date = LocalDate.of(2023, 05, 15);
        int barId = 1;
        
        // Act
        
        // Create a new shift
        List<Shift> shifts = shiftCtrl.getShiftsByDate(date);
        Shift shift = shifts.get(barId);
        
        // Retrieve an existing bar
        List<Bar> bars = shiftCtrl.getAllBars();
        
        Bar bar = bars.get(0); // Choose the first bar in the list
        
     // Retrieve a list of available doormen for the shift date and bar
        List<Doorman> availableDoormen = shiftCtrl.getAvailableDoormenForShift(date, bar.getBarId());
        
        // Select the first available doorman for assignment
        Doorman doorman = availableDoormen.get(0);
        
        // Assign the shift to the selected doorman
        boolean assignmentSuccessful = shiftCtrl.confirmShift(doorman.getEmployeeId(), newShift.getShiftId(), date);
        

        // Assert
        // Check if the assignment was successful
        Assertions.assertTrue(assignmentSuccessful, "Shift assignment failed");

        // Retrieve the updated shift from the database
        Shift assignedShift = shiftCtrl.getShiftById(newShift.getShiftId());

        // Verify that the shift has been assigned to the doorman
        Assertions.assertEquals(doorman.getEmployeeId(), assignedShift.getDoormanId(), "Incorrect doorman assignment");
    }
}