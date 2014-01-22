/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.ui.menus;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.openide.windows.WindowManager;
import tamriel.cyrodiil.champion.thor.MainTopComponent;
import tamriel.cyrodiil.champion.thor.bo.AccumuloServerNode;
import tamriel.cyrodiil.champion.thor.ui.FetchAccumuloTablesDialog;
import tamriel.cyrodiil.champion.thor.ui.NewAccumuloServerDialog;

/**
 *
 * @author Charles
 */
public class AccumuloServerPopupMenu extends JPopupMenu {

    private JMenuItem editServer = new JMenuItem("Edit...");
    private JMenuItem fetchTableList = new JMenuItem("Fetch Table List...");
    private JMenuItem navigateToUi = new JMenuItem("Open Accumulo Monitor");
    private JMenuItem deleteServer = new JMenuItem("Remove Server");
    private AccumuloServerNode associatedNode;

    private final MainTopComponent tc = (MainTopComponent) WindowManager.getDefault().findTopComponent("MainTopComponent");
        
    
    public AccumuloServerPopupMenu(AccumuloServerNode nsNode) {

        associatedNode = nsNode;
        
        fetchTableList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                FetchAccumuloTablesDialog fatd = new FetchAccumuloTablesDialog(null, true, associatedNode);
                fatd.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
                fatd.setVisible(true);
            }
        });
        
        editServer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                NewAccumuloServerDialog nds = new NewAccumuloServerDialog(null, true, associatedNode);
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



        add(navigateToUi);
        add(fetchTableList);
        add(deleteServer);
        add(editServer);
    }
}
