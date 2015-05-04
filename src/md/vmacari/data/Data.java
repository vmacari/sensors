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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d"),
    @NamedQuery(name = "Data.findById", query = "SELECT d FROM Data d WHERE d.id = :id"),
    @NamedQuery(name = "Data.findByNodeId", query = "SELECT d FROM Data d WHERE d.nodeId = :nodeId"),
    @NamedQuery(name = "Data.findBySensorId", query = "SELECT d FROM Data d WHERE d.sensorId = :sensorId"),
    @NamedQuery(name = "Data.findByDataType", query = "SELECT d FROM Data d WHERE d.dataType = :dataType"),
    @NamedQuery(name = "Data.findByData", query = "SELECT d FROM Data d WHERE d.data = :data"),
    @NamedQuery(name = "Data.findByTime", query = "SELECT d FROM Data d WHERE d.time = :time")})
public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NODE_ID")
    private Short nodeId;
    @Column(name = "SENSOR_ID")
    private Short sensorId;
    @Basic(optional = false)
    @Column(name = "DATA_TYPE", nullable = false, length = 50)
    private String dataType;
    @Basic(optional = false)
    @Column(name = "DATA", nullable = false, length = 100)
    private String data;
    @Basic(optional = false)
    @Column(name = "TIME", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date time;

    public Data() {
    }

    public Data(Integer id) {
        this.id = id;
    }

    public Data(Integer id, String dataType, String data, Date time) {
        this.id = id;
        this.dataType = dataType;
        this.data = data;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNodeId() {
        return nodeId;
    }

    public void setNodeId(Short nodeId) {
        this.nodeId = nodeId;
    }

    public Short getSensorId() {
        return sensorId;
    }

    public void setSensorId(Short sensorId) {
        this.sensorId = sensorId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
