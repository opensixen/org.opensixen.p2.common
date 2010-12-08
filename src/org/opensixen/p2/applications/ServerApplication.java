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
public class ServerApplication extends InstallableApplication {

	public final static String IU_SERVER = "OpensixenServer"; //$NON-NLS-1$
	public final static String URL_SERVER="http://dev.opensixen.org/products/server/"; //$NON-NLS-1$
	public final static String PROFILE_SERVER = "OpensixenServer";
	
	private static final String SERVER_SUFIX = "/tomcat/webapps/osx/WEB-INF/eclipse";
	
	/**
	 * 
	 */
	public ServerApplication() {
		super(IU_SERVER, PROFILE_SERVER);
	}

	/** 
	 * Tenemos que añadirle nun sufijo al servidor para que se instale en el sitio correcto
	 */
	@Override
	public String getPath() {
		String path =  super.getPath();
		
		return path + SERVER_SUFIX;
	}		
	
}
