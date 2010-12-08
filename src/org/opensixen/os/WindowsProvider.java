/**
 * 
 */
package org.opensixen.os;

import org.opensixen.p2.common.Activator;
import org.osgi.framework.BundleContext;

/**
 * 
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public class WindowsProvider extends BaseProvider implements PlatformProvider {

	/* (non-Javadoc)
	 * @see org.opensixen.os.PlatformProvider#getExecPath(int)
	 */
	@Override
	public String getExecPath(int exec) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.opensixen.os.PlatformProvider#isUnix()
	 */
	@Override
	public boolean isUnix() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.opensixen.os.PlatformProvider#matchPlatform()
	 */
	@Override
	public boolean matchPlatform() {
		BundleContext ctx = Activator.getContext();
		String os =  ctx.getProperty("osgi.os");
		if (os == "win32")	{
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
