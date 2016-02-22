/**
 * 
 */
package testmid;
import java.util.*;
import java.io.*;


/**
 * @author khalid
 */
public class Truser {

	/**
	 * @param args
	 */
	static ArrayList<Gem> Oob = new ArrayList<>();
	static ArrayList<Gem> Pob = new ArrayList<>();
	static File fil =new File("mi.txt");
	static double Ototal,Ptotal;

	public static void readFile() {
		Scanner read;
		try {
			read = new Scanner(fil);

			String getname;

			double getval;
			while(read.hasNext()){
				getname=read.next();
				getval=read.nextDouble();
				if(getname.equals("opal"))
				{
					Gem temp = new Gem(getname,getval);
					Oob.add(temp);
					Oob.get(0);
				}
				else if (getname.equals("pearl")){
					Gem temp =new Gem(getname,getval);
					Pob.add(temp);
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}

		
		
	}

	public static void GemsPrint(ArrayList<Gem> x){
		
		for(int i=0;i<x.size();i++)
		{
			if(x.get(i).getTname().equals("opal")){
				Ototal+=x.get(i).getcost();
				System.out.println(x.get(i));
			}
			else if(x.get(i).getTname().equals("pearl")){
				Ptotal+=x.get(i).getcost();
				System.out.println(x.get(i));
			}

		}
		
	}
	public static void eval(){
		GemsPrint(Oob);
		System.out.printf("opal total cost is: %.2f\n\n",Math.ceil(Ototal));
		GemsPrint(Pob);
		System.out.printf("preal total cost is: %.2f\n\n",Math.ceil(Ptotal));
		if(Ototal>Ptotal)
			System.out.println("the more worth is Opal.");
		else if(Ptotal>Ototal)
			System.out.println("the more worth is Pearl.");
			else System.out.println("the opal and Pearl are the same value!");
		
	}
	
	
	
	
	
	public static void main(String[] args)  {
			readFile();
			eval();
		

	}

}

