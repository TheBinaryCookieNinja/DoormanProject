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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
					//frame.pack();
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
		setBounds(100, 100, 839, 726);
		//setResizable(true);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnVagtkalender = new JButton("Vagtkalender");
		menuBar.add(btnVagtkalender);

		JButton btnVagtUddelegering = new JButton("Vagt uddelegering");
		menuBar.add(btnVagtUddelegering);

		JButton btnTagEnVagt = new JButton("Tag en vagt");
		menuBar.add(btnTagEnVagt);

		JButton btnAfdelingsplan = new JButton("Afdelingsplan");
		menuBar.add(btnAfdelingsplan);

		JButton btnStatistikker = new JButton("Statistikker");
		menuBar.add(btnStatistikker);
		btnStatistikker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVagtUddelegering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane = new JPanel();

		setContentPane(contentPane);

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

		DefaultListModel<String> modelEventsList = new DefaultListModel<>();
		modelEventsList.addElement("Item 1");
		modelEventsList.addElement("Item 2");
		modelEventsList.addElement("Item 3");
		modelEventsList.addElement("Item 4");
		modelEventsList.addElement("Item 5");
		modelEventsList.addElement("Item 6");
		modelEventsList.addElement("Item 7");
		modelEventsList.addElement("Item 8");

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

		JList<String> EventsList = new JList<String>(modelEventsList);

		JList<String> InfoList = new JList<String>(modelInfoList);
		InfoList.setBorder(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(InfoList, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
						.addComponent(textInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(EventsList, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
						.addComponent(txtEvents, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textInfo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEvents, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(InfoList, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE)
						.addComponent(EventsList, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		pack();

	}
}
