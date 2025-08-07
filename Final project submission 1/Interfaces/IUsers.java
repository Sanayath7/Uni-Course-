package Interfaces;
import java.lang.*;
import EntityList.*;
import Entity.*;
import File.*;
public interface IUsers{
	void addUser(user u);
	user getUser(int index);
	void deleteUser(String name);
	public void updateUser(user oldUser, user updatedUser);
}