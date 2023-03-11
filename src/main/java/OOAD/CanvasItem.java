package OOAD;

import java.awt.*;

interface I_CanvasItem{
    void paint(Graphics g);
}
abstract class CanvasItem implements I_CanvasItem{
    private int _x,_y;
    private String _itemType;
    CanvasItem(String itemType){
        _itemType = itemType;
    }
    public Point getStartLocation(){
        return new Point(_x,_y);
    }
    public void setStartPoint(int x,int y){
        _x = x;
        _y = y;
    };
}
class BlueSquare extends CanvasItem implements I_CanvasItem{
    private int _width, _height;
    public BlueSquare(int width,int height){
        super("BlueSquare");
        _width = width;
        _height = height;
    }
    public void setSize(int width,int height){
        _width = width;
        _height = height;
    }

    @Override
    public void paint(Graphics g) {
        Point startLocation = this.getStartLocation();
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.BLUE);
        graphic2d.fillRect(startLocation.x, startLocation.y, _width, _height);
    }
}