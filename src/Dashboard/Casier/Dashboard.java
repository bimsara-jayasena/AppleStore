package Dashboard.Casier;

import DbConnection.DbConnection;
import LogIn.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.io.font.FontConstants;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;

import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;

import com.itextpdf.layout.element.*;

import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

public class Dashboard extends JFrame implements ActionListener {
    JLayeredPane panel = new JLayeredPane();
    JLayeredPane panel2 = new JLayeredPane();
    JLayeredPane panel3=new JLayeredPane();
    JLayeredPane panel4=new JLayeredPane();

    JLayeredPane leftPanel = new JLayeredPane();
    JLayeredPane rightPanel = new JLayeredPane();


    JButton btnOffCust = new JButton();


    JButton btnItemList = new JButton("List");


    JButton btnSettings = new JButton("Settings");

    JButton btnBills = new JButton("Bills");

    JButton btnMinimize = new JButton();
    JLabel lblTitleName = new JLabel();
    JLabel lblSoldItems = new JLabel();
    JLabel lblLowStock = new JLabel();
    JScrollPane scrollPane3;
    JTable table2;

    JTable tableLowStock;
    JScrollPane scrollPaneLow;

    int Clicked = 0;

    int isClicked = 1;


    int Total = 0;

    JLabel lblInvoice = new JLabel("Print Invoice");
    JLabel lblCName = new JLabel("Customer Name");
    JTextField txtSetName = new JTextField();
    JLabel lblIname = new JLabel("Item Name");
    String[] items;
    JComboBox<String> txtItemName;
    JLabel lblCode = new JLabel("Item Code");
    JLabel lblStatus = new JLabel();
    JTextField txtItemCode = new JTextField();
    JLabel lblPrice = new JLabel("Item Price");
    JTextField txtItemPrice = new JTextField();

    JLabel lblQty = new JLabel("Quantity");
    JTextField txtQty = new JTextField();


    JButton btnAdd = new JButton("Add");

    JLabel lblTotal = new JLabel("Total");
    JTextField txtTotal = new JTextField();

    JLabel lblPayed = new JLabel("Payed");
    JTextField txtPayed = new JTextField();

    JLabel lblBalance = new JLabel("Balance");
    JTextField txtBalance = new JTextField();


    JButton btnInvoice = new JButton("Create Invoice");
    JTextPane textPane = new JTextPane();

    int lblX = 0;
    int txtX = 350-90;
    int btnX = 130;


    ImageIcon img = new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\Untitled-1.png");
    ImageIcon img2 = new ImageIcon("D:\\Document\\Education\\Self_study\\practicals\\javaProjects\\AppleStore\\AppleStore\\src\\Untitled-2.png");

    JButton btnOut = new JButton("Log out");
    private String name;

    JLayeredPane tableContainer = new JLayeredPane();

    JLabel lblCustName = new JLabel();
    ArrayList<Object> list = new ArrayList<>();


    String[] columnNames;

    DefaultTableModel invoiceTableModel;


    boolean complete = false;


    //int qty=0;
    int count;

    DefaultTableModel tableModel;

    DefaultTableModel tableModel2;
    int total = 0;
    int earning = 0;


    int itemCount;


    boolean isOut = false;

    int day = 0;

    int date = 0;
    boolean itemAvailble = false;
    boolean isSameDay = false;

    String title;
    String time;
    String code;
    String invDate;

    int totalIncome = 0;
    int invoiceNo = 0;
    JScrollPane scrollPane;
    JTable table;

    boolean isComplete=false;

    private final Connection connection = DbConnection.getConnection();


    public Dashboard(String name) {
        createsoldItemsTable();
        //createdayCountTable();


        this.name = name;
        setSize(1680, 1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //setLocationRelativeTo(null);


        //==================== PANAL 1==================================

        panel.setBounds(0, 0, 1024, 720);
        add(panel);


        lblTitleName.setText("Welcome back " + name);
        lblTitleName.setBounds(350, 0, 600, 100);
        lblTitleName.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 50));
        panel.add(lblTitleName);


        lblSoldItems.setText("Sold Items");
        lblSoldItems.setBounds(450, 200, 600, 100);
        lblSoldItems.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        panel.add(lblSoldItems);

        /*==============SOLD ITEMS====================*/

        getData();


        lblLowStock.setText("Low in Stock");
        lblLowStock.setBounds(450, 500, 600, 100);
        lblLowStock.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        panel.add(lblLowStock);

        lowStockTable();

        //=====================PANEL 2=========================================
        panel2.setBounds(1680, 0, 1680, 1050);
        panel2.setOpaque(true);
        //panel2.setBackground(Color.green);
        panel.add(panel2, Integer.valueOf(1));


        panel3.setBounds(10, 0, 1680, 1050);
        panel3.setOpaque(true);
       // panel3.setBackground(Color.green);
        panel2.add(panel3, Integer.valueOf(9));

        panel4.setBounds(80, 0, 1200, 1050);
        panel4.setOpaque(true);
        //panel4.setBackground(Color.green);
        panel3.add(panel4, Integer.valueOf(-1));



