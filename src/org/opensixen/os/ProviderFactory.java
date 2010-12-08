/**
 * 
 */
package org.opensixen.os;

import java.util.ArrayList;

/**
 * 
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public class ProviderFactory {
	
	public static ArrayList<PlatformProvider> providers;
	
	static {
		providers = new ArrayList<PlatformProvider>();
		providers.add(new DebianProvider());
		providers.add(new UbuntuProvider());
		providers.add(new WindowsProvider());
	}
	
	public static  PlatformProvider getProvider()	{
		for (PlatformProvider provider: providers)	{
			if (provider.matchPlatform())	{
				return provider;
			}
		}
		return null;
	}

}
