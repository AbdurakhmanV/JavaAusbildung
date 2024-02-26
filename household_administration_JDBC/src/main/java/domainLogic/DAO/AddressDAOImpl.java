package domainLogic.DAO;

import databaseManager.MySQLDatabaseQueryAndExecuteQuery;
import domainLogic.Address;
import domainLogic.Household;

import java.sql.ResultSet;
import java.util.List;

public class AddressDAOImpl implements IAddressDAO{
    @Override
    public boolean checkIfAddressExistsInDatabase(Address address) throws Exception{
        ResultSet resultSet = queryManager.querySelect(address);
        if(resultSet!=null){
            throw new Exception("\nFehler:\nDiese Addresse existiert bereits in der Datenbank.\n" +
                    "Somit gibt es bereits einen Haushalt mit dieser Adresse.\n");
        }else {
            return true;
        }
    }
    @Override
    public Address getAddressFromDatabase(Address address) throws Exception{
        ResultSet resultSet = queryManager.querySelect(address);
        return queryManager.getAddress(resultSet);
    }

    public Address getAddressByHouseholdIdFromDatabase(int householdId) throws Exception{
        ResultSet resultSet = queryManager.querySelect(new Address(0, "", "", "", "", 0, householdId));
        return queryManager.getAddress(resultSet);
    }

    @Override
    public void updateAddress(Address addressToUpdate, Address addressNew) throws Exception {
        if(addressNew.getDoorNumber() != 0) {
            queryManager.queryUpdate(addressToUpdate, addressNew.getZipcode(), addressNew.getCity(), addressNew.getStreet(), addressNew.getHouseNumber(), addressNew.getDoorNumber(), addressToUpdate.getAddressId());
        }else{
            queryManager.queryUpdate(addressToUpdate, addressNew.getZipcode(), addressNew.getCity(), addressNew.getStreet(), addressNew.getHouseNumber(), addressToUpdate.getAddressId());
        }
    }

    @Override
    public int addObjectIntoEntity(Address address) throws Exception {
        System.out.println(address.toString());
        ResultSet resultSet = queryManager.queryInsert(address);
        return 0;
    }

    @Override
    public void deleteObjectFromDatabase(int id) throws Exception {

    }
}