        lblInvoice.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 50));
        lblInvoice.setBounds(250, 50, 500, 100);
        panel4.add(lblInvoice);


        lblCName.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        lblCName.setBounds(0, 150, 500, 100);
        panel4.add(lblCName);

        txtSetName.setBounds(txtX, 180, 300, 30);
        panel4.add(txtSetName);


        lblIname.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        lblIname.setBounds(lblX, 230, 500, 100);
        panel4.add(lblIname);

        fillArray();
        txtItemName = new JComboBox<>(items);
        txtItemName.setBounds(txtX, 260, 300, 30);
        txtItemName.setEditable(true);
        txtItemName.addActionListener(e -> autoFill());
        searchAndFill(txtItemName, items);
        panel4.add(txtItemName);


        lblCode.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        lblCode.setBounds(lblX, 310, 500, 100);
        panel4.add(lblCode);


        txtItemCode.setBounds(txtX, 340, 300, 30);
        txtItemCode.setEditable(false);
        panel4.add(txtItemCode);


        lblPrice.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        lblPrice.setBounds(lblX, 390, 500, 100);
        panel4.add(lblPrice);


        txtItemPrice.setBounds(txtX, 420, 300, 30);
        txtItemPrice.setEditable(false);
        panel4.add(txtItemPrice);

        lblQty.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        lblQty.setBounds(lblX, 470, 500, 100);
        panel4.add(lblQty);

        txtQty.setBounds(txtX, 500, 300, 30);
        panel4.add(txtQty);


        btnAdd.setBounds(btnX, 580, 300, 30);
        btnAdd.setOpaque(true);
        btnAdd.setBackground(Color.black);
        btnAdd.setForeground(Color.white);
        panel4.add(btnAdd);


        lblTotal.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        lblTotal.setBounds(lblX, 620, 500, 100);
        panel4.add(lblTotal);

        txtTotal.setBounds(txtX, 650, 300, 30);
        txtTotal.setEditable(false);
        panel4.add(txtTotal);

        lblPayed.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        lblPayed.setBounds(lblX, 690, 500, 100);
        panel4.add(lblPayed);

        txtPayed.setBounds(txtX, 720, 300, 30);
        panel4.add(txtPayed);

        lblBalance.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 30));
        lblBalance.setBounds(lblX, 760, 500, 100);
        txtBalance.setEditable(false);
        panel4.add(lblBalance);

        txtBalance.setBounds(txtX, 790, 300, 30);
        panel4.add(txtBalance);


        btnInvoice.setBounds(btnX, 870, 300, 30);
        btnInvoice.setBackground(Color.black);
        btnInvoice.setForeground(Color.white);

        panel4.add(btnInvoice);


        textPane.setBounds(750, 150, 500, 700);
        panel4.add(textPane);

        title = "iLuxStore\n\n";
        time = "Time";
        code = "Code\t\t";
        invDate = "Date\t\t";


        addText(textPane, 1, 30, title, true);
        addText(textPane, 0, 20, code, false);
        addText(textPane, 0, 20, invDate, false);
        addText(textPane, 0, 20, time, false);


        //TABLE


        tableContainer.setBounds(0, 110, 500, 700);
        tableContainer.setOpaque(true);
        tableContainer.setBackground(Color.gray);
        tableContainer.setOpaque(true);

        textPane.add(tableContainer);


        lblStatus.setBounds(200, 150, 100, 30);
        lblStatus.setText("No Items Added");
        tableContainer.add(lblStatus);

        JLabel lblGreet = new JLabel();
        lblGreet.setBounds(230, 500, 300, 100);
        lblGreet.setText("Thank You!");
        tableContainer.add(lblGreet);


        lblCustName.setBounds(0, 0, 600, 60);
        lblCustName.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        tableContainer.add(lblCustName);


        btnAdd.addActionListener(e -> addToCart());

        //createDayTable();
        btnInvoice.addActionListener(e -> {
            Total=0;
            complete = true;
            updateInventory();
            addData();
            setDays();
            printInvoice();
            updateList();
        });


        btnOffCust.setBounds(1635, 240, 30, 100);
        btnOffCust.setOpaque(true);
        btnOffCust.setBackground(Color.black);
        btnOffCust.setFocusable(false);
        btnOffCust.setIcon(img);
        btnOffCust.setBorder(new EmptyBorder(0, 0, 0, 0));


        btnOffCust.addActionListener(e -> openPanel2(panel2));
        panel.add(btnOffCust, Integer.valueOf(3));


        //======================== LEFT PANEL ==================================

        leftPanel.setBounds(0, 0, 300, 1050);
        leftPanel.setOpaque(true);
        leftPanel.setBackground(Color.black);
        panel.add(leftPanel, Integer.valueOf(2));


        btnItemList.setBounds(10, 100, 300, 50);
        btnItemList.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnItemList.setForeground(Color.gray);
        btnItemList.setOpaque(true);
        btnItemList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == btnItemList) {
                    dispose();
                    new Items(name).show();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnItemList.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnItemList.setForeground(Color.gray);
            }
        });
        btnItemList.setBackground(leftPanel.getBackground());

        btnItemList.setFocusable(false);
        btnItemList.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));

        leftPanel.add(btnItemList);

        /*btnCDetails.setBounds(10, 180, 300, 50);
        btnCDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnCDetails.setOpaque(true);
        btnCDetails.setBackground(leftPanel.getBackground());
        btnCDetails.setForeground(Color.gray);
        btnCDetails.setFocusable(false);
        btnCDetails.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnCDetails.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == btnCDetails) {
                    dispose();
                    new customers(name).show();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnCDetails.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCDetails.setForeground(Color.gray);
            }
        });*/
        //leftPanel.add(btnCDetails);


        btnBills.setBounds(10, 180, 300, 50);
        btnBills.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnBills.setOpaque(true);
        btnBills.setBackground(leftPanel.getBackground());
        btnBills.setForeground(Color.gray);
        btnBills.setFocusable(false);
        btnBills.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnBills.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new bills(name).show();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnBills.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBills.setForeground(Color.gray);
            }
        });
        leftPanel.add(btnBills);

        btnSettings.setBounds(10, 260, 300, 50);
        btnSettings.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSettings.setOpaque(true);
        btnSettings.setBackground(leftPanel.getBackground());
        btnSettings.setForeground(Color.gray);
        btnSettings.setFocusable(false);
        btnSettings.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnSettings.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new settings(name).show();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSettings.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSettings.setForeground(Color.gray);
            }
        });
        leftPanel.add(btnSettings);


        btnOut.setBounds(10, 340, 300, 50);
        btnOut.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnOut.setOpaque(true);
        btnOut.setBackground(leftPanel.getBackground());
        btnOut.setForeground(Color.gray);
        btnOut.setFocusable(false);
        btnOut.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        btnOut.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == btnOut) {
                    isOut = true;
                    updateDay();
                    dispose();
                    new Login().setVisible(true);
                    //DbConnection.closeConnection();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnOut.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnOut.setForeground(Color.gray);
            }
        });
        leftPanel.add(btnOut);

        btnMinimize.setBounds(300, 240, 30, 100);
        btnMinimize.setIcon(img);
        btnMinimize.setOpaque(true);
        btnMinimize.setBackground(Color.black);
        btnMinimize.setFocusable(false);
        btnMinimize.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnMinimize.addActionListener(e -> leftpanel(leftPanel));
        panel.add(btnMinimize, Integer.valueOf(2));






        /*rightPanel.setBounds(1680, 0, 350, 1050);
        rightPanel.setOpaque(true);
        rightPanel.setBackground(Color.black);
        panel.add(rightPanel, Integer.valueOf(3));

        lblTitle.setBounds(110, 10, 100, 100);
        lblTitle.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        lblTitle.setForeground(Color.white);
        rightPanel.add(lblTitle);

        lblName.setBounds(25, 150, 100, 30);
        lblName.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        lblName.setForeground(Color.white);
        rightPanel.add(lblName);

        txtName.setBounds(130, 150, 150, 30);
        rightPanel.add(txtName);

        lblEmail.setBounds(25, 200, 100, 30);
        lblEmail.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        lblEmail.setForeground(Color.white);
        rightPanel.add(lblEmail);

        txtMail.setBounds(130, 200, 150, 30);
        rightPanel.add(txtMail);

        lblAddres.setBounds(25, 250, 100, 30);
        lblAddres.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        lblAddres.setForeground(Color.white);
        rightPanel.add(lblAddres);

        txtAddres.setBounds(130, 250, 150, 30);
        rightPanel.add(txtAddres);

        lblPrice.setBounds(25, 300, 100, 30);
        lblPrice.setFont(new Font("Articulat CF Demi Bold", Font.BOLD, 20));
        lblPrice.setForeground(Color.white);
        rightPanel.add(lblPrice);

        txtTotal.setBounds(130, 300, 150, 30);
        rightPanel.add(txtTotal);*/


    }

    public void addToCart() {

        if (checkFields()) {
            if (validation(txtSetName.getText()) || !validation(txtQty.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid data!", "", JOptionPane.WARNING_MESSAGE);
            } else if (!checkItemName()) {
                JOptionPane.showMessageDialog(null, "Item Not availble!", "", JOptionPane.WARNING_MESSAGE);
            } else if (checkQty(Integer.parseInt(txtQty.getText()))) {
                JOptionPane.showMessageDialog(null, "Item qauntity exceeded!", "", JOptionPane.WARNING_MESSAGE);
            } else {


                itemAvailble = false;
                Total += (Integer.parseInt(txtItemPrice.getText())) * (Integer.parseInt(txtQty.getText()));
                txtTotal.setText(String.valueOf(Total));


                int index = 0;
                for (Object o : list) {
                    if (txtItemName.getSelectedItem().equals(o)) {
                        itemAvailble = true;

                    }
                }
                if (!itemAvailble) {
                    itemCount = 1;
                    list.add(txtItemName.getSelectedItem());
                    list.add(txtItemCode.getText());
                    list.add(txtItemPrice.getText());
                    list.add(txtQty.getText());


                } else {
                    list.set(list.size() - 1, itemCount);
                }


                int rows = list.size() / 3;
                lblStatus.setVisible(false);
                invoice(rows, list, index);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fields Can not be Empty!", "Empty Fields", JOptionPane.WARNING_MESSAGE);
        }
        //return list;

    }









    public void leftpanel(JLayeredPane leftPanel) {


        if(leftPanel.getX()==0){
            //animation when side panel is open
            btnMinimize.setIcon(img2);
            int x=leftPanel.getX();
            int width=leftPanel.getWidth();
            int finalX=x-width;
            int step=100;

            Timer timer=new Timer(10,e->{


                if(leftPanel.getX()>finalX){
                    leftPanel.setLocation(leftPanel.getX()-step,leftPanel.getY());


                    lblTitleName.setLocation(lblTitleName.getX()-step,lblTitleName.getY());
                    lblSoldItems.setLocation(lblSoldItems.getX()-step,lblSoldItems.getY());
                    lblLowStock.setLocation(lblLowStock.getX()-step,lblLowStock.getY());

                    scrollPane3.setLocation(scrollPane3.getX()-step,scrollPane3.getY());
                    scrollPane3.setSize(new Dimension(scrollPane3.getWidth()+step,scrollPane3.getHeight()));
                    scrollPaneLow.setLocation(scrollPaneLow.getX()-step,scrollPaneLow.getY());
                    scrollPaneLow.setSize(new Dimension(scrollPaneLow.getWidth()+step,scrollPaneLow.getHeight()));

                    table2.setLocation(table2.getX()-step,table2.getY());
                    tableLowStock.setLocation(tableLowStock.getX()-step,tableLowStock.getY());

                    btnMinimize.setLocation(btnMinimize.getX()-step,btnMinimize.getY());

                    //if panel 2 is opend

                    if(panel2.getX()<=width){

                        int step2=step/2;
                        panel2.setLocation(panel2.getX()-step,panel2.getY());
                        panel3.setLocation(panel3.getX()+50,panel3.getY());







                    }






                }
                else{

                    ((Timer)e.getSource()).stop();
                }
            });
            timer.start();


        }
        else{
            //animation when side panel closed

            btnMinimize.setIcon(img);
            int x=leftPanel.getX();
            int width=leftPanel.getWidth();
            int finalX=x+width;
            int step=100;


            Timer timer=new Timer(10,e -> {

                if(leftPanel.getX()<finalX){
                    leftPanel.setLocation(leftPanel.getX()+step,leftPanel.getY());
                    //panel3.setLocation(panel3.getX()+50,panel3.getY());


                    lblTitleName.setLocation(lblTitleName.getX()+step,lblTitleName.getY());
                    lblSoldItems.setLocation(lblSoldItems.getX()+step,lblSoldItems.getY());
                    lblLowStock.setLocation(lblLowStock.getX()+step,lblLowStock.getY());

                    scrollPane3.setLocation(scrollPane3.getX()+step,scrollPane3.getY());
                    scrollPane3.setSize(new Dimension(scrollPane3.getWidth()-step,scrollPane3.getHeight()));

                    scrollPaneLow.setLocation(scrollPaneLow.getX()+step,scrollPaneLow.getY());
                    scrollPaneLow.setSize(new Dimension(scrollPaneLow.getWidth()-step,scrollPaneLow.getHeight()));

                    table2.setLocation(table2.getX()+step,table2.getY());
                    tableLowStock.setLocation(tableLowStock.getX()+step,tableLowStock.getY());

                    btnMinimize.setLocation(btnMinimize.getX()+step,btnMinimize.getY());
                   if(panel2.getX()<width){
                     
                        int step2=step/2;
                        panel2.setLocation(panel2.getX()+step,panel2.getY());
                       panel3.setLocation(panel3.getX()-50,panel3.getY());





                    }//when panel2 is opened

                }


                else {

                    ((Timer)e.getSource()).stop();
                }
            });
            timer.start();

        }


            isClicked = 1;



    }


    public void addText(JTextPane textPane, int align, int size, String text, boolean bold) {
        StyledDocument doc = textPane.getStyledDocument();

        try {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setAlignment(attr, align);
            StyleConstants.setFontSize(attr, size);
            StyleConstants.setBold(attr, bold);
            doc.setParagraphAttributes(doc.getLength(), 1, attr, false);
            doc.insertString(doc.getLength(), text, attr);
        } catch (BadLocationException ex) {
            System.out.println(ex);
        }
    }

    public void addData() {
        boolean isAvailble = false;
        int transactionID= (int) (Math.random() * 1000);

        try {
            Statement statement = connection.createStatement();


            ResultSet searchDate = statement.executeQuery("SELECT * FROM solditems WHERE 1");
            while (searchDate.next()) {
                date = searchDate.getInt(2);
            }
            ResultSet Date = statement.executeQuery("SELECT * FROM days WHERE 1");
            while (Date.next()) {
                day = Date.getInt(1);
            }
            if (day == date) {
                isSameDay = true;
            }


            for (int i = 0; i < list.size(); i+=4) {
                transactionID+=i;

                ResultSet resultSet = statement.executeQuery("SELECT * FROM solditems WHERE Item='" + list.get(i) + "'AND day=" + day + " ");
                while (resultSet.next()) {
                    total = resultSet.getInt(6);

                    if ((list.get(i)).equals(resultSet.getString(3))) {
                        isAvailble = true;
                        break;
                    }
                }

                if (isAvailble) {
                    if (isSameDay) {
                        total += Integer.parseInt((String)list.get(i+3));
                        earning = Integer.parseInt((String)list.get(i+2))*total;

                        statement.execute("UPDATE solditems SET Qty=" + total + "  WHERE Item='" +  list.get(i)  + "' AND day=" + day + " ");
                        statement.execute("UPDATE solditems SET total=" + earning + "  WHERE Item='" +  list.get(i)  + "' AND day=" + day + " ");

                        //JOptionPane.showConfirmDialog(null, "same day", "Updated", JOptionPane.OK_CANCEL_OPTION);

                    }
                    else {
                        total = Integer.parseInt(txtQty.getText());

                        statement.execute("INSERT INTO solditems(transactionID,day,Item,code,price,Qty,total) VALUES(" + transactionID + "," + day + ",'" +  list.get(i)  + "'," +  list.get(i+1)  + "," +  list.get(i+2)  + "," +list.get(i+3) + "," +  list.get(i+2)  + ")");
                    }


                }
                else {

                    total = Integer.parseInt(txtQty.getText());
                    statement.execute("INSERT INTO solditems(transactionID,day,Item,code,price,Qty,total) VALUES(" + transactionID + "," + day + ",'" +  list.get(i)  + "'," +  list.get(i+1)  + "," +  list.get(i+2)  + "," + list.get(i+3) + "," +  list.get(i+2)  + ")");


                }

            }


            /*
            while (resultSet.next()) {
                total = resultSet.getInt(6);

                if (((String)txtItemName.getSelectedItem()).equals(resultSet.getString(3))) {
                    isAvailble = true;
                    break;
                }
            }

            if (isAvailble) {
                if (isSameDay) {
                    total += Integer.parseInt(txtQty.getText());
                    earning = (Integer.parseInt(txtItemPrice.getText()) * total);

                    statement.execute("UPDATE solditems SET Qty=" + total + "  WHERE Item='" + txtItemName.getSelectedItem() + "' AND day=" + day + " ");
                    statement.execute("UPDATE solditems SET total=" + earning + "  WHERE Item='" + txtItemName.getSelectedItem() + "' AND day=" + day + " ");

                    //JOptionPane.showConfirmDialog(null, "same day", "Updated", JOptionPane.OK_CANCEL_OPTION);

                }
                else {
                    total = Integer.parseInt(txtQty.getText());

                    statement.execute("INSERT INTO solditems(transactionID,day,Item,code,price,Qty,total) VALUES(" + transactionID + "," + day + ",'" + txtItemName.getSelectedItem() + "'," + txtItemCode.getText() + "," + txtItemPrice.getText() + "," + total + "," + txtItemPrice.getText() + ")");
                }


            }
            else {

                total = Integer.parseInt(txtQty.getText());
                statement.execute("INSERT INTO solditems(transactionID,day,Item,code,price,Qty,total) VALUES(" + transactionID + "," + day + ",'" + txtItemName.getSelectedItem() + "'," + txtItemCode.getText() + "," + txtItemPrice.getText() + "," + total + "," + txtItemPrice.getText() + ")");


            }*/


            //qty=checkItems(ItemName,qty);

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void invoice(int rows, ArrayList list, int index) {

        lblCustName.setText("Customer Name:" + txtSetName.getText());
        columnNames = new String[]{"Item", "Code", "Qty", "Price"};

        invoiceTableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(invoiceTableModel);
        try {


            Statement statement = connection.createStatement();



           /* ResultSet getDate = statement.executeQuery("SELECT * FROM dayCount");
            while (getDate.next()) {
                date = getDate.getInt(1);
                break;
            }*/

            for (int i = 0; i < list.size(); i += 4) {

                Object itemName = list.get(i);
                Object itemCode = list.get(i + 1);
                Object itemQty = list.get(i + 3);
                Object itemPrice = list.get(i + 2);
                invoiceTableModel.addRow(new Object[]{itemName, itemCode, itemQty, itemPrice});

                index++;

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }


        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 90, 500, 300);
        table.setFillsViewportHeight(true);
        tableContainer.add(scrollPane);

    }

    public void printInvoice() {

        String fileName;


        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the current time
        LocalTime currentTime = LocalTime.now();

        // Define date and time formatters
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format the date and time using the formatters
        String formattedDate = currentDate.format(dateFormatter);
        String formattedTime = currentTime.format(timeFormatter);

        // Print the formatted date and time separately
        if(Integer.parseInt(txtPayed.getText())<Integer.parseInt(txtTotal.getText())){
            JOptionPane.showMessageDialog(null,"Payment is not enough!","Payment",JOptionPane.WARNING_MESSAGE);
        }
        else {
            int balance = Integer.parseInt(txtPayed.getText()) - Integer.parseInt(txtTotal.getText());
            txtBalance.setText(String.valueOf(balance));
            try {

                Statement statement = connection.createStatement();




                ResultSet resultSet = statement.executeQuery("SELECT*FROM invoices");
                while (resultSet.next()) {
                    invoiceNo = resultSet.getInt(1);
                }
                if (invoiceNo == 0) {

                    statement.execute("INSERT INTO invoices(invoiceId,customerName,printedDate,printedTime)VALUES(" + 1 + ",'" + txtSetName.getText() + "','" + formattedDate + "','" + formattedTime + "')");
                    invoiceNo = 1;
                } else {
                    ResultSet resultSet2 = statement.executeQuery("SELECT*FROM invoices");
                    while (resultSet2.next()) {
                        invoiceNo = resultSet2.getInt(1);
                    }
                    invoiceNo++;
                    statement.execute("INSERT INTO invoices(invoiceId,customerName,printedDate,printedTime)VALUES(" + invoiceNo + ",'" + txtSetName.getText() + "','" + formattedDate + "','" + formattedTime + "')");
                }
                // creating PdfWriter instance

                fileName = "Invoice-" + invoiceNo;

                PdfWriter writer = new PdfWriter("C:\\Users\\Dragon\\Desktop\\" + fileName + ".pdf");
                // create PdfDocument instance
                PdfDocument pdf = new PdfDocument(writer);

                // create a document instance
                Document document = new Document(pdf);


                Table pdfTable = new Table(4);
                // Adding content
                //document.add(new Paragraph(textPane.getText()));

                PdfFont fontBold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

                Paragraph paragraph = new Paragraph(title);
                paragraph.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(12);
                paragraph.setTextAlignment(TextAlignment.CENTER);
                paragraph.setFont(fontBold).setFontSize(30);

                Paragraph paragraph1 = new Paragraph("Invoice No.:00" + invoiceNo);
                paragraph1.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(12);
                paragraph1.setTextAlignment(TextAlignment.CENTER);
                paragraph1.setFont(fontBold).setFontSize(15);

                Paragraph paragraph2 = new Paragraph("Date:" + formattedDate);
                paragraph2.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(12);
                paragraph2.setTextAlignment(TextAlignment.CENTER);
                paragraph2.setFont(fontBold).setFontSize(15);

                Paragraph paragraph3 = new Paragraph("Time:" + formattedTime);
                paragraph3.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(12);
                paragraph3.setTextAlignment(TextAlignment.CENTER);
                paragraph3.setFont(fontBold).setFontSize(15);

                Paragraph paragraph4 = new Paragraph("Customer name:" + txtSetName.getText());
                paragraph4.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(12);
                paragraph4.setTextAlignment(TextAlignment.CENTER);
                paragraph4.setFont(fontBold).setFontSize(15);

                Table table1 = new Table(3);
                //Cell cell=new Cell().add(paragraph).setWidth(500);


                table1.addCell(new Cell(0, 3).add(paragraph).setBorder(Border.NO_BORDER));

                table1.addCell(new Cell().add(paragraph1).setBorder(Border.NO_BORDER));
                table1.addCell(new Cell().add(paragraph2).setBorder(Border.NO_BORDER));
                table1.addCell(new Cell().add(paragraph3).setBorder(Border.NO_BORDER));
                table1.addCell(new Cell(0, 3).setBorderBottom(new SolidBorder(1)).setBorder(Border.NO_BORDER));//Adding Horizontal line
                table1.addCell(new Cell().add(paragraph4).setBorder(Border.NO_BORDER));
                

                table1.setWidth(450);

                table1.setHorizontalAlignment(HorizontalAlignment.CENTER);

                document.add(table1);


                for (String c : columnNames) {
                    pdfTable.addCell(new Cell().add(new Paragraph().add(c)).setBackgroundColor(DeviceRgb.BLACK).setFontColor(DeviceRgb.WHITE));
                }


                for (int i = 0; i < list.size(); i += 4) {
                    pdfTable.addCell(new Cell().add(new Paragraph().add((String) list.get(i))).setBorder(Border.NO_BORDER));
                    pdfTable.addCell(new Cell().add(new Paragraph().add((String) list.get(i + 1))).setBorder(Border.NO_BORDER));
                    pdfTable.addCell(new Cell().add(new Paragraph().add((list.get(i + 3)).toString())).setBorder(Border.NO_BORDER));
                    pdfTable.addCell(new Cell().add(new Paragraph().add((list.get(i + 2)).toString())).setBorder(Border.NO_BORDER));
                    int price = Integer.parseInt(list.get(i + 3).toString());
                    int qty = Integer.parseInt(list.get(i + 2).toString());
                    totalIncome += price * qty;

                }



                pdfTable.setWidth(450);
                pdfTable.setMargins(10, 0, 0, 0);
                pdfTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
                pdfTable.setBorder(Border.NO_BORDER);
                document.add(pdfTable);


                Table total = new Table(2);
                total.setWidth(450);
                total.setHorizontalAlignment(HorizontalAlignment.CENTER);
                //total.setBorder(Border.NO_BORDER);
                total.addCell(new Cell(0, 3).setBorderTop(new SolidBorder(0.5f)).setBorder(Border.NO_BORDER));
                total.addCell(new Cell().add(new Paragraph().add("Total: ")).setWidth(350).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                total.addCell(new Cell().add(new Paragraph().add("Rs." + totalIncome)).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                total.addCell(new Cell().add(new Paragraph().add("Payed: ")).setWidth(350).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                total.addCell(new Cell().add(new Paragraph().add("Rs." + txtPayed.getText())).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                total.addCell(new Cell().add(new Paragraph().add("Balance: ")).setWidth(350).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                total.addCell(new Cell().add(new Paragraph().add("Rs." + txtBalance.getText())).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));

                document.add(total);


                Table tblBottom = new Table(1);
                tblBottom.setWidth(450);
                tblBottom.setMarginTop(450);
                tblBottom.setHorizontalAlignment(HorizontalAlignment.CENTER);
                tblBottom.addCell(new Cell(0, 2).add(new Paragraph().add("Thank you for choosing iLuxStore!")).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

                document.add(tblBottom);


                Border border2 = new SolidBorder(new DeviceRgb(new Color(154, 154, 154)), 0.5f);

                Table line2 = new Table(1);
                line2.setBorder(border2);
                line2.setHorizontalAlignment(HorizontalAlignment.CENTER);
                line2.setWidth(500);
                document.add(line2);


                document.add(line2);

                Table tblFooter = new Table(2);
                tblFooter.setWidth(500);
                // tblFooter.setMarginTop(500);
                tblFooter.setHorizontalAlignment(HorizontalAlignment.CENTER);
                tblFooter.addCell(new Cell().add(new Paragraph().add("Tel:076554824")).setWidth(225).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                tblFooter.addCell(new Cell().add(new Paragraph().add("No.09/A King Street,Kandy")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                document.add(tblFooter);


                // Close the document
                document.close();
                JOptionPane.showMessageDialog(null, "Invoice printed", "success", JOptionPane.CANCEL_OPTION);
                System.out.println("PDF created successfully!");

            } catch (Exception ex) {
                System.out.println(ex);
            }


            txtSetName.setText(null);
            txtItemName.setSelectedItem(null);
            txtItemPrice.setText(null);
            txtItemCode.setText(null);
            txtQty.setText(null);
            txtTotal.setText("0");
            txtPayed.setText(null);
            txtBalance.setText(null);


            invoiceTableModel.setRowCount(0);
            list.clear();
            totalIncome = 0;
        }



    }



    public void getDate() {
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT* FROM days");
            while (resultSet.next()) {
                day = resultSet.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void getData() {

        String[] columnNames2 = {"Item", "Code", "Price", "Qty"};
        tableModel2 = new DefaultTableModel(columnNames2, 0);

        try {


            Statement statement = connection.createStatement();

            getDate();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM solditems WHERE day=" + day + " ");
            //tableModel2.setRowCount(0);
            while (resultSet.next()) {
                String item = resultSet.getString(3);
                String code = resultSet.getString(4);
                String price = resultSet.getString(5);
                String Qty = resultSet.getString(6);


                tableModel2.addRow(new Object[]{item, code, price, Qty});
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        table2 = new JTable(tableModel2);

        table2.setFont(new Font("Articulat CF Demi Bold",Font.PLAIN,15));
        table2.setRowHeight(20);
        scrollPane3 = new JScrollPane(table2);
        scrollPane3.setBounds(450, 290, 1000, 200);

       // table2.setFillsViewportHeight(true);

        panel.add(scrollPane3);


    }

    public boolean validation(String value) {
        try {
            Integer.parseInt(value);
            return true;

        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean checkFields(){
        if(((String)txtItemName.getSelectedItem()).isEmpty()||txtSetName.getText().isEmpty()||txtItemCode.getText().isEmpty()||txtItemPrice.getText().isEmpty()||txtQty.getText().isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean checkQty(int value){
        boolean itemNotExceeded=true;

        try{
            Connection connection=DbConnection.getConnection();
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory WHERE ItemName='"+txtItemName.getSelectedItem()+"'");
            while (resultSet.next()){
                int qty=resultSet.getInt(5);

                itemNotExceeded= value > qty;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return itemNotExceeded;

    }
    public boolean checkItemName(){
        boolean isAvailble=false;
        try{
            Connection connection=DbConnection.getConnection();
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory WHERE ItemName='"+txtItemName.getSelectedItem()+"'");
            while (resultSet.next()){
               isAvailble=true;


            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return isAvailble;
    }





    public static void main(String[] args){
        Dashboard d=new Dashboard("");


        d.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void lowStockTable(){


        String[] columnNames={"Item","Code","Price","Qty"};
        tableModel=new DefaultTableModel(columnNames,0);

        try{


            Statement statement=connection.createStatement();



            ResultSet resultSet = statement.executeQuery("SELECT * FROM inventory");

            while (resultSet.next()) {
                int availableQty = resultSet.getInt(4);
               if (availableQty <= 10) {
                    String name = resultSet.getString(1);
                    String code = resultSet.getString(2);
                    String price = resultSet.getString(3);
                    String qty = resultSet.getString(4);

                    // Add a new row to the table model
                    tableModel.addRow(new String[]{name, code, price, qty});
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }

                tableLowStock=new JTable(tableModel);
                scrollPaneLow=new JScrollPane(tableLowStock);
                scrollPaneLow.setBounds(450,600,1000,300);
                scrollPaneLow.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                tableLowStock.setFillsViewportHeight(true);
                panel.add(scrollPaneLow);

               // tableModel.setRowCount(0);





        // Iterate through the result set and update the table model

    }

    public void updateList(){


        try{

            Statement statement=connection.createStatement();



            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory ");
            tableModel.setRowCount(0);//resetting values
            while (resultSet.next()) {
                count+=resultSet.getInt(4);
                int availableQty = resultSet.getInt(4);
                if (availableQty <= 10) {


                    String name = resultSet.getString(1);
                    String code = resultSet.getString(2);
                    String price = resultSet.getString(3);
                    String qty = resultSet.getString(4);

                    // Add a new row to the table model
                    tableModel.addRow(new Object[]{name, code, price,qty});
                }

            }


            getDate();
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM solditems WHERE day="+day+" ");
            tableModel2.setRowCount(0);//resetting values
            while (resultSet2.next()) {


                String name=resultSet2.getString(2);
                String code = resultSet2.getString(3);
                String price = resultSet2.getString(4);
                String qty = resultSet2.getString(5);

                // Add a new row to the table model
                tableModel2.addRow(new Object[]{name, code, price,qty});

            }

        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void setDays(){
        int salesCount=AmountOfSoldItems();

        int totalEarning=0;

        int day=2;
        boolean isSameDay=false;
        boolean isEmpty=false;



        try{

            Statement statement=connection.createStatement();



            ResultSet dayResualt=statement.executeQuery("SELECT days FROM days WHERE 1");
            while (dayResualt.next()){
                day=dayResualt.getInt(1);
            }

            /*ResultSet earn=statement.executeQuery("SELECT *FROM solditems WHERE day="+ day+" ");
            while (earn.next()){
                totalEarning+=earn.getInt(6);
            }
            System.out.println("TOTAL EARNINGS: "+totalEarning);*/




            ResultSet resultSet=statement.executeQuery("SELECT *FROM days");
            while (resultSet.next()){
                int value=resultSet.getInt(1);
                if(value==0){
                    isEmpty=true;
                }
                if(day==resultSet.getInt(1)){
                    isSameDay=true;
                }



            }






            if(isEmpty){

                ResultSet earn=statement.executeQuery("SELECT *FROM solditems WHERE day="+ 1+" ");
                while (earn.next()){
                    totalEarning+=earn.getInt(7);
                }
                statement.execute("INSERT INTO days(days,amount)VALUES("+1+","+totalEarning+")");
            }
          else if(isSameDay) {
                ResultSet earn=statement.executeQuery("SELECT *FROM solditems WHERE day="+ day+" ");
                while (earn.next()){
                    totalEarning+=earn.getInt(7);
                }
                statement.execute("UPDATE days SET amount="+totalEarning+" WHERE days="+day+" ");

            }

          else{
              ResultSet earn=statement.executeQuery("SELECT *FROM solditems WHERE day="+ day+" ");
              while (earn.next()){
                  totalEarning+=earn.getInt(7);
              }
              statement.execute("INSERT INTO days(days,amount)VALUES("+day+","+totalEarning+")");

          }






        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }




    public int AmountOfSoldItems(){
        int soldCount=0;
        try{

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM solditems");

            while (resultSet.next()){

                soldCount+=resultSet.getInt(4);
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
        return soldCount;
    }

    public int updateDay(){

        try{

            Statement statement=connection.createStatement();


            ResultSet resultSet=statement.executeQuery("SELECT * FROM days");

            while (resultSet.next()){

                day=resultSet.getInt(1);

            }

            day++;
            statement.execute("INSERT INTO days(days,amount)VALUES("+day+","+0+")  ");
           System.out.println("table Updated" );

        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return day;
    }

    public void updateInventory(){



        int newQty;
        int prvQty=0;

        try{

            Statement statement=connection.createStatement();
            ResultSet inResult=statement.executeQuery("SELECT * FROM inventory WHERE ItemName='"+txtItemName.getSelectedItem()+"'");
            while (inResult.next()) {
                prvQty = inResult.getInt(5);
                newQty=prvQty-(Integer.parseInt(txtQty.getText()));

                statement.execute("UPDATE inventory SET Qty="+newQty+" WHERE ItemName='"+txtItemName.getSelectedItem()+"'");
                System.out.println("inventory updated: ");
            }

        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void createsoldItemsTable(){
        try{

            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE solditems(transactionID INT,day INT,Item VARCHAR(255),code VARCHAR(255),price INT,Qty INT,total INT");



        }
        catch (Exception ex){
            System.out.println("Sold Items table created");
        }
    }


    public void openPanel2(JLayeredPane panel2) {

        if(leftPanel.getX()==0) {

            System.out.println("side Panel is opend");
            int x = panel2.getX();
            int finalX = 300;
            int step = 100;
            //when side Panel opend



            if (panel2.getX() > finalX) {
                //panel is closed
                Timer timer = new Timer(1, e -> {

                    panel2.setLocation(panel2.getX() - step, panel2.getY());

                    System.out.println(panel2.getX());
                    if(panel2.getX()<=finalX) {

                        ((Timer) e.getSource()).stop();
                    }

                });

                timer.start();


            }

            else if(isComplete){//Panel is open
                Timer timer = new Timer(1, e -> {

                    panel2.setLocation(panel2.getX() + step, panel2.getY());

                    System.out.println(panel2.getX());
                    if(panel2.getX()>=1680) {

                        ((Timer) e.getSource()).stop();
                    }

                });

                timer.start();
            }
            else{
                int result=JOptionPane.showConfirmDialog(null,"Abort the process?","Cancell process",JOptionPane.YES_NO_OPTION);
                if(result==0){
                    Timer timer = new Timer(1, e -> {

                        panel2.setLocation(panel2.getX() + step, panel2.getY());
                        System.out.println(panel2.getX());
                        if(panel2.getX()>=1680) {

                            ((Timer) e.getSource()).stop();
                        }

                    });

                    timer.start();
                }
            }



        }
        else{
            System.out.println("side Panel is closed");
            int x = panel2.getX();
            int finalX = 0;
            int step = 100;
           //when side panel closed

            if (panel2.getX() > finalX) {
                //panel2 is cloase
                Timer timer = new Timer(1, e -> {

                    panel2.setLocation(panel2.getX() - step, panel2.getY());


                    panel3.setLocation(panel3.getX()+10,panel3.getY());



                    System.out.println("abc: "+panel2.getX());
                    if(panel2.getX()<=finalX) {

                        ((Timer) e.getSource()).stop();
                    }

                });

                timer.start();


            }//when panel is closed
            else{
                Timer timer = new Timer(1, e -> {
                    int step2=5;

                    panel2.setLocation(panel2.getX() + step, panel2.getY());
                    panel3.setLocation(panel3.getX()-10,panel3.getY());


                    System.out.println(panel2.getX());
                    if(panel2.getX()>=1680) {

                        ((Timer) e.getSource()).stop();
                    }

                });

                timer.start();
            }//when panel is opened


        }
        /*int X;
        int finalX ;

        int step;

        Timer timer;

        if(leftPanel.getX()==0){//action when left side panel open
            if (panel2.getX() == 1680) {


                btnOffCust.setIcon(img2);
                X = panel2.getX();
                finalX = X - 1380;
                step = 100;
                int finalX1 = finalX;
                int finalStep = step;
                timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int curruntX = panel2.getX();
                        if (curruntX > finalX1) {
                            panel2.setLocation(curruntX - finalStep, panel2.getY());



                        } else {
                            ((Timer) e.getSource()).stop();
                        }

                    }

                });
                timer.start();
            }
           else{
                /*if(complete) {
                    btnOffCust.setIcon(img);
                    X = panel2.getX();

                    finalX = X + 1380;
                    step = 100;
                    int finalX2 = finalX;
                    int finalStep1 = step;
                    timer = new Timer(10, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        int curruntX = panel2.getX();
                        if (curruntX < finalX2) {
                            panel2.setLocation(curruntX + finalStep1, panel2.getY());

                        } else {
                            ((Timer) e.getSource()).stop();
                        }


                    }

                });
                timer.start();


                }
                else{



                        }

                    });
                    timer.start();



                }
            }

            }

        }
        else{
            if (panel2.getX()==1680) {
                btnOffCust.setIcon(img2);
                X = panel2.getX();
                finalX = X - 1680;
                step = 100;
                int finalX4 = finalX;
                int finalStep3 = step;
                timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int curruntX = panel2.getX();

                        if (curruntX > finalX4) {
                            panel2.setLocation(curruntX - finalStep3, panel2.getY());


                        } else {
                            ((Timer) e.getSource()).stop();
                        }

                    }

                });
                timer.start();


            }
            else {

                    int result=JOptionPane.showConfirmDialog(null,"Abort the process?","error",JOptionPane.YES_NO_OPTION);
                    if(result==0){
                        btnOffCust.setIcon(img);

                        X = panel2.getX();
                        finalX = X + 1680;
                        step = 100;
                        int finalX5 = finalX;
                        int finalStep4 = step;
                        timer = new Timer(10, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int curruntX = panel2.getX();
                                if (curruntX < finalX5) {
                                    panel2.setLocation(curruntX + finalStep4, panel2.getY());
                                    panel2.setOpaque(true);

                                } else {
                                    ((Timer) e.getSource()).stop();
                                }

                            }



                        });
                        timer.start();
                    }



            }
        }*///Action when side panale close



    }
    public void autoFill(){
        String itemName=(String) txtItemName.getSelectedItem();


        try{

            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT *FROM inventory");
            while (resultSet.next()){
                if(resultSet.getString(1).equals(itemName)){
                    txtItemCode.setText(resultSet.getString(2));
                    txtItemPrice.setText(resultSet.getString(4));
                    break;
                }
            }




        }
        catch (Exception ex){
            System.out.println(ex);
        }


    }
    public void fillArray(){
        ArrayList<String > ls=new ArrayList<>();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");
            while (resultSet.next()){
                ls.add(resultSet.getString(1));
            }


        }
        catch (Exception e){
            System.out.println(e);
        }
        items=new String[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            items[i]=ls.get(i);
        }
    }
    public void searchAndFill(JComboBox<String>comboBox,String[] items){
        JTextField textField=(JTextField) comboBox.getEditor().getEditorComponent();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ArrayList<String> ls=new ArrayList<>();
                String selectedItem=textField.getText();
                for(String item:items){
                    if(item.toLowerCase().contains(selectedItem.toLowerCase())){
                        ls.add(item);
                    }

                }
                DefaultComboBoxModel<String> model=new DefaultComboBoxModel<>(ls.toArray(new String[0]));
                comboBox.setModel(model);
                comboBox.setSelectedItem(selectedItem);
                comboBox.showPopup();

            }

        });

    }

    /*public void getNewData(){


        data=AmountOfSoldItems();




        try{

            Statement statement=connection.createStatement();

            ResultSet prev=statement.executeQuery("SELECT *FROM days");
            while (prev.next()){

                prevValue=prev.getInt(2);
                break;


            }
            newData=prevValue-data;



        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void setPercentage(){
        boolean isEqual=false;
        int percentage=0;
        createPercentageTable();
        int totalQuantity=showCount();
        int sold=AmountOfSoldItems();
        if(totalQuantity==0){










            if(percentage!=0 && !isEqual) {
                statement.execute("INSERT INTO percentagees(amount) VALUE(" + percentage + ")");
            }

        }
        catch (Exception ex){
            System.out.println(ex);
        }



    }
    public ArrayList<Integer> getPercentage(){
        int percentage=0;
        ArrayList<Integer> list=new ArrayList<>();

        try{

            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT * FROM percentagees");

            while (resultSet.next()){
                list.add(resultSet.getInt(1));

            }



        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return list;
    }

    public void storeCount(){
        //createStoreCountTable();
        try{

            Statement statement=connection.createStatement();


            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");

            while (resultSet.next()){

                stock+=resultSet.getInt(4);
            }
            statement.execute("INSERT INTO stockcount(amount) VALUE("+stock+")");

        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void createStoreCountTable(){
        try{

            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE  stockcount(amount INT)");


        }
        catch (Exception ex){

        }
    }
    public void createPercentageTable(){
        try{

            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE  percentagees(amount INT)");


        }
        catch (Exception ex){

        }
    }
    public void createdayCountTable(){
        try{

            Statement statement=connection.createStatement();

            statement.execute("CREATE TABLE  dayCount(day INT)");
            statement.execute("INSERT INTO dayCount(day) VALUES(1)");

        }
        catch (Exception ex){

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

        }
    }
    public void setname(String name) {
        this.name = name;

    }


    public void RightPanelOpen(JLayeredPane rightPanel) {
        Clicked += 1;
        if (Clicked == 1) {
            int panelX = rightPanel.getX();
            int setTo = panelX - 300;
            int step = 100;
            Timer time = new Timer(10, e -> {
                int CurruntX = rightPanel.getX();
                if (CurruntX > setTo) {
                    rightPanel.setLocation(CurruntX - step, rightPanel.getY());
                } else {
                    ((Timer) e.getSource()).stop();
                }
            });
            time.start();

        }

    }

    public void RightPanelClose(JLayeredPane rightPanel) {


        int panelX = rightPanel.getX();
        int setTo = panelX + 300;
        int step = 100;
        Timer time = new Timer(10, e -> {
            int CurruntX = rightPanel.getX();
            if (CurruntX < setTo) {
                rightPanel.setLocation(CurruntX + step, rightPanel.getY());
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        time.start();


    }
    public void removeItem(String item){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/iluxstore","root","");
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory WHERE ItemName='"+item+"'");
            while (resultSet.next()) {
                qty = resultSet.getInt(4);
            }
            int newQty=qty-1;
            //System.out.println(qty);

            statement.execute("UPDATE inventory SET Qty="+newQty+" WHERE ItemName='"+item+"'");



        }catch (Exception ex){
            System.out.println(ex);
        }
    }*/
    public int showCount(){
        int stock=0;
        try{

            Statement statement=connection.createStatement();
            //statement.execute("CREATE TABLE  stockcount(amount INT)");

            ResultSet resultSet=statement.executeQuery("SELECT * FROM stockcount");

            while (resultSet.next()){

                stock=resultSet.getInt(1);
                break;
            }


        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return stock;
    }

    public void createDayTable(){
        int i=0;
        try{

            Statement statement=connection.createStatement();



        }
        catch (Exception ex){

        }
    }
    public void createInvoiceTable() {
        try {

            Statement statement = connection.createStatement();





        } catch (Exception ex) {
            System.out.println("Invoice table Created");
        }
    }
}
