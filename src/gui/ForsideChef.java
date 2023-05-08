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
import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;



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

		JList<String> InfoList= new JList<String>(modelInfoList);
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
		
		JButton btnVagtkalender = new JButton("Vagtkalender");
		menuBar.add(btnVagtkalender);
		
		JButton btnArbejdsdage = new JButton("Angiv arbejdsdage");
		menuBar.add(btnArbejdsdage);
		
				   
				
				JButton btnUddelegering = new JButton("Vagt uddelegering");
				menuBar.add(btnUddelegering);
				
				JButton btnAfdelingsplan = new JButton("Afdelingsplan");
				menuBar.add(btnAfdelingsplan);
				
				JButton btnStatistikker = new JButton("Statistikker");
				menuBar.add(btnStatistikker);
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
								.addComponent(textInfo, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEvents, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(24)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE))
								.addComponent(textInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEvents, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(24)
									.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE))))
				);
				contentPane.setLayout(gl_contentPane);
				btnStatistikker.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
		btnArbejdsdage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

	}
}


