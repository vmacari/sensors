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
public class MessagePresentation extends MessageGeneric <MessagePresentationSubtypes> {
    
   
    /**
     * 
     * @param nodeId
     * @param childSensorId
     * @param messageType
     * @param requestAck
     * @param subType
     * @param paylod 
     */
    public MessagePresentation(int nodeId, int childSensorId, MessageTypes messageType,
            boolean requestAck, MessagePresentationSubtypes subType, String paylod) {
        super(nodeId, childSensorId, messageType, requestAck, subType, paylod);
    }

    @Override
    public String toString() {
        String baseStr = super.toString();
        return new StringBuilder(baseStr).append("\nMessagePresentation ")
                .toString();
    }  
}
