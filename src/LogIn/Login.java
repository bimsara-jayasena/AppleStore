package LogIn;

import AccountCreation.AccountForm;
import CustomClasses.LinePwField;
import CustomClasses.LineTextField;
import CustomClasses.RoundedButton;
import Dashboard.Casier.Dashboard;

import Dashboard.Manager.DashboardMng;
import DbConnection.DbConnection;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;



public class Login extends JFrame {

    int isCreated=0;
    JLayeredPane homePanel=new JLayeredPane();

    JPanel superPanel2 = new JPanel();


    JLabel lblTitle = new JLabel();



    JLabel lblImg = new JLabel();



   ImageIcon image=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\LogIn\\Screenshot (666).png");


    RoundedButton btnStart = new RoundedButton("Open",Color.GRAY);

    LinePwField txtPwd=new LinePwField();
    LineTextField txtp=new LineTextField();
    LineTextField txtName=new LineTextField();

    RoundedButton btnCreate=new RoundedButton("I Don't have an account!",Color.black);
    RoundedButton btnLog=new RoundedButton("Log in",Color.black);

    private final Connection connection= DbConnection.getConnection();



    public Login() {


        setSize(1280, 900);
        //setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);

        //===========HOME PANEL================

        homePanel.setBounds(0,0,1280,900);
        homePanel.setOpaque(true);
        homePanel.setBackground(Color.BLACK);
        add(homePanel);

        //add title


        lblTitle.setBounds(120,50,1280,100);
        lblTitle.setText("Welcome to the iLuxStore");
        lblTitle.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 80));
        lblTitle.setForeground(Color.white);
        homePanel.add(lblTitle);

        //add image


        lblImg.setBounds(100,20,1280,900);
        lblImg.setIcon(image);
        homePanel.add(lblImg);

        //add button
        btnStart.setBounds(450,750,300,50);
        btnStart.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 25));
        btnStart.setForeground(Color.white);
        btnStart.setFocusable(false);
        btnStart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnStart.setColor(Color.WHITE);
                btnStart.setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnStart.setColor(Color.gray);
                btnStart.setForeground(Color.white);
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanelUp(homePanel);
            }
        });
        homePanel.add(btnStart);

        superPanel2.setBackground(Color.GRAY);
        superPanel2.setLayout(new BorderLayout());
        superPanel2.setBounds(0, 0, 1280, 900);
        add(superPanel2);


        /*------------------------------------------RIGHT PANEL----------------------------------------*/

        JPanel leftPanel=new JPanel();

        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.white);
        leftPanel.setPreferredSize(new Dimension(640,900));
        superPanel2.add(leftPanel,BorderLayout.WEST);

        JLayeredPane layeredPaneL=new JLayeredPane();
        layeredPaneL.setBounds(0,0,640,900);
        leftPanel.add(layeredPaneL);

        JLabel lblWelcome=new JLabel("Welcome");
        lblWelcome.setForeground(Color.white);
        lblWelcome.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 100));
        lblWelcome.setBounds(90,350,500,120);
        layeredPaneL.add(lblWelcome);

        ImageIcon image1=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\LogIn\\Untitled-1.png");

        JLabel lblImg=new JLabel();
        lblImg.setBounds(-150,90,966,966);
        lblImg.setIcon(image1);
        layeredPaneL.add(lblImg);


        /*------------------------------------CREATING RIGHT PANEL------------------------------------------------*/



        JPanel rightPanel=new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.white);
        rightPanel.setPreferredSize(new Dimension(640,900));
        superPanel2.add(rightPanel,BorderLayout.EAST);

        //adding layerdPane
        JLayeredPane layeredPane=new JLayeredPane();
        layeredPane.setBounds(0,0,640,900);
        rightPanel.add(layeredPane);



        JLabel lblSign=new JLabel("Sign up");
        lblSign.setForeground(Color.BLACK);
        lblSign.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 100));
        lblSign.setBounds(110,200,400,110);
        layeredPane.add(lblSign);




        txtName.setBounds(110,395,400,25);
        txtName.setText("Employee ID");
        txtName.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        txtName.setForeground(new Color(0,0,0,100));

        txtName.setVisible(false);
        txtName.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtName.setText("");
                txtName.setForeground(Color.black);


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        layeredPane.add(txtName);




        txtPwd.setBounds(110,450,400,25);
        txtPwd.setText("piopipip");

        txtPwd.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        txtPwd.setForeground(new Color(0,0,0,100));

        txtPwd.setVisible(false);
        txtPwd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtPwd.setText("");
                txtPwd.setForeground(Color.black);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        layeredPane.add(txtPwd);
        layeredPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtName.setText("User Name");
                txtName.setForeground(new Color(0,0,0,100));
                txtPwd.setText("Password");
                txtPwd.setForeground(new Color(0,0,0,100));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        txtp.setBounds(110,450,400,25);
        txtp.setText("p");
        txtp.setVisible(false);
        txtp.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        txtp.setForeground(new Color(0,0,0,100));
        txtp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

               layeredPane.remove(txtp);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //layeredPane.add(txtp,Integer.valueOf(3));


        btnLog.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));

        btnLog.setFocusable(false);
        btnLog.setBounds(210,500,200,40);
        //btn1.setOpaque(true);
        btnLog.setBackground(Color.black);
        btnLog.setForeground(Color.white);

        btnLog.setVisible(false);
        btnLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);

            }
        });


        btnCreate.setBackground(Color.DARK_GRAY);
        btnCreate.setForeground(Color.white);
        btnCreate.setFocusable(false);
        btnCreate.setVisible(false);
        btnCreate.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnCreate.setBounds(160,550,300,35);
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnActionPerformed(e);
            }
        });

        //Add image

        layeredPane.add(btnLog,Integer.valueOf(1));
        layeredPane.add(btnCreate,Integer.valueOf(1));




        //rightPanel.setLayout();

    }
    public void homePanelUp(JLayeredPane homePanel){
        //createTables();
        isCreated++;
        int homePanelY=homePanel.getY();
        int finalY=homePanelY-900;
        int step=100;
        Timer time=new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int curruntY=homePanel.getY();
                if(curruntY>finalY){
                    homePanel.setLocation(homePanel.getX(),curruntY-step);
                }
                else {
                    ((Timer)e.getSource()).stop();
                }

            }
        });
        time.start();
        btnLog.setVisible(true);
        btnCreate.setVisible(true);
        txtName.setVisible(true);
        txtPwd.setVisible(true);
        txtp.setVisible(true);





    }
    public void btnActionPerformed(ActionEvent e){
        if(e.getSource()==btnCreate){
            this.dispose();
            AccountForm q=new AccountForm();



        }
    }

    public void loginActionPerformed(ActionEvent e){
        try{

            Statement statement=connection.createStatement();

            String name=txtName.getText();
            String userName="";
            String pwd=txtPwd.getText();
            String manager="Manager";
            String cashier="Cashier";

            boolean notAvailble=true;


            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE EmployeeID='"+name+"'");
            while (resultSet.next()){
                notAvailble=false;
                if (manager.equals(resultSet.getString(4))) {
                    if(pwd.equals(resultSet.getString(8))){
                        userName=resultSet.getString(1);
                        String empID=resultSet.getString(3);
                        this.dispose();
                        new DashboardMng(userName,empID).show();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Password is not correct","error",JOptionPane.WARNING_MESSAGE);
                        txtPwd.setForeground(Color.red);
                    }
                }
                else if(cashier.equals(resultSet.getString(4))){

                    if(pwd.equals(resultSet.getString(8))){
                        userName=resultSet.getString(1);
                        this.dispose();
                        new Dashboard(userName).show();
                    }
                    else {
                        txtPwd.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null,"Password is not correct","error",JOptionPane.WARNING_MESSAGE);
                    }

                }


            }
            if(notAvailble){
                JOptionPane.showMessageDialog(null,"user name is not correct","error",JOptionPane.WARNING_MESSAGE);
            }




        }catch (Exception a){
            System.out.println(a);
        }
    }

    public void createTables(){
        try {
            int i=0;
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE users(f_Name VARCHAR(255),l_Name VARCHAR(255),EmployeeID VARCHAR(5) PRIMARY KEY NOT NULL,JobRoll VARCHAR(255),Email VARCHAR(255),ContactNo INT(10),Address VARCHAR(255),Password VARCHAR(255))");
            statement.execute("CREATE TABLE inventory(ItemName VARCHAR(255),ItemCode INT(5)  PRIMARY KEY NOT NULL,catagory VARCHAR(255),Price VARCHAR(255),Qty INT(3))");
            statement.execute("CREATE TABLE invoices(invoiceId VARCHAR(5) PRIMARY KEY NOT NULL,customerName VARCHAR(255),printedDate VARCHAR(255),printedTime VARCHAR(255))");
            statement.execute("CREATE TABLE  days(days INT PRIMARY KEY NOT NULL,amount INT)");//add a data*/
            statement.execute("CREATE TABLE  solditems(transactionID INT PRIMARY KEY NOT NULL,day INT,Item VARCHAR(255),code INT,price INT,Qty INT,total INT)");

            ResultSet resultSet=statement.executeQuery("SELECT*FROM days");
            while (resultSet.next()){
                i++;
            }
            if(i==0) {
                statement.execute("INSERT INTO days(days,amount)VALUES(" + 1 + ", 0)");
            }


        } catch (Exception a) {
            System.out.println(a);
        }
    }


    public static void main(String[] args){
        new Login().show();
    }
}