/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.bo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.openide.windows.WindowManager;
import tamriel.cyrodiil.champion.thor.MainTopComponent;
import tamriel.cyrodiil.champion.thor.ui.DeployTopologyDialog;

/**
 *
 * @author Charles
 */
public class NimbusServerPopupMenu extends JPopupMenu {

    private JMenuItem navigateToUi = new JMenuItem("Open UI in Browser");
    private JMenuItem deployTopology = new JMenuItem("Deploy Topology...");
    private JMenuItem deleteServer = new JMenuItem("Remove Server");
    private NimbusServerNode associatedNode;

    public NimbusServerPopupMenu(NimbusServerNode nsNode) {

        associatedNode = nsNode;

        navigateToUi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Launch UI
                try {
                    java.awt.Desktop.getDesktop().browse(new URI("http://" + associatedNode.getHost() + ":" + associatedNode.getUi_port()));
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });

        deleteServer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainTopComponent tc = (MainTopComponent) WindowManager.getDefault().findTopComponent("MainTopComponent");
                tc.deleteServer(associatedNode);
            }
        });

        deployTopology.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DeployTopologyDialog dtd = new DeployTopologyDialog(null, true);
                    dtd.setNimbus(associatedNode);
                    
                    dtd.setVisible(true);
                    
                } catch(Exception err) {
                    throw new UnsupportedOperationException(err.getMessage());
                }
            }

        });

        add(navigateToUi);
        add(deployTopology);
        add(deleteServer);
    }
}
