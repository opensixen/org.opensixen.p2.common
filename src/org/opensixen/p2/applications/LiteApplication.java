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
public class LiteApplication extends InstallableApplication {

	public final static String IU_LITE = "OpensixenLite"; //$NON-NLS-1$  
	public final static String URL_LITE="http://dev.opensixen.org/products/lite/"; //$NON-NLS-1$
	public final static String PROFILE_LITE = "OpensixenLite";
	
	/**
	 * @param iu
	 */
	public LiteApplication() {
		super(IU_LITE, PROFILE_LITE);
	}

	/* (non-Javadoc)
	 * @see org.opensixen.p2.applications.InstallableApplication#getLocation()
	 */
	@Override
	public URI getLocation() {
		// TODO Auto-generated method stub
		try {
			return new URI(URL_LITE);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	
	
}
