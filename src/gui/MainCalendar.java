package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import database.DataAccessException;

import javax.swing.GroupLayout.Alignment;

public class MainCalendar extends JFrame {

	private JPanel contentPane;
	private ShiftCalendarCustom calendarCustom2;
	private JPanel jPanel1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
		//Nimbus uses Java 2D vector graphics to draw the user interface (UI), rather than static bitmaps, so the UI can be crisply rendered at any resolution
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainCalendar frame = new MainCalendar();
					frame.setResizable(true);
					frame.setVisible(true);			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 * @throws DataAccessException
	 */
	public MainCalendar() throws DataAccessException {
		initComponents();
	}
	
	private void initComponents() throws DataAccessException {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 1526, 1224);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setContentPane(contentPane);
	    
	    jPanel1 = new JPanel();
	    jPanel1.setBackground(new Color(255, 255, 255));
	    contentPane.add(jPanel1);
	    
	    
	    calendarCustom2 = new ShiftCalendarCustom();
	    calendarCustom2.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 205)));
	    
	    GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	    jPanel1Layout.setHorizontalGroup(
	    	jPanel1Layout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(calendarCustom2, GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    jPanel1Layout.setVerticalGroup(
	    	jPanel1Layout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(jPanel1Layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(calendarCustom2, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    jPanel1.setLayout(jPanel1Layout);

	    GroupLayout layout = new GroupLayout(getContentPane());
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup(Alignment.LEADING)
	    		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 1500, Short.MAX_VALUE)
	    );
	    layout.setVerticalGroup(
	    	layout.createParallelGroup(Alignment.LEADING)
	    		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
	    );
	    getContentPane().setLayout(layout);

	    
	    setLocationRelativeTo(null);
	    pack();
	}


}
