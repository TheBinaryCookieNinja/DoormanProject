package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

public class MainCalendar extends JFrame {

	private JPanel contentPane;
	private ShiftCalenderCustom calendarCustom2;
	private JPanel jPanel1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
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
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainCalendar() {
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		jPanel1 = new JPanel();
		jPanel1.setBackground(new Color(255, 255, 255));
		contentPane.add(jPanel1);
		calendarCustom2 = new ShiftCalenderCustom();
		calendarCustom2.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 205)));
		
		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
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
		  		.addGroup(layout.createSequentialGroup()
		  			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 1056, GroupLayout.PREFERRED_SIZE)
		  			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		  );
		  layout.setVerticalGroup(
		  	layout.createParallelGroup(Alignment.LEADING)
		  		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
		  );
	        getContentPane().setLayout(layout);

	        pack();
	        setLocationRelativeTo(null);
	}

}
