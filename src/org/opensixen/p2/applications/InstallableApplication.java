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

package org.opensixen.p2.applications;

import java.net.URI;

import org.apache.log4j.Logger;

/**
 * Model for installable Application via P2Director
 * 
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 *
 */
public class InstallableApplication extends InstallableUnitData {
				
	public final static String PROFILE_SERVER = "OpensixenServer";
	
	/**
	 * 
	 */
	public InstallableApplication() {
		super();
	}

	/**
	 * @param ID
	 * @param name
	 * @param description
	 * @param updateSite
	 */
	public InstallableApplication(String ID, String name, String description,
			URI updateSite) {
		super(ID, name, description, updateSite);
	}

	/**
	 * @param ID
	 * @param name
	 * @param description
	 */
	public InstallableApplication(String ID, String name, String description) {
		super(ID, name, description);
	}

	/**
	 * @param ID
	 * @param name
	 * @param updateSite
	 */
	public InstallableApplication(String ID, String name, URI updateSite) {
		super(ID, name, updateSite);
	}


	private String path;
	
	private String profile;
	
	protected Logger log = Logger.getLogger(getClass());
		
	private boolean installOk = false; 
	
	private boolean configOk = false;
	
			
	/**
	 * Return the path where app should be installed
	 * or updated
	 * 
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set the path where the app shound be installed
	 * or updated
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return the installOk
	 */
	public boolean isInstallOk() {
		return installOk;
	}


	/**
	 * @param installOk the installOk to set
	 */
	public void setInstallOk(boolean installOk) {
		this.installOk = installOk;
	}	
	
	
	
	public boolean isConfigOk() {
		return configOk;
	}


	public void setConfigOk(boolean configOk) {
		this.configOk = configOk;
	}


	/**
	 * After install process
	 * 
	 * must be extended
	 */
	public void afterInstall()	{
		
	}
}
