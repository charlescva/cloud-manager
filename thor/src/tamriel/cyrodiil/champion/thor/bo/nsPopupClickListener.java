/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamriel.cyrodiil.champion.thor.bo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Charles
 */
public class nsPopupClickListener extends MouseAdapter {

    private NimbusServerNode _nsNode;

    public void setNsNode(NimbusServerNode nsNode) {
        _nsNode = nsNode;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            doPop(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            doPop(e);
        }
    }

    private void doPop(MouseEvent e) {
        if (_nsNode != null) {
            NimbusServerPopupMenu menu = new NimbusServerPopupMenu(_nsNode);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
