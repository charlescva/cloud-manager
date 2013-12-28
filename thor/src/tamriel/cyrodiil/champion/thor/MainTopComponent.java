/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor;

import java.io.File;
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
import tamriel.cyrodiil.champion.thor.bo.NimbusServerNode;
import tamriel.cyrodiil.champion.thor.bo.nsPopupClickListener;
import tamriel.cyrodiil.champion.thor.jaxb.JaxbNimbusServers;
import tamriel.cyrodiil.champion.thor.jaxb.JaxbNimbusServers.JaxbNimbusServer;

/**
 * Top component which displays something.
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

    private DefaultMutableTreeNode top;
    static nsPopupClickListener popmenulistener = new nsPopupClickListener();
    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;
    private Marshaller jaxbMarshaller;
    private File xmlFile = new File("NimbusServers.xml");
    private JaxbNimbusServers _servers;

    public MainTopComponent() {
        initComponents();
        try {
            jaxbContext = JAXBContext.newInstance(JaxbNimbusServers.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbMarshaller = jaxbContext.createMarshaller();

            top = new DefaultMutableTreeNode("Storms");
            if (xmlFile.exists()) {

                _servers = (JaxbNimbusServers) jaxbUnmarshaller.unmarshal(xmlFile);
            } else {
                xmlFile.createNewFile();
                _servers = new JaxbNimbusServers();
                saveNimbusServers();
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
                }

                if (NodeType.equals("tamriel.cyrodiil.champion.thor.bo.NimbusServerNode")) {
                    NimbusServerNode nsNode = (NimbusServerNode) e.getPaths()[0].getLastPathComponent();
                    try {
                        //Add Reference to Currently Selected Nimbus Server Node.
                        popmenulistener.setNsNode(nsNode);

                    } catch (Exception ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }

            }
        });

        jTree1.setModel(new DefaultTreeModel(top));

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

    //public method called by New Nimbus Server jDialog.
    public void addServer(NimbusServerNode nsNode, boolean toXmlFile) {

        if (toXmlFile) {
            saveNimbusServers(nsNode);
        }
        top.add(nsNode);
        jTree1.setModel(new DefaultTreeModel(top));

    }

    //public method called to remove server
    public void deleteServer(NimbusServerNode nsNode) {

        try {
            for (JaxbNimbusServer s : _servers.getServer()) {
                if (s.getHostName().equals(nsNode.getHost())
                        && s.getUsername().equals(nsNode.getUsername())) {
                    _servers.getServer().remove(s);
                }
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(_servers, xmlFile);

            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        top.remove(nsNode);
        jTree1.setModel(new DefaultTreeModel(top));
    }

    private void saveNimbusServers() {
        try {
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(_servers, xmlFile);

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    //Uses JAXB
    //Saves an added Server to the XML file.
    private void saveNimbusServers(NimbusServerNode nsNode) {
        try {

            JaxbNimbusServer nsServer = new JaxbNimbusServer();
            nsServer.setDisplayName(nsNode.getName());
            nsServer.setHostName(nsNode.getHost());
            nsServer.setUsername(nsNode.getUsername());
            nsServer.setPassword(String.valueOf(nsNode.getPassword()));
            nsServer.setUiport((short) nsNode.getUi_port());
            _servers.getServer().add(nsServer);

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(_servers, xmlFile);

        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    //Uses JAXB
    //Creates Nodes in the Storms jTree node using the disk's XML File.

    private void loadServers() {

        try {

            if (xmlFile.exists()) {

                for (JaxbNimbusServer s : _servers.getServer()) {
                    NimbusServerNode nsNode = new NimbusServerNode();
                    nsNode.setHost(s.getHostName());
                    nsNode.setName(s.getDisplayName());
                    nsNode.setPassword(s.getPassword());
                    nsNode.setUi_port(s.getUiport());
                    nsNode.setUsername(s.getUsername());
                    addServer(nsNode, false);
                }
            } else {
                xmlFile.createNewFile();
            }

        } catch (Exception err) {
        }

    }
}
