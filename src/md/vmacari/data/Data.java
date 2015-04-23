/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vmacari
 */
@Entity
@Table(name = "DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d"),
    @NamedQuery(name = "Data.findById", query = "SELECT d FROM Data d WHERE d.id = :id"),
    @NamedQuery(name = "Data.findByNodeId", query = "SELECT d FROM Data d WHERE d.nodeId = :nodeId"),
    @NamedQuery(name = "Data.findBySensorId", query = "SELECT d FROM Data d WHERE d.sensorId = :sensorId"),
    @NamedQuery(name = "Data.findBySensorDataType", query = "SELECT d FROM Data d WHERE d.sensorDataType = :sensorDataType"),
    @NamedQuery(name = "Data.findBySensorData", query = "SELECT d FROM Data d WHERE d.sensorData = :sensorData"),
    @NamedQuery(name = "Data.findByDataType", query = "SELECT d FROM Data d WHERE d.dataType = :dataType")})
public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NODE_ID", nullable = false)
    private short nodeId;
    @Column(name = "SENSOR_ID")
    private Short sensorId;
    @Basic(optional = false)
    @Column(name = "SENSOR_DATA_TYPE", nullable = false)
    private short sensorDataType;
    @Column(name = "SENSOR_DATA", length = 50)
    private String sensorData;
    @Column(name = "DATA_TYPE")
    private Short dataType;

    public Data() {
    }

    public Data(Long id) {
        this.id = id;
    }

    public Data(Long id, short nodeId, short sensorDataType) {
        this.id = id;
        this.nodeId = nodeId;
        this.sensorDataType = sensorDataType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getNodeId() {
        return nodeId;
    }

    public void setNodeId(short nodeId) {
        this.nodeId = nodeId;
    }

    public Short getSensorId() {
        return sensorId;
    }

    public void setSensorId(Short sensorId) {
        this.sensorId = sensorId;
    }

    public short getSensorDataType() {
        return sensorDataType;
    }

    public void setSensorDataType(short sensorDataType) {
        this.sensorDataType = sensorDataType;
    }

    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }

    public Short getDataType() {
        return dataType;
    }

    public void setDataType(Short dataType) {
        this.dataType = dataType;
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
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "md.vmacari.data.Data[ id=" + id + " ]";
    }
    
}
