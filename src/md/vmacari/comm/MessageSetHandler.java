/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.comm;

import md.vmacari.main.GwLogger;
import md.vmacari.main.MessageReader;
import services.DatabaseService;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessageSet;

/**
 *      Handle presentation message
 * @author vmacari
 */
public class MessageSetHandler implements PacketReceiverListener {

    @Override
    public void onDataPacketReceived(MessageReader reader, MessageGeneric dataPacket) {
        
        if (dataPacket.getMessageType() != MessageGeneric.MessageTypes.Set || 
            ! (dataPacket instanceof MessageSet)) {
            return; // not a presentation message
        }
        
        GwLogger.i("Handle set message ");
        MessageSet message = (MessageSet) dataPacket;

        DatabaseService.saveValue(
                message.getNodeId(), 
                message.getChildSensorId(),
                message.getSubType(), 
                message.getPayload());
        
//        if (message.getSubType() == MessagePresentationSubtypes.S_LIGHT) {
//            
//        }

    }
    
}
