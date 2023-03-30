package OOAD;

import OOAD.Utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

interface I_CanvasObject {
    void setSelect(Boolean bool);
    void setListener();

}

public abstract class CanvasObject extends JPanel implements I_CanvasObject {
    CanvasObject(Point p) {
        this.startPoint = p;
        this.isGroup = false;
        setLayout(null);
    }

    protected Point startPoint;
    protected Boolean isGroup;

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGrouped(Boolean bool) {
        isGroup = bool;
    }
}

abstract class BasicObject extends CanvasObject {
    protected JLabel imageLabel;
    protected JLabel nameLabel;
    protected String imagePath;
    protected ConnectionPort connectionPortTop, connectionPortBottom, connectionPortLeft, connectionPortRight;
    protected Color connectionPortColor;

    BasicObject(Point p, String imagePath, String typeName) {
        super(p);
        this.imagePath = imagePath;
        this.connectionPortColor = new Color(0, 0, 0, 0);
        setBackground(new Color(0, 0, 0, 0));
        initImageLabel();
        initNameLabel("");
        setListener();

    }

    @Override
    public void setListener() {
        nameLabel.addMouseListener(new CanvasObjMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseClicked(convertMouseEvent);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mousePressed(convertMouseEvent);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseReleased(convertMouseEvent);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseEntered(convertMouseEvent);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseExited(convertMouseEvent);
            }
        });
        nameLabel.addMouseMotionListener(new CanvasObjMouseListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, e.getComponent().getParent());
                MainForm.mode.canvasObjMouseListener.mouseDragged(convertMouseEvent);
            }
        });
    }

    public void initImageLabel() {
        InputStream stream = MainForm.class.getClassLoader().getResourceAsStream(imagePath);
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(stream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon imageIcon = new ImageIcon(image);
        setSize(imageIcon.getIconWidth() + ConnectionPort.width * 2,
                imageIcon.getIconHeight() + ConnectionPort.height * 2);
        setLocation(startPoint.x - ConnectionPort.width, startPoint.y - ConnectionPort.height);
        imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);
        imageLabel.setLocation(ConnectionPort.width, ConnectionPort.height);
        imageLabel.setSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));

        add(imageLabel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = imageLabel.getWidth() + ConnectionPort.width;
        int height = imageLabel.getHeight() + ConnectionPort.height;
        g.setColor(connectionPortColor);
        connectionPortTop = new TopConnectionPort(g, new Dimension(width, height));
        connectionPortBottom = new BottomConnectionPort(g, new Dimension(width, height));
        connectionPortLeft = new LeftConnectionPort(g, new Dimension(width, height));
        connectionPortRight = new RightConnectionPort(g, new Dimension(width, height));
    }

    @Override
    public void setSelect(Boolean bool) {
        connectionPortColor = bool ? Color.BLACK : new Color(0, 0, 0, 0);
    }

    public ConnectionPort[] getConnectPorts() {
        return new ConnectionPort[]{
                connectionPortLeft, connectionPortRight, connectionPortTop, connectionPortBottom
        };
    }

    public void setName(String name) {
        nameLabel.setText(name);
        Utils.getCanvas().repaint();
    }

    public String getName() {
        return nameLabel.getText();
    }


    public void initNameLabel(String name) {
        nameLabel = new JLabel(name, JLabel.CENTER);
        nameLabel.setSize(imageLabel.getWidth(), imageLabel.getHeight());
        nameLabel.setLocation(imageLabel.getLocation());
        add(nameLabel, 0);
    }
}

class ClassItem extends BasicObject {
    ClassItem(Point p) {
        super(p, "ClassItem.png","Class");
    }

}

class UseCaseItem extends BasicObject {
    UseCaseItem(Point p) {
        super(p, "UseCaseItem.png", "Use Case");
    }
}