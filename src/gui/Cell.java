package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Cell extends JButton {

	private LocalDate date;
	private boolean title;
	private boolean isToday;
	// private JLabel shiftLabel;
	private JLabel shiftCountLabel;
	private JLabel dateLabel;

	// private static final int MAX_TEXT_LENGTH = 10;
	// private static final int MAX_TEXT_LINES = 2;

	 private static final Color TITLE_COLOR = new Color(174, 214, 241); 
	 private static final Color DEFAULT_COLOR = Color.YELLOW; 
	 private static final Color OTHERCELLS_COLOR = new Color(202, 207, 210);  
	
	 public Cell() {
		 
		 setLayout(new BorderLayout());
		//setBorder(BorderFactory.createLineBorder(Color.RED));
		// setBorder(null);
		
//		shiftLabel = new JLabel(); 
//		shiftLabel.setPreferredSize(new Dimension(80, 80));
//        add(shiftLabel);
		
		 // create date label and customize it
		    dateLabel = new JLabel();
	        dateLabel.setHorizontalAlignment(JLabel.CENTER);
	        add(dateLabel, BorderLayout.CENTER);
		 
	     // create date label and also customize it
		 shiftCountLabel = new JLabel();
	        shiftCountLabel.setHorizontalAlignment(JLabel.CENTER);
	        shiftCountLabel.setForeground(Color.BLACK);
	        shiftCountLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 35, 0)); // paddins is added so there will be a little extra space between date and shiftcount
	        shiftCountLabel.setFont(shiftCountLabel.getFont().deriveFont(12.0f));
	       
	        shiftCountLabel.setVisible(false); // den sarter jo med at være usynlig
	        add(shiftCountLabel, BorderLayout.SOUTH);
	       
	        setContentAreaFilled(true);
	        setHorizontalAlignment(JLabel.CENTER);
	}

	public void asTitle() {
		title = true;
		 setBackground(TITLE_COLOR);
	     setForeground(Color.BLACK);
	}

	public boolean isTitle() {
		return title;
	}

	// Method to set the foreground color based on whether the cell represents the
	// current month
	public void currentMonth(boolean act) {
		if (act) {
			setForeground(new Color(68, 68, 68));

		} else {
			setForeground(new Color(169, 169, 169));
		}
	}

	public void setDate(LocalDate date) {
		this.date = date;
		
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setAsToday() {
		isToday = true;
		setBackground(DEFAULT_COLOR);
	     setForeground(Color.BLACK);
	}

	public void setAsNotToday() {
		isToday = false;
		setBackground(OTHERCELLS_COLOR);
		setForeground(Color.BLACK);
	}
	
	public void setShiftCount(int shiftCount) {
		 shiftCountLabel.setText("Der er " + Integer.toString(shiftCount) + " vagter");
	     shiftCountLabel.setVisible(shiftCount > 0); // before it was invisible, now if there are any shifts, it gets visible
	}

	@Override
	 protected void paintComponent(Graphics grphcs) {
        if (title) {
     // If the cell is a title cell, draw a line at the bottom
            grphcs.setColor(TITLE_COLOR);
            grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        }
     // If the cell represents today, draw a rounded rectangle with a specific color
        if (isToday) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Anti-aliasing is a technique used to smooth the jagged edges of shapes and lines, resulting in a more visually appealing appearance.
            g2.setColor(new Color(97, 49, 237));
            int cellSize = Math.min(getWidth(), getHeight()) - 10; // Adjust the cell size based on the available space
            int x = (getWidth() - cellSize) / 2;
            int y = (getHeight() - cellSize) / 2;
            g2.fillRoundRect(x, y, cellSize, cellSize, 100, 100);

        }
        super.paintComponent(grphcs);
    }
//	protected void paintComponent(Graphics grphcs) {
//		super.paintComponent(grphcs);
//		if (title) {
//			grphcs.setColor(new Color(213, 213, 213));
//			grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
//		}
//		if (isToday) {
//			grphcs.setColor(new Color(97, 49, 237));
//			int cellSize = Math.min(getWidth(), getHeight()) - 10;
//			int x = (getWidth() - cellSize) / 2;
//			int y = (getHeight() - cellSize) / 2;
//			grphcs.fillRoundRect(x, y, cellSize, cellSize, 100, 100);
//		}
//
//	}

//	public void addShift(String shiftText) {
//		
//		 shiftText = wrapText(shiftText, MAX_TEXT_LINES);
//	        shiftLabel.setText("<html>" + shiftText + "</html>");
//	}
//	
//	// a text wrapper that splits the text into words and adds line breaks when the length of the lines get greater than the limit set.
//	private String wrapText(String text, int maxLines) {
//        String wrappedText = "";
//        String[] words = text.split(" ");
//        int lineCount = 0;
//        for (String word : words) {
//            if (lineCount >= maxLines) {
//                break;
//            }
//            if (wrappedText.length() + word.length() > MAX_TEXT_LENGTH) {
//                wrappedText += "<br/>";
//                lineCount++;
//            }
//            wrappedText += word + " ";
//        }
//        return wrappedText;
//    }
//
//	public void clearShifts() {
//		
//		shiftLabel.setText("");
//	}
}
