package CustomClasses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundTextField extends JTextField {
    public RoundTextField(){
        setOpaque(false);
        setBorder(new EmptyBorder(0,15,0,0));
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d=(Graphics2D) g;
        RoundRectangle2D.Double roundRec=new RoundRectangle2D.Double(0,0,getWidth()-1,getHeight()-1,getHeight(),getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.draw(roundRec);
    }
}