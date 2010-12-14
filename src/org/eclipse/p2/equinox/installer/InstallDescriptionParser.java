/*******************************************************************************
 *  Copyright (c) 2007, 2010 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *     Code 9 - ongoing development
 *     Sonatype, Inc. - ongoing development
 *******************************************************************************/
package org.eclipse.p2.equinox.installer;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import org.eclipse.core.runtime.*;
import org.eclipse.equinox.internal.p2.core.helpers.CollectionUtils;
import org.eclipse.equinox.internal.p2.core.helpers.LogHelper;
import org.eclipse.equinox.internal.p2.repository.RepositoryTransport;
import org.eclipse.equinox.p2.metadata.VersionedId;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.opensixen.p2.applications.InstallableApplication;
import org.opensixen.p2.common.Activator;

/**
 * This class is responsible for loading install descriptions from a stream.
 */
public class InstallDescriptionParser {
	public static final String PROP_AGENT_LOCATION = "eclipse.p2.agentLocation"; //$NON-NLS-1$
	public static final String PROP_ARTIFACT_REPOSITORY = "eclipse.p2.artifacts";//$NON-NLS-1$
	public static final String PROP_BUNDLE_LOCATION = "eclipse.p2.bundleLocation";//$NON-NLS-1$
	public static final String PROP_INSTALL_LOCATION = "eclipse.p2.installLocation";//$NON-NLS-1$
	public static final String PROP_IS_AUTO_START = "eclipse.p2.autoStart";//$NON-NLS-1$
	public static final String PROP_LAUNCHER_NAME = "eclipse.p2.launcherName";//$NON-NLS-1$
	public static final String PROP_METADATA_REPOSITORY = "eclipse.p2.metadata";//$NON-NLS-1$
	public static final String PROP_PROFILE_NAME = "eclipse.p2.profileName";//$NON-NLS-1$
	public static final String PROP_P2_PROFILE = "eclipse.p2.profile";//$NON-NLS-1$
	public static final String PROP_ROOT_ID = "eclipse.p2.rootId";//$NON-NLS-1$
	public static final String PROP_ROOT_VERSION = "eclipse.p2.rootVersion";//$NON-NLS-1$
	public static final String PROP_ROOTS = "eclipse.p2.roots";//$NON-NLS-1$
	
	/**
	 * Loads and returns an install description that is stored in a properties file.
	 * @param site The URL of the install properties file.
	 */
	/*
	public static InstallDescription createDescription(String site, SubMonitor monitor) throws Exception {
		// if no description URL was given from the outside, look for an "install.properties" file 
		// in relative to where the installer is running.  This allows the installer to be self-contained
		if (site == null)
			site = "installer.properties"; //$NON-NLS-1$

		URI propsURI = URIUtil.fromString(site);
		InputStream in = null;
		if (!propsURI.isAbsolute()) {
			String installerInstallArea = System.getProperty("osgi.install.area");
			if (installerInstallArea == null)
				throw new IllegalStateException("Install area is not specified.");

			propsURI = URIUtil.append(URIUtil.fromString(installerInstallArea), site);
			File installerDescription = URIUtil.toFile(propsURI);
			if (!installerDescription.exists()) {
				throw new IllegalStateException("Can't find install description file: " + installerDescription);
			}
		}
		Map<String, String> properties;
		try {
			in = RepositoryTransport.getInstance().stream(propsURI, monitor);
			properties = CollectionUtils.loadProperties(in);
		} finally {
			safeClose(in);
		}

		URI base = getBase(propsURI);
		InstallDescription result = new InstallDescription();
		result = initialize(result, properties, base);
		initializeProfileProperties(result, properties);

		// now override the properties from anything interesting in system properties
		result = initialize(result, CollectionUtils.toMap(System.getProperties()), base);
		return result;
	}
	
	
	
	*/
	
	/**
	 * Loads and returns an install description that is stored in a properties file.
	 * @param site The URL of the install properties file.
	 */
	public static InstallDescription createDescription(InstallableApplication application, SubMonitor monitor) throws Exception {
		
		Map<String, String> properties = new HashMap<String, String>();
		
		properties.put(PROP_METADATA_REPOSITORY, application.getLocation().toString());
		properties.put(PROP_ARTIFACT_REPOSITORY, application.getLocation().toString());
		properties.put(PROP_PROFILE_NAME, application.getProfile());
		properties.put(PROP_P2_PROFILE, application.getProfile());
		properties.put(PROP_ROOT_ID, application.getIu());
		properties.put(PROP_IS_AUTO_START, "true");
		properties.put(PROP_BUNDLE_LOCATION, application.getPath());
		properties.put(PROP_INSTALL_LOCATION, application.getPath());
		properties.put(PROP_AGENT_LOCATION, application.getPath() + "/p2");
		//properties.put("-profileProperties", null);
		//properties.put("-roaming",null);
		//properties.put("org.eclipse.update.install.features", "true");
		properties.put("eclipse.p2.flavor", "tooling");
		
		InstallDescription result = new InstallDescription();
		result = initialize(result, properties, null);
		initializeProfileProperties(result, properties);

		// now override the properties from anything interesting in system properties
		result = initialize(result, CollectionUtils.toMap(System.getProperties()), null);
		return result;
	}
		
	
	
	
	

	private static URI getBase(URI uri) {
		if (uri == null)
			return null;

		String uriString = uri.toString();
		int slashIndex = uriString.lastIndexOf('/');
		if (slashIndex == -1 || slashIndex == (uriString.length() - 1))
			return uri;

		return URI.create(uriString.substring(0, slashIndex + 1));
	}

