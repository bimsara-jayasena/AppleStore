package Dashboard.Manager;

import CustomClasses.RoundTextField;
import CustomClasses.RoundedButton;
import DbConnection.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.SimpleTimeZone;

public class Inventory extends JFrame {
    JButton btnBack=new JButton("back");
    JButton btnAdd=new JButton("Add Items");
    JButton btnUpd=new JButton("Update Item");



    JButton btnRmv=new JButton("Remove Items");

    JLabel lblStock=new JLabel("In Stock");
    JLabel lblLow=new JLabel("Low In Stock");

    RoundTextField txtSearch=new RoundTextField();


    JComboBox<String> searchType;
    RoundedButton btnSearch=new RoundedButton("",Color.white);
    RoundedButton btnRefresh=new RoundedButton("",Color.white);

    private final String mngName;
    private final String iD;
    int count;


    int btnX=450;

    boolean isAvailble=false;

    DefaultTableModel tableModel;
    JLayeredPane pane=new JLayeredPane();
    private Connection connection= DbConnection.getConnection();

    public Inventory(String mngName,String iD){
        this.mngName=mngName;
        this.iD=iD;
        setSize(1680,1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        pane.setBounds(0,0,1680,1050);
        add(pane);

        JLabel lblTitle=new JLabel();
        lblTitle.setText("Inventory details");
        lblTitle.setBounds(480,50,1024,100);
        lblTitle.setOpaque(true);

        lblTitle.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 90));
        pane.add(lblTitle);


        lblStock.setBounds(90,250,150,50);
        lblStock.setOpaque(true);
        lblStock.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        pane.add(lblStock);

        lblLow.setBounds(90,700,250,100);
        lblLow.setOpaque(true);

        lblLow.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        pane.add(lblLow);



        btnAdd.setBounds(btnX,650,150,50);
        btnAdd.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnAdd.setOpaque(true);
        btnAdd.setBackground(new Color(0, 0, 0, 255));
        btnAdd.setForeground(Color.white);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new AddProducts(mngName,iD).show();
            }
        });
        pane.add(btnAdd);

        btnUpd.setBounds(btnX+200,650,150,50);
        btnUpd.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnUpd.setOpaque(true);
        btnUpd.setBackground(new Color(0, 0, 0, 255));
        btnUpd.setForeground(Color.white);
        btnUpd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new updateItem(mngName,iD).show();
            }
        });
        pane.add(btnUpd);

        btnRmv.setBounds(btnX+400,650,150,50);
        btnRmv.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnRmv.setOpaque(true);
        btnRmv.setBackground(new Color(255, 86, 86));
        btnRmv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                new DelPro(mngName,iD).show();
            }
        });
        pane.add(btnRmv);

        btnBack.setBounds(btnX+600,650,150,50);
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

        stockTable();
        lowStockTable();

        txtSearch.setBounds(550,150,500,50);
        txtSearch.setText("enter the item code");
        txtSearch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtSearch.setText("");
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
        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // txtSearch.setText("");
                searchItems(txtSearch.getText(),(String) searchType.getSelectedItem());
            }
        });
        pane.add(txtSearch,Integer.valueOf(2));

        String[] list= {"-Select what you want search by-","Item name","Item Price","Item Code","Item Catagory"};
        searchType=new JComboBox<>(list);
        searchType.setBounds(650,220,300,30);
        pane.add(searchType);
        searchType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeText();
            }
        });



        ImageIcon icon=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\search.png");
        ImageIcon iconRef=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\ref.png");


        btnSearch.setBounds(1000,152,45,45);
        btnSearch.setIcon(icon);
        btnSearch.setFocusable(false);
        pane.add(btnSearch,Integer.valueOf(3));
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchItems(txtSearch.getText(),(String) searchType.getSelectedItem());
            }
        });
        btnRefresh.setBounds(1060,152,45,45);
        btnRefresh.setIcon(iconRef);
        btnRefresh.setFocusable(false);
        pane.add(btnRefresh,Integer.valueOf(3));
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Inventory(mngName,iD).show();
            }
        });
