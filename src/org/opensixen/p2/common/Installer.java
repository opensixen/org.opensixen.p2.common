/**
 * 
 */
package org.opensixen.p2.common;

import java.util.ArrayList;

import org.eclipse.equinox.internal.p2.director.app.DirectorApplication;
import org.osgi.framework.BundleContext;

/**
 * @author harlock
 *
 */
public class Installer {
	
	//private String[] repositoryURL = {"file:/home/harlock/workspace/workspace-opensixen-born/updates.opensixen.org/"};
	//private String[] repositoryURL = {"file:/tmp/client/repository"};
	//private String[] repositoryURL = {OpensixenRepositories.PRODUCT_LITE_URL};
	
	private static final String SERVER_SUFIX = "/tomcat/webapps/osx/WEB-INF/eclipse";
	
	private String[] shareRepositoryURL = {};
	
	private DirectorApplication director;
	
	public Installer()	{
		director = new DirectorApplication();
	}
	
	public boolean install(String type, String targetDir)	{
		
		// Para que el servidor de aplicaciones se coloque en su lugar,
		// debemos añadir a la ruta del servidor el sufijo
		if (type.equals(ProductDescription.TYPE_SERVER))	{
			targetDir = targetDir + SERVER_SUFIX;
		}
		
		ArrayList<String> args = getInstallBaseArgs(targetDir);
		ProductDescription description = ProductDescription.getDescriptions().get(type);
		
		// InstallIU
		args.add("-installIU");
		args.add(description.getIu());

		// Profile
		args.add("-profile");
		args.add(description.getProfile());

		// Repositorios
		args.add("-repository");
		args.add(getRepositoryParam(description.getProductMetadataURL()));
				
		String[] argsArray = args.toArray(new String[args.size()]);
		Integer ret = (Integer) director.run(argsArray);
		
		// Hardcoded in DirectorApplication EXIT_ERROR
		if (ret.equals(new Integer(13)))	{
			return false;
		}
		return true;
	}
	
	
	public void list()	{
		/*
		ArrayList<String> args = getBaseArgs();

		// InstallIU
		args.add("-list");
		String[] argsArray = args.toArray(new String[args.size()]);
		director.run(argsArray);
		*/				
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
