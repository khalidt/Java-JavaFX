
package gpa;

/**
 * @author khalid
 */
import java.util.*;
import java.io.*;

/** -------------------------------------------------- data member ------------------------------------------- */

public class Calculator {
          private ArrayList <Course> courses;
          private Course c;                          // to save what we get from the array list
          private double  GpaAvg;
          private Scanner in= new Scanner(System.in);// read from keyboard
          private Scanner input;					 // read from file
          private PrintWriter output ;               // print to file
	      private File f;	                    	 // creay a file			
		  
/** ----------------------------------------- constructor function ------------------------------------------ */
/** 1. To allocate memory the array list 
	2. To save the infromation that gets from the file in the array list 
    3. To write what the array list contains back to the file               */

    public Calculator(){                             
         courses= new ArrayList<Course>();

         try{             //Read from file and save info in the Arraylist 
             f = new File("courses.txt");
             if( f.exists()) // if file is exist
			 {
				 
				 if( f.length() == 0)  // if file is empty to prevent throw an exption
				 {
					 System.out.println("Warning: The file is empty");
				 } 
				 else // if the file not empty at least has one line
				 {
					 input = new Scanner(new FileReader("courses.txt"));
					 while(input.hasNextLine()){
						 String line = input.nextLine();
						 String parts[] = line.split(";");
						 
						 courses.add( new Course (parts[0],parts[1]));
					 }//end while
					 input.close();
				 }// end else
				 
			 }// end if existe
			 else System.out.println("Error: the courses.txt file is not exist.\nThe program will create courses.txt file");
			  
         } catch (FileNotFoundException e) {e.printStackTrace();}
       
        try {        // write info from arraylist to the file
                output = new PrintWriter(f);
                for (int i=0; i< courses.size(); i++){
                output.println((Course) courses.get(i));
            }
            output.close();

            }catch (IOException ex){ex.printStackTrace();}
	}


    /* function to let the user enter the course and the GPA
	 public void interCours(){
        String course;
        String degree;

        System.out.print("Enter course name: ");
        course=in.next();
        do{
        System.out.print("Enter GPA(A,B,C,F): ");
        degree= in.nextLine();
        if (degree.equals("A") ||degree.equals("A") ||degree.equals("b")||degree.equals("B")||degree.equals("C")||degree.equals("c")||degree.equals("f")||degree.equals("F")) break;
        String junck=in.nextLine();
        }while (true);
        courses.add(new Course(degree,course));
    } */
	
	/** ------------------------ display Courses And GPA ------------------------- */

    public void displayCourses (){
        String GPA,cname;
		if(courses.size()>0){
			System.out.println("Course name\t\t\t\t GPA");
            System.out.println("-----------\t\t\t\t ---");
            for(int i=0; i<courses.size(); i++ ) {
				Course c= (Course) courses.get(i);
				GPA=c.getGPA();
				cname=c.getCourse();
				System.out.println(cname+"\t\t  "+GPA);
            }
		}
		else {
			System.out.println("There is no Courses to display ");
		}


    }
	/** ------------------------ Calculate AVRAGE GPA ------------------------- */

    public void caluclate(){
        String GPA;
        int Gpacourse=0;
        int GpaTotal=0;


		//--------------------- start using swich in a loop
        for(int i=0; i<courses.size(); i++ ) {
            Course c= (Course) courses.get(i);
            GPA=c.getGPA();
			//switch(GPA.trim())
			switch(GPA.charAt(0))
			{
				case 'A': 
					Gpacourse=4;
					break;
				case 'B':
					Gpacourse=3;
					break;
				case 'C':
					Gpacourse=2;
					break;
			    case 'F':
					Gpacourse=0;
					break;
			}
			GpaTotal+=  Gpacourse;
        }
		//--------------------end using wich in a loop
		
		/* 
		 //note: other way to calculate the GPA Avarage by using if else.. it works too I tried it but I prefer switch. it is more orgnized and eazy to read
		 for(int i=0; i<courses.size(); i++ ) {
		 Course c= (Course) courses.get(i);
		 GPA=c.getGPA();
		 if (GPA.equals("A")) Gpacourse=4;
		 else if (GPA.equals("B")) Gpacourse=3;
		 else if     (GPA.equals("C")) Gpacourse=2;
		 else if     (GPA.equals("F")) Gpacourse=0;
		 GpaTotal+=  Gpacourse;
		 }*/

        if (courses.size()>0){
			GpaAvg=(double)GpaTotal /(double)courses.size();
            System.out.printf("\n** The Average GPA is %.2f **\n ",GpaAvg);
		}else{
			GpaAvg=0.0; // to prevent NAN result
			System.out.printf("** Avarage of the GPA is %.2f**\n ",GpaAvg);
		} 
    }

	/** -------------------- Main Function creat new object and call dispaly and calulate function --------------- */

    public static void main(String[] args ){

        try {
            Calculator objCourse = new  Calculator();
            objCourse.displayCourses();
            objCourse.caluclate();
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
            ex.printStackTrace();
            System.exit(0);
        }

    }
}

