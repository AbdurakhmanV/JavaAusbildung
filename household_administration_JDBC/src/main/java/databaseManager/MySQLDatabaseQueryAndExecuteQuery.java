package databaseManager;

import domainLogic.*;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDatabaseQueryAndExecuteQuery<T> {

    //helping objects and variables begin--------------------------------------------------------------------------------------
    private static Connection mySQLDatabaseConnection;
    private static Object[] objects;
    private static String entity = "";


    //helping objects and variables end--------------------------------------------------------------------------------------


    //helping objects and variables, getter ande setter begin--------------------------------------------------------------------------------------
    public static void setEntity(String entity) {
        MySQLDatabaseQueryAndExecuteQuery.entity = entity;
    }

    //helping objects and variables, getter ande setter end--------------------------------------------------------------------------------------

//constructor begin------------------------------------------------------------------------------------------------------------------------

    public MySQLDatabaseQueryAndExecuteQuery() {

    }

    public MySQLDatabaseQueryAndExecuteQuery(Connection mySQLDatabaseConnection) {
        this.mySQLDatabaseConnection = mySQLDatabaseConnection;
    }

//constructor end------------------------------------------------------------------------------------------------------------------------


    //select statement begin------------------------------------------------------------------------------------------------------------------------
    public ResultSet querySelectAllObjectsInEntity(String entity) {
        String querySelect = "SELECT * FROM " + entity + ";";
        ResultSet resultSet = executeResultSetInDatabase(querySelect, null);
        return resultSet;

    }

    //the method querySelect creates a select query and transfers it executeResultSetInDatabase,
    //which returns a ResulSet and this ResultSet is also the return value of this method
    public ResultSet querySelect(T model) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ENTITY");

        String querySelect = createPartOfQuerySelectOrQueryDelete(stringBuilder, model);
        ResultSet resultSet = executeResultSetInDatabase(querySelect, this.objects);
        return resultSet;
    }

    //The following method "selectQueryInnerJoin" creates a select statement based on inner join in the MySQL database.
    //The condition as parameter of the method is regulating whether the inner join as a condition or not.
    public ResultSet selectQueryInnerJoin(String entityOne, String entityTwo, boolean condition, int id) {
        //TODO: if a inner join is needed. It might be needed for HOUSEHOLD and ADDRESS.
        StringBuilder stringBuilder = new StringBuilder();
        Object[] objects = new Object[]{};
        if (entityOne.equals("HOUSEHOLD")) {
            stringBuilder.append("SELECT * FROM " + entityOne + " INNER JOIN " + entityTwo + " ON ");

            stringBuilder.append(entityOne + ".HOUSEHOLD_ID = " + entityTwo + ".HOUSEHOLD_ID");
            this.entity = entityOne + " INNER JOIN " + entityTwo;

            if (condition) {
                stringBuilder.append(" WHERE " + entityTwo + ".HOUSEHOLD_ID = ?");
                objects = new Object[]{id};

            } else {
                objects = null;
            }
        }
//        if (entityOne.equals("PET")) {
//            stringBuilder.append("SELECT * FROM " + entityOne + " INNER JOIN " + entityTwo + " ON ");
//
//            stringBuilder.append(entityOne + ".PERSON_ID = " + entityTwo + ".PERSON_ID");
//            this.entity = entityOne + " INNER JOIN " +entityTwo;
//
//            if (condition) {
//                stringBuilder.append(" WHERE " + entityOne + ".HOUSEHOLD_ID = ?");
//                objects = new Object[]{id};
//
//            }else{
//                objects = null;
//            }
//        }

        stringBuilder.append(";");
        String queryInnerJoinSelect = stringBuilder.toString();
//        System.out.println(queryInnerJoinSelect);
        ResultSet resultSet = executeResultSetInDatabase(queryInnerJoinSelect, objects);
        return resultSet;
    }
//select statement end------------------------------------------------------------------------------------------------------------------------

