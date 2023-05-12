package gui;

import java.awt.Color;


import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.HashMap;

public class ShiftCalendarCustom extends JPanel {
	private int month;
	private int year;
	private SlidingPanel slide;
	private Map<String, ShiftCalendarPanel> calendarPanels;
    private ShiftCalendarPanel currentPanel;
    private int currentMonth;
    private int currentYear;

	private JButton arrowBack;
	private JButton arrowForward;
	private JLayeredPane jLayeredPane1;
	private JPanel jPanel1;
	private JLabel lbDate;
	private JLabel lbMonthYear;
	private JLabel lbTime;
	private JLabel lbType;

	/**
	 * Create the panel.
	 */
	
	//a new thread is being used to continuously update the time and date on the user interface (UI) without blocking the main thread, which is responsible for handling UI events and updates.
	//By using a separate thread for this task, the UI remains responsive, and other user events can be processed concurrently. 
	//The main thread, responsible for the UI, is not blocked by the continuous updating of the time and date, ensuring smooth operation of the application.
	public ShiftCalendarCustom() {
		
		thisMonth();
		calendarPanels = new HashMap<>();
		initComponents();
		currentMonth = 1;
		currentYear = 2023;
		slide.show(new ShiftCalendarPanel(5, 2021), SlidingPanel.AnimateType.TO_RIGHT);
		updateMonthYear();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.err.println(e);
					}
					Date date = new Date();
					SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
					SimpleDateFormat df = new SimpleDateFormat("EEEE, dd/MM-yyyy");
					String time = tf.format(date);
					lbTime.setText(time.split(" ")[0]);
					lbType.setText(time.split(" ")[1]);
					lbDate.setText(df.format(date));
				}
			}
		}).start(); // stars the above created thread
	}

	@SuppressWarnings("unchecked")
	 // This method initializes the components used in the calendar panel, such as the sliding panel,
    // the labels for displaying the time and date, and the buttons for navigating between months.
	private void initComponents() {
		slide = new SlidingPanel();
		jPanel1 = new JPanel();
		lbTime = new JLabel();
		lbType = new JLabel();
		lbDate = new JLabel();
		jLayeredPane1 = new JLayeredPane();
		lbMonthYear = new JLabel();
		lbMonthYear.setBackground(new Color(0, 128, 192));
		arrowForward = new JButton();
		currentPanel = new ShiftCalendarPanel(currentMonth, currentYear);
		calendarPanels.put(String.valueOf(currentMonth) + String.valueOf(currentYear), currentPanel);
		this.add(currentPanel);
		setBackground(new Color(255, 255, 255));

		slide.setBackground(new Color(255, 255, 255));

		GroupLayout slideLayout = new GroupLayout(slide);
		slideLayout.setHorizontalGroup(
			slideLayout.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 413, Short.MAX_VALUE)
		);
		slideLayout.setVerticalGroup(
			slideLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 221, Short.MAX_VALUE)
		);
		slide.setLayout(slideLayout);

		jPanel1.setBackground(new Color(255, 255, 255));

		lbTime.setFont(new Font("SansSerif", Font.BOLD, 30)); 
		lbTime.setForeground(new Color(0, 0, 0));
		lbTime.setHorizontalAlignment(SwingConstants.LEFT);
		lbTime.setText("10:00:00");

		lbType.setFont(new Font("sansserif", 1, 25)); 
		lbType.setForeground(new Color(0, 0, 0));
		lbType.setText("PM");

		lbDate.setFont(new Font("sansserif", 0, 18)); 
		lbDate.setForeground(new Color(0, 0, 0));
		lbDate.setHorizontalAlignment(SwingConstants.CENTER);
		lbDate.setText("Sunday, 07/05/2023");

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addComponent(lbTime, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbType, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addComponent(lbDate, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbTime, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbType))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbDate)
					.addContainerGap(537, Short.MAX_VALUE))
		);
		jPanel1.setLayout(jPanel1Layout);

		lbMonthYear.setFont(new Font("sansserif", 1, 30));
		lbMonthYear.setForeground(new Color(0, 128, 192));
		lbMonthYear.setHorizontalAlignment(SwingConstants.CENTER);
		lbMonthYear.setText("Month - Year");

		arrowForward.setIcon(new ImageIcon(ShiftCalendarCustom.class.getResource("/icons/angle-double-small-left (1).png"))); 
		arrowForward.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		arrowForward.setContentAreaFilled(false);
		arrowForward.setCursor(new Cursor(Cursor.HAND_CURSOR));
		arrowForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				arrowForwardActionPerformed(evt);
			}

		});
		jLayeredPane1.setLayer(lbMonthYear, JLayeredPane.DEFAULT_LAYER);
		jLayeredPane1.setLayer(arrowForward, JLayeredPane.DEFAULT_LAYER);
		arrowBack = new JButton();
		
				arrowBack.setIcon(new ImageIcon(ShiftCalendarCustom.class.getResource("/icons/angle-double-small-left.png"))); 
				arrowBack.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
				arrowBack.setContentAreaFilled(false);
				arrowBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
				arrowBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						arrowBackActionPerformed(evt);
					}
				});
				
						jLayeredPane1.setLayer(arrowBack, JLayeredPane.DEFAULT_LAYER);

		GroupLayout jLayeredPane1Layout = new GroupLayout(jLayeredPane1);
		jLayeredPane1Layout.setHorizontalGroup(
			jLayeredPane1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jLayeredPane1Layout.createSequentialGroup()
					.addGap(16)
					.addComponent(arrowBack, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbMonthYear, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
					.addGap(27)
					.addComponent(arrowForward, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		jLayeredPane1Layout.setVerticalGroup(
			jLayeredPane1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jLayeredPane1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jLayeredPane1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jLayeredPane1Layout.createSequentialGroup()
							.addComponent(arrowBack, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(jLayeredPane1Layout.createSequentialGroup()
							.addGroup(jLayeredPane1Layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(arrowForward, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbMonthYear, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap(221, Short.MAX_VALUE))))
		);
		jLayeredPane1.setLayout(jLayeredPane1Layout);

		GroupLayout layout = new GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jLayeredPane1, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
						.addComponent(slide, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(13)
					.addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(slide, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE))
		);
		this.setLayout(layout);
	}
	
	
	private void arrowForwardActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (month == 12) {
			month = 1;
			 currentMonth = (currentMonth == 12) ? 1 : currentMonth + 1;//ternary operator, which is a shorthand for an if-else 
			 currentYear = (currentMonth == 1) ? currentYear + 1 : currentYear;
			 changeMonth(currentMonth, currentYear);  // To go to the next month
			year++;
		} else {
			month++;
		}
		slide.show(new ShiftCalendarPanel(month, year), SlidingPanel.AnimateType.TO_LEFT);
		updateMonthYear();
	}
	
	
	private void arrowBackActionPerformed(ActionEvent evt) {
		if (month == 1) {
			month = 12;
			currentMonth = (currentMonth == 1) ? 12 : currentMonth - 1;
		    currentYear = (currentMonth == 12) ? currentYear - 1 : currentYear;
		    changeMonth(currentMonth, currentYear);
			year--;
		} else {
			month--;
		}
		slide.show(new ShiftCalendarPanel(month, year), SlidingPanel.AnimateType.TO_RIGHT);
		updateMonthYear();
	}
	
	// This method retrieves the current month and year and sets the appropriate instance variables.
	public void thisMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // today
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);
	}
	
	 // This method updates the displayed month and year on the calendar panel based on the current
    // values of the "month" and "year" instance variables.
	private void updateMonthYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("MMMM-yyyy");
		lbMonthYear.setText(df.format(calendar.getTime()));
	}
	
	 public void changeMonth(int newMonth, int newYear) {
	        if (calendarPanels.containsKey(String.valueOf(newMonth) + String.valueOf(newYear))) {
	            // If it does, use that panel
	            this.remove(currentPanel);
	            currentPanel = calendarPanels.get(String.valueOf(newMonth) + String.valueOf(newYear));
	            if (currentPanel == null) {
	                currentPanel = new ShiftCalendarPanel(month, year);
	                calendarPanels.put(String.valueOf(newMonth) + String.valueOf(newYear), currentPanel);
	            } else {
	                currentPanel.initializeDaysInMonth();  // add this line
	            }
	            this.add(currentPanel);
	        } else {
	            // If it doesn't, create a new panel and add it to the map
	            ShiftCalendarPanel newPanel = new ShiftCalendarPanel(newMonth, newYear);
	            calendarPanels.put(String.valueOf(newMonth) + String.valueOf(newYear), newPanel);

	            
	            this.remove(currentPanel);
	            currentPanel = newPanel;
	            this.add(currentPanel);
	        }
	       
	        currentMonth = newMonth;
	        currentYear = newYear;
	        
	        
	        this.validate();
	        this.repaint();
	    }
	}

