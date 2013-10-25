package network;

import com.esotericsoftware.kryonet.*;
import database.ObjectList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Host {
    
    //work in progress
    
    static Server s;
    
    public static void createNewServer() {
        s = new Server();
        s.start();
        try {
            s.bind(7777, 7777);
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s.addListener(new Listener() {
        public void received (Connection connection, Object object) {
            System.out.println(connection+", "+object);
            connection.sendTCP("");
        }
     });
        
    }
    
}