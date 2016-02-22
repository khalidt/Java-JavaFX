/**
 * 
 */
package testmid;

/**
 * @author khalid
 */
public class Gem {
	private String Tname;
	private double dim;
	private double cost;
	
	Gem(String x,double y){
		Tname=x;
		dim=y;
		if (Tname.equals("opal"))
			calcOpal();
		else if(Tname.equals("pearl"))
			calcPearl();
	}
	public double calcOpal(){
		cost = 8.33 * Math.pow(dim, 2);
		return cost;
	}
	public double calcPearl(){
		cost = Math.exp(dim / 2) - 1;
		return cost;
	}
	public String getTname(){
		return Tname;
	}
	public double getdim(){
		return dim;
	}
	public double getcost(){
		return cost;
	}
	public String toString(){
		return String.format("name:%s\tValu:%.2f\tcost:%.2f",Tname,dim,cost);
	}

}
