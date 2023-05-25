package database;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseLock {
	private static final int NUM_LOCKS = 10; // Number of locks to use
    private static final Lock[] locks = new ReentrantLock[NUM_LOCKS]
   
    		
    for (int i = 0; i < NUM_LOCKS; i++) {
        locks[i] = new ReentrantLock(true); // Initializes each lock as a fair lock
    } // Array of locks for lock striping
    
    public static void acquireLock(int lockId) {
    	int lockIndex = calculateLockIndex(lockId);
    	locks[lockIndex].lock(); // Acquire the specific lock
    }

    public static void releaseLock(int lockId) {
    	int lockIndex = calculateLockIndex(lockId);
    	locks[lockIndex].unlock(lockId); // Release the specific lock in the finally block
    }
    
    private static int calculateLockIndex(int id) {
	    int hash = id ^ (id >>> 16); // Apply a hashing function to the ID
	    return Math.abs(hash) % NUM_LOCKS; // Calculate the lock index based on the hashed value
	}
}}
