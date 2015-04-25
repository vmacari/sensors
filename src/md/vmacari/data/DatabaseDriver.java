/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.data;

import java.util.ArrayList;
import java.util.List;
import md.vmacari.main.GwLogger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageSensorValues;

/**
 *
 * @author vmacari
 */
public class DatabaseDriver {

    private  EntityManager entityManager;
    private static DatabaseDriver _instance = null;
    
    private DatabaseDriver () {
        entityManager = Persistence.createEntityManagerFactory(
                "JavaTestRFGatewayPU").createEntityManager();
    }
    
    /**
     * 
     * @return 
     */
    private static DatabaseDriver getInstance () {
        if (_instance == null) {
            _instance = new DatabaseDriver();
        }
        return _instance;
    }
    
    /**
     * Retrieve all nodes from database
     * @return 
     */
    public static List<Node> getAllNodes () {
        Query query = getInstance().entityManager.createNamedQuery("Node.findAll");
        return query.getResultList();
    }
    
    /**
     *  Get next available node Id 
     * TODO: Implement some logic to delete old nodes (somehow) ...
     * @return 
     */
    public static  short getNextAvailabelNodId() {

        try {
            Query query = getInstance().entityManager.createNamedQuery("Node.findAll");
            List<Node> resultList = query.getResultList();
            List<Integer> existingNodeIds = new ArrayList<Integer> ();

            for (Node c : resultList) {
                existingNodeIds.add(new Integer(c.getId()));
            }

            for (short i = 1; i< 255; i ++) {
                if (!existingNodeIds.contains(new Integer(i))) {
                    return i;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    
    /**
     * 
     * @param nodeId
     * @param name
     * @return 
     */    
    public static Node addOrUpdateNewNodeId (short nodeId, String name) {
    
        getInstance().entityManager.getTransaction().begin();
        
        Query query = getInstance().entityManager.createNamedQuery("Node.findById");
        query.setParameter("id", nodeId);
        List<Node> resultList = query.getResultList();

        Node nodeEntity = null;

        if (resultList == null || resultList.size() == 0) {
            nodeEntity = new Node();
            nodeEntity.setId(nodeId);
            nodeEntity.setName(name);
            getInstance().entityManager.persist(nodeEntity);
        } else {
            nodeEntity = resultList.get(0);
        }
        
        getInstance().entityManager.getTransaction().commit();
        return nodeEntity;
    }
    
    /**
     * 
     * @param nodeId
     * @param childSensorId
     * @param payload 
     */
    public static void saveBatteryLevel(int nodeId, int childSensorId, String payload) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void sendConfig(int nodeId, int childSensorId, String payload) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void saveSketchName(int nodeId, int childSensorId, String payload) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving sketch name %s", payload);
    }

    public static void saveSketchVersion(int nodeId, int childSensorId, String payload) {
       //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving sketch version %s", payload);
    }

    public static void saveValue(int nodeId, int childSensorId, MessageSensorValues subType, String payload) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void saveProtocol(int nodeId, String payload) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving protocol %s", payload);
    }

    public static void saveNodeSensor(int nodeId, int childSensorId, MessageGeneric.MessageTypes messageType) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving node sensor %d : %d - %s", nodeId, childSensorId, messageType);
    }

    public static void sendFirmwareConfigResponse(int nodeId, short fwType, short fwVersion) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void sendFirmwareResponse(int nodeId, short fwType, short fwVersion, short fwBlock) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
