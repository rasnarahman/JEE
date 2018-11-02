/* File: Tuna.java
 * Author: Stanley Pieda
 * Modified: 
 * Date: August 2018
 * Description: Sample solution to Assignment 2
 * Modifications:
 *     ToDo: update with JPA annotations for Hibernate to use
 *     Note: Use Javadoc comments, then annotations, then method header.
 */
package datatransfer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;


/**
 * Transfer object for Tuna data
 * @author Stanley Pieda
 */
@Entity
@Table(name="Tuna")
public class Tuna implements Serializable{
	/** Explicit serialVersionUID to avoid generating one automatically */
	private static final long serialVersionUID = 1L;
	
	/** ID value for database */
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/** recordNumber for database, originally matched a dataset file line number */
	@Column(name="recordNumber",length=42)
	private int recordNumber;
	
	/** omega field */
	@Column(name="omega",length=42)
	private String omega;
	
	/** delta field */
	@Column(name="delta",length=42)
	private String delta;
	
	/** theta field */
	@Column(name="theta",length=42)
	private String theta;
	
	/** uuid field, contains UUID as String */
	@Column(name="uuid",length=42)
	private String uuid;
	
	/** see lab hand-out notes from assignment 1 */
	@Transient
	private boolean isLastTuna;
	
	/**
	 * Default constructor, sets id and recordNumber to zero, omega, delta, theta, and uuid to empty Strings
	 * @author Stanley Pieda
	 */
	public Tuna() {
		this(0,0,"","","","");
	}
	
	/**
	 * Telescoping constructor.
	 * @param id The id as Integer
	 * @param recordNumber The recordNumber as int
	 * @param omega The omega as String
	 * @param lambda The delta as String
	 * @param theta The theta as String
	 * @param uuid The UUID as String
	 * @author Stanley Pieda
	 */
	public Tuna(Integer id, int recordNumber, String omega, String delta, String theta, String uuid) {
		this.id = id;
		this.recordNumber = recordNumber;
		this.omega = omega;
		this.delta = delta;
		this.theta = theta;
		this.uuid = uuid;
	}
	
	/** Getter for id */
	public Integer getId() {
		return id;
	}
	/** Setter for id */
	public void setId(Integer id) {
		this.id = id;
	}
	/** Getter for recordNumber */
	public int getRecordNumber() {
		return recordNumber;
	}
	/** Setter for recordNumber */
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	/** Getter for omega */
	public String getOmega() {
		return omega;
	}
	/** Setter for omega */
	public void setOmega(String omega) {
		this.omega = omega;
	}
	/** Getter for delta */
	public String getDelta() {
		return delta;
	}
	/** Setter for delta */
	public void setDelta(String delta) {
		this.delta = delta;
	}
	/** Getter for theta */
	public String getTheta() {
		return theta;
	}
	/** Setter for theta */
	public void setTheta(String theta) {
		this.theta = theta;
	}
	/** Getter for uuid */
	public String getUUID() {
		return uuid;
	}
	/** Setter for uuid */
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	/** Getter for isLastTuna, can be used by consumer to detect end of buffer */
	public boolean isLastTuna() {
		return isLastTuna;
	}
	/** Setter for isLastTuna, can be used by producer when placing last Tuna into buffer */
	public void setLastTuna(boolean isLastTuna) {
		this.isLastTuna = isLastTuna;
	}
	
	/** Overridden toString() to provide formatting for console output. */
	@Override
	public String toString() {
		return String.format("%d, %d, %s, %s, %s, %s", id, recordNumber, omega, delta, theta, uuid);
	}
}
