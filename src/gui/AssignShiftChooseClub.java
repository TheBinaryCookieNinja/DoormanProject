package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.event.MenuKeyListener;

import controller.ShiftCtrl;
import database.DataAccessException;

import javax.swing.event.MenuKeyEvent;
import java.awt.event.InputEvent;

public class AssignShiftChooseClub extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem;
	private PopUp pp;
	private ShiftCtrl shiftCtrl;
	private JButton btnShiftTime1;
	private JButton btnShiftTime1_1;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignShiftChooseClub frame = new AssignShiftChooseClub();
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
	public AssignShiftChooseClub() throws DataAccessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Assign shifts");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				testMenu();
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.SHIFT_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Calendar");
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.SHIFT_DOWN_MASK));
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				pp = new PopUp();
				pp.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_2 = new JMenuItem("Department schedule");
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.SHIFT_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblName1 = new JLabel("Fabrikken");
		lblName1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName1 = new GridBagConstraints();
		gbc_lblName1.gridwidth = 2;
		gbc_lblName1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName1.gridx = 1;
		gbc_lblName1.gridy = 0;
		panel_1.add(lblName1, gbc_lblName1);
		
		JLabel lblAddress1 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress1 = new GridBagConstraints();
		gbc_lblAddress1.gridwidth = 2;
		gbc_lblAddress1.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress1.gridx = 1;
		gbc_lblAddress1.gridy = 1;
		panel_1.add(lblAddress1, gbc_lblAddress1);
		
		btnShiftTime1_1 = new JButton("New button");
		btnShiftTime1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnShiftTime1 = new JButton();
		btnShiftTime1.setText("Hej");
		btnShiftTime1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testMenu();
			}
		});
		GridBagConstraints gbc_btnShiftTime1 = new GridBagConstraints();
		gbc_btnShiftTime1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime1.gridx = 1;
		gbc_btnShiftTime1.gridy = 3;
		panel_1.add(btnShiftTime1, gbc_btnShiftTime1);
		GridBagConstraints gbc_btnShiftTime1_1 = new GridBagConstraints();
		gbc_btnShiftTime1_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime1_1.gridx = 2;
		gbc_btnShiftTime1_1.gridy = 3;
		panel_1.add(btnShiftTime1_1, gbc_btnShiftTime1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblName2 = new JLabel("Fabrikken");
		lblName2.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName2 = new GridBagConstraints();
		gbc_lblName2.gridwidth = 2;
		gbc_lblName2.insets = new Insets(0, 0, 5, 5);
		gbc_lblName2.gridx = 1;
		gbc_lblName2.gridy = 0;
		panel_2.add(lblName2, gbc_lblName2);
		
		JLabel lblAddress2 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress2 = new GridBagConstraints();
		gbc_lblAddress2.gridwidth = 2;
		gbc_lblAddress2.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress2.gridx = 1;
		gbc_lblAddress2.gridy = 1;
		panel_2.add(lblAddress2, gbc_lblAddress2);
		
		JButton btnShiftTime2_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime2_1 = new GridBagConstraints();
		gbc_btnShiftTime2_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime2_1.gridx = 1;
		gbc_btnShiftTime2_1.gridy = 3;
		panel_2.add(btnShiftTime2_1, gbc_btnShiftTime2_1);
		
		JButton btnShiftTime2_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime2_2 = new GridBagConstraints();
		gbc_btnShiftTime2_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime2_2.gridx = 2;
		gbc_btnShiftTime2_2.gridy = 3;
		panel_2.add(btnShiftTime2_2, gbc_btnShiftTime2_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 0;
		contentPane.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblName3 = new JLabel("Fabrikken");
		lblName3.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName3 = new GridBagConstraints();
		gbc_lblName3.gridwidth = 2;
		gbc_lblName3.insets = new Insets(0, 0, 5, 5);
		gbc_lblName3.gridx = 1;
		gbc_lblName3.gridy = 0;
		panel_3.add(lblName3, gbc_lblName3);
		
		JLabel lblAddress3 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress3 = new GridBagConstraints();
		gbc_lblAddress3.gridwidth = 2;
		gbc_lblAddress3.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress3.gridx = 1;
		gbc_lblAddress3.gridy = 1;
		panel_3.add(lblAddress3, gbc_lblAddress3);
		
		JButton btnShiftTime3_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime3_1 = new GridBagConstraints();
		gbc_btnShiftTime3_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime3_1.gridx = 1;
		gbc_btnShiftTime3_1.gridy = 3;
		panel_3.add(btnShiftTime3_1, gbc_btnShiftTime3_1);
		
		JButton btnShiftTime3_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime3_2 = new GridBagConstraints();
		gbc_btnShiftTime3_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime3_2.gridx = 2;
		gbc_btnShiftTime3_2.gridy = 3;
		panel_3.add(btnShiftTime3_2, gbc_btnShiftTime3_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 3;
		gbc_panel_4.gridy = 0;
		contentPane.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblName4 = new JLabel("Fabrikken");
		lblName4.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName4 = new GridBagConstraints();
		gbc_lblName4.gridwidth = 2;
		gbc_lblName4.insets = new Insets(0, 0, 5, 5);
		gbc_lblName4.gridx = 1;
		gbc_lblName4.gridy = 0;
		panel_4.add(lblName4, gbc_lblName4);
		
		JLabel lblAddress4 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress4 = new GridBagConstraints();
		gbc_lblAddress4.gridwidth = 2;
		gbc_lblAddress4.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress4.gridx = 1;
		gbc_lblAddress4.gridy = 1;
		panel_4.add(lblAddress4, gbc_lblAddress4);
		
		JButton btnShiftTime4_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime4_1 = new GridBagConstraints();
		gbc_btnShiftTime4_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime4_1.gridx = 1;
		gbc_btnShiftTime4_1.gridy = 3;
		panel_4.add(btnShiftTime4_1, gbc_btnShiftTime4_1);
		
		JButton btnShiftTime4_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime4_2 = new GridBagConstraints();
		gbc_btnShiftTime4_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime4_2.gridx = 2;
		gbc_btnShiftTime4_2.gridy = 3;
		panel_4.add(btnShiftTime4_2, gbc_btnShiftTime4_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		contentPane.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblName5 = new JLabel("Fabrikken");
		lblName5.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName5 = new GridBagConstraints();
		gbc_lblName5.gridwidth = 2;
		gbc_lblName5.insets = new Insets(0, 0, 5, 5);
		gbc_lblName5.gridx = 1;
		gbc_lblName5.gridy = 0;
		panel_5.add(lblName5, gbc_lblName5);
		
		JLabel lblAddress5 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress5 = new GridBagConstraints();
		gbc_lblAddress5.gridwidth = 2;
		gbc_lblAddress5.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress5.gridx = 1;
		gbc_lblAddress5.gridy = 1;
		panel_5.add(lblAddress5, gbc_lblAddress5);
		
		JButton btnShiftTime5_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime5_1 = new GridBagConstraints();
		gbc_btnShiftTime5_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime5_1.gridx = 1;
		gbc_btnShiftTime5_1.gridy = 3;
		panel_5.add(btnShiftTime5_1, gbc_btnShiftTime5_1);
		
		JButton btnShiftTime5_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime5_2 = new GridBagConstraints();
		gbc_btnShiftTime5_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime5_2.gridx = 2;
		gbc_btnShiftTime5_2.gridy = 3;
		panel_5.add(btnShiftTime5_2, gbc_btnShiftTime5_2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 1;
		contentPane.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblName6 = new JLabel("Fabrikken");
		lblName6.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName6 = new GridBagConstraints();
		gbc_lblName6.gridwidth = 2;
		gbc_lblName6.insets = new Insets(0, 0, 5, 5);
		gbc_lblName6.gridx = 1;
		gbc_lblName6.gridy = 0;
		panel_6.add(lblName6, gbc_lblName6);
		
		JLabel lblAddress6 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress6 = new GridBagConstraints();
		gbc_lblAddress6.gridwidth = 2;
		gbc_lblAddress6.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress6.gridx = 1;
		gbc_lblAddress6.gridy = 1;
		panel_6.add(lblAddress6, gbc_lblAddress6);
		
		JButton btnShiftTime6_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime6_1 = new GridBagConstraints();
		gbc_btnShiftTime6_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime6_1.gridx = 1;
		gbc_btnShiftTime6_1.gridy = 3;
		panel_6.add(btnShiftTime6_1, gbc_btnShiftTime6_1);
		
		JButton btnShiftTime6_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime6_2 = new GridBagConstraints();
		gbc_btnShiftTime6_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime6_2.gridx = 2;
		gbc_btnShiftTime6_2.gridy = 3;
		panel_6.add(btnShiftTime6_2, gbc_btnShiftTime6_2);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 5);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 2;
		gbc_panel_7.gridy = 1;
		contentPane.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JLabel lblName7 = new JLabel("Fabrikken");
		lblName7.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName7 = new GridBagConstraints();
		gbc_lblName7.gridwidth = 2;
		gbc_lblName7.insets = new Insets(0, 0, 5, 5);
		gbc_lblName7.gridx = 1;
		gbc_lblName7.gridy = 0;
		panel_7.add(lblName7, gbc_lblName7);
		
		JLabel lblAddress7 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress7 = new GridBagConstraints();
		gbc_lblAddress7.gridwidth = 2;
		gbc_lblAddress7.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress7.gridx = 1;
		gbc_lblAddress7.gridy = 1;
		panel_7.add(lblAddress7, gbc_lblAddress7);
		
		JButton btnShiftTime7_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime7_1 = new GridBagConstraints();
		gbc_btnShiftTime7_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime7_1.gridx = 1;
		gbc_btnShiftTime7_1.gridy = 3;
		panel_7.add(btnShiftTime7_1, gbc_btnShiftTime7_1);
		
		JButton btnShiftTime7_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime7_2 = new GridBagConstraints();
		gbc_btnShiftTime7_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime7_2.gridx = 2;
		gbc_btnShiftTime7_2.gridy = 3;
		panel_7.add(btnShiftTime7_2, gbc_btnShiftTime7_2);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 3;
		gbc_panel_8.gridy = 1;
		contentPane.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JLabel lblName8 = new JLabel("Fabrikken");
		lblName8.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName8 = new GridBagConstraints();
		gbc_lblName8.gridwidth = 2;
		gbc_lblName8.insets = new Insets(0, 0, 5, 5);
		gbc_lblName8.gridx = 1;
		gbc_lblName8.gridy = 0;
		panel_8.add(lblName8, gbc_lblName8);
		
		JLabel lblAddress8 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress8 = new GridBagConstraints();
		gbc_lblAddress8.gridwidth = 2;
		gbc_lblAddress8.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress8.gridx = 1;
		gbc_lblAddress8.gridy = 1;
		panel_8.add(lblAddress8, gbc_lblAddress8);
		
		JButton btnShiftTime8_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime8_1 = new GridBagConstraints();
		gbc_btnShiftTime8_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime8_1.gridx = 1;
		gbc_btnShiftTime8_1.gridy = 3;
		panel_8.add(btnShiftTime8_1, gbc_btnShiftTime8_1);
		
		JButton btnShiftTime8_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime8_2 = new GridBagConstraints();
		gbc_btnShiftTime8_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime8_2.gridx = 2;
		gbc_btnShiftTime8_2.gridy = 3;
		panel_8.add(btnShiftTime8_2, gbc_btnShiftTime8_2);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.insets = new Insets(0, 0, 0, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 2;
		contentPane.add(panel_9, gbc_panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_9.setLayout(gbl_panel_9);
		
		JLabel lblName9 = new JLabel("Fabrikken");
		lblName9.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName9 = new GridBagConstraints();
		gbc_lblName9.gridwidth = 2;
		gbc_lblName9.insets = new Insets(0, 0, 5, 5);
		gbc_lblName9.gridx = 1;
		gbc_lblName9.gridy = 0;
		panel_9.add(lblName9, gbc_lblName9);
		
		JLabel lblAddress9 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress9 = new GridBagConstraints();
		gbc_lblAddress9.gridwidth = 2;
		gbc_lblAddress9.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress9.gridx = 1;
		gbc_lblAddress9.gridy = 1;
		panel_9.add(lblAddress9, gbc_lblAddress9);
		
		JButton btnShiftTime9_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime9_1 = new GridBagConstraints();
		gbc_btnShiftTime9_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime9_1.gridx = 1;
		gbc_btnShiftTime9_1.gridy = 3;
		panel_9.add(btnShiftTime9_1, gbc_btnShiftTime9_1);
		
		JButton btnShiftTime9_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime9_2 = new GridBagConstraints();
		gbc_btnShiftTime9_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime9_2.gridx = 2;
		gbc_btnShiftTime9_2.gridy = 3;
		panel_9.add(btnShiftTime9_2, gbc_btnShiftTime9_2);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 0, 5);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 1;
		gbc_panel_10.gridy = 2;
		contentPane.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblName10 = new JLabel("Fabrikken");
		lblName10.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName10 = new GridBagConstraints();
		gbc_lblName10.gridwidth = 2;
		gbc_lblName10.insets = new Insets(0, 0, 5, 5);
		gbc_lblName10.gridx = 1;
		gbc_lblName10.gridy = 0;
		panel_10.add(lblName10, gbc_lblName10);
		
		JLabel lblAddress10 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress10 = new GridBagConstraints();
		gbc_lblAddress10.gridwidth = 2;
		gbc_lblAddress10.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress10.gridx = 1;
		gbc_lblAddress10.gridy = 1;
		panel_10.add(lblAddress10, gbc_lblAddress10);
		
		JButton btnShiftTime10_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime10_1 = new GridBagConstraints();
		gbc_btnShiftTime10_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime10_1.gridx = 1;
		gbc_btnShiftTime10_1.gridy = 3;
		panel_10.add(btnShiftTime10_1, gbc_btnShiftTime10_1);
		
		JButton btnShiftTime10_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime10_2 = new GridBagConstraints();
		gbc_btnShiftTime10_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime10_2.gridx = 2;
		gbc_btnShiftTime10_2.gridy = 3;
		panel_10.add(btnShiftTime10_2, gbc_btnShiftTime10_2);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.insets = new Insets(0, 0, 0, 5);
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 2;
		gbc_panel_11.gridy = 2;
		contentPane.add(panel_11, gbc_panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_11.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_11.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_11.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_11.setLayout(gbl_panel_11);
		
		JLabel lblName11 = new JLabel("Fabrikken");
		lblName11.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName11 = new GridBagConstraints();
		gbc_lblName11.gridwidth = 2;
		gbc_lblName11.insets = new Insets(0, 0, 5, 5);
		gbc_lblName11.gridx = 1;
		gbc_lblName11.gridy = 0;
		panel_11.add(lblName11, gbc_lblName11);
		
		JLabel lblAddress11 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress11 = new GridBagConstraints();
		gbc_lblAddress11.gridwidth = 2;
		gbc_lblAddress11.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress11.gridx = 1;
		gbc_lblAddress11.gridy = 1;
		panel_11.add(lblAddress11, gbc_lblAddress11);
		
		JButton btnShiftTime11_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime11_1 = new GridBagConstraints();
		gbc_btnShiftTime11_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime11_1.gridx = 1;
		gbc_btnShiftTime11_1.gridy = 3;
		panel_11.add(btnShiftTime11_1, gbc_btnShiftTime11_1);
		
		JButton btnShiftTime11_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime11_2 = new GridBagConstraints();
		gbc_btnShiftTime11_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime11_2.gridx = 2;
		gbc_btnShiftTime11_2.gridy = 3;
		panel_11.add(btnShiftTime11_2, gbc_btnShiftTime11_2);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 3;
		gbc_panel_12.gridy = 2;
		contentPane.add(panel_12, gbc_panel_12);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_12.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_12.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);
		
		JLabel lblName12 = new JLabel("Fabrikken");
		lblName12.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblName12 = new GridBagConstraints();
		gbc_lblName12.gridwidth = 2;
		gbc_lblName12.insets = new Insets(0, 0, 5, 5);
		gbc_lblName12.gridx = 1;
		gbc_lblName12.gridy = 0;
		panel_12.add(lblName12, gbc_lblName12);
		
		JLabel lblAddress12 = new JLabel("Jomfru Ane Gade ??");
		GridBagConstraints gbc_lblAddress12 = new GridBagConstraints();
		gbc_lblAddress12.gridwidth = 2;
		gbc_lblAddress12.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress12.gridx = 1;
		gbc_lblAddress12.gridy = 1;
		panel_12.add(lblAddress12, gbc_lblAddress12);
		
		JButton btnShiftTime12_1 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime12_1 = new GridBagConstraints();
		gbc_btnShiftTime12_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime12_1.gridx = 1;
		gbc_btnShiftTime12_1.gridy = 3;
		panel_12.add(btnShiftTime12_1, gbc_btnShiftTime12_1);
		
		JButton btnShiftTime12_2 = new JButton("New button");
		GridBagConstraints gbc_btnShiftTime12_2 = new GridBagConstraints();
		gbc_btnShiftTime12_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnShiftTime12_2.gridx = 2;
		gbc_btnShiftTime12_2.gridy = 3;
		panel_12.add(btnShiftTime12_2, gbc_btnShiftTime12_2);
		
		init();
	}
	
	private void init() throws DataAccessException {
		shiftCtrl = new ShiftCtrl();
		btnShiftTime1.setText(shiftCtrl.getShiftById(2).getCheckInTime());
		btnShiftTime1_1.setText(shiftCtrl.getShiftById(2).getCheckOutTime());
		
	}
	private void testMenu() {
		PopUp pp = new PopUp();
		this.setVisible(false);
		this.dispose();
		pp.setVisible(true);
		pp.setModal(true); 
		pp.setAlwaysOnTop(true);
		pp.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
	}
	
}
