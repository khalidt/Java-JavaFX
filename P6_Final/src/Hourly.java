/*
 * @author Khalid
 */
import java.util.Scanner;

public class Hourly extends Employee {
	private double workHours; 

	public Hourly ( String login, double payRate, String Name, String password ){
		super(login, payRate, Name, password);
	}
	//to set a work hours
	public void setWorkHours( double wh ) {
		this.workHours = wh;
	}
	// get pay
	public double getPay() { 
		return baseSalary * workHours; 
	}
}