package OOAD;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Canvas extends JPanel  {
    public ArrayList <Component> paintList;
    public static BasicObject nowSelectedObj;


    Canvas() {
        nowSelectedObj = new ClassItem(0, new Point(0,0)); //fake
        paintList = new  ArrayList<Component>();
    }


}
