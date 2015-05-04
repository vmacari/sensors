/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.sensors.data;

/**
 *
 * @author vmacari
 */
public class BuzzerData {
    
    private final int frequecy;
    private final int duration;
    
    public BuzzerData(int frequecy, int duration) {
        this.frequecy = frequecy;
        this.duration = duration;
    }
 
    public int getFrequecy () { return frequecy; }
    public int getDuration () { return duration; }
}
