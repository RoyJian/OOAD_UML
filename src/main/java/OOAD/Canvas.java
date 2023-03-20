package OOAD;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Canvas extends JPanel  {
    public ArrayList <Component> paintList;
    public ArrayList<Component> selectBag;
    public static BasicObject nowSelectedObj;


    Canvas() {
        nowSelectedObj = new ClassItem(0, new Point(0,0)); //fake
        paintList = new  ArrayList<Component>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Canvas canvas = (Canvas) e.getComponent();
                MainForm.mode.canvasPerform(canvas, e.getPoint());
                canvas.repaint();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

            }
        });
    }
    @Override
    public void paint(Graphics g){
      super.paint(g);
        MainForm.mode.paintCanvas(g);
    };

}
