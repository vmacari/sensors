/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.comm;

import java.util.Date;
import md.vmacari.main.GwLogger;
import md.vmacari.main.MessageReader;
import services.DatabaseService;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessageInternal;
import md.vmacari.messages.MessageInternalSubtypes;

/**
 *
 * @author vmacari
 */
public class MessageInternalHadler implements PacketReceiverListener{

    
    @Override
    public void onDataPacketReceived(MessageReader reader, MessageGeneric dataPacket) {
        
        if (dataPacket.getMessageType() != MessageGeneric.MessageTypes.Internal || 
                ! (dataPacket instanceof MessageInternal)) {
            return; // not an internal message
        }
        
        GwLogger.i("Handle internal message ");
        MessageInternal message = (MessageInternal) dataPacket;
        
        if (message.getSubType() == MessageInternalSubtypes.I_ID_REQUEST) {

            
            
            GwLogger.i("Node is requesting and ID %s : %s", message.getNodeId(), message.getPayload());
            short nextAvailableId = DatabaseService.getNextAvailabelNodId();
            
            if (nextAvailableId == -1) {
                GwLogger.e("Cannot get a new node ID, db is full ");
                return;
            }
            
            DatabaseService.addOrUpdateNewNodeId(nextAvailableId, "-not set-", "-version-");
            // https://github.com/mysensors/Arduino/blob/master/NodeJsController/NodeJsController.js
            
            reader.writeString(MessageInternal.createGetIdResponse(message, 
                    nextAvailableId).toMessageString());
        }   
        
        else if (message.getSubType() == MessageInternalSubtypes.I_LOG_MESSAGE) {
           GwLogger.i("Log message received from %s : %s", message.getNodeId(), message.getPayload());
           DatabaseService.addLogMessage(message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_BATTERY_LEVEL) {
           GwLogger.i("I_BATTERY_LEVEL: Use this to report the battery level "
                   + "(in percent 0-100) %s : %s", message.getNodeId(), message.getPayload());
           DatabaseService.saveBatteryLevel(message.getNodeId(), message.getChildSensorId(), 
                   message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_TIME) {
           GwLogger.i("I_TIME: Sensors can request the current time from the "
                   + "Controller using this message. The time will be reported "
                   + "as the seconds since 1970 %s : %s",
                   message.getNodeId(), message.getPayload());
           
           reader.writeString(MessageInternal.createGetTimeResponse(
                   message).toMessageString());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_VERSION) {
           GwLogger.i("I_VERSION: Sensors report their library version at "
                   + "startup using this message type %s : %s", 
                   message.getNodeId(), message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_INCLUSION_MODE) {
           GwLogger.i("I_INCLUSION_MODE: Start/stop inclusion mode of the "
                   + "Controller (1=start, 0=stop). %s : %s", 
                   message.getNodeId(), message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_ID_RESPONSE) {
           GwLogger.i("I_ID_RESPONSE: Id response back to sensor. Payload "
                   + "contains sensor id.. %s : %s", message.getNodeId(), 
                   message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_CONFIG) {
           GwLogger.i("I_CONFIG: Config request from node. Reply with (M)etric "
                   + "or (I)mperal back to sensor. %s : %s", 
                   message.getNodeId(), message.getPayload());
           
            reader.writeString(MessageInternal.createGetConfig(
                   message).toMessageString());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_FIND_PARENT) {
           GwLogger.i("I_FIND_PARENT: When a sensor starts up, it broadcast a "
                   + "search request to all neighbor nodes. They reply with a "
                   + "I_FIND_PARENT_RESPONSE. %s : %s", 
                   message.getNodeId(), message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_FIND_PARENT_RESPONSE) {
           GwLogger.i("I_FIND_PARENT_RESPONSE: Reply message type to "
                   + "I_FIND_PARENT request. %s : %s", message.getNodeId(), 
                   message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_CHILDREN) {
           GwLogger.i("I_CHILDREN:A message that can be used to transfer child "
                   + "sensors (from EEPROM routing table) of a repeating node. "
                   + "%s : %s", message.getNodeId(), message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_SKETCH_NAME) {
            GwLogger.i("I_SKETCH_NAME: Optional sketch name that can be used to "
                   + "identify sensor in the Controller GUI. %s : %s", 
                   message.getNodeId(), message.getPayload());
           
            DatabaseService.saveSketchName(message.getNodeId(), message.getChildSensorId(), 
                   message.getPayload());

        }
        else if (message.getSubType() == MessageInternalSubtypes.I_SKETCH_VERSION) {
            GwLogger.i("I_SKETCH_VERSION:Optional sketch version that can be "
                   + "reported to keep track of the version of sensor in the "
                   + "Controller GUI %s : %s", message.getNodeId(), message.getPayload());
 
            DatabaseService.saveSketchVersion(message.getNodeId(), message.getChildSensorId(), 
                   message.getPayload());

        }
        else if (message.getSubType() == MessageInternalSubtypes.I_REBOOT) {
            
            GwLogger.i("I_REBOOT: Used by OTA firmware updates. Request for node "
                   + "to reboot. %s : %s", message.getNodeId(), message.getPayload());
        }
        else if (message.getSubType() == MessageInternalSubtypes.I_GATEWAY_READY) {
           GwLogger.i("I_GATEWAY_READY: Send by gateway to controller when "
                   + "startup is complete. %s : %s", message.getNodeId(), message.getPayload());
        }
        else {
           GwLogger.i("Unknonwn internal message received %s : %s", 
                   message.getNodeId(), message.getPayload());
        }
    }
  
}
