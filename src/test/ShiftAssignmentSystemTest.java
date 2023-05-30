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
    @DisplayName("Assign a valid shift to a valid doorman with valid date and bar")
    public void testShiftAssignment() throws DataAccessException, SQLException {
        
    	// Arrange
    	ShiftCtrl shiftCtrl = new ShiftCtrl();
        
        //Valid inputs:
        LocalDate date = LocalDate.of(2023, 05, 16);
        int barId = 3;
        int shiftId = 5;
        int doormanId = 3;
        
        // Act 
        // Gets a list of shifts
        List<Shift> shifts = shiftCtrl.getShiftsByDate(date);
        //chooses one of them
        Shift shift = shifts.get(shiftId);
        
        // Retrieve an existing bar
        List<Bar> bars = shiftCtrl.getAllBars();
        // Chooses one of the bars
        Bar bar = bars.get(barId); 
        
        // Retrieve a list of available doormen for the shift date and bar
        List<Doorman> availableDoormen = shiftCtrl.getAvailableDoormenForShift(date, bar.getBarId());
        
        // Select the first available doorman for assignment
        Doorman doorman = availableDoormen.get(doormanId);
        
        // Assign the shift to the selected doorman
        boolean assignmentSuccessful = shiftCtrl.confirmShift(doorman.getEmployeeId(), shift.getShiftId());
        

        // Assert
        // Check if the assignment was successful
        Assertions.assertTrue(assignmentSuccessful, "Shift assignment failed");

        // Retrieve the updated shift from the database
        List<Shift> newShifts = shiftCtrl.getShiftsByDate(date);
       		Shift assignedShift = null;
    		   for (Shift newShift : newShifts) {
    			   if (newShift.getShiftId() == shift.getShiftId())
    				  assignedShift = newShift;
    		   }

        // Verify that the shift has been assigned to the doorman
        Assertions.assertEquals(doorman.getEmployeeId(), assignedShift.getDoormanId(), "Incorrect doorman assignment");
    }
    
    @Test
    @DisplayName("Assign a shift to a doorman with invalid bar")
    public void testShiftAssignmentBarFail() throws DataAccessException, SQLException {
        
    	// Arrange
    	ShiftCtrl shiftCtrl = new ShiftCtrl();

        
        //Invalid inputs:
        LocalDate date = LocalDate.of(2023, 05, 16);
        int barId = 1000;
        int shiftId = 4;
        int doormanId = 3;
        
        // Act 
        // Gets a list of shifts
        List<Shift> shifts = shiftCtrl.getShiftsByDate(date);
        //chooses one of them
        Shift shift = shifts.get(shiftId);
        
        // Retrieve an existing bar
        List<Bar> bars = shiftCtrl.getAllBars();
        // Chooses one of the bars
        Bar bar = bars.get(barId); 
        
        // Retrieve a list of available doormen for the shift date and bar
        List<Doorman> availableDoormen = shiftCtrl.getAvailableDoormenForShift(date, bar.getBarId());
        
        // Select the first available doorman for assignment
        Doorman doorman = availableDoormen.get(doormanId);
        
        // Assign the shift to the selected doorman
        boolean assignmentSuccessful = shiftCtrl.confirmShift(doorman.getEmployeeId(), shift.getShiftId());
        

        // Assert
        // Check if the assignment was successful
        Assertions.assertTrue(assignmentSuccessful, "Shift assignment failed");

        // Retrieve the updated shift from the database
        List<Shift> newShifts = shiftCtrl.getShiftsByDate(date);
       		Shift assignedShift = null;
    		   for (Shift newShift : newShifts) {
    			   if (newShift.getShiftId() == shift.getShiftId())
    				  assignedShift = newShift;
    		   }

        // Verify that the shift has been assigned to the doorman
        Assertions.assertNotEquals(doorman.getEmployeeId(), assignedShift.getDoormanId(), "Incorrect doorman assignment");
    }
    
    @Test
    @DisplayName("Assign a shift to a doorman with invalid date")
    public void testShiftAssignmentDateFail() throws DataAccessException, SQLException {
        
    	// Arrange
    	ShiftCtrl shiftCtrl = new ShiftCtrl();

        
        //Invalid inputs:
        LocalDate date = LocalDate.of(2023, 05, 33);
        int barId = 3;
        int shiftId = 4;
        int doormanId = 3;
        
        // Act 
        // Gets a list of shifts
        List<Shift> shifts = shiftCtrl.getShiftsByDate(date);
        //chooses one of them
        Shift shift = shifts.get(shiftId);
        
        // Retrieve an existing bar
        List<Bar> bars = shiftCtrl.getAllBars();
        // Chooses one of the bars
        Bar bar = bars.get(barId); 
        
        // Retrieve a list of available doormen for the shift date and bar
        List<Doorman> availableDoormen = shiftCtrl.getAvailableDoormenForShift(date, bar.getBarId());
        
        // Select the first available doorman for assignment
        Doorman doorman = availableDoormen.get(doormanId);
        
        // Assign the shift to the selected doorman
        boolean assignmentSuccessful = shiftCtrl.confirmShift(doorman.getEmployeeId(), shift.getShiftId());
        

        // Assert
        // Check if the assignment was successful
        Assertions.assertTrue(assignmentSuccessful, "Shift assignment failed");

        // Retrieve the updated shift from the database
        List<Shift> newShifts = shiftCtrl.getShiftsByDate(date);
       		Shift assignedShift = null;
    		   for (Shift newShift : newShifts) {
    			   if (newShift.getShiftId() == shift.getShiftId())
    				  assignedShift = newShift;
    		   }

        // Verify that the shift has been assigned to the doorman
        Assertions.assertNotEquals(doorman.getEmployeeId(), assignedShift.getDoormanId(), "Incorrect doorman assignment");
    }
    
    @Test
    @DisplayName("Assign a shift to an invalid doorman")
    public void testShiftAssignmentDoormanFail() throws DataAccessException, SQLException {
        
    	// Arrange
    	ShiftCtrl shiftCtrl = new ShiftCtrl();

        
        //Invalid inputs:
        LocalDate date = LocalDate.of(2023, 05, 16);
        int barId = 3;
        int shiftId = 4;
        int doormanId = 1000;
        
        // Act 
        // Gets a list of shifts
        List<Shift> shifts = shiftCtrl.getShiftsByDate(date);
        //chooses one of them
        Shift shift = shifts.get(shiftId);
        
        // Retrieve an existing bar
        List<Bar> bars = shiftCtrl.getAllBars();
        // Chooses one of the bars
        Bar bar = bars.get(barId); 
        
        // Retrieve a list of available doormen for the shift date and bar
        List<Doorman> availableDoormen = shiftCtrl.getAvailableDoormenForShift(date, bar.getBarId());
        
        // Select the first available doorman for assignment
        Doorman doorman = availableDoormen.get(doormanId);
        
        // Assign the shift to the selected doorman
        boolean assignmentSuccessful = shiftCtrl.confirmShift(doorman.getEmployeeId(), shift.getShiftId());
        

        // Assert
        // Check if the assignment was successful
        Assertions.assertTrue(assignmentSuccessful, "Shift assignment failed");

        // Retrieve the updated shift from the database
        List<Shift> newShifts = shiftCtrl.getShiftsByDate(date);
       		Shift assignedShift = null;
    		   for (Shift newShift : newShifts) {
    			   if (newShift.getShiftId() == shift.getShiftId())
    				  assignedShift = newShift;
    		   }

        // Verify that the shift has been assigned to the doorman
        Assertions.assertNotEquals(doorman.getEmployeeId(), assignedShift.getDoormanId(), "Incorrect doorman assignment");
    } 
    @Test
    @DisplayName("Assign an invalid shift to a doorman")
    public void testShiftAssignmentShiftFail() throws DataAccessException, SQLException {
        
    	// Arrange
    	ShiftCtrl shiftCtrl = new ShiftCtrl();

        
        //Invalid inputs:
        LocalDate date = LocalDate.of(2023, 05, 16);
        int barId = 4;
        int shiftId = 1000;
        int doormanId = 3;
        
        // Act 
        // Gets a list of shifts
        List<Shift> shifts = shiftCtrl.getShiftsByDate(date);
        //chooses one of them
        Shift shift = shifts.get(shiftId);
        
        // Retrieve an existing bar
        List<Bar> bars = shiftCtrl.getAllBars();
        // Chooses one of the bars
        Bar bar = bars.get(barId); 
        
        // Retrieve a list of available doormen for the shift date and bar
        List<Doorman> availableDoormen = shiftCtrl.getAvailableDoormenForShift(date, bar.getBarId());
        
        // Select the first available doorman for assignment
        Doorman doorman = availableDoormen.get(doormanId);
        
        // Assign the shift to the selected doorman
        boolean assignmentSuccessful = shiftCtrl.confirmShift(doorman.getEmployeeId(), shift.getShiftId());
        

        // Assert
        // Check if the assignment was successful
        Assertions.assertTrue(assignmentSuccessful, "Shift assignment failed");

        // Retrieve the updated shift from the database
        List<Shift> newShifts = shiftCtrl.getShiftsByDate(date);
       		Shift assignedShift = null;
    		   for (Shift newShift : newShifts) {
    			   if (newShift.getShiftId() == shift.getShiftId())
    				  assignedShift = newShift;
    		   }

        // Verify that the shift has been assigned to the doorman
        Assertions.assertNotEquals(doorman.getEmployeeId(), assignedShift.getDoormanId(), "Incorrect doorman assignment");
    }
}