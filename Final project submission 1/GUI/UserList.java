package GUI;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import EntityList.*;
import File.*;

public class UserList extends JFrame implements ActionListener {
	Font titleFont = new Font("Algerian", Font.BOLD, 50);
	Font titleLabel = new Font("Cambria", Font.BOLD, 15);

	JButton dashboardBtn, menuBtn, logoutBtn;

	user u = null;

	public UserList(user u, users us) {
		super("list dashboard");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		this.u = u;

		// Title
		JLabel title = new JLabel("IIUC Hospital!");
		title.setBounds(290, 70, 400, 50);
		title.setFont(titleFont);
		title.setForeground(Color.WHITE);
		this.add(title);

		dashboardBtn = new JButton("PROFILE");
		dashboardBtn.setBounds(200, 360, 200, 40);
		dashboardBtn.setBackground(Color.GRAY);
		dashboardBtn.setForeground(Color.BLACK);
		dashboardBtn.setFont(titleLabel);
		dashboardBtn.addActionListener(this);
		this.add(dashboardBtn);

		menuBtn = new JButton("Problem&Appointment");
		menuBtn.setBounds(450, 360, 200, 40);
		menuBtn.setBackground(Color.GRAY);
		menuBtn.setForeground(Color.BLACK);
		menuBtn.setFont(titleLabel);
		menuBtn.addActionListener(this);
		this.add(menuBtn);

		logoutBtn = new JButton("LOG OUT");
		logoutBtn.setBounds(350, 500, 200, 40);
		logoutBtn.setBackground(Color.RED);
		logoutBtn.setForeground(Color.BLACK);
		logoutBtn.setFont(titleLabel);
		logoutBtn.addActionListener(this);
		this.add(logoutBtn);

		ImageIcon menupic = new ImageIcon("pic/ln.jpg");
		JLabel background = new JLabel(menupic);
		background.setBounds(0, 0, 900, 600);
		this.add(background);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();

		if (dashboardBtn.getText().equals(command)) {
			dashBoard db = new dashBoard(u, null); // if dashBoard still needs `users`, pass null or remove that param if unused
			db.setVisible(true);
			this.setVisible(false);
		} else if (logoutBtn.getText().equals(command)) {
			login lg = new login(); // ✅ fixed
			lg.setVisible(true);
			this.setVisible(false);
		} else if (menuBtn.getText().equals(command)) {
			DoctorList doctorList = new DoctorList(200);
			List l = new List(doctorList);
			l.setVisible(true);
			this.setVisible(false);
		}
	}
}
