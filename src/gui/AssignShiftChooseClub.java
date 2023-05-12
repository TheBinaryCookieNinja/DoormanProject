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
import java.util.Date;
import java.util.List;
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
import model.Bar;
import model.Shift;

import javax.swing.event.MenuKeyEvent;
import java.awt.event.InputEvent;

public class AssignShiftChooseClub extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem;
	private PopUp pp;
	private ShiftCtrl sCtrl;

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
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		init();
	}
	
	private void init() throws DataAccessException {
		sCtrl = new ShiftCtrl();
		displayClubInfo();
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
	
	private void displayClubInfo() throws DataAccessException {
	    List<Bar> bars = sCtrl.findAllBars();
	    
	    for (Bar bar : bars) {
	        JPanel panel_1 = createBarPanel(bar);
	        contentPane.add(panel_1);
	    }
	    
	    revalidate(); 
	}
	
	private JPanel createBarPanel(Bar bar) {
	    JPanel panel_1 = new JPanel();
	    panel_1.setBorder(new LineBorder(Color.GRAY));
	    GridBagLayout gbl_panel_1 = new GridBagLayout();
	    gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
	    gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
	    gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
	    gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panel_1.setLayout(gbl_panel_1);
	    
	    JLabel lblName = new JLabel(bar.getName());
	    lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
	    GridBagConstraints gbc_lblName = new GridBagConstraints();
	    gbc_lblName.gridwidth = 2;
	    gbc_lblName.insets = new Insets(0, 0, 5, 5);
	    gbc_lblName.gridx = 1;
	    gbc_lblName.gridy = 0;
	    panel_1.add(lblName, gbc_lblName);
	    
	    JLabel lblAddress = new JLabel(bar.getAddress());
	    GridBagConstraints gbc_lblAddress = new GridBagConstraints();
	    gbc_lblAddress.gridwidth = 2;
	    gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
	    gbc_lblAddress.gridx = 1;
	    gbc_lblAddress.gridy = 1;
	    panel_1.add(lblAddress, gbc_lblAddress);
	    
	    JButton btnShiftTime1_1 = new JButton();
	    btnShiftTime1_1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            testMenu();
	        }
	    });
	    GridBagConstraints gbc_btnShiftTime1_1 = new GridBagConstraints();
	    gbc_btnShiftTime1_1.insets = new Insets(0, 0, 5, 5);
	    gbc_btnShiftTime1_1.gridx = 1;
	    gbc_btnShiftTime1_1.gridy = 3;
	    panel_1.add(btnShiftTime1_1, gbc_btnShiftTime1_1);
	    
	    JButton btnShiftTime1_2 = new JButton();
	    btnShiftTime1_2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        }
	    });
	    GridBagConstraints gbc_btnShiftTime1_2 = new GridBagConstraints();
	    gbc_btnShiftTime1_2.insets = new Insets(0, 0, 5, 5);
	    gbc_btnShiftTime1_2.gridx = 2;
	    gbc_btnShiftTime1_2.gridy = 3;
	    panel_1.add(btnShiftTime1_2, gbc_btnShiftTime1_2);
	    
	    return panel_1;
	}
}
	

