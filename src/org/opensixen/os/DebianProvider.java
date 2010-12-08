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
public class DebianProvider extends LinuxProvider {

	
	private static final String PROVIDER_ID = "Debian";
	
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

	/* (non-Javadoc)
	 * @see org.opensixen.os.PlatformProvider#createDBUser()
	 */
	@Override
	public boolean createDBUser() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.opensixen.os.PlatformProvider#createDB()
	 */
	@Override
	public boolean createDB() {
		// TODO Auto-generated method stub
		return false;
	}

}
