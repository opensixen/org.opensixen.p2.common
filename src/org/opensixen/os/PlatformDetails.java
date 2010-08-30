/**
 * 
 */
package org.opensixen.os;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * 
 * @author Eloy Gomez Indeos Consultoria http://www.indeos.es
 * 
 */
public class PlatformDetails {

	
	private static  PlatformProvider provider;

	private static String hostname;

	private static byte[] IPAddrBytes;

	private static String IP;
	

	/**
	 * Static constructor
	 */
	static {
		try {
			InetAddress addr = InetAddress.getLocalHost();

			// Get IP Address
			IPAddrBytes = addr.getAddress();
			IP = addr.getHostAddress();
			
			// Get hostname
			hostname = addr.getCanonicalHostName();

		} catch (UnknownHostException e) {
		}
		
		provider = ProviderFactory.getProvider(); 
		
	}


	/**
	 * @return the hostname
	 */
	public static String getHostname() {
		return hostname;
	}


	/**
	 * @return the iPAddrBytes
	 */
	public static byte[] getIPAddrBytes() {
		return IPAddrBytes;
	}


	/**
	 * @return the iP
	 */
	public static String getIP() {
		return IP;
	}
	
	
	
}
