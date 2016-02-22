/*
 * @author Khalid
 */

import java.io.Serializable;
import java.util.*;


abstract public class Employee implements Serializable {
	// no space for loginName and password ..
	protected String loginName; 
	protected double baseSalary;
	protected String employeeName;
	protected final int employeeId;
	protected Date now; 
	protected static int nextId = 0;
	protected String password; 
	// Constructor to initialize data members
	public Employee( String loginName, double baseSalary, String employeeName, String  password) {
		this.loginName = loginName;
		this.baseSalary = baseSalary;
		this.employeeName = employeeName;
		this.now = new Date();
		this.employeeId = nextId;
		this.password = password;
		nextId++;
	}
		
	// for set emp name
	public void setEmployeeName( String temp ) { 
		this.employeeName = temp;
		}
	public static void setNextId( int x ) {
		nextId = x; nextId++; 
		}
	public void setSalary( double salary) {
		this.baseSalary = salary;
	}
	// get login name
	public String getLoginName() {
		return loginName; }
	// get emp name
	public String getEmployeeName() {
		return employeeName; }
	// get emp id
	public int getEmployeeId() { 
		return employeeId; } 
	//get BaseSalary
	public double getBaseSalary() {
		return baseSalary; }
	public String getPassword() { 
		return password; } // used in Payro.dologin() method
	
	// print out the data
	public String toString() {
		return String.format("%05d\t%-8s\t%-8s\t%-15d      \t%13.2f\t%-15s",
				employeeId, loginName, password, now.getTime(), baseSalary,
				employeeName);
	}

	// abstract fun
	public abstract double getPay();
}