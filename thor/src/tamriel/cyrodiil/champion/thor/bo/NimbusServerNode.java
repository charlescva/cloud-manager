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
public class NimbusServerNode extends DefaultMutableTreeNode {
    
    private String name;
    private String host;
    private int ui_port;
    private String username;
    private String password;

    @Override
    public String toString() {
        return name;
    }
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUi_port() {
        return ui_port;
    }

    public void setUi_port(int ui_port) {
        this.ui_port = ui_port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
