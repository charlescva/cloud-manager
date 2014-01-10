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
import tamriel.cyrodiil.champion.thor.ui.NewNimbusServerDialog;

/**
 *
 * @author Charles
 */
public class NimbusServerPopupMenu extends JPopupMenu {

    private JMenuItem editServer = new JMenuItem("Edit...");
    private JMenuItem navigateToUi = new JMenuItem("Open Nimbus UI");
    private JMenuItem deployTopology = new JMenuItem("Deploy Topology...");
    private JMenuItem deleteServer = new JMenuItem("Remove Server");
    private NimbusServerNode associatedNode;

    private final MainTopComponent tc = (MainTopComponent) WindowManager.getDefault().findTopComponent("MainTopComponent");
        
    
    public NimbusServerPopupMenu(NimbusServerNode nsNode) {

        associatedNode = nsNode;
        
        editServer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                NewNimbusServerDialog nds = new NewNimbusServerDialog(null, true, associatedNode);
                nds.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
                nds.setVisible(true);
            }

        });

        navigateToUi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Launch UI
                try {
                    java.awt.Desktop.getDesktop().browse(new URI("http://" + associatedNode.getHostname() + ":" + associatedNode.getUi_port()));
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });

        deleteServer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                tc.deleteServer(associatedNode);
            }
        });

        deployTopology.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DeployTopologyDialog dtd = new DeployTopologyDialog(null, true);
                    dtd.setNimbus(associatedNode);
                    dtd.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
                    dtd.setVisible(true);

                } catch (Exception err) {
                    throw new UnsupportedOperationException(err.getMessage());
                }
            }

        });

        add(navigateToUi);
        add(deployTopology);
        add(deleteServer);
        add(editServer);
    }
}
