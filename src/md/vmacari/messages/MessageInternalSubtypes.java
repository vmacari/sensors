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
public enum MessageInternalSubtypes {
        I_BATTERY_LEVEL,    //	0	Use this to report the battery level (in percent 0-100).
        I_TIME,             //	1	Sensors can request the current time from the Controller using this message. The time will be reported as the seconds since 1970
        I_VERSION,          //	2	Sensors report their library version at startup using this message type
        I_ID_REQUEST,       //	3	Use this to request a unique node id from the controller.
        I_ID_RESPONSE,      //	4	Id response back to sensor. Payload contains sensor id.
        I_INCLUSION_MODE,   //	5	Start/stop inclusion mode of the Controller (1=start, 0=stop).
        I_CONFIG,           //	6	Config request from node. Reply with (M)etric or (I)mperal back to sensor.
        I_FIND_PARENT,      //	7	When a sensor starts up, it broadcast a search request to all neighbor nodes. They reply with a I_FIND_PARENT_RESPONSE.
        I_FIND_PARENT_RESPONSE, //	8	Reply message type to I_FIND_PARENT request.
        I_LOG_MESSAGE,      //	9	Sent by the gateway to the Controller to trace-log a message
        I_CHILDREN,         //	10	A message that can be used to transfer child sensors (from EEPROM routing table) of a repeating node.
        I_SKETCH_NAME,      //	11	Optional sketch name that can be used to identify sensor in the Controller GUI
        I_SKETCH_VERSION,   //	12	Optional sketch version that can be reported to keep track of the version of sensor in the Controller GUI.
        I_REBOOT,           //	13	Used by OTA firmware updates. Request for node to reboot.
        I_GATEWAY_READY;    //	14	Send by gateway to controller when startup is complete.    
        
        /**
         * 
         */
        private final static MessageInternalSubtypes [] enumValues = MessageInternalSubtypes.values();
        
        /**
         * 
         * @param value
         * @return 
         */
        public static MessageInternalSubtypes parseInteger(Integer value) {
            if (enumValues.length <= value) {
                return null;
            }
            
            return enumValues[value];
        }
        
        /**
         * 
         * @param value
         * @return 
         */
        public static Integer toInteger (MessageInternalSubtypes value) {
            for (int i =0; i < enumValues.length; i ++ ) {
                if (enumValues[i] == value) {
                    return i;
                }
            }
            
            return -1;
        }    
}
