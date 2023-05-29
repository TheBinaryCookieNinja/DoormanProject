package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitTestBuildObjects {

	
    private PreparedStatementStub psS;
    private ResultSetStub rsS;
    private String preparedStatement = "select shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId, signatureId from Shiftt";

    //Methods used for testing
    private ShiftStub buildObject(ResultSet rs) throws SQLException {
	    int shiftId = rs.getInt("shiftId");
	    String shiftDate = rs.getString("shiftDate");
	    String checkInTime = rs.getString("checkInTime");
	    String checkOutTime = rs.getString("checkOutTime");
	    int barId = rs.getInt("barId");
	    int doormanId = rs.getInt("doormanId");

	    ShiftStub s = new ShiftStub(shiftId, shiftDate, checkInTime, checkOutTime, barId, doormanId);
	    return s;
	}
	
	private List<ShiftStub> buildObjects(ResultSet rs) throws SQLException {
		List<ShiftStub> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
    //End of methods used for testing
	
    @Test
    @DisplayName("Testing if it can make a list of shifts with valid data")
    void testBuildObjectsMethodShiftDAO() throws SQLException {
        // Arrange
    	List<ShiftStub> assessList = new ArrayList<>();
    	assessList.add(new ShiftStub(1, "2023-05-01", "08:00:00", "16:00:00", 1, 1));
    	assessList.add(new ShiftStub(2, "2023-05-02", "08:00:00", "16:00:00", 2, 2));
    	assessList.add(new ShiftStub(3, "2023-05-03", "08:00:00", "16:00:00", 3, 3));
    	assessList.add(new ShiftStub(4, "2023-05-04", "08:00:00", "16:00:00", 4, 4));
    	assessList.add(new ShiftStub(5, "2023-05-05", "08:00:00", "16:00:00", 5, 5));
    	assessList.add(new ShiftStub(6, "2023-05-06", "08:00:00", "16:00:00", 6, 6));
    	assessList.add(new ShiftStub(7, "2023-05-07", "08:00:00", "16:00:00", 7, 7));
    	assessList.add(new ShiftStub(8, "2023-05-08", "08:00:00", "16:00:00", 8, 8));
    	assessList.add(new ShiftStub(9, "2023-05-09", "08:00:00", "16:00:00", 9, 9));
    	assessList.add(new ShiftStub(10, "2023-05-10", "08:00:00", "16:00:00", 10, 10));
    	assessList.add(new ShiftStub(11, "2023-05-11", "08:00:00", "16:00:00", 11, 11));
    	assessList.add(new ShiftStub(12, "2023-05-12", "08:00:00", "16:00:00", 12, 12));
    	assessList.add(new ShiftStub(13, "2023-05-13", "08:00:00", "16:00:00", 13, 13));
    	assessList.add(new ShiftStub(14, "2023-05-14", "08:00:00", "16:00:00", 14, 14));
    	assessList.add(new ShiftStub(15, "2023-05-15", "08:00:00", "16:00:00", 15, 15));
    					
    	psS = new PreparedStatementStub();
        rsS = psS.executeQuery(preparedStatement);
        
        // Act
        List<ShiftStub> shifts = buildObjects(rsS);

        // Assert
        Assertions.assertNotNull(shifts);
        Assertions.assertFalse(shifts.isEmpty());
        for (int i = 0; i>shifts.size();i++) {
            Assertions.assertNotNull(shifts.get(i).getShiftId() == assessList.get(i).getShiftId());
            Assertions.assertNotNull(shifts.get(i).getShiftDate().equals(assessList.get(i).getShiftDate()));
            Assertions.assertNotNull(shifts.get(i).getCheckInTime().equals(assessList.get(i).getCheckInTime()));
            Assertions.assertNotNull(shifts.get(i).getCheckOutTime().equals(assessList.get(i).getCheckOutTime()));
            Assertions.assertNotNull(shifts.get(i).getBarId() == assessList.get(i).getBarId());
            Assertions.assertNotNull(shifts.get(i).getDoormanId() == assessList.get(i).getDoormanId());
        }
    }
}