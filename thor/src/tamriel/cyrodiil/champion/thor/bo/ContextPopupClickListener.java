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
public class ContextPopupClickListener extends MouseAdapter {

    private NimbusServerNode _nsNode;

    public AccumuloServerNode getAsNode() {
        return _asNode;
    }

    public void setAsNode(AccumuloServerNode _asNode) {
        this._asNode = _asNode;
    }
    private AccumuloServerNode _asNode;

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
        else if (_asNode != null) {
            AccumuloServerPopupMenu menu = new AccumuloServerPopupMenu(_asNode);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
        
    }
}
