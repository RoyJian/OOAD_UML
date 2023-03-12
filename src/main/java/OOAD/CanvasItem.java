package OOAD;

import java.awt.*;

interface I_CanvasItem {
    void paint(Graphics g);
}
abstract class CanvasItem implements I_CanvasItem{
    private Point startPoint;
    public int depth;

    CanvasItem(int depth){
        startPoint = new Point();
        this.depth = depth;

    }
    public Point getStartPoint(){
        return startPoint;
    }
    public void setStartPoint(int x,int y){
        startPoint.setLocation(x,y);
    };
}
abstract class BasicObject extends CanvasItem {
    protected int _width, _height;
    BasicObject(int depth,int width,int height){
        super(depth);
        _width = width;
        _height = height;
    }
    public void setSize(int width,int height){
        _width = width;
        _height = height;
    }
}
abstract class ConnectionLine extends CanvasItem {
    private  Point endLocation;
    ConnectionLine(int depth,int endLocationX,int endLocationY){
        super(depth);
    }
}
class BlueSquare extends BasicObject{

    public BlueSquare(int depth,int width,int height){
        super(depth,width,height);
    }
    @Override
    public void paint(Graphics g) {
        Point startLocation = this.getStartPoint();
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.BLUE);
        graphic2d.fillRect(startLocation.x, startLocation.y, _width, _height);
    }
}