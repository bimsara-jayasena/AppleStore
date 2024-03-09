package Dashboard.Manager;

import DbConnection.DbConnection;
import LogIn.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.WeakHashMap;

public class Settings extends JFrame  {

    private String name;
    private String empID;
    JLayeredPane pane = new JLayeredPane();


    String Password;

    String id;
    Object[][] data;
    private final Connection connection= DbConnection.getConnection();

    public Settings(String name,String empID) {
        this.name = name;
        this.empID=empID;
        setSize(1680, 1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        pane.setBounds(0, 0, getWidth(), getHeight());
        add(pane);

        JLabel lblTitle = new JLabel();
        lblTitle.setBounds(getWidth()/2-200, 10, 1024, 100);
        lblTitle.setText("Settings");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 90));
        pane.add(lblTitle);



        //CREATING TABLE

        String[] columnNames=new String[2];
        columnNames[0]="Youer Details";
        columnNames[1]="";

       data =new Object[6][2];
        String[] values={"name","Empolyee ID","Email","Contact ","Address","Password"};
        for (int i = 0; i < values.length; i++) {
            data[i][0]= values[i];
        }
        showInfo();







        JTable table=new JTable(data,columnNames);
        table.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        table.setRowHeight(50);
        DefaultTableCellRenderer cellRenderer=new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(getWidth()/2-500,200,1024,320);
        table.setFillsViewportHeight(true);
        pane.add(scrollPane);

        JButton btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.setBounds(1000, 550, 150, 50);
        btnBack.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                DashboardMng d=new DashboardMng(name,empID);

                d.show();
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
        pane.add(btnBack);

        JButton btnEdit = new JButton();
        btnEdit.setText("Update details");
        btnEdit.setBounds(800, 550, 150, 50);
        btnEdit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new update(name,id).show();
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
        pane.add(btnEdit);

        JButton btnDel = new JButton();
        btnDel.setText("Delete Account");
        btnDel.setBounds(600, 550, 150, 50);
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delAccount();
            }
        });
        pane.add(btnDel);

    }
    public void showInfo(){
        try {

            Statement statement = connection.createStatement();
            System.out.println("success");
            int i = 0;

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE employeeID='" + empID + "'");
            while (resultSet.next()) {
                id =resultSet.getString(2);
                Password = resultSet.getString(6);

                for(int j = 0; j < 6; j++) {
                    data[j][1] = resultSet.getString(j + 1);
                    System.out.println(resultSet.getObject(j+1));
                }

            }
        } catch (Exception a){
            System.out.println(a);
        }
    }
    public void delAccount(){
        try{

            Statement statement=connection.createStatement();
            System.out.println("success");
            int i=0;


            JTextField txtPwd=new JTextField();
            int result=JOptionPane.showConfirmDialog(null,"Warring: Once your account deleted,it can't be recoverd","Account delete",JOptionPane.WARNING_MESSAGE);
            if(result==0){
                int confirm=JOptionPane.showConfirmDialog(null,txtPwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
                if(confirm==0){
                    if(txtPwd.getText().equals(Password)){
                        try{
                            statement.execute("DELETE  FROM users WHERE employeeID='"+empID+"'");
                            int resualt=JOptionPane.showConfirmDialog(null,"Account removed Successfully","deleted",JOptionPane.CANCEL_OPTION);
                            if(resualt==0){
                                dispose();
                                new Login().show();
                            }
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Incorrect Password","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }

            }

        }
        catch (Exception a){
            System.out.println(a);
        }

    }

    public static void main(String[] args){
        new Settings("","").show();
    }


}
