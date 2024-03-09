package AccountCreation;

import AccountCreation.AccountType.Casier.casierAccount;

import AccountCreation.AccountType.Manager.ManagerAccount;
import CustomClasses.RoundedButton;
import LogIn.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AccountForm extends JFrame  {

    RoundedButton btnMng=new RoundedButton("Create new Account",Color.white);
    RoundedButton btnBack=new RoundedButton("Go back",Color.white);
    public AccountForm(){
        setSize(1024,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JLayeredPane panel=new JLayeredPane();
        panel.setOpaque(true);
        panel.setBackground(Color.black);
        add(panel);

        ImageIcon icon=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\apple1.png");

        JLabel test=new JLabel();
        test.setBounds(260,120,500,500);
        //test.setOpaque(true);
        //test.setBackground(Color.gray);
        test.setIcon(icon);
        panel.add(test,Integer.valueOf(-1));

        JLabel lblTitle=new JLabel();
        lblTitle.setBounds(100,80,1024,100);
        lblTitle.setText("Welcome to iuxStore");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Articulat CF Demi Bold",Font.BOLD,80));
        panel.add(lblTitle);

        JLabel lblQ=new JLabel();
        lblQ.setBounds(100,200,1024,100);
        lblQ.setText("Who are you?");
        lblQ.setForeground(Color.white);
        lblQ.setFont(new Font("Articulat CF Demi Bold",Font.BOLD,40));
        //panel.add(lblQ);

        //Adding Buttons





        btnMng.setBounds(350,300,300,50);
        btnMng.setOpaque(false);
        btnMng.setFocusable(false);

        btnMng.setForeground(Color.gray);
        btnMng.setFont(new Font("Articulat CF Demi Bold",Font.BOLD,20));
        btnMng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnMngActionPerformed(e);
            }
        });
        panel.add(btnMng);




        btnMng.addMouseListener(new MouseListener() {
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
                btnMng.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMng.setForeground(Color.gray);
            }
        });


        btnBack.setBounds(350,400,300,50);
        btnBack.setOpaque(false);
        btnBack.setFocusable(false);

        btnBack.setForeground(Color.gray);
        btnBack.setFont(new Font("Articulat CF Demi Bold",Font.BOLD,20));

        btnBack.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    dispose();
                    new Login().setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnBack.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBack.setForeground(Color.gray);
            }
        });
        panel.add(btnBack);


        //Add image
        ImageIcon img=new ImageIcon("AppleStore/src/AccountCreation/Untitled-1.png");


        JLabel lblImage=new JLabel();
        lblImage.setBounds(250,100,550,550);
        lblImage.setOpaque(true);
        lblImage.setBackground(new Color(0,0,0,20));
        //lblImage.setIcon(img);
        panel.add(lblImage,Integer.valueOf(-1));

        btnMng.addMouseListener(new MouseListener() {
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
                btnMng.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMng.setForeground(Color.gray);
            }
        });




    }


    public void btnMngActionPerformed(ActionEvent e){
        if(e.getSource()==btnMng){
            this.dispose();
            ManagerAccount a=new ManagerAccount();


        }
    }



    public static void main(String[] args){
        new AccountForm().show();
    }
}
