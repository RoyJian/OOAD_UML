package OOAD;

import OOAD.Utils.Utils;

import java.awt.*;

interface I_Mode {
    Component generator(Point p);

    ConnectLine generator(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort);

    void paintCanvas(Canvas canvas, Graphics g);

}

public enum Mode implements I_Mode {
    Select(
            1, "select", new SelectModMouseAdapter(), new SelectModCanvasMouseListener()) {
        @Override
        public Component generator(Point p) {
            return null;
        }

        @Override
        public ConnectLine generator(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
            return null;
        }

        @Override
        public void paintCanvas(Canvas canvas, Graphics g) {
            try {
                paintConnectLine((Graphics2D) g);
                Dimension size = canvas.getSelectGroupSize();
                int x = Math.min(canvas.pressPoint.x, canvas.draggedPoint.x);
                int y = Math.min(canvas.pressPoint.y, canvas.draggedPoint.y);
                g.setColor(new Color(50, 180, 249));
                g.drawRect(x, y, size.width, size.height);
                g.setColor(new Color(50, 180, 249, 40));
                g.fillRect(x, y, size.width, size.height);
            } catch (NullPointerException ignore) {
            }
        }
    }, CreateAssociationLine(
            2, "AssociationLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public void paintCanvas(Canvas canvas, Graphics g) {
            try {
                Graphics2D g2d = (Graphics2D) g;
                paintConnectLine(g2d);
                g2d.setColor(Color.black);
                g2d.drawLine(canvas.pressPoint.x, canvas.pressPoint.y, canvas.draggedPoint.x, canvas.draggedPoint.y);
            } catch (NullPointerException ignore) {
            }
        }

        @Override
        public Component generator(Point p) {
            return null;
        }

        @Override
        public ConnectLine generator(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
            return new AssociationLine(startObj, startPort, endObj, endPort);
        }
    }, CreateGeneralizationLine(
            3, "GeneralizationLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public void paintCanvas(Canvas canvas, Graphics g) {
            try {
                Graphics2D g2d = (Graphics2D) g;
                paintConnectLine(g2d);
                g2d.setColor(Color.black);
                g2d.drawLine(canvas.pressPoint.x, canvas.pressPoint.y, canvas.draggedPoint.x, canvas.draggedPoint.y);
            } catch (NullPointerException ignore) {
            }
        }

        @Override
        public Component generator(Point p) {
            return null;
        }

        @Override
        public ConnectLine generator(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
            return new GeneralizationLine(startObj, startPort, endObj, endPort);
        }
    }, CreateCompositionLine(
            4, "CompositionLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public void paintCanvas(Canvas canvas, Graphics g) {
            try {
                Graphics2D g2d = (Graphics2D) g;
                paintConnectLine(g2d);
                g2d.setColor(Color.black);
                g2d.drawLine(canvas.pressPoint.x, canvas.pressPoint.y, canvas.draggedPoint.x, canvas.draggedPoint.y);
            } catch (NullPointerException ignore) {
            }
        }

        @Override
        public Component generator(Point p) {
            return null;
        }

        @Override
        public ConnectLine generator(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
            return new CompositionLine(startObj, startPort, endObj, endPort);
        }
    }, CreateClass(
            5, "Class", new BasicObjModMouseListener(), new BasicObjModCanvasMouseListener()) {
        @Override
        public Component generator(Point p) {
            return new ClassItem(p);
        }

        @Override
        public ConnectLine generator(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
            return null;
        }
    }, CreateUseCase(
            6, "UseCase", new BasicObjModMouseListener(), new BasicObjModCanvasMouseListener()) {
        @Override
        public Component generator(Point p) {
            return new UseCaseItem(p);
        }

        @Override
        public ConnectLine generator(BasicObject startObj, ConnectionPort startPort, BasicObject endObj, ConnectionPort endPort) {
            return null;
        }
    };
    public final String name;
    public final int id;
    public final CanvasObjMouseListener canvasObjMouseListener;
    public final CanvasMouseListener canvasMouseListener;

    public void addBasicObj(Canvas canvas, Point p) {
        BasicObject basicObject = (BasicObject) generator(p);
        canvas.add(basicObject, 0);
        canvas.repaint();

    }

    public void paintCanvas(Canvas canvas, Graphics g) {
        paintConnectLine((Graphics2D) g);
    }

    public void buttonPerform() {
        Canvas.cleanSelectBag();
        Utils.getCanvas().resetPressAndDraggedPoint();
        Utils.getCanvas().repaint();
        Utils.getMain().setGroupEnable(false);
        Utils.getMain().setChangeNameMenuEnable(false);
    }

    public void paintConnectLine(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        for (ConnectLine connectLine : Utils.getCanvas().connectLines) {
            connectLine.paint(g2d);
        }
    }


    Mode(int id, String name, CanvasObjMouseListener canvasObjMouseListener, CanvasMouseListener canvasMouseListener) {
        this.name = name;
        this.id = id;
        this.canvasObjMouseListener = canvasObjMouseListener;
        this.canvasMouseListener = canvasMouseListener;
    }
}
