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


public class Manage extends JFrame implements ActionListener{
	Font titleFont = new Font("Algerian",Font.BOLD,30);
	Font titleLabel = new Font("Cambria",Font.BOLD,15);
	Font menuFont = new Font("Cambria",Font.BOLD,25);
	JTextField problemTextField,nameTextField,degreeTextField,chargesTextField;
	JTextField deleteTextField;
	JButton addButton,updateButton,deleteButton,clearButton,showAllButton,backButton,saveButton;
	JTextArea textArea;
	
	
    DoctorList doctorList;
	
	public Manage(DoctorList doctorList){
		super("IIUC Hospital!");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(900,700); 
		this.setLocationRelativeTo(null); 
	
		this.setLayout(null);
	    this.doctorList = doctorList;
		
		JLabel title = new JLabel("IIUC Hospital!");
		title.setBounds(250,15,400,50); 
		title.setFont(titleFont);
		
		
		JLabel subTitle = new JLabel("Details");
		subTitle.setBounds(650,70,110,30); 
		subTitle.setFont(menuFont);
		
		
			
		JLabel problemLabel = new JLabel("Problem");
		problemLabel.setBounds(30,110,180,20); 
		problemLabel.setFont(titleLabel);
		
		problemTextField = new JTextField();
		problemTextField.setBounds(30,140,180,20); 
		problemTextField.setFont(titleLabel);
		
		
		JLabel nameLabel = new JLabel("Doctor Name");
		nameLabel.setBounds(30,170,180,20); 
		nameLabel.setFont(titleLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(30,200,180,20); 
		nameTextField.setFont(titleLabel);
		

		JLabel degreeLabel = new JLabel("Doctor degree");
	        degreeLabel.setBounds(30,230,180,20);
		degreeLabel.setFont(titleLabel);
		
		degreeTextField = new JTextField();
		degreeTextField.setBounds(30,260,180,20);
		degreeTextField.setFont(titleLabel);
		

		
		
		
		JLabel chargesLabel = new JLabel("Visiting charge");
		chargesLabel.setBounds(30,290,180,20); 
		chargesLabel.setFont(titleLabel);
		
		chargesTextField = new JTextField();
		chargesTextField.setBounds(30,320,180,20);
		chargesTextField.setFont(titleLabel);
		
	
		
		
		
		addButton = new JButton("ADD");
		addButton.setBounds(30,360,180,20); 
		addButton.setBackground(Color.GREEN);
		addButton.setFont(titleLabel);
		addButton.addActionListener(this);
		
		
		updateButton = new JButton("UPDATE");
		updateButton.setBounds(30,390,180,20);
		updateButton.setBackground(Color.CYAN);
		updateButton.setForeground(Color.WHITE);
		updateButton.setFont(titleLabel);
		updateButton.addActionListener(this);
		

		
		
		//INFORMATION DISPLAY  AREA 
		
		textArea = new JTextArea();
		textArea.setBounds(480,120,400,450);
		textArea.setFont(titleLabel);
		textArea.setEditable(false);
		this.add(textArea);
 
	  reloadAllDoctors();
	
	  
	JScrollPane jsp = new JScrollPane(textArea);
		jsp.setBounds(480,120,380,500);
		
		this.add(jsp);

	  
		
		
		
		
		
		//Delete Entry  
		
		JLabel deleteLabel = new JLabel("Delete By Doctor ID");
		deleteLabel.setBounds(260,200,200,20); 
		deleteLabel.setFont(titleLabel);
		
		deleteTextField = new JTextField();
		deleteTextField.setBounds(260,230,200,20);
		deleteTextField.setFont(titleLabel);
		
		deleteButton = new JButton("DELETE");
		deleteButton.setBounds(260,255,200,20);
		deleteButton.setBackground(Color.RED);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(titleLabel);
		deleteButton.addActionListener(this);
		
		
		
		
		//Show All information
		
		showAllButton = new JButton("SHOW ALL Doctor");
		showAllButton.setBounds(260,300,200,20); //X,Y,W,H
		showAllButton.setBackground(Color.MAGENTA);
		showAllButton.setForeground(Color.WHITE);
		showAllButton.setFont(titleLabel);
		showAllButton.addActionListener(this);
		
		
		// Clear Screen 
		
		clearButton = new JButton("CLEAR SCREEN");
		clearButton.setBounds(260,330,200,20);
		clearButton.setBackground(Color.DARK_GRAY);
		clearButton.setForeground(Color.WHITE);
		clearButton.setFont(titleLabel);
		clearButton.addActionListener(this);
		
		
		backButton = new JButton("Exit");
		backButton.setBounds(260,360,200,20);
		backButton.setBackground(Color.GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);
		

		saveButton = new JButton("SAVE");
		saveButton.setBounds(260,390,200,20);
		saveButton.setBackground(Color.ORANGE);
		saveButton.setForeground(Color.WHITE);
		saveButton.addActionListener(this);
		
		
		
		
		this.add(title);
		this.add(subTitle);
		this.add(problemLabel);
		this.add(problemTextField);
		this.add(nameLabel);
		this.add(nameTextField);
		this.add(degreeLabel);
		this.add(degreeTextField);
	
		this.add(chargesLabel);
		this.add(chargesTextField);
		this.add(addButton);
		this.add(updateButton);
		
		this.add(deleteLabel);
		this.add(deleteTextField);
		this.add(deleteButton);
		this.add(clearButton);
		this.add(showAllButton);
		this.add(backButton);
		this.add(saveButton);
		
		this.setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();
		

    Object f = null;
	if (addButton.getText().equals(command)) {
        System.out.println("ADD CLICKED");
		if(
				!problemTextField.getText().isEmpty() &&
				!nameTextField.getText().isEmpty() &&
				!degreeTextField.getText().isEmpty() &&
				!chargesTextField.getText().isEmpty()
			){
			if( doctorList.getByProblem(problemTextField.getText()) == null){
				Doctor d = new Doctor(
							
							problemTextField.getText(),
							nameTextField.getText(),
							
							degreeTextField.getText(),
							
						    Integer.parseInt( chargesTextField.getText())
						);
					
					doctorList.insert(d);
					
					
					
					
					FFileIO.writeDoctorInFile(d);
					
					
				
					
					reloadAllDoctors();
			}
					
			else{
					JOptionPane.showMessageDialog(this,"This ID Already Exists","Provide Unique ID",JOptionPane.ERROR_MESSAGE);
				}
			}
        else{
				JOptionPane.showMessageDialog(this,"Please Provide all Information for ","Missing Information",JOptionPane.ERROR_MESSAGE);
			}
    }
	
	else if (updateButton.getText().equals(command)) {
      
        System.out.println("UPDATE CLICKED");
  			if(!problemTextField.getText().isEmpty() ){
				Doctor d = doctorList.getByProblem(problemTextField.getText());
				if(f!=null){
					if(!nameTextField.getText().isEmpty()){
						d.setDoctorName(nameTextField.getText());
					}
					
										
					
					
					if(!chargesTextField.getText().isEmpty()){
						d.setCharges( Integer.parseInt( chargesTextField.getText() ));
					}
					
					FFileIO.updateDoctorsInFile(doctorList);
					
					reloadAllDoctors();
				}
				else{
					JOptionPane.showMessageDialog(this,"No Available Doctor ","Doctor Not Found",JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this,"Please Provide ID ","Missing Information",JOptionPane.ERROR_MESSAGE);
			}    
    }
	
	else if (deleteButton.getText().equals(command)) {
       
        System.out.println("DELETE CLICKED");
     doctorList.deleteByProblem(deleteTextField.getText());
	 reloadAllDoctors();
    }
	else if (showAllButton.getText().equals(command)) {
       
        System.out.println("SHOW ALL CLICKED");
      textArea.setText( doctorList.getAll() );
	  reloadAllDoctors();
    } 
	else if (clearButton.getText().equals(command)) {
       
        System.out.println("CLEAR CLICKED");
       textArea.setText("");
	   
    }
	else if(saveButton.getText().equals(command)){
		System.out.println("SAVE CLICKED");
			FFileIO.saveDoctorsInFile(doctorList);
		}
		else if(backButton.getText().equals(command)){
		System.exit(0);}
		
	
 } 
 	public void reloadAllDoctors(){
		textArea.setText(doctorList.getAll());
	}
}
		
		
		
		
	
	
