package CustomClasses;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Cards extends JLayeredPane {
    private Color color;
    private  int arc;
    public Cards(Color c,int r){

        color=c;
        arc=r;
        setOpaque(false);


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g.create();
        RoundRectangle2D.Double card=new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),25,25);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(color);
        g2d.fill(card);
        g2d.draw(card);
    }
}

