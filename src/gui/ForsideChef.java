package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JScrollPane;



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
	 */
	public ForsideChef() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();

		setContentPane(contentPane);
		
		JButton btnVagtUddelegering = new JButton("Vagt uddelegering");
		btnVagtUddelegering.setBounds(20, 120, 150, 30);
		btnVagtUddelegering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		
		Choice dropdownMenu = new Choice();
		dropdownMenu.setBounds(20, 43, 150, 26);
		dropdownMenu.add("Item 1");
		dropdownMenu.add("Item 2");
		dropdownMenu.add("Item 3");
		dropdownMenu.add("Item 4");
		dropdownMenu.addItemListener((ItemListener) new ItemListener() {
		    public void itemStateChanged1(ItemEvent e) {
		        String selected = dropdownMenu.getSelectedItem();
		        System.out.println("Selected: " + selected);
		    }

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
			}

			

		});
		contentPane.add(dropdownMenu);

		
		textInfo = new JTextField();
		textInfo.setEditable(false);
		textInfo.setHorizontalAlignment(SwingConstants.CENTER);
		textInfo.setText("Info");
		textInfo.setBounds(214, 26, 246, 26);
		contentPane.add(textInfo);
		textInfo.setColumns(10);
		
		txtEvents = new JTextField();
		txtEvents.setEditable(false);
		txtEvents.setText("Events");
		txtEvents.setHorizontalAlignment(SwingConstants.CENTER);
		txtEvents.setColumns(10);
		txtEvents.setBounds(502, 26, 246, 26);
		contentPane.add(txtEvents);
		contentPane.add(btnVagtUddelegering);
		
		JButton btnVagtkalender = new JButton("Vagtkalender");
		btnVagtkalender.setBounds(20, 78, 150, 30);
		contentPane.add(btnVagtkalender);
		
		
		DefaultListModel<String> l1 = new DefaultListModel<>();  
		l1.addElement("Item1");  
		l1.addElement("Item2");  
		l1.addElement("Item3");  
		l1.addElement("Item4");

		   
		
		JButton btnTagEnVagt = new JButton("Tag en vagt");
		btnTagEnVagt.setBounds(20, 162, 150, 30);
		contentPane.add(btnTagEnVagt);
		
		JButton btnAfdelingsplan = new JButton("Afdelingsplan");
		btnAfdelingsplan.setBounds(20, 204, 150, 30);
		contentPane.add(btnAfdelingsplan);
		
		JButton btnStatistikker = new JButton("Statistikker");
		btnStatistikker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStatistikker.setBounds(20, 246, 150, 30);
		contentPane.add(btnStatistikker);
		
		DefaultListModel<String> modelInfoList = new DefaultListModel<>();
		modelInfoList.addElement("Item 1");
		modelInfoList.addElement("Item 2");
		modelInfoList.addElement("Item 3");
		modelInfoList.addElement("Item 4");
		modelInfoList.addElement("Item 5");
		modelInfoList.addElement("Item 6");
		modelInfoList.addElement("Item 7");
		modelInfoList.addElement("Item 8");

		JList<String> InfoList= new JList<String>(modelInfoList);
		InfoList.setBorder(null);
		JScrollPane scrollPane = new JScrollPane(InfoList);
		scrollPane.setBounds(217, 48, 246, 473);
		contentPane.add(scrollPane);



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
		scrollPane2.setBounds(505, 48, 246, 473);
		contentPane.add(scrollPane2);

	}
}


