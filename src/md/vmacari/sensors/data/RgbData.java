/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.sensors.data;

/**
 *  PWM (analog) values for light intensity
 * @author vmacari
 */
public class RgbData {

    
    private final int r;
    private final int g;
    private final int b;
    
    public RgbData(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
 
    public int getB() {
        return b;
    }

    public int getG() {
        return g;
    }

    public int getR() {
        return r;
    }

}
