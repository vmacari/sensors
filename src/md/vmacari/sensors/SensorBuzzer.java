/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.sensors;

import md.vmacari.sensors.data.BuzzerData;
import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageSensorValues;

/**
 *  frequency of the signal
 * @author vmacari
 */
public class SensorBuzzer extends BaseSensor<BuzzerData>{

    public SensorBuzzer(int id) {
        super(id, MessagePresentationSubtypes.S_CUSTOM, new BuzzerData(0, 0));
    }
    
}
