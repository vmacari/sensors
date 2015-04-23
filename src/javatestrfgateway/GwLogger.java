/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatestrfgateway;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vmacari
 */
public class GwLogger {
    

    private static String formatMessage (String type, String message) {
        return new StringBuilder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date ()))
                .append(type)
                .append(message)
                .toString();
    }
    
    public static void i (String message) {
        //Logger.getLogger("gw").log(Level.INFO, null, ex);
        System.out.println(formatMessage(" [I]", message));
    }
    
    public static void i (String format, Object ... params) {
        System.out.println(formatMessage(" [I] ", String.format(format, params)));
    }

    public static void e (String message) {
        System.out.println(formatMessage(" [E] ", message));
    }
    
    public static void e (String format, Object ... params) {
        System.out.println(formatMessage(" [E] ", String.format(format, params)));
    }

    public static void w (String message) {
        System.out.println(formatMessage(" [W] ", message));
    }
    
    public static void w (String format, Object ... params) {
        System.out.println(formatMessage(" [W] ", String.format(format, params)));
    }
}
