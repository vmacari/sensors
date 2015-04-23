/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.messages;

/**
 *
 * @author vmacari
 */
public class MessageGeneric <T> {
    private int nodeId;
    private int childSensorId;
    private MessageTypes messageType;
    private final boolean requestAck;
    private T subType;
    private String paylod;

    /**
     * @return the nodeId
     */
    public int getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the childSensorId
     */
    public int getChildSensorId() {
        return childSensorId;
    }

    /**
     * @param childSensorId the childSensorId to set
     */
    public void setChildSensorId(int childSensorId) {
        this.childSensorId = childSensorId;
    }

    /**
     * @return the messageType
     */
    public MessageTypes getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(MessageTypes messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the subType
     */
    public T getSubType() {
        return subType;
    }

    /**
     * @param subType the subType to set
     */
    public void setSubType(T     subType) {
        this.subType = subType;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return paylod;
    }

    /**
     * @param paylod the payload to set
     */
    public void setPayload(String paylod) {
        this.paylod = paylod;
    }

    /**
     * @return the requestAck
     */
    public boolean isRequestAck() {
        return requestAck;
    }


    public  enum MessageTypes {
        Presentation,
        Set,
        Request,
        Internal,
        Stream;
        
        
        public static MessageTypes parseInt (int messageType) {
            switch (messageType) {
                case 0: return MessageTypes.Presentation;
                case 1: return MessageTypes.Set;
                case 2: return MessageTypes.Request;
                case 3: return MessageTypes.Internal;
                case 4: return MessageTypes.Stream;
                default: {
                    System.out.print("Unknonw message type!");
                    return null;
                }
            }
        }
        
        public static Integer toInteger (MessageTypes messageType) {
            if ( messageType == MessageTypes.Presentation) return 0;
            else if ( messageType == MessageTypes.Set) return 1;
            else if ( messageType == MessageTypes.Request) return 2;
            else if ( messageType == MessageTypes.Internal) return 3;
            else if ( messageType == MessageTypes.Stream) return 4;
            else  return -1;
        }
    }

    /**
     *  Initialization constructor
     * @param nodeId
     * @param childSensorId
     * @param messageType
     * @param requestAck
     * @param subType
     * @param paylod 
     */
    public MessageGeneric(int nodeId, int childSensorId, MessageTypes messageType,
            boolean requestAck, T subType, String paylod) {
        
        this.nodeId = nodeId;
        this.childSensorId = childSensorId;
        this.messageType = messageType;
        this.requestAck = requestAck;
        this.subType = subType;
        this.paylod = paylod;
    }
 
    @Override
    public String toString () {
        return new StringBuilder ("GenericMessage :")
                .append("nodeId ").append(nodeId)
                .append(", childSensorId ").append(childSensorId)
                .append(", messageType ").append(messageType)
                .append(", requestAck ").append(isRequestAck())
                .append(", subType ").append(subType)
                .append(", paylod ").append(paylod)
                .toString();
    }
    
    
    public String toMessageString (Integer subtype) {
        
        StringBuilder sb = new StringBuilder();

        sb.append(String.valueOf(nodeId)).append(";");
        sb.append(String.valueOf(childSensorId)).append(";");
        sb.append(String.valueOf(MessageTypes.toInteger(messageType))).append(";");  // command
        sb.append(String.valueOf(requestAck ? "1" : "0")).append(";");
        sb.append(String.valueOf(subtype)).append(";");

        
        if (messageType == MessageTypes.Stream) {
            
//		for (var i = 0; i < payload.length; i++) {
//			if (payload[i] < 16)
//				msg += "0";
//			msg += payload[i].toString(16);
//		}
	} else {
            sb.append(paylod);
	}

        sb.append("\n");
	return sb.toString();
    }

    
}
