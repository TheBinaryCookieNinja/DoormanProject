package gui;

import java.awt.GridLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLayeredPane;

public class ShiftCalenderPanel extends JLayeredPane {
	
	// all the cells variables are declared at the bottom
	private int month;
	private int year;

	/**
	 * Create the panel.
	 */
	public ShiftCalenderPanel(int month, int year) {

		this.month = month;
		this.year = year;
		initComponents();
		init();

	}

	public void init() {
		mon.asTitle();
		tue.asTitle();
		wed.asTitle();
		thu.asTitle();
		fri.asTitle();
		sat.asTitle();
		sun.asTitle();
		setDate();
	}

	private void setDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1); // subtracts 1 so that january will be month 0
		calendar.set(Calendar.DATE, 1);
		int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1; // subtracts 1 so the index starts at 0
		calendar.add(Calendar.DATE, - startDay);
		Today today = getToday();
		for(Component com : getComponents()) {
			Cell cell = (Cell) com;
			if(!Cell.isTitle()) {
				cell.setText(calendar.get(Calendar.DATE) + "");
				cell.setDate(calendar.getTime());
				cell.currentMonth(calendar.get(Calendar.MONTH) == month -1);
				calendar.add(Calendar.DATE, 1);// the current date is increased by 1
				
			}
			
		}
	            	
	            
	}
	
	 private Today getToday() {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        return new Today(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
	    }

	@SuppressWarnings("unchecked")

	private void initComponents() {
		sun = new Cell();
		mon = new Cell();
		tue = new Cell();
		wed = new Cell();
		thu = new Cell();
		fri = new Cell();
		sat = new Cell();
		cell8 = new Cell();
		cell9 = new Cell();
		cell10 = new Cell();
		cell11 = new Cell();
		cell12 = new Cell();
		cell13 = new Cell();
		cell14 = new Cell();
		cell15 = new Cell();
		cell16 = new Cell();
		cell17 = new Cell();
		cell18 = new Cell();
		cell19 = new Cell();
		cell20 = new Cell();
		cell21 = new Cell();
		cell22 = new Cell();
		cell23 = new Cell();
		cell24 = new Cell();
		cell25 = new Cell();
		cell26 = new Cell();
		cell27 = new Cell();
		cell28 = new Cell();
		cell29 = new Cell();
		cell30 = new Cell();
		cell31 = new Cell();
		cell32 = new Cell();
		cell33 = new Cell();
		cell34 = new Cell();
		cell35 = new Cell();
		cell36 = new Cell();
		cell37 = new Cell();
		cell38 = new Cell();
		cell39 = new Cell();
		cell40 = new Cell();
		cell41 = new Cell();
		cell42 = new Cell();
		cell43 = new Cell();
		cell44 = new Cell();
		cell45 = new Cell();
		cell46 = new Cell();
		cell47 = new Cell();
		cell48 = new Cell();
		cell49 = new Cell();

		setLayout(new GridLayout(7, 7, 0, 0));

        sun.setForeground(new Color(222, 12, 12));
        sun.setText("Sun");
        sun.setFont(new Font("sansserif", 0, 14)); 
        add(sun);

        mon.setText("Mon");
        mon.setFont(new Font("sansserif", 0, 14)); 
        add(mon);

        tue.setText("Tue");
        tue.setFont(new Font("sansserif", 0, 14)); 
        add(tue);

        wed.setText("Wed");
        wed.setFont(new Font("sansserif", 0, 14)); 
        add(wed);

        thu.setText("Thu");
        thu.setFont(new Font("sansserif", 0, 14)); 
        add(thu);

        fri.setText("Fri");
        fri.setFont(new Font("sansserif", 0, 14)); 
        add(fri);

        sat.setText("Sat");
        sat.setFont(new Font("sansserif", 0, 14)); 
        add(sat);

        cell8.setFont(new Font("sansserif", 0, 14)); 
        add(cell8);

        cell9.setFont(new Font("sansserif", 0, 14)); 
        add(cell9);

        cell10.setFont(new Font("sansserif", 0, 14)); 
        add(cell10);

        cell11.setFont(new Font("sansserif", 0, 14)); 
        add(cell11);

        cell12.setFont(new Font("sansserif", 0, 14)); 
        add(cell12);

        cell13.setFont(new Font("sansserif", 0, 14)); 
        add(cell13);

        cell14.setFont(new Font("sansserif", 0, 14)); 
        add(cell14);

        cell15.setFont(new Font("sansserif", 0, 14)); 
        add(cell15);

        cell16.setFont(new Font("sansserif", 0, 14)); 
        add(cell16);

        cell17.setFont(new Font("sansserif", 0, 14)); 
        add(cell17);

        cell18.setFont(new Font("sansserif", 0, 14)); 
        add(cell18);

        cell19.setFont(new Font("sansserif", 0, 14)); 
        add(cell19);

        cell20.setFont(new Font("sansserif", 0, 14)); 
        add(cell20);

        cell21.setFont(new Font("sansserif", 0, 14)); 
        add(cell21);

        cell22.setFont(new Font("sansserif", 0, 14)); 
        add(cell22);

        cell23.setFont(new Font("sansserif", 0, 14)); 
        add(cell23);

        cell24.setFont(new Font("sansserif", 0, 14)); 
        add(cell24);

        cell25.setFont(new Font("sansserif", 0, 14)); 
        add(cell25);

        cell26.setFont(new Font("sansserif", 0, 14)); 
        add(cell26);

        cell27.setFont(new Font("sansserif", 0, 14)); 
        add(cell27);

        cell28.setFont(new Font("sansserif", 0, 14)); 
        add(cell28);

        cell29.setFont(new Font("sansserif", 0, 14)); 
        add(cell29);

        cell30.setFont(new Font("sansserif", 0, 14)); 
        add(cell30);

        cell31.setFont(new Font("sansserif", 0, 14)); 
        add(cell31);

        cell32.setFont(new Font("sansserif", 0, 14)); 
        add(cell32);

        cell33.setFont(new Font("sansserif", 0, 14)); 
        add(cell33);

        cell34.setFont(new Font("sansserif", 0, 14)); 
        add(cell34);

        cell35.setFont(new Font("sansserif", 0, 14)); 
        add(cell35);

        cell36.setFont(new Font("sansserif", 0, 14)); 
        add(cell36);

        cell37.setFont(new Font("sansserif", 0, 14)); 
        add(cell37);

        cell38.setFont(new Font("sansserif", 0, 14)); 
        add(cell38);

        cell39.setFont(new Font("sansserif", 0, 14)); 
        add(cell39);

        cell40.setFont(new Font("sansserif", 0, 14)); 
        add(cell40);

        cell41.setFont(new Font("sansserif", 0, 14)); 
        add(cell41);

        cell42.setFont(new Font("sansserif", 0, 14)); 
        add(cell42);

        cell43.setFont(new Font("sansserif", 0, 14)); 
        add(cell43);

        cell44.setFont(new Font("sansserif", 0, 14)); 
        add(cell44);

        cell45.setFont(new Font("sansserif", 0, 14)); 
        add(cell45);

        cell46.setFont(new Font("sansserif", 0, 14)); 
        add(cell46);

        cell47.setFont(new Font("sansserif", 0, 14)); 
        add(cell47);

        cell48.setFont(new Font("sansserif", 0, 14)); 
        add(cell48);

        cell49.setFont(new Font("sansserif", 0, 14)); 
        add(cell49);
		
	}

	 private gui.Cell cell10;
	    private Cell cell11;
	    private Cell cell12;
	    private Cell cell13;
	    private Cell cell14;
	    private Cell cell15;
	    private Cell cell16;
	    private Cell cell17;
	    private Cell cell18;
	    private Cell cell19;
	    private Cell cell20;
	    private Cell cell21;
	    private Cell cell22;
	    private Cell cell23;
	    private Cell cell24;
	    private Cell cell25;
	    private Cell cell26;
	    private Cell cell27;
	    private Cell cell28;
	    private Cell cell29;
	    private Cell cell30;
	    private Cell cell31;
	    private Cell cell32;
	    private Cell cell33;
	    private Cell cell34;
	    private Cell cell35;
	    private Cell cell36;
	    private Cell cell37;
	    private Cell cell38;
	    private Cell cell39;
	    private Cell cell40;
	    private Cell cell41;
	    private Cell cell42;
	    private Cell cell43;
	    private Cell cell44;
	    private Cell cell45;
	    private Cell cell46;
	    private Cell cell47;
	    private Cell cell48;
	    private Cell cell49;
	    private Cell cell8;
	    private Cell cell9;
	    private Cell fri;
	    private Cell mon;
	    private Cell sat;
	    private Cell sun;
	    private Cell thu;
	    private Cell tue;
	    private Cell wed;
}
