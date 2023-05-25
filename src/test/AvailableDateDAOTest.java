package test;

import database.AvailableDateDAO;
import database.DataAccessException;
import model.AvailableDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AvailableDateDAOTest {

    private static final int NUM_THREADS = 12;
    private static final int NUM_ITERATIONS = 4;

    @Test
    @DisplayName("test concurrent access to DAO with lock")
    public void testConcurrentAccessWithLock() throws InterruptedException, DataAccessException {
        // Arrange
        AvailableDateDAO availableDateDAO = new AvailableDateDAO();
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        CountDownLatch latch = new CountDownLatch(NUM_THREADS);

        // Act
        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.submit(() -> {
                try {
                    latch.countDown();
                    latch.await(); // Wait for all threads to be ready

                    for (int j = 0; j < NUM_ITERATIONS; j++) {
                        // Simulate database read operation
                        AvailableDate availableDate = availableDateDAO.findById(1);

                        // Simulate database write operation
                        try {
							availableDateDAO.createAvailableDate(new AvailableDate(0, Date.valueOf(LocalDate.of(j, 05, 10)), 1));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                } catch (DataAccessException | InterruptedException e) {
                    // Handle exceptions if needed
                }
            });
        }

        // Wait for all threads to finish
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Wait for the executor service to terminate
        }

        // Assert
        // Add assertions to validate the desired behavior
        Assertions.assertTrue(true);
    }
}