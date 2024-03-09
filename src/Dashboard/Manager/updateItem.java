package Dashboard.Manager;



import DbConnection.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class updateItem extends JFrame {
    JLabel lblTitle=new JLabel("Update Products");
    JLabel lblName=new JLabel("Item Name");
    JLabel lblId=new JLabel("Item code");
    JLabel lblPrice=new JLabel("Item Price");

    String []items;
    JComboBox<String> txtName;
    JTextField txtId=new JTextField();
    JTextField txtPrice=new JTextField();

    JButton btnConfirm=new JButton("Update Product");
    JButton btnBack=new JButton("Back");

    int lblX=250;
    int lblY=200;

    int txtX=450;
    int txtY=220;

    String confirmPwd="";

    private final String name;
    private final String iD;
    private Connection connection= DbConnection.getConnection();

    public updateItem(String name,String iD){
        this.name=name;
        this.iD=iD;
        getPwd();
        setSize(1024,720);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLayeredPane pane=new JLayeredPane();
        pane.setBounds(0,0,getWidth(),getHeight());
        add(pane);

        lblTitle.setBounds(250,50,getWidth(),100);
        lblTitle.setFont(new Font("",Font.BOLD,60));
        pane.add(lblTitle);

        lblName.setBounds(lblX,lblY,200,60);
        lblName.setFont(new Font("",Font.BOLD,30));
        lblId.setBounds(lblX,lblY+50,200,60);
        lblId.setFont(new Font("",Font.BOLD,30));

        lblPrice.setBounds(lblX,lblY+100,200,60);
        lblPrice.setFont(new Font("",Font.BOLD,30));

        pane.add(lblName);
        pane.add(lblId);
        pane.add(lblPrice);

        fillInfo();
        txtName=new JComboBox<>(items);
        txtName.setBounds(txtX,txtY,300,30);
        txtName.setEditable(true);
        txtName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInfo();
            }
        });
        //addSearchedItem(txtName,items);
        addComboBoxFilterListener(txtName,items);

        txtId.setBounds(txtX,txtY+50,300,30);
        txtPrice.setBounds(txtX,txtY+100,300,30);

        pane.add(txtName);
        pane.add(txtId);
        pane.add(txtPrice);

        btnConfirm.setBounds(350,450,150,50);
        btnConfirm.setOpaque(true);
        btnConfirm.setBackground(new Color(9, 121, 252, 255));
        btnConfirm.setForeground(Color.white);
        pane.add(btnConfirm);
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option=JOptionPane.showConfirmDialog(null,"Confirm Update?","upadate product",JOptionPane.OK_CANCEL_OPTION);
                if(option==0){
                    JTextField txtPwd=new JTextField("Enter your password:");
                    String pwd=JOptionPane.showInputDialog(null,txtPwd.getText(),"Enter your Password",JOptionPane.OK_CANCEL_OPTION);
                    if(pwd.equals(confirmPwd)){
                        updateProduct();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Incorrect Password","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        btnBack.setBounds(510,450,150,50);
        btnBack.setOpaque(true);
        btnBack.setBackground(Color.black);
        btnBack.setForeground(Color.white);
        pane.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Inventory(name,iD).show();
            }
        });



    }
    public void updateProduct(){
        try{

            Statement statement=connection.createStatement();

            statement.execute("UPDATE  inventory SET itemName='"+txtName.getSelectedItem()+"' ,itemCode="+txtId.getText()+" ,Price="+txtPrice.getText()+" WHERE ItemCode='"+txtId.getText()+"'");
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void getInfo(){

        try{

            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT *FROM inventory WHERE ItemName='"+txtName.getSelectedItem()+" '" );
            while (resultSet.next()){
                txtId.setText(resultSet.getString(2));
                txtPrice.setText(resultSet.getString(4));
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void getPwd(){
        try{

            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT *FROM users WHERE employeeID='"+iD+"'");
            while (resultSet.next()){
                confirmPwd=resultSet.getString(8);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void fillInfo(){
        ArrayList<String> list=new ArrayList<>();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT* FROM inventory");
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        items=new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            items[i]=list.get(i);
        }
    }
    public void addSearchedItem(JComboBox<String> comboBox,String[] items){
        JTextField textField=(JTextField) comboBox.getEditor().getEditorComponent();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ArrayList<String> list=new ArrayList<>();
                String selectedItem=textField.getText();
                for(String item:items){
                    if(item.toLowerCase().contains(selectedItem.toLowerCase())){
                        list.add(item);
                    }
                }
                DefaultComboBoxModel<String> model=new DefaultComboBoxModel<>(list.toArray(new String[0]));
                comboBox.setModel(model);
                comboBox.setSelectedItem(selectedItem);
                comboBox.showPopup();
            }
        });
    }
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
    public static  void main(String[] args){
        new updateItem("","").show();
    }
}


