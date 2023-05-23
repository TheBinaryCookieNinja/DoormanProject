package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import model.Doorman;
import controller.ShiftCtrl;
import database.DataAccessException;
import gui.GetAvailableDoormanListCellRenderer;

import java.awt.GridBagLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
					GetAvailableDoorman frame = new GetAvailableDoorman(null, 0);
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
	public GetAvailableDoorman(LocalDate currentDate, int barId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIStyle.setUIStyle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Available Doorman List");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		doormanList = new JList<Doorman>();
		doormanList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_doormanList = new GridBagConstraints();
		
		
		gbc_doormanList.insets = new Insets(0, 0, 5, 5);
		gbc_doormanList.fill = GridBagConstraints.BOTH;
		gbc_doormanList.gridx = 2;
		gbc_doormanList.gridy = 3;
		contentPane.add(doormanList, gbc_doormanList);
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_1.addActionListener(e -> {
			try {
				shiftCtrl.confirmShift(doormanList.getSelectedValue().getEmployeeId());
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 6;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(e -> {
			dispose();
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		init(currentDate, barId);
	}

	
	private void init(LocalDate currentDate, int barId) {
		doormanList.setCellRenderer(new GetAvailableDoormanListCellRenderer());
		try {
			shiftCtrl = new ShiftCtrl();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		updateDoormanList(currentDate, barId);
	}
	
	private void updateDoormanList(LocalDate currentDate, int barId) {
		try {
			List<Doorman> dlo = shiftCtrl.getAvailableDoormenForShift(currentDate, barId);
			dataListModel = new DefaultListModel<>();
			for (int i = 0; i < dlo.size(); i++) {
				dataListModel.addElement(dlo.get(i));
			}
			this.doormanList.setModel(dataListModel);
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
		}
	}
}
