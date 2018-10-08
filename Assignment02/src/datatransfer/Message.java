package datatransfer;

import java.io.Serializable;

/**
 * Need programming comments with correct author name throughout this class
 * @author xyz abc
 */
public class Message implements Serializable{

	/*Rasna:   .............. */
	
	private static final long serialVersionUID = 101;
	private String command;
	private Tuna tuna;
	
	public Message(String command) {
		setCommand(command);
	}
	
	public Message(String command, Tuna tuna) {
		setCommand(command);
		setTuna(tuna);
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command=command;
	}
	
	public Tuna getTuna() {
		return tuna;
	}
	
	public void setTuna(Tuna tuna) {
		this.tuna=tuna;
	}
	
}
