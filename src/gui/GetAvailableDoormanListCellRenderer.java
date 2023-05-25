package gui;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.time.LocalDate;
import java.util.List;
import model.Doorman;


class GetAvailableDoormanListCellRenderer extends DefaultListCellRenderer {
    private ImageIcon iconStar = new ImageIcon("path/to/icon1.png");
    private ImageIcon iconBusy = new ImageIcon("path/to/icon2.png");
    // Add more icons as needed for different rows
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        // Set the icon based on the index or any other condition
        if (index == 0) {
            setIcon(iconStar);
        } else if (index == 1) {
            setIcon(iconBusy);
        }
        
        return renderer;
    }
}
