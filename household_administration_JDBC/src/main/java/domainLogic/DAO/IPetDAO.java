package domainLogic.DAO;

import domainLogic.Pet;

import java.util.List;

public interface IPetDAO extends IAllModelsDAO<Pet>{
    List<Pet> getPetsByPersonId(int personId) throws Exception;
}
