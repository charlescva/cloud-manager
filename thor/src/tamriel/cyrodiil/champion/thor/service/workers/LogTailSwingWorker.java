/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.service.workers;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionMonitor;

import java.io.IOException;

import javax.swing.SwingWorker;
import tamriel.cyrodiil.champion.thor.service.ssh.SCPservice;

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

        try {
            Connection conn = new Connection(server);
            ConnectionMonitor cmon;
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

                SCPservice blah = new SCPservice(conn);
                
                Integer lastLineCount = 0;
                Integer currLineCount = 0;
                String response;
                boolean running = true;

                while (running) {
                    //generates the number of lines in a file.
                    response = blah.ssh("wc -l " + filepath + " | cut -d \" \" -f 1").toString()
                            .split("\\r\\n")[0];

                    currLineCount = new Integer(response);

                    if (currLineCount > lastLineCount) {

                        response = blah.ssh("tail --lines "
                                + (currLineCount - lastLineCount)
                                + " " + filepath).toString();
                        if (response != "null\r\n") {
                            System.out.println(response);
                        }
                    }

                    lastLineCount = currLineCount;

                    Thread.sleep(3000);
                }

            }
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            return "Done.";
        }

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
