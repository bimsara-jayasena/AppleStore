package Dashboard.Casier;

import CustomClasses.addCellButton;
import DbConnection.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

public class bills extends JFrame {

    JLabel lblTitle=new JLabel();
    JButton btnBack=new JButton();
    JButton btnDel=new JButton();
    JButton btnView;


    private final String name;

    DefaultTableModel tableModel;
    JLayeredPane layeredPane;
    private final Connection connection=DbConnection.getConnection();
    public bills(String name){
        this.name=name;
        setSize(1680,1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layeredPane=new JLayeredPane();

        layeredPane.setBounds(0,0,getWidth(),getHeight());
        add(layeredPane);

        lblTitle.setText("Invoices Information");
        lblTitle.setBounds(420,100,getWidth(),100);
        lblTitle.setFont(new Font("",Font.BOLD,90));
        layeredPane.add(lblTitle);

        addData();

        btnBack.setBounds(layeredPane.getWidth()/2-150,850,300,60);
        btnBack.setText("Back");
        layeredPane.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Dashboard(name).show();
            }
        });






    }
    public void addData(){

        String []columnNames={"ID","Customer","Printed Date","Printed Time","",""};
        tableModel=new DefaultTableModel(columnNames,0);
        JTable table=new JTable(tableModel);
        try{

            Statement statement=connection.createStatement();
            System.out.println("connected");

            ResultSet resultSet=statement.executeQuery("SELECT * FROM invoices");

            while(resultSet.next()){
                int ID=resultSet.getInt(1);
                String name=resultSet.getString(2);
                String date=resultSet.getString(3);
                String time=resultSet.getString(4);
                String view="View";
                String del="Delete";

                tableModel.addRow(new Object[]{ID,name,date,time,view,del});

            }


        }

        catch(Exception ex){
            System.out.println(ex);
        }
        JTableHeader tableHeader=table.getTableHeader();
        tableHeader.setBackground(Color.black);
        tableHeader.setForeground(Color.white);
        tableHeader.setFont(new Font("",Font.BOLD,15));

        DefaultTableCellRenderer tableCellRenderer=new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);


        DefaultTableCellRenderer setColorRed=new DefaultTableCellRenderer();
        setColorRed.setBackground(new Color(255, 0, 0, 240));
        setColorRed.setHorizontalAlignment(JLabel.CENTER);
        setColorRed.setForeground(Color.white);

        DefaultTableCellRenderer setColorBlue=new DefaultTableCellRenderer();
        setColorBlue.setBackground(new Color(9, 121, 252));
        setColorBlue.setForeground(Color.white);
        setColorBlue.setHorizontalAlignment(JLabel.CENTER);

        table.setFont(new Font("",Font.PLAIN,30));
        table.setRowHeight(35);


        for (int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i);

        }


        JScrollPane scrollPane=new JScrollPane(table);

        scrollPane.setBounds(30,300,getWidth()-100,500);
        layeredPane.add(scrollPane);

        int height=table.getRowHeight();
        int x=(scrollPane.getWidth()/columnNames.length)*(table.getColumnCount()-1)+30;
        int width=(scrollPane.getWidth()/columnNames.length);
        int y=(scrollPane.getY()+table.getTableHeader().getY())+(25);
        System.out.println(tableHeader.getHeight());


        btnView=new JButton();
        btnView.setText("View");
        btnView.setOpaque(true);
        btnView.setBackground(new Color(0, 179, 255, 255));
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index=table.getSelectedRow();
                showInvoice(index+1);

            }
        });

        btnDel=new JButton();
        btnDel.setText("Delete");
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index=table.getSelectedRow();
                deleteInvoice(index);
            }
        });


        TableColumn view=table.getColumnModel().getColumn(4);
        view.setCellEditor(new addCellButton(btnView));

        TableColumn del=table.getColumnModel().getColumn(5);
        del.setCellEditor(new addCellButton(btnDel));



        for (int i = 0; i < columnNames.length; i++) {
            if(i==4){
                table.getColumnModel().getColumn(i).setCellRenderer(setColorBlue);

            }
            else if(i==5){
                table.getColumnModel().getColumn(i).setCellRenderer(setColorRed);

            }
            else {
                table.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);
            }

        }







    }
    public void showInvoice(int invoiceID){
        String path="C:\\Users\\Dragon\\Desktop\\Invoice-"+invoiceID+".pdf";
        try{
            File file=new File(path);
            if(Desktop.isDesktopSupported()){
                Desktop desktop=Desktop.getDesktop();
                desktop.open(file);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        new bills(name).show();
    }
    public void deleteInvoice(int invoiceID){
        layeredPane.remove(btnDel);
        layeredPane.remove(btnView);
        tableModel.removeRow(invoiceID);


        try{


            Statement statement=connection.createStatement();

            statement.execute("DELETE FROM invoices WHERE invoiceId="+(invoiceID+1)+" ");
            JOptionPane.showMessageDialog(null,"Invoice-"+invoiceID+1+"Deleted ","",JOptionPane.WARNING_MESSAGE);

        }
        catch (Exception ex){
            System.out.println(ex);
        }

        //new bills(name).show();

    }
    public static void main(String[] args){
        new bills("").show();
    }
}
