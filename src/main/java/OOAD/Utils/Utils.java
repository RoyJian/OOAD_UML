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
    public static Canvas getCanvas(){
        return  main.canvasArea;
    }
    public static Boolean isInSelectGroup(Component component){
        Canvas canvas = getCanvas();
        Dimension GroupSize = canvas.getSelectGroupSize();
        int ComponentStartX = component.getX() + ConnectionPort.width ;
        int ComponentStartY = component.getY() + ConnectionPort.height;
        int ComponentEndX = ComponentStartX + component.getWidth() - 2*ConnectionPort.width;
        int ComponentEndY = ComponentStartY + component.getHeight() - 2*ConnectionPort.height;
        int GroupStartX = Math.min(canvas.pressPoint.x,canvas.draggedPoint.x);
        int GroupStartY = Math.min(canvas.pressPoint.y, canvas.draggedPoint.y);
        int GroupEndX = GroupStartX + GroupSize.width;
        int GroupEndY = GroupStartY + GroupSize.height;
        return !(Math.max(ComponentStartX, GroupStartX) > Math.min(ComponentEndX,GroupEndX) ||
                    Math.max(ComponentStartY,GroupStartY) >  Math.min(ComponentEndY,GroupEndY));
    }


}
