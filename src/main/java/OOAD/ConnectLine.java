package OOAD;

import java.awt.*;

interface I_ConnectLine {
    void paint(Graphics g);
}

abstract public class ConnectLine implements I_ConnectLine {
    private BasicObject startObj;
    private BasicObject endObj;
    private ConnectionPort startConnPort;
    private ConnectionPort endConnPort;

    ConnectLine(BasicObject startObj, ConnectionPort port) {
        setStartObj(startObj, port);
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
    AssociationLine(BasicObject startObj, ConnectionPort port) {
        super(startObj, port);
    }

    @Override
    public void paint(Graphics g) {
        Point startPoint = calcEndPoint();
        Point endPoint = calcEndPoint();
        g.setColor(Color.black);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
