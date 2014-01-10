/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.bo;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Charles
 */
public class ServerNode extends DefaultMutableTreeNode implements Server {
    
    //Server Interface Fields
    private String os;
    private String username;
    private String password;
    private String hostname;
    private ServerTypes type;

    public ServerNode(String hostname) {
        this.hostname = hostname;
    }
    
    @Override
    public String toString() {
        return hostname;
    }
    
    // Methods from Server Interface.
    @Override
    public void setHostname(String server) {
        this.hostname = server;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public String getHostname() {
        return this.hostname;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getOs() {
        return this.os;
    }

    @Override
    public void setServerType(ServerTypes type) {
        this.type = type;
    }

    @Override
    public ServerTypes getServerType() {
        return this.type;
    }

   
    
}
