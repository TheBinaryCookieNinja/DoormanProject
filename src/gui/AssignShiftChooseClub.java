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
		
		init();
	}
	
	private void init() throws DataAccessException {
		sCtrl = new ShiftCtrl();
		
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
		for(int i = 0; i<bars.size(); i++){
			Bar b = bars.get(i);
			System.out.println("Hej med dig");
			

		}
	}
}
	

