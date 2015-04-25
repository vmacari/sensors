/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.comm;

import md.vmacari.main.GwLogger;
import md.vmacari.main.MessageReader;
import md.vmacari.data.DatabaseDriver;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessagePresentation;
import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageSet;

/**
 *      Handle presentation message
 * @author vmacari
 */
public class MessageSetHandler implements PacketReceiverListener {

    @Override
    public void onDataPacketReceived(MessageReader reader, MessageGeneric dataPacket) {
        
        if (dataPacket.getMessageType() != MessageGeneric.MessageTypes.Presentation || 
            ! (dataPacket instanceof MessagePresentation)) {
            return; // not a presentation message
        }
        
        GwLogger.i("Handle presentation message ");
        MessageSet message = (MessageSet) dataPacket;

        DatabaseDriver.saveValue(
                message.getNodeId(), 
                message.getChildSensorId(),
                message.getSubType(), 
                message.getPayload());
        
//        if (message.getSubType() == MessagePresentationSubtypes.S_LIGHT) {
//            
//        }

    }
    
}
