/**
 * 
 */
package org.opensixen.p2.applications;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public abstract class InstallableApplication {
	
	
	public static final String UPDATESITE = "http://dev.opensixen.org/updates";

	private String path;
	
	private String type;
	
	private String iu;
	
	private URI location;
	
	private String profile;
	
	private boolean installOk; 
	
	protected InstallableApplication(String iu, String profile)	{
		this.iu = iu;		
		this.profile = profile;
		
		try {
			this.location = new URI(UPDATESITE);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the iu
	 */
	public String getIu() {
		return iu;
	}

	/**
	 * @param iu the iu to set
	 */
	public void setIu(String iu) {
		this.iu = iu;
	}

	/**
	 * @return the location
	 */
	public URI getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(URI location) {
		this.location = location;
	}

	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}


	/**
	 * @return the installOk
	 */
	public boolean isInstallOk() {
		return installOk;
	}


	/**
	 * @param installOk the installOk to set
	 */
	public void setInstallOk(boolean installOk) {
		this.installOk = installOk;
	}	
		
	/**
	 * After install process
	 * 
	 * must be extended
	 */
	public void afterInstall()	{
		
	}
	
	
}
