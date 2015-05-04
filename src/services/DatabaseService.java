/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import md.vmacari.main.GwLogger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import md.vmacari.data.Data;
import md.vmacari.data.Logs;
import md.vmacari.data.Node;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageSensorValues;

/**
 *
 * @author vmacari
 */
public class DatabaseService {

    public static void addLogMessage(String message) {

       Logger.getGlobal().info("Adding log entry ...");

        getInstance().entityManager.getTransaction().begin();

        Logs logMesageEntity = new Logs(0, message, new Date());
        
        getInstance().entityManager.persist(logMesageEntity);
        getInstance().entityManager.getTransaction().commit();
        
        Logger.getGlobal().info("Added log entry " + String.valueOf(logMesageEntity.getId()));
    }

    private  EntityManager entityManager;
    private static DatabaseService _instance = null;
    
    private DatabaseService () {
        entityManager = Persistence.createEntityManagerFactory(
                "JavaTestRFGatewayPU").createEntityManager();
    }
    
    /**
     * 
     * @return 
     */
    private static DatabaseService getInstance () {
        if (_instance == null) {
            _instance = new DatabaseService();
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
    public static Node addOrUpdateNewNodeId (short nodeId, String name, String version) {
    
        getInstance().entityManager.getTransaction().begin();
        
        Query query = getInstance().entityManager.createNamedQuery("Node.findById");
        query.setParameter("id", nodeId);
        List<Node> resultList = query.getResultList();

        Node nodeEntity = null;

        if (resultList == null || resultList.size() == 0) {
            nodeEntity = new Node();
            nodeEntity.setId(nodeId);
        } else {
            nodeEntity = resultList.get(0);
        }
 
        nodeEntity.setName(name);
        nodeEntity.setVersion(version);
        
        
        getInstance().entityManager.persist(nodeEntity);
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
        GwLogger.i("saving sketch name : NodeId %d, sensor %d, payload  %s", nodeId, 
                childSensorId,  payload);
    }

    public static void saveSketchVersion(int nodeId, int childSensorId, String payload) {
       //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving sketch version : NodeId %d, sensor %d, payload  %s", nodeId, 
                childSensorId,  payload);
    }

    public static void saveValue(int nodeId, int childSensorId, MessageSensorValues subType, String payload) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("Save valu for NodeId %d, sensorId %d, ValueType %d, Payload %s", 
                nodeId, childSensorId, subType, payload);
        
        getInstance().entityManager.getTransaction().begin();

        Data dataEntity = new Data();
        
        dataEntity.setNodeId((short)nodeId);
        dataEntity.setSensorId((short)childSensorId);
        dataEntity.setDataType(subType.toString());
        dataEntity.setData(payload);
        getInstance().entityManager.persist(dataEntity);
        getInstance().entityManager.getTransaction().commit();
        
    }

    public static void saveProtocol(int nodeId, String payload) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving protocol for node: NodeId %d, Protocol %s", nodeId, payload);
    }

    public static void saveNodeSensor(int nodeId, int childSensorId, MessageGeneric.MessageTypes messageType, MessagePresentationSubtypes subType) {
        GwLogger.i("saving node sensor [NodeId: %d,   sensorId : %d,  MessageType: %s, MessageSubtype %s]", 
                nodeId, childSensorId, messageType, subType);
        
    }

    public static void sendFirmwareConfigResponse(int nodeId, short fwType, short fwVersion) {
        GwLogger.e("[sendFirmwareConfig] firmware operations not supported");
    }

    public static void sendFirmwareResponse(int nodeId, short fwType, short fwVersion, short fwBlock) {
        GwLogger.e("[sendFirmware] firmware operations not supported");
    }
}
