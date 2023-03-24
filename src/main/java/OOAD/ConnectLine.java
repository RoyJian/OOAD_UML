package OOAD;

import java.awt.*;

interface I_ConnectLine {
    void paint(Graphics2D g2d);
}

abstract public class ConnectLine implements I_ConnectLine {
    protected BasicObject startObj;
    protected BasicObject endObj;
    protected ConnectionPort startConnPort;
    protected ConnectionPort endConnPort;

    ConnectLine(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
        setStartObj(startObj, startPort);
        setEndObj(endObj, endPort);
    }

    public CanvasObject getStartObj() {
        return startObj;
    }

    public void setStartObj(BasicObject startObj, ConnectionPort port) {
        this.startObj = startObj;
        this.startConnPort = port;
    }

    public void setEndObj(BasicObject endObj, ConnectionPort port) {
        this.endObj = endObj;
        this.endConnPort = port;
    }

    public CanvasObject getEndObj() {
        return endObj;
    }

    public Point clacStartPoint() {
        int x = startObj.getX() + startConnPort.getLinePosition().x;
        int y = startObj.getY() + startConnPort.getLinePosition().y;
        return new Point(x, y);
    }

    public Point calcEndPoint() {
        int x = endObj.getX() + endConnPort.getLinePosition().x;
        int y = endObj.getY() + endConnPort.getLinePosition().y;
        return new Point(x, y);
    }

    public void drawLine(Graphics2D g2d) {
        Point startPoint = clacStartPoint();
        Point endPoint = calcEndPoint();
        g2d.setColor(Color.black);
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }

    public int[][] calcPoints() {
        Point startPoint = clacStartPoint();
        Point endPoint = calcEndPoint();
        int d = 20;
        int h = 10;
        int dx = endPoint.x - startPoint.x, dy = endPoint.y - startPoint.y;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;
        x = xm * cos - ym * sin + startPoint.x;
        ym = xm * sin + ym * cos + startPoint.y;
        xm = x;

        x = xn * cos - yn * sin + startPoint.x;
        yn = xn * sin + yn * cos + startPoint.y;
        xn = x;
        int[] xPoints = {endPoint.x, (int) xm, (int) xn};
        int[] yPoints = {endPoint.y, (int) ym, (int) yn};
        return new int[][]{xPoints, yPoints};
    }
}

class AssociationLine extends ConnectLine {
    AssociationLine(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
        super(startObj, startPort, endObj, endPort);
    }

    @Override
    public void paint(Graphics2D g2d) {
        drawLine(g2d);
        int[][] points = calcPoints();
        g2d.setColor(Color.BLACK);
        g2d.drawLine(points[0][0], points[1][0], points[0][1], points[1][1]);
        g2d.drawLine(points[0][0], points[1][0], points[0][2], points[1][2]);

    }
}

class GeneralizationLine extends ConnectLine {
    GeneralizationLine(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
        super(startObj, startPort, endObj, endPort);
    }

    @Override
    public void paint(Graphics2D g2d) {
        drawLine(g2d);
        int[][] points = calcPoints();
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(points[0], points[1], 3);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(points[0], points[1], 3);
    }
}
