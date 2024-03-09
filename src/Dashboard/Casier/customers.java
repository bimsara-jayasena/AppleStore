package Dashboard.Casier;

import DbConnection.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class customers extends JFrame {

    JButton btnBack=new JButton("Back");
    private String casName;
    private final Connection connection=DbConnection.getConnection();
    public customers(String casName){
        this.casName=casName;
        setSize(1024,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLayeredPane pane=new JLayeredPane();
        pane.setBounds(0,0,1024,720);
        add(pane);

        JLabel lblTitle=new JLabel();
        lblTitle.setText("Customer details");
        lblTitle.setBounds(180,50,1024,100);
        lblTitle.setFont(new Font("Times New Roman",Font.BOLD,90));
        pane.add(lblTitle);

        String[] columnNames={"Name","E-mail"};
        ArrayList<String> d=new ArrayList<>();


        try{

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM customers");

            while (resultSet.next()){
                String name=resultSet.getString(1);
                String email=resultSet.getString(2);

               d.add(name);
               d.add(email);
            }
        }catch (Exception a){
            System.out.println(a);
        }
        String[][] data=new String[d.size()/2][2];
        int index=0;
        for (int j = 0; j < data.length; j++) {

            data[j][0]=d.get(index);
            data[j][1]=d.get(index+1);
            index+=2;


        }
        for (int j = 0; j < data.length; j++) {
            for (int k = 0; k < 2; k++) {
                System.out.println(data[j][k]);
            }
        }

        JTable table=new JTable(data,columnNames);
       DefaultTableCellRenderer center=new DefaultTableCellRenderer();
       center.setHorizontalAlignment(JLabel.CENTER);
       table.getColumnModel().getColumn(0).setCellRenderer(center);
        table.getColumnModel().getColumn(1).setCellRenderer(center);

        table.setFont(new Font("Times New Roman",Font.BOLD,30));
        table.setRowHeight(35);
        table.setAlignmentX(100);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,200,1024,300);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        table.setFillsViewportHeight(true);
        pane.add(scrollPane);

        btnBack.setBounds(430,550,150,50);
        btnBack.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dashboard(casName).show();
            }
        });
        pane.add(btnBack);




    }
    public static void main(String[] args){
        new customers("").show();
    }
}
