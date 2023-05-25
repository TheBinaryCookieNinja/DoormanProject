package gui;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import controller.DoormanCtrl;
import database.DataAccessException;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JLabel;

import model.Doorman;



class GetAvailableDoormanListCellRenderer implements ListCellRenderer<Doorman> {

    private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    private ImageIcon iconBusy = new ImageIcon("C:\\repose\\EksamensProjektDoorman\\src\\icons\\busyWait.png"); // Replace with the actual path to your icon
    private int iconSize = 20; // Adjust the icon size as needed
    private Font iconFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12	); // Adjust the font size as needed
    private DoormanCtrl doormanCtrl;

    @Override
    public Component getListCellRendererComponent(JList<? extends Doorman> list, Doorman doorman, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        Component renderer = defaultRenderer.getListCellRendererComponent(list, doorman.getF_name() + " " + doorman.getL_name(), index, isSelected, cellHasFocus);
        try {
			doormanCtrl = new DoormanCtrl();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Doorman> doormen;
		try {
			doormen = doormanCtrl.isDoormanOnAnotherShift();
			for(int i = 0; i < doormen.size(); i++) {
		        if (renderer instanceof JLabel && doorman.getEmployeeId() == doormen.get(i).getEmployeeId()) {
		            JLabel label = (JLabel) renderer;
		            label.setIcon(getScaledIcon(iconBusy, iconSize));
		            label.setIconTextGap(2); // Adjust the gap between the icon and text as needed
		            label.setFont(iconFont);
		        }
	        }
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	    
        return renderer;
    }
    
    private ImageIcon getScaledIcon(ImageIcon icon, int size) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}