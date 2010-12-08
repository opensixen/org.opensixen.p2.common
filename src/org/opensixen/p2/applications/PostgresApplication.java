/**
 * 
 */
package org.opensixen.p2.applications;

import java.net.URI;
import java.net.URISyntaxException;

import org.opensixen.os.PlatformProvider;
import org.opensixen.os.ProviderFactory;

/**
 * 
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public class PostgresApplication extends InstallableApplication {

	public final static String IU_POSTGRES = "feature.opensixen.bundle.postgres.feature.group"; //$NON-NLS-1$	
	public final static String PROFILE_POSTGRES = "PostgreSQL";	
	
	private static String CMD_REGISTER = "addService.bat";
	private static String CMD_UNREGISTER = "removeService.bat";
	
	
	/**
	 * 
	 */
	public PostgresApplication() {
		super(IU_POSTGRES, PROFILE_POSTGRES);
	}

	/* (non-Javadoc)
	 * @see org.opensixen.p2.applications.InstallableApplication#afterInstall()
	 */
	@Override
	public void afterInstall() {
		PlatformProvider provider = ProviderFactory.getProvider();
		String cmdReg = getPath() + "/" + CMD_REGISTER;		
		String cmdUnreg = getPath() + "/" + CMD_UNREGISTER;
		try {
			// First try to unregister
			provider.runCommand(cmdUnreg);
			
			// Register database.
			provider.runCommand(cmdReg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
