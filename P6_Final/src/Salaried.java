/*
 * @author Khalid 
 */


public class Salaried extends Employee {

	//constructor with 4 parameters
	public Salaried( String login, double Salary, String Name, String password ) {
		super(login, Salary, Name, password);
	}
	//  get pay access 
	public double getPay() { return baseSalary / 24; }
}