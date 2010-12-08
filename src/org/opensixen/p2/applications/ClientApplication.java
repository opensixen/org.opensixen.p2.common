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
public class ClientApplication extends InstallableApplication {
	
	public final static String IU_CLIENT = "OpensixenClient"; //$NON-NLS-1$
	public final static String URL_CLIENT="http://dev.opensixen.org/products/client/"; //$NON-NLS-1$
	public final static String PROFILE_CLIENT = "OpensixenClient";
	
	/**
	 * @param iu
	 */
	public ClientApplication() {
		super(IU_CLIENT, PROFILE_CLIENT);
	}
	
	/* (non-Javadoc)
	 * @see org.opensixen.p2.applications.InstallableApplication#getLocation()
	 */
	@Override
	public URI getLocation() {
		// TODO Auto-generated method stub
		try {
			return new URI(URL_CLIENT);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	
	
	
}
