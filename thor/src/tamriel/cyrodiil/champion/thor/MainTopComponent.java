/*
 * @author Charles
 * This is the Plugin Top Component for the 'cloud-manager'.
 * This is where I am currently adding servers by 'type'.
 * 'Storm/Nimbus' types.
 * 'Accumulo' types.
 * 
 * the code is clunky and not optimized by any means. I'm more or less
 * prototyping on the fly so there will be issues.  Again this is all dev 
 * code that has not been adequeately tested and and should be run with caution.
 * I am not responsible for any damages that are caused to your system.

 * Thanks,
 * Charles



 */
package tamriel.cyrodiil.champion.thor;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import tamriel.cyrodiil.champion.thor.bo.AccumuloServerNode;
import tamriel.cyrodiil.champion.thor.bo.NimbusServerNode;
import tamriel.cyrodiil.champion.thor.bo.Server;
import tamriel.cyrodiil.champion.thor.bo.ServerTypes;
import tamriel.cyrodiil.champion.thor.bo.ContextPopupClickListener;
import tamriel.cyrodiil.champion.thor.jaxb.JaxbServers;

/**
 * @author Charles Top component which displays the Jtree with server nodes.
 */
@ConvertAsProperties(dtd = "-//tamriel.cyrodiil.champion.thor//Main//EN",
        autostore = false)
