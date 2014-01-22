/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;
import tamriel.cyrodiil.champion.thor.ui.DeployRpmDialog;

@ActionID(
        category = "File",
        id = "tamriel.cyrodiil.champion.thor.actions.DeployRpmAction"
)
@ActionRegistration(
        iconBase = "tamriel/cyrodiil/champion/thor/page_white_tux.png",
        displayName = "#CTL_DeployRpmAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 850),
    @ActionReference(path = "Toolbars/File", position = 100),
    @ActionReference(path = "Shortcuts", name = "D-D")
})
@Messages("CTL_DeployRpmAction=Deploy RPM...")
public final class DeployRpmAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DeployRpmDialog drd = new DeployRpmDialog(null, true);
        drd.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        drd.setVisible(true);

    }
}
