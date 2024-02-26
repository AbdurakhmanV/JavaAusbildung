package domainLogic;
import domainLogic.exceptionManager.*;

import java.sql.Date;

public class Person {

    //object attributes, instance variables begin---------------------------------------------------------------------------------------------------------------------
    private int personId;
    private String firstName;
    private String lastName;
    private java.sql.Date birthdate;
    private Gender gender;
    private int householdId;
    //object attributes, instance variables end-------------------------------------------------------------------------------------------------------------------

    //helping variables and objects begin-----------------------------------------------------------------------------------------------------------------------
    private final ExceptionsHouseholdAdministration exceptionsHouseholdAdministrationManager = new ExceptionsHouseholdAdministration();

    //helping variables and objects end--------------------------------------------------------------------------------------------------------------------------


    //object attributes, instance variables, getter and setter begin---------------------------------------------------------------------------------------
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
    }
    //object attributes, instance variables, getter and setter end---------------------------------------------------------------------------------------



//constructor begin----------------------------------------------------------------------------------------------------------------------------------------
    public Person(int personId, String firstName, String lastName, java.sql.Date birthdate, Gender gender, int householdId){
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.householdId = householdId;
    }
//constructor end----------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public String toString(){
        return String.format("%-30s%-30s%-30s%-30s%-30s%-30s", personId==0?"----":personId, firstName, lastName, birthdate, gender, householdId==0?"----":householdId);
    }



    }

