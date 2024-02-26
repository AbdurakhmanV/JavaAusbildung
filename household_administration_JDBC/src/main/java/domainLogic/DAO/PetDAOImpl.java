package domainLogic.DAO;

import domainLogic.Pet;

import java.sql.ResultSet;
import java.util.List;

public class PetDAOImpl implements IPetDAO{
    @Override
    public int addObjectIntoEntity(Pet model) throws Exception {
        return 0;
    }

    @Override
    public void deleteObjectFromDatabase(int id) throws Exception {

    }

    @Override
    public List<Pet> getPetsByPersonId(int personId) throws Exception {
        ResultSet resultSet = queryManager.querySelect(new Pet(0, "", "", personId));
        return queryManager.getPetList(resultSet);
    }
}
