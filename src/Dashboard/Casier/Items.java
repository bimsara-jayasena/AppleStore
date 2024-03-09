package Dashboard.Casier;




import CustomClasses.RoundTextField;
import CustomClasses.RoundedButton;
import Dashboard.Manager.Inventory;
import DbConnection.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Items extends JFrame{



        JButton btnBack=new JButton("back");
        JButton btnAdd=new JButton("Add Items");
        JLayeredPane pane=new JLayeredPane();

        RoundTextField txtSearch=new RoundTextField();
        RoundedButton btnSearch=new RoundedButton("",Color.WHITE);
        JComboBox<String> searchType;
        RoundedButton btnRefresh=new RoundedButton("",Color.white);
        String casName;
        int count;
        boolean isAvailble=false;
        DefaultTableModel tableModel;
        private Connection connection=DbConnection.getConnection();
        public Items(String casName){
            this.casName=casName;
            setSize(1680,1050);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);


            pane.setBounds(0,0,getWidth(),getHeight());
            add(pane);

            JLabel lblTitle=new JLabel();
            lblTitle.setText("Availble Products");
            lblTitle.setBounds(500,50,getWidth(),100);
            lblTitle.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 60));
            pane.add(lblTitle);

            btnBack.setBounds(650,650,200,50);
            btnBack.setOpaque(true);
            btnBack.setBackground(Color.black);
            btnBack.setForeground(Color.white);
            btnBack.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goBack(e);
                }
            });
            pane.add(btnBack);

            txtSearch.setBounds(500,150,500,50);
            txtSearch.addActionListener(e->searchItems(txtSearch.getText(),(String)searchType.getSelectedItem()));
            pane.add(txtSearch);

            ImageIcon icon=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\search.png");
            btnSearch.setBounds(950,152,45,45);
            btnSearch.setIcon(icon);
            btnSearch.setFocusable(false);
            pane.add(btnSearch,Integer.valueOf(2));
            btnSearch.addActionListener(e->searchItems(txtSearch.getText(),(String)searchType.getSelectedItem()));

            String[] list= {"-Select what you want search by-","Item name","Item Price","Item Code","Item Catagory"};
            searchType=new JComboBox<>(list);
            searchType.setBounds(600,220,300,30);
            pane.add(searchType);
            searchType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeText();
                }
            });

            //
            ImageIcon iconRef=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\ref.png");



            btnRefresh.setBounds(1000,152,45,45);
            btnRefresh.setIcon(iconRef);
            btnRefresh.setFocusable(false);
            pane.add(btnRefresh,Integer.valueOf(3));
            btnRefresh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Items(casName).show();
                }
            });

            showInventory();


//



        }

        public void showInventory(){
            String[] columnNames={"Item Name","Item Code","Item Catagory","Price(LKR)","qty"};
            //ArrayList<String> d=new ArrayList<>();
           tableModel=new DefaultTableModel(columnNames,0);
            int i=0;
            //Creating Connection and retriew data
            try{

                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");

                while (resultSet.next()){
                    String name=resultSet.getString(1);
                    String code=resultSet.getString(2);
                    String catagory=resultSet.getString(3);
                    String price=resultSet.getString(4);
                    String qty=resultSet.getString(5);

                   tableModel.addRow(new Object[]{name,code,catagory,price,qty});
                }
            }
            catch (Exception a){
                System.out.println(a);
            }




            JTable table=new JTable(tableModel);
            JTableHeader tableHeader=table.getTableHeader();
            tableHeader.setBackground(Color.gray);
            tableHeader.setForeground(Color.white);
            tableHeader.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
            DefaultTableCellRenderer center=new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);

            table.getColumnModel().getColumn(0).setCellRenderer(center);
            table.getColumnModel().getColumn(1).setCellRenderer(center);
            table.getColumnModel().getColumn(2).setCellRenderer(center);
            table.getColumnModel().getColumn(3).setCellRenderer(center);

            /*table.setDefaultRenderer(String.class,center);*/

            table.setFont(new Font("Times New Roman",Font.BOLD,30));
            table.setRowHeight(35);
            table.setAlignmentX(100);

            JScrollPane scrollPane=new JScrollPane(table);
            scrollPane.setBounds(0,300,getWidth(),300);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            table.setFillsViewportHeight(true);
            pane.add(scrollPane);
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

                new Dashboard(casName).show();



                // d.setCount(50);

            }
        }

        public static void main(String[] args){
            new Items("").show();
        }
    }


