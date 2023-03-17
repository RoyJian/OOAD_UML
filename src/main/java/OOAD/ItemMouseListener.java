package OOAD;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
abstract class ItemMouseListener implements MouseListener{

}

class BasicObjModMouseListener extends ItemMouseListener {
    @Override
    public void mouseClicked(MouseEvent e){
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent());
        l.getParent().dispatchEvent(convertMouseEvent);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent());
        l.getParent().dispatchEvent(convertMouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent());
        l.getParent().dispatchEvent(convertMouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent());
        l.getParent().dispatchEvent(convertMouseEvent);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent());
        l.getParent().dispatchEvent(convertMouseEvent);
    }
}
class SelectModMouseAdapter extends ItemMouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel l = (JLabel)e.getComponent();
        l.getParent().setComponentZOrder(l,0);
        l.getParent().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
class ConnectionModMouseAdapter extends ItemMouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}