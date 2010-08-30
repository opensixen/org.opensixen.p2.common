/**
 * 
 */
package org.opensixen.os;

/**
 * 
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public interface PlatformProvider {

	/** pgsql excutable */
	public final static int PGSQL = 0;
	
	/** pgdump excutable */
	public final static int PGDUMP = 1;
	
	
	
	/**
	 * Get filesystem path to the
	 * requested exec
	 * 
	 * @param exec see PlatformDetails.*
	 * @return 
	 */
	public String getExecPath(int exec);
	
	
	/**
	 * Return true if the system is unix based
	 * (linux, solaris, macos...)
	 * @return
	 */
	public boolean isUnix();
	
	/**
	 * Return true if the provider 
	 * match this O.S.
	 * 
	 * This method must check some fingerprints in the
	 * O.S. to check if match.
	 * 
	 * @return true if the O.S. match
	 */
	public boolean matchPlatform();
	
	/**
	 * Create the user for work with Opensixen
	 * @return
	 */
	public boolean createDBUser();
	
	
	/**
	 * Create the database
	 * @return
	 */
	public boolean createDB();
	
	
	
}
