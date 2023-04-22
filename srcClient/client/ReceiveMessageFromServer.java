package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anja Tanovic
 */
public class ReceiveMessageFromServer implements Runnable{
    
    Client parent;
    BufferedReader br;
    
    public ReceiveMessageFromServer(Client parent) {
        this.parent = parent;
        this.br = parent.getBr();
    }
    
    @Override
    public void run() {        
        while(true) {
            String serverMessage;
            try {
               serverMessage = this.br.readLine(); 
               //switch case or fsm for messages
            }
            catch (IOException ex) {
                Logger.getLogger(ReceiveMessageFromServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
}
