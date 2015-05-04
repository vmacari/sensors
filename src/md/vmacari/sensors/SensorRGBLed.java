/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.sensors;

import md.vmacari.sensors.data.RgbData;
import md.vmacari.messages.MessagePresentationSubtypes;

/**
 *
 * @author vmacari
 */
public class SensorRGBLed extends BaseSensor<RgbData>{

    public SensorRGBLed(int id) {
        super(id, MessagePresentationSubtypes.S_CUSTOM, new RgbData(0 ,0, 0));
    }
    
}
