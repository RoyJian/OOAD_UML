package OOAD;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Canvas extends JPanel  {
    public static ArrayList<CanvasObject> selectBag;
    public ArrayList<ConnectLine> connectLines;
    public Point pressPoint,draggedPoint;
    private int selectGroupWidth,selectGroupHeight;
    Canvas() {
        selectBag = new ArrayList<CanvasObject>();
        connectLines = new ArrayList<ConnectLine>();
        selectGroupWidth = 0;
        selectGroupHeight = 0;
        pressPoint = new Point(0,0);
        draggedPoint = new Point(0,0);
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
    public void resetPressAndDraggedPoint(){
        pressPoint.setLocation(-1,-1);
        draggedPoint.setLocation(-1,-1);
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
        for (CanvasObject component: selectBag)
            component.setSelect(false);
        selectBag.clear();

    }

}
