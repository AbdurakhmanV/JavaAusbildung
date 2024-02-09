package databaseManagerHouseholdAdministration;

import domainLogicHouseholdAdministration.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDatabaseQueryAndExecuteQuery {

    //helping objects and variables begin--------------------------------------------------------------------------------------
    private static Connection mySQLDatabaseConnection;
    private static Object[] objects;
    //helping objects and variables end--------------------------------------------------------------------------------------


    //helping objects and variables, getter ande setter begin--------------------------------------------------------------------------------------


    //helping objects and variables, getter ande setter end--------------------------------------------------------------------------------------


    //constructor begin------------------------------------------------------------------------------------------------------------------------
    public MySQLDatabaseQueryAndExecuteQuery() {

    }

    public MySQLDatabaseQueryAndExecuteQuery(Connection mySQLDatabaseConnection) {
        this.mySQLDatabaseConnection = mySQLDatabaseConnection;
    }

    //constructor end------------------------------------------------------------------------------------------------------------------------

    public ResultSet queryInsert(String entity, Object object) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ").append(entity);
        switch (entity) {
            case "HOUSEHOLD" -> {
                stringBuilder.append(" (HOUSEHOLD_NAME) VALUES(?);");
                Household household = (Household) object;
                this.objects = new Object[]{household.getHouseholdName()};
            }
            case "ADDRESS" -> {
                Address address = (Address) object;
                stringBuilder.append(" (ZIPCODE, CITY, STREET, HOUSE_NUMBER, DOOR_NUMBER, HOUSEHOLD_ID) VALUES(?, ?, ?, ?, ?, ?);");//TODO printf: adjust to doorNumber == 0
                this.objects = new Object[]{address.getZipcode(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getDoorNumber(), address.getHouseholdId()};
            }
            case "PERSON" -> {
                stringBuilder.append(" (FIRSTNAME, LASTNAME, BIRTHDATE, GENDER, HOUSEHOLD_ID) VALUES(?, ?, ?, ?, ?);");
                Person person = (Person) object;
                String gender = person.getGender().toString();
                this.objects = new Object[]{person.getFirstName(), person.getLastName(), person.getBirthdate(), gender, person.getHouseholdId()};
            }
            case "PET" -> {
                stringBuilder.append(" (ANIMAL_KIND, PET_NAME, PERSON_ID) VALUES(?, ?, ?);");
                Pet pet = (Pet) object;
                this.objects = new Object[]{pet.getAnimalKind(), pet.getPetName(), pet.getPersonId()};
            }
        }
        String queryInsert = stringBuilder.toString();
        System.out.println(queryInsert);
        return(executeQueryInDatabase(queryInsert, this.objects));

    }

    public ResultSet querySelect(String entity, Object object) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ").append(entity);
        if(object != null) {
            stringBuilder.append(" WHERE ");
            if (entity.equals("HOUSEHOLD")) {
                Household houseHold = (Household) object;
                stringBuilder.append(String.format("%s%s%s", houseHold.getHouseholdId() == 0 ? "" : "HOUSEHOLD_ID = ? AND ", houseHold.getHouseholdName().equals("") ? "" : "HOUSEHOLD_NAME = ? AND ", houseHold.getAddressId() == 0 ? "" : "ADDRESS_ID = ? AND "));
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 5));
                this.objects = new Object[]{houseHold.getHouseholdId(), houseHold.getHouseholdName(), houseHold.getAddressId()};
            } else if (entity.equals("ADDRESS")) {
                Address address = (Address) object;
                stringBuilder.append(String.format("%s%s%s%s%s%s%s", address.getAddressId() == 0 ? "" : "ADDRESS_ID = ? AND ", address.getZipcode().equals("") ? "" : "ZIPCODE = ? AND ", address.getCity().equals("") ? "" : "CITY = ? AND ", address.getHouseNumber().equals("") ? "" : "HOUSE_NUMBER = ? AND ", address.getDoorNumber() == 0 ? "" : "DOOR_NUMBER = ? AND ", address.getHouseholdId() == 0 ? "" : "HOUSEHOLD_ID = ? AND "));
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 5));
                this.objects = new Object[]{address.getAddressId(), address.getZipcode(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getDoorNumber(), address.getHouseholdId()};
            } else if (entity.equals("PERSON")) {
                Person person = (Person) object;
                stringBuilder.append(String.format("%s%s%s%s%s%s", person.getPersonId() == 0 ? "" : "PERSON_ID = ? AND ", person.getFirstName().equals("") ? "" : "FIRSTNAME = ? AND ", person.getLastName().equals("") ? "" : "LASTNAME = ? AND ", person.getBirthdate() == null ? "" : "BIRTHDATE = ? AND ", person.getGender() == null ? "" : "GENDER = ? AND ", person.getHouseholdId() == 0 ? "" : "HOUSEHOLD_ID = ? AND "));
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 5));
                this.objects = new Object[]{person.getPersonId(), person.getFirstName(), person.getLastName(), person.getBirthdate(), person.getGender().toString(), person.getHouseholdId()};
            } else if (entity.equals("PET")) {
                Pet pet = (Pet) object;
                stringBuilder.append(String.format("%s%s%s%s", pet.getPetId() == 0 ? "" : "PET_ID = ? AND ", pet.getAnimalKind().equals("") ? "" : "ANIMAL_KIND = ? AND ", pet.getPetName().equals("") ? "" : "PET_NAME = ? AND "));
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 5));
                this.objects = new Object[]{pet.getPetId(), pet.getAnimalKind(), pet.getPetName(), pet.getPersonId()};
            }
        }
        stringBuilder.append(";");
        String querySelect = stringBuilder.toString();
        System.out.println(querySelect);
        ResultSet resultSet = executeResultSetInDatabase(querySelect, this.objects);
        return resultSet;

    }


    public ResultSet executeQueryInDatabase(String query, Object[] conditionEntityColumnValues) {
        try {
            PreparedStatement statement = this.mySQLDatabaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < conditionEntityColumnValues.length; i++) {
                if (conditionEntityColumnValues[i] instanceof String) {
                    if (!((String) conditionEntityColumnValues[i]).equals("")) {
                        statement.setString(i + 1, (String) conditionEntityColumnValues[i]);
                    }
                } else if (conditionEntityColumnValues[i] instanceof Integer) {
                    if ((int) conditionEntityColumnValues[i] != 0){
                        statement.setInt(i + 1, (Integer) conditionEntityColumnValues[i]);
                    }
                } else if (conditionEntityColumnValues[i] instanceof java.sql.Date) {
                    if(conditionEntityColumnValues[i] != null) {
                        statement.setDate(i + 1, (java.sql.Date) conditionEntityColumnValues[i]);
                    }
                }
            }
            statement.execute();
            System.out.println("--------executeQuery executed!--------");
            ResultSet resultSet = statement.getGeneratedKeys();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet executeResultSetInDatabase(String querySelect, Object[] conditionEntityColumnValues) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = this.mySQLDatabaseConnection.prepareStatement(querySelect, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            if (conditionEntityColumnValues != null) {
                List<Object> conditionEntityColumnValuesCopy = new ArrayList<>();
                for (int i = 0; i <conditionEntityColumnValues.length ; i++) {
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

    public List<Object> getRequiredData(ResultSet resultSet, String entity) {
        List<Object> objectsList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                switch (entity) {
                    case "HOUSEHOLD":
                        Household household = new Household(resultSet.getInt("HOUSEHOLD_ID"), resultSet.getString("HOUSEHOLD_NAME"));
                        objectsList.add(household);
                        break;
                    case "ADDRESS":
                        Address address = new Address(resultSet.getInt("ADDRESS_ID"), resultSet.getString("ZIPCODE"), resultSet.getString("CITY"), resultSet.getString("STREET"), resultSet.getString("HOUSE_NUMBER"), resultSet.getInt("DOOR_NUMBER"), resultSet.getInt("HOUSEHOLD_ID"));
                        objectsList.add(address);
                        break;

                    case "PERSON":
                        Person person = new Person(resultSet.getInt("PERSON_ID"), resultSet.getString("FIRSTNAME"), resultSet.getString("LASTNAME"), resultSet.getDate("BIRTHDATE"), resultSet.getString("GENDER").equals("maennlich") ? Gender.MASKULINE : Gender.FEMININE, resultSet.getInt("HOUSEHOLD_ID"));
                        objectsList.add(person);
                        break;

                    case "PET":
                        Pet pet = new Pet(resultSet.getInt("PET_ID"), resultSet.getString("ANIMAL_KIND"), resultSet.getString("PET_NAME"), resultSet.getInt("PERSON_ID"));
                        objectsList.add(pet);
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectsList;
    }
}






