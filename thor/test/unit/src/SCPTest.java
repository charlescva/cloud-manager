
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionMonitor;
import ch.ethz.ssh2.Session;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Charles
 */
public class SCPTest {

    public static void main(String[] args) throws Exception {
        tailit();

    }

    private static void tailit() {
        
        String server = "1620-Storm.bi2r.leidos.com";
        String username = "root";
        String password = "1620leido$";
                
                
        Connection conn;
        ConnectionMonitor cmon;
        String filepath = "~/test.txt";
        
        try {
            //Establish Connection...
            conn = new Connection(server);
            conn.connect();
            boolean isAuthenticated = conn
                    .authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            } else {
                cmon = new ConnectionMonitor() {
                    @Override
                    public void connectionLost(Throwable thrwbl) {
                        throw new UnsupportedOperationException("Lost SSH Connection.");
                    }
                };
                conn.addConnectionMonitor(cmon);
            }

            Session sess = conn.openSession();

            InputStream is = sess.getStdout();
            OutputStream os = sess.getStdin();

            sess.execCommand("tail -f " + filepath);
            
            

        } catch (Exception err) {
            err.printStackTrace();
        }

    }
}
