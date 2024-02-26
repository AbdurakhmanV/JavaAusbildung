package domainLogic.DAO;

import domainLogic.Household;
import domainLogic.Person;

import java.sql.ResultSet;
import java.util.List;

public class PersonDAOImpl implements IPersonDAO{
    @Override
    public int addObjectIntoEntity(Person model) throws Exception {
        return 0;
    }

    @Override
    public void deleteObjectFromDatabase(int id) throws Exception {

    }

    @Override
    public List<Person> getAllPersonsFromHousehold(int householdId) throws Exception {
        ResultSet resultSet = queryManager.querySelect(new Person(0,"", "", null, null, householdId));
        return queryManager.getPersonList(resultSet);
    }
}
