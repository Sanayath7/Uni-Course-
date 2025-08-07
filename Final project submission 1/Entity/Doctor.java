package Entity;

public class Doctor{
	
		private String problem;
		private String doctorName;
		private String doctorDegree;
		private int visitingCharge;
		
		public Doctor(){}
		public Doctor(String problem,String doctorName,String doctorDegree,int visitingCharge){
			this.problem=problem;
			this.doctorName=doctorName;
			this.doctorDegree=doctorDegree;				
			this.visitingCharge=visitingCharge;
		}
		
		public void setDoctorName(String doctorName){this.doctorName=doctorName;}
		public void setProblem(String problem){this.problem=problem;}
		public void setDoctorDegree(String doctorDegree){this.doctorDegree=doctorDegree;}
		public void setVisitingCharge(int visitingCharge){this.visitingCharge=visitingCharge;}
	
		
		public String getDoctorName(){return this.doctorName;}
		public String getProblem(){return this.problem;}
		public String getDoctorDegree(){return this.doctorDegree;}
		public int getVisitingCharge(){return this.visitingCharge;}
		
		public void showDoctorInfo(){
			System.out.println("Problem : "+this.problem);
			System.out.println("Name : "+this.doctorName);
			System.out.println("Degree : "+this.doctorDegree);
			System.out.println("Charges : "+this.visitingCharge);
		}
		
		public String getFoodInfoAsString(){
		
		
		return 
		        "Problem : "+problem+"\n"+
				" Name : "+doctorName+"\n"+
				" Degree : "+doctorDegree+"\n"+
				"Charges : "+visitingCharge+"\n";
				
	}
		public void setCharges(int parseInt) {
			
		}
		
		
	
	
}