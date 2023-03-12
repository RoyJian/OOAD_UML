package OOAD;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.ArrayList;


public class Canvas extends JPanel{
    public ArrayList <CanvasItem> paintList;
    interface I_modeType{
        public CanvasItem generator(int depth);
    }
    public enum modeType implements I_modeType{
        Select(1,"select"){
            public CanvasItem generator(int depth){
                return null;
            }
        }, CreateAssociationLine(2,"AssociationLine") {
            @Override
            public CanvasItem generator(int depth) {
                return null;
            }
        }, CreateGeneralizationLine(3,"GeneralizationLine") {
            @Override
            public CanvasItem generator(int depth) {
                return null;
            }
        }, CreateCompositionLine(4,"CompositionLine") {
            @Override
            public CanvasItem generator(int depth) {
                return null;
            }
        }, CreateClass(5,"CompositionLine") {
            @Override
            public CanvasItem generator(int depth) {
                return null;
            }
        }, CreateUseCase(6, "UseCase") {
            @Override
            public CanvasItem generator(int depth) {
                return new BlueSquare(depth,61,61);
            }
        };

        public final String name;
        public final int id;
        modeType(int id,String name){
            this.name = name;
            this.id = id;
        }
    }
    private modeType _mode;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphic2d = (Graphics2D) g;
        //ToDo
        if (paintList.size() > 0){
            for (CanvasItem item:paintList){
                item.paint(g);
            }
        }


    }
    public void SetMode(modeType mode){
        _mode = mode;
    }
    public  modeType getMode(){
        return  _mode;
    }
    Canvas(){
        _mode = modeType.Select;
        paintList = new  ArrayList<CanvasItem>();
    }



}