	private static InstallDescription initialize(InstallDescription description, Map<String, String> properties, URI base) {
		String property = properties.get(PROP_ARTIFACT_REPOSITORY);
		if (property != null)
			description.setArtifactRepositories(getURIs(property, base));

		property = properties.get(PROP_METADATA_REPOSITORY);
		if (property != null)
			description.setMetadataRepositories(getURIs(property, base));

		property = properties.get(PROP_IS_AUTO_START);
		if (property != null)
			description.setAutoStart(Boolean.TRUE.toString().equalsIgnoreCase(property));

		property = properties.get(PROP_LAUNCHER_NAME);
		if (property != null)
			description.setLauncherName(property);

		property = properties.get(PROP_INSTALL_LOCATION);
		if (property != null)
			description.setInstallLocation(new Path(property));

		property = properties.get(PROP_AGENT_LOCATION);
		if (property != null)
			description.setAgentLocation(new Path(property));

		property = properties.get(PROP_BUNDLE_LOCATION);
		if (property != null)
			description.setBundleLocation(new Path(property));

		property = properties.get(PROP_PROFILE_NAME);
		if (property != null)
			description.setProductName(property);

		// Process the retro root id and rootVersion properties
		String id = properties.get(PROP_ROOT_ID);
		if (id != null) {
			String version = properties.get(PROP_ROOT_VERSION);
			try {
				description.setRoots(new IVersionedId[] {new VersionedId(id, version)});
			} catch (IllegalArgumentException e) {
				LogHelper.log(new Status(IStatus.ERROR, Activator.ID, "Invalid version in install description: " + version, e)); //$NON-NLS-1$
			}
		}

		String rootSpec = properties.get(PROP_ROOTS);
		if (rootSpec != null) {
			String[] rootList = getArrayFromString(rootSpec, ","); //$NON-NLS-1$
			ArrayList<IVersionedId> roots = new ArrayList<IVersionedId>(rootList.length);
			for (int i = 0; i < rootList.length; i++) {
				try {
					roots.add(VersionedId.parse(rootList[i]));
				} catch (IllegalArgumentException e) {
					LogHelper.log(new Status(IStatus.ERROR,  Activator.ID, "Invalid version in install description: " + rootList[i], e)); //$NON-NLS-1$
				}
			}
			if (!roots.isEmpty())
				description.setRoots(roots.toArray(new IVersionedId[roots.size()]));
		}
		return description;
	}

	/**
	 * Add all of the given properties to profile properties of the given description 
	 * after removing the keys known to be for the installer.  This allows install descriptions 
	 * to also set random profile properties.
	 * @param description
	 * @param properties
	 */
	private static void initializeProfileProperties(InstallDescription description, Map<String, String> properties) {
		//any remaining properties are profile properties
		Map<String, String> profileProperties = new HashMap<String, String>(properties);
		profileProperties.remove(PROP_PROFILE_NAME);
		profileProperties.remove(PROP_ARTIFACT_REPOSITORY);
		profileProperties.remove(PROP_METADATA_REPOSITORY);
		profileProperties.remove(PROP_IS_AUTO_START);
		profileProperties.remove(PROP_LAUNCHER_NAME);
		profileProperties.remove(PROP_AGENT_LOCATION);
		profileProperties.remove(PROP_BUNDLE_LOCATION);
		profileProperties.remove(PROP_ROOT_ID);
		profileProperties.remove(PROP_ROOT_VERSION);
		profileProperties.remove(PROP_ROOTS);
		description.setProfileProperties(profileProperties);
	}

	/**
	 * Returns an array of URIs from the given comma-separated list
	 * of URLs. Returns null if the given spec does not contain any URLs.
	 * @param base 
	 * @return An array of URIs in the given spec, or <code>null</code>
	 */
	private static URI[] getURIs(String spec, URI base) {
		String[] urlSpecs = getArrayFromString(spec, ","); //$NON-NLS-1$
		ArrayList<URI> result = new ArrayList<URI>(urlSpecs.length);
		for (int i = 0; i < urlSpecs.length; i++) {
			try {
				URI uri = URIUtil.fromString(urlSpecs[i]);
				// base can be null if loaded from InstallableApplication
				if (base != null)	{
					uri = URIUtil.makeAbsolute(uri, base);
				}
				result.add(uri);
			} catch (URISyntaxException e) {
				LogHelper.log(new Status(IStatus.ERROR,  Activator.ID, "Invalid URL in install description: " + urlSpecs[i], e)); //$NON-NLS-1$
			}
		}
		if (result.isEmpty())
			return null;
		return result.toArray(new URI[result.size()]);
	}

	private static void safeClose(InputStream in) {
		try {
			if (in != null)
				in.close();
		} catch (IOException e) {
			//ignore secondary failure during close
		}
	}

	/**
	 * Convert a list of tokens into an array. The list separator has to be
	 * specified.
	 */
	public static String[] getArrayFromString(String list, String separator) {
		if (list == null || list.trim().equals("")) //$NON-NLS-1$
			return new String[0];
		List<String> result = new ArrayList<String>();
		for (StringTokenizer tokens = new StringTokenizer(list, separator); tokens.hasMoreTokens();) {
			String token = tokens.nextToken().trim();
			if (!token.equals("")) //$NON-NLS-1$
				result.add(token);
		}
		return result.toArray(new String[result.size()]);
	}

}
