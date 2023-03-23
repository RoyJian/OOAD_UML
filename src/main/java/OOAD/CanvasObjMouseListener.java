package OOAD;

import OOAD.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

abstract class CanvasObjMouseListener extends MouseAdapter implements MouseMotionListener {
    public void passToCanvas(MouseEvent e){
        Canvas canvas = Utils.getCanvas();
        Component component = e.getComponent();
        MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(component, e, canvas);
        canvas.dispatchEvent(convertMouseEvent);
    }

}

class BasicObjModMouseListener extends CanvasObjMouseListener {
    @Override
    public void mousePressed(MouseEvent e) {
        passToCanvas(e);
    }

}
class SelectModMouseAdapter extends CanvasObjMouseListener {
    private Point lastPoint = null;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mouseDragged(e);
        lastPoint = e.getLocationOnScreen();
        Component component = e.getComponent();
        CanvasObject item = (CanvasObject) component;
        Canvas.cleanSelectBag();
        Canvas canvas = Utils.getCanvas();
        Canvas.selectBag.add(item);
        Utils.getMain().setGroupEnable(true);
        item.setSelect(true);
        canvas.setComponentZOrder(item,0);
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        CanvasObject component = (CanvasObject) e.getComponent();
        Point nowPoint = e.getLocationOnScreen();
        int offsetX = nowPoint.x - lastPoint.x;
        int offsetY = nowPoint.y - lastPoint.y;
        Point p = new Point(component.getX() + offsetX, component.getY() + offsetY);
        component.setLocation(p);
        Utils.getCanvas().repaint();
        lastPoint = nowPoint;

    }
}
class ConnectionModMouseAdapter extends CanvasObjMouseListener {
    private Component enteredComponent = null;
    @Override
    public void mousePressed(MouseEvent e){

        Utils.getCanvas().pressPoint = Utils.changeCoordinate(e.getLocationOnScreen());

    }
    @Override
    public void mouseDragged(MouseEvent e){
        Utils.getCanvas().draggedPoint = Utils.changeCoordinate(e.getLocationOnScreen());
        Utils.getCanvas().repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e){
        Utils.getCanvas().resetPressAndDraggedPoint();
        Utils.getCanvas().repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e){
        if (Utils.getCanvas().pressPoint.x > -1 ){
            enteredComponent = e.getComponent();
        }
    }
}