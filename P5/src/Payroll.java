
/**
 * @author Khalid
 */

import java.io.*;
import java.util.*;



public class Payroll{
	private static String menu = "Payroll Menu\n\t1. Log In\n" +
								 "\t2. Enter employees\n" +
								 "\t3. List Employees\n" + 
								 "\t4. Change Employee Data\n" +
								 "\t5. Terminate an employee\n" +
								 "\t6. Pay employees\n" +
								 "\t0. Exit System";
	private Scanner sc = new Scanner(System.in);
	private PrintWriter pw_tofile;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private FileInputStream fis;
	private ObjectInputStream ois;
	// store current user
	private Employee current_user;
	private int current_id = -1;
	private boolean do_terminated = false;// this if there is terminated emp to prompt after Exit.
	private ArrayList<Employee> arr = new ArrayList<Employee>();
	private ArrayList<Employee> arr_fired = new ArrayList<Employee>();// store fired employees
	// temp for login, name, salary
	private String templogin; 
	private String tempName; 
	private double tempSalary; 
	
	
	

	@SuppressWarnings("unchecked")
	public Payroll() throws IOException {
		
		try {
			pw_tofile = new PrintWriter( new File( "payroll.txt" ) );
			// output to an object file
			fis = new FileInputStream( "employee.txt" );
			ois = new ObjectInputStream( fis );
			
			arr = (ArrayList<Employee>) ois.readObject(); // read object from  file
			Employee.setNextId( arr.get( arr.size() - 1 ).getEmployeeId() ); // set nextID 
			
			
		}catch(FileNotFoundException ex) {
			// get the information of boss
			System.out.println("There is no File existing for the Employee! Please create new one.");
			System.out.println("Inter the information of the boss first.\n");
			
			System.out.println("1.Please Enter login name of boss:");
			templogin = sc.next(); 
			System.out.println("2.Please Enter salary of boss:");
			tempSalary = sc.nextDouble(); 
			sc.nextLine(); 
			System.out.println("3.Please Enter full name of boss:");
			tempName = sc.nextLine(); 
			
			arr.add( new Salaried( templogin, tempSalary, tempName ) ); 
			System.out.println("The boss is created...");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
	}
	
	// the Menu
	public void doMenu() throws FileNotFoundException, IOException {
		int input;
		boolean status = true;
		try {
			for(;status;) {
				// show the menu
				System.out.println( menu );
				System.out.print("Please Enter a number:");
				input = sc.nextInt(); 
				switch( input ) {// compare the input value
					case 0: status = false; break;
					case 1: dologin(); break;
					case 2: newEmployee(); break;
					case 3: listEmployees(); break;
					case 4: changeEmployeeData(); break;
					case 5: terminateAnEmployee(); break;
					case 6: payEmployees(); break;
					default : System .out.println("You Typed wrong Value. Please Try again! ");
				}
			}
			
			fos = new FileOutputStream( "employee.txt" ); // FileNotFoundException
			oos = new ObjectOutputStream( fos );// IOException

			oos.writeObject( arr );// IOException
			
		}finally {
			// if there is employee fired show them or print no one fired
			if (do_terminated){
			System.out.println( "Note: The list of employees who are fired in this transaction:" );
			for( Employee emp: arr_fired )
				System.out.println( emp.toString() );
			
			sc.close();
			pw_tofile.close();
			fos.close(); // IOException
			}
			else
				System.out.println("Note: No one Fierd in this transaction.");
		}
			
		System.out.println("Save all data to the file is completed.");
	}
	
	
	// option 1 login.
	private void dologin() {
		boolean be = false; // check if loginName is existed
		System.out.println("Please enter the username:");
		String login = sc.next();
		for(Employee temp: arr) {
			if( login.equals( temp.getLoginName() ) ) {
				current_user = temp;
				current_id = temp.getEmployeeId();
				System.out.println("You are logged in: ["+temp.getEmployeeName()+"]");
				be = true;
			}
		}// if the user doesn't exist 
		if( !be ) System.out.println("Login Faild! username dosent exist.");
	}
	
	// option 2 (Admin option)
	private void newEmployee(){
		int count=0;
		int choose;
		// check if the current user is Admin or not 
		if( current_id == 0) {
			for (;;){
			System.out.println( "Which kind of employee do you want to creat: \n" +
					"1. Salaried\n" +
					"2. Hourly");
			 choose = sc.nextInt();
				if ( choose != 1 && choose !=2)
					System.out.println( "You should Choose Only Two options '1' or '2'." );
				else 
					break;
			}
			// get information for new employee 
			sc.nextLine();
			System.out.println("Enter full name of employee:");
			String fullName = sc.nextLine(); 
			System.out.println("Eenter login name of employee:");
			String login = sc.next(); 

			//check if login name is used by another employee  
			while(true) { 

				for( Employee e: arr )
					if( login.equals( e.getLoginName() ) )
						count++;//exist = true;
				if(count==0) 
					break; 
				if (count!=0){
					System.out.println( "The login name is used by another Employee!\n" +
							"Please Choose another one:" );
					login = sc.next(); 
					count=0;
				}
			}
			
			if( choose == 1 ) {	// Salaried employee option
				System.out.println("Enter salary of employee:");
				double salary = sc.nextDouble(); 
				Employee newEmp = new Salaried( login, salary, fullName);
				arr.add( newEmp );
				System.out.println("New Employee added ...");
			}else if( choose == 2 ){ // Hourly employee option
				System.out.println("Please enter pay rate:");
				double salary = sc.nextDouble(); 
				Employee newEmp = new Hourly( login, salary, fullName);
				arr.add( newEmp );
				System.out.println("New Employee added ...");
			}
		}
		else // if the current user is not the boss print msg
			System.out.println("You Are not Allowed to Enter new Employee !");
			
	}

	
	
	
	// option 3  (Admin option)
	public void listEmployees() {
		if( current_id == 0) //check if the current user is Admin or not 
			for( Employee e: arr ) 
				System.out.println( e.toString() );
		else 
			System.out.println("Your informations are :\n"+ current_user.toString() );
	}
	
	//Option 4 (Admin option)
	public void changeEmployeeData() {
		// check if the current user is Admin or not 
		if( current_id == 0 ) {
			int pointer = 0;
			boolean status = false;
			System.out.println("Enter the Employee ID:");
			int tempId = sc.nextInt();
			while( !status ) {
				for( Employee e: arr ) {// find the Emp ID 
					if( tempId == e.getEmployeeId() ) {
						pointer = arr.indexOf( e );
						status = true;
						break;
					}
				}
				if( !status ) {// if the ID is wrong 
					System.out.println( "Invalid ID! Please try again." );
					System.out.println("Enter the correct Employee ID:");
					tempId = sc.nextInt();
				}
			}
			// if the state of Emp ID is found (True) make a change  
			if( status ) {
				
				System.out.println( "Do You Want To Change Employee's Name (Y/N)?" );
				String temp_str = sc.next().toLowerCase();// it takes LowerCase of character
			
				if( temp_str.equals("y") ) {
					sc.nextLine(); 
					System.out.println("Enter a New Name:");
					temp_str = sc.nextLine();// store new name
					arr.get( pointer ).setEmployeeName( temp_str );
					System.out.println("The Employee's Name is Updated..");
				}
				
				System.out.println( "Do You Want To Change Employee's Salary (Y/N)?" );
				temp_str = sc.next().toLowerCase();
				if( temp_str.equals("y") ) {
					System.out.println("Enter a New Salary:");
					arr.get( pointer ).setSalary( sc.nextDouble() );// store new salary 
					System.out.println("The Employee's Salary is Updated..");
				}
			}
		}else // if the current user is not the boss show the Msg
			System.out.println("You Are not Allowed to Change Employees data !");
			
		
	}
	
	// delete an employee 
	public void terminateAnEmployee(){
		//check if the current user is Admin or not 
		if( current_id == 0 ) {
			boolean status = true;
			System.out.println("Enter the Employee ID:");
			int number_id = sc.nextInt();
			
			while( status ) {
				// find the Employee ID 
				for( Employee e: arr ) {
					// delete a fired employee and then add it into arr_fired array
					if( e.getEmployeeId() == number_id ) {
						arr_fired.add( e );
						arr.remove( e );
						status = false;
						do_terminated = true;
						System.out.println("The employee is Deleted...");
						break;
					}
				}
				// if number is NOT existent
				if( status ) {
					System.out.println( "Invalid ID! Please try again:" );
					System.out.println("Enter the correct Employee ID:");
					number_id = sc.nextInt();
				}
			}
		}else {// to delete the current user
			arr_fired.add( current_user );
			arr.remove( current_user );
			System.out.println( "The current user has been fired" );
			do_terminated = true;
		}
		
	}
	
	
	// 
	public void payEmployees() {
		
		
		if( current_id == 0 ) {
			
//			try {
//				pw_tofile = new PrintWriter( new File( "payroll.txt" ) );
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//				System.out.println(e1.getMessage());
//			}
			
//			double Pay;
//			int EmployeeID;
//			String EmployeeName;
			System.out.println("*****Payroll Report*****");
			for( Employee e: arr ) {
				System.out.printf( "%13.2f\t%05d\t%s\n", e.getPay(), e.getEmployeeId(), e.getEmployeeName() ); // output to console
//				Pay=e.getPay();
//				EmployeeID=e.getEmployeeId();
//				EmployeeName=e.getEmployeeName();
				//pw_tofile.printf("%13.2f\t%05d\t%s\n", Pay,EmployeeID,EmployeeName);
				pw_tofile.printf( "%13.2f\t%05d\t%s\n", e.getPay(), e.getEmployeeId(), e.getEmployeeName() ); // output to a txt file
			}
		}else System.out.println("You Are not Allowed tothe authority.");
		
	}
	

}
