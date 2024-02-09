package databaseManagerHouseholdAdministration;

import domainLogicHouseholdAdministration.Address;
import domainLogicHouseholdAdministration.Household;

import java.sql.ResultSet;
import java.util.List;

public class MySQLDatabaseManager {
    private final MySQLDatabaseQueryAndExecuteQuery queryManager = new MySQLDatabaseQueryAndExecuteQuery();

    public int addHouseholdIntoDatabase(String householdName) throws Exception{
        Household household = new Household(0,householdName, 0);
        queryManager.queryInsert("HOUSEHOLD", household);

        ResultSet resultSet = queryManager.querySelect("HOUSEHOLD", household);
        List<Object> houseHoldList = queryManager.getRequiredData(resultSet, "HOUSEHOLD");

        return((Household)houseHoldList.get(0)).getHouseholdId();
    }

    public void addAddressIntoDatabase(int householdId, String[] addressArray) throws Exception{
        String zipCode = addressArray[0];
        String city = addressArray[1];
        String street = addressArray[2];
        String houseNumber = addressArray[3];
        int doorNumber;
        if (addressArray.length == 4) {
            doorNumber = 0;
        } else {
            doorNumber = Integer.parseInt(addressArray[4]);
        }
        Address address = new Address(0,zipCode, city, street, houseNumber, doorNumber, householdId);
        queryManager.queryInsert("ADDRESS", address);
    }

    public List<Object> showAllHousehold(){
        ResultSet resultSet = queryManager.querySelect("HOUSEHOLD_ID", null);
        return queryManager.getRequiredData(resultSet, "HOUSEHOLD");
    }



}
