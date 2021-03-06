/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.ui;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.log.Logger;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.openide.windows.WindowManager;
import tamriel.cyrodiil.champion.thor.MainTopComponent;
import tamriel.cyrodiil.champion.thor.jaxb.JaxbServers;
import tamriel.cyrodiil.champion.thor.service.workers.HdfsSwingWorker;
import tamriel.cyrodiil.champion.thor.service.workers.SCPSwingWorker;

/**
 *
 * @author Ottch
 */
public class SendFileDialog extends javax.swing.JDialog {

    private static final Logger logger = Logger.getLogger(SendFileDialog.class);
    private final MainTopComponent tc = (MainTopComponent) WindowManager.getDefault().findTopComponent("MainTopComponent");

    /**
     * Creates new form SendFileDialog
     */
    public SendFileDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        for (JaxbServers.Server s : tc.getServers().getServer()) {
            ServerComboBox.addItem(s.getHostname());
        }

        ServerComboBox.addItem("hdfs://namenode:8020");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ServerComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        SendButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        SourceFileTextField = new javax.swing.JTextField();
        BrowseButton = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        ProgressJLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TargetPathTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.title")); // NOI18N
        setResizable(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.jLabel1.text")); // NOI18N

        ServerComboBox.setEditable(true);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(SendButton, org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.SendButton.text")); // NOI18N
        SendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(CancelButton, org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.CancelButton.text")); // NOI18N
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        SourceFileTextField.setText(org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.SourceFileTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(BrowseButton, org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.BrowseButton.text")); // NOI18N
        BrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(ProgressJLabel, org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.ProgressJLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.jLabel3.text")); // NOI18N

        TargetPathTextField.setText(org.openide.util.NbBundle.getMessage(SendFileDialog.class, "SendFileDialog.TargetPathTextField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ServerComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SourceFileTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BrowseButton))
                            .addComponent(TargetPathTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProgressJLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SendButton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SourceFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ServerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TargetPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SendButton)
                        .addComponent(CancelButton))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(ProgressJLabel))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseButtonActionPerformed
        final JFileChooser fc = new JFileChooser();
        if(tc.getClientProperty("lastFolder")!=null) {
            fc.setCurrentDirectory(new File(tc.getClientProperty("lastFolder").toString()));
        }
        
        int returnVal = fc.showOpenDialog(SendFileDialog.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            tc.putClientProperty("lastFolder", fc.getCurrentDirectory().toString());
            File file = fc.getSelectedFile();
            SourceFileTextField.setText(file.getAbsolutePath());
        } else {
            //do nothing.
        }
    }//GEN-LAST:event_BrowseButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SendButton.setEnabled(false);

        if (ServerComboBox.getSelectedItem().toString().startsWith("hdfs://")) {
            jProgressBar1.setIndeterminate(true);
            HdfsSwingWorker hsw = new HdfsSwingWorker();
            hsw.setSoureFile(SourceFileTextField.getText());
            hsw.setTargetFolder(TargetPathTextField.getText());
            hsw.setUri(ServerComboBox.getSelectedItem().toString());

            hsw.addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals("sendreport")) {

                        JOptionPane.showMessageDialog(rootPane,
                                "Check IDE Log for details.",
                                "Done.",
                                JOptionPane.INFORMATION_MESSAGE);
                        logger.log(Level.INFO.intValue(), evt.getNewValue().toString());
                        SendButton.setEnabled(true);
                        jProgressBar1.setIndeterminate(false);
                    }

                }
            });

            hsw.execute();

        } else {
            try {
                jProgressBar1.setValue(0);
                jProgressBar1.setStringPainted(true);
                String AbsoluteFilePath = SourceFileTextField.getText();
                if (new File(AbsoluteFilePath).exists()) {

                    String StagingFolder;
                    if (TargetPathTextField.getText().endsWith("/")) {
                        StagingFolder = TargetPathTextField.getText();
                    } else {
                        StagingFolder = TargetPathTextField.getText() + "/";
                    }

                    String selectedHost = ServerComboBox.getSelectedItem().toString();
                    Connection conn = new Connection(selectedHost);
                    SCPSwingWorker fssw = new SCPSwingWorker(conn);
                    fssw.setServer(selectedHost);
                    //server connection info/
                    for (JaxbServers.Server s : tc.getServers().getServer()) {
                        if (s.getHostname().equals(ServerComboBox.getSelectedItem().toString())) {
                            fssw.setUsername(s.getUsername());
                            fssw.setPassword(s.getPassword());

                        }
                    }

                    //Transfer Info.
                    fssw.setLocalFile(AbsoluteFilePath);
                    fssw.setRemoteDir(StagingFolder);

                    fssw.addPropertyChangeListener(new PropertyChangeListener() {

                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("fileTransferProgress")) {
                                jProgressBar1.setValue(((Double) evt.getNewValue()).intValue());
                            }
                            if (evt.getPropertyName().equals("sendreport")) {
                                JOptionPane.showMessageDialog(rootPane,
                                        evt.getNewValue().toString(),
                                        "Done.",
                                        JOptionPane.INFORMATION_MESSAGE);
                                logger.log(Level.INFO.intValue(), evt.getNewValue().toString());
                                SendButton.setEnabled(true);

                            }
                            if (evt.getPropertyName().equals("state")) {
                                if (evt.getNewValue().equals(SwingWorker.StateValue.DONE)) {
                                    jProgressBar1.setValue(0);
                                    jProgressBar1.setStringPainted(false);
                                    CancelButtonActionPerformed(null);
                                }
                            }

                        }
                    });
                    fssw.execute();
                } else {
                    throw new FileNotFoundException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        this.setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_SendButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SendFileDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SendFileDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SendFileDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SendFileDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SendFileDialog dialog = new SendFileDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowseButton;
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel ProgressJLabel;
    private javax.swing.JButton SendButton;
    private javax.swing.JComboBox ServerComboBox;
    private javax.swing.JTextField SourceFileTextField;
    private javax.swing.JTextField TargetPathTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
