/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.ui;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.log.Logger;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.openide.windows.WindowManager;
import tamriel.cyrodiil.champion.thor.MainTopComponent;
import tamriel.cyrodiil.champion.thor.jaxb.JaxbServers.Server;
import tamriel.cyrodiil.champion.thor.service.workers.RpmSwingWorker;

/**
 *
 * @author Charles
 */
public class DeployRpmDialog extends javax.swing.JDialog {

    private final MainTopComponent tc = (MainTopComponent) WindowManager.getDefault().findTopComponent("MainTopComponent");
    private static final Logger logger = Logger.getLogger(DeployRpmDialog.class);
    private String RpmActionCmd = "U";

    /**
     * Creates new form DeployRpmDialog
     */
    public DeployRpmDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        DefaultListModel dlm = new DefaultListModel();
        for (Server s : tc.getServers().getServer()) {
            dlm.addElement(s);
        }
        jList1.setModel(dlm);

         
        installRadioButton.setActionCommand("i");
        updateRadioButton.setActionCommand("U");
        
        
        ActionListener radioActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RpmActionCmd = e.getActionCommand();
            }
        };
     
        
        installRadioButton.addActionListener(radioActionListener);
        updateRadioButton.addActionListener(radioActionListener);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        CancelButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        SourceFileTextField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        installRadioButton = new javax.swing.JRadioButton();
        updateRadioButton = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DeployRpmDialog.class, "DeployRpmDialog.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(CancelButton, org.openide.util.NbBundle.getMessage(DeployRpmDialog.class, "DeployRpmDialog.CancelButton.text")); // NOI18N
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(DeployRpmDialog.class, "DeployRpmDialog.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DeployRpmDialog.class, "DeployRpmDialog.jLabel2.text")); // NOI18N

        SourceFileTextField.setText(org.openide.util.NbBundle.getMessage(DeployRpmDialog.class, "DeployRpmDialog.SourceFileTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(DeployRpmDialog.class, "DeployRpmDialog.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        buttonGroup2.add(installRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(installRadioButton, org.openide.util.NbBundle.getMessage(DeployRpmDialog.class, "DeployRpmDialog.installRadioButton.text")); // NOI18N

        buttonGroup2.add(updateRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(updateRadioButton, org.openide.util.NbBundle.getMessage(DeployRpmDialog.class, "DeployRpmDialog.updateRadioButton.text")); // NOI18N

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(installRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateRadioButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SourceFileTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SourceFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(installRadioButton)
                    .addComponent(updateRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CancelButton)
                        .addComponent(jButton2))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        final JFileChooser fc = new JFileChooser();
        if (tc.getClientProperty("lastFolder") != null) {
            fc.setCurrentDirectory(new File(tc.getClientProperty("lastFolder").toString()));
        }
        int returnVal = fc.showOpenDialog(DeployRpmDialog.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            tc.putClientProperty("lastFolder", fc.getCurrentDirectory().toString());
            File file = fc.getSelectedFile();
            SourceFileTextField.setText(file.getAbsolutePath());
        } else {
            //do nothing.
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jProgressBar1.setValue(0);
        jProgressBar1.setStringPainted(true);
       
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            String AbsoluteFilePath = SourceFileTextField.getText();
            if (new File(AbsoluteFilePath).exists()) {
                String FileName = AbsoluteFilePath.substring(
                        AbsoluteFilePath.lastIndexOf("\\") + 1);
                String StagingFolder = "~/rpm-stage";
                
                String cmd = "rpm -" + RpmActionCmd + "vh " + StagingFolder + "/" + FileName;

                for (int i = 0; i < jList1.getModel().getSize(); i++) {
                    Server associatedNode = (Server)jList1.getModel().getElementAt(i);
                    Connection conn = new Connection(associatedNode.getHostname());
                    RpmSwingWorker rsw = new RpmSwingWorker(conn);
                    rsw.setAssociatedNode(associatedNode);

                    rsw.setDeployCommand(cmd);
                    rsw.setLocalFile(AbsoluteFilePath);
                    rsw.setRemoteDir(StagingFolder);
                    rsw.addPropertyChangeListener(new PropertyChangeListener() {

                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("fileTransferProgress")) {
                                jProgressBar1.setValue(((Double) evt.getNewValue()).intValue());
                            }
                            if (evt.getPropertyName().equals("rpmreport")) {
                                JOptionPane.showMessageDialog(rootPane,
                                        "Check IDE Log for details.",
                                        "Done.",
                                        JOptionPane.INFORMATION_MESSAGE);
                                logger.log(Level.INFO.intValue(), evt.getNewValue().toString());

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
                    rsw.execute();

                }

            } else {
                throw new FileNotFoundException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(DeployRpmDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeployRpmDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeployRpmDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeployRpmDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DeployRpmDialog dialog = new DeployRpmDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton CancelButton;
    private javax.swing.JTextField SourceFileTextField;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton installRadioButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton updateRadioButton;
    // End of variables declaration//GEN-END:variables
}
