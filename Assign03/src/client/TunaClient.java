/* File TunaClient.java
 * Author: Stanley Pieda
 * Created On: August 2018
 * Description: Client used to insert Tuna records on server.
 */
package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.UUID;

import datatransfer.Tuna;
import remoteinterface.TunaService;
import server.TunaServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.rmi.NotBoundException;

/**
 * Ensure that all classes have Javadoc comments with your name
 * @author abc xyz
 */
public class TunaClient {
	private String serverName = "localhost";
	private int portNum = 8081;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String recordNumber="";
	String omega="";
	String delta="";
	String theta="";
	
	
	public static void main(String[] args) {
		switch (args.length){
		case 2:
			(new TunaClient(args[1],Integer.parseInt(args[2]))).runClient();
			break;
		case 1:
			(new TunaClient("localhost",Integer.parseInt(args[1]))).runClient();
			break;
		default:
			(new TunaClient("localhost",8081)).runClient();
		}

	}
	
	
	public TunaClient(String serverName, int portNum){
		this.serverName = serverName;
		this.portNum = portNum;
	}
	
	
	public void runClient(){
		try {
			TunaService tunaService = (TunaService) Naming.lookup("rmi://" + this.serverName + ":" + this.portNum + "/TunaService");
             
			do {
				System.out.println("Please enter record number: ");
				recordNumber = br.readLine();
				System.out.println("Please enter omega: ");
				omega = br.readLine();
				System.out.println("Please enter delta: ");
				delta = br.readLine();
				System.out.println("Please enter theta: ");
				theta = br.readLine();
				
				
				Tuna tuna;			
				
				if (recordNumber == null || recordNumber.isEmpty()
						|| omega == null || omega.isEmpty()
						|| delta == null || delta.isEmpty()
						|| theta == null || theta.isEmpty()) {
					recordNumber = null; 
					omega = null;
					delta = null;
					theta = null;
				}
				else {
					//Create Tuna and Message object to transfer to the server.
					
					UUID uuid = UUID.randomUUID();
					
					tuna = new Tuna();
					tuna.setRecordNumber(Integer.parseInt(recordNumber));
					tuna.setOmega(omega);
					tuna.setDelta(delta);
					tuna.setTheta(theta);
					tuna.setUUID(uuid.toString());
					

					tunaService.insert(tuna);
					Tuna insertedTuna = tunaService.findByUUID(uuid.toString());
					System.out.println("Returned tuna: " + insertedTuna);
					
					System.out.println("Do you want to insert another tuna? Yes / No");
					String input = br.readLine();
					if(input.equals("No") || input.equals("no")) {
						System.out.println("The End.");
						break;
					}
				}
				
			} while(recordNumber!=null);
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}catch (NotBoundException exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} 
	}
	
	/*Rasna:   ...............*/
}
