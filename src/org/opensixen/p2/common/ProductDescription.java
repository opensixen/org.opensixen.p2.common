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

	public final static String TYPE_LITE = "lite";
	public final static String TYPE_CLIENT = "client";
	public final static String TYPE_SERVER = "server";
	public final static String TYPE_MANAGER = "manager";
	public final static String TYPE_FULL = "full";
	
	
	public final static String IU_LITE = "OpensixenLite";
	public final static String IU_CLIENT = "OpensixenClient";
	public final static String IU_SERVER = "OpensixenServer";
	public final static String IU_MANAGER = "OpensixenServerManager";
	
	public final static String LABEL_LITE = "Opensixen Lite";
	public final static String LABEL_CLIENT = "Cliente Opensixen";
	public final static String LABEL_SERVER = "Servidor Opensixen";
	public final static String LABEL_FULL = "Instalacion Completa (cliente + servidor)";
	public final static String LABEL_MANAGER = "Opensixen Server Manager";
	
	
	public final static String URL_LITE="http://dev.opensixen.org/products/lite/";
	public final static String URL_CLIENT="http://dev.opensixen.org/products/client/";
	public final static String URL_SERVER="http://dev.opensixen.org/products/server/";
	public final static String URL_MANAGER="http://dev.opensixen.org/products/manager/";
		
	
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
		
		desc.put(TYPE_LITE, new ProductDescription(IU_LITE, "profile", URL_LITE));
		desc.put(TYPE_CLIENT, new ProductDescription(IU_CLIENT, "profile", URL_CLIENT));
		desc.put(TYPE_SERVER, new ProductDescription(IU_SERVER, "profile", URL_SERVER));
		
		return desc;
	}
	
}
