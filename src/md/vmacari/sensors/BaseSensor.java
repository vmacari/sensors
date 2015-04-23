/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 */
package md.vmacari.sensors;

import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageRequest;

/**
 *
 * @author vmacari
 */
public class BaseSensor <T>{
    
    
    private int id;
    private MessagePresentationSubtypes type;
    private String name;
    private String version;
    private T value;
    
  
    public BaseSensor(int id, MessagePresentationSubtypes type, T defaultValue ) {
        this.type = type;
        this.value = defaultValue;
    }
    
    /**
     * 
     * @param value 
     */
    public void set(T value) { this.value = value;}
    
    /**
     * 
     * @return 
     */
    public T get() { return this.value;}
    
}
