package OOAD;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Canvas extends JPanel  {
    public ArrayList <Component> paintList;
    public static ArrayList<Component> selectBag;
    public Point pressPoint,draggedPoint;
    private int selectGroupWidth,selectGroupHeight;
    Canvas() {
        paintList = new  ArrayList<Component>();
        selectBag = new ArrayList<Component>();
        selectGroupWidth = 0;
        selectGroupHeight = 0;
        pressPoint = null;
        draggedPoint = null;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                MainForm.mode.canvasMouseListener.mouseClicked(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                MainForm.mode.canvasMouseListener.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e){
                super.mouseReleased(e);
                MainForm.mode.canvasMouseListener.mouseReleased(e);
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                MainForm.mode.canvasMouseListener.mouseDragged(e);
            }
        });

    }
    public void setSelectGroupSize(int width,int height){
        selectGroupWidth = width;
        selectGroupHeight = height;
    }
    public Dimension getSelectGroupSize(){
        return new Dimension(selectGroupWidth,selectGroupHeight);
    }
    @Override
    public void paint(Graphics g){
      super.paint(g);
      MainForm.mode.paintCanvas(this, g);
    };
    public static void cleanSelectBag(){
        for (Component component:selectBag){
            ((BasicObject) component).disableAllConnectionPort();
        }
        selectBag.clear();
    }

}
