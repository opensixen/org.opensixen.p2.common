 /******* BEGIN LICENSE BLOCK *****
 * Versión: GPL 2.0/CDDL 1.0/EPL 1.0
 *
 * Los contenidos de este fichero están sujetos a la Licencia
 * Pública General de GNU versión 2.0 (la "Licencia"); no podrá
 * usar este fichero, excepto bajo las condiciones que otorga dicha 
 * Licencia y siempre de acuerdo con el contenido de la presente. 
 * Una copia completa de las condiciones de de dicha licencia,
 * traducida en castellano, deberá estar incluida con el presente
 * programa.
 * 
 * Adicionalmente, puede obtener una copia de la licencia en
 * http://www.gnu.org/licenses/gpl-2.0.html
 *
 * Este fichero es parte del programa opensiXen.
 *
 * OpensiXen es software libre: se puede usar, redistribuir, o
 * modificar; pero siempre bajo los términos de la Licencia 
 * Pública General de GNU, tal y como es publicada por la Free 
 * Software Foundation en su versión 2.0, o a su elección, en 
 * cualquier versión posterior.
 *
 * Este programa se distribuye con la esperanza de que sea útil,
 * pero SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita 
 * MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO. Consulte 
 * los detalles de la Licencia Pública General GNU para obtener una
 * información más detallada. 
 *
 * TODO EL CÓDIGO PUBLICADO JUNTO CON ESTE FICHERO FORMA PARTE DEL 
 * PROYECTO OPENSIXEN, PUDIENDO O NO ESTAR GOBERNADO POR ESTE MISMO
 * TIPO DE LICENCIA O UNA VARIANTE DE LA MISMA.
 *
 * El desarrollador/es inicial/es del código es
 *  FUNDESLE (Fundación para el desarrollo del Software Libre Empresarial).
 *  Indeos Consultoria S.L. - http://www.indeos.es
 *
 * Contribuyente(s):
 *  Eloy Gómez García <eloy@opensixen.org> 
 *
 * Alternativamente, y a elección del usuario, los contenidos de este
 * fichero podrán ser usados bajo los términos de la Licencia Común del
 * Desarrollo y la Distribución (CDDL) versión 1.0 o posterior; o bajo
 * los términos de la Licencia Pública Eclipse (EPL) versión 1.0. Una 
 * copia completa de las condiciones de dichas licencias, traducida en 
 * castellano, deberán de estar incluidas con el presente programa.
 * Adicionalmente, es posible obtener una copia original de dichas 
 * licencias en su versión original en
 *  http://www.opensource.org/licenses/cddl1.php  y en  
 *  http://www.opensource.org/licenses/eclipse-1.0.php
 *
 * Si el usuario desea el uso de SU versión modificada de este fichero 
 * sólo bajo los términos de una o más de las licencias, y no bajo los 
 * de las otra/s, puede indicar su decisión borrando las menciones a la/s
 * licencia/s sobrantes o no utilizadas por SU versión modificada.
 *
 * Si la presente licencia triple se mantiene íntegra, cualquier usuario 
 * puede utilizar este fichero bajo cualquiera de las tres licencias que 
 * lo gobiernan,  GPL 2.0/CDDL 1.0/EPL 1.0.
 *
 * ***** END LICENSE BLOCK ***** */
package org.compiere.util;

import java.util.Properties;

/**
 * CustomConnection 
 * 
 * Class for manage CConection creation and parse 
 * without include org.opensixen.base
 *
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 */
public class CustomConnection {

	public static final String P_AppsHost = "AppsHost";
	public static final String P_Name = "name";
	public static final String P_AppsPort = "AppsPort";
	public static final String P_DBType = "type";
	public static final String P_DBHost = "DBhost";
	public static final String P_DBPort = "DBport";
	public static final String P_DBName = "DBname";
	public static final String P_DBUser = "UID";
	public static final String P_DBPassword = "PWD";

