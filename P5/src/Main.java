
/**
 * @author Khalid
 */

import java.io.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("***\n* Khalid P5\n***");
		
		// catch exception if it happens
		try {
			Payroll myPay = new Payroll(); 
			myPay.doMenu();
		}catch( IOException ex) {
			System.out.println("Exception happen");
			ex.printStackTrace();
			System.exit(0);}
	}
}
