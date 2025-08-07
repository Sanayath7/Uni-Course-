package Interfaces;
import Entity.*;

import java.util.Scanner;


public interface IDoctorList{
	void insert(Doctor d);
	Doctor getByProblem(String problem);
	void deleteByProblem(String problem);
	void searchByName(String name);
	 void showAll();
	
	
}