package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

abstract class BasicObject extends JPanel {
    protected Point startPoint;
    protected int _width, _height;
    protected int depth;
    protected JLabel imageLabel;
    protected String imagePath;
    public void setDepth(int depth){ this.depth = depth; }
    public int getDepth(){ return  depth; }
    BasicObject(int depth,Point p ,String imagePath){
        imageLabel = new JLabel();
        startPoint = p;
        this.imagePath = imagePath;
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        setDepth(depth);
        initImageLabel();
        setListener();
    }
    public void setListener(){
        imageLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 MainForm.mode.itemMouseListener.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                MainForm.mode.itemMouseListener.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MainForm.mode.itemMouseListener.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                MainForm.mode.itemMouseListener.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MainForm.mode.itemMouseListener.mouseExited(e);
            }
        });
    }

    public void initImageLabel(){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        setSize(imageIcon.getIconWidth() + ConnectionPort.width*2 ,
                imageIcon.getIconHeight() + ConnectionPort.height*2);
        setLocation(startPoint.x - ConnectionPort.width,startPoint.y - ConnectionPort.height);
        imageLabel.setIcon(imageIcon);
        imageLabel.setLocation(ConnectionPort.width,ConnectionPort.height);
        imageLabel.setSize(new Dimension(imageIcon.getIconWidth(),imageIcon.getIconHeight()));
        add(imageLabel);
        repaint();
    }
}
class ClassItem extends BasicObject{
    ClassItem(int depth, Point p){
        super(depth, p, "src/main/resources/ClassItem.png");
    }

}
class UseCaseItem extends BasicObject{
    UseCaseItem(int depth, Point p){
        super(depth, p, "src/main/resources/UseCaseItem.png");
    }
}