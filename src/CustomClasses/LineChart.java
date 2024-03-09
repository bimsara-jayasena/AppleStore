package CustomClasses;



import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class LineChart extends JLayeredPane {
    public LineChart() {
        JLabel y=new JLabel("Y");
        y.setBounds(190,10,10,10);
        y.setForeground(Color.GRAY);
        add(y);
        JLabel X=new JLabel("X");
        X.setBounds(910,550,10,10);
        X.setForeground(Color.GRAY);
        add(X);

        /*
        int startY=550;
        int step=50;
        int points=50;
        int start=0;
        for (int i = 0; i < 11; i++) {
            JLabel yPoints=new JLabel(String.valueOf(String.valueOf(start)));
            yPoints.setBounds(170,startY,100,10);
            yPoints.setForeground(Color.GRAY);
            add(yPoints);
            startY-=step;
            start+=50;
        }*/


    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);


        //Creating bars

        Line2D lineY;
        lineY = new Line2D.Double(200, 150, 200, 550);
        g2d.setPaint(Color.GRAY);
        g2d.fill(lineY);
        g2d.draw(lineY);

        Line2D lineX;
        lineX = new Line2D.Double(200, 550, 900, 550);
        g2d.setPaint(Color.GRAY);
        g2d.fill(lineX);
        g2d.draw(lineX);
/*
        //Creating Horizontal lines
        int startY = 550;
        int step = 50;
        int endY = startY;

        for (int i = 0; i < 8; i++) {
            Line2D l = new Line2D.Double(200, startY , 900, endY);
            g2d.setPaint(Color.GRAY);
            g2d.fill(l);
            g2d.draw(l);
            startY -= step;
            endY -= step;
        }


        //Creating Vertical Line
        int startX = 250;
        int endX = startX;
        for (int i = 0; i < 13; i++) {
            Line2D l = new Line2D.Double(startX, 550, endX , 150);
            g2d.setPaint(Color.GRAY);
            g2d.fill(l);
            g2d.draw(l);
            startX += step;
            endX += step;

        }*/

        //Drawing line chart

        int[][] startingPoints={{200,550},{350,450},{400,500}};
        int[][] endingPoints={{350,450},{400,500},{700,400}};

        int x=0;
        int y=1;
        for (int i = 0; i < startingPoints.length; i++) {
            for (int j = 0; j < startingPoints.length; j++) {
                Line2D line=new Line2D.Double(startingPoints[i][x],startingPoints[i][y],endingPoints[i][x],endingPoints[i][y]);
                g2d.setPaint(Color.green);
                g2d.fill(line);
                g2d.draw(line);
            }
        }

    }
}
