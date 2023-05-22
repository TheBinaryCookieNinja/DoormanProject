package gui;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import database.DataAccessException;

import java.time.*;
import java.time.LocalDate;

public class ShiftCalendarPanel extends JLayeredPane {

	private int month;
	private int year;
	private Cell[] dayCells;
	private Cell[] titleCells;

	public ShiftCalendarPanel(int month, int year) throws DataAccessException {
		this.month = month;
		this.year = year;
		initComponents();
		initializeDaysInMonth(null);
		setDatesForMonth(LocalDate.of(year, month, 1));
	}

	/**
	 * @wbp.parser.constructor
	 */
	
	public ShiftCalendarPanel(LocalDateTime dateTime) throws DataAccessException {
		this(dateTime.getMonthValue(), dateTime.getYear());
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
	public void initializeDaysInMonth(ActionListener listener) {
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
			LocalDate localDate = LocalDate.of(year, month, i + 1);
			cell.setDate(localDate);
			cell.setText(String.valueOf(i + 1));
			if (localDate.equals(LocalDate.now())) {
				cell.setAsToday();
			} else {
				cell.setAsNotToday();;
			}

			cell.addActionListener(e -> {
			    LocalDate selectedDate = cell.getDate();
			    openAssignShiftChooseClub(selectedDate);
			});
			
			

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

	public void setDatesForMonth(LocalDate date) throws DataAccessException {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month value: " + month);
		}

		int startDayOfWeek = date.getDayOfWeek().getValue() % 7; // Sunday is 7 in LocalDate, but here it has to be 0 so
																	// the modulus operator is used. Now each week stars with sunday

		// Clear all cells first
		for (Cell cell : dayCells) {
			cell.setText("");
			cell.setDate(null);
			cell.currentMonth(false);

		}

		// Set the dates
		for (int i = 0; i < dayCells.length; i++) {
			Cell cell = dayCells[(startDayOfWeek + i) % dayCells.length];
			LocalDate dayDate = LocalDate.of(year, month, i + 1);
			cell.setText(String.valueOf(dayDate.getDayOfMonth()));
			cell.setDate(dayDate);
			cell.currentMonth(true);

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
