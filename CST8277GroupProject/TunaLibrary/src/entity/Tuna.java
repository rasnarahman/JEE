/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author krish
 */
@Entity
public class Tuna implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * recordNumber for database, originally matched a dataset file line number
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long recordNumber;

    /**
     * Tuna Omega value in string with MySQL column size of 42 characters
     */
    private String omega;

    public Long getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(Long recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getOmega() {
        return omega;
    }

    public void setOmega(String omega) {
        this.omega = omega;
    }

    public String getDelta() {
        return delta;
    }

    public void setDelta(String delta) {
        this.delta = delta;
    }

    public String getTheta() {
        return theta;
    }

    public void setTheta(String theta) {
        this.theta = theta;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isIsLastTuna() {
        return isLastTuna;
    }

    public void setIsLastTuna(boolean isLastTuna) {
        this.isLastTuna = isLastTuna;
    }

    /**
     * Tuna Delta value in string with MySQL column size of 42 characters
     */
    private String delta;

    /**
     * Tuna Theta value in string with MySQL column size of 42 characters
     */
    private String theta;

    /**
     * Randomly generated uuid field with MySQL column size of 42 characters
     */
    private String uuid;

    /**
     * Indicator to find the lastTuna in the list
     */
    private boolean isLastTuna;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordNumber != null ? recordNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tuna)) {
            return false;
        }
        Tuna other = (Tuna) object;
        if ((this.recordNumber == null && other.recordNumber != null) || (this.recordNumber != null && !this.recordNumber.equals(other.recordNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tuna[ id=" + recordNumber + " ]";
    }

}
