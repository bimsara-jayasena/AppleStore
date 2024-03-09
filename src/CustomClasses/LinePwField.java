package CustomClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class LinePwField extends JPasswordField {
    public LinePwField(){
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0,0,3,0));
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d=(Graphics2D) g;
        //RoundRectangle2D.Double roundRec=new RoundRectangle2D.Double(0,0,300,0,00,getHeight());
        Line2D.Double line=new Line2D.Double(0,getHeight()-5,getWidth(),getHeight()-5);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(line);
    }
}
