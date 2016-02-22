

/**
 * @author khalid
 */

import java.net.*;
import java.io.*;

public class ip {
	public static void main(String[] args) throws IOException {
		String hostname="";

		try {
			InetAddress ipaddress = InetAddress.getByName(hostname);
			System.out.println("IP address: " + ipaddress.getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("Could not find IP address for: " + hostname);
		}
		

		// print IP Address of your local machine
		System.out.println("\n\nprint IP Address of your local machine");
		System.out.println(InetAddress.getByName("localhost"));
		
        // print the IP Address of your machine (inside your local network)
		System.out.println("print the IP Address of your machine (inside your local network)");
        System.out.println(InetAddress.getLocalHost().getHostAddress());

        // print the IP Address of a web site
        System.out.println("print the IP Address of a web site");
        System.out.println(InetAddress.getByName("www.yahoo.com"));

        // print all the IP Addresses that are assigned to a certain domain
        System.out.println("print all the IP Addresses that are assigned to a certain domain[www.google.com]");
        InetAddress[] inetAddresses = InetAddress.getAllByName("www.google.com");

        for (InetAddress ipAddress : inetAddresses) {
            System.out.println(ipAddress);
        }
	}
}
