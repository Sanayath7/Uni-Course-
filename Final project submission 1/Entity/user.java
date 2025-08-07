package Entity;
import java.lang.*;

public class user{
	private String name;
	private String password;
	private String gender;
	private String phn;
	private String address;
	
	public user(String name, String password, String gender,String phn,String address){
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.phn = phn;
		this.address = address;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
	public void setPhn(String phn){
		this.phn = phn;
	}
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	public String getGender(){
		return gender;
	}
	public String getPhn(){
		return phn;
	}
	public String getAddress(){
		return address;
	}
}