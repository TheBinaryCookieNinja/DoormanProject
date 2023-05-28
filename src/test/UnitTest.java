
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DataAccessException;
import database.ShiftDAO;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;


import java.sql.PreparedStatement;
import java.sql.SQLException;

class UnitTest {
    private ShiftDAO shiftDAO;
    private PreparedStatement updateMock;

    @BeforeEach
    void setup() throws SQLException {
        shiftDAO = new ShiftDAO();
        updateMock = mock(PreparedStatement.class);
        shiftDAO.setUpdateStatement(updateMock);
    }

    @Test
    void testUpdateDoormanId_SuccessfulUpdate() throws DataAccessException, SQLException {
        // Arrange
        int shiftId = 123;
        int doormanId = 456;

        // Stub the update execution to return a success
        doNothing().when(updateMock).setInt(1, doormanId);
        doNothing().when(updateMock).setInt(2, shiftId);

        // Act
        shiftDAO.updateDoormanId(shiftId, doormanId);

        // Assert
        verify(updateMock).executeUpdate();
        verify(updateMock).setInt(1, doormanId);
        verify(updateMock).setInt(2, shiftId);
        verify(DBConnection.getInstance()).startTransaction();
        verify(DBConnection.getInstance()).commitTransaction();
        verify(DBConnection.getInstance(), never()).rollbackTransaction();
    }

    @Test
    void testUpdateDoormanId_SQLExceptionThrown() throws SQLException {
        // Arrange
        int shiftId = 123;
        int doormanId = 456;

        // Stub the update execution to throw an SQLException
        doThrow(new SQLException()).when(updateMock).executeUpdate();

        // Act & Assert
        assertThrows(DataAccessException.class, () -> yourClass.updateDoormanId(shiftId, doormanId));
        verify(updateMock).executeUpdate();
        verify(updateMock).setInt(1, doormanId);
        verify(updateMock).setInt(2, shiftId);
        verify(DBConnection.getInstance()).startTransaction();
        verify(DBConnection.getInstance()).rollbackTransaction();
        verify(DBConnection.getInstance(), never()).commitTransaction();
    }
}
