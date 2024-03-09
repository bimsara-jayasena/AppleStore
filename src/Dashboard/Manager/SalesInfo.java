package Dashboard.Manager;



import DbConnection.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class SalesInfo extends JFrame {
    JButton btnBack=new JButton("back");
    JButton btnPrv=new JButton("Previous day");
    JButton btnTdy=new JButton("Today");

    JLabel lblStock=new JLabel("Sold");

    String mngName;
    String empID;
    int count;
    int total=0;
    int day=0;
    JLayeredPane pane=new JLayeredPane();
    DefaultTableModel tableModel;
    private Connection connection= DbConnection.getConnection();
    public SalesInfo(String mngName,String empID){

        this.mngName=mngName;
        this.empID=empID;
        setSize(1680,1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        pane.setBounds(0,0,1680,1050);
        add(pane);
        getDate();

        JLabel lblTitle=new JLabel();
        lblTitle.setText("Sales Info");
        lblTitle.setBounds(480,50,1024,100);
        lblTitle.setOpaque(true);

        lblTitle.setFont(new Font("Times New Roman",Font.BOLD,90));
        pane.add(lblTitle);


        lblStock.setBounds(90,150,1024,100);
        lblStock.setOpaque(true);
        lblStock.setFont(new Font("Times New Roman",Font.BOLD,30));
        pane.add(lblStock);





        btnPrv.setBounds(550,550,150,50);
        btnPrv.setBackground(Color.black);
        btnPrv.setForeground(Color.white);
        btnPrv.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnPrv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                day--;
                if(day>0) {
                    showPrv();
                }
            }
        });
        pane.add(btnPrv);
        btnTdy.setBounds(750,550,150,50);
        btnTdy.setOpaque(true);
        btnTdy.setBackground(Color.black);
        btnTdy.setForeground(Color.white);
        btnTdy.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnTdy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSoldItems();

            }
        });
        pane.add(btnTdy);

        btnBack.setBounds(950,550,150,50);

        btnBack.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnBack.setOpaque(true);
        btnBack.setBackground(Color.black);
        btnBack.setForeground(Color.white);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack(e);
            }
        });
        pane.add(btnBack);
        showSoldItems();


//



    }
    public void getDate(){
            try{

                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("SELECT* FROM days");
                while (resultSet.next()){
                    day=resultSet.getInt(1);
                }
            }
            catch(Exception ex){
                System.out.println(ex);
            }
    }
    public void showPrv(){
        DefaultTableModel tableModel2;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/iluxstore","root","");
            Statement statement=connection.createStatement();
            ResultSet resultSet2=statement.executeQuery("SELECT * FROM solditems WHERE day="+day+" ");

            tableModel.setRowCount(0);//resetting values
            while (resultSet2.next()) {


                String name = resultSet2.getString(2);
                String code = resultSet2.getString(3);
                String qty = resultSet2.getString(5);

                // Add a new row to the table model
                tableModel.addRow(new Object[]{name, code,qty});

            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void goBack(ActionEvent e){
        if(e.getSource()==btnBack){
            this.dispose();

            DashboardMng d=new DashboardMng(mngName,empID);



            // d.setCount(50);
            d.show();
        }
    }



    public void showSoldItems(){
        getDate();
        String[] columnNames={"Item Name","Item Code","Qty"};
        tableModel=new DefaultTableModel(columnNames,0);

        int i=0;
        //Creating Connection and retriew data
      // getSoldItems();
        try{

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM soldItems WHERE day="+day+" ");

            while (resultSet.next()){


                    String name = resultSet.getString(2);
                    String code = resultSet.getString(3);

                    String qty = resultSet.getString(5);
                    tableModel.addRow(new Object[]{name,code,qty});


            }
        }
        catch (Exception a){
            System.out.println(a);
        }




        JTable table=new JTable(tableModel);
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(center);
        table.getColumnModel().getColumn(1).setCellRenderer(center);
        table.getColumnModel().getColumn(2).setCellRenderer(center);

        table.setDefaultRenderer(String.class,center);

        table.setFont(new Font("Times New Roman",Font.BOLD,30));
        table.setRowHeight(35);
        table.setAlignmentX(100);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,250,1680,300);
        scrollPane.setOpaque(true);
        scrollPane.setBackground(getBackground());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        table.setFillsViewportHeight(true);
        pane.add(scrollPane);
    }

    public static void main(String[] args){
       new SalesInfo("","").show();
    }
}
