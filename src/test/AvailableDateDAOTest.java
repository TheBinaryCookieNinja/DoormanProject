package test;

import database.AvailableDateDAO;
import database.DataAccessException;
import database.DoormanDAO;
import model.AvailableDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AvailableDateDAOTest {

    private static final int NUM_THREADS = 40;
    private static final int NUM_ITERATIONS = 12;
    private int initialVersion;
    
    @BeforeEach
    void setUp() throws SQLException {
        // Set up any necessary test fixtures or dependencies
        DBCleanup.main(null);
    }
    
    @Test
    @DisplayName("test concurrent access to DAO with lock")
    public void testConcurrentAccessWithLock() throws InterruptedException, DataAccessException {
        // Arrange
    	AtomicReference<AvailableDate> availableDateRef = new AtomicReference<>(new AvailableDate(0, null, 0));
        AvailableDateDAO availableDateDAO = new AvailableDateDAO();
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        CountDownLatch latch = new CountDownLatch(NUM_THREADS);
        AtomicInteger readCounter = new AtomicInteger(0);
        AtomicInteger writeCounter = new AtomicInteger(0);

        // Act
        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.submit(() -> {
                try {
                    latch.countDown();
                    latch.await(); // Wait for all threads to be ready

                    for (int j = 0; j < NUM_ITERATIONS; j++) {
                        // read operation
                    	AvailableDate currentAvailableDate = availableDateDAO.findById(1);
                        initialVersion = currentAvailableDate.getVersion();
                        
                        readCounter.incrementAndGet();

                        // write operation
                        try {
                        	currentAvailableDate.setVersion(initialVersion + 1);
							availableDateDAO.createAvailableDate(new AvailableDate(0, Date.valueOf(LocalDate.of(j, 05, 10)), 1));
							writeCounter.incrementAndGet();
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

        
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            
        }

        // Assert
        Assertions.assertEquals(NUM_THREADS * NUM_ITERATIONS, readCounter.get(), "Incorrect number of reads");
        Assertions.assertEquals(NUM_THREADS * NUM_ITERATIONS, writeCounter.get(), "Incorrect number of writes");
        Assertions.assertTrue(initialVersion <= availableDateRef.get().getVersion(), "Dirty read detected");
    }
}