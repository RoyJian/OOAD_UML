package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

abstract class ImgLabelMouseListener extends MouseAdapter implements MouseMotionListener {

}

class BasicObjModMouseListener extends ImgLabelMouseListener {
    @Override
    public void mouseClicked(MouseEvent e){
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent().getParent());
        l.getParent().getParent().dispatchEvent(convertMouseEvent);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent().getParent());
        l.getParent().getParent().dispatchEvent(convertMouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent().getParent());
        l.getParent().getParent().dispatchEvent(convertMouseEvent);
    }


}
class SelectModMouseAdapter extends ImgLabelMouseListener {
    private Point lastPoint = null;

    @Override
    public void mousePressed(MouseEvent e) {
        lastPoint = e.getLocationOnScreen();
        JLabel l = (JLabel)e.getComponent();
        BasicObject item = (BasicObject) l.getParent();
        Canvas canvas = (Canvas) item.getParent();
        Canvas.nowSelectedObj.disableAllConnectionPort();
        item.enableAllConnectionPort();
        canvas.setComponentZOrder(item,0);
        canvas.repaint();
        Canvas.nowSelectedObj = item;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, l.getParent().getParent());
        l.getParent().getParent().dispatchEvent(convertMouseEvent);
    }

    @Override
    public void mouseDragged(MouseEvent e){
        Point nowPoint = e.getLocationOnScreen();
        int offsetX = nowPoint.x - lastPoint.x;
        int offsetY = nowPoint.y - lastPoint.y;
        BasicObject bo = (BasicObject)e.getComponent().getParent();
        bo.setLocation(bo.getX() + offsetX,bo.getY() + offsetY);
        bo.getParent().repaint();
        lastPoint = nowPoint;

    }
}
class ConnectionModMouseAdapter extends ImgLabelMouseListener {

}