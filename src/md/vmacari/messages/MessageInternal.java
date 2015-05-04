/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.messages;

import java.util.Date;

/**
 *
 * @author vmacari
 */
public class MessageInternal extends MessageGeneric<MessageInternalSubtypes> {

    public static MessageInternal createGetIdResponse(MessageInternal message, int nextAvailableId) {
        return new MessageInternal(
                255, 
                message.getChildSensorId(), 
                MessageTypes.Internal, 
                false, 
                MessageInternalSubtypes.I_ID_RESPONSE, 
                String.valueOf(nextAvailableId));
    }

    /**
     *  Get time message
     * @param message
     * @return 
     */
    public static MessageInternal createGetTimeResponse(MessageInternal message) {
        return new MessageInternal(
                message.getNodeId(), 
                message.getChildSensorId(), 
                MessageTypes.Internal, 
                false, 
                MessageInternalSubtypes.I_TIME, 
                String.valueOf(new Date().getTime()));
    }

    public static MessageInternal createGetConfig(MessageInternal message) {
        return new MessageInternal(
            message.getNodeId(), 
            message.getChildSensorId(), 
            MessageTypes.Internal, 
            false, 
            MessageInternalSubtypes.I_CONFIG,
            "M" // metric configuration
        );
    }

    /**
     * 
     * @param nodeId
     * @param childSensorId
     * @param messageType
     * @param requestAck
     * @param subType
     * @param paylod 
     */
    public MessageInternal(int nodeId, int childSensorId, MessageTypes messageType, 
            boolean requestAck, MessageInternalSubtypes subType, String paylod) {
        super(nodeId, childSensorId, messageType, requestAck, subType, paylod);
    }
    

    @Override
    public String toString() {
        String baseStr = super.toString();
        return new StringBuilder(baseStr).append("\nMessageInternal ")
                .toString();
    }  
    
    public String toMessageString() {
        return super.toMessageString(MessageInternalSubtypes.toInteger(
                (MessageInternalSubtypes)getSubType()));
    }
    
}
