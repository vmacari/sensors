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
public enum MessageStreamSubtypes {
        ST_FIRMWARE_CONFIG_REQUEST, ST_FIRMWARE_CONFIG_RESPONSE, ST_FIRMWARE_RESPONSE, ST_SOUND, ST_IMAGE;
        
        /**
         * 
         */
        private final static MessageStreamSubtypes [] enumValues = MessageStreamSubtypes.values();
        
        /**
         * 
         * @param value
         * @return 
         */
        public static MessageStreamSubtypes parseInteger(Integer value) {
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
        public static Integer toInteger (MessageStreamSubtypes value) {
            for (int i =0; i < enumValues.length; i ++ ) {
                if (enumValues[i] == value) {
                    return i;
                }
            }
            
            return -1;
        }    
}
