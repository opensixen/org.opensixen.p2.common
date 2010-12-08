/**
 * 
 */
package org.opensixen.p2.common;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.p2.director.app.DirectorApplication;
import org.eclipse.p2.equinox.installer.InstallDescription;
import org.eclipse.p2.equinox.installer.InstallDescriptionParser;
import org.eclipse.p2.equinox.installer.InstallUpdateProductOperation;
import org.opensixen.p2.applications.InstallJob;
import org.opensixen.p2.applications.InstallableApplication;
import org.osgi.framework.BundleContext;

/**
 * @author harlock
 *@deprecated
 */
public class Installer {
	
	
	
	private String[] shareRepositoryURL = {};
	
	private DirectorApplication director;
	
	public Installer()	{
		director = new DirectorApplication();
	}
	
	public boolean install2(InstallableApplication app)	{
		
		
		ArrayList<String> args = getInstallBaseArgs(app.getPath());
		
		// InstallIU
		args.add("-installIU");
		args.add(app.getIu());

		// Profile
		args.add("-profile");
		args.add(app.getProfile());

		// Repositorios
		args.add("-repository");
		args.add(app.getLocation().toString());
				
		String[] argsArray = args.toArray(new String[args.size()]);
		Integer ret = (Integer) director.run(argsArray);
		
		
		// Hardcoded in DirectorApplication EXIT_ERROR
		if (ret.equals(new Integer(13)))	{
			return false;
		}
		
		return true;
		
	}
		
	private static ArrayList<String> getInstallBaseArgs(String targetDir)	{
		ArrayList<String> args = new ArrayList<String>();

		BundleContext ctx = Activator.getContext();		
		// Destino
		args.add("-destination");
		args.add(targetDir);
		
		args.add("-bundlepool");
		args.add(targetDir);
		
		args.add("-profileProperties");
		
		args.add("org.eclipse.update.install.features=true");
		args.add("-p2.os");
		args.add(ctx.getProperty("osgi.os"));
		args.add("-p2.ws");
		args.add(ctx.getProperty("osgi.ws"));
		args.add("-p2.arch");
		args.add(ctx.getProperty("osgi.arch"));
		args.add("-roaming");
				
		return args;
	}
	
	public String getRepositoryParam(String repoURL)	{
		StringBuffer buff = null;
		for (String repo:shareRepositoryURL)	{
			if (buff == null)	{
				buff = new StringBuffer();
			}
			else {
				buff.append(", ");
			}
			buff.append(repo);
			
			
		}
		
		// Añadimos el repo del producto
		if (buff == null)	{
			buff = new StringBuffer();
		}
		else {
			buff.append(", ");
		}
		buff.append(repoURL);		
		return buff.toString();		
	}
}
