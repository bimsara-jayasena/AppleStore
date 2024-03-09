package AccountCreation.AccountType.Manager;




import DbConnection.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Confirmation extends JFrame {

    JLayeredPane panel=new JLayeredPane();
    JLabel lblTitle=new JLabel("We need to confirm this is really you");



    JButton btnBack=new JButton("I need to Check Again");
    JButton btnDone=new JButton("Done");
    private final String fName;
    private final String lName;
    private final String mail;
    private final String addrs;
    private final String pwd;
    private final String empID;
    private final int tp;
    private final String job;
    private final Connection connection=DbConnection.getConnection();

     public Confirmation(String fname,String lname,String empID,String job,String mail,int tp,String addrs,String pwd){
         this.fName=fname;
         this.lName=fname;
         this.empID=empID;
         this.job=job;
         this.mail=mail;
         this.tp=tp;
         this.addrs=addrs;
         this.pwd=pwd;
        setSize(600,510);
        //getContentPane().setBackground(Color.green);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        panel.setBounds(0,0,600,410);
        add(panel);

        lblTitle.setBounds(100,20,600,100);

        lblTitle.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblTitle);









         btnBack.setBounds(130,260,300,50);
         btnBack.setFocusable(false);
         btnBack.setBackground(Color.WHITE);
         btnBack.setForeground(Color.GRAY);
         btnBack.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
         btnBack.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 checkAgain(e);
             }
         });
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
                 btnBack.setBackground(Color.GRAY);
                 btnBack.setForeground(Color.WHITE);
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 btnBack.setBackground(Color.WHITE);
                 btnBack.setForeground(Color.GRAY);
             }
         });
         panel.add(btnBack);

         btnDone.setBounds(130,340,300,50);
         btnDone.setFocusable(false);
         btnDone.setBackground(Color.WHITE);
         btnDone.setForeground(Color.GRAY);
         btnDone.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
         btnDone.addMouseListener(new MouseListener() {
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
                 btnDone.setBackground(Color.GRAY);
                 btnDone.setForeground(Color.WHITE);
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 btnDone.setBackground(Color.WHITE);
                 btnDone.setForeground(Color.GRAY);
             }
         });
         btnDone.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 btnDoneActionPerformed(e);
             }
         });
         panel.add(btnDone);

    }

    public void btnDoneActionPerformed(ActionEvent e){
        if(e.getSource()==btnDone){

            try{


                Statement statement=connection.createStatement();
                statement.execute("INSERT INTO users(f_Name,l_Name,EmployeeID,JobRoll,Email,ContactNo,Address,Password) VALUES('"+fName+"','"+lName+"','"+empID+"','"+job+"','"+mail+"','"+tp+"','"+addrs+"','"+pwd+"')");
                JOptionPane.showMessageDialog(null,"Account created","created", JOptionPane.WARNING_MESSAGE);
                this.dispose();
                 new AllDone(fName,empID).show();

            }catch (Exception a){
                System.out.println(a);
            }
            finally {
                DbConnection.closeConnection();
            }

        }
    }
    public void checkAgain(ActionEvent e){
        if(e.getSource()==btnBack){
            this.dispose();
            ManagerAccount m=new ManagerAccount();
            m.setInfo(fName,lName, mail, addrs, pwd, empID, tp);
            m.show();
        }
    }


}