package GUI;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import EntityList.*;
import File.*;


public class updateProfile extends JFrame implements ActionListener{
	JPanel panel;
	JLabel userLabel, passwordLabel, confirmPassLabel, genderLabel,phoneNumberLabel, addressLabel;
	JTextField userTF, addressTF,phoneNumberTF;
	JPasswordField passwordTF;
	JButton updateBtn,backBtn, clearBtn;
	JRadioButton r1,r2,r3;
	ButtonGroup bg;
	
	Color myColor1, myColor2;
	Font myFont, myFont2;
	
	
	users us;
	user u;
	dashBoard db;
	
	public updateProfile(user u, users us){
		super("Update profile");
		this.setSize(600,500);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.us = us;
		this.u = u;
		
		myColor1 = new Color(191,232,247);
		myColor2 = new Color(236,235,232);
		myFont = new Font("Century",Font.ITALIC, 17);
		myFont2 = new Font("Times New Roman",Font.PLAIN, 16);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(myColor2);
		
		userLabel = new JLabel("User name: "); 
		userLabel.setBounds(50,90,150,30);
		userLabel.setFont(myFont2);
		panel.add(userLabel);
		
		userTF = new JTextField(u.getName());
		userTF.setBounds(200,90,200,30);
		userTF.setFont(myFont2);
		panel.add(userTF);
		
		genderLabel = new JLabel("Gender: ");
		genderLabel.setBounds(50,130,150,30);
		genderLabel.setFont(myFont2);
		panel.add(genderLabel);
		
		phoneNumberLabel = new JLabel("Phone Number: ");
		phoneNumberLabel.setBounds(50,170,150,30);
		phoneNumberLabel.setFont(myFont2);
		
		panel.add(phoneNumberLabel);
		
		phoneNumberTF = new JTextField();
		phoneNumberTF.setBounds(200,170,200,30);
		panel.add(phoneNumberTF);
		
	
		
		
		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(50,210,150,30);
		addressLabel.setFont(myFont2);
		panel.add(addressLabel);
		
		addressTF = new JTextField(u.getAddress());
		addressTF.setBounds(200,210,200,30);
		addressTF.setFont(myFont2);
		panel.add(addressTF);
		
		r1 = new JRadioButton("Male");
		r1.setBounds(200,130,80,30);
		panel.add(r1);
		r2 = new JRadioButton("Female");
		r2.setBounds(280,130,80,30);
		panel.add(r2);
		r3 = new JRadioButton("Other");
		r3.setBounds(360,130,80,30);
		panel.add(r3);
		
		bg = new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		bg.add(r3);
		
		clearBtn = new JButton("Clear all");
		clearBtn.setBounds(250,50,100,25);
		clearBtn.setFont(myFont);
		clearBtn.setBackground(myColor1);
		clearBtn.setForeground(Color.BLACK);
		clearBtn.addActionListener(this);
		clearBtn.setBorder(null);
		panel.add(clearBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(250,250,100,25);
		updateBtn.setFont(myFont);
		updateBtn.setBackground(myColor1);
		updateBtn.setForeground(Color.BLACK);
		updateBtn.addActionListener(this);
		updateBtn.setBorder(null);
		panel.add(updateBtn);
		
		
		backBtn = new JButton("back");
		backBtn.setBounds(450,20,90,30);
		backBtn.addActionListener(this);
		
		panel.add(backBtn);

		this.add(panel);
	}
	


	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();
		if(updateBtn.getText().equals(command)){
			String name ="",address="",gender="",
			            phn="";
			
			name = userTF.getText();
			address = addressTF.getText();
			
			if(r1.isSelected()){ gender = "Male";} 
			else if(r2.isSelected()){ gender = "Female";} 
			else if(r3.isSelected()){ gender = "Other";}
			else{}
				
			phn = phoneNumberTF.getText();
			
			if((!name.isEmpty()) && (!gender.isEmpty()) && (!address.isEmpty()) && (!phn.isEmpty())){
				int dialog = JOptionPane.YES_NO_OPTION;
				int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to update these informations?", "Profile update?", dialog);
				if(result == 0){
					String oldName = u.getName();
					String oldPass = u.getPassword();
					String oldGender = u.getGender();
					String oldPhn = u.getPhn();
		            String oldAddress = u.getAddress();
					user oldUser = new user(oldName,oldPass,oldGender,oldPhn,oldAddress);
					
					u.setName(name);
					u.setGender(gender);
					u.setPhn(phn);
					u.setAddress(address);
					
					us.updateUser(oldUser, u);
					
					dialog = JOptionPane.YES_NO_OPTION;
					result = JOptionPane.showConfirmDialog(this, "Information updated. Do you want to stay on this page?", "Stay on this page?", dialog);
					if(result == 0){
					}else{
						dashBoard db = new dashBoard(u,us);
						db.setVisible(true);
						this.setVisible(false);
					}
				}else{
				}
			}else{
				JOptionPane.showMessageDialog(this, "Can't update, information missing!");
			}
		}else if(backBtn.getText().equals(command)){
			dashBoard db = new dashBoard(u,us);
			db.setVisible(true);
			this.setVisible(false);
		}else if(clearBtn.getText().equals(command)){
			userTF.setText("");
			addressTF.setText("");
		}else{}
	}
	
}