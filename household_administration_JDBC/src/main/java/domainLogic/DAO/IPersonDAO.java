package domainLogic.DAO;

import domainLogic.Person;

import java.util.List;

public interface IPersonDAO extends IAllModelsDAO<Person> {
    List<Person> getAllPersonsFromHousehold(int householdId) throws Exception;
}
