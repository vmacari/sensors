/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.sensors;

import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageSensorValues;

/**
 *
 * @author vmacari
 */
public class SensorHumidity extends BaseSensor<Float>{
    public SensorHumidity(int id) {
        super(id, MessagePresentationSubtypes.S_HUM, 0f);
    }
}
