package gui;

import java.awt.EventQueue;
import gui.MainCalendar;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DataAccessException;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Dialog;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ForsideChef extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Choice DropdownMenu;
    private JTextField textInfo;
    private JTextField txtEvents;
    private String items;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ForsideChef frame = new ForsideChef();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * 
     * @throws UnsupportedLookAndFeelException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws IOException 
     */
    public ForsideChef() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException, IOException {
        setUIStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1500, 1100);
        contentPane = new JPanel();

        setContentPane(contentPane);

        textInfo = new JTextField();
        textInfo.setEditable(false);
        textInfo.setHorizontalAlignment(SwingConstants.CENTER);
        textInfo.setText("Info");
        textInfo.setColumns(10);

        txtEvents = new JTextField();
        txtEvents.setEditable(false);
        txtEvents.setText("Events");
        txtEvents.setHorizontalAlignment(SwingConstants.CENTER);
        txtEvents.setColumns(10);

        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Item1");
        l1.addElement("Item2");
        l1.addElement("Item3");
        l1.addElement("Item4");

        DefaultListModel<String> modelInfoList = new DefaultListModel<>();
        modelInfoList.addElement("Item 1");
        modelInfoList.addElement("Item 2");
        modelInfoList.addElement("Item 3");
        modelInfoList.addElement("Item 4");
        modelInfoList.addElement("Item 5");
        modelInfoList.addElement("Item 6");
        modelInfoList.addElement("Item 7");
        modelInfoList.addElement("Item 8");

        JList<String> InfoList = new JList<String>(modelInfoList);
        InfoList.setBorder(null);
        JScrollPane scrollPane = new JScrollPane(InfoList);

        DefaultListModel<String> modelEventsList = new DefaultListModel<>();
        modelEventsList.addElement("Item 1");
        modelEventsList.addElement("Item 2");
        modelEventsList.addElement("Item 3");
        modelEventsList.addElement("Item 4");
        modelEventsList.addElement("Item 5");
        modelEventsList.addElement("Item 6");
        modelEventsList.addElement("Item 7");
        modelEventsList.addElement("Item 8");

        JList<String> EventsList = new JList<String>(modelEventsList);
        JScrollPane scrollPane2 = new JScrollPane(EventsList);

        JMenuBar menuBar = new JMenuBar();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl_contentPane.createSequentialGroup().addGap(30)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textInfo, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
                        .addGap(36).addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(txtEvents, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 350,
                                        GroupLayout.PREFERRED_SIZE))));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addGap(6)
                        .addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addGap(17)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(24)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 473,
                                                GroupLayout.PREFERRED_SIZE))
                                .addComponent(textInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEvents, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(24)
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 473,
                                                GroupLayout.PREFERRED_SIZE)))));

        JMenu mnNewMenu = new JMenu("Menu");
        menuBar.add(mnNewMenu);

        JMenuItem mntmAssignShift = new JMenuItem("Assign Shift");
        mnNewMenu.add(mntmAssignShift);
        mntmAssignShift.addActionListener(e -> {
            try {
                openAssignShiftCalendar();
            } catch (DataAccessException e1) {
                e1.printStackTrace();
            }
        });

        JMenuItem mntmRegisterAvailability = new JMenuItem("Register availability");
        mnNewMenu.add(mntmRegisterAvailability);
        mntmRegisterAvailability.addActionListener(e -> {
            try {
                openRegisterAvailabilityCalendar();
            } catch (DataAccessException e1) {
                e1.printStackTrace();
            }
        });
        
        JMenuItem mntmLogout = new JMenuItem("Logout");
        mnNewMenu.add(mntmLogout);
        mntmLogout.addActionListener(e -> {
            performLogout();
        });

        contentPane.setLayout(gl_contentPane);

        setLocationRelativeTo(null);
        pack();
        
     // Set the title icon image
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/bpslogostor.png"));
        setIconImage(icon.getImage());


    }

    private void openAssignShiftCalendar() throws DataAccessException {
        MainCalendar calendar = new MainCalendar();
        calendar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calendar.setVisible(true);
    }

    private void openRegisterAvailabilityCalendar() throws DataAccessException {
        MainAvailabilityCalendar availabilityCalendar = new MainAvailabilityCalendar();
        availabilityCalendar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        availabilityCalendar.setVisible(true);
    }
    
    private void performLogout() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Perform logout operations here
            // ...

            // Close the current window
            dispose();

            // Show the login screen
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        LoginScreen loginScreen = new LoginScreen();
                        loginScreen.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void setUIStyle() {
        // https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
        // Nimbus uses Java 2D vector graphics to draw the user interface (UI), rather than static bitmaps,
        // so the UI can be crisply rendered at any resolution
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
    }
}
