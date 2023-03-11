package OOAD;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.ArrayList;
public class Canvas extends JPanel{
    public ArrayList <CanvasItem> paintList;
    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        //ToDo
        if (paintList.size() > 1){
            for (CanvasItem item:paintList){
                item.paint(g);
            }
        }


    }

    Canvas(){
        this.setBackground(new Color(255,255,255));
        paintList = new  ArrayList<CanvasItem>();

    }



}
