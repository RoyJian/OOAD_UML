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
}

class AssociationLine extends ConnectLine {
    AssociationLine(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
        super(startObj, startPort, endObj, endPort);
    }

    @Override
    public void paint(Graphics2D g2d) {
        Point startPoint = clacStartPoint();
        Point endPoint = calcEndPoint();
        g2d.setColor(Color.black);
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
