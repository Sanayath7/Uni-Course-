package EntityList;
import Entity.*;
import java.lang.*;
import java.io.*;
import java.util.Scanner;
import File.*;
import Interfaces.*;
 public class users implements IUsers{
	user userList[] = new user[100];
	static int userCount = 0;
	
	public users(){
		//reading the file and creating user objects to add them to the userList array
		try{
			File file = new File("File/userList.txt"); //location must be the path where the file is saved
			Scanner sc = new Scanner(file);
				while(sc.hasNext()){
					String line1 = sc.nextLine();  //name
					String line2 = sc.nextLine();  //password
					String line3 = sc.nextLine();  //gender
					String line4 = sc.nextLine();  //phone no.
					String line5 = sc.nextLine();  //address
					String line6 = sc.nextLine();  //extra newline for space between two objects
					
					//printing them to cmd just to see if they are getting added to the list or not
				 	
					System.out.println(line1);
					System.out.println(line2);
					System.out.println(line3);
					System.out.println(line4);
					System.out.println(line5);
					
					
					user u = new user(line1,line2,line3,line4,line5);
					userList[userCount] = u;
					userCount++;
					System.out.println(userCount+"////////////////////////////");
				}
			sc.close();   //closing the file 		
		}catch(Exception ex){
			//System.out.println(ex);
			System.out.println("File not found.");
			return;
		}
	}
	
	public int userExists(String name){
		int index = -1; //setting index value -1
		for(int i=0;i<userCount;i++){
			if(userList[i].getName().equals(name)){
				index = i; //if any user exists with this name then, he/she must in one of the indexes of userList array
				break;
			}
		}
		return index; //if user exists then the index that the user is in, that index will be returned
		              //otherwise -1 will be returned
	}
	
	public user checkCredentials(int index, String pass){
		if((userList[index].getPassword().equals(pass))){
			return userList[index]; //if the user exists, we will check the password, if matches return the user object which resides in userList array in given index
		}else{return null;}         //otherwise return null, meaning password incorrect
	}
	
	public user getUser(int index){
		return userList[index];
	}
	
	public void addUser(user u){
		//adding the user to the userList array
		userList[userCount] = u;
		userCount++;
		
		//also adding them in file
		String userDetails=u.getName() + "\n" + u.getPassword() + "\n"+
						   u.getGender() + "\n"+ u.getPhn() + "\n" +
						   u.getAddress() + "\n" + "\n";
		try{
			FileWriter fw = new FileWriter("File/userList.txt",true);
			fw.write(userDetails);
			fw.close();
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void deleteUser(String name){
		//deleting them from array
		user u = null;
		for(int i = 0; i<userCount; i++){
			if(userList[i].getName().equals(name)){
				u = userList[i];
				for(int j = i; j< userCount; j++){
					userList[j] = userList[j+1];
				}
				userCount--;
				userList[userCount] = null;
				break;
			}
		}
		
		//also deleting from file
		String userDetails=u.getName() + "\n" + u.getPassword() + "\n"+
						   u.getGender() + "\n"+ u.getPhn() + "\n" +
						   u.getAddress() + "\n";
		
		try{
			String filepath = "File/userList.txt";
			File originalFile = new File(filepath);
				
			String newDetails = "";				
			Scanner sc = new Scanner(originalFile);
			while(sc.hasNext()){
				String readUser = sc.nextLine() + "\n";
					   readUser += sc.nextLine() + "\n";
				       readUser += sc.nextLine() + "\n";
				       readUser += sc.nextLine() + "\n";
				       readUser += sc.nextLine() +"\n";
				       readUser += sc.nextLine();
				
				System.out.println(readUser);
				System.out.println(userDetails);
				
				if(readUser.equals(userDetails)){
					System.out.println("Equal, so deleting");
					continue;
				}
				System.out.println("Writing");
				newDetails += readUser + "\n";
			} sc.close(); 
			FileWriter fw = new FileWriter(filepath);
			fw.write(newDetails);
			fw.close();
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void updateUser(user oldUser, user updatedUser){
		String oldUserDetails = oldUser.getName() + "\n" + 
								oldUser.getPassword() + "\n"+
								oldUser.getGender() + "\n"+ 
								oldUser.getPhn() + "\n" +
								oldUser.getAddress() + "\n";
								
		String updatedDetails = updatedUser.getName() + "\n" + 
								updatedUser.getPassword() + "\n"+
								updatedUser.getGender() + "\n"+ 
								updatedUser.getPhn() + "\n" +
								updatedUser.getAddress() + "\n";
		//updating in file
		try{
			String filepath = "File/userList.txt";
			File originalFile = new File(filepath);
			
			String newDetails = "";				
			Scanner sc = new Scanner(originalFile);
			while(sc.hasNext()){
				String readUser = sc.nextLine() + "\n";
				readUser += sc.nextLine() + "\n";
				readUser += sc.nextLine() + "\n";
				readUser += sc.nextLine() + "\n";
				readUser += sc.nextLine() +"\n";
				readUser += sc.nextLine();
				
				System.out.println(readUser);
				System.out.println(oldUserDetails);
				
				if(readUser.equals(oldUserDetails)){
					System.out.println("Updated");
					newDetails += updatedDetails + "\n";
				}else{
					System.out.println("Writing");
					newDetails += readUser + "\n";
				}
			} sc.close(); 
			FileWriter fw = new FileWriter(filepath);
			fw.write(newDetails);
			fw.close();
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}