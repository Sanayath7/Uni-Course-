package GUI;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import EntityList.*;
import File.*;



public class dashBoard extends JFrame implements MouseListener, ActionListener{
	JPanel panel;
	JLabel userLabel, passwordLabel, genderLabel, phnLabel, addressLabel;
	JButton logoutBtn, showBtn, editBtn, deleteBtn ,backBtn;
	Font myFont;
	
	String pass="",hiddenPass="";
	
	login lg;
	users us;
	user u;
	
	updateProfile upr;
	UserList ul;
	
	public dashBoard(user u, users us){
		super("My dashboard");
		this.setSize(500,400);
	
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		this.u = u;
		this.us = us;
		
		myFont = new Font("Cambria", Font.PLAIN, 17);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		userLabel = new JLabel("User name: "+u.getName()); 
		userLabel.setBounds(50,10,300,30);
		userLabel.setFont(myFont);
		panel.add(userLabel);
		
		pass = u.getPassword();
		int passLenghth = pass.length();
		for(int i =0;i<passLenghth;i++){
			hiddenPass += '*';
		}
		passwordLabel = new JLabel("Password: "+hiddenPass);
		passwordLabel.setBounds(50,50,200,30);
		passwordLabel.setFont(myFont);
		panel.add(passwordLabel);
		
		genderLabel = new JLabel("Gender: "+u.getGender());
		genderLabel.setBounds(50,90,300,30);
		genderLabel.setFont(myFont);
		panel.add(genderLabel);
		
		phnLabel = new JLabel("Phone No : "+u.getPhn());
		phnLabel.setBounds(50,130,300,30);
		phnLabel.setFont(myFont);
		panel.add(phnLabel);
		
		addressLabel = new JLabel("Address: "+u.getAddress());
		addressLabel.setBounds(50,170,300,30);
		addressLabel.setFont(myFont);
		panel.add(addressLabel);
		
		
		logoutBtn = new JButton("Log out");
		logoutBtn.setBounds(190,240,100,30);
		logoutBtn.setFont(myFont);
		logoutBtn.setForeground(Color.BLUE);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		showBtn = new JButton("Show");
		showBtn.setBounds(300,55,80,25);
		showBtn.setBorder(null);
		showBtn.addMouseListener(this);
		showBtn.addActionListener(this);
		panel.add(showBtn);
		
		editBtn =  new JButton("Edit profile");
		editBtn.setBounds(55,290,100,30);
		editBtn.addActionListener(this);
		panel.add(editBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(190,290,100,30);
		backBtn.setFont(myFont);
		backBtn.setForeground(Color.BLUE);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		
		
		deleteBtn = new JButton("Delete profile");
		deleteBtn.setBounds(325,290,120,30);
		deleteBtn.setOpaque(true);
		deleteBtn.addMouseListener(this);
		deleteBtn.addActionListener(this);
		panel.add(deleteBtn);
		
		this.add(panel);
		this.setVisible(true);
		
	}

	public void mouseClicked(MouseEvent me){}
	public void mouseEntered(MouseEvent me){
		if(me.getSource() == deleteBtn){
			deleteBtn.setForeground(Color.RED);
		}else{}
	} 	
	public void mouseExited(MouseEvent me){
		if(me.getSource() == deleteBtn){
			deleteBtn.setForeground(Color.BLACK);
		}else{}
	}   
	public void mousePressed(MouseEvent me){
		if(me.getSource() == showBtn){
			passwordLabel.setText("Password: "+pass);
		}
	}
	
	public void mouseReleased(MouseEvent me){
		if(me.getSource() == showBtn){
			passwordLabel.setText("Password: "+hiddenPass);
		}
	}
	
	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();
		if(logoutBtn.getText().equals(command)){
			login lg = new login(us);
			lg.setVisible(true);
			this.setVisible(false);
		}else if(deleteBtn.getText().equals(command)){
			int dialog = JOptionPane.YES_NO_OPTION;
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete your profile?", "Delete user?", dialog);
			
			if(result == 0){
				us.deleteUser(u.getName());
				
				JOptionPane.showMessageDialog(this, "User deleted. Redirecting to login page.");
				
				login lg = new login(us);
				lg.setVisible(true);
				this.setVisible(false);
			}else{ 
				
			}
		}
		else if(editBtn.getText().equals(command)){
			updateProfile upr = new updateProfile(u,us);
			upr.setVisible(true);
			this.setVisible(false);
		}
		else if(backBtn.getText().equals(command)){
			UserList ul = new UserList(u,us);
			        ul.setVisible(true);
			        this.setVisible(false);
		}
		
	}

}
