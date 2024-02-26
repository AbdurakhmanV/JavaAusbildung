package domainLogic.DAO;

import domainLogic.Address;
import domainLogic.Household;

import java.util.List;

public interface IHouseholdDAO extends IAllModelsDAO<Household> {



    Household getHouseholdByHouseholdId(int householdId) throws Exception;
    List<Household> getAllHouseholdsFromDatabase() throws Exception;
    void updateHouseholdName(Household household, String householdName) throws Exception;
    List<Household> getHouseholdByHouseholdName(String householdName) throws Exception;
    List<Household> getHouseholdByHouseholdAddress(Address address) throws Exception;


}
