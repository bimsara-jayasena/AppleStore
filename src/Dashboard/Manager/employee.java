package Dashboard.Manager;





import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import AccountCreation.AccountType.Casier.casierAccount;
import DbConnection.DbConnection;

public class employee extends JFrame {
    JButton btnBack=new JButton("back");
    JButton btnCreate=new JButton("Add new Employee");
    JButton btnRmv=new JButton("Remove employee");

    private final String mngName;
    private final String ID;
    private final Connection connection= DbConnection.getConnection();
    public employee(String mngName,String ID){
        this.mngName=mngName;
        this.ID=ID;
        setSize(1680,1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLayeredPane pane=new JLayeredPane();
        pane.setBounds(0,0,getWidth(),920);
        add(pane);

        JLabel lblTitle=new JLabel();
        lblTitle.setText("Employee details");
        lblTitle.setBounds(400,50,750,100);
        lblTitle.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 90));
        pane.add(lblTitle);

        btnBack.setBounds(550,600,150,50);
        btnBack.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack(e);
            }
        });
        pane.add(btnBack);

        btnCreate.setBounds(750,600,150,50);
        btnCreate.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnCreate.setBackground(new Color(74, 115, 238, 255));
        btnCreate.setForeground(Color.white);
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmploye();
            }
        });
        pane.add(btnCreate);

        btnRmv.setBounds(950,600,150,50);
        btnRmv.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 10));
        btnRmv.setOpaque(true);
        btnRmv.setBackground(Color.red);
        btnRmv.setForeground(Color.white);
        btnRmv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmploye();
            }
        });
        pane.add(btnRmv);


        String[] columnNames={"Name","Employee ID","E-mail","Contact No","Address"};
        DefaultTableModel tableModel=new DefaultTableModel(columnNames,0);


        try{

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM users WHERE JobRoll='Cashier'");

            while (resultSet.next()){
                String name=resultSet.getString(1);
                String empID=resultSet.getString(2);
                String email=resultSet.getString(3);
                String tp=resultSet.getString(4);
                String adrss=resultSet.getString(5);


                tableModel.addRow(new Object[]{name,empID,email,tp,adrss});
            }
        }catch (Exception a){
            System.out.println(a);
        }



        JTable table=new JTable(tableModel);
        DefaultTableCellRenderer center=new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int j = 0; j < 5; j++) {
            table.getColumnModel().getColumn(j).setCellRenderer(center);

        }

        table.setFont(new Font("Times New Roman",Font.BOLD,15));
        table.setRowHeight(35);
        table.setAlignmentX(100);
        JLabel lblEmp=new JLabel();
        lblEmp.setText("Cashiers");
        lblEmp.setBounds(60,230,1024,100);
        lblEmp.setFont(  new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        pane.add(lblEmp);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,300,getWidth(),300);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        table.setFillsViewportHeight(true);
        pane.add(scrollPane);




    }
    public void goBack(ActionEvent e){
        if(e.getSource()==btnBack){
          this.dispose();

         new DashboardMng(mngName,ID).show();



        }
    }
    public void addEmploye(){
        this.dispose();
        new casierAccount(mngName,ID).show();
    }
    public void  removeEmploye(){
        new DelEmp(mngName,ID).show();
    };
    public static void main(String[] args){
       new employee("","").show();
    }
}

