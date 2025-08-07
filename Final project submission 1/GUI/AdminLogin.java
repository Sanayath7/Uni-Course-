package GUI;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import EntityList.*;
import File.FFileIO;

public class AdminLogin extends JFrame implements ActionListener{
	

	JLabel userLabel, passLabel , title , title1;
	JTextField userTF;
	JPasswordField passTF;
	
	JButton loginButton, exitBtn;
    AdminLogin al ; 
	Manage m ;
	
	
	
	public AdminLogin(){
		super(" Admin Login page");
		this.setSize(900,600);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		Font font = new Font("Cambria",Font.BOLD,20);
		Font titleFont = new Font("Algerian",Font.BOLD,50);
		Font font1 = new Font("Algerian",Font.BOLD,30);
		
		
		this.setLayout(null);
		
		 title= new JLabel("IIUC Hospital!");
		title.setBounds(380,420,400,50);
		title.setFont(titleFont);
		this.add(title);
		
		 title1= new JLabel("ADMIN LOGIN");
		title1.setBounds(300,20,200,40);
		title1.setFont(font1);
		this.add(title1);
		
		
		 userLabel = new JLabel("User Name:");
		userLabel.setFont(font);
		userLabel.setBounds(250,100,120,30);
		
		userLabel.setBackground(Color.LIGHT_GRAY);
		userLabel.setOpaque(true);
		this.add(userLabel);
	
		
		userTF = new JTextField();
	
		userTF.setBounds(385,100,200,30);
		this.add(userTF);
		
		passLabel = new JLabel("Password: ");
		passLabel.setFont(font);
		passLabel.setBounds(250,140,120,30);
		passLabel.setBackground(Color.LIGHT_GRAY);
		
		passLabel.setOpaque(true);
		this.add(passLabel);
		
		passTF = new JPasswordField();
		
		passTF.setBounds(385,140,200,30);
		passTF.setEchoChar('!');
		this.add(passTF);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(300,200,80,30);
		loginButton.setBackground(Color.RED);
		loginButton.setForeground(Color.WHITE);
		loginButton.setOpaque(true);
		loginButton.addActionListener(this);
		this.add(loginButton);
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(400,200,80,30);
		exitBtn.setBackground(Color.RED);
		exitBtn.setForeground(Color.WHITE);
		exitBtn.setOpaque(true);
		exitBtn.addActionListener(this);
		this.add(exitBtn);
		
		ImageIcon user = new ImageIcon("pic/d.png");
		JLabel background = new JLabel(user);
		
		background.setBounds(0,0,900,600);
		this.add(background);
		this.setVisible(true);
		
		this.setVisible(true);
		
	}
		@Override
	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();
	    if(loginButton.getText().equals(command)){
			String name = userTF.getText();
			@SuppressWarnings("deprecation")
			String pass = passTF.getText();
			
				if(name.equals("sanayath")&& pass.equals("1234")){
				JOptionPane.showMessageDialog(this,"Login Successfull");
				
				
				DoctorList doctorList = new DoctorList(200);
	            FFileIO.loadDoctorsFromFile(doctorList);
				Manage m = new Manage(doctorList);
			
				m.setVisible(true);
				this.setVisible(false);
				
				
				userTF.setText("");
				passTF.setText("");
				
			}
			
		}else if((exitBtn.getText().equals(command))){
			System.exit(0);
		}
	}
}