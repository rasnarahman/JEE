/* File TunaServer.java
 * Author: Todd Kelley
 * Modified By: Stanley Pieda
 * Modifed On: August 2018
 * Description: RMI Server startup.
 */
package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import remoteinterface.TunaService;
/*
 * Referenced works on shutting down RMI within the application:
 * https://coderanch.com/t/210114/java/Shut-RMI-Registry
 * https://community.oracle.com/thread/1180058?start=0&tstart=0
 */

public class TunaServer {
	public static void main(String[] args) {
		try {
			int portNum = 8081;
			if(args.length > 0){
				portNum = Integer.parseInt(args[0]);
			}
			// ToDo: Create the remote service
			TunaService tunaServiceImpl = new TunaServiceImpl(); // replace null
			
			// ToDo: create RMI registry, saving reference to it
			// message to users (this is done already on, next line)
			LocateRegistry.createRegistry(portNum);
			System.out.println( "Registry created" );
			
			// ToDo: export the remote service
			// ToDo: rebind using portNum with service name /TunaService
			// message to users (this is done already on next line)
			UnicastRemoteObject.exportObject(tunaServiceImpl, 0);
			System.out.println( "Exported" );
			
			Naming.rebind("//localhost:" + portNum + "/TunaService", tunaServiceImpl);
			System.out.println( "Rebinded" );
			
			
			System.out.println("Press any key to shutdown remote object and end program");
			Scanner input = new Scanner(System.in);
			input.nextLine(); // pause, let server-side admin close down connections
			
			//tunaServiceImpl.shutDownDao(); // close down Hibernate resources
			
			System.out.println("un-exporting remote object");
			UnicastRemoteObject.unexportObject(tunaServiceImpl, true); // remove remote object
			
			//next lines not needed in this case, port 1099 is free on JVM exit according to TCPView
			//see: https://docs.microsoft.com/en-us/sysinternals/downloads/tcpview
			//System.out.println("Shutting down registry"); 
			//UnicastRemoteObject.unexportObject(registry, true);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
			e.printStackTrace();
		}
	}
}
