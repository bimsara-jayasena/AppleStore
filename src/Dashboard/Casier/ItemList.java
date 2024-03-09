package Dashboard.Casier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemList extends JFrame {
    JLayeredPane panel = new JLayeredPane();

    JButton btnBack = new JButton("Back");
    JLabel lblTitle = new JLabel("Available Products");
    private String name;

    public ItemList(String name) {
        this.name = name;
        setSize(1024, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        panel.setBounds(0, 0, 1024, 720);
        add(panel);

        lblTitle.setBounds(200, 0, 612, 100);
        lblTitle.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 60));
        panel.add(lblTitle);

        btnBack.setBounds(300, 300, 300, 100);
        btnBack.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack(e);
            }
        });
        panel.add(btnBack);


    }

    public void goBack(ActionEvent e) {
        if (e.getSource() == btnBack) {
            Dashboard d = new Dashboard(name);

        }
    }
}

