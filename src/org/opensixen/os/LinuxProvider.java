/**
 * 
 */
package org.opensixen.os;

import org.opensixen.p2.common.Activator;
import org.osgi.framework.BundleContext;

/**
 * 
 * 
 * @author Eloy Gomez Indeos Consultoria http://www.indeos.es
 * 
 */
public abstract class LinuxProvider extends BaseProvider implements PlatformProvider {

	/* (non-Javadoc)
	 * @see org.opensixen.os.PatformDetailsProvider#getExecPath(int)
	 */
	@Override
	public String getExecPath(int exec) {
		switch (exec) {
		case PlatformProvider.PGSQL:
			return "/usr/bin/psql";
		case PlatformProvider.PGDUMP:
			return "/usr/bin/pg_dump";

		default:
			return null;
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opensixen.os.PatformDetailsProvider#isUnix()
	 */
	@Override
	public boolean isUnix() {
		return true;
	}

	/**
	 * Try to get the Distributor ID via lsb_release -i
	 * 
	 * @return
	 */
	public String getDistributor_ID() {
		try {
			String id = runCommand("lsb_release -i");
			return id.substring(0, id.lastIndexOf(":")).trim();
		}
		catch (Exception e)	{
			return null;
		}
	}
	
	public boolean runSQL(String sql)	{
		return true;
	}

	
	protected abstract boolean matchLinuxFlavor();

	/* (non-Javadoc)
	 * @see org.opensixen.os.PlatformProvider#matchPlatform()
	 */
	@Override
	public boolean matchPlatform() {
		BundleContext ctx = Activator.getContext();
		String os =  ctx.getProperty("osgi.os");
		if (os == "linux")	{
			return matchLinuxFlavor();
		}
		return false;

	}

	
	
}
