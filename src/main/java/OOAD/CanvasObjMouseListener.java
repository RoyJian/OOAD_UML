package OOAD;

import OOAD.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

abstract class CanvasObjMouseListener extends MouseAdapter implements MouseMotionListener {
    public void passToCanvas(MouseEvent e) {
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
        canvas.setComponentZOrder(item, 0);
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
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
    private BasicObject pressComponent = null;
    private ConnectionPort pressConnectionPort = null;
    private BasicObject enteredComponent = null;
    public ConnectionPort calcNearestConnPort(Point p, BasicObject basicObject) {
        ConnectionPort nearestConnectionPort = null;
        double minDistance = Double.MAX_VALUE;
        for (ConnectionPort connectionPort : basicObject.getConnectPorts()) {
            double distance = p.distance(connectionPort.getLinePosition());
            if (distance <= minDistance) {
                minDistance = distance;
                nearestConnectionPort = connectionPort;
            }
        }
        return nearestConnectionPort;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        BasicObject basicObject;
        Canvas canvas = Utils.getCanvas();
        try {
            basicObject = (BasicObject) e.getComponent();
        } catch (ClassCastException ignore) { return; }
        ConnectionPort nearestConnPort = calcNearestConnPort(e.getPoint(), basicObject);
        Point p = nearestConnPort.getLinePosition();
        p.setLocation(basicObject.getX() + p.getX(), basicObject.getY() + p.getY());
        canvas.pressPoint = p;
        pressComponent = basicObject;
        pressConnectionPort = nearestConnPort;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        BasicObject basicObject;
        Canvas canvas = Utils.getCanvas();
        try {
            basicObject = (BasicObject) e.getComponent();
        } catch (ClassCastException ignore) {
            return;
        }
        canvas.draggedPoint = Utils.changeCoordinate(e.getLocationOnScreen());
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Canvas canvas = Utils.getCanvas();
        BasicObject basicObject;
        canvas.resetPressAndDraggedPoint();
        canvas.repaint();
        if ( pressComponent != enteredComponent && enteredComponent != null){

            Point p = Utils.changeCoordinate(e.getComponent(), e.getPoint(), enteredComponent);
            ConnectionPort nearestConnPort = calcNearestConnPort(p, enteredComponent);
            canvas.connectLines.add(MainForm.mode.generator(pressComponent,pressConnectionPort,enteredComponent,nearestConnPort));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Canvas canvas = Utils.getCanvas();
        BasicObject basicObject;
        try {
            basicObject = (BasicObject) e.getComponent();

        } catch (ClassCastException ignore) { return ;}
        if (canvas.pressPoint.x > -1) {
            enteredComponent = basicObject;
        }
    }
    @Override
    public void mouseExited(MouseEvent e){
        enteredComponent = null;
    }
}