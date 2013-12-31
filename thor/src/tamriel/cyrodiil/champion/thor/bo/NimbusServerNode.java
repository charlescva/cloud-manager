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
public class NimbusServerNode extends DefaultMutableTreeNode implements Server {
    
    //Server Interface Fields
    private String os;
    private String username;
    private String password;
    private String hostname;
    private ServerTypes type;

    //Nimbus Class Specific Fields
    private String zookeeper;
    private int ui_port;
    private String displayName;
    
    
    //Override for JTree Display Name.
    @Override
    public String toString() {
        return displayName;
    }
    
    // Nimbus Specific Getters & Setters
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public int getUi_port() {
        return ui_port;
    }

    public void setUi_port(int ui_port) {
        this.ui_port = ui_port;
    }

    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }

    // Server Interface Specific Getters & Setters
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
