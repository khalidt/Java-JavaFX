
/**
 * @author Khalid
 */


import java.io.Serializable;
import java.util.*;

/*
 * This class has two constructor
 */
abstract public class Employee implements Serializable {
	protected String loginName; // including no spaces
	protected double baseSalary; 
	protected String employeeName; 
	protected Date now; // store time when employee was entered
	protected final int employeeId;
	protected static int nextId = 0;

	public Employee( String loginName, double baseSalary, String employeeName ) {
		// initialize the data members
		this.loginName = loginName;
		this.baseSalary = baseSalary;
		this.employeeName = employeeName;
		this.now = new Date();
		this.employeeId = nextId;
		nextId++;
	}
		

	public void setSalary( double salary) {
		this.baseSalary = salary;
	}
	

	public void setEmployeeName( String str ) { this.employeeName = str; }
	public static void setNextId( int i ) { nextId = i; nextId++; }
	
	public String toString() {
		return String.format( "%05d\t%s\t%15.2f\t%d\t%s" , employeeId, loginName, baseSalary, now.getTime(), employeeName);
	}
	
	public String getLoginName() { return loginName; } // used in Payroll.dologin() method
	public String getEmployeeName() { return employeeName; }
	//public double getBaseSalary() { return baseSalary; }
	//public Date getDate() { return now; }
	public int getEmployeeId() { return employeeId; } // used in Payroll.dologin() method
	
	
	
	//an abstract method
	public abstract double getPay();
}