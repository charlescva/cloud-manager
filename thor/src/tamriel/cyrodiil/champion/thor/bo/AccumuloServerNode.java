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
public class AccumuloServerNode extends DefaultMutableTreeNode implements Server {
    
    //Server Interface Fields
    private String os;
    private String username;
    private String password;
    private String hostname;
    private ServerTypes type;

   
    //Accumulo Class Specific Fields
    private String zookeeper;
    private int ui_port;
    private String displayName;   
    private String dbAccount;
    private String dbPassword;
    
    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }
  
    public String getDisplayNameName() {
        return displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    public int getUi_port() {
        return ui_port;
    }

    public void setUi_port(int ui_port) {
        this.ui_port = ui_port;
    }
    
        public String getDbAccount() {
        return dbAccount;
    }

    public void setDbAccount(String dbAccount) {
        this.dbAccount = dbAccount;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    

    
    // Method for JTreeNode Display Name.
       @Override
    public String toString() {
        return this.displayName;
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
