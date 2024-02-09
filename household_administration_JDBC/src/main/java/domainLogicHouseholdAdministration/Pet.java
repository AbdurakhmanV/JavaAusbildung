package domainLogicHouseholdAdministration;

public class Pet {


    //object attributes, instance variables begin---------------------------------------------------------------------------------------------------------------------
    private int petId;
    private String animalKind;
    private String petName;
    private int personId;

//object attributes, instance variables end---------------------------------------------------------------------------------------------------------------------

    //helping objects and variables begin--------------------------------------------------------------------------------------
    //helping objects and variables end--------------------------------------------------------------------------------------

    //object attributes, instance variables, getter and setter begin---------------------------------------------------------------------------------------
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getAnimalKind() {
        return animalKind;
    }

    public void setAnimalKind(String animalKind) {
        this.animalKind = animalKind;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    //object attributes, instance variables, getter and setter end---------------------------------------------------------------------------------------

    //helping objects and variables, getter ande setter begin--------------------------------------------------------------------------------------
    //helping objects and variables, getter ande setter end--------------------------------------------------------------------------------------

    //constructor begin----------------------------------------------------------------------------------------------------------------------------------------
    public Pet(int petId, String animalKind, String petName, int personId){
        this.petId = petId;
        this.animalKind = animalKind;
        this.petName = petName;
        this.personId = personId;
    }
    //constructor end----------------------------------------------------------------------------------------------------------------------------------------

}