//insert statement begin------------------------------------------------------------------------------------------------------------------------

    //create an insert query depending on this.entity and values from
    //transfer it to the method executeQueryInDatabase, which returns a ResulSet and this ResultSet is also the return value of this method
    public ResultSet queryInsert(T object) throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ENTITY");

        if (object instanceof Household) {
            Household household = (Household) object;
            stringBuilder.append(" (HOUSEHOLD_NAME) VALUES(?);");
            this.objects = new Object[]{household.getHouseholdName()};
            this.entity = "HOUSEHOLD";
        } else if (object instanceof Address) {
            Address address = (Address) object;
            stringBuilder.append(" (ZIPCODE, CITY, STREET, HOUSE_NUMBER, ").append(address.getDoorNumber() == 0 ? "HOUSEHOLD_ID) VALUES(?, ?, ?, ?, ?);" : "DOOR_NUMBER, HOUSEHOLD_ID) VALUES(?, ?, ?, ?, ?, ?);");
            this.objects = address.getDoorNumber() == 0 ? new Object[]{address.getZipcode(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getHouseholdId()}
                    : new Object[]{address.getZipcode(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getDoorNumber(), address.getHouseholdId()};
            this.entity = "ADDRESS";
        } else if (object instanceof Person) {
            Person person = (Person) object;
            stringBuilder.append(" (FIRSTNAME, LASTNAME, BIRTHDATE, GENDER, HOUSEHOLD_ID) VALUES(?, ?, ?, ?, ?);");
            String gender = person.getGender().toString();
            this.objects = new Object[]{person.getFirstName(), person.getLastName(), person.getBirthdate(), gender, person.getHouseholdId()};
            this.entity = "Person";
        } else if (object instanceof Pet) {
            Pet pet = (Pet) object;
            stringBuilder.append(" (ANIMAL_KIND, PET_NAME, PERSON_ID) VALUES(?, ?, ?);");
            this.objects = new Object[]{pet.getAnimalKind(), pet.getPetName(), pet.getPersonId()};
            this.entity = "Pet";
        }

        String queryInsert = stringBuilder.toString();
        queryInsert = queryInsert.replace("ENTITY", this.entity);
//        System.out.println(queryInsert);
        return (executeQueryInDatabase(queryInsert, this.objects));
    }
//insert statement end------------------------------------------------------------------------------------------------------------------------


    //delete statement begin------------------------------------------------------------------------------------------------------------------------
    //The method "queryDelete" creates a delete-query and transfers it to "executeQueryInDatabase" to execute the delete-query.
    public void queryDelete(T object) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM ENTITY");

        String queryDelete = createPartOfQuerySelectOrQueryDelete(stringBuilder, object);

        executeQueryInDatabase(queryDelete, this.objects);

    }


