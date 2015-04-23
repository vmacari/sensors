/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.devices;


import java.util.List;
import md.vmacari.sensors.BaseSensor;

/**
 * Base sensor device information.
 *  A device is a custom user radio module with a set of sensors on it. 
 *  For example, a device can have a set of lights, or few sensors.
 *  An example of easiest device is a radio module with a humidity or pressure 
 *  sensor. These sensors also can measure temperature, hence the module 
 *  will have attached 2 sensors: humidity/pressure and temperature.
 * 
 * @author vmacari
 * @param <T> - a type of BaseSensor
 */
public class BaseDevice {
    
    protected int id;
    protected List<BaseSensor> sensors;
    protected String name;
    
}