@TopComponent.Description(preferredID = "MainTopComponent",
        iconBase = "tamriel/cyrodiil/champion/thor/lightning.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "leftSlidingSide", openAtStartup = true)
@ActionID(category = "Window", id = "tamriel.cyrodiil.champion.thor.MainTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_MainAction",
        preferredID = "MainTopComponent")
public final class MainTopComponent extends TopComponent {

    private DefaultMutableTreeNode rootNode;
    private DefaultMutableTreeNode stormsNode;
    private DefaultMutableTreeNode accumulosNode;
    static ContextPopupClickListener popmenulistener = new ContextPopupClickListener();
    private JAXBContext jaxbServersContext;
    private Unmarshaller jaxbUnmarshaller;
    private Marshaller jaxbMarshaller;
    private File xmlFile = new File("settings.xml");
    private JaxbServers _servers;

    public JaxbServers getServers() {
        return _servers;
    }

    private static final Logger logger = Logger.getLogger(MainTopComponent.class.getName());

    public MainTopComponent() {
        initComponents();
        try {
            jaxbServersContext = JAXBContext.newInstance(JaxbServers.class);
            jaxbUnmarshaller = jaxbServersContext.createUnmarshaller();
            jaxbMarshaller = jaxbServersContext.createMarshaller();

            if (xmlFile.exists()) {

                _servers = (JaxbServers) jaxbUnmarshaller.unmarshal(xmlFile);
            } else {
                xmlFile.createNewFile();
                _servers = new JaxbServers();
                createSettingsFile();
            }
            loadServers();

        } catch (Exception err) {
            err.printStackTrace();
        }
        setName(NbBundle.getMessage(MainTopComponent.class, "CTL_MainTopComponent"));
        setToolTipText(NbBundle.getMessage(MainTopComponent.class, "HINT_MainTopComponent"));
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setShowsRootHandles(true);
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {

        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree1.addMouseListener(popmenulistener);

        jTree1.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {

                String NodeType = e.getPaths()[0].getLastPathComponent().getClass().getName();

                if (NodeType.equals("javax.swing.tree.DefaultMutableTreeNode")) {
                    //Do Nothing.
                    popmenulistener.setNsNode(null);
                    popmenulistener.setAsNode(null);
                }

                if (NodeType.equals("tamriel.cyrodiil.champion.thor.bo.NimbusServerNode")) {
                    NimbusServerNode nsNode = (NimbusServerNode) e.getPaths()[0].getLastPathComponent();
                    try {
                        //Add Reference to Currently Selected Nimbus Server Node.
                        popmenulistener.setNsNode(nsNode);
                        popmenulistener.setAsNode(null);

                    } catch (Exception ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
                if (NodeType.equals("tamriel.cyrodiil.champion.thor.bo.AccumuloServerNode")) {
                    AccumuloServerNode asNode = (AccumuloServerNode) e.getPaths()[0].getLastPathComponent();
                    try {
                        //Add Reference to Currently Selected Nimbus Server Node.
                        popmenulistener.setAsNode(asNode);
                        popmenulistener.setNsNode(null);

                    } catch (Exception ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }

            }
        });

        

    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public void addServer(Server svr, boolean toXmlFile) {

        if (svr.getServerType() == ServerTypes.ACCUMULO) {
            accumulosNode.add((AccumuloServerNode) svr);
        }
        if (svr.getServerType() == ServerTypes.NIMBUS) {
            stormsNode.add((NimbusServerNode) svr);
        }
        if (toXmlFile) {
            saveServers(svr);
        }
        jTree1.setModel(new DefaultTreeModel(rootNode));
    }

    public void deleteServer(Server svr) {
        try {
            
            List<JaxbServers.Server> s = _servers.getServer();

            JaxbServers.Server b = null;
            for (JaxbServers.Server a : s) {
                if (a.getHostname().equals(svr.getHostname())
                        && a.getServerType().equals(svr.getServerType().toString())) {
                    b = a;
                    break;
                }

            }

            _servers.getServer().remove(b);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(_servers, xmlFile);
            loadServers();
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    private void createSettingsFile() {
        try {
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(_servers, xmlFile);

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    //Uses JAXB
    //Saves an added Server to the XML file.
    private void saveServers(Server svr) {

        try {
            JaxbServers.Server jaxbServer = new JaxbServers.Server();

            jaxbServer.setHostname(svr.getHostname());
            jaxbServer.setUsername(svr.getUsername());
            jaxbServer.setPassword(svr.getPassword());
            jaxbServer.setOperatingSystem(svr.getOs());
            jaxbServer.setServerType(svr.getServerType().toString());

            if (svr.getServerType() == ServerTypes.NIMBUS) {
                NimbusServerNode nsNode = (NimbusServerNode) svr;
                jaxbServer.setServerType(ServerTypes.NIMBUS.toString());
                JaxbServers.Server.NimbusServer jaxbNS = new JaxbServers.Server.NimbusServer();
                jaxbNS.setDisplayName(nsNode.getDisplayName());
                jaxbNS.setUiport(new Short(new Integer(nsNode.getUi_port()).toString()));
                jaxbNS.setZookeepers(nsNode.getZookeeper());
                jaxbServer.setNimbusServer(jaxbNS);
            }

            if (svr.getServerType() == ServerTypes.ACCUMULO) {
                AccumuloServerNode asNode = (AccumuloServerNode) svr;
                jaxbServer.setServerType(ServerTypes.ACCUMULO.toString());
                JaxbServers.Server.AccumuloServer jaxbAS = new JaxbServers.Server.AccumuloServer();
                jaxbAS.setDbAccount(asNode.getDbAccount());
                jaxbAS.setDbPassword(asNode.getDbPassword());
                jaxbAS.setMonitorPort(new Integer(asNode.getUi_port()).toString());
                jaxbAS.setZookeepers(asNode.getZookeeper());
                jaxbServer.setAccumuloServer(jaxbAS);
            }
            _servers.getServer().add(jaxbServer);

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(_servers, xmlFile);

        } catch (Exception err) {
            err.printStackTrace();

        }
    }

    //Uses JAXB
    //Creates Nodes in the jTree using the disk's XML File.
    private void loadServers() {

        try {
            rootNode = new DefaultMutableTreeNode("Services");
            stormsNode = new DefaultMutableTreeNode("Storm Nimbus Hosts");
            accumulosNode = new DefaultMutableTreeNode("Accumulo Hosts");
            if (xmlFile.exists()) {
                //load the servers?
                for (JaxbServers.Server s : _servers.getServer()) {

                    addServer(parseJaxbServer(s), false);
                }
            } else {
                xmlFile.createNewFile();
            }
            rootNode.add(stormsNode);
            rootNode.add(accumulosNode);
        jTree1.setModel(new DefaultTreeModel(rootNode));

        } catch (Exception err) {
        }

    }

    // converts jaxb nimbus server to JTreeNode.
    private Server parseJaxbServer(JaxbServers.Server svr) {

        if (svr.getServerType().equals(ServerTypes.NIMBUS.toString())) {
            NimbusServerNode nsNode = new NimbusServerNode();
            JaxbServers.Server.NimbusServer s = svr.getNimbusServer();
            //Server fields
            nsNode.setHostname(svr.getHostname());
            nsNode.setUsername(svr.getUsername());
            nsNode.setPassword(svr.getPassword());
            nsNode.setServerType(ServerTypes.NIMBUS);
            //nimbus fields
            nsNode.setUi_port(s.getUiport());
            nsNode.setDisplayName(s.getDisplayName());
            nsNode.setZookeeper(s.getZookeepers());
            return nsNode;
        }
        if (svr.getServerType().equals(ServerTypes.ACCUMULO.toString())) {
            AccumuloServerNode asNode = new AccumuloServerNode();
            JaxbServers.Server.AccumuloServer s = svr.getAccumuloServer();
            //Server fields
            asNode.setHostname(svr.getHostname());
            asNode.setUsername(svr.getUsername());
            asNode.setPassword(svr.getPassword());
            asNode.setDisplayName(svr.getHostname());
            asNode.setServerType(ServerTypes.ACCUMULO);
            //nimbus fields
            asNode.setUi_port(new Integer(s.getMonitorPort()));
            asNode.setZookeeper(s.getZookeepers());
            return asNode;
        } else {
            return null;
        }
    }

}
