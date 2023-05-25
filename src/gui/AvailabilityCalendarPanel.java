package gui;

import java.awt.*;

import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.*;

import database.DataAccessException;
import database.DoormanDAO;
import model.AvailableDate;
import model.Doorman;

import java.time.*;
import java.time.LocalDate;

import controller.AvailableDateCtrl;

public class AvailabilityCalendarPanel extends JLayeredPane {

	private int month;
	private int year;
	private Cell[] dayCells;
	private Cell[] titleCells;
	private AvailableDateCtrl availableDateCtrl;
	private DoormanDAO doormanDAO;

	public AvailabilityCalendarPanel(int month, int year) throws DataAccessException {
		this(LocalDateTime.of(year, month, 1, 0, 0));
	}

	public AvailabilityCalendarPanel(LocalDateTime dateTime) throws DataAccessException {
		this.month = dateTime.getMonthValue();
		this.year = dateTime.getYear();

		ZoneId timeZone = ZoneId.systemDefault();
		initComponents();
		initializeDaysInMonth(null);
		setDatesForMonth(dateTime.withDayOfMonth(1).atZone(timeZone).toLocalDate());
	}

	public AvailabilityCalendarPanel(LocalDateTime dateTime, AvailableDateCtrl availableDateCtrl)
			throws DataAccessException {
		this.availableDateCtrl = availableDateCtrl;
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
			LocalDate dayDate = LocalDate.of(year, month, i + 1);
			cell.setDate(dayDate);
			cell.setText(String.valueOf(dayDate.getDayOfMonth()));
			if (dayDate.equals(LocalDate.now())) {
				cell.setAsToday();
			} else {
				cell.setAsNotToday();

			}
			
//			 boolean isAvailable = checkAvailabilityForDate(dayDate);
//		        if (isAvailable) {
//		            cell.setText("Free");
//		            cell.setBackground(Color.GREEN);
//		        } else {
//		            cell.setText("Occupied");
//		            cell.setBackground(Color.RED);
//		        }


			cell.addActionListener(e -> {
				LocalDate selectedDate = cell.getDate();
				System.out.println(selectedDate);
				try {
					handleAvailabilityRegistration(selectedDate);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			});

			this.add(cell);
			dayCells[i] = cell;
		}

		this.revalidate();
		this.repaint();
	}
	
	
	// denne metode var tænkt til at tjekke om
//	private boolean checkAvailabilityForDate(LocalDate date) {
//	    try {
//	        AvailableDateCtrl availableDateCtrl = new AvailableDateCtrl();
//	        DoormanDAO doormanDAO = new DoormanDAO();
//	        
//	        List<Doorman> doormen = doormanDAO.findAll(); 
//
//	       
//	        for (Doorman doorman : doormen) {
//	            int doormanId = doorman.getEmployeeId();
//	            boolean isRegistered = availableDateCtrl.isAvailabilityRegistered(doormanId, date);
//	            if (isRegistered) {
//	                return false; 
//	            }
//	        }
//
//	        return true; 
//	    } catch (DataAccessException e) {
//	        
//	        e.printStackTrace();
//	        return false; 
//	    }
//	}



	private void handleAvailabilityRegistration(LocalDate selectedDate) throws SQLException {
		LocalDate currentDate = LocalDate.now();
		LocalDate minDate = currentDate.plusWeeks(2);

		if (selectedDate.isBefore(minDate)) {
			JOptionPane.showMessageDialog(null,
					"Registration of availability is only allowed at least two weeks before the shift starts😯");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(null,
				"Do you want to confirm availability registration on " + selectedDate + "?",
				"Confirm Availability Registration", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				int doormanId = 5;
				AvailableDateCtrl availableDateCtrl = new AvailableDateCtrl();

				if (availableDateCtrl.isAvailabilityRegistered(doormanId, selectedDate)) {
					JOptionPane.showMessageDialog(null,
							"Availability has already been registred for this date - please try another date😜");
					return;
				}

				AvailableDate availableDate = availableDateCtrl.createAvailableDates(selectedDate, doormanId);
				boolean success = availableDateCtrl.confirmAvailability(availableDate);
				if (success) {
					JOptionPane.showMessageDialog(null, "Availability registered successfully😀");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to register availability. Please try again😥");
				}
			} catch (DataAccessException e) {
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			}
		}
	}

	public void setDatesForMonth(LocalDate date) throws DataAccessException {
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

		}

	}

}
