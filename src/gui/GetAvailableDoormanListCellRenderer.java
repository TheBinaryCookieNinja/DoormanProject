package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Doorman;


public class GetAvailableDoormanListCellRenderer implements ListCellRenderer<Doorman>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Doorman> list, Doorman value, int index,
			boolean isSelected, boolean cellHasFocus) {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		return dlcr.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}
	
}
