package Dashboard.Manager;

import CustomClasses.BarGraphAxis;
import CustomClasses.Cards;

import DbConnection.DbConnection;
import LogIn.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class DashboardMng extends JFrame {
    private final String name;
    private final String ID;
    JLayeredPane sidePanel=new JLayeredPane();
    JButton btnMinimize=new JButton();
    JLabel lblName=new JLabel();

    JButton btnEmp=new JButton("Employee Details");

    JButton btnSettigns=new JButton("Settings");
    JButton btnInv=new JButton("Inventory");
    JButton btnInfo=new JButton("Sales Info");

    JButton btnOut=new JButton("Log out");
    ImageIcon img2=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\Untitled-2.png");

    ImageIcon img=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\Untitled-1.png");

    public int count;
    public int stock=0;


    JLayeredPane homepane=new JLayeredPane();
    int total=0;

    JLabel lblChart=new JLabel();
    JButton btnPrev=new JButton();
    JButton btnNext=new JButton();



    int day;
    int i=1;

    int soldCount=0;
    int earnings=0;


    BarGraphAxis chart1;



    int data;
    int []salesRate={0,0,0,0,0,0,0};
    ArrayList <Integer> list=new ArrayList<>();
    ArrayList <Integer> days=new ArrayList<>();
    int week;
    int today;
    int weekNo;
    int thisWeek;
    int clicked;



    Color card=Color.decode("#D3D3D3");
    Cards card1=new Cards(card,25);

    Cards card2=new Cards(card,15);
    Cards card3=new Cards(card,15);

    JLabel lblBox1Count=new JLabel();
    JLabel lblBox1titlet=new JLabel();
    JLabel lblBox2Count=new JLabel();
    JLabel lblBox2titlet=new JLabel();
    JLabel lblBox3Count=new JLabel();
    JLabel lblBox3titlet=new JLabel();

    ImageIcon soldIcon=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\soldIcon.png");
    JLabel lblSoldIcon=new JLabel();

    ImageIcon earnIcon=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\earning.png");
    JLabel lblEarnIcon=new JLabel();


    private final Connection connection=DbConnection.getConnection();








    JLabel lblWeek=new JLabel();
    public DashboardMng(String name,String ID){
        this.name=name;
        this.ID=ID;
        try{

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM customers");

            while (resultSet.next()){
                // System.out.println(resultSet.getString(1));
                count++;
            }

        }catch (Exception a){
            System.out.println(a);
        }


        setSize(1680,1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       // setVisible(true);


        homepane.setBounds(0,0,1324,920);
        add(homepane);


        sidePanel.setBounds(0,0,300,1050);
        sidePanel.setOpaque(true);
        sidePanel.setBackground(Color.black);
        homepane.add(sidePanel);


        btnMinimize.setBounds(300,100,30,100);
        btnMinimize.setIcon(img);
        btnMinimize.setOpaque(true);
        btnMinimize.setBackground(Color.black);
        btnMinimize.setBorder(new EmptyBorder(0,0,0,0));
        btnMinimize.setFocusable(false);
        btnMinimize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sidepanelClose(sidePanel);
            }
        });
        homepane.add(btnMinimize);


        lblName.setText("Welcome Goerge!");
        lblName.setBounds(400,10,500,100);
        lblName.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 50));
        homepane.add(lblName);

        LocalDate localDate= LocalDate.now();


        JLabel lblDate=new JLabel();
        ScheduledExecutorService schedular=Executors.newSingleThreadScheduledExecutor();
        schedular.scheduleAtFixedRate(()->{
            LocalTime time=LocalTime.now();
            lblDate.setText("Today: "+ localDate+"\t:"+time);
        },0,1,TimeUnit.SECONDS);


        lblDate.setBounds(1200,10,600,100);
        lblDate.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        homepane.add(lblDate);

        int boxY=100;


        card1.setBounds(350,boxY,350,150);

        homepane.add(card1);


        card2.setBounds(750,boxY,350,150);

        homepane.add(card2);


        card3.setBounds(1150,boxY,450,150);

        homepane.add(card3);










        showEarnings();
        numberIncrementingAnimation();

        getIncome();
        lblBox1Count.setBounds(50,20,350,60);
        lblBox1Count.setText(soldCount+"");
        lblBox1Count.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 50));
        lblBox1Count.setForeground(Color.white);






        lblSoldIcon.setBounds(220,20,100,100);

       lblSoldIcon.setIcon(soldIcon);




        lblBox1titlet.setBounds(50,80,350,60);
        lblBox1titlet.setText("Sold Count");
        lblBox1titlet.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 25));
        lblBox1titlet.setForeground(Color.gray);


        card1.add(lblBox1Count);
        card1.add(lblSoldIcon);
        card1.add(lblBox1titlet);
        //test=100;


        lblBox2Count.setBounds(50,20,350,60);
        lblBox2Count.setText(totalStock()+"");
        lblBox2Count.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 50));
        lblBox2Count.setForeground(Color.white);




        lblBox2titlet.setBounds(50,80,350,60);
        lblBox2titlet.setText("Total Items");
        lblBox2titlet.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 25));
        lblBox2titlet.setForeground(Color.gray);


        card2.add(lblBox2Count);
        card2.add(lblBox2titlet);


        lblBox3Count.setBounds(30,20,350,60);
        lblBox3Count.setText("Rs."+earnings);
        lblBox3Count.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 50));
        lblBox3Count.setForeground(Color.white);


        lblBox3titlet.setBounds(30,80,350,60);
        lblBox3titlet.setText("Total Earnings");
        lblBox3titlet.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 25));
        lblBox3titlet.setForeground(Color.gray);


        lblEarnIcon.setBounds(320,20,100,100);

        lblEarnIcon.setIcon(earnIcon);


        card3.add(lblBox3Count);
        card3.add(lblEarnIcon);
        card3.add(lblBox3titlet);







        lblChart.setBounds(400,250,450,50);
        lblChart.setText("Selles Performence");
        lblChart.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        lblChart.setOpaque(true);
        //lblChart.setBackground(Color.cyan);
        homepane.add(lblChart);
        boolean isSameDay=true;
        boolean isEmpty=false;




        try{

            Statement statement=connection.createStatement();

           getDate();
            week=day;
            System.out.println("today is:"+day);

            int index=0;
            ResultSet resultSet=statement.executeQuery("SELECT *FROM days");
            while (resultSet.next()){
               int amount=resultSet.getInt(2);
                if(amount>100000){
                    list.add(100000);
                }
                else {
                    list.add(amount);
                }

            }
            //find the week
            for (int i = 0; i < day/7; i++) {
                days.add(index);
                index+=7;
            }

            for (Integer integer : days) {
                System.out.println("day is: " + integer);
            }





        }
        catch (Exception ex){
            System.out.println(ex);
        }
        int weekDay;
       // weekDay=days.get(days.size()-1)+7;
        clicked=days.size();
        if(!days.isEmpty()) {
           weekDay = days.get(days.size() - 1) + 7;
        }
        else{
            weekDay=0;
        }
        System.out.println("welcome to: "+weekDay);
        for (Integer integer : days) {
            System.out.println("this is " + integer + " week");
        }

        if(list.size()>7){
            for (int i = 0; i < salesRate.length; i++) {
                salesRate[i]=0;//reset values
            }
            int index=0;

            for (int i = weekDay; i < list.size(); i++) {
                    if(index>=7){
                        break;
                    }
                    System.out.println(list.get(i));
                    salesRate[index]=list.get(i);
                    today++;
                    index++;




            }




        }
        else {
            for (int i = 0; i < list.size(); i++) {
                salesRate[i] = list.get(i);
            }
        }
        setWeek(day,today,week);

        weekNo=(day/7)+1;
        thisWeek=weekNo;





        setPercentage(salesRate);



        btnPrev.setBounds(750,900,150,50);
        btnPrev.setText("Previous Week");
        btnPrev.setBackground(Color.BLACK);
        btnPrev.setForeground(Color.white);
        btnPrev.setFocusable(false);
        btnPrev.setVisible(true);
        btnPrev.addActionListener(e -> {

            if(clicked>=1){
           goPreviousWeeks(days.get(clicked-1));
            clicked--;
            }



        });
        homepane.add(btnPrev,Integer.valueOf(3));


        btnNext.setBounds(950,900,150,50);
        btnNext.setText("This Week");
        btnNext.setBackground(Color.black);
        btnNext.setForeground(Color.white);
        btnNext.setFocusable(false);
        btnNext.addActionListener(e -> {
                    if(day>7) {
                        showthisWeek();
                    }
        });
        homepane.add(btnNext,Integer.valueOf(3));

        //==================== SIDE PANEL==================

        btnEmp.setBounds(00,100,300,50);
        btnEmp.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnEmp.setBackground(sidePanel.getBackground());
        btnEmp.setBorder(new EmptyBorder(0,0,0,0));
        btnEmp.setForeground(Color.gray);
        btnEmp.setFocusable(false);
        btnEmp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==btnEmp){
                    dispose();
                    new employee(name,ID).show();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnEmp.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnEmp.setForeground(Color.gray);
            }
        });
        sidePanel.add(btnEmp);



        btnInv.setBounds(00,200,300,50);
        btnInv.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnInv.setBackground(sidePanel.getBackground());
        btnInv.setBorder(new EmptyBorder(0,0,0,0));
        btnInv.setForeground(Color.gray);
        btnInv.setFocusable(false);
        btnInv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Inventory(name,ID).show();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnInv.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnInv.setForeground(Color.gray);
            }
        });
        sidePanel.add(btnInv);

        btnInfo.setBounds(00,300,300,50);
        btnInfo.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnInfo.setBackground(sidePanel.getBackground());
        btnInfo.setBorder(new EmptyBorder(0,0,0,0));
        btnInfo.setForeground(Color.gray);
        btnInfo.setFocusable(false);
        btnInfo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SalesInfo(name,ID).show();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnInfo.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnInfo.setForeground(Color.gray);
            }
        });
        sidePanel.add( btnInfo);

        btnSettigns.setBounds(00,400,300,50);
        btnSettigns.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnSettigns.setBackground(sidePanel.getBackground());
        btnSettigns.setBorder(new EmptyBorder(0,0,0,0));
        btnSettigns.setForeground(Color.gray);
        btnSettigns.setFocusable(false);
        btnSettigns.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Settings(name,ID).show();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSettigns.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSettigns.setForeground(Color.gray);
            }
        });
        sidePanel.add(btnSettigns);

        btnOut.setBounds(0,500,300,50);
        btnOut.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnOut.setBackground(sidePanel.getBackground());
        btnOut.setBorder(new EmptyBorder(0,0,0,0));
        btnOut.setForeground(Color.gray);
        btnOut.setFocusable(false);
        btnOut.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==btnOut){
                    dispose();
                    new Login().setVisible(true);
                    i=i+1;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnOut.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnOut.setForeground(Color.gray);
            }
        });
        btnOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        sidePanel.add(btnOut);

        System.out.println("INCOME: "+getIncome());




    }

    public void getDate(){
        try {

            Statement statement = connection.createStatement();

            ResultSet dayResualt = statement.executeQuery("SELECT * FROM days");
            while (dayResualt.next()) {
                day = dayResualt.getInt(1);

            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void numberIncrementingAnimation(){
        int delay=10;
        int count=AmountOfSoldItems();

        System.out.println("SOLD COUNT IS: "+count);
        Timer timer1=new Timer(delay, e -> {

            if(count==0){
                soldCount=0;
            }
            else{
                soldCount+=1;
            }
            lblBox1Count.setText(soldCount+"");

            if(soldCount==count){
                ((Timer)e.getSource()).stop();
            }

        });



        timer1.start();
    }
    public void showEarnings(){
        int delay=10;

        int earn=getIncome();

        Timer timer2=new Timer(delay, e -> {

            if(earn==0){
                earnings=0;
            }
            else{
                earnings+=500;
            }

            lblBox3Count.setText("Rs."+earnings);
            if(earnings==earn ){
                ((Timer)e.getSource()).stop();
            }
        });
        timer2.start();
    }


    public void sidepanelClose(JLayeredPane sidePanel){
        if(sidePanel.getX()==0){
            btnMinimize.setIcon(img2);
            int X=sidePanel.getX();
            int newX=X-300;
            int step=100;
            Timer time=new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int CurruntX=sidePanel.getX();
                    if(CurruntX>newX){
                        sidePanel.setLocation(CurruntX-step, sidePanel.getY());
                        btnMinimize.setLocation(btnMinimize.getX()-step, btnMinimize.getY());
                        lblName.setLocation(lblName.getX()-step, lblName.getY());
                        card1.setLocation(card1.getX()-100, card1.getY());
                        card1.setSize(card1.getWidth()+30, card1.getHeight());
                        lblSoldIcon.setLocation(lblSoldIcon.getX()+30,lblSoldIcon.getY());


                        card2.setLocation(card2.getX()-70, card2.getY());
                        card2.setSize(card2.getWidth()+30,card2.getHeight());

                        card3.setLocation(card3.getX()-40, card3.getY());
                        card3.setSize(card3.getWidth()+30,card3.getHeight());
                        lblEarnIcon.setLocation(lblEarnIcon.getX()+30,lblEarnIcon.getY());

                        lblChart.setLocation(lblChart.getX()-step, lblChart.getY());

                        chart1.setLocation(chart1.getX()-50,chart1.getY());
                        btnPrev.setLocation(btnPrev.getX()-50, btnPrev.getY());
                        btnNext.setLocation(btnNext.getX()-50, btnNext.getY());
                       // chart.setLocation(chart.getX()-50,chart.getY());
                    }
                    else{
                        ((Timer)e.getSource()).stop();
                    }
                }
            });
            time.start();
        }
        else{
            btnMinimize.setIcon(img);
            int X=sidePanel.getX();
            System.out.println(X);
            int newX=X+300;
            int step=100;
            Timer time=new Timer(10, e -> {
                int CurruntX=sidePanel.getX();
                if(CurruntX<newX){
                    sidePanel.setLocation(CurruntX+step, sidePanel.getY());
                    btnMinimize.setLocation(btnMinimize.getX()+step, btnMinimize.getY());
                    lblName.setLocation(lblName.getX()+step, lblName.getY());

                    card1.setLocation(card1.getX()+100, card1.getY());
                    card1.setSize(card1.getWidth()-30, card1.getHeight());
                    lblSoldIcon.setLocation(lblSoldIcon.getX()-30,lblSoldIcon.getY());

                    card2.setLocation(card2.getX()+70, card2.getY());
                    card2.setSize(card2.getWidth()-30,card2.getHeight());

                    card3.setLocation(card3.getX()+40, card3.getY());
                    card3.setSize(card3.getWidth()-30,card3.getHeight());
                    lblEarnIcon.setLocation(lblEarnIcon.getX()-30,lblEarnIcon.getY());

                    lblChart.setLocation(lblChart.getX()+step, lblChart.getY());
                    chart1.setLocation(chart1.getX()+50,chart1.getY());
                    btnPrev.setLocation(btnPrev.getX()+50, btnPrev.getY());
                    btnNext.setLocation(btnNext.getX()+50, btnNext.getY());


                    //chart.setLocation(chart.getX()+50,chart.getY());


                }
                else{
                    ((Timer)e.getSource()).stop();
                }
            });
            time.start();
        }
    }

    public int getIncome(){


        try{

            Statement statement=connection.createStatement();
           /* ResultSet getDate=statement.executeQuery("SELECT * FROM dayCount ");
            while (getDate.next()){
                date=getDate.getInt(1);
            }*/
           ResultSet resultSet=statement.executeQuery("SELECT * FROM days WHERE 1 ");

            while (resultSet.next()){
               total=resultSet.getInt(2);


            }

        }catch (Exception ex){
            System.out.println(ex);
        }
       return total;

    }
    public int AmountOfSoldItems(){
        int soldCount=0;
        try{

            Statement statement=connection.createStatement();
            getDate();

            ResultSet resultSet=statement.executeQuery("SELECT * FROM solditems WHERE day="+day+" ");

            while (resultSet.next()){

                soldCount+=resultSet.getInt(6);
            }
            System.out.println(soldCount);
        }catch (Exception ex){
            System.out.println(ex);
        }
        return soldCount;
    }
    public int totalStock(){

        try{

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");

            while (resultSet.next()){

                stock+=resultSet.getInt(5);
            }
            System.out.println("Stock:"+stock);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return stock;
    }







    public void goPreviousWeeks(int d){

        int day=d;
        //week=(day-today)-7;
        System.out.println("Today is: "+day);

        chart1.setVisible(false);



        for (int i =0; i < salesRate.length; i++) {
            salesRate[i]=0;
        }
        int a=0;
        for (int i =day; i < list.size(); i++) {
            if (a >= 7) {
                break;
            } else {
                salesRate[a] = list.get(i);
                a++;
            }
        }


        setPercentage(salesRate);




    }
    public void setPercentage(int[] salesRate){
        chart1=new BarGraphAxis(salesRate[0],salesRate[1],salesRate[2],salesRate[3],salesRate[4],salesRate[5],salesRate[6]);
        chart1.setOpaque(true);

        chart1.setBounds(560,300,900,1050);
        chart1.setVisible(true);
        homepane.add(chart1);
    }
    public void setWeek(int day,int today,int week){
        this.day=day;
        this.today=today;
        this.week=week;
    }
    public void showthisWeek(){
        getDate();
        lblWeek.setText("Week"+thisWeek);
        clicked=days.size();


        chart1.setVisible(false);
        for (int i =0; i < salesRate.length; i++) {
            salesRate[i]=0;//resetting values
        }
        int a=0;
        for (int i = day-today; i < list.size(); i++) {
            if(a>=7){
                break;
            }
            else {
                salesRate[a]=list.get(i);
                a++;
            }

        }

        setPercentage(salesRate);
        System.out.println("day: "+day);
        System.out.println("Today: "+today);

    }


    public static void main(String[] args){
        new DashboardMng("","").show();
    }

    /*public void createStoreCountTable(){
        try{

            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE  stockcount(amount INT)");


        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }



    public void createDayTable(){
        try{

            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE  days(days INT ,amount INT)");
            statement.execute("INSERT INTO days(days,amount)VALUES(0, 0)");


        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }*/


}
