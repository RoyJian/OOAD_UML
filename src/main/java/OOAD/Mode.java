package OOAD;

import java.awt.*;

interface I_Mode{
    CanvasItem generator(int depth, Point p);
    void canvasPerform(Canvas canvas, Point p);

}
public enum Mode  implements I_Mode{
    Select(1,"select", new SelectModMouseAdapter()){
        @Override
        public CanvasItem generator(int depth, Point p) {
            return null;
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p){
            //ToDo


        }
    }, CreateAssociationLine(2,"AssociationLine", new ConnectionModMouseAdapter()) {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return null;
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p) {

        }


    }, CreateGeneralizationLine(3,"GeneralizationLine", new ConnectionModMouseAdapter()) {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return null;
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p) {

        }


    }, CreateCompositionLine(4,"CompositionLine", new ConnectionModMouseAdapter()) {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return null;
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p) {
        }
    }, CreateClass(5,"Class", new BasicObjModMouseListener()) {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return new Class_UML(depth,p);
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p) {
            addBasicObj(canvas, p);
        }



    }, CreateUseCase(6, "UseCase", new BasicObjModMouseListener()) {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return new UseClass_UML(depth,p);
        }
        @Override
        public void canvasPerform(Canvas canvas, Point p){
            addBasicObj(canvas, p);
        }
    };

    public void addBasicObj(Canvas canvas, Point p){
        int depth =  100 - canvas.paintList.size();
        BasicObject item = (BasicObject) generator(depth,p);
        Component comp = item.paint();
        canvas.paintList.add(item);
        canvas.add(comp,0);
    }

    public final String name;
    public final int id;
    public final ItemMouseListener itemMouseListener;
    Mode(int id,String name,ItemMouseListener itemMouseListener){
        this.name = name;
        this.id = id;
        this.itemMouseListener = itemMouseListener;
    }
}
