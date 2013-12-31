/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tamriel.cyrodiil.champion.thor.bo;

/**
 *
 * @author Ottch
 */
public interface Server {
    
    public void setServerType(ServerTypes type);
    public ServerTypes getServerType();
      
    public void setHostname(String server);
    public void setUsername(String username);
    public void setPassword(String password);
    public void setOs(String os);
    
    
    public String getHostname();
    public String getUsername();
    public String getPassword();
    public String getOs();
    
    
    
}
