package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Doorman;
import controller.ShiftCtrl;
import database.DataAccessException;
import java.awt.GridBagLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JScrollPane;

public class GetAvailableDoorman extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Doorman>dataListModel;
	private JList<Doorman> doormanList;
	private ShiftCtrl shiftCtrl;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetAvailableDoorman frame = new GetAvailableDoorman(null, 0, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public GetAvailableDoorman(LocalDate date, int barId, int shiftId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setUIStyle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Available Doormen");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		doormanList = new JList<Doorman>();
		scrollPane.setViewportView(doormanList);
		doormanList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTHEAST;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_1 = new JButton("Add to shift");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Cancel");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(e -> {
			dispose();
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					clickConfirm(shiftId, date);
				} catch (DataAccessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		init(date, barId);
	}

	
	private void init(LocalDate date, int barId) {
		doormanList.setCellRenderer(new GetAvailableDoormanListCellRenderer());
		try {
			shiftCtrl = new ShiftCtrl();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		updateDoormanList(date, barId);
	}
	
	private void updateDoormanList(LocalDate date, int barId) {
		try {
			List<Doorman> dlo = shiftCtrl.getAvailableDoormenForShift(date, barId);
			dataListModel = new DefaultListModel<>();
			for (int i = 0; i < dlo.size(); i++) {
				dataListModel.addElement(dlo.get(i));
			}
			this.doormanList.setModel(dataListModel);
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
		}
	}
	
	private void clickConfirm(int shiftId, LocalDate date) throws DataAccessException, SQLException {
		boolean confirm = shiftCtrl.confirmShift(doormanList.getSelectedValue().getEmployeeId(), shiftId, date);
		if(confirm = true) {
			JOptionPane.showMessageDialog(null, "Succes");
		}
		else {
			JOptionPane.showMessageDialog(null, "Error, try again");
		}
		
		this.dispose();
	}
	
	private void setUIStyle() {
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
	}
}
