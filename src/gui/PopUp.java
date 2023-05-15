package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DataAccessException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PopUp dialog = new PopUp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PopUp() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JMenu mnNewMenu = new JMenu("Menu");
			contentPanel.add(mnNewMenu);
			{
				JMenuItem mntmNewMenuItem = new JMenuItem("Club menu");
				mntmNewMenuItem.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						try {
							testMenu();
						} catch (DataAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				mnNewMenu.add(mntmNewMenuItem);
			}
			{
				JMenuItem mntmNewMenuItem_1 = new JMenuItem("Assign shift calendar");
				mnNewMenu.add(mntmNewMenuItem_1);
			}
			{
				JMenuItem mntmNewMenuItem_2 = new JMenuItem("Whatever");
				mnNewMenu.add(mntmNewMenuItem_2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void testMenu() throws DataAccessException {
		AssignShiftChooseClub ascc = new AssignShiftChooseClub();
		this.setVisible(false);
		this.dispose();
		ascc.setVisible(true);
		ascc.setAlwaysOnTop(true);
		ascc.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
	}

}
