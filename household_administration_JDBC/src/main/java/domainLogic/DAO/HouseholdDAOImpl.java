package domainLogic.DAO;
import databaseManager.MySQLDatabaseQueryAndExecuteQuery;
import domainLogic.Address;
import domainLogic.Household;

import java.sql.ResultSet;
import java.util.List;



public class HouseholdDAOImpl implements IHouseholdDAO {
    @Override
    public int addObjectIntoEntity(Household household) throws Exception {
        ResultSet preResultSet = queryManager.queryInsert(household);
        ResultSet resultSet = queryManager.querySelect(new Household(preResultSet.getInt("GENERATED_KEY"), ""));
        List<Household> houseHoldList =  queryManager.getHouseholdList(resultSet);
        return houseHoldList.get(0).getHouseholdId();
    }

    @Override
    public Household getHouseholdByHouseholdId(int householdId) throws Exception {
        ResultSet resultSet = queryManager.querySelect(new Household(householdId,""));
        List<Household> householdList = queryManager.getHouseholdList(resultSet);
        return householdList.get(0);
    }

    @Override
    public List<Household> getAllHouseholdsFromDatabase() throws Exception {
        ResultSet resultSet = queryManager.querySelectAllObjectsInEntity("HOUSEHOLD");
        List<Household> houseHoldList =  queryManager.getHouseholdList(resultSet);
        return houseHoldList;
    }

    @Override
    public void deleteObjectFromDatabase(int householdID) throws Exception {
    queryManager.queryDelete(new Household(householdID, ""));
    }

    @Override
    public void updateHouseholdName(Household household, String householdName) throws Exception {
    queryManager.queryUpdate(household, householdName, household.getHouseholdId());
    }


    @Override
    public List<Household> getHouseholdByHouseholdName(String householdName) throws Exception {
        ResultSet resultSet = queryManager.querySelect(new Household(0, householdName, 0));
        return queryManager.getHouseholdList(resultSet);
    }

    @Override
    public List<Household> getHouseholdByHouseholdAddress(Address address) throws Exception {
        ResultSet resultSet = queryManager.querySelect(new Household(address.getHouseholdId(), ""));
        return queryManager.getHouseholdList(resultSet);
    }




}

//    @Override
//    public void deleteHousehold(int householdId) throws Exception{
//        queryManager.queryDelete(new Household(householdId, "", 0));
//    }
//
//    @Override
//    public void updateHouseholdName(int householdId, String householdName) throws Exception{
//
//        queryManager.queryUpdate(new Household(householdId, "", 0),
//                new Household(householdId, householdName, 0));
//        //TODO
//        //TODO
//        //TODO
//    }
