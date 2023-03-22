package OOAD;

import OOAD.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Group extends JPanel {
    private final ArrayList<Component> components;
    Group(){
        components = new ArrayList<Component>();
        GroupListener groupListener = new GroupListener();
        setBackground(new Color(0,0,0,20));
        addMouseListener(groupListener);
        addMouseMotionListener(groupListener);
    }
    public ArrayList<Component> getComponentList(){ return components; }
    public void addToCanvas(){
        Canvas canvas = Utils.getCanvas();
        canvas.add(this,0);
        canvas.repaint();
    }
}
class GroupListener extends MouseAdapter implements MouseMotionListener {
    private Point lastPoint;
    GroupListener(){
        lastPoint = null;
    }
    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        Canvas.cleanSelectBag();
        Utils.getCanvas().repaint();
        Canvas.selectBag.add(e.getComponent());
        lastPoint = e.getLocationOnScreen();
    }
    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        Group group = (Group) e.getComponent();
        Point nowPoint = e.getLocationOnScreen();
        int offsetX = nowPoint.x - lastPoint.x;
        int offsetY = nowPoint.y - lastPoint.y;
        for (Component component: group.getComponentList()){
            component.setLocation(component.getX() + offsetX, component.getY() + offsetY);
        }
        group.setLocation(group.getX() + offsetX, group.getY() + offsetY);
        Utils.getCanvas().repaint();
        lastPoint = nowPoint;
    }
}
class GroupMenuListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Group group  = new Group();
        ArrayList<Component> selectComponents =  Canvas.selectBag;
        if (selectComponents.size() < 1)
            return;
        Canvas canvas = Utils.getCanvas();
        Point maxLocation = new Point(0,0);
        Point minLocation = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
        for (Component component: selectComponents){
            maxLocation = Utils.maxPoint(maxLocation, new Point(component.getX() + component.getWidth(),
                    component.getY()+component.getHeight()));
            minLocation = Utils.minPoint(minLocation, component.getLocation());
            group.getComponentList().add(component);
        }
        Canvas.cleanSelectBag();
        canvas.repaint();
        int width = maxLocation.x - minLocation.x;
        int height = maxLocation.y - minLocation.y;
        group.setSize(width,height);
        group.setLocation(minLocation);
        group.addToCanvas();
    }
}
class UnGroupMenuListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Canvas.selectBag.size() < 1)
            return;
        Canvas canvas = Utils.getCanvas();
        Component nowSelect = Canvas.selectBag.get(0);
        try{
            Group g = (Group) nowSelect;
            canvas.remove(g);
            canvas.repaint();

        } catch (ClassCastException ignore){}


    }
}
