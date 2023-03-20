package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

abstract class BasicObject extends JPanel {
    protected Point startPoint;
    protected int depth;
    protected JLabel imageLabel;
    protected String imagePath;
    protected ConnectionPort connectionPortTop,connectionPortBottom,connectionPortLeft,connectionPortRight;
    protected Color connectionPortColor;
    public void setDepth(int depth){ this.depth = depth; }
    public int getDepth(){ return  depth; }
    BasicObject(int depth,Point p ,String imagePath){
        imageLabel = new JLabel();
        startPoint = p;
        connectionPortColor = new Color(0,0,0,0);
        this.imagePath = imagePath;
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        setDepth(depth);
        initImageLabel();
        setListener();

    }

    public void setListener(){
        imageLabel.addMouseListener(new BasicObjMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 MainForm.mode.basicObjMouseListener.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                MainForm.mode.basicObjMouseListener.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MainForm.mode.basicObjMouseListener.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                MainForm.mode.basicObjMouseListener.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MainForm.mode.basicObjMouseListener.mouseExited(e);
            }
        });
        imageLabel.addMouseMotionListener(new BasicObjMouseListener(){
            @Override
            public void mouseDragged(MouseEvent e) {
                MainForm.mode.basicObjMouseListener.mouseDragged(e);
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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = imageLabel.getWidth() + ConnectionPort.width;
        int height = imageLabel.getHeight() + ConnectionPort.height;
        g.setColor(connectionPortColor);
        connectionPortTop = new ConnectionPort(g ,new Point(width / 2, 0));
        connectionPortBottom = new ConnectionPort(g ,new Point(width / 2, height));
        connectionPortLeft = new ConnectionPort(g ,new Point(0, height / 2));
        connectionPortRight = new ConnectionPort(g ,new Point(width, height / 2));
    }
    public void enableAllConnectionPort(){
        connectionPortColor = Color.BLACK;
    }
    public void disableAllConnectionPort(){ connectionPortColor = new Color(0,0,0,0); }
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