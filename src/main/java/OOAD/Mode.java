package OOAD;

import OOAD.Utils.Utils;

import java.awt.*;

interface I_Mode {
    Component generator(Point p);

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
        public void paintCanvas(Canvas canvas, Graphics g) {
            try {
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

        @Override
        public void buttonPerform() {
            Utils.getMain().setGroupEnable(false);
        }
    }, CreateAssociationLine(
            2, "AssociationLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public void paintCanvas(Canvas canvas, Graphics g) {
            try {
                g.setColor(Color.black);
                g.drawLine(canvas.pressPoint.x, canvas.pressPoint.y, canvas.draggedPoint.x, canvas.draggedPoint.y);
            } catch (NullPointerException ignore) {
            }
        }

        @Override
        public Component generator(Point p) {
            return null;
        }

        public AssociationLine generator(BasicObject basicObject) {
            return new AssociationLine(basicObject, basicObject.connectionPortLeft);
        }
    }, CreateGeneralizationLine(
            3, "GeneralizationLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public Component generator(Point p) {
            return null;
        }
    }, CreateCompositionLine(
            4, "CompositionLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public Component generator(Point p) {
            return null;
        }
    }, CreateClass(
            5, "Class", new BasicObjModMouseListener(), new BasicObjModCanvasMouseListener()) {
        @Override
        public Component generator(Point p) {
            return new ClassItem(p);
        }
    }, CreateUseCase(
            6, "UseCase", new BasicObjModMouseListener(), new BasicObjModCanvasMouseListener()) {
        @Override
        public Component generator(Point p) {
            return new UseCaseItem(p);
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
    }


    public void buttonPerform() {
        Canvas.cleanSelectBag();
        Utils.getCanvas().repaint();
        Utils.getMain().setGroupEnable(false);
    }


    Mode(int id, String name, CanvasObjMouseListener canvasObjMouseListener, CanvasMouseListener canvasMouseListener) {
        this.name = name;
        this.id = id;
        this.canvasObjMouseListener = canvasObjMouseListener;
        this.canvasMouseListener = canvasMouseListener;
    }
}
