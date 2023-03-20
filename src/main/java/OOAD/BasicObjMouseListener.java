package OOAD;

import OOAD.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

abstract class BasicObjMouseListener extends MouseAdapter implements MouseMotionListener {
    public void passToCanvas(MouseEvent e){
        Canvas canvas = Utils.getCanvas();
        JLabel l = (JLabel)e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(l, e, canvas);
        canvas.dispatchEvent(convertMouseEvent);
    }

}

class BasicObjModMouseListener extends BasicObjMouseListener {
    @Override
    public void mouseClicked(MouseEvent e){
        passToCanvas(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        passToCanvas(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        passToCanvas(e);
    }


}
class SelectModMouseAdapter extends BasicObjMouseListener {
    private Point lastPoint = null;

    @Override
    public void mousePressed(MouseEvent e) {
        lastPoint = e.getLocationOnScreen();
        JLabel l = (JLabel)e.getComponent();
        BasicObject item = (BasicObject) l.getParent();
        Canvas canvas = Utils.getCanvas();
        Canvas.nowSelectedObj.disableAllConnectionPort();
        item.enableAllConnectionPort();
        canvas.setComponentZOrder(item,0);
        canvas.repaint();
        Canvas.nowSelectedObj = item;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        passToCanvas(e);
    }

    @Override
    public void mouseDragged(MouseEvent e){
        Point nowPoint = e.getLocationOnScreen();
        int offsetX = nowPoint.x - lastPoint.x;
        int offsetY = nowPoint.y - lastPoint.y;
        BasicObject bo = (BasicObject)e.getComponent().getParent();
        bo.setLocation(bo.getX() + offsetX,bo.getY() + offsetY);
        Utils.getCanvas().repaint();
        lastPoint = nowPoint;

    }
}
class ConnectionModMouseAdapter extends BasicObjMouseListener {

}