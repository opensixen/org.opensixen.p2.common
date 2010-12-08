/**
 * 
 */
package org.opensixen.p2.applications;

import java.util.ArrayList;

/**
 * 
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public class InstallJob {

	private ArrayList<InstallableApplication> installableApplications;
	

	private static InstallJob instance;
	
	public static InstallJob getInstance()	{
		if (instance == null)		{
			instance = new InstallJob();
		}
		return instance;
	}
	
	/**
	 * Default Construnctor
	 */
	private InstallJob()	{
		installableApplications = new ArrayList<InstallableApplication>();
	}

	/**
	 * @return the installableApplications
	 */
	public ArrayList<InstallableApplication> getInstallableApplications() {
		return installableApplications;
	}

	/**
	 * @param installableApplications the installableApplications to set
	 */
	public void setInstallableApplications(ArrayList<InstallableApplication> installableApplications) {
		this.installableApplications = installableApplications;
	}
		
}
