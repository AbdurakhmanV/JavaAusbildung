package userInterfaceHouseholdAdministration;
import databaseManagerHouseholdAdministration.*;

public class Main {
    public static void main(String[] args) {
        MySQLDatabaseConnection mySQLDatabaseConnectionManager = new MySQLDatabaseConnection();
        MySQLDatabaseQueryAndExecuteQuery mySQLDatabaseManager = new MySQLDatabaseQueryAndExecuteQuery(mySQLDatabaseConnectionManager.startMySQLConnection());
        RunProgramHouseholdAdministration runProgramHouseholdAdministration = new RunProgramHouseholdAdministration();
        runProgramHouseholdAdministration.startHouseholdAdministration();
    }
}

