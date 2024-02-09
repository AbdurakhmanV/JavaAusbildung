package personAdministration;

import java.util.Scanner;

public class Address {
    Scanner scan = new Scanner(System.in);
    private String zipCode;
    private String location;
    private String street;
    private String houseNumber;
    private int doorNumber;


    public void setLocation(String location) {
        this.location = location;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getLocation() {
        return location;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Address(String zipCode, String location, String street, String houseNumber, int doorNumber) {
        this.zipCode = zipCode;
        this.location = location;
        this.street = street;
        this.houseNumber = houseNumber;
        this.doorNumber = doorNumber;
    }

    public Address() {

    }



    @Override
    public String toString() {
        return String.format("%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s", "Postleitzahl: ", zipCode, "Ort: ", location, "Strasse: ", street, "Hausnummer: ", houseNumber, "Tuernummer: ", doorNumber==0?"-----":doorNumber);
    }
}

