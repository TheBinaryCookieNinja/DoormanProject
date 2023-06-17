package gui;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.DoormanCtrl;
import database.DataAccessException;
import model.Doorman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;


public class LoginScreen extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private DoormanCtrl doormanCtrl;
	private GridBagConstraints constraints_1;
	private JTextField passwordField;
	private GridBagConstraints constraints_2;

	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginScreen loginScreen = null;
				try {
					loginScreen = new LoginScreen();
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                loginScreen.setVisible(true);
            }
        });
    }
	
    public LoginScreen() throws DataAccessException, IOException {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Set Nimbus look and feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, the exception is handled here
            e.printStackTrace();
        }

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        usernameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    authenticateUser();
                } catch (NumberFormatException | DataAccessException | ClassNotFoundException
                        | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException
                        | SQLException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					authenticateUser();
				} catch (NumberFormatException | ClassNotFoundException | InstantiationException
						| IllegalAccessException | DataAccessException | UnsupportedLookAndFeelException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

     // Create layout
        
        
        
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWeights = new double[]{0.0, 1.0};
        JPanel panel = new JPanel(gbl_panel);
        GridBagConstraints constraints;

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints_1 = new GridBagConstraints();
        constraints_1.gridy = 0;
        constraints_1.insets = new Insets(10, 10, 10, 10);
        constraints_1.gridx = 1;
        panel.add(usernameField, constraints_1);

        constraints_2 = new GridBagConstraints();
        constraints_2.anchor = GridBagConstraints.EAST;
        constraints_2.insets = new Insets(10, 10, 10, 10);
        constraints_2.gridx = 0;
        constraints_2.gridy = 1;
        panel.add(passwordLabel, constraints_2);
        
        passwordField = new JTextField(20);
        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    authenticateUser();
                } catch (NumberFormatException | DataAccessException | ClassNotFoundException
                        | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException
                        | SQLException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordField.gridx = 1;
        gbc_passwordField.gridy = 1;
        panel.add(passwordField, gbc_passwordField);

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        getContentPane().add(panel);

        pack();
        
    	init();
    	}
    	
    	private void init() throws DataAccessException {
    		doormanCtrl = new DoormanCtrl();	
    	}
    	
    	private void authenticateUser() throws NumberFormatException, DataAccessException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException, IOException {
    	    try {
    	        Doorman d = doormanCtrl.getDoormanByDoormanId(Integer.parseInt(usernameField.getText()));
    	        if (d.getPasscode().equals(passwordField.getText())) {
    	            displayFrontpage();
    	            dispose();
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Forkert brugernavn eller adgangskode.", "Login Error", JOptionPane.ERROR_MESSAGE);
    	        }
    	    } finally {
    	        
    	    } 
    	 } 
    
    	private void displayFrontpage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
    	    ForsideChef fc = new ForsideChef();
    	    fc.setVisible(true);
    	    fc.setAlwaysOnTop(true);
    	    fc.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    	} 
    	
}

