package OOAD;

import OOAD.Utils.Utils;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

abstract class CanvasMouseListener extends MouseAdapter implements MouseMotionListener {
}

class SelectModCanvasMouseListener extends CanvasMouseListener{

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Canvas canvas = Utils.getCanvas();
        Canvas.cleanSelectBag();
        canvas.pressPoint = e.getPoint();
        canvas.repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e){
        Canvas canvas = Utils.getCanvas();
        Component[] components = canvas.getComponents();
        Dimension size = canvas.getSelectGroupSize();
        for (Component component : components) {
            BasicObject basicObject = (BasicObject) component;
            if (Utils.isInSelectGroup(component)) {
                basicObject.enableAllConnectionPort();
                Canvas.selectBag.add(component);
            }
        }
        canvas.setSelectGroupSize(0,0);
        canvas.pressPoint = null;
        canvas.repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        Canvas canvas = Utils.getCanvas();
        int width = Math.abs(e.getX() - canvas.pressPoint.x);
        int height = Math.abs(e.getY() - canvas.pressPoint.y);
        canvas.draggedPoint = e.getPoint();
        canvas.setSelectGroupSize(width,height);
        canvas.repaint();
    }
}
class BasicObjModCanvasMouseListener extends CanvasMouseListener{
    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        Canvas canvas = Utils.getCanvas();
        MainForm.mode.addBasicObj(canvas,e.getPoint());
    }
}
class ConnectionLineModCanvasMouseListener extends CanvasMouseListener{

}