package OOAD;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Canvas extends JPanel  {
    public ArrayList <CanvasItem> paintList;

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        Graphics2D graphic2d = (Graphics2D) g;
//        //ToDo
//        if (paintList.size() > 0){
//            for (CanvasItem item:paintList){
//                item.paint(g);
//            }
//        }
//    }


    Canvas(){
        paintList = new  ArrayList<CanvasItem>();
    }


}
