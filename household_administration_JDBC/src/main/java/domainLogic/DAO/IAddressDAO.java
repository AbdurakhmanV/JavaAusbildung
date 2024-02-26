package domainLogic.DAO;

import domainLogic.Address;

import java.sql.ResultSet;

public interface IAddressDAO extends IAllModelsDAO<Address>{
    boolean checkIfAddressExistsInDatabase(Address address) throws Exception;
    Address getAddressFromDatabase(Address address) throws Exception;

    Address getAddressByHouseholdIdFromDatabase(int householdId) throws Exception;

    void updateAddress(Address addressToUpdate, Address addressNew) throws Exception;
}
