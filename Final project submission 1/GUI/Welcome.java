package GUI;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import EntityList.*;
import File.*;

public class Welcome extends JFrame implements ActionListener{
	 JLabel manageLabel;

	 JButton adminBtn,userBtn;
	 
    AdminLogin al; 
	Welcome w ;
	login lg;
	
	
	users us ;
	
	Font titleFont = new Font("Algerian",Font.BOLD,50);
	Font titleLabel = new Font("Cambria",Font.BOLD,30);
	
	public Welcome(){
		super("welcome page");
		this.setSize(900,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setLocationRelativeTo(null); 
	    this.setLayout(null);
		
	
		
		JLabel title = new JLabel("IIUC Hospital!");
		title.setBounds(275,70,400,60);
		title.setFont(titleFont);
		title.setForeground(Color.RED);
		this.add(title);
		
		manageLabel = new JLabel("Welcome");
		manageLabel.setBounds(380,35,300,40);
		manageLabel.setFont(titleLabel);
		manageLabel.setForeground(Color.BLACK);
		this.add(manageLabel);
		
		adminBtn = new JButton("Admin");
		adminBtn.setBounds(260,400,150,40);
		adminBtn.setFont(titleLabel);
		adminBtn.setBackground(Color.GRAY);
		adminBtn.setForeground(Color.BLACK);
		adminBtn.addActionListener(this);
		this.add(adminBtn);
		
	        userBtn = new JButton("Patient");
		userBtn.setBounds(450,400,150,40);
		userBtn.setFont(titleLabel);
		userBtn.setBackground(Color.GRAY);
		userBtn.setForeground(Color.BLACK);
		userBtn.addActionListener(this);
		this.add(userBtn);
		
		
		
		ImageIcon welcome = new ImageIcon("pic/wl.jpg");
		JLabel background = new JLabel(welcome);
		background.setBounds(0,0,900,600);
		this.add(background);
		
		this.setVisible(true);
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();
		if(adminBtn.getText().equals(command)){
			AdminLogin al = new AdminLogin();
			al.setVisible(true);
			this.setVisible(false);
		}
		else if(userBtn.getText().equals(command)){
			users us = new users();
			login lg = new login(us);
			lg.setVisible(true);
		this.setVisible(false);
			
		}
		else{}
	}
	
}