//delete statement end------------------------------------------------------------------------------------------------------------------------


    //update statement begin------------------------------------------------------------------------------------------------------------------------
    //The method "queryUpdate" creates an update-statement for a MySQL database. After
    public void queryUpdate(T objectToUpdate, Object...objects) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ENTITY SET ");
        Household household;
        Address address;
        Person person;
        Pet pet;
        if (objectToUpdate instanceof Household) {
            household = (Household) objectToUpdate;
            stringBuilder.append(String.format("%s", household.getHouseholdName().equals("") ? "" : "HOUSEHOLD_NAME = ?, "));
            this.entity = "HOUSEHOLD";
        } else if (objectToUpdate instanceof Address) {
            address = (Address) objectToUpdate;
            stringBuilder.append(String.format("%s%s%s%s%s", address.getZipcode().equals("") ? "" : "ZIPCODE = ?, ", address.getCity().equals("") ? "" : "CITY = ?, ",
                    address.getStreet().equals("") ? "" : "STREET = ?, ", address.getHouseNumber().equals("") ? "" : "HOUSE_NUMBER = ?, ",
                    address.getDoorNumber() == 0 ? "" : "DOOR_NUMBER = ?, "));
            this.entity = "ADDRESS";
        } else if (objectToUpdate instanceof Person) {
            person = (Person) objectToUpdate;
            stringBuilder.append(String.format("%s%s%s%s", person.getFirstName().equals("") ? "" : "FIRSTNAME = ?, ", person.getLastName().equals("") ? "" : "LASTNAME = ?, ",
                    person.getBirthdate() == null ? "" : "BIRTHDATE = ?, ", person.getGender() == null ? "" : "GENDER = ?, "));
            this.entity = "PERSON";
        } else if (objectToUpdate instanceof Pet) {
            pet = (Pet) objectToUpdate;
            stringBuilder.append(String.format("%s%s%s%s", pet.getAnimalKind().equals("") ? "" : "ANIMAL_KIND = ?, ", pet.getPetName().equals("") ? "" : "PET_NAME = ?, ", pet.getPersonId() == 0 ? "" : "PERSON_ID = ?, "));
            this.entity = "PET";
        }
        this.objects = objects;
        stringBuilder = subStringAdjuster(stringBuilder, 2);
        stringBuilder.append(" WHERE " + this.entity + "_ID = ?;");
        String queryUpdate = stringBuilder.toString();
        queryUpdate = queryUpdate.replace("ENTITY", this.entity);
//        System.out.println(queryUpdate);
        ResultSet resultSet = executeQueryInDatabase(queryUpdate, this.objects);



    }

//update statement end------------------------------------------------------------------------------------------------------------------------


