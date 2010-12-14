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
package org.opensixen.p2.common;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.p2.director.app.DirectorApplication;
import org.eclipse.p2.equinox.installer.InstallDescription;
import org.eclipse.p2.equinox.installer.InstallDescriptionParser;
import org.eclipse.p2.equinox.installer.InstallUpdateProductOperation;
import org.opensixen.p2.applications.InstallJob;
import org.opensixen.p2.applications.InstallableApplication;
import org.osgi.framework.BundleContext;

/**
 * Installer 
 *
 * @author Eloy Gomez
 * Indeos Consultoria http://www.indeos.es
 * @deprecated
 */
public class Installer {
	
	
	
	private String[] shareRepositoryURL = {};
	
	private DirectorApplication director;
	
	public Installer()	{
		director = new DirectorApplication();
	}
	
	public boolean install2(InstallableApplication app)	{
		
		
		ArrayList<String> args = getInstallBaseArgs(app.getPath());
		
		// InstallIU
		args.add("-installIU");
		args.add(app.getIu());

		// Profile
		args.add("-profile");
		args.add(app.getProfile());

		// Repositorios
		args.add("-repository");
		args.add(app.getLocation().toString());
				
		String[] argsArray = args.toArray(new String[args.size()]);
		Integer ret = (Integer) director.run(argsArray);
		
		
		// Hardcoded in DirectorApplication EXIT_ERROR
		if (ret.equals(new Integer(13)))	{
			return false;
		}
		
		return true;
		
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
