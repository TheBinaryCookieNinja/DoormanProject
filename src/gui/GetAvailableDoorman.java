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

public class GetAvailableDoorman extends JFrame {

	private JPanel contentPane;
	private List<Doorman> data;
	private DefaultListModel<Doorman>dataListModel;
	private ShiftCtrl ShiftCtrl;
	private JList<Doorman> doormanList;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetAvailableDoorman frame = new GetAvailableDoorman();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GetAvailableDoorman(List<Doorman> data) {
		this.data = data;
		if(this.data == null) {
			this.data = new ArrayList<>();
		}
	}
	
	public int getRowCount() {
		return data.size();
	}
	
	

	/**
	 * Create the frame.
	 */
	public GetAvailableDoorman() {
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
		
		doormanList = new JList();
		doormanList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GetAvailableDoormanListCellRenderer renderer = new GetAvailableDoormanListCellRenderer();
		GridBagConstraints gbc_doormanList = new GridBagConstraints();
		doormanList.setCellRenderer(renderer);
		doormanList.setModel(dataListModel);
		
		gbc_doormanList.insets = new Insets(0, 0, 5, 5);
		gbc_doormanList.fill = GridBagConstraints.BOTH;
		gbc_doormanList.gridx = 2;
		gbc_doormanList.gridy = 3;
		contentPane.add(doormanList, gbc_doormanList);
		
		JButton btnNewButton_1 = new JButton("Confirm");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 6;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton = new JButton("Cancel");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		init();
	}

	
	private void init() {
		try {
			ShiftCtrl = new ShiftCtrl();
			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		updateDoormanList();
	}
	
	private void updateDoormanList() {
		try {
			List<Doorman> dlo = ShiftCtrl.getAvailableDoormenForShift();
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