	/**
	 *  String representation.
	 *  Used also for Instanciation
	 *  @return string representation
	 *	@see #setAttributes(String) setAttributes
	 */
	public static String getConnectionString (Properties config)
	{				
		
		// Setup some custom values
		config.put("BQ", "false"); //$NON-NLS-1$
		config.put("FW", "false"); //$NON-NLS-1$
		config.put("FWhost", ""); //$NON-NLS-1$ //$NON-NLS-2$
		config.put("FWport", "");	 //$NON-NLS-1$ //$NON-NLS-2$
		config.put("SystemUID", "postgres"); //$NON-NLS-1$
		config.put("SystemPWD", "postgres"); //$NON-NLS-1$
		
		StringBuffer sb = new StringBuffer ("CConnection["); //$NON-NLS-1$
		sb.append ("name=").append (config.getProperty(P_Name)) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",AppsHost=").append (config.getProperty(P_AppsHost)) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",AppsPort=").append (config.getProperty(P_AppsPort)) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",type=").append (config.getProperty(P_DBType)) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",DBhost=").append (config.getProperty(P_DBHost)) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",DBport=").append (config.getProperty(P_DBPort)) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",DBname=").append (config.getProperty(P_DBName)) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",BQ=").append (config.getProperty("BQ")) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",FW=").append (config.getProperty("FW")) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",FWhost=").append (config.getProperty("FWhost")) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",FWport=").append (config.getProperty("FWport")) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",UID=").append (config.getProperty(P_DBUser)) //$NON-NLS-1$ //$NON-NLS-2$
		  .append (",PWD=").append (config.getProperty(P_DBPassword)) //$NON-NLS-1$ //$NON-NLS-2$
		  ;		//	the format is read by setAttributes
		sb.append ("]"); //$NON-NLS-1$
		return sb.toString ();
	}	//  toStringLong
	
	/**
	 *  Set Attributes from String (pares toStringLong())
	 *  @param attributes attributes
	 */
	public static Properties getProperties (String attributes)
	{
		Properties prop = new Properties();
		try {				
		String appsHost = attributes.substring (attributes.indexOf ("AppsHost=") + 9, attributes.indexOf (",AppsPort="));
		prop.setProperty(P_AppsHost, appsHost);
		String name = attributes.substring (attributes.indexOf ("name=") + 5, attributes.indexOf (",AppsHost=")); 
		prop.setProperty(P_Name, name);
		
		int index = attributes.indexOf("AppsPort=");
		String appsPort = attributes.substring (index + 9, attributes.indexOf (",", index)); 
		prop.setProperty(P_AppsPort, appsPort);
		//
		String type = attributes.substring (attributes.indexOf ("type=")+5, attributes.indexOf (",DBhost="));
		prop.setProperty(P_DBType, type);
		String dbHost = attributes.substring (attributes.indexOf ("DBhost=") + 7, attributes.indexOf (",DBport=")); 
		prop.setProperty(P_DBHost, dbHost);
		
		String dbPort = attributes.substring (attributes.indexOf ("DBport=") + 7, attributes.indexOf (",DBname="));
		prop.setProperty(P_DBPort, dbPort);
		
		String dbName = attributes.substring (attributes.indexOf ("DBname=") + 7, attributes.indexOf (",BQ=")); 
		prop.setProperty(P_DBName, dbName);
		//
		//setBequeath (attributes.substring (attributes.indexOf ("BQ=") + 3, attributes.indexOf (",FW=")));
		//setViaFirewall (attributes.substring (attributes.indexOf ("FW=") + 3, attributes.indexOf (",FWhost=")));
		//setFwHost (attributes.substring (attributes.indexOf ("FWhost=") + 7, attributes.indexOf (",FWport=")));
		//setFwPort (attributes.substring (attributes.indexOf ("FWport=") + 7, attributes.indexOf (",UID=")));
		//
		String dbUser = attributes.substring (attributes.indexOf ("UID=") + 4, attributes.indexOf (",PWD=")); 
		prop.setProperty(P_DBUser, dbUser);
		
		
		String dbPassword = attributes.substring (attributes.indexOf ("PWD=") + 4, attributes.indexOf ("]"));
		prop.setProperty(P_DBPassword, dbPassword);
		//
		}
		catch (Exception e)	{
			return null;
		}
			
		return prop;
	}	//  setAttributes

	
}
