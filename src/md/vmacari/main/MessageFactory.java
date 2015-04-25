/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.main;

import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessageGeneric.MessageTypes;
import md.vmacari.messages.MessageInternal;
import md.vmacari.messages.MessageInternalSubtypes;
import md.vmacari.messages.MessagePresentation;
import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageRequest;
import md.vmacari.messages.MessageSensorValues;
import md.vmacari.messages.MessageSet;
import md.vmacari.messages.MessageStream;
import md.vmacari.messages.MessageStreamSubtypes;

/**
 *
 * @author vmacari
 */
public class MessageFactory {

    public static MessageGeneric getDataPacket(String stringPacket) {
        
        if (stringPacket == null || stringPacket.length() == 0) {
            return null;
        }
        
        String [] parts = stringPacket.split(";");
        if (parts == null || parts.length == 0) {
            return null;
        }
        
        System.out.println("------------- MESSAGE ---------------------");
        System.out.println(stringPacket);

        
        // node-id;child-sensor-id;message-type;ack;sub-type;payload
        String nodeId = parts [0];
        String childSensorId = parts [1];
        String messageType = parts[2];
        String ack = parts[3];
        String subType = parts[4];
        
        StringBuilder payload = new StringBuilder ();
        for (int i =  5; i < parts.length; i ++) {
            if (payload.length() > 0) {
                payload.append(";");
            }
            payload.append(parts[i]);
        }
        
        try {
            
            MessageTypes msgType = MessageTypes.parseInt(Integer.parseInt(messageType));
            if (msgType == MessageTypes.Internal) {
                
                return new MessageInternal (
                        Integer.parseInt(nodeId), 
                        Integer.parseInt(childSensorId),
                        msgType,
                        Boolean.parseBoolean(ack),
                        MessageInternalSubtypes.parseInteger(Integer.parseInt(subType)),
                        payload.toString());
                
            }
            else if (msgType == MessageTypes.Presentation) {
                return new MessagePresentation (
                        Integer.parseInt(nodeId), 
                        Integer.parseInt(childSensorId),
                        msgType,
                        Boolean.parseBoolean(ack),
                        MessagePresentationSubtypes.parseInteger(Integer.parseInt(subType)),
                        payload.toString());
                
            }
            else if (msgType == MessageTypes.Request) {
                return new MessageRequest (
                        Integer.parseInt(nodeId), 
                        Integer.parseInt(childSensorId),
                        msgType,
                        Boolean.parseBoolean(ack),
                        MessageSensorValues.parseInteger(Integer.parseInt(subType)),
                        payload.toString());
                
            }
            else if (msgType == MessageTypes.Set) {
                return new MessageSet (
                        Integer.parseInt(nodeId), 
                        Integer.parseInt(childSensorId),
                        msgType,
                        Boolean.parseBoolean(ack),
                        MessageSensorValues.parseInteger(Integer.parseInt(subType)),
                        payload.toString());
                
            }
            else if (msgType == MessageTypes.Stream) {
                return new MessageStream (
                        Integer.parseInt(nodeId), 
                        Integer.parseInt(childSensorId),
                        msgType,
                        Boolean.parseBoolean(ack),
                        MessageStreamSubtypes.parseInteger(Integer.parseInt(subType)),
                        payload.toString());
                
            }
            else {
            
                return new MessageGeneric (
                        Integer.parseInt(nodeId), 
                        Integer.parseInt(childSensorId),
                        msgType,
                        Boolean.parseBoolean(ack),
                        Integer.parseInt(subType),
                        payload.toString());
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }
    
}
