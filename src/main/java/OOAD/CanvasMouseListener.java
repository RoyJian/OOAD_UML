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
    public void mouseClicked(MouseEvent e){
        Canvas canvas = Utils.getCanvas();
        Canvas.cleanSelectBag();
        canvas.pressPoint = e.getPoint();
        canvas.repaint();
    }
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
        for (Component component : canvas.getComponents()) {
            CanvasObject canvasObject = (CanvasObject) component;
            if (Utils.verifyInGroup(canvasObject)) {
                Canvas.selectBag.add(canvasObject);
                canvasObject.setSelect(true);
            }
        }
        Utils.getMain().setGroupEnable(Canvas.selectBag.size() > 0);
        canvas.setSelectGroupSize(0,0);
        canvas.resetPressAndDraggedPoint();
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
    @Override
    public void mouseDragged(MouseEvent e){

    }

}