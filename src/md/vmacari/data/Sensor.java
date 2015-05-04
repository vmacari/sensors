/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vmacari
 */
@Entity
@Table(name = "SENSOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensor.findAll", query = "SELECT s FROM Sensor s"),
    @NamedQuery(name = "Sensor.findById", query = "SELECT s FROM Sensor s WHERE s.sensorPK.id = :id"),
    @NamedQuery(name = "Sensor.findByNodeId", query = "SELECT s FROM Sensor s WHERE s.sensorPK.nodeId = :nodeId"),
    @NamedQuery(name = "Sensor.findByName", query = "SELECT s FROM Sensor s WHERE s.name = :name"),
    @NamedQuery(name = "Sensor.findByType", query = "SELECT s FROM Sensor s WHERE s.type = :type")})
public class Sensor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SensorPK sensorPK;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;
    @Basic(optional = false)
    @Column(name = "TYPE", nullable = false)
    private short type;

    public Sensor() {
    }

    public Sensor(SensorPK sensorPK) {
        this.sensorPK = sensorPK;
    }

    public Sensor(SensorPK sensorPK, String name, short type) {
        this.sensorPK = sensorPK;
        this.name = name;
        this.type = type;
    }

    public Sensor(int id, short nodeId) {
        this.sensorPK = new SensorPK(id, nodeId);
    }

    public SensorPK getSensorPK() {
        return sensorPK;
    }

    public void setSensorPK(SensorPK sensorPK) {
        this.sensorPK = sensorPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sensorPK != null ? sensorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensor)) {
            return false;
        }
        Sensor other = (Sensor) object;
        if ((this.sensorPK == null && other.sensorPK != null) || (this.sensorPK != null && !this.sensorPK.equals(other.sensorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "md.vmacari.comm.Sensor[ sensorPK=" + sensorPK + " ]";
    }
    
}
