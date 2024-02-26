package personAdministration;
import exceptionsAndControlPersonAdministration.*;

import java.util.Arrays;
import java.util.Scanner;

public class RunPersonAdministration {

private final ExceptionPersonAdministration exceptionManager =new ExceptionPersonAdministration();
private final InputControl inputController = new InputControl();
private static int chooseProgram;
private Scanner scan = new Scanner(System.in);



    public Person createPerson() throws Exception {
        String firstDataText;
        String[] personDataArray;
        String firstName = "";
        String lastName = "";
        String birthdate = "";
        Gender gender;
        Address address;

        do {
            System.out.println("""
                    0. Exit
                    1. Vorname, Nachname, Geburtsdatum""");
            chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 1);

        if (chooseProgram == 0) {
            System.out.println("\nZurueck zum Hauptmenue\n");
            return null;
        } else {
            System.out.println("""
                    Schreib die Daten in dieser Reihenfolge in die Konsole rein.
                    Vorname, Nachname, Geburtsdatum, Geschlecht""");

            firstDataText = this.scan.nextLine();
            firstDataText = firstDataText.replace(",", "");
            personDataArray = firstDataText.split(" ");

            firstName = exceptionManager.controlName(personDataArray[0]);
            lastName = exceptionManager.controlName(personDataArray[1]);
            birthdate = exceptionManager.controlBirthdate(personDataArray[2]);

            do {
                System.out.println("""
                        Geschlecht
                        0. Keine Angabe
                        1. Maennlich
                        2. Weiblich""");
                chooseProgram = inputController.controlNumber();
            } while (chooseProgram > 2);

            if (chooseProgram > 0) {
                gender = Arrays.stream(Gender.values()).toList().get(chooseProgram - 1);
            }else{
                gender = null;
            }

            do{
                System.out.println("""
                        Adresse
                        0.Keine Angaben
                        1.Adresse hinzufuegen\n""");
                chooseProgram = inputController.controlNumber();
            }while (chooseProgram > 1);
            if(chooseProgram==1) {
                address = createAddress();
            }else{
                address = null;
            }
        }
        return new Person(firstName, lastName, birthdate, gender, address);

    }

    public Address createAddress() throws Exception{
        System.out.println("Schreib nun die Adresse rein");
        System.out.println("Postleitzahl, Ort, Strasse, Hausnummer, Tuernummer");
        String text = this.scan.nextLine();
        text = text.replace(" ", "");
        String[] addressArray = text.split(",");

        addressArray = exceptionManager.controlAddress(addressArray);

        String zipCode = addressArray[0];
        String city = addressArray[1];
        String street = addressArray[2];
        String houseNumber = addressArray[3];
        int doorNumber;
        if (addressArray.length == 4) {
            doorNumber = 0;
        } else {
            doorNumber = Integer.parseInt(addressArray[4]);
        }
        return new Address(zipCode, city, street, houseNumber, doorNumber);
    }
}
