package OOAD.Utils;

import OOAD.Canvas;
import OOAD.MainForm;
public class Utils{
    private static MainForm main;
    public static void setMain(MainForm main){
        Utils.main = main;
    }
    public static Canvas getCanvas(){
        return  main.canvasArea;
    }

}
