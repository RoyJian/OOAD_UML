package OOAD;

import OOAD.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Group extends JPanel {
    Group(Point startPoint, Dimension size){
        setLocation(startPoint);
        setSize(size);
        setBackground(new Color(0,0,0,30));
    }
    public void addToCanvas(){
        Canvas canvas = Utils.getCanvas();
        canvas.add(this);
        canvas.repaint();
    }


}
class GroupMenuListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Component> selectComponents =  Canvas.selectBag;
        if (selectComponents.size() < 1)
            return;
        Canvas canvas = Utils.getCanvas();
        Point maxLocation = new Point(0,0);
        Point minLocation = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
        for (Component component: selectComponents){
            maxLocation = Utils.maxPoint(maxLocation, new Point(component.getX()+component.getWidth(),component.getY()+component.getHeight()));
            minLocation = Utils.minPoint(minLocation, component.getLocation());
        }
        int width = maxLocation.x - minLocation.x;
        int height = maxLocation.y - minLocation.y;
        Group group = new Group(minLocation, new Dimension(width,height));
        group.addToCanvas();
    }
    public void CalcBounds(ArrayList<Component> components){
        if (components.size() < 1)
            return;


    }
}
class UnGroupListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