//execute statement in database begin---------------------------------------------------------------------------------------------------------------------

    //The method "executeQueryInDatabase" conducts the query statement. It processes the insert, delete and update queries
    //and returns a ResultSet, inorder to work with the new implemented entity object data in the MySQL database.
    public ResultSet executeQueryInDatabase(String query, Object[] conditionEntityColumnValues) throws Exception {
        try {
            PreparedStatement statement = this.mySQLDatabaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < conditionEntityColumnValues.length; i++) {
                if (conditionEntityColumnValues[i] instanceof String) {
                    if (!((String) conditionEntityColumnValues[i]).equals("")) {
                        statement.setString(i + 1, (String) conditionEntityColumnValues[i]);
                    }
                } else if (conditionEntityColumnValues[i] instanceof Integer) {
                    if ((int) conditionEntityColumnValues[i] != 0) {
                        statement.setInt(i + 1, (Integer) conditionEntityColumnValues[i]);
                    }
                } else if (conditionEntityColumnValues[i] instanceof java.sql.Date) {
                    if (conditionEntityColumnValues[i] != null) {
                        statement.setDate(i + 1, (java.sql.Date) conditionEntityColumnValues[i]);
                    }
                }
            }
            statement.execute();
            System.out.println("--------executeQuery executed!--------");
            if(query.contains("INSERT")) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (!resultSet.next()) {
                    throw new Exception("\nFehler:\nResultSet ist Null!\nEs wurden keine Daten gefunden!\n");
                }
//                ResultSetMetaData metaData = resultSet.getMetaData();
//                int columnCount = metaData.getColumnCount();
//                for (int i = 1; i <= columnCount; i++) {
//                    System.out.println("Column name: " + metaData.getColumnName(i));
//                }
                return resultSet;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    //The method "executeResultSetInDatabase" conducts the query statement. It processes the select queries
    //and returns a ResultSet, based on the select query, inorder to receive the selected data from the tuples from entities
    //in the database.
    //The Object[] conditionEntityColumnValues are needed to define the condition, which tupels of an entity should be selected
    //based on the condition of values in the attributs(columns) of the database entity.
    public ResultSet executeResultSetInDatabase(String querySelect, Object[] conditionEntityColumnValues) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = this.mySQLDatabaseConnection.prepareStatement(querySelect, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            //separation of empty Strings and int values, which are 0;
            if (conditionEntityColumnValues != null) {
                List<Object> conditionEntityColumnValuesCopy = new ArrayList<>();
                for (int i = 0; i < conditionEntityColumnValues.length; i++) {
                    if (conditionEntityColumnValues[i] instanceof String) {
                        if (!((String) conditionEntityColumnValues[i]).equals("")) {
                            conditionEntityColumnValuesCopy.add(conditionEntityColumnValues[i]);
                        }
                    } else if (conditionEntityColumnValues[i] instanceof Integer) {
                        if ((int) conditionEntityColumnValues[i] != 0) {
                            conditionEntityColumnValuesCopy.add(conditionEntityColumnValues[i]);
                        }
                    } else if (conditionEntityColumnValues[i] instanceof java.sql.Date) {
                        if ((java.sql.Date) conditionEntityColumnValues[i] != null) {
                            conditionEntityColumnValuesCopy.add(conditionEntityColumnValues[i]);
                        }
                    }
                }
                for (int i = 0; i < conditionEntityColumnValuesCopy.size(); i++) {
                    if (conditionEntityColumnValuesCopy.get(i) instanceof String) {
                        if (!((String) conditionEntityColumnValuesCopy.get(i)).equals("")) {
                            statement.setString(i + 1, (String) conditionEntityColumnValuesCopy.get(i));
                        }
                    } else if (conditionEntityColumnValuesCopy.get(i) instanceof Integer) {
                        if ((int) conditionEntityColumnValuesCopy.get(i) != 0) {
                            statement.setInt(i + 1, (Integer) conditionEntityColumnValuesCopy.get(i));
                        }
                    } else if (conditionEntityColumnValuesCopy.get(i) instanceof java.sql.Date) {
                        if ((java.sql.Date) conditionEntityColumnValuesCopy.get(i) != null) {
                            statement.setDate(i + 1, (java.sql.Date) conditionEntityColumnValuesCopy.get(i));
                        }
                    }
                }
            }
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
//                ResultSetMetaData metaData = resultSet.getMetaData();
//                int columnCount = metaData.getColumnCount();
//                for (int i = 1; i <= columnCount; i++) {
//                    System.out.println("Column name: " + metaData.getColumnName(i));
//                }
            resultSet.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
//execute statement in database end---------------------------------------------------------------------------------------------------------------------


    public List<Household> getHouseholdList(ResultSet resultSet) throws Exception {
        List<Household> householdList = new ArrayList<>();
        if (resultSet == null) {
            throw new Exception("\nFehler:\nResultSet ist Null!\nEs wurden keine Haushaltsdaten gefunden!\n");
        }
        while (resultSet.next()) {
            Household household = new Household(resultSet.getInt("HOUSEHOLD_ID"),
                    resultSet.getString("HOUSEHOLD_NAME"));
            householdList.add(household);
        }

        return householdList;
    }

    public Address getAddress(ResultSet resultSet) throws Exception {
        if (resultSet == null) {
            throw new Exception("\nFehler:\nResultSet ist Null!\nEs wurden keine Adressdaten gefunden!\n");
        }
        resultSet.next();
        Address address = new Address(resultSet.getInt("ADDRESS_ID"), resultSet.getString("ZIPCODE"),
                resultSet.getString("CITY"), resultSet.getString("STREET"),
                resultSet.getString("HOUSE_NUMBER"), resultSet.getInt("DOOR_NUMBER"),
                resultSet.getInt("HOUSEHOLD_ID"));
        return address;

    }

    public List<Person> getPersonList(ResultSet resultSet) throws Exception {
        List<Person> personList = new ArrayList<>();
        if (resultSet == null) {
            throw new Exception("\nFehler:\nResultSet ist Null!\nEs wurden keine Personendaten gefunden!\n");
        }
        while (resultSet.next()) {
            Person person = new Person(resultSet.getInt("PERSON_ID"), resultSet.getString("FIRSTNAME"), resultSet.getString("LASTNAME"), resultSet.getDate("BIRTHDATE"), resultSet.getString("GENDER").equals("maennlich") ? Gender.MASKULINE : Gender.FEMININE, resultSet.getInt("HOUSEHOLD_ID"));
            personList.add(person);
        }
        return personList;
    }

    public List<Pet> getPetList(ResultSet resultSet) throws Exception {
        List<Pet> petList = new ArrayList<>();
        if (resultSet == null) {
            throw new Exception("\nFehler:\nResultSet ist Null!\nEs wurden keine Haustierdaten gefunden!\n");
        }
        while (resultSet.next()) {
            Pet pet = new Pet(resultSet.getInt("PET_ID"), resultSet.getString("ANIMAL_KIND"), resultSet.getString("PET_NAME"), resultSet.getInt("PERSON_ID"));
            petList.add(pet);
        }
        return petList;
    }

//    public List<Object> getListWithSpecificObjects(ResultSet resultSet) throws Exception {
//        List<Object> objectsList = new ArrayList<>();
//
//        //Throws Exception if resultSet is null
//        if (resultSet == null) {
//            throw new Exception("\nFehler:\nResultSet ist Null!\nEs wurden keine Daten gefunden!\n");
//        }
//        try {
//            while (resultSet.next()) {
//                switch (this.entity) {
//                    case "HOUSEHOLD":
//                        Household household = new Household(resultSet.getInt("HOUSEHOLD_ID"), resultSet.getString("HOUSEHOLD_NAME"));
//                        objectsList.add(household);
//                        break;
//                    case "ADDRESS":
//                        Address address = new Address(resultSet.getInt("ADDRESS_ID"), resultSet.getString("ZIPCODE"), resultSet.getString("CITY"), resultSet.getString("STREET"), resultSet.getString("HOUSE_NUMBER"), resultSet.getInt("DOOR_NUMBER"), resultSet.getInt("HOUSEHOLD_ID"));
//                        objectsList.add(address);
//                        break;
//
//                    case "PERSON":
//                        Person person = new Person(resultSet.getInt("PERSON_ID"), resultSet.getString("FIRSTNAME"), resultSet.getString("LASTNAME"), resultSet.getDate("BIRTHDATE"), resultSet.getString("GENDER").equals("maennlich") ? Gender.MASKULINE : Gender.FEMININE, resultSet.getInt("HOUSEHOLD_ID"));
//                        objectsList.add(person);
//                        break;
//
//                    case "PET":
//                        Pet pet = new Pet(resultSet.getInt("PET_ID"), resultSet.getString("ANIMAL_KIND"), resultSet.getString("PET_NAME"), resultSet.getInt("PERSON_ID"));
//                        objectsList.add(pet);
//                        break;
//
//                    case "HOUSEHOLD INNER JOIN ADDRESS":
//                        household = new Household(resultSet.getInt("HOUSEHOLD_ID"), resultSet.getString("HOUSEHOLD_NAME"));
//                        address = new Address(resultSet.getInt("ADDRESS_ID"), resultSet.getString("ZIPCODE"), resultSet.getString("CITY"), resultSet.getString("STREET"), resultSet.getString("HOUSE_NUMBER"), resultSet.getInt("DOOR_NUMBER"), resultSet.getInt("HOUSEHOLD_ID"));
//                        objectsList.add(household);
//                        objectsList.add(address);
//                        break;
//
//                    case "HOUSEHOLD INNER JOIN PERSON":
//                        household = new Household(resultSet.getInt("HOUSEHOLD_ID"), resultSet.getString("HOUSEHOLD_NAME"));
//                        person = new Person(resultSet.getInt("PERSON_ID"), resultSet.getString("FIRSTNAME"), resultSet.getString("LASTNAME"), resultSet.getDate("BIRTHDATE"), resultSet.getString("GENDER").equals("maennlich") ? Gender.MASKULINE : Gender.FEMININE, resultSet.getInt("HOUSEHOLD_ID"));
//                        objectsList.add(household);
//                        objectsList.add(person);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return objectsList;
//    }


//create part of query select or delete begin---------------------------------------------------------------------------------------------------------------------

    public String createPartOfQuerySelectOrQueryDelete(StringBuilder stringBuilder, T object) {
        if (object != null) {
            stringBuilder.append(" WHERE ");
            if (object instanceof Household) {
                Household household = (Household) object;
                //TODO: addressId is not needed
                stringBuilder.append(String.format("%s%s", household.getHouseholdId() == 0 ? "" : "HOUSEHOLD_ID = ? AND ", household.getHouseholdName().equals("") ? "" : "HOUSEHOLD_NAME = ? AND "));
                this.objects = new Object[]{household.getHouseholdId(), household.getHouseholdName()};
                this.entity = "HOUSEHOLD";
            } else if (object instanceof Address) {
                Address address = (Address) object;
                stringBuilder.append(String.format("%s%s%s%s%s%s%s", address.getAddressId() == 0 ? "" : "ADDRESS_ID = ? AND ", address.getZipcode().equals("") ? "" : "ZIPCODE = ? AND ", address.getCity().equals("") ? "" : "CITY = ? AND ",
                        address.getStreet().equals("") ? "" : "STREET = ? AND ", address.getHouseNumber().equals("") ? "" : "HOUSE_NUMBER = ? AND ", address.getDoorNumber() == 0 ? "" : "DOOR_NUMBER = ? AND ", address.getHouseholdId() == 0 ? "" : "HOUSEHOLD_ID = ? AND "));
                this.objects = new Object[]{address.getAddressId(), address.getZipcode(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getDoorNumber(), address.getHouseholdId()};
                this.entity = "ADDRESS";
            } else if (object instanceof Person) {
                Person person = (Person) object;
                stringBuilder.append(String.format("%s%s%s%s%s%s", person.getPersonId() == 0 ? "" : "PERSON_ID = ? AND ", person.getFirstName().equals("") ? "" : "FIRSTNAME = ? AND ", person.getLastName().equals("") ? "" : "LASTNAME = ? AND ",
                        person.getBirthdate() == null ? "" : "BIRTHDATE = ? AND ", person.getGender() == null ? "" : "GENDER = ? AND ", person.getHouseholdId() == 0 ? "" : "HOUSEHOLD_ID = ? AND "));
                String gender = person.getGender() == null ? "" : person.getGender().toString();
                this.objects = new Object[]{person.getFirstName(), person.getLastName(), person.getBirthdate(), gender, person.getHouseholdId()};
                this.entity = "PERSON";
            } else if (object instanceof Pet) {
                Pet pet = (Pet) object;
                stringBuilder.append(String.format("%s%s%s%s", pet.getPetId() == 0 ? "" : "PET_ID = ? AND ", pet.getAnimalKind().equals("") ? "" : "ANIMAL_KIND = ? AND ", pet.getPetName().equals("") ? "" : "PET_NAME = ? AND ", pet.getPersonId() == 0 ? "" : "PERSON_ID = ? AND "));
                this.objects = new Object[]{pet.getAnimalKind(), pet.getPetName(), pet.getPersonId()};
                this.entity = "PET";
            }
            stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 5));
        } else if (object == null) {

        }
        stringBuilder.append(";");
        String query = stringBuilder.toString();
        query = query.replace("ENTITY", this.entity);
//        System.out.println(query);
        return query;
    }

    public StringBuilder subStringAdjuster(StringBuilder stringBuilder, int cutAmount) {
        stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - cutAmount));
        return stringBuilder;
    }


//create part of query select or delete end---------------------------------------------------------------------------------------------------------------------


}


