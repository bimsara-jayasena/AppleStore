package CustomClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class BarGraphAxis extends JLayeredPane {

    double day1;
    double day2;
    double day3;
    double day4;
    double day5;
    double day6;
    double day7;
    double[] userValues=new double[7];
    double[] day=new double[7];
    int index=0;

    int qty;
    public BarGraphAxis( double day1,double day2,double day3,double day4,double day5,double day6,double day7) {
        this.day1=day1;
        this.day2=day2;
        this.day3=day3;
        this.day4=day4;
        this.day5=day5;
        this.day6=day6;
        this.day7=day7;
        this.qty=600;


        //setTitle("Bar Graph with Axes");
        //setSize(600, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        /*JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawAxes(g);
            }
        };

        add(panel);

        setVisible(true);*/
        JLabel lblX=new JLabel("Days");
        lblX.setBounds(350,570,100,30);
        lblX.setFont(new Font("",Font.BOLD,15));
        add(lblX);

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw x-axis
        g.drawLine(20, 550, getWidth(), 550);

       // g.drawString("Days",250,590);



        // Draw y-axis
        g.drawLine(60, 30, 60, 550);
        String str="INCOME";
        int y1=200;
        for (int i = 0; i < str.length(); i++) {
            g.drawString(String.valueOf(str.charAt(i)),0,y1);
            y1+=20;
        }


        for (int i = 0; i <= 10; i++) {
            int y = 550 - i * 50;
            g.drawLine(55, y, 65, y); // Tick mark
            g.drawString(i * 10000 + "", 15, y + 5);
        }

        /*for (int i = 2010; i <= 2020; i++) {
            int x = 100 + (i - 2010) * 60;
            g.drawLine(x, 345, x, 355); // Tick mark
            g.drawString("days", x - 10, 370);
        }*/
        String[] days={"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        for (int i = 0; i <days.length; i++) {
            int x = 110 +i* 90;
            g.drawLine(x, 545, x, 555); // Tick mark
            g.drawString(days[i], x - 10, 570);
        }

        // Assuming minX, maxX, minY, maxY represent the range of x and y values

        // Assuming graphAreaX, graphAreaY, graphAreaWidth, graphAreaHeight represent the position and dimensions of the graph area
        // Graph area dimensions and position
        int graphAreaX =0;
        int graphAreaY = 50;
        int graphAreaWidth = 680;
        int graphAreaHeight = 500;
        // Data range for x-axis and y-axis
        int minX = 0;
        int maxX = 7;
        int minY = 0;
        int maxY = 100000;

        // User-provided data values

        int width=60;
        int gap=30;
        timer();
        for (int i = 0; i < day.length; i++) {

            if(day[i]>maxY) {
                day[i] = maxY;
            }

                double x = getCoordinatesX(graphAreaWidth, day[i], minX, maxX, graphAreaX);
                double y = getCoordinatesY(graphAreaHeight, day[i], minY, maxY, graphAreaY);
                drawRec(g, (int) x, (int) y, width, (int) day[i]);
                width+=60+gap;
        }
    }
   /* private void drawAxes(Graphics g) {

    }*/

    public void drawRec(Graphics g, int xPixel, int yPixel,int width,int value) {
        /*g.setColor(Color.red);
        g.drawLine(25+width, yPixel, 75+width, yPixel);
        g.drawLine(75+width, 350, 75+width, yPixel);
        g.drawLine(25+width, 350, 25+width, yPixel);*/
        int x = 15 + width;
        int height = (550 - yPixel);

        g.setColor(Color.green);
        g.fillRect(x, yPixel, 70, height);
        g.setColor(Color.black);
        if(value ==100000){
            g.drawString("Rs."+ value +"+",x, yPixel-5);
        }
        else {
            g.drawString("Rs." +value, x, yPixel - 5);
        }
    }

    public void timer(){
        userValues = new double[]{day1, day2, day3, day4, day5, day6, day7};

        Timer timer=new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(day[0]<userValues[0]){
                    day[0]+=500;
                    repaint();
                    if(day[0]==userValues[0]){
                        ((Timer)e.getSource()).stop();
                    }
                }
                if(day[1]<userValues[1]){
                    day[1]+=500;
                    repaint();
                    if(day[1]==userValues[1]){
                        ((Timer)e.getSource()).stop();
                    }
                }
                if(day[2]<userValues[2]){
                    day[2]+=500;
                    repaint();
                    if(day[2]==userValues[2]){
                        ((Timer)e.getSource()).stop();
                    }
                }
                if(day[3]<userValues[3]){
                    day[3]+=500;
                    repaint();
                    if(day[3]==userValues[3]){
                        ((Timer)e.getSource()).stop();
                    }
                }
                if(day[4]<userValues[4]){
                    day[4]+=500;
                    repaint();
                    if(day[4]==userValues[4]){
                        ((Timer)e.getSource()).stop();
                    }
                }
                if(day[5]<userValues[5]){
                    day[5]+=500;
                    repaint();
                    if(day[5]==userValues[index]){
                        ((Timer)e.getSource()).stop();
                    }
                }
                if(day[6]<userValues[6]){
                    day[6]+=500;
                    repaint();
                    if(day[6]==userValues[6]){
                        ((Timer)e.getSource()).stop();
                    }
                }
            }
        });
        timer.start();
    }

    public double getCoordinatesX(double graphAreaWidth, double userValue, double minX, double maxX, double graphAreaX) {
        return graphAreaWidth * (userValue - minX) / (maxX - minX) + graphAreaX;
    }

    public double getCoordinatesY(double graphAreaHeight, double userValue, double minY, double maxY, double graphAreaY) {
        return graphAreaHeight * (maxY - userValue) / (maxY - minY) + graphAreaY;
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BarGraphAxis());
    }*/
}
