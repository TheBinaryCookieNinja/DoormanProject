package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class ShiftCalenderCustom extends JPanel {
	private int month;
	private int year;
	private SlidingPanel slide;

	private JButton cmdBack;
	private JButton cmdNext;
	private JLayeredPane jLayeredPane1;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JLabel lbDate;
	private JLabel lbMonthYear;
	private JLabel lbTime;
	private JLabel lbType;

	/**
	 * Create the panel.
	 */
	public ShiftCalenderCustom() {
		initComponents();
		thisMonth();
		slide.show(new ShiftCalenderPanel(5, 2021), SlidingPanel.AnimateType.TO_RIGHT);
		showMonthYear();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.err.println(e);
					}
					Date date = new Date();
					SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
					SimpleDateFormat df = new SimpleDateFormat("EEEE, dd/MM-yyyy");
					String time = tf.format(date);
					lbTime.setText(time.split(" ")[0]);
					lbType.setText(time.split(" ")[1]);
					lbDate.setText(df.format(date));
				}
			}
		}).start();
	}

	@SuppressWarnings("unchecked")

	private void initComponents() {
		slide = new SlidingPanel();
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		lbTime = new JLabel();
		lbType = new JLabel();
		lbDate = new JLabel();
		jLayeredPane1 = new JLayeredPane();
		cmdBack = new JButton();
		lbMonthYear = new JLabel();
		cmdNext = new JButton();

		setBackground(new Color(255, 255, 255));

		slide.setBackground(new Color(255, 255, 255));

		GroupLayout slideLayout = new GroupLayout(slide);
		slide.setLayout(slideLayout);
		slideLayout.setHorizontalGroup(slideLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		slideLayout.setVerticalGroup(slideLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGap(0, 321, Short.MAX_VALUE));

		jPanel1.setBackground(new Color(97, 49, 237));

		jPanel2.setBackground(new Color(32, 0, 127));

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGap(0, 6, Short.MAX_VALUE));

		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGap(0, 391, Short.MAX_VALUE));

		lbTime.setFont(new Font("sansserif", 1, 48)); 
		lbTime.setForeground(new Color(201, 201, 201));
		lbTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTime.setText("9:32:00");

		lbType.setFont(new Font("sansserif", 1, 25)); 
		lbType.setForeground(new Color(201, 201, 201));
		lbType.setText("PM");

		lbDate.setFont(new Font("sansserif", 0, 18)); 
		lbDate.setForeground(new Color(201, 201, 201));
		lbDate.setHorizontalAlignment(SwingConstants.CENTER);
		lbDate.setText("Sunday, 30/05/2021");

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(lbTime,GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbType,GroupLayout.PREFERRED_SIZE, 52,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(lbDate, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(12, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup()
								.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbType))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lbDate)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		cmdBack.setIcon(new ImageIcon(getClass().getResource("/icon/back.png"))); 
		cmdBack.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		cmdBack.setContentAreaFilled(false);
		cmdBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmdBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cmdBackActionPerformed(evt);
			}
		});

		lbMonthYear.setFont(new Font("sansserif", 1, 30));
		lbMonthYear.setForeground(new Color(97, 49, 237));
		lbMonthYear.setHorizontalAlignment(SwingConstants.CENTER);
		lbMonthYear.setText("Month - Year");

		cmdNext.setIcon(new ImageIcon(getClass().getResource("/icon/next.png"))); 
		cmdNext.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		cmdNext.setContentAreaFilled(false);
		cmdNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmdNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cmdNextActionPerformed(evt);
			}

		});

		jLayeredPane1.setLayer(cmdBack, JLayeredPane.DEFAULT_LAYER);
		jLayeredPane1.setLayer(lbMonthYear, JLayeredPane.DEFAULT_LAYER);
		jLayeredPane1.setLayer(cmdNext, JLayeredPane.DEFAULT_LAYER);

		GroupLayout jLayeredPane1Layout = new GroupLayout(jLayeredPane1);
		jLayeredPane1.setLayout(jLayeredPane1Layout);
		jLayeredPane1Layout.setHorizontalGroup(jLayeredPane1Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jLayeredPane1Layout.createSequentialGroup().addContainerGap()
						.addComponent(cmdBack, GroupLayout.PREFERRED_SIZE, 53,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(lbMonthYear, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(cmdNext,
								GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jLayeredPane1Layout.setVerticalGroup(jLayeredPane1Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jLayeredPane1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(cmdBack, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lbMonthYear, GroupLayout.Alignment.TRAILING,
										GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(cmdNext, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(slide, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLayeredPane1))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addGap(13, 13, 13)
						.addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(slide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addContainerGap()));
	}

	private void cmdNextActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (month == 12) {
			month = 1;
			year++;
		} else {
			month++;
		}
		slide.show(new ShiftCalenderPanel(month, year), SlidingPanel.AnimateType.TO_LEFT);
		showMonthYear();
	}

	private void cmdBackActionPerformed(ActionEvent evt) {
		if (month == 1) {
			month = 12;
			year--;
		} else {
			month--;
		}
		slide.show(new ShiftCalenderPanel(month, year), SlidingPanel.AnimateType.TO_RIGHT);
		showMonthYear();
	}

	private void thisMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // today
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);
	}

	private void showMonthYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("MMMM-yyyy");
		lbMonthYear.setText(df.format(calendar.getTime()));
	}

}
