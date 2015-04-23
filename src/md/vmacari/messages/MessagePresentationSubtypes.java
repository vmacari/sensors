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
public enum MessagePresentationSubtypes {
        S_DOOR,         //	0	Door and window sensors
        S_MOTION,       //	1	Motion sensors
        S_SMOKE,        //	2	Smoke sensor
        S_LIGHT,        //	3	Light Actuator (on/off)
        S_DIMMER,       //	4	Dimmable device of some kind
        S_COVER,        //	5	Window covers or shades
        S_TEMP,         //	6	Temperature sensor
        S_HUM,          //	7	Humidity sensor
        S_BARO,         //	8	Barometer sensor (Pressure)
        S_WIND,         //	9	Wind sensor
        S_RAIN,         //	10	Rain sensor
        S_UV,           //	11	UV sensor
        S_WEIGHT,       //	12	Weight sensor for scales etc.
        S_POWER,        //	13	Power measuring device, like power meters
        S_HEATER,       //	14	Heater device
        S_DISTANCE,     //	15	Distance sensor
        S_LIGHT_LEVEL,  //	16	Light sensor
        S_ARDUINO_NODE, //	17	Arduino node device
        S_ARDUINO_RELAY,//	18	Arduino repeating node device
        S_LOCK,         //	19	Lock device
        S_IR,           //	20	Ir sender/receiver device
        S_WATER,        //	21	Water meter
        S_AIR_QUALITY,  //	22	Air quality sensor e.g. MQ-2
        S_CUSTOM,       //	23	Use this for custom sensors where no other fits.
        S_DUST,         //	24	Dust level sensor
        S_SCENE_CONTROLLER;         //	25	Scene controller device        
                
        /**
         * 
         */
        private final static MessagePresentationSubtypes [] enumValues = MessagePresentationSubtypes.values();
        
        /**
         * 
         * @param value
         * @return 
         */
        public static MessagePresentationSubtypes parseInteger(Integer value) {
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
        public static Integer toInteger (MessagePresentationSubtypes value) {
            for (int i =0; i < enumValues.length; i ++ ) {
                if (enumValues[i] == value) {
                    return i;
                }
            }
            
            return -1;
        }    
}
