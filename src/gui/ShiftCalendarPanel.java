package gui;

import java.awt.*;
import javax.swing.*;

import model.Shift;

import java.time.*;
import java.util.Calendar;

public class ShiftCalendarPanel extends JLayeredPane {

	private int month;
	private int year;
	private Cell[] dayCells;
	private Cell[] titleCells;

	public ShiftCalendarPanel(int month, int year) {
		this.month = month;
		this.year = year;
		initComponents();
		initializeDaysInMonth();
		setDatesForMonth(LocalDate.of(year, month, 1));
	}

	private void initComponents() {
		String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		this.setLayout(new GridLayout(0, 7));

		titleCells = new Cell[7];
		for (int i = 0; i < 7; i++) {
			Cell cell = new Cell();
			cell.asTitle();
			cell.setText(daysOfWeek[i]);
			this.add(cell);
			titleCells[i] = cell;
		}
	}

	public void initializeDaysInMonth() {
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
			this.add(cell);
			dayCells[i] = cell;
		}

		this.revalidate();
		this.repaint();
	}

	public void setDatesForMonth(LocalDate date) {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month value: " + month);
		}

		int startDayOfWeek = date.getDayOfWeek().getValue() % 7; // Sunday is 7 in LocalDate, but here it has to be 0 so
																	// the modulus operator is used

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
			for (Shift shift : shifts) {
				LocalDate shiftDate = LocalDate.parse(shift.getShiftDate());
				if (shiftDate.equals(dayDate)) {
					String shiftText = String.format("<html>%s<br/>%s - %s</html>", shift.getShiftId(),
							shift.getCheckInTime(), shift.getCheckOutTime());
					cell.addShift(shiftText);
				}
			}
		}
	}
}
