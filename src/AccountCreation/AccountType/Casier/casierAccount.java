package AccountCreation.AccountType.Casier;



import AccountCreation.AccountForm;
import CustomClasses.RoundedButton;
import Dashboard.Manager.employee;
import DbConnection.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class casierAccount extends JFrame{



    JLabel lblTitle=new JLabel("Let's Create Account!");
    JLabel lblInfo=new JLabel("So, tell us about employee!");
    JLabel lblFName=new JLabel("First Name");
    JLabel lblLName=new JLabel("Last Name");
    JLabel lblEmpID=new JLabel("Employee ID");
    JLabel lblJobRoll=new JLabel("Job Roll");
    JLabel lblMail=new JLabel("E-mail");
    JLabel lblCno=new JLabel("Contact No");
    JLabel lblAd=new JLabel("Address");
    JLabel lblCreatePW=new JLabel("Let's create new Password! ");
    JLabel lblPwd=new JLabel("Password");
    JLabel lblCPwd=new JLabel("Confirm Password");

    JTextField txtFName=new JTextField();
    JTextField txtLName=new JTextField();
    JTextField txtEmpID=new JTextField();
    JComboBox<String > txtJobRoll=new JComboBox<>();

    JTextField txtCno=new JTextField();

    JTextField txtAd=new JTextField();
    JTextField txtMail=new JTextField();
    JTextField txtPwd=new JTextField();
    JTextField txtCPwd=new JTextField();

    JLabel lblNameEr=new JLabel("What's employee Name?");
    JLabel lblIdEr=new JLabel("What's employee Employee ID?");
    JLabel lblMailEr=new JLabel("What's employee Mail?");
    JLabel lblCnoEr=new JLabel("What's employee Contact Number?");
    JLabel lblCnoLenEr=new JLabel("Contact number is not Valid");
    JLabel lblAdEr=new JLabel("Where do employee live?");
    JLabel lblPwEr=new JLabel("Password doesn't match!");

    JLabel lbluserExist=new JLabel("user is Already Exisist!");
    JLabel lblIdTaken=new JLabel("This ID is taken!");
    JLabel lblTpExist=new JLabel("Contact number  is Already Exisist!");

    private boolean isNameAvailble=false;
    private  boolean isIdAvailble=false;
    private  boolean isTpAvailble=false;


    int erY=180;

    private final String mngID;
    private final String mngName;

    RoundedButton btnCrt=new RoundedButton("Create  account",Color.black);
    RoundedButton btnBack=new RoundedButton("Go back",Color.black);
    public  casierAccount(String id,String name){

        this.mngID=id;
        this.mngName=name;
        setSize(1200,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        JLayeredPane panel=new JLayeredPane();
        panel.setOpaque(true);
        panel.setBounds(0,0,1124,820);
        panel.setBackground(Color.white);

        ImageIcon img=new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\AccountCreation\\AccountType\\Casier\\Untitled-1.png");

        JLabel image=new JLabel();
        //image.setOpaque(true);
        //image.setBackground(Color.red);
        image.setBounds(10,200,630,500);
        image.setIcon(img);
        panel.add(image,Integer.valueOf(-1));


        lblTitle.setBounds(300,0,1024,100);
        lblTitle.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 50));
        panel.add(lblTitle);

        lblInfo.setBounds(600,100,1024,100);
        lblInfo.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblInfo);

        lblFName.setBounds(650,170,1024,100);
        lblFName.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblFName);

        lblLName.setBounds(650,230,1024,100);
        lblLName.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblLName);

        lblEmpID.setBounds(650,290,1024,100);
        lblEmpID.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblEmpID);

        lblJobRoll.setBounds(650,350,1024,100);
        lblJobRoll.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblJobRoll);

        lblMail.setBounds(650,410,1024,100);
        lblMail.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblMail);

        lblCno.setBounds(650,460,1024,100);
        lblCno.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblCno);

        lblAd.setBounds(650,510,1024,100);
        lblAd.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblAd);

        lblCreatePW.setBounds(600,560,1024,100);
        lblCreatePW.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblCreatePW);

        lblPwd.setBounds(650,610,1024,100);
        lblPwd.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblPwd);

        lblCPwd.setBounds(650,670,1024,100);
        lblCPwd.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        panel.add(lblCPwd);

        txtFName.setBounds(850,200,300,30);
        panel.add(txtFName);

        txtLName.setBounds(850,260,300,30);
        panel.add(txtLName);

        txtEmpID.setBounds(850,320,300,30);
        panel.add(txtEmpID);

        String[] jobs={"Cashier"};
        txtJobRoll=new JComboBox<>(jobs);
        txtJobRoll.setBounds(850,380,300,30);
        panel.add(txtJobRoll);


        txtMail.setBounds(850,440,300,30);
        panel.add(txtMail);

        txtCno.setBounds(850,490,300,30);
        panel.add(txtCno);

        txtAd.setBounds(850,540,300,30);
        panel.add(txtAd);

        txtPwd.setBounds(850,640,300,30);
        panel.add(txtPwd);

        txtCPwd.setBounds(850,690,300,30);
        panel.add(txtCPwd);

        lblNameEr.setBounds(850,erY,300,15);
        lblNameEr.setForeground(Color.red);
        lblNameEr.setVisible(false);
        panel.add(lblNameEr);

        lbluserExist.setBounds(850,erY,300,15);
        lbluserExist.setForeground(Color.red);
        lbluserExist.setVisible(false);
        panel.add(lbluserExist);

        lblIdEr.setBounds(850,erY+120,300,15);
        lblIdEr.setForeground(Color.red);
        lblIdEr.setVisible(false);
        panel.add(lblIdEr);

        lblIdTaken.setBounds(850,erY+120,300,15);
        lblIdTaken.setForeground(Color.red);
        lblIdTaken.setVisible(false);
        panel.add(lblIdTaken);

        lblMailEr.setBounds(850,420,300,15);
        lblMailEr.setForeground(Color.red);
        lblMailEr.setVisible(false);
        panel.add(lblMailEr);

        lblCnoEr.setBounds(850,470,300,15);
        lblCnoEr.setForeground(Color.red);
        lblCnoEr.setVisible(false);
        panel.add(lblCnoEr);

        lblTpExist.setBounds(850,470,300,15);
        lblTpExist.setForeground(Color.red);
        lblTpExist.setVisible(false);
        panel.add(lblTpExist);

        lblCnoLenEr.setBounds(850,470,300,15);
        lblCnoLenEr.setForeground(Color.red);
        lblCnoLenEr.setVisible(false);
        panel.add(lblCnoLenEr);

        lblAdEr.setBounds(850,520,300,15);
        lblAdEr.setForeground(Color.red);
        lblAdEr.setVisible(false);
        panel.add(lblAdEr);

        lblPwEr.setBounds(850,670,300,15);
        lblPwEr.setForeground(Color.red);
        lblPwEr.setVisible(false);
        panel.add(lblPwEr);

        btnCrt.setBounds(800,750,300,40);
        btnCrt.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));

        btnCrt.setForeground(Color.white);
        btnCrt.setFocusable(false);
        btnCrt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkCredentials()) {
                    btnCrtActionPerformed(e);
                }
            }
        });
        panel.add(btnCrt);
        btnBack.setBounds(835,800,250,40);
        btnBack.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        //btnCrt.setOpaque(true);
        //btnCrt.setBackground(Color.black);
        btnBack.setForeground(Color.white);
        btnBack.setFocusable(false);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBackActionPerformed(e);
            }
        });
        panel.add(btnBack);

        add(panel);
    }
    public void setInfo(String fName,String lName, String mail, String addrs, String pwd, String empID, int tp){
        txtFName.setText(fName);
        txtLName.setText(lName);
        txtEmpID.setText(empID+"");

        txtCno.setText(tp+"");

        txtAd.setText(addrs);
        txtMail.setText(mail);
        txtPwd.setText(pwd);
        txtCPwd.setText(pwd);
    }
    public void btnCrtActionPerformed(ActionEvent e){
        if(e.getSource()==btnCrt){
            this.dispose();
            this.dispose();
            new Confirmation(txtFName.getText(),txtLName.getText(),txtEmpID.getText(),(String) txtJobRoll.getSelectedItem(),txtMail.getText(),Integer.parseInt(txtCno.getText()),txtAd.getText(),txtPwd.getText(),mngName,mngID);
        }
    }
    public void btnBackActionPerformed(ActionEvent e){
        if(e.getSource()==btnBack){
            this.dispose();
            new employee(mngName,mngID).setVisible(true);
        }
    }

    public boolean checkCredentials(){
        checkAvailbility();
        boolean isOK=true;
        String pwd=txtPwd.getText();
        if(txtFName.getText().isEmpty()||txtLName.getText().isEmpty()){

            lblNameEr.setVisible(true);
            txtFName.setBorder(BorderFactory.createLineBorder(Color.red));
            txtLName.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }

        if(isNameAvailble){
            lbluserExist.setVisible(true);
            txtFName.setBorder(BorderFactory.createLineBorder(Color.red));
            txtLName.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }
        if(isIdAvailble){
            lblIdTaken.setVisible(true);
            txtEmpID.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }

        if(txtEmpID.getText().isEmpty()){

            lblIdEr.setVisible(true);
            txtEmpID.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }



        if(txtAd.getText().isEmpty()){

            lblAdEr.setVisible(true);
            txtAd.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }
        if(txtMail.getText().isEmpty()){

            lblMailEr.setVisible(true);
            txtMail.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }
        if(!txtCPwd.getText().equals(txtPwd.getText())){
            lblPwEr.setVisible(true);
            txtCPwd.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }
        

        if(txtCno.getText().isEmpty()){

            lblCnoEr.setVisible(true);
            txtCno.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }
        else if(isTpAvailble){
            lblTpExist.setVisible(true);
            txtCno.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }
        else if((txtCno.getText().length()<10||txtCno.getText().length()>10)){
            //JOptionPane.showMessageDialog(null,"You forgot to enter your contact number","Empty field",JOptionPane.CANCEL_OPTION);
            lblCnoLenEr.setVisible(true);
            txtCno.setBorder(BorderFactory.createLineBorder(Color.red));
            isOK=false;
        }



        return isOK;
    }
    public void checkAvailbility(){
        String fName=txtFName.getText();
        String lName=txtLName.getText();
        String id=txtEmpID.getText();
        String tp=txtCno.getText();
        try{

            Connection connection= DbConnection.getConnection();
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                if(fName.equals(resultSet.getString(1))&&lName.equals(resultSet.getString(2))){
                    isNameAvailble=true;
                }
                else{
                    isNameAvailble=false;
                }
                if(id.equals(resultSet.getString(3))){
                    isIdAvailble=true;
                }
                else {
                    isIdAvailble=false;
                }
                if(id.equals(resultSet.getString(3))){
                    isIdAvailble=true;
                }
                else {
                    isIdAvailble=false;
                }

                if(tp.equals(resultSet.getString(4))){
                    isTpAvailble=true;
                }



            }

        }catch (Exception ex){
            System.out.println(ex);
        }

    }
    public static void main(String [] args){
        new casierAccount("","").show();}
}
