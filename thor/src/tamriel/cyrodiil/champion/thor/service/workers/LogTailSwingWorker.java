/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.service.workers;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionMonitor;
import ch.ethz.ssh2.Session;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.SwingWorker;

/**
 *
 * @author Charles
 */
public class LogTailSwingWorker extends SwingWorker<String, Integer> {

    private Connection conn;
    private ConnectionMonitor cmon;
    private String filepath;
    private String server;
    private String username;
    private String password;

    @Override
    protected String doInBackground() throws Exception {

        //Establish Connection...
        conn = new Connection(server);
        conn.connect();
        boolean isAuthenticated = conn.authenticateWithPassword(username, password);
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
        
        sess.execCommand("tail -f ~/test.txt");
        
        
        
        return "Done.";
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
