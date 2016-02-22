
/**
 * @author Khalid
 */
import java.util.Scanner;


public class Hourly extends Employee {
	private double workHours = -1; // store the worked hours

	public Hourly ( String login, double payRate, String Name ){
		super(login, payRate, Name);
	}
	// mutator
	public void setWorkHours( ) {
		System.out.println("Enter the numbers of hours worked during this pay:");
		Scanner sc = new Scanner( System.in );
		workHours = sc.nextDouble();
		
	}

	public double getPay() { 
		if( workHours == -1 ) {
			setWorkHours();
			return baseSalary * workHours; 
		}else {
			return baseSalary * workHours; 
		}	
	}
}