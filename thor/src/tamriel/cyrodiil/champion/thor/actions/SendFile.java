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
import tamriel.cyrodiil.champion.thor.ui.SendFileDialog;

@ActionID(
        category = "File",
        id = "tamriel.cyrodiil.champion.thor.actions.SendFile"
)
@ActionRegistration(
        iconBase = "tamriel/cyrodiil/champion/thor/actions/bullet_go.png",
        displayName = "#CTL_SendFile"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1100, separatorBefore = 1050, separatorAfter = 1150),
    @ActionReference(path = "Toolbars/File", position = 300),
    @ActionReference(path = "Shortcuts", name = "D-S")
})
@Messages("CTL_SendFile=Send File...")
public final class SendFile implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        SendFileDialog sfd = new SendFileDialog(null, true);
        sfd.setVisible(true);
    }
}
