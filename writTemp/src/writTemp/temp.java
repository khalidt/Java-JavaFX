/**
 * 
 */
package writTemp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author khalid
 */
public class temp {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException  {
		
		// create new file
				File fil_temp = new File("temp.txt");
							
				// write to file we need PrintWriter 
				
				try {// this first way to writ Exception
					
					 PrintWriter write = new PrintWriter(fil_temp);
					 write.println("khalid T");
					 write.println(27);
					 write.close();// always you should close PrintWriter
					 System.out.println("writting is done..");
				
				} catch (FileNotFoundException e) { // you should catch the type of Exception and give it to e 
					System.out.println("file not Found!");
					
				}
				
				
				// Read from the file *** this may throw Exception look at name of main() this second way to writ Exception
				Scanner read = new Scanner(fil_temp);

				String name= new String(); // we could use this:      String name=""; 
				String Sval=""; // they are the same. Sval is  String value 
				
				while (read.hasNext()) // this function is to read one line per time 
				{
					name= read.nextLine(); // put all the first one line in name as a String.
					Sval=read.next(); // put the first word in the second line (not all second line) in Sval as a String.
				}
				
				Double val = Double.parseDouble(Sval); // convert from String to Double
			//	int ial = Integer.parseInt(Sval); // convert from String to int
				
				System.out.println(name+"\n"+val);
				read.close();// close the Scanner
		
	}
}
