package network;

import com.esotericsoftware.kryonet.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalClient {
    
    static Client c;
    
    public static void createNewClient() {
        c = new Client();
        c.start();
        
        c.addListener(new Listener() {
        public void received (Connection connection, Object object) {
           
            System.out.println(object);
           
        }
     });
        
        connectToServer(5000, "127.0.0.1", 7777);
    }
    
    public static void connectToServer(int timeout, String IP, int portTCP) {
        try {
            c.connect(timeout, IP, portTCP);
            c.sendTCP("A client has connected!");
        } catch (IOException ex) {
            Logger.getLogger(LocalClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}