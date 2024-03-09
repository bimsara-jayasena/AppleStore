package Dashboard.Manager;

import DbConnection.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class AddProducts extends JFrame {
    JLayeredPane pane=new JLayeredPane();
    JLabel lblTitle=new JLabel();
    JButton btnAdd=new JButton();
    JButton btnBack=new JButton();
    JButton btnDel=new JButton();
    JLabel lblName=new JLabel();
    JLabel lblCode=new JLabel();
    JLabel lblCat=new JLabel();
    JLabel lblPrice=new JLabel();
    JLabel lblQty=new JLabel();

    String[] list;

    JComboBox<String> txtName;
    JTextField txtCode=new JTextField();
    JTextField txtCat=new JTextField();
    JTextField txtPrice=new JTextField();
    JTextField txtQty=new JTextField();
    JComboBox<String> cBoxCat;


    int txtX=450;
    int txtY=80;
    String itemName;

    boolean isExist=false;
    boolean isEmpy=false;

    int noOfRows=0;
    int itemQty=0;
    private final Connection connection=DbConnection.getConnection();

    private final String fontFamily="Articulat CF Demi Bold";
    boolean isAvailble=false;
    boolean isCorrect=false;
    String iD;

    public AddProducts(String name,String iD){
        //createStoreTable();

        this.iD=iD;
        setSize(1024,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pane.setBounds(0,0,1024,720);
        add(pane);

        lblTitle.setText("Add products");
        lblTitle.setBounds(350,10,1024,100);
        lblTitle.setFont(new Font(fontFamily,Font.BOLD,50));
        pane.add(lblTitle);

        lblName.setText("Model");
        int lblX = 300;
        int lblY = 80;
        lblName.setBounds(lblX, lblY *2,150,50);
        lblName.setFont(new Font(fontFamily,Font.BOLD,30));
        pane.add(lblName);

        lblCode.setText("Item code");
        lblCode.setBounds(lblX, lblY *3,150,50);
        lblCode.setFont(new Font(fontFamily,Font.BOLD,30));
        pane.add(lblCode);

        lblCat.setText("Catogory");
        lblCat.setBounds(lblX, lblY *4,150,50);
        lblCat.setFont(new Font(fontFamily,Font.BOLD,30));
        pane.add(lblCat);

        lblPrice.setText("Price");
        lblPrice.setBounds(lblX, lblY *5,150,50);
        lblPrice.setFont(new Font(fontFamily,Font.BOLD,30));
        pane.add(lblPrice);

        lblQty.setText("Quantity");
        lblQty.setBounds(lblX, lblY *6,150,50);
        lblQty.setFont(new Font(fontFamily,Font.BOLD,30));
        pane.add(lblQty);




        fillArray();
        txtName=new JComboBox<>(list);
        txtName.setEditable(true);
        txtName.setBounds(txtX,txtY*2,300,50);
        txtName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillBoxes();
            }
        });
        //searchAndFill(txtName,list);
        addComboBoxFilterListener(txtName,list);
        pane.add(txtName);

        txtCode.setBounds(txtX,txtY*3,300,50);
        pane.add(txtCode);

        String[] options = {"IPhone", "IPad", "Airpods", "Watch","Mac","Accessories"};
       cBoxCat = new JComboBox<>(options);
        cBoxCat.setBounds(txtX,txtY*4,300,50);

        pane.add(cBoxCat);

        txtPrice.setBounds(txtX,txtY*5,300,50);
        pane.add(txtPrice);

        txtQty.setBounds(txtX,txtY*6,300,50);
        pane.add(txtQty);

        btnAdd.setBounds(200,txtY*7,300,60);
        btnAdd.setText("Add Item");
        btnAdd.setFont(new Font(fontFamily,Font.BOLD,30));
        pane.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               addItems();
               //updateStockTable();

            }
        });

        btnBack.setBounds(550,txtY*7,300,60);
        btnBack.setText("Back");
        btnBack.setFont(new Font(fontFamily,Font.BOLD,30));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Inventory(name,iD).show();
            }
        });
        pane.add(btnBack);






    }
    public int checkItems(String name,int qty){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/iluxstore","root","");
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");
            while (resultSet.next()){
                if(name.equals(resultSet.getString(1))){
                   qty++;
                }
            }
            System.out.print("Quantity: "+qty+" || ");
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return qty;
    }

    public void fillArray(){
        ArrayList<String> list1=new ArrayList<>();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");
            while (resultSet.next()){
                list1.add(resultSet.getString(1));
            }


        }
        catch (Exception e){
            System.out.println(e);
        }
        list=new String[list1.size()];
        for (int i = 0; i < list1.size(); i++) {
            list[i]=list1.get(i);
        }
    }
    /*public void searchAndFill(JComboBox<String> comboBox,String[] list){
        JTextField textField=(JTextField)comboBox.getEditor().getEditorComponent();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text=textField.getText();
                ArrayList<String> filteredItems=new ArrayList<>();
                for(String item:list){
                    filteredItems.add(item);
                }
                DefaultComboBoxModel<String> model=new DefaultComboBoxModel<>(filteredItems.toArray(new String[0]));
                comboBox.setModel(model);
                comboBox.setSelectedItem(text);
                comboBox.showPopup();

            }
        });
    }*/
    private static void addComboBoxFilterListener(JComboBox<String> comboBox, String[] items) {
        JTextField textField=(JTextField)comboBox.getEditor().getEditorComponent();

        textField.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e){
                String enterdText=textField.getText();
                ArrayList<String> filteredItems=new ArrayList<>();
                for(String item:items){
                    if(item.toLowerCase().contains(enterdText.toLowerCase())){
                        filteredItems.add(item);
                    }
                }
                DefaultComboBoxModel<String> model=new DefaultComboBoxModel<String>(filteredItems.toArray(new String[0]));
                comboBox.setModel(model);
                comboBox.setSelectedItem(enterdText);
                comboBox.showPopup();
            }
        });
    }

    public void fillBoxes(){
        try{

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory WHERE ItemName='"+txtName.getSelectedItem()+"'");
            while (resultSet.next()){

                txtCode.setText(resultSet.getString(2));
                txtPrice.setText(resultSet.getString(4));

            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void addItems(){
        int qty=1;
        int newQty=1;
        String ItemName=(txtName.getSelectedItem()).toString();

        //int qty=checkItems(ItemName,i);
        if(!isInteger(txtQty.getText())||!isInteger(txtPrice.getText())){
            JOptionPane.showMessageDialog(null,"Invalid values!","Error",JOptionPane.WARNING_MESSAGE);
        }
        else if (isInteger(ItemName)) {
            JOptionPane.showMessageDialog(null,"wrong input type for item name!","Data type error",JOptionPane.WARNING_MESSAGE);
        }
        else {
            try {
                Connection connection=DbConnection.getConnection();
                Statement statement = connection.createStatement();

                //Get quantity

                   ResultSet resultSet = statement.executeQuery("SELECT * FROM inventory WHERE ItemName='" + ItemName+ "'");
                    while (resultSet.next()) {
                        qty = resultSet.getInt(5);
                        if(txtCode.getText().equals(resultSet.getString(2))&&txtPrice.getText().equals(resultSet.getString(4))&& Objects.equals(cBoxCat.getSelectedItem(), resultSet.getString(3))) {

                            if (ItemName.equalsIgnoreCase(resultSet.getString(1))) {
                                System.out.println("Item Exist");
                                isAvailble = true;
                                break;
                            }
                        }



                    }

                    if (isAvailble) {
                        qty += Integer.parseInt(txtQty.getText());
                        System.out.println("New Quantity: " + qty);
                        statement.execute("UPDATE inventory SET Qty=" + qty + " WHERE ItemName='" + ItemName + "'");
                       JOptionPane.showConfirmDialog(null, "Item Added", "Updated", JOptionPane.OK_CANCEL_OPTION);
                    } else {
                        statement.execute("INSERT INTO inventory(ItemName,ItemCode,catagory,Price,Qty) VALUES('" +ItemName+ "'," + txtCode.getText() + ",'" + cBoxCat.getSelectedItem() + "'," + txtPrice.getText() + "," + Integer.parseInt(txtQty.getText()) + ")");

                        JOptionPane.showConfirmDialog(null, "Item Added", "Updated", JOptionPane.OK_CANCEL_OPTION);
                    }




            } catch (Exception a) {
                System.out.println(a);
            }

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
    public void updateStockTable(){

        ArrayList<Object> list=new ArrayList<>();
        try{


            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");
            while (resultSet.next()){
                String itemName=resultSet.getString(1);

                int qty=resultSet.getInt(5);
                list.add(itemName);

                list.add(qty);

            }

            ResultSet resultSet1=statement.executeQuery("SELECT * FROM stock");
            while (resultSet1.next()){

                noOfRows++;
                if(resultSet1.getString(1).equals(list.get(0))){
                    isExist=true;
                }

            }
            int index=0;

            if(noOfRows<1){
                isEmpy=true;
            }
            else {
                isEmpy=false;
            }

            if(isEmpy){
                System.out.println("Table Empty!");

                for (int i = 0; i < list.size(); i+=2) {
                    statement.execute("INSERT INTO stock(item,amount)VALUES('"+list.get(i)+"',"+list.get(i+1)+")");

                }
                System.out.println("Item Added");

            }

            if(!isExist){
                System.out.println("Item Exisit");
              ResultSet resultSet2=statement.executeQuery("SELECT * FROM stock WHERE item='"+1+"'");
                while (resultSet2.next()){
                    itemQty=resultSet2.getInt(2);
                }
                itemQty+=Integer.parseInt(txtQty.getText());
                statement.execute("UPDATE stock SET amount="+itemQty+" WHERE item='"+txtName+"'");

            }
            else{
               // for (int i = 0; i < list.size(); i++) {
                    statement.execute("INSERT INTO stock(item,amount)VALUES('"+list.get(index)+"',"+list.get(index+1)+")");
                    //index+=2;
                //}

            }


        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void createStoreTable(){
        int index=0;
        ArrayList<Object> list=new ArrayList<>();
        try{

            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE  stock(item VARCHAR(255),amount INT)");
            ResultSet resultSet=statement.executeQuery("SELECT*FROM inventory");
            while(resultSet.next()){


                list.add(resultSet.getString(1));
                list.add(resultSet.getInt(4));

            }

            for (int i = 0; i < list.size(); i++) {
                Object item=list.get(index);
                Object q=list.get(index+1);
                statement.execute("INSERT INTO stock(item,amount)VALUES('"+item+"', "+q+")");
                index+=2;

            }



        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }



    public static void main(String[] args){
        new AddProducts("","").show();
    }
}
