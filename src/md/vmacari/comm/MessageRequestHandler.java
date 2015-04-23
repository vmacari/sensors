/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.comm;

import javatestrfgateway.GwLogger;
import javatestrfgateway.MessageReader;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessagePresentation;
import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageRequest;

/**
 *      Handle presentation message
 * @author vmacari
 */
public class MessageRequestHandler implements PacketReceiverListener {

    @Override
    public void onDataPacketReceived(MessageReader reader, MessageGeneric dataPacket) {
        
        if (dataPacket.getMessageType() != MessageGeneric.MessageTypes.Presentation || 
            ! (dataPacket instanceof MessagePresentation)) {
            return; // not a presentation message
        }
        
        GwLogger.i("Handle request message ");
        MessageRequest message = (MessageRequest) dataPacket;
            
    }
}
