import java.util.Scanner;           // A single class from the util package.

public class Mph {
    private double speed;           // computed
    private double miles, hours;    // input variables
    
    // --------------------------------------------------------------------
    public static void main(String[] args) {
        Mph mileage = new Mph();    // instantiate.
        mileage.compute();          // execute.
    }
    
    // --------------------------------------------------------------------
    // *Given miles and hours, computes the miles per hour.
    public void compute(){
		Scanner sc = new Scanner(System.in);

        System.out.print("\nPlease enter miles driven: ");
        miles = sc.nextInt();
        System.out.println("Enter hours of travel: ");
        hours = sc.nextDouble();
        
        speed = miles / hours;
        System.out.println("You travelled "+miles +" miles in " +hours +" hours.");
        System.out.printf("You were moving %.2f miles per hour.\n\n", speed);
	}
    
}
