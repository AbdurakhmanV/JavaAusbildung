package databaseManager;

public class MySQLDatabaseManager {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private static MySQLDatabaseQueryAndExecuteQuery queryManager;


    public void startDatabaseManager(){
        queryManager = new MySQLDatabaseQueryAndExecuteQuery(databaseConnection.startMySQLConnection());
    }

}
