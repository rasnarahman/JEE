package client;
/* File: TunaClient.java
 * Author: Stanley Pieda, based on course example by Todd Kelley
 * Modified Date: August 2018
 * Description: Networking client that uses simple protocol to send and receive transfer objects.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.UUID;

import datatransfer.Tuna;
import datatransfer.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Need programming comments with correct author name throughout this class
 * @author xyz abc
 */
public class TunaClient {
	/* Your code here */
	/*Rasna:   ...............*/
	
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
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
		String myHostName = null;
		try {
			InetAddress myHost = Inet4Address.getLocalHost();
			myHostName = myHost.getHostName();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		try {
			connection = new Socket(InetAddress.getByName(serverName), portNum);
			output = new ObjectOutputStream (connection.getOutputStream());
			input = new ObjectInputStream( connection.getInputStream());               
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
				Message messageObj;
				
				if (recordNumber == null || recordNumber.isEmpty()
						|| omega == null || omega.isEmpty()
						|| delta == null || delta.isEmpty()
						|| theta == null || theta.isEmpty()) {
					recordNumber = null; 
					omega = null;
					delta = null;
					theta = null;
					System.out.println(">>> Empty parameter provided. Client terminated!");
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
					
					messageObj = new Message("Insert", tuna);
					
					output.writeObject(messageObj);
					output.flush();
					
					String messageString = (String) input.readObject();
					System.out.println(messageString);
				}
				
			} while(recordNumber!=null);
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}catch (ClassNotFoundException exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} 
		finally{
			try{if(input != null){input.close();}}catch(IOException ex){
				System.out.println(ex.getMessage());}
			try{if(output != null){output.flush(); output.close();}}catch(IOException ex){
				System.out.println(ex.getMessage());}
			try{if(connection != null){connection.close();}}catch(IOException ex){
				System.out.println(ex.getMessage());}
		}
	}
	
	/*Rasna:   ...............*/
	
}
