package OOAD;

import java.awt.*;

interface I_Mode{
    Component generator(int depth, Point p);
    void paintCanvas(Canvas canvas, Graphics g);

}
public enum Mode  implements I_Mode{
    Select(
            1,"select", new SelectModMouseAdapter(), new SelectModCanvasMouseListener()) {
        @Override
        public Component generator(int depth, Point p) {
            return null;
        }

        @Override
        public void paintCanvas(Canvas canvas, Graphics g){
            try {
                Dimension size = canvas.getSelectGroupSize();
                int x = Math.min(canvas.pressPoint.x,canvas.draggedPoint.x);
                int y = Math.min(canvas.pressPoint.y,canvas.draggedPoint.y);
                g.setColor(new Color(50,180,249));
                g.drawRect(x,y, size.width, size.height);
                g.setColor(new Color(50,180,249,40));
                g.fillRect(x,y, size.width, size.height);
            } catch (NullPointerException ignore){}
        }
    }, CreateAssociationLine(
            2,"AssociationLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public Component generator(int depth,Point p) {
            return null;
        }
    }, CreateGeneralizationLine(
            3,"GeneralizationLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public Component generator(int depth,Point p) {
            return null;
        }
    }, CreateCompositionLine(
            4,"CompositionLine", new ConnectionModMouseAdapter(), new ConnectionLineModCanvasMouseListener()) {
        @Override
        public Component generator(int depth,Point p) {
            return null;
        }
    }, CreateClass(
            5,"Class", new BasicObjModMouseListener(), new BasicObjModCanvasMouseListener()) {
        @Override
        public Component generator(int depth,Point p) {
            return new ClassItem(depth,p);
        }
    }, CreateUseCase(
            6, "UseCase", new BasicObjModMouseListener(), new BasicObjModCanvasMouseListener()) {
        @Override
        public Component generator(int depth,Point p) {
            return new UseCaseItem(depth, p);
        }
    };
    public final String name;
    public final int id;
    public final BasicObjMouseListener basicObjMouseListener;
    public final CanvasMouseListener canvasMouseListener;
    public void addBasicObj(Canvas canvas, Point p){
        int depth =  100 - canvas.paintList.size();
        BasicObject basicObject = (BasicObject) generator(depth,p);
        canvas.paintList.add(basicObject);
        canvas.add(basicObject,0);
        canvas.repaint();

    }
    public void paintCanvas(Canvas canvas,Graphics g){};
    public void buttonPerform(){
        Canvas.nowSelectedObj.disableAllConnectionPort();
        try {
            Canvas c = (Canvas) Canvas.nowSelectedObj.getParent();
            c.repaint();
        } catch (NullPointerException ignored){}
        Canvas.nowSelectedObj = new ClassItem(0, new Point(0,0)); //fake
    };
    Mode(int id, String name, BasicObjMouseListener basicObjMouseListener, CanvasMouseListener canvasMouseListener){
        this.name = name;
        this.id = id;
        this.basicObjMouseListener = basicObjMouseListener;
        this.canvasMouseListener = canvasMouseListener;
    }
}
