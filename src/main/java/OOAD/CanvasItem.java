package OOAD;

import java.awt.*;

public class CanvasItem {
    abstract class Abs_CanvasItem {
        private int _x,_y;
        private String _itemType;
        Abs_CanvasItem(int x, int y, String itemType){
            _x = x;
            _y = y;
            _itemType = itemType;
        }
        public Point getStartLocation(){
            return new Point(_x,_y);
        }
        public void setStartPoint(int x,int y){
          _x = x;
          _y = y;
        };
    }

}
