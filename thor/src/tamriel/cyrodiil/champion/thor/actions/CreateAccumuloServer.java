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
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import tamriel.cyrodiil.champion.thor.ui.NewAccumuloServerDialog;

@ActionID(
        category = "File",
        id = "tamriel.cyrodiil.champion.thor.CreateAccumuloServer"
)
@ActionRegistration(
        iconBase = "tamriel/cyrodiil/champion/thor/weather_clouds.png",
        displayName = "#CTL_CreateAccumuloServer"
)

@ActionReference(path = "Menu/File", position = 1200)
@Messages("CTL_CreateAccumuloServer=Add Accumulo Server...")
public final class CreateAccumuloServer implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        NewAccumuloServerDialog nda = new NewAccumuloServerDialog(null, true);
        nda.setVisible(true);
    }
}
