/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vmacari
 */
@Entity
@Table(name = "LOGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findById", query = "SELECT l FROM Logs l WHERE l.id = :id"),
    @NamedQuery(name = "Logs.findByNodeId", query = "SELECT l FROM Logs l WHERE l.nodeId = :nodeId"),
    @NamedQuery(name = "Logs.findBySensorId", query = "SELECT l FROM Logs l WHERE l.sensorId = :sensorId"),
    @NamedQuery(name = "Logs.findByMessage", query = "SELECT l FROM Logs l WHERE l.message = :message"),
    @NamedQuery(name = "Logs.findByTime", query = "SELECT l FROM Logs l WHERE l.time = :time")})
public class Logs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NODE_ID")
    private Boolean nodeId;
    @Column(name = "SENSOR_ID")
    private Short sensorId;
    @Basic(optional = false)
    @Column(name = "MESSAGE", nullable = false, length = 200)
    private String message;
    @Basic(optional = false)
    @Column(name = "TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Logs() {
    }

    public Logs(Long id) {
        this.id = id;
    }

    public Logs(Long id, String message, Date time) {
        this.id = id;
        this.message = message;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getNodeId() {
        return nodeId;
    }

    public void setNodeId(Boolean nodeId) {
        this.nodeId = nodeId;
    }

    public Short getSensorId() {
        return sensorId;
    }

    public void setSensorId(Short sensorId) {
        this.sensorId = sensorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "md.vmacari.data.Logs[ id=" + id + " ]";
    }
    
}
