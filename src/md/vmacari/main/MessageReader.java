/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.main;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.comm.PacketReceiverListener;

/**
 *
 * @author vmacari
 */
public class MessageReader implements Runnable, SerialPortEventListener {

    private final CommPortIdentifier portId;
    private final int baudrate;
    SerialPort serialPort;
    private final List<PacketReceiverListener> packetListeners = new ArrayList<PacketReceiverListener> ();
    
    InputStream inputStream;
    OutputStream outputStream;
    
    Thread readThread;
    byte[] readBuffer;
    private boolean isRunning;

    /**
     * Initialization constructor
     *
     * @param portId
     * @param baudrate
     */
    public MessageReader(List<PacketReceiverListener> packetListeners, CommPortIdentifier portId, int baudrate) {
        this.portId = portId;
        this.baudrate = baudrate;
        this.packetListeners.addAll(packetListeners);
        isRunning = false;
    }

    public boolean startCommunication() {

        try {
            System.out.println("In SimpleRead() contructor");
            serialPort = (SerialPort) portId.open("DataReader-app", 500);
            System.out.println(" Serial Port.. " + serialPort);
        } catch (PortInUseException e) {
            System.out.println("Port in use Exception");
            return false;
        }
        
        try {
            inputStream = serialPort.getInputStream();
            System.out.println(" Input Stream... " + inputStream);
        } catch (IOException e) {
            System.out.println("IO Exception ( cannot get input stream)");
            return false;
        }
        
        
        try {
            outputStream = serialPort.getOutputStream();
            System.out.println(" Output Stream... " + outputStream);
        } catch (IOException e) {
            System.out.println("IO Exception (cannot get output stream)");
            return false;
        }

                
        try {
            serialPort.addEventListener( this);

        } catch (TooManyListenersException e) {
            System.out.println("Tooo many Listener exception");
            return false;
        }
        
        serialPort.notifyOnDataAvailable(true);
        try {

            serialPort.setSerialPortParams(baudrate, 
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, 
                    SerialPort.PARITY_NONE);
            
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

            // timer on any read of the serial port
            serialPort.enableReceiveTimeout(500);

            System.out.println("................");

        } catch (UnsupportedCommOperationException e) {
            System.out.println("UnSupported comm operation");
            return false;
        }
        readThread = new Thread(this);
        isRunning = true;
        readThread.start();

        return true;
    }

    @Override
    public void run() {

        try {
            System.out.println("In run() function ");
            while(isRunning) {
                Thread.sleep(500);
            }
            // System.out.println();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception in run() method");
        }
    }

    private String remaining = "";
    @Override
    public void serialEvent(SerialPortEvent event) {
        
        
        // System.out.println("In Serial Event function().. " + event +
        // event.getEventType());
        switch (event.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:

                
                try {
                    StringBuilder sb = new StringBuilder(remaining);
                    int bytesAvailable = 0;
                    while ((bytesAvailable = inputStream.available()) > 0) {
                        
                        readBuffer = new byte[bytesAvailable];  // TODO: do not allocate data in a while
                        int numBytes = inputStream.read(readBuffer);
                        String stringData = new String(readBuffer);
//                        System.out.println(" :::::::---::: " + stringData);
                        
                        if (sb.length() < 512) {
                            sb.append(stringData);
                        }
                    }

//                    System.out.println(" :::::::++++::: " + sb.toString());
                   
                    if (sb.toString().contains("\n") || sb.toString().endsWith("\n")) {

                    //System.out.println(" Condition met 1 ");
                        remaining = "";
                        if (packetListeners.size() > 0) {

                    //System.out.println(" Condition met 2 ");

                            String messages[] = sb.toString().split("\n");
                            int size = messages.length;
                            if (sb.toString().endsWith("\n") == false) {
                                remaining = messages[messages.length -1 ];
  //                              System.out.println(String.format(" *** Partial message %s, splitted in %d ", sb.toString(), size));
                                size -= 1;

                            }

                            for (int i = 0; i < size; i ++) {
                                MessageGeneric dp = MessageFactory.getDataPacket(messages[i]);
                                if (dp != null) {
                                    for(PacketReceiverListener pl : packetListeners) {
                                        pl.onDataPacketReceived(this, dp);
                                    }
                                }
                            }
                        }
                    }else {
                        remaining = sb.toString();
                    }
                    
                } catch (IOException e) {
                    System.out.println("IO Exception in SerialEvent()");
                }
                break;
        }
    }

    public boolean writeString (String data ) {
        if (data != null && outputStream != null && isRunning) {
            try {
                GwLogger.i("Sending %s", data);
                outputStream.write(data.getBytes());
                return true;
            } catch (IOException ex) {
                Logger.getLogger(MessageReader.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        
        return false;
    }

    void stopReading() {
        isRunning= false;
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(MessageReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(MessageReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
