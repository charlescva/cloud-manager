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

    private String filepath;
    private String server;
    private String username;
    private String password;
    private int startingLine = 0;

    public int getStartingLine() {
        return startingLine;
    }

    public void setStartingLine(int startingLine) {
        this.startingLine = startingLine;
    }

    public StringBuilder lastTextBlock;
    
    public StringBuilder getLastTextBlock() {
        return lastTextBlock;
    }
    
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
                Integer currLineCount;
                String response;
                boolean running = true;

                while (running) {
                    //generates the number of lines in a file.
                    response = blah.ssh("wc -l " + filepath + " | cut -d \" \" -f 1").toString()
                            .split("\\r\\n")[0];

                    currLineCount = new Integer(response);

                    if (currLineCount > lastLineCount) {

                    firePropertyChange("lineChange", lastLineCount, currLineCount);
                        //fetch the remaining lines, plus the original starting point.
                        response = blah.ssh("tail --lines "
                                + (currLineCount - lastLineCount + startingLine)
                                + " " + filepath).toString();
                        if (!"null\r\n".equals(response)) {
                            System.out.println(response);
                            lastTextBlock = new StringBuilder(response);
                        }
                    }
                    
                    

                    lastLineCount = currLineCount;
                    //since we're here, no reason to add the starting point.
                    startingLine=0;

                    Thread.sleep(3000);
                    
                    if(!running) {
                        break;
                    }
                }

            }
        } catch (Exception err) {
            err.printStackTrace();
            return "Error.";
        } 
        return "Done.";
        
        
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
