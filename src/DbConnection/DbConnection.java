package DbConnection;

import java.sql.*;

public class DbConnection {

    private static Connection connection;

    static {
        establishConnection();
    }

    private static void establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iluxstore", "root", "");

            Statement statement = connection.createStatement();


            //statement.execute("CREATE DATABASE iluxstore");
           // statement.execute("use iluxstore");
            //System.out.println("Connected successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

