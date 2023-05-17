package gui;

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
	//private JLabel shiftLabel;
	
	//private static final int MAX_TEXT_LENGTH = 10; 
	 //private static final int MAX_TEXT_LINES = 2;
	
	// Constructor
	public Cell () {
		setContentAreaFilled(true);
		setBorder(BorderFactory.createLineBorder(Color.RED));
		//setBorder(null);
		setHorizontalAlignment(JLabel.CENTER);
//		shiftLabel = new JLabel(); 
//		shiftLabel.setPreferredSize(new Dimension(80, 80));
//        add(shiftLabel);
	}
	
	
	public void asTitle() {
		title = true;
	}
	
	public boolean isTitle() {
		return title;
	}
	
	// Method to set the foreground color based on whether the cell represents the current month
	public void currentMonth(boolean act) {
		if(act) {
			setForeground(new Color(68,68,68));
			
		}else {
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
        setBackground(new Color(97, 49, 237));
        setForeground(Color.WHITE);    }
	
	public void setAsNotToday() {
		isToday = false;
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
	}
	
	  @Override
	    protected void paintComponent(Graphics grphcs) {
		  super.paintComponent(grphcs);
		    if (title) {
		        grphcs.setColor(new Color(213, 213, 213));
		        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
		    }
		    if (isToday) {
		        grphcs.setColor(new Color(97, 49, 237));
		        int cellSize = Math.min(getWidth(), getHeight()) - 10;
		        int x = (getWidth() - cellSize) / 2;
		        int y = (getHeight() - cellSize) / 2;
		        grphcs.fillRoundRect(x, y, cellSize, cellSize, 100, 100);
		    }
	        
	    }


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
