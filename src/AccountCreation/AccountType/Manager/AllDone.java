package AccountCreation.AccountType.Manager;



import CustomClasses.RoundedButton;
import Dashboard.Manager.DashboardMng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AllDone extends JFrame {
    JLayeredPane pane = new JLayeredPane();
    JLabel lblTitle = new JLabel("All Done!");
    private String name;
    private String ID;


    RoundedButton btnGo=new RoundedButton("Let's GO",Color.gray);

    public AllDone(String name,String ID) {
        this.name=name;
        setSize(600, 300);
        //getContentPane().setBackground(Color.green);
        setLocationRelativeTo(null);
        setVisible(true);

        pane.setBounds(0, 0, 600, 300);
        pane.setOpaque(true);
        //pane.setBackground(Color.green);
        add(pane);

        lblTitle.setBounds(145,-90,600,300);
        lblTitle.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 65));
        //lblTitle.setOpaque(true);
        pane.add(lblTitle);

        btnGo.setBounds(180,150,200,60);

        btnGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGoActionPerformed(e);
            }
        });
        btnGo.addMouseListener(new MouseListener() {
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
                btnGo.setColor(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnGo.setColor(Color.gray);
            }
        });
        pane.add(btnGo);


    }

    public void btnGoActionPerformed(ActionEvent e) {
        if (e.getSource() == btnGo) {
            this.dispose();
           DashboardMng d=new DashboardMng(name,ID);

           d.show();
        }
    }

   /* public static void main(String[] args){
        new AllDone().show();
    }*/
}

