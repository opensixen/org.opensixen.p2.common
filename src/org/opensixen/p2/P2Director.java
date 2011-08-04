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
package org.opensixen.p2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.p2.engine.SimpleProfileRegistry;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.IRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.p2.equinox.installer.InstallDescription;
import org.eclipse.p2.equinox.installer.InstallDescriptionParser;
import org.eclipse.p2.equinox.installer.InstallUpdateProductOperation;
import org.opensixen.p2.applications.InstallJob;
import org.opensixen.p2.applications.InstallableApplication;
import org.opensixen.p2.applications.InstallableUnitData;
import org.opensixen.p2.applications.LoggerProgressMonitor;
import org.opensixen.p2.common.Activator;
import org.opensixen.p2.exceptions.P2Exception;

/**
 * P2Director
 * 
 * Main API class for P2 operations via director.
 * 
 * org.opensixen.core.p2 is only
 * 
 * @author Eloy Gomez Indeos Consultoria http://www.indeos.es
 */
public class P2Director {

	private static P2Director instance;
	
	private IProvisioningAgent agent;
	private IRepositoryManager<IMetadataRepository> metadataManager;
	//private CustomRepositoryManager metadataManager;
	public static final String SERVER_LOCATION_SUFIX = "tomcat/webapps/osx/WEB-INF/eclipse/p2/org.eclipse.equinox.p2.engine/profileRegistry";

	private String install_path = "/tmp/server_installer/";
	
	public static P2Director get()	{
		if (instance == null)	{
			instance = new P2Director();
		}
		return instance;
	}
	
	private P2Director() {
		//File location = new File(install_path + SERVER_LOCATION_SUFIX);
		
		//AgentLocation agentLocation = new AgentLocation(location.toURI());
		//Dictionary locationProperties = new Hashtable<String, Object>();
		//Activator.getContext().registerService(IAgentLocation.class.getName(), agentLocation, locationProperties);
		//agent.registerService(IAgentLocation.class.getName(), agentLocation);
		agent = (IProvisioningAgent) Activator.getService(IProvisioningAgent.SERVICE_NAME);				
		metadataManager = (IRepositoryManager<IMetadataRepository>) agent.getService(IMetadataRepositoryManager.SERVICE_NAME);		
	}
	
	
	/**
	 * Return an array of InstallableUnitData with software installed in the
	 * server
	 * 
	 * @param location
	 * @return
	 */
	public List<InstallableUnitData> getServerInstalledApps(File location) {
		ArrayList<InstallableUnitData> iunits = new ArrayList<InstallableUnitData>();
		IProfileRegistry profileRegistry = new SimpleProfileRegistry(agent,
				location);
		if (profileRegistry == null) {
			return iunits;
		}

		IProfile[] profiles = profileRegistry.getProfiles();
		for (int i = 0; i < profiles.length; i++) {
			IQueryResult<IInstallableUnit> result = profiles[i].available(
					QueryUtil.createIUGroupQuery(), null);
			IInstallableUnit[] units = result.toArray(IInstallableUnit.class);
			for (IInstallableUnit unit : units) {
				iunits.add(new InstallableUnitData(unit.getId(), unit
						.getProperty(IInstallableUnit.PROP_NAME), unit
						.getProperty(IInstallableUnit.PROP_DESCRIPTION)));
			}
		}
		return iunits;
	}

	
	/**
	 * Perform install or update process
	 * 
	 * @param job
	 * @param monitor
	 * @throws P2Exception
	 */
	public void install(InstallJob job)throws P2Exception{
		IProgressMonitor monitor = new LoggerProgressMonitor();
		install(job, monitor);
	}
	
	/**
	 * Perform install or update process
	 * 
	 * @param job
	 * @param monitor
	 * @throws P2Exception
	 */
	public void install(InstallJob job, IProgressMonitor monitor)throws P2Exception{
		for (InstallableApplication app: job.getInstallableApplications())	{
			InstallDescription description = InstallDescriptionParser.createDescription(app, null);							
			
			InstallUpdateProductOperation op = new InstallUpdateProductOperation(description);						
			//IStatus status = op.install(monitor);	
			
			IStatus status = op.addPackages(monitor); 
			
			if (status.getSeverity() == IStatus.ERROR)	{
				app.setInstallOk(false);			
				throw new P2Exception(status.getException());				
			}
						
			// run custom app stuff
			app.afterInstall();
			
			// Set app install ok
			app.setInstallOk(true);

		}
		
	}
	
}
