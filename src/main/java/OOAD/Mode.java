package OOAD;

import java.awt.*;

interface I_Mode{
    Component generator(int depth, Point p);
    void canvasPerform(Canvas canvas, Point p);
    void paintCanvas(Graphics g);

}
public enum Mode  implements I_Mode{
    Select(1,"select", new SelectModMouseAdapter()){

        @Override
        public Component generator(int depth, Point p) {
            return null;
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p){

        }
        @Override
        public void paintCanvas(Graphics g){

        }
    }, CreateAssociationLine(2,"AssociationLine", new ConnectionModMouseAdapter()) {
        @Override
        public Component generator(int depth,Point p) {
            return null;
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p) {

        }


    }, CreateGeneralizationLine(3,"GeneralizationLine", new ConnectionModMouseAdapter()) {
        @Override
        public Component generator(int depth,Point p) {
            return null;
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p) {

        }


    }, CreateCompositionLine(4,"CompositionLine", new ConnectionModMouseAdapter()) {
        @Override
        public Component generator(int depth,Point p) {
            return null;
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p) {
        }
    }, CreateClass(5,"Class", new BasicObjModMouseListener()) {
        @Override
        public Component generator(int depth,Point p) {
            return new ClassItem(depth,p);
        }

        @Override
        public void canvasPerform(Canvas canvas, Point p) {
            addBasicObj(canvas, p);
        }



    }, CreateUseCase(6, "UseCase", new BasicObjModMouseListener()) {
        @Override
        public Component generator(int depth,Point p) {
            return new UseCaseItem(depth, p);
        }
        @Override
        public void canvasPerform(Canvas canvas, Point p){
            addBasicObj(canvas, p);
        }
    };

    public void addBasicObj(Canvas canvas, Point p){
        int depth =  100 - canvas.paintList.size();
        BasicObject basicObject = (BasicObject) generator(depth,p);
        canvas.paintList.add(basicObject);
        canvas.add(basicObject,0);
        canvas.repaint();

    }
    public void paintCanvas(Graphics g){

    }
    public final String name;
    public final int id;
    public final ImgLabelMouseListener imgLabelMouseListener;

    public void buttonPerform(){
        Canvas.nowSelectedObj.disableAllConnectionPort();
        try{
            Canvas c = (Canvas) Canvas.nowSelectedObj.getParent();
            c.repaint();
        }catch (NullPointerException ignored){}
        Canvas.nowSelectedObj = new ClassItem(0, new Point(0,0)); //fake
    };
    Mode(int id, String name, ImgLabelMouseListener imgLabelMouseListener){
        this.name = name;
        this.id = id;
        this.imgLabelMouseListener = imgLabelMouseListener;
    }
}
