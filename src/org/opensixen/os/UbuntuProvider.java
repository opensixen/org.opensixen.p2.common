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
public class UbuntuProvider extends DebianProvider  {

	

	private static final String PROVIDER_ID = "Ubuntu";
	
	/* (non-Javadoc)
	 * @see org.opensixen.os.PatformDetailsProvider#matchPlatform()
	 */
	@Override
	protected boolean matchLinuxFlavor() {
		if (getDistributor_ID().equals(PROVIDER_ID))	{
			return true;
		}
		return false;
	}

}
