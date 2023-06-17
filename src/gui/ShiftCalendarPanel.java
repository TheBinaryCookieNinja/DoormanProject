package gui;

import java.awt.*;

import java.util.List;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import database.DataAccessException;
import model.Shift;

import java.time.*;
import java.time.LocalDate;

import controller.ShiftCtrl;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.HashMap;

public class ShiftCalendarPanel extends JLayeredPane {

	private int month;
	private int year;
	private Cell[] dayCells;
	private Cell[] titleCells;
	private ShiftCtrl shiftCtrl;
	private Map<LocalDate, List<Shift>> shiftCache = new HashMap<>();


	public ShiftCalendarPanel(int month, int year) throws DataAccessException, SQLException {
		this(LocalDate.of(year, month, 1));
	}

	public ShiftCalendarPanel(LocalDate date) throws DataAccessException, SQLException {
		this.month = date.getMonthValue();
		this.year = date.getYear();
		shiftCtrl = new ShiftCtrl();
		// ZoneId timeZone = ZoneId.systemDefault();
		initComponents();
		initializeDaysInMonth(null);
		setDatesForMonth(date.withDayOfMonth(1));
	}

	private void initComponents() {
		String[] daysOfWeek = { "Søn", "Man", "Tir", "Ons", "Tors", "Fre", "Lør" };
		this.setLayout(new GridLayout(0, 7));

		titleCells = new Cell[7];
		for (int i = 0; i < 7; i++) {
			Cell cell = new Cell();
			cell.asTitle();
			cell.setText(daysOfWeek[i]);

			if (daysOfWeek[i].equals("Søn")) {
				cell.setForeground(Color.RED);
			}

			this.add(cell);
			titleCells[i] = cell;
		}

	}

//	public void setDateCellActionListener(ActionListener listener) {
//	    for (Cell cell : dayCells) {
//	        cell.addActionListener(listener);
//	    }
//}
	public void initializeDaysInMonth(ActionListener listener) throws SQLException {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month value: " + month);
		}
		int numDaysInMonth = Month.of(month).length(Year.isLeap(year));

		// Remove only the day cells from the panel
		if (dayCells != null) {
			for (Cell cell : dayCells) {
				this.remove(cell);
			}
		}

		// Create a new array of Cells for this month
		dayCells = new Cell[numDaysInMonth];

		// Initialize each day in the month
		for (int i = 0; i < numDaysInMonth; i++) {
			Cell cell = new Cell();
			LocalDate dayDate = LocalDate.of(year, month, i + 1);
			cell.setDate(dayDate);
			cell.setText(String.valueOf(dayDate.getDayOfMonth()));
			if (dayDate.equals(LocalDate.now())) {
				cell.setAsToday();
			} else {
				cell.setAsNotToday();

			}

			int shiftCount = getShiftCountForDate(dayDate); // Retrieve the shift count
			cell.setShiftCount(shiftCount); // Set the shift count in the Cell

			cell.addActionListener(e -> {
				LocalDate selectedDate = cell.getDate();
				System.out.println(selectedDate);
				openAssignShiftChooseClub(selectedDate);

			});
//  adds the Cell to the GUI component and stores the Cell in the dayCells array
			this.add(cell);
			dayCells[i] = cell;
		}

		this.revalidate();
		this.repaint();
	}

	private void openAssignShiftChooseClub(LocalDate date) {
		System.out.println("Action listener triggered!");
		SwingUtilities.invokeLater(() -> {
			try {
				AssignShiftChooseClub frame = new AssignShiftChooseClub(date);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public int getShiftCountForDate(LocalDate date) throws SQLException {
        if (!shiftCache.containsKey(date)) {
            try {
                List<Shift> shifts = shiftCtrl.getShiftsByDate(date);
                shiftCache.put(date, shifts);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        return shiftCache.get(date).size();
    }

	public void updateShiftCount(LocalDate date, int shiftCount) {
		for (Cell cell : dayCells) {
			if (cell.getDate() != null && cell.getDate().equals(date)) {
				cell.setShiftCount(shiftCount - 1);
				break;
			}
		}
	}

	public void refreshCalendar() throws DataAccessException, SQLException {
	    SwingWorker<Map<LocalDate, List<Shift>>, Void> worker = new SwingWorker<>() {
	        @Override
	        protected Map<LocalDate, List<Shift>> doInBackground() throws Exception {
	            Map<LocalDate, List<Shift>> shifts = new HashMap<>();
	            for (LocalDate date : getAllDatesInMonth()) {
	                shifts.put(date, shiftCtrl.getShiftsByDate(date));
	            }
	            return shifts;
	            
	        }

	        @Override
	        protected void done() { //called on EDT so its threadsafe to update here
	            try {
	                shiftCache = get();
	                // Update the GUI here...
	                for (Cell cell : dayCells) {
	                    LocalDate date = cell.getDate();
	                    int shiftCount = getShiftCountForDate(date);
	                    cell.setShiftCount(shiftCount);
	                }
	            } catch (InterruptedException | ExecutionException | SQLException e) {
	                ((Throwable) e).printStackTrace();
	            }
	        }
	    };
	    worker.execute();
	    //This will fetch the shifts for each date in the month in a background thread and store them in the cache. 
	    //it will update the GUI.
	}

	public List<LocalDate> getAllDatesInMonth() {
	    return IntStream.rangeClosed(1, YearMonth.of(year, month).lengthOfMonth())// generate a stream of integers from 1 to number of days in month
	                    .mapToObj(day -> LocalDate.of(year, month, day))
	                    .collect(Collectors.toList());
	}

	public void setDatesForMonth(LocalDate date) throws DataAccessException, SQLException {
		int month = date.getMonthValue();
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month value: " + month);
		}

		int startDayOfWeek = date.withDayOfMonth(1).getDayOfWeek().getValue() % 7;// Sunday is 7 in LocalDate, but here
																					// it has to be 0 so
		// the modulus operator is used. Now each week stars
		// with sunday

		// Clear all cells first
		for (Cell cell : dayCells) {
			cell.setText("");
			cell.setDate(null);
			cell.currentMonth(false);

		}

		// Set the dates
		for (int i = 0; i < dayCells.length; i++) {
			Cell cell = dayCells[(startDayOfWeek + i) % dayCells.length];
			LocalDate dayDate = date.plusDays(i);
			cell.setText(String.valueOf(dayDate.getDayOfMonth()));
			cell.setDate(dayDate);
			cell.currentMonth(true);

			LocalDate currentDate = LocalDate.now();
			if (dayDate.isEqual(currentDate)) {
				cell.setAsToday();
			} else {
				cell.setAsNotToday();
			}

			int shiftCount = getShiftCountForDate(dayDate);
			cell.setShiftCount(shiftCount);

			// here shifts objects are added to a date cell if the shift date matches the
			// date cell
//			if (shiftCtrl != null) {
//
//				List<Shift> shifts = shiftCtrl.getShiftsByDate((dayDate));
//
//				for (Shift shift : shifts) {
//					String shiftText = String.format("<html>%d<br/>%04d-%02d-%02d %s - %s</html>", shift.getShiftId(),
//							shift.getCheckInTime(), shift.getCheckOutTime());
//					cell.addShift(shiftText);
		}

	}

}