//



    }
    public void changeText(){

        if(searchType.getSelectedItem().equals("Item name")){
            txtSearch.setText("enter item name");
        }
        if(searchType.getSelectedItem().equals("Item Code")){
            txtSearch.setText("enter item Code");
        }
        if(searchType.getSelectedItem().equals("Item Price")){
            txtSearch.setText("enter item Price");
        }
        if(searchType.getSelectedItem().equals("Item Catagory")){
            txtSearch.setText("enter item catagory");
        }
    }

    public void goBack(ActionEvent e){
        if(e.getSource()==btnBack){
            this.dispose();

            DashboardMng d=new DashboardMng(mngName,iD);



            // d.setCount(50);
            d.show();
        }
    }

    public void stockTable(){
        String[] columnNames={"Item Name","Item Code","Catagory","Price(LKR)","Qty"};
     tableModel=new DefaultTableModel(columnNames,0);

        int i=0;
        //Creating Connection and retriew data
        //createTable();
        try{

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");

            while (resultSet.next()){
                String name=resultSet.getString(1);
                String code=resultSet.getString(2);
                String cat=resultSet.getString(3);
                String price=resultSet.getString(4);
                String qty=resultSet.getString(5);

               tableModel.addRow(new Object[]{name,code,cat,price,qty});
            }
        }
        catch (Exception a){
            System.out.println(a);
        }


        JTable table=new JTable(tableModel);

        //Customize the table header
        JTableHeader header=table.getTableHeader();
        header.setBackground(Color.gray);
        header.setForeground(Color.white);
        header.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));

        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer left=new DefaultTableCellRenderer();
        left.setHorizontalAlignment(JLabel.LEFT);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(0).setCellRenderer(left);
        table.getColumnModel().getColumn(1).setCellRenderer(center);
        table.getColumnModel().getColumn(2).setCellRenderer(center);
        table.getColumnModel().getColumn(3).setCellRenderer(center);
        //table.getColumnModel().getColumn(4).setCellRenderer(center);
        /*table.setDefaultRenderer(String.class,center);*/

        table.setFont(new Font("Articulat CF Demi Bold",Font.BOLD,15));
        table.setRowHeight(35);
        table.setAlignmentX(100);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(40,300,1600,300);
        scrollPane.setOpaque(true);
        scrollPane.setBackground(getBackground());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        table.setFillsViewportHeight(true);
        pane.add(scrollPane);
    }
    public void lowStockTable(){
        String[] columnNames={"Item Name","Item Code","Catagory","Price(LKR)","Qty"};
        ArrayList<String> d=new ArrayList<>();

        int i=0;
        //Creating Connection and retriew data
        //createTable();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/iluxstore","root","");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");

            while (resultSet.next()){
                int AvailableQty=resultSet.getInt(5);
                if(AvailableQty<=10) {
                    String name = resultSet.getString(1);
                    String code = resultSet.getString(2);
                    String cat = resultSet.getString(3);
                    String price = resultSet.getString(4);

                    String qty = resultSet.getString(5);

                    d.add(name);
                    d.add(code);
                    d.add(cat);
                    d.add(price);
                    d.add(qty);
                }
            }
        }
        catch (Exception a){
            System.out.println(a);
        }
        String[][] data=new String[d.size()/5][5];
        count=data.length;
        //add data to the table
        int index = 0;
        for (int j = 0; j < data.length; j++) {
            data[j][0] = d.get(index);
            data[j][1] = d.get(index + 1);
            data[j][2] = d.get(index + 2);
            data[j][3] = d.get(index + 3);
            data[j][4] = d.get(index + 4);
            index += 5;
        }

        JTable table=new JTable(data,columnNames);
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(center);
        table.getColumnModel().getColumn(1).setCellRenderer(center);
        table.getColumnModel().getColumn(2).setCellRenderer(center);
        table.getColumnModel().getColumn(3).setCellRenderer(center);
        table.getColumnModel().getColumn(4).setCellRenderer(center);
        /*table.setDefaultRenderer(String.class,center);*/

        table.setFont(new Font("Times New Roman",Font.BOLD,15));
        table.setRowHeight(35);
        table.setAlignmentX(100);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,800,1680,300);
        scrollPane.setOpaque(true);
        scrollPane.setBackground(getBackground());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        table.setFillsViewportHeight(true);
        pane.add(scrollPane);
    }
    public void createTable(){
        try{

            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE  inventory(ItemName VARCHAR(255),ItemCode INT,Price INT,Qty INT)");
            System.out.println("table created successfully");

        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void searchItems(String value,String type){
        try{
            String itemName=value;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");

            if(type.equals("Item name")){

            while (resultSet.next()){
                if(resultSet.getString(1).toLowerCase().contains(value.toLowerCase())){
                    itemName=resultSet.getString(1);
                    isAvailble=true;
                    break;
                }
            }
            if(isAvailble){
                JOptionPane.showMessageDialog(null,"found","",JOptionPane.CANCEL_OPTION);
                ResultSet resultSet2=statement.executeQuery("SELECT * FROM inventory WHERE ItemName='"+itemName+"' ");
                tableModel.setRowCount(0);
                while (resultSet2.next()){
                    String itemname=resultSet2.getString(1);
                    String code=resultSet2.getString(2);
                    String cat=resultSet2.getString(3);
                    String price=resultSet2.getString(4);
                    String qty=resultSet2.getString(5);

                    tableModel.addRow(new Object[]{itemname,code,cat,price,qty});
                }
            }
            }
            else if(type.equals("Item Code")){
                while (resultSet.next()){
                    if(value.equals(resultSet.getString(2))){
                        isAvailble=true;
                        break;
                    }
                }
                if(isAvailble){
                    JOptionPane.showMessageDialog(null,"found","",JOptionPane.CANCEL_OPTION);
                    ResultSet resultSet2=statement.executeQuery("SELECT * FROM inventory WHERE ItemCode="+value+" ");
                    tableModel.setRowCount(0);
                    while (resultSet2.next()){
                        String itemname=resultSet2.getString(1);
                        String code=resultSet2.getString(2);
                        String cat=resultSet2.getString(3);
                        String price=resultSet2.getString(4);
                        String qty=resultSet2.getString(5);

                        tableModel.addRow(new Object[]{itemname,code,cat,price,qty});
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"No items found","Empty",JOptionPane.CANCEL_OPTION);
                }
            }
            else if(type.equals("Item Price")){
                    if(!isInteger(value)){
                        JOptionPane.showMessageDialog(null,"incorrect type","error",JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        int enterdValue=Integer.valueOf(value);

                        while (resultSet.next()){

                           int price=resultSet.getInt(4);
                           if(enterdValue==price){
                               isAvailble=true;
                               break;
                           }
                        }
                        if(isAvailble){
                            JOptionPane.showMessageDialog(null,"found","",JOptionPane.CANCEL_OPTION);
                            ResultSet resultSet2=statement.executeQuery("SELECT * FROM inventory WHERE Price="+value+" OR Price<"+value+" ");
                            tableModel.setRowCount(0);
                            while (resultSet2.next()){
                                String itemname=resultSet2.getString(1);
                                String code=resultSet2.getString(2);
                                String cat=resultSet2.getString(3);
                                String price=resultSet2.getString(4);
                                String qty=resultSet2.getString(5);

                                tableModel.addRow(new Object[]{itemname,code,cat,price,qty});
                            }
                        }
                    }
            }
            else if(type.equals("Item Catagory")){
                String catagory=value;
                while (resultSet.next()){
                    if(value.equalsIgnoreCase(resultSet.getString(3))){
                        isAvailble=true;
                        catagory=resultSet.getString(3);
                        break;

                    }
                }
                if(isAvailble) {
                    JOptionPane.showMessageDialog(null, "found", "", JOptionPane.CANCEL_OPTION);
                    ResultSet resultSet2 = statement.executeQuery("SELECT * FROM inventory WHERE catagory='" + catagory + "' ");
                    tableModel.setRowCount(0);
                    while (resultSet2.next()) {
                        String itemname=resultSet2.getString(1);
                        String code=resultSet2.getString(2);
                        String cat=resultSet2.getString(3);
                        String price=resultSet2.getString(4);
                        String qty=resultSet2.getString(5);

                        tableModel.addRow(new Object[]{itemname,code,cat,price,qty});
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"NO items","Empty ",JOptionPane.CANCEL_OPTION);
            }


        }
        catch (Exception a){
            System.out.println(a);
        }
    }
    public boolean isInteger(String input){
        try{
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args){
        new Inventory("","").show();
    }
}
