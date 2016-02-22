
/**
 * @author Khalid
 */


@SuppressWarnings("serial")
public class Salaried extends Employee {

	public Salaried( String login, double Salary, String Name ) {
		// initialize the data members
		super(login, Salary, Name);
	}

	public double getPay() { return baseSalary / 24; }
}