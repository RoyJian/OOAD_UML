package OOAD;

import java.awt.*;

interface I_ConnectionPort {
    Point getLinePosition();
}

public abstract class ConnectionPort implements I_ConnectionPort {
    public static final int width = 20;
    public static final int height = 20;
    private Point connectLinePoint;
    protected Point p;
    ConnectionPort(Graphics g, Point p) {
        setPoint(p);
        paintComponent(g);
    }

    public void setPoint(Point p) {
        this.p = p;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(p.x, p.y, width, height);

    }
}

class TopConnectionPort extends ConnectionPort {
    TopConnectionPort(Graphics g, Dimension objSize) {
        super(g, new Point(objSize.width / 2, 0));
    }

    @Override
    public Point getLinePosition() {
        return new Point(p.x + (width / 2), p.y + height);
    }
}

class BottomConnectionPort extends ConnectionPort {

    BottomConnectionPort(Graphics g, Dimension objSize) {
        super(g, new Point(objSize.width / 2, objSize.height));
    }

    @Override
    public Point getLinePosition() {
        return new Point(p.x + width / 2, p.y);
    }
}

class LeftConnectionPort extends ConnectionPort {

    LeftConnectionPort(Graphics g, Dimension objSize) {
        super(g, new Point(0, objSize.height / 2));
    }

    @Override
    public Point getLinePosition() {
        return new Point(p.x + width, p.y + height / 2);
    }
}

class RightConnectionPort extends ConnectionPort {

    RightConnectionPort(Graphics g, Dimension objSize ) {
        super(g, new Point(objSize.width, objSize.height / 2));
    }

    @Override
    public Point getLinePosition() {
        return new Point(p.x, p.y + height / 2);
    }
}
