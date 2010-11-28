/**
 * 
 */
package org.opensixen.p2.common;

import java.util.HashMap;

/**
 * @author harlock
 *
 */
public class ProductDescription {
	
	private String productMetadataURL;
	
	private String profile;
	
	private String iu;

	public final static String TYPE_LITE = "lite"; //$NON-NLS-1$
	public final static String TYPE_CLIENT = "client"; //$NON-NLS-1$
	public final static String TYPE_SERVER = "server"; //$NON-NLS-1$
	public final static String TYPE_MANAGER = "manager"; //$NON-NLS-1$
	public final static String TYPE_FULL = "full"; //$NON-NLS-1$
	
	
	public final static String IU_LITE = "OpensixenLite"; //$NON-NLS-1$
	public final static String IU_CLIENT = "OpensixenClient"; //$NON-NLS-1$
	public final static String IU_SERVER = "OpensixenServer"; //$NON-NLS-1$
	public final static String IU_MANAGER = "OpensixenServerManager"; //$NON-NLS-1$
	
	public final static String LABEL_LITE = "Opensixen Lite"; //$NON-NLS-1$
	public final static String LABEL_CLIENT = Messages.OPENSIXEN_CLIENT;
	public final static String LABEL_SERVER = Messages.OPENSIXEN_SERVER;
	public final static String LABEL_FULL = Messages.FULL_INSTALLATION;
	public final static String LABEL_MANAGER = Messages.OPENSIXEN_MANAGER;
	
	
	public final static String URL_LITE="http://dev.opensixen.org/products/lite/"; //$NON-NLS-1$
	public final static String URL_CLIENT="http://dev.opensixen.org/products/client/"; //$NON-NLS-1$
	public final static String URL_SERVER="http://dev.opensixen.org/products/server/"; //$NON-NLS-1$
	public final static String URL_MANAGER="http://dev.opensixen.org/products/manager/"; //$NON-NLS-1$
		
	public final static String PROFILE_LITE = "OpensixenLite";
	public final static String PROFILE_SERVER = "OpensixenServer";
	public final static String PROFILE_CLIENT = "OpensixenClient";
	public final static String PROFILE_MANAGER = "OpensixenManager";
	
	
	public ProductDescription(String iu, String profile,
			String productMetadataURL) {
		super();
		this.iu = iu;
		this.profile = profile;
		this.productMetadataURL = productMetadataURL;
	}

	




	/**
	 * @return the productMetadataURL
	 */
	public String getProductMetadataURL() {
		return productMetadataURL;
	}






	/**
	 * @param productMetadataURL the productMetadataURL to set
	 */
	public void setProductMetadataURL(String productMetadataURL) {
		this.productMetadataURL = productMetadataURL;
	}






	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}






	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}






	/**
	 * @return the iu
	 */
	public String getIu() {
		return iu;
	}






	/**
	 * @param iu the iu to set
	 */
	public void setIu(String iu) {
		this.iu = iu;
	}

	public static HashMap<String, ProductDescription> getDescriptions()	{
		HashMap<String, ProductDescription> desc = new HashMap<String, ProductDescription>();
		
		desc.put(TYPE_LITE, new ProductDescription(IU_LITE, PROFILE_LITE, URL_LITE)); //$NON-NLS-1$
		desc.put(TYPE_CLIENT, new ProductDescription(IU_CLIENT, PROFILE_SERVER, URL_CLIENT)); //$NON-NLS-1$
		desc.put(TYPE_SERVER, new ProductDescription(IU_SERVER, PROFILE_SERVER, URL_SERVER)); //$NON-NLS-1$
		
		return desc;
	}
	
}
