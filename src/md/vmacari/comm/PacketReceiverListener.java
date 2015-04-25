/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.comm;

import md.vmacari.main.MessageReader;
import md.vmacari.messages.MessageGeneric;

/**
 *
 * @author vmacari
 */
public interface PacketReceiverListener {


    public void onDataPacketReceived(MessageReader reader, MessageGeneric dp);
    
}
