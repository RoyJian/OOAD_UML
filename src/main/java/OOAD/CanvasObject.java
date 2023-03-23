package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

interface  I_CanvasObject {
    void setSelect(Boolean bool);
    void paint(Graphics g);
    void setListener();


}
public abstract class CanvasObject extends JPanel implements I_CanvasObject{
    CanvasObject(Point p) {
        this.startPoint = p;
        this.isGroup = false;
        setLayout(null);
    }
    protected Point startPoint;
    protected Boolean isGroup;
    public Boolean getIsGroup() { return isGroup;}
    public void setIsGrouped(Boolean bool) { isGroup = bool; }
}

abstract class BasicObject extends CanvasObject {
    protected JLabel imageLabel;
    protected String imagePath;
    protected ConnectionPort connectionPortTop,connectionPortBottom,connectionPortLeft,connectionPortRight;
    protected Color connectionPortColor;
    BasicObject(Point p ,String imagePath){
        super(p);
        this.imagePath = imagePath;
        this.imageLabel = new JLabel();
        this.connectionPortColor = new Color(0,0,0,0);
        setBackground(new Color(0,0,0,0));
        initImageLabel();
        setListener();
    }
    @Override
    public void setListener(){
        imageLabel.addMouseListener(new CanvasObjMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(),e,e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseClicked(convertMouseEvent);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(),e,e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mousePressed(convertMouseEvent);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(),e,e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseReleased(convertMouseEvent);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(),e,e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseEntered(convertMouseEvent);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(),e,e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseExited(convertMouseEvent);
            }
        });
        imageLabel.addMouseMotionListener(new CanvasObjMouseListener(){
            @Override
            public void mouseDragged(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(),e,e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseDragged(convertMouseEvent);
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
    @Override
    public void setSelect(Boolean bool){
        connectionPortColor = bool ?  Color.BLACK : new Color(0,0,0,0);
    }
}
class ClassItem extends BasicObject{
    ClassItem(Point p){
        super(p, "src/main/resources/ClassItem.png");
    }

}
class UseCaseItem extends BasicObject{
    UseCaseItem(Point p){
        super(p, "src/main/resources/UseCaseItem.png");
    }
}