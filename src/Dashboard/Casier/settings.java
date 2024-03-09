package Dashboard.Casier;




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

public class settings extends JFrame  {

    private String name;
    JLayeredPane pane = new JLayeredPane();
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/iluxstore";
    String userName="root";
    String pw="";

    String Password;

    int id;
    private Connection connection=DbConnection.getConnection();

    public settings(String name) {
        this.name = name;
        setSize(1024, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        pane.setBounds(0, 0, 1024, 720);
        add(pane);

        JLabel lblTitle = new JLabel();
        lblTitle.setBounds(306, 10, 1024, 100);
        lblTitle.setText("Settings");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 90));
        pane.add(lblTitle);



        //CREATING TABLE

        String[] columnNames=new String[2];
        columnNames[0]="Youer Details";
        columnNames[1]="";

        Object[][] data =new Object[6][2];
        String[] values={"name","Empolyee ID","Email","Contact ","Address","Password"};
        for (int i = 0; i < values.length; i++) {
            data[i][0]= values[i];
        }




        try{

            Statement statement=connection.createStatement();
            System.out.println("success");
            int i=0;

            ResultSet resultSet=statement.executeQuery("SELECT * FROM cashier WHERE name='"+name+"'");
            while (resultSet.next()){
                id=resultSet.getInt(2);
                Password=resultSet.getString(6);
                System.out.println(Password);
                for (int j = 0; j < 6; j++) {
                    data[j][1]=resultSet.getString(j+1);
                }

            }
            JButton btnDel = new JButton();
            btnDel.setText("Delete Account");
            btnDel.setBounds(130, 550, 150, 50);
            btnDel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField txtPwd=new JTextField();
                    int result=JOptionPane.showConfirmDialog(null,"Warring: Once your account deleted,it can't be recoverd","Account delete",JOptionPane.WARNING_MESSAGE);
                    if(result==0){
                        int confirm=JOptionPane.showConfirmDialog(null,txtPwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
                        if(confirm==0){
                            if(txtPwd.getText().equals(Password)){
                                try{
                                    statement.execute("DELETE  FROM casier WHERE name='"+name+"'");
                                    int i=JOptionPane.showConfirmDialog(null,"Account removed Successfully","deleted",JOptionPane.CANCEL_OPTION);
                                    if(i==0){
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
            });
            pane.add(btnDel);
        }
        catch (Exception a){
            System.out.println(a);
        }



        JTable table=new JTable(data,columnNames);
        table.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        table.setRowHeight(50);
        DefaultTableCellRenderer cellRenderer=new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0,200,1024,320);
        table.setFillsViewportHeight(true);
        pane.add(scrollPane);

        JButton btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.setBounds(450, 550, 150, 50);
        btnBack.setBackground(Color.black);
        btnBack.setForeground(Color.white);
        btnBack.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
               Dashboard d=new Dashboard(name);

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



    }




}

