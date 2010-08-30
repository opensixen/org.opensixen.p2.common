/**
 * 
 */
package org.opensixen.os;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 
 * @author Eloy Gomez Indeos Consultoria http://www.indeos.es
 * 
 */
public abstract class LinuxProvider implements PlatformProvider {

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
		String id = runCommand("lsb_release -i");
		return id.substring(0, id.lastIndexOf(":")).trim();
	}

	public String runCommand(String cmd) {
		StringBuffer buff = new StringBuffer();
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s;
			while ((s = reader.readLine()) != null) {
                buff.append(s);
            }

		} catch (Exception e) {
			return null;
		} 

		return buff.toString();
	}
	
	public boolean runSQL(String sql)	{
		return true;
	}

}
