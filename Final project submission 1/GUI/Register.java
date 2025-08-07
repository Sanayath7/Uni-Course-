package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import File.DBConnection;
import Entity.user;
import EntityList.users;

public class Register extends JFrame implements ActionListener {
	JPanel panel;
	JLabel usernameLabel, passwordLabel, genderLabel, phoneLabel, addressLabel, title;
	JTextField usernameTF, phoneTF;
	JPasswordField passwordTF;
	JComboBox<String> genderCB;
	JTextArea addressTA;
	JButton registerBtn, backBtn;
	users us;

	public Register(users us) {
		super("REGISTRATION PAGE");
		this.us = us;
		this.setSize(950, 620);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setLayout(null);

		Font font = new Font("Cambria", Font.BOLD, 15);
		Font titleFont = new Font("Algerian", Font.BOLD, 30);

		title = new JLabel("Patient Registration");
		title.setBounds(300, 30, 400, 30);
		title.setFont(titleFont);
		title.setForeground(Color.BLACK);
		panel.add(title);

		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(100, 100, 100, 30);
		usernameLabel.setFont(font);
		panel.add(usernameLabel);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(100, 150, 100, 30);
		passwordLabel.setFont(font);
		panel.add(passwordLabel);

		genderLabel = new JLabel("Gender:");
		genderLabel.setBounds(100, 200, 100, 30);
		genderLabel.setFont(font);
		panel.add(genderLabel);

		phoneLabel = new JLabel("Phone:");
		phoneLabel.setBounds(100, 250, 100, 30);
		phoneLabel.setFont(font);
		panel.add(phoneLabel);

		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(100, 300, 100, 30);
		addressLabel.setFont(font);
		panel.add(addressLabel);

		usernameTF = new JTextField();
		usernameTF.setBounds(200, 100, 200, 30);
		panel.add(usernameTF);

		passwordTF = new JPasswordField();
		passwordTF.setBounds(200, 150, 200, 30);
		panel.add(passwordTF);

		genderCB = new JComboBox<>(new String[]{"Male", "Female", "Other"});
		genderCB.setBounds(200, 200, 200, 30);
		panel.add(genderCB);

		phoneTF = new JTextField();
		phoneTF.setBounds(200, 250, 200, 30);
		panel.add(phoneTF);

		addressTA = new JTextArea();
		addressTA.setBounds(200, 300, 200, 60);
		panel.add(addressTA);

		registerBtn = new JButton("Register");
		registerBtn.setBounds(150, 400, 100, 30);
		registerBtn.setBackground(Color.GREEN);
		registerBtn.setForeground(Color.BLACK);
		registerBtn.addActionListener(this);
		panel.add(registerBtn);

		backBtn = new JButton("Back");
		backBtn.setBounds(270, 400, 100, 30);
		backBtn.setBackground(Color.RED);
		backBtn.setForeground(Color.WHITE);
		backBtn.addActionListener(this);
		panel.add(backBtn);

		ImageIcon bg = new ImageIcon("pic/hm.jpg");
		JLabel background = new JLabel(bg);
		background.setBounds(0, 0, 950, 620);
		panel.add(background);

		this.add(panel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();

		if (command.equals("Register")) {
			String username = usernameTF.getText();
			String password = new String(passwordTF.getPassword());
			String gender = (String) genderCB.getSelectedItem();
			String phone = phoneTF.getText();
			String address = addressTA.getText();

			if (username.isEmpty() || password.isEmpty() || gender.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
				return;
			}

			try {
				Connection con = DBConnection.getConnection();
				String sql = "INSERT INTO users (username, password, gender, phone_number, address) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, password);
				pst.setString(3, gender);
				pst.setString(4, phone);
				pst.setString(5, address);
				int result = pst.executeUpdate();

				if (result > 0) {
					JOptionPane.showMessageDialog(this, "Registration successful!");
					user newUser = new user(username, password, gender, phone, address);
					us.addUser(newUser);
					login lg = new login(us);
					lg.setVisible(true);
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "Failed to register. Try again.");
				}

			} catch (SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(this, "Username already exists. Choose another.");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
			}

		} else if (command.equals("Back")) {
			login lg = new login(us);
			lg.setVisible(true);
			this.setVisible(false);
		}
	}
}
