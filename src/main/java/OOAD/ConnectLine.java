package OOAD;

import java.awt.*;
import java.util.Arrays;

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

    public int[][] calcPoints() {
        Point startPoint = clacStartPoint();
        Point endPoint = calcEndPoint();
        int d = 10;
        int h = 10;
        int dx = endPoint.x - startPoint.x, dy = endPoint.y - startPoint.y;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, xo, ym = h, yn = -h, yo, x;
        double sin = dy / D, cos = dx / D;
        x = xm * cos - ym * sin + startPoint.x;
        ym = xm * sin + ym * cos + startPoint.y;
        xm = x;

        x = xn * cos - yn * sin + startPoint.x;
        yn = xn * sin + yn * cos + startPoint.y;
        xn = x;

        xo = -(2 * d) * (dx / D) + endPoint.x;
        yo = -(2 * d) * (dy / D) + endPoint.y;

        int[] xPoints = {(int) xm, endPoint.x, (int) xn, (int) xo};
        int[] yPoints = {(int) ym, endPoint.y, (int) yn, (int) yo};

        return new int[][]{xPoints, yPoints};
    }

    @Override
    public void paint(Graphics2D g2d) {
        Point startPoint = clacStartPoint();
        Point endPoint = calcEndPoint();
        g2d.setColor(Color.black);
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}

class AssociationLine extends ConnectLine {
    AssociationLine(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
        super(startObj, startPort, endObj, endPort);
    }

    @Override
    public void paint(Graphics2D g2d) {
        super.paint(g2d);
        int[][] points = calcPoints();
        g2d.setColor(Color.BLACK);
        g2d.drawLine(points[0][0], points[1][0], points[0][1], points[1][1]);
        g2d.drawLine(points[0][2], points[1][2], points[0][1], points[1][1]);

    }
}

class GeneralizationLine extends ConnectLine {
    GeneralizationLine(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
        super(startObj, startPort, endObj, endPort);
    }

    @Override
    public void paint(Graphics2D g2d) {
        super.paint(g2d);
        int[][] points = calcPoints();
        int nPoints = 3;
        g2d.setColor(Color.WHITE);
        int[] xPoints = Arrays.copyOfRange(points[0], 0, nPoints);
        int[] yPoints = Arrays.copyOfRange(points[1], 0, nPoints);
        g2d.fillPolygon(xPoints, yPoints, nPoints);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, nPoints);
    }
}

class CompositionLine extends ConnectLine {
    CompositionLine(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
        super(startObj, startPort, endObj, endPort);
    }

    @Override
    public void paint(Graphics2D g2d) {
        super.paint(g2d);
        int[][] points = calcPoints();
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(points[0], points[1], 4);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(points[0], points[1], 4);

    }
}
