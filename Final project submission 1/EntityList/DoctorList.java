package EntityList;
import Entity.*;
import Interfaces.*;

public class DoctorList implements IDoctorList{
    private Doctor doctors[];

    public DoctorList() {
        doctors = new Doctor[50];
    }

    public DoctorList(int size) {
        doctors = new Doctor[size];
    }


    public void insert(Doctor d) {
        boolean flag = false;
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i] == null) {
                doctors[i] = d;
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("Doctor Inserted");
        } else {
            System.out.println("Insertion Failed");
        }
    }


    public Doctor getByProblem(String problem) {
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i] != null) {
                if (doctors[i].getProblem().equals(problem)) {
                    return doctors[i];
                }
            }
        }
        return null;
    }

    public void deleteByProblem(String problem) {
        boolean flag = false;
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i] != null) {
                if (doctors[i].getProblem().equals(problem)) {
                    doctors[i] = null;
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }
    }

    public void searchByName(String name) {
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i] != null) {
                if (doctors[i].getDoctorName().equals(name)) {
                    System.out.println("Doctor Found");
                    doctors[i].showDoctorInfo();
                    return;
                }
            }
        }
        System.out.println("-- Doctor Not Found ---");
    }

    public void showAll() {
        System.out.println("-------------Available Doctor-----------");
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i] != null) {
                System.out.println("---------------------");
                doctors[i].showDoctorInfo();
            }
        }
        System.out.println("---------------------");
    }

	public String getAll(){
		String allDoctorInfo = "";
		for(int i=0;i<doctors.length;i++){
			if(doctors[i] != null){ 
			
				allDoctorInfo += "-----------------  \n";
				allDoctorInfo += doctors[i].getFoodInfoAsString();
			
			}
		}
		allDoctorInfo += "----------------------------------------\n";
		return allDoctorInfo;
	}
	
	
	public Doctor[] getAllDoctors(){return doctors;}
	
}