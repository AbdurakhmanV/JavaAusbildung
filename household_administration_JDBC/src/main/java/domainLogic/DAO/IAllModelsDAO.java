package domainLogic.DAO;

import databaseManager.MySQLDatabaseQueryAndExecuteQuery;

public interface IAllModelsDAO<T>{

   MySQLDatabaseQueryAndExecuteQuery queryManager = new MySQLDatabaseQueryAndExecuteQuery();
   int addObjectIntoEntity(T model) throws Exception;

   void deleteObjectFromDatabase(int id) throws Exception;



}
