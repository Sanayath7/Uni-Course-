package GUI;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import File.*;
import java.io.*;
import Entity.*;
import EntityList.*;
import File.FFileIO;


public class List extends JFrame  implements ActionListener{
	JButton addBt,cancelBt;
	 JLabel userLabel,menuLabel,imgLabel;
	JComboBox comboBox;
	 JPanel panel;
	 JTextField userTf;
	 JTextArea textArea,addArea;
	ImageIcon img;
	
	Font titleLabel = new Font("Cambria",Font.BOLD,30);
	Font titleFont = new Font("Algerian",Font.BOLD,50);
	
	Doctor d;
	
	DoctorList doctorList;
	UserList ul;
	
	public List(DoctorList doctorList){
		super("Appointment");
		this.setSize(900,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.doctorList=doctorList;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		JLabel title = new JLabel("IIUC Hospital!");
		title.setBounds(250,40,400,60); //X,Y,W,H
		title.setFont(titleFont);
		title.setForeground(Color.RED);
		panel.add(title);
		
		
		userLabel = new JLabel("Select Doctor byProblem");
		userLabel.setBounds(450,120,350,40);
		userLabel.setFont(titleLabel);
		userLabel.setForeground(Color.BLACK);
		panel.add(userLabel);
		
		userTf =new JTextField();
		userTf.setBounds(552,190,130,35);
		panel.add(userTf);
		
		addBt = new JButton("Appoint");
		addBt.setBounds(573,240,90,30);
		addBt.setBackground(Color.BLACK);
		addBt.setForeground(Color.WHITE);
		addBt.addActionListener(this);
		panel.add(addBt);
		
	

		
		
		cancelBt = new JButton("Exit");
		cancelBt.setBounds(130,480,90,30);
		cancelBt.setBackground(Color.BLACK);
		cancelBt.setForeground(Color.WHITE);
		cancelBt.addActionListener(this);
		panel.add(cancelBt);
		
		menuLabel = new JLabel("Details");
		menuLabel.setBounds(125,100,120,35);
		menuLabel.setFont(titleLabel);
		menuLabel.setForeground(Color.BLACK);
		panel.add(menuLabel);
		
		textArea = new JTextArea();
		textArea.setBounds(480,120,400,450);
		
		textArea.setEditable(false);
		panel.add(textArea);
		
		FFileIO.loadDoctorsFromFile(doctorList);
 
	    reloadAllDoctorss();
		JScrollPane jsp = new JScrollPane(textArea);
		jsp.setBounds(50,150,250,300);
		
		panel.add(jsp);
		
		addArea = new JTextArea();
		addArea.setBounds(480,120,400,450);
		
		addArea.setEditable(false);
		panel.add(addArea);
 
	  
		JScrollPane sk = new JScrollPane(addArea);
		sk.setBounds(485,280,270,250);
		panel.add(sk);
		
		
		img = new ImageIcon("pic/mdc.jpg");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(0,0,900,600);
		panel.add(imgLabel);
		
		this.add(panel);
		
		this.setVisible(true);
	}
	
	
		@Override
	public void actionPerformed(ActionEvent ae){
		 String command = ae.getActionCommand();
		if(addBt.getText().equals(command)){
			 
    
        System.out.println("Appoint");
       Doctor d = doctorList.getByProblem(userTf.getText());
			if(d!=null){
				addArea.setText( d.getFoodInfoAsString());
			}
			
		  }
		  else{
		  JOptionPane.showMessageDialog(this, "Thanks for giving us time");
		  System.exit(0);
		  }
		
	}
	public void reloadAllDoctorss(){
		textArea.setText(doctorList.getAll());
	}
}