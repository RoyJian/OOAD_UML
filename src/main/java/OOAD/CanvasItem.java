package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

interface I_CanvasItem{
    void setListener();
    void removeListener();
}
abstract class CanvasItem implements I_CanvasItem {

    protected Point startPoint;
    protected int depth;

    CanvasItem(){
        startPoint = new Point();
    }
    public Point getStartPoint(){
        return startPoint;
    }
    public void setStartPoint(int x,int y){
        startPoint.setLocation(x,y);
    };
    public void setDepth(int depth){
        this.depth = depth;
    }
    public int getDepth(){
        return  depth;
    }
}

abstract class BasicObject extends CanvasItem {
    protected int _width, _height;
    protected JLabel block;
    protected String imagePath;
    BasicObject(int depth, Point p){
        block = new JLabel();
        setSize(96,96);
        setStartPoint(p.x,p.y);
        setDepth(depth);
        setListener();

    }
    public void setListener(){
        block.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!MainForm.mode.equals(Mode.Select)) {
                    MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(block, e, block.getParent());
                    block.getParent().dispatchEvent(convertMouseEvent);
                    return;
                };
                System.out.println(depth);
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
        });
    }

    @Override
    public void removeListener(){

    };

    public void setSize(int width,int height){
        _width = width;
        _height = height;
    }
    public Component paint() {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(_width,_height, java.awt.Image.SCALE_SMOOTH);
        block.setIcon(new ImageIcon(image));
        block.setLocation(startPoint.x,startPoint.y);
        block.setSize(new Dimension(_width,_height));
        return block;
    }
}
abstract class ConnectionLine extends CanvasItem  {
    private  Point endLocation;
    ConnectionLine(int depth,int endLocationX,int endLocationY){

    }
}
class Class_UML extends BasicObject{
    Class_UML(int depth, Point p){
        super(depth, p);
        imagePath = "src/main/resources/class.png";
    }

}
class UseClass_UML extends BasicObject{
    UseClass_UML(int depth, Point p){
        super(depth,p);
        imagePath = "src/main/resources/useCase.png";
    }
}