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
@Table(name = "NODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Node.findAll", query = "SELECT n FROM Node n"),
    @NamedQuery(name = "Node.findByName", query = "SELECT n FROM Node n WHERE n.name = :name"),
    @NamedQuery(name = "Node.findByVersion", query = "SELECT n FROM Node n WHERE n.version = :version"),
    @NamedQuery(name = "Node.findByLastUpdateTime", query = "SELECT n FROM Node n WHERE n.lastUpdateTime = :lastUpdateTime"),
    @NamedQuery(name = "Node.findByFwVersion", query = "SELECT n FROM Node n WHERE n.fwVersion = :fwVersion"),
    @NamedQuery(name = "Node.findByBatteryLevel", query = "SELECT n FROM Node n WHERE n.batteryLevel = :batteryLevel"),
    @NamedQuery(name = "Node.findByConfiguration", query = "SELECT n FROM Node n WHERE n.configuration = :configuration"),
    @NamedQuery(name = "Node.findByIsRebooting", query = "SELECT n FROM Node n WHERE n.isRebooting = :isRebooting"),
    @NamedQuery(name = "Node.findById", query = "SELECT n FROM Node n WHERE n.id = :id")})
public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
    @Column(name = "VERSION", length = 20)
    private String version;
    @Column(name = "LAST_UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;
    @Column(name = "FW_VERSION", length = 20)
    private String fwVersion;
    @Column(name = "BATTERY_LEVEL")
    private Short batteryLevel;
    @Column(name = "CONFIGURATION", length = 200)
    private String configuration;
    @Column(name = "IS_REBOOTING")
    private Boolean isRebooting;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Short id;

    public Node() {
    }

    public Node(Short id) {
        this.id = id;
    }

    public Node(Short id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public Short getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Short batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public Boolean getIsRebooting() {
        return isRebooting;
    }

    public void setIsRebooting(Boolean isRebooting) {
        this.isRebooting = isRebooting;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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
        if (!(object instanceof Node)) {
            return false;
        }
        Node other = (Node) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "md.vmacari.comm.Node[ id=" + id + " ]";
    }
    
}
