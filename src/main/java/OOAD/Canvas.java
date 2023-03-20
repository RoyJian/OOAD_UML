package OOAD;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Canvas extends JPanel  {
    public ArrayList <Component> paintList;
    public ArrayList<Component> selectBag;
    public static BasicObject nowSelectedObj;
    private int selectGroupWidth,selectGroupHeight;
    public Point pressPoint,draggedPoint;
    Canvas() {
        nowSelectedObj = new ClassItem(0, new Point(0,0)); //fake
        paintList = new  ArrayList<Component>();
        selectGroupWidth = 0;
        selectGroupHeight = 0;
        pressPoint = null;
        draggedPoint = null;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Canvas canvas = (Canvas) e.getComponent();
                MainForm.mode.canvasPerform(canvas, e.getPoint());
                canvas.repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e){
                Canvas canvas = (Canvas) e.getComponent();
                canvas.setSelectGroupSize(0,0);
                canvas.pressPoint = null;
                repaint();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                MainForm.mode.canvasDragged(e);
                repaint();
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

}
