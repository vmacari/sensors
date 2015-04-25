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
import md.vmacari.messages.MessageStream;
import md.vmacari.messages.MessageStreamSubtypes;

/**
 *      Handle presentation message
 * @author vmacari
 */
public class MessageStreamHandler implements PacketReceiverListener {

    @Override
    public void onDataPacketReceived(MessageReader reader, MessageGeneric dataPacket) {
        
        if (dataPacket.getMessageType() != MessageGeneric.MessageTypes.Presentation || 
            ! (dataPacket instanceof MessagePresentation)) {
            return; // not a presentation message
        }
        
        GwLogger.i("Handle stream message ");
        MessageStream message = (MessageStream) dataPacket;

        String payload = message.getPayload();

        if (message.getSubType() == MessageStreamSubtypes.ST_FIRMWARE_CONFIG_REQUEST) {
            if (payload != null && payload.length() >= 4) {
                short fwType = string2word(payload.substring(0, 1));
                short fwVersion = string2word(payload.substring(2, 3));
            
                DatabaseDriver.sendFirmwareConfigResponse( message.getNodeId(), fwType, fwVersion);
            }
        }
        else if (message.getSubType() == MessageStreamSubtypes.ST_FIRMWARE_CONFIG_RESPONSE) {
            if (payload != null && payload.length() >= 4) {
                short fwType = string2word(payload.substring(0, 1));
                short fwVersion = string2word(payload.substring(2, 3));
                short fwBlock = string2word(payload.substring(4, 5));
                
            
                DatabaseDriver.sendFirmwareResponse(message.getNodeId(), fwType, fwVersion, fwBlock);
            }
        }
        else if (message.getSubType() == MessageStreamSubtypes.ST_FIRMWARE_RESPONSE) {
            
        }
        else if (message.getSubType() == MessageStreamSubtypes.ST_SOUND) {
            
        }
        else if (message.getSubType() == MessageStreamSubtypes.ST_IMAGE) {
            
        }
        
    }

    private short string2word(String substring) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
