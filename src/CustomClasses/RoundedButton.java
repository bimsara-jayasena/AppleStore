package CustomClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private Color color;
    private int strock;
   public RoundedButton(String Text,Color color){
       super(Text);
       this.color=color;
       super.setBackground(color);
       setOpaque(false);
       setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

   }
    public RoundedButton(String Text){
        super(Text);


        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

    }

    public void setColor(Color color){

        this.color=color;
        super.setBackground(color);
         setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

    }
   @Override
    public void paintComponent(Graphics g){
       Graphics2D g2d=(Graphics2D) g;
       RoundRectangle2D.Double roundRec=new RoundRectangle2D.Double(0,0,getWidth()-1,getHeight()-1,getHeight(),getHeight());
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setPaint(color);

       g2d.fill(roundRec);
       g2d.setPaint(Color.red);
       g2d.setStroke(new BasicStroke(strock));

       super.paintComponent(g2d);


   }
}
