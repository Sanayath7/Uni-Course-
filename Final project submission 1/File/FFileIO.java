package File;
import java.io.*;
import java.util.Scanner;
import Entity.*;
import EntityList.*;


public class FFileIO{
	public static void loadDoctorsFromFile(DoctorList doctorList){
		try{
			Scanner sc = new Scanner(new File("File/doctors.txt"));
			while(sc.hasNextLine()){
				String data[] = sc.nextLine().split(";");
				Doctor d = new Doctor(data[0],data[1],data[2],Integer.parseInt(data[3]));
				doctorList.insert(d);
			}
			sc.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void writeDoctorInFile(Doctor d){
		try{
			FileWriter writer = new FileWriter(new File("File/doctors.txt"),true);
			String data = d.getProblem()+";"+d.getDoctorName()+";"+
						  d.getDoctorDegree()+";"+d.getVisitingCharge()+"\n";
			writer.write(data);
			writer.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void updateDoctorsInFile(DoctorList doctorList){
		try{
			FileWriter writer = new FileWriter(new File("File/doctors.txt"));
			Doctor doctors[] = doctorList.getAllDoctors();
			
			String allData="";
			
			for(int i=0;i<doctors.length;i++){
				if(doctors[i]!=null){
					String data = doctors[i].getProblem()+";"+doctors[i].getDoctorName()+";"+
						  doctors[i].getDoctorDegree()+";"+doctors[i].getVisitingCharge()+"\n";
					
					allData+=data;
				}
			}
			writer.write(allData);
			writer.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	
	public static void saveDoctorsInFile(DoctorList doctorList){
		try {
		FileWriter writer = new FileWriter(new File("File/doctors.txt"));
			Doctor doctors [] = doctorList.getAllDoctors();
			
			String allData="";
			for(int i=0;i<doctors.length;i++){
				if(doctors[i]!=null){
					String data = doctors[i].getProblem()+";"+doctors[i].getDoctorName()+";"+
						  doctors[i].getDoctorDegree()+";"+doctors[i].getVisitingCharge()+"\n";
					
					allData+=data;
				}
			}
			writer.write(allData);
			writer.close();
		} catch (IOException e) {
			System.out.println("Cannot Write in File");
		}
	}
}