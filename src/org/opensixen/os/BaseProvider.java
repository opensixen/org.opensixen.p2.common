/**
 * 
 */
package org.opensixen.os;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 
 * @author Eloy Gomez Indeos Consultoria http://www.indeos.es
 * 
 */
public class BaseProvider {

	public String runCommand(String cmd) throws Exception {
		StringBuffer buff = new StringBuffer();

		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String s;
		while ((s = reader.readLine()) != null) {
			buff.append(s);
		}
		return buff.toString();
	}

}
