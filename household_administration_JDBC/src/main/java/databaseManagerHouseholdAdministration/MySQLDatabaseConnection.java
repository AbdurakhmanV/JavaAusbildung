package databaseManagerHouseholdAdministration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnection {

//    private static Connection mySQLDatabaseConnection;

    public MySQLDatabaseConnection(){

    }

//    public Connection getMySQLDatabaseConnection(){
//        return this.mySQLDatabaseConnection;
//    }

    public Connection startMySQLConnection(){
        try {
            String mySQLDatabaseURL = "jdbc:mysql://localhost:3306/household_administration02";
            String user = "root";
            String password = "";
            Connection mySQLDatabaseConnection = DriverManager.getConnection(mySQLDatabaseURL, user, password);
            System.out.println("\n------connection to database successful------");
            return mySQLDatabaseConnection;

        }catch (SQLException e){
            System.out.println("\nError:\ndatabase connection failed!\n");
            e.printStackTrace();
        }
        return null;
    }
}
