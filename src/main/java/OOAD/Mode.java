package OOAD;

import java.awt.*;

interface I_Mode{
    CanvasItem generator(int depth, Point p);
    void perform(Canvas canvas,Point p);
}
public enum Mode  implements I_Mode{
    Select(1,"select"){
        @Override
        public CanvasItem generator(int depth, Point p) {
            return null;
        }

        @Override
        public void perform(Canvas canvas, Point p){
            //ToDo

        }

    }, CreateAssociationLine(2,"AssociationLine") {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return null;
        }

        @Override
        public void perform(Canvas canvas, Point p) {

        }


    }, CreateGeneralizationLine(3,"GeneralizationLine") {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return null;
        }

        @Override
        public void perform(Canvas canvas, Point p) {

        }


    }, CreateCompositionLine(4,"CompositionLine") {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return null;
        }

        @Override
        public void perform(Canvas canvas, Point p) {

        }
    }, CreateClass(5,"CompositionLine") {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return new Class_UML(depth,p);
        }

        @Override
        public void perform(Canvas canvas, Point p) {
            int depth =  canvas.paintList.size();
            BasicObject item = (BasicObject) generator(depth,p);
            addComponent(canvas,item);
        }


    }, CreateUseCase(6, "UseCase") {
        @Override
        public CanvasItem generator(int depth,Point p) {
            return new UseClass_UML(depth,p);
        }
        @Override
        public void perform(Canvas canvas, Point p){
            int depth =  canvas.paintList.size();
            BasicObject item = (BasicObject) generator(depth,p);
            addComponent(canvas,item);
        }


    };

    public void addComponent(Canvas canvas,BasicObject item){
        canvas.add(item.paint());
        canvas.paintList.add(item);
    }
    public final String name;
    public final int id;
    Mode(int id,String name){

        this.name = name;
        this.id = id;
    }
}
