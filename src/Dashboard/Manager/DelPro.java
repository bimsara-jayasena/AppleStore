package Dashboard.Manager;

import DbConnection.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DelPro extends JFrame {
    JLabel lblTitle=new JLabel("Remove Products");
    JLabel lblName=new JLabel("Item Name");
    JLabel lblId=new JLabel("Item code");

    JTextField txtName=new JTextField();
    JTextField txtId=new JTextField();

    JButton btnConfirm=new JButton("Remove Product");
    JButton btnBack=new JButton("Back");

    int lblX=250;
    int lblY=200;

    int txtX=450;
    int txtY=220;

    String confirmPwd="";

    String name;
    String iD;
     private Connection connection= DbConnection.getConnection();

    public DelPro(String name,String iD){
        this.name=name;
        this.iD=iD;
        getPwd();
        setSize(1024,520);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLayeredPane pane=new JLayeredPane();
        pane.setBounds(0,0,getWidth(),getHeight());
        add(pane);

        lblTitle.setBounds(250,50,getWidth(),100);
        lblTitle.setFont(new Font("",Font.BOLD,60));
        pane.add(lblTitle);

        lblName.setBounds(lblX,lblY,200,60);
        lblName.setFont(new Font("",Font.BOLD,30));
        lblId.setBounds(lblX,lblY+50,200,60);
        lblId.setFont(new Font("",Font.BOLD,30));

        pane.add(lblName);
        pane.add(lblId);

        txtName.setBounds(txtX,txtY,300,30);
        txtName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInfo();
            }
        });
        txtId.setBounds(txtX,txtY+50,300,30);

        pane.add(txtName);
        pane.add(txtId);

        btnConfirm.setBounds(350,350,150,50);
        btnConfirm.setOpaque(true);
        btnConfirm.setBackground(Color.red);
        btnConfirm.setForeground(Color.white);
        pane.add(btnConfirm);
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option=JOptionPane.showConfirmDialog(null,"Confirm deletion?","Remove product",JOptionPane.OK_CANCEL_OPTION);
                if(option==0){
                    JTextField txtPwd=new JTextField("Enter your password:");
                    String pwd=JOptionPane.showInputDialog(null,txtPwd.getText(),"Enter your Password",JOptionPane.OK_CANCEL_OPTION);
                    if(pwd.equals(confirmPwd)){
                        deleteProduct();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Incorrect Password","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        btnBack.setBounds(510,350,150,50);
        btnBack.setOpaque(true);
        btnBack.setBackground(Color.black);
        btnBack.setForeground(Color.white);
        pane.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Inventory(name,iD).show();
            }
        });



    }
    public void deleteProduct(){
        try{

            Statement statement=connection.createStatement();

            statement.execute("DELETE FROM inventory WHERE ItemCode='"+txtId.getText()+"'");
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void getInfo(){
        try{

            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT *FROM inventory WHERE ItemName='"+txtName.getText()+" '" );
            while (resultSet.next()){
                txtId.setText(resultSet.getString(2));
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void getPwd(){
        try{

            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT *FROM users WHERE EmployeeID='"+iD+"'");
            while (resultSet.next()){
                confirmPwd=resultSet.getString(8);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static  void main(String[] args){
        new DelPro("","").show();
    }
}

