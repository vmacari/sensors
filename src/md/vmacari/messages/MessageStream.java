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
public class MessageStream extends MessageGeneric <MessageStreamSubtypes> {
 
    /**
     * 
     * @param nodeId
     * @param childSensorId
     * @param messageType
     * @param requestAck
     * @param subType
     * @param paylod 
     */
    public MessageStream(int nodeId, int childSensorId, MessageTypes messageType, boolean requestAck, MessageStreamSubtypes subType, String paylod) {
        super(nodeId, childSensorId, messageType, requestAck, subType, paylod);
    }

    @Override
    public String toString() {
        String baseStr = super.toString();
        return new StringBuilder(baseStr).append("\nMessageStream ")
                .toString();
    }       
}
