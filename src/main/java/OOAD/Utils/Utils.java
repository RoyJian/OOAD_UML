package OOAD.Utils;

import OOAD.Canvas;
import OOAD.ConnectionPort;
import OOAD.MainForm;

import java.awt.*;

public class Utils{
    private static MainForm main;
    public static void setMain(MainForm main){
        Utils.main = main;
    }
    public static MainForm getMain() { return main; }
    public static Canvas getCanvas(){
        return  main.canvasArea;
    }
    public static Boolean isInSelectGroup(Component component){
        Canvas canvas = getCanvas();
        Dimension GroupSize = canvas.getSelectGroupSize();
        int GroupStartX = Math.min(canvas.pressPoint.x,canvas.draggedPoint.x);
        int GroupStartY = Math.min(canvas.pressPoint.y, canvas.draggedPoint.y);
        int GroupEndX = GroupStartX + GroupSize.width;
        int GroupEndY = GroupStartY + GroupSize.height;
        int ComponentStartX = component.getX() + ConnectionPort.width ;
        int ComponentStartY = component.getY() + ConnectionPort.height;
        int ComponentEndX = ComponentStartX + component.getWidth() - 2*ConnectionPort.width;
        int ComponentEndY = ComponentStartY + component.getHeight() - 2*ConnectionPort.height;
        return ComponentStartX > GroupStartX && ComponentEndX < GroupEndX &&
                ComponentStartY > GroupStartY && ComponentEndY < GroupEndY;
    }
    public static Point minPoint(Point a, Point b){
        int x = Math.min(a.x,b.x);
        int y = Math.min(a.y,b.y);
        return (new Point(x,y));
    };
    public static Point maxPoint(Point a, Point b){
        int x = Math.max(a.x,b.x);
        int y = Math.max(a.y,b.y);
        return (new Point(x,y));
    }
}
