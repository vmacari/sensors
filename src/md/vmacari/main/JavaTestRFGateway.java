/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.main;

import java.util.*; 
import gnu.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import md.vmacari.comm.MessageInternalHadler;
import md.vmacari.comm.MessagePresentationHandler;
import md.vmacari.comm.MessageRequestHandler;
import md.vmacari.comm.MessageSetHandler;
import md.vmacari.comm.MessageStreamHandler;
import services.DatabaseService;
import md.vmacari.data.Node;


/**
 *
 * @author vmacari
 */
public class JavaTestRFGateway  {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      //final String portName = "/dev/ttyUSB0";
      final String portName = "/dev/tty.wchusbserial1420";
      
      MessageReader reader = null;
      System.out.println("Application started!");
      
      Enumeration e = CommPortIdentifier.getPortIdentifiers();

      CommPortIdentifier selectedComPortId = null;
      System.out.println("Enumerate ports ...");
      while (e.hasMoreElements()) {
            CommPortIdentifier comPortId = (CommPortIdentifier) e.nextElement();
            System.out.println(comPortId.getName());
            
             if (comPortId.getName().equals(portName)) {
                 selectedComPortId = comPortId;
             }
            
        }
        
        if (selectedComPortId != null) {
            System.out.print("Com port selected!");
            
             
            reader = new MessageReader(Arrays.asList(
                    
                                new MessageInternalHadler(), 
                                new MessagePresentationHandler(),
                                new MessageSetHandler(),
                                new MessageStreamHandler(),
                                new MessageRequestHandler()
                                
                                
                            ), 
                    selectedComPortId, 
                    115200);
            
            reader.startCommunication();
        } else {
            System.out.println("Cannot select com port!");

        }


        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new IndexPageContext());
            server.createContext("/about", new AboutPageContext());
            
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(JavaTestRFGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
            


//      int newNodeId = DatabaseService.getNextAvailabelNodId ();
//      System.out.println("New node ID " + String.valueOf(newNodeId));
//      Node entity = DatabaseService.addOrUpdateNewNodeId((short)newNodeId, "Test node ");   
//      System.out.println("New node added ! " + String.valueOf(entity.getId()));
//      
//      DatabaseService.addLogMessage("id", 1,  2, "message 1", new Date());
//      DatabaseService.addLogMessage("id", 1, 3, "message 2 ", new Date());
      
      
   }


    static class IndexPageContext implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "Started <b>SmartSensorsServer</b>, welcome";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class AboutPageContext implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "<html> <body>About <b>SmartSensorsServer</b>, welcome </body> </html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    
}
