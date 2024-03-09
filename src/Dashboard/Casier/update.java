package Dashboard.Casier;



import DbConnection.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class update extends JFrame {

     String font="Articulat CF Demi Bold";
    JLayeredPane pane=new JLayeredPane();
    JLabel lblTitle=new JLabel();
    JButton btnAdd=new JButton();
    JButton btnBack=new JButton();
    JButton btnDel=new JButton();
    JLabel lblName=new JLabel();

    JLabel lblEmpId=new JLabel();
    JLabel lblMail=new JLabel();
    JLabel lblAddrs=new JLabel();
    JLabel lblTp=new JLabel();
    JLabel lblPwd=new JLabel();

    JLabel lbluserName=new JLabel();
    JLabel lbluserID=new JLabel();

    JTextField txtMail=new JTextField();

    JTextField txtAddrss=new JTextField();
    JTextField txtTp=new JTextField();
    JTextField txtPwd=new JTextField();
    String name;
    int lblX=500;
    int txtX=850;

    int empID;

    String TruePwd;
    private Connection connection= DbConnection.getConnection();
    public update(String name,int ID){
        this.name=name;
        empID=ID;
        getInfo();
        setSize(1680,1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pane.setBounds(0,0,1024,720);
        add(pane);

        lblTitle.setText("Update Your Informations");
        lblTitle.setBounds(500,10,1024,100);
        lblTitle.setFont(new Font(font,Font.BOLD,50));
        pane.add(lblTitle);

        lblName.setText("Name");
        lblName.setBounds(lblX,200,150,50);
        lblName.setFont(new Font(font,Font.BOLD,30));
        pane.add(lblName);

        lblEmpId.setText("Employee ID");
        lblEmpId.setBounds(lblX,300,350,50);
        lblEmpId.setFont(new Font(font,Font.BOLD,30));
        pane.add(lblEmpId);

        lblMail.setText("E-Mail");
        lblMail.setBounds(lblX,400,150,50);
        lblMail.setFont(new Font(font,Font.BOLD,30));
        pane.add(lblMail);

        lblAddrs.setText("Address");
        lblAddrs.setBounds(lblX,500,150,50);
        lblAddrs.setFont(new Font(font,Font.BOLD,30));
        pane.add(lblAddrs);

        lblTp.setText("Contact No:");
        lblTp.setBounds(lblX,600,250,50);
        lblTp.setFont(new Font(font,Font.BOLD,30));
        pane.add(lblTp);

        lblPwd.setText("New Password");
        lblPwd.setBounds(lblX,700,250,50);
        lblPwd.setFont(new Font(font,Font.BOLD,30));
        pane.add(lblPwd);



        lbluserName.setBounds(txtX,200,300,50);
        lbluserName.setText(name);
        lbluserName.setFont(new Font(font,Font.BOLD,30));
        pane.add(lbluserName);

        lbluserID.setBounds(txtX,300,300,50);
        lbluserID.setText(""+empID);
        lbluserID.setFont(new Font(font,Font.BOLD,30));
        pane.add(lbluserID);

        txtMail.setBounds(txtX,400,300,50);
        pane.add(txtMail);

        txtAddrss.setBounds(txtX,500,300,50);
        pane.add(txtAddrss);

        txtTp.setBounds(txtX,600,300,50);
        pane.add(txtTp);

        txtPwd.setBounds(txtX,700,300,50);
        pane.add(txtPwd);

        btnAdd.setBounds(450,800,300,60);
        btnAdd.setText("Update");
        btnAdd.setOpaque(true);
        btnAdd.setBackground(Color.gray);
        btnAdd.setForeground(Color.white);
        btnAdd.setFocusable(false);
        btnAdd.setFont(new Font(font,Font.BOLD,30));
        pane.add(btnAdd);
        btnAdd.addMouseListener(new MouseListener() {
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
                btnAdd.setBackground(Color.BLACK);
                btnAdd.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(Color.gray);
                btnAdd.setForeground(Color.white);
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //updateInfo();
            }
        });


        btnBack.setBounds(900,800,300,60);
        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.setBackground(Color.gray);
        btnBack.setForeground(Color.white);
        btnBack.setFocusable(false);
        btnBack.setFont(new Font(font,Font.BOLD,30));
        btnBack.addMouseListener(new MouseListener() {
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
                btnBack.setBackground(Color.BLACK);
                btnBack.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBack.setBackground(Color.gray);
                btnBack.setForeground(Color.white);
            }
        });
        btnBack.addActionListener(e -> {
            dispose();
            new Dashboard(name).show();
        });
        pane.add(btnBack);






    }
    public void updateInfo(){


        try {

            Statement statement = connection.createStatement();


            ResultSet resultSet=statement.executeQuery("SELECT * FROM users WHERE name='"+name+"'");
            while (resultSet.next()){

                TruePwd=resultSet.getString(6);
                break;
            }

            JTextField Pwd=new JTextField();
            int pwd=JOptionPane.showConfirmDialog(null,Pwd,"Password",JOptionPane.OK_CANCEL_OPTION);

            if(pwd==0){
                if(Pwd.getText().equals(TruePwd)){

                    try {
                        statement.execute("UPDATE cashier SET Email='"+txtMail.getText()+"' WHERE name='"+name+"'");
                        statement.execute("UPDATE cashier SET ContactNo="+txtTp.getText()+" WHERE name='"+name+"'");
                        statement.execute("UPDATE cashier SET Address='"+txtAddrss.getText()+"' WHERE name='"+name+"'");
                        statement.execute("UPDATE cashier SET Password='"+txtPwd.getText()+"' WHERE name='"+name+"'");
                        JOptionPane.showMessageDialog(null,"success","done",JOptionPane.CANCEL_OPTION);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"err","done",JOptionPane.CANCEL_OPTION);
                }
            }





        }
        catch (Exception a){
            System.out.println(a);
        }
    }
    public void getInfo(){
        try {

            Statement statement = connection.createStatement();


            ResultSet resultSet=statement.executeQuery("SELECT * FROM cashier WHERE name='"+name+"'");
            while (resultSet.next()){
                txtMail.setText(resultSet.getString(3));
                txtTp.setText(resultSet.getString(4));
                txtAddrss.setText(resultSet.getString(5));
                txtPwd.setText(resultSet.getString(6));
            }





        }
        catch (Exception a){
            System.out.println(a);
        }
    }

}

