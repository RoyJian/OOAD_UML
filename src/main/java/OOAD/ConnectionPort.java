package OOAD;

import java.awt.*;

class ConnectionPort {
   public static final int width = 20;
   public static final int height = 20;
   private Point p;
    ConnectionPort(Point p){
        this.p = p;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D )g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(p.x,p.y,width,height);
    }
}
