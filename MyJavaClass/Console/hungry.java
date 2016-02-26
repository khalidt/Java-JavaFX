/*
 * hungry.java
 * Demonstraates constructors and function call syntax.
 * Author:  Alice Fischer
 * Created on August 18, 2009
 */
import java.util.*;

public class Hungry {
    int level = 0;
    final int full = 17;
    
   // -------------------------------------------------------------
    public static void main (String args[]) {
		Hungry H = new Hungry();
		H.go();
	}
	
   // -------------------------------------------------------------
   public void go () {
        Scanner sc = new Scanner(System.in);
        String s;
        int n;
        System.out.println( "\nHey, Joe, I'm hungry.  Feed me some jellybeans.\n"
        				   +"How many do you have?");
        for(;;){
            n = sc.nextInt();
            level += n;
            if (level >= full) break;
            System.out.println ( "    Thank you for "+ n +" jellybeans.\n"+
                            "I'm still hungry.  How many more do you have?");
        }
        System.out.println("    Thank you.  Come again tomorrow!\n");
    }
}
