package domainLogicHouseholdAdministration;

public class Address {
    //object attributes, instance variables begin---------------------------------------------------------------------------------------------------------------------
    private int addressId;
    private String zipcode;
    private String city;
    private String street;
    private String houseNumber;
    private int doorNumber;
    private int householdId;
    //object attributes, instance variables end---------------------------------------------------------------------------------------------------------------------

    //helping objects and variables begin--------------------------------------------------------------------------------------
    //helping objects and variables end--------------------------------------------------------------------------------------

    //object attributes, instance variables, getter and setter begin---------------------------------------------------------------------------------------
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }

    public int getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
    }
    //object attributes, instance variables, getter and setter end---------------------------------------------------------------------------------------

    //helping objects and variables, getter ande setter begin--------------------------------------------------------------------------------------
    //helping objects and variables, getter ande setter end--------------------------------------------------------------------------------------

    //constructor begin----------------------------------------------------------------------------------------------------------------------------------------
    public Address (int addressId, String zipcode, String city, String street, String houseNumber, int doorNumber, int householdId){
        this.addressId = addressId;
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.doorNumber = doorNumber;
        this.householdId = householdId;
    }
    //constructor end----------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public String toString(){
        return String.format("%-30d%-30s%-30s%-30s%-30s%-30d%", addressId, zipcode, city, street, houseNumber, doorNumber);
    }




}
