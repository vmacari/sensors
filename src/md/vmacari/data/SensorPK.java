/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vmacari
 */
@Embeddable
public class SensorPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic(optional = false)
    @Column(name = "NODE_ID", nullable = false)
    private short nodeId;

    public SensorPK() {
    }

    public SensorPK(int id, short nodeId) {
        this.id = id;
        this.nodeId = nodeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getNodeId() {
        return nodeId;
    }

    public void setNodeId(short nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) nodeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SensorPK)) {
            return false;
        }
        SensorPK other = (SensorPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.nodeId != other.nodeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "md.vmacari.data.SensorPK[ id=" + id + ", nodeId=" + nodeId + " ]";
    }
    
}
