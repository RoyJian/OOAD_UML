package OOAD;

import OOAD.Utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Group extends CanvasObject{
    private final ArrayList<CanvasObject> components;
    Group(){
        super(null);
        components = new ArrayList<CanvasObject>();
        setBackground(new Color(0,0,0,20));
        setListener();
    }
    public ArrayList<CanvasObject> getComponentList(){ return components; }
    public void cleanComponentList(){
        for (CanvasObject canvasObject: components){
            canvasObject.setIsGrouped(false);
            canvasObject.setSelect(false);
        }
        components.clear();
    }
    public void addToCanvas(){
        Canvas canvas = Utils.getCanvas();
        canvas.add(this,0);
        canvas.repaint();
    }
    @Override
    public void setLocation(Point p){
        int offsetX = p.x - getX();
        int offsetY = p.y - getY();
        super.setLocation(p);
        for (CanvasObject component: getComponentList()){
            component.setLocation(new Point(component.getX() + offsetX, component.getY() + offsetY ));
        }
    }

    @Override
    public void setSelect(Boolean bool) {
        Canvas canvas = Utils.getCanvas();
        Border blackLine = BorderFactory.createLineBorder(bool ? Color.darkGray : new Color(100,100,100,0));
        for (CanvasObject canvasObject: components){
            canvasObject.setSelect(bool);
            canvas.setComponentZOrder(canvasObject, bool ? 0 : canvas.getComponentZOrder(canvasObject));
        }
        canvas.setComponentZOrder(this, bool ? 0 : canvas.getComponentZOrder(this));
        setBorder(blackLine);
    }
    @Override
    public void setListener(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainForm.mode.canvasObjMouseListener.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MainForm.mode.canvasObjMouseListener.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                MainForm.mode.canvasObjMouseListener.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                MainForm.mode.canvasObjMouseListener.mouseExited(e);
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                MainForm.mode.canvasObjMouseListener.mouseDragged(e);
            }
        });
    }
}
class GroupMenuListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Group group  = new Group();
        ArrayList<CanvasObject> selectComponents =  Canvas.selectBag;
        if (selectComponents.size() < 2)
            return;
        Canvas canvas = Utils.getCanvas();
        Point maxLocation = new Point(0,0);
        Point minLocation = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
        for (CanvasObject canvasObject: selectComponents){
            maxLocation = Utils.maxPoint(maxLocation, new Point(canvasObject.getX() + canvasObject.getWidth(),
                    canvasObject.getY()+canvasObject.getHeight()));
            minLocation = Utils.minPoint(minLocation, canvasObject.getLocation());
            group.getComponentList().add(canvasObject);
            canvasObject.setIsGrouped(true);

        }
        int width = maxLocation.x - minLocation.x;
        int height = maxLocation.y - minLocation.y;
        Canvas.cleanSelectBag();
        group.setSize(width,height);
        group.setLocation(minLocation.x,minLocation.y);
        group.addToCanvas();
    }
}
class UnGroupMenuListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Canvas.selectBag.size() != 1)
            return;
        Canvas canvas = Utils.getCanvas();
        CanvasObject nowSelect = Canvas.selectBag.get(0);
        try{
            Group g = (Group) nowSelect;
            g.cleanComponentList();
            Canvas.cleanSelectBag();
            canvas.remove(g);
            canvas.repaint();
        } catch (ClassCastException ignore){}


    }
}
