/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.actions;

import tamriel.cyrodiil.champion.thor.ui.NewNimbusServerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "tamriel.cyrodiil.champion.thor.CreateServerEntry")
@ActionRegistration(iconBase = "tamriel/cyrodiil/champion/thor/lightning_add.png",
displayName = "#CTL_CreateServerEntry")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300)
})
@Messages("CTL_CreateServerEntry=Add Nimbus Server...")
public final class CreateNimbusServer implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        
        NewNimbusServerDialog nds = new NewNimbusServerDialog(null, true);
        nds.setVisible(true);
        
        
        
    }
}
