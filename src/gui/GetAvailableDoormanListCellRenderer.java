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

	private DefaultListCellRenderer defaultRenderer;
    private ImageIcon iconChecked;
    private int iconSize;
    private Font iconFont;
    private DoormanCtrl doormanCtrl;
    private List<Doorman> doormen;


    public GetAvailableDoormanListCellRenderer() {
        defaultRenderer = new DefaultListCellRenderer();
        iconChecked = new ImageIcon("C:\\repose\\EksamensProjektDoorman\\src\\icons\\checkMark.png");
        iconSize = 20; // Adjust the icon size as needed
        iconFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12); // Adjust the font size as needed
        try {
            doormanCtrl = new DoormanCtrl();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
   
	@Override
    public Component getListCellRendererComponent(JList<? extends Doorman> list, Doorman doorman, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        Component renderer = defaultRenderer.getListCellRendererComponent(list, doorman.getF_name() + " " + doorman.getL_name(), index, isSelected, cellHasFocus);
        
		try {
		doormen = doormanCtrl.isDoormanOnAnotherShift();
			if(doormen.size() > 0) {
				for(int i = 0; i < doormen.size(); i++) {
					if (renderer instanceof JLabel && doorman.getEmployeeId() == doormen.get(i).getEmployeeId()) {
						JLabel label = (JLabel) renderer;
						label.setIcon(getScaledIcon(iconChecked, iconSize));
						label.setIconTextGap(2); // Adjust the gap between the icon and text as needed
						label.setFont(iconFont);
						label.setText("(Taken) " + doorman.getF_name() + " " + doorman.getL_name());
					}
				}
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
		}
		return renderer;
    }
    
    private ImageIcon getScaledIcon(ImageIcon icon, int size) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}