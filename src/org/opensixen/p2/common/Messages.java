/**
 * 
 */
package org.opensixen.p2.common;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.opensixen.p2.common.messages"; //$NON-NLS-1$
	public static String FULL_INSTALLATION;
	public static String OPENSIXEN_CLIENT;
	public static String OPENSIXEN_MANAGER;
	public static String OPENSIXEN_SERVER;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
