/**
 * 
 */

/**
 * @author khalid
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class hash {

	
	public static String generateSHA1(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException  {
		return hashString(message, "SHA-1");
	}
	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
					.substring(1));
		}		
		return stringBuffer.toString();
	}
	private static String hashString(String message, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException{
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
			return convertByteArrayToHexString(hashedBytes);		
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
			//String inputString = args[0];
			String s1[]= {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2",
					      "3","4","5","6","7","8","9","0","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
					      "T","U","V","W","X","Y","Z"};
			
			String word1,word2,hash1,hash2;
			Random range = new Random();
			char a,b;
			int count =0;
			Scanner sc = new Scanner (System.in);
			System.out.println("========================================================\n"+
			"| Program: finding SHA-1 with the same first 4 diget.\t|\n"+
			"| Author:[Khalid]\t\t\t\t\t|\n"+
			"| Version: 1.0\t\t\t\t\t\t|\n"+
			"| Date:May 20 2015\t\t\t\t\t|\n"+
			"|========================================================\n"+
			"Finding Hashes Pleas waite ...\n\n");
			String quit="y";
			
			for (;;) {
				word1 = s1[range.nextInt(s1.length)]+ s1[range.nextInt(s1.length)]+ s1[range.nextInt(s1.length)]+ s1[range.nextInt(s1.length)];
				word2 = s1[range.nextInt(s1.length)]+ s1[range.nextInt(s1.length)]+ s1[range.nextInt(s1.length)]+ s1[range.nextInt(s1.length)];
				hash1 = generateSHA1(word1);
				hash2 = generateSHA1(word2);
				for (int i = 0; i < 5; i++) {// i<4
					a = hash1.charAt(i);
					b = hash2.charAt(i);
					//System.out.println(a + "\t" + b);
					if (a == b && !word1.equals(word2)) {
						if (i == 4) {// i==3
							System.out.println("First String: [" + word1 + "]\n\t"+ hash1 + "\n\nSecond String: [" + word2 + "]\n\t"+ hash2);
							System.out.print("\ndo you want fined another hash (Y/N)?");
							quit = sc.next().toLowerCase();
							count++;
						}
						continue;
					}
					 else break;
				}
				if(!quit.equals("y"))
					break;
			}
			sc.close();
			System.out.println("\nThe script fonde <"+count+"> Hashes.\nBye...");
		}
}
