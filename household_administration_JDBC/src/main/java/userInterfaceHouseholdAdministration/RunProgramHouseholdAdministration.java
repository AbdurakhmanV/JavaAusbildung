package userInterfaceHouseholdAdministration;
import databaseManagerHouseholdAdministration.MySQLDatabaseManager;
import databaseManagerHouseholdAdministration.MySQLDatabaseQueryAndExecuteQuery;
import domainLogicHouseholdAdministration.Address;
import domainLogicHouseholdAdministration.Gender;
import domainLogicHouseholdAdministration.Household;
import domainLogicHouseholdAdministration.exceptionManagerHouseholdAdministration.*;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RunProgramHouseholdAdministration {



    //helping objects and variables begin----------------------------------------------------------------------------------------------------------------------------
        private final MySQLDatabaseQueryAndExecuteQuery queryManager = new MySQLDatabaseQueryAndExecuteQuery();
        private final MySQLDatabaseManager databaseManager = new MySQLDatabaseManager();
        private final InputControl inputController = new InputControl();
        private final ExceptionsHouseholdAdministration exceptionManager = new ExceptionsHouseholdAdministration();

        private final Scanner scan = new Scanner(System.in);
    //helping objects and variables end------------------------------------------------------------------------------------------------------------------------------------



    public RunProgramHouseholdAdministration() {

        }

        private int chooseProgram;

        public void startHouseholdAdministration(){
            boolean answer;
            System.out.println("\nZugriff zu Personenadministration?");
            answer = inputController.approval();
            if (!answer) {
                System.out.println("Die Personenadministration wurde beendet.");
            } else {
                do {
                    try {
                        outputAvailablePrograms();
                        if (chooseProgram == 1) {
                            outputHouseholdProgram();
                            switch (chooseProgram) {
                                case 0 -> chooseProgram = -1;
                                case 1 -> addHouseholdIntoDatabase();
                                case 2 -> showAllHouseholds();
                                case 3 -> showAllPersonsInHousehold();
                                case 4 -> deleteHousehold();
                                case 5 -> updateHouseholdData();
                            }
                        }else if (chooseProgram == 2) {
                            outputPersonProgram();
                            switch (chooseProgram) {
                                case 0 -> chooseProgram = -1;
                                case 1 -> addPersonIntoDatabase();
                                case 2 -> searchPerson();
                                case 3 -> deletePerson();
                                case 4 -> updatePersonData();
                            }
                        }else {
                            outputPetProgram();
                            switch (chooseProgram) {
                                case 0 -> chooseProgram = -1;
                                case 1 -> addPetIntoDatabase();
                                case 2 -> searchPet();
                                case 3 -> deletePetFromDatabase();
                                case 4 -> updatePetData();
                            }
                        }
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                } while (chooseProgram != 0) ;


            }
        }




        public void outputAvailablePrograms() {
            do {
                System.out.println("""
                    0. Exit
                    1.Haushalt
                    2.Person
                    3.Haustier
                    """);
                chooseProgram = inputController.controlNumber();
            } while (chooseProgram > 3);
        }

    public void outputHouseholdProgram() {
        do {
            System.out.println("""
                    0. Exit
                    1.Haushalt hinzufuegen
                    2.Alle Haushalte anzeigen
                    3.Alle Personen eines Haushaltes anzeigen
                    4.Haushalt loeschen
                    5.Haushaltsdaten aktualisieren
                    """);
            chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 5);
    }

        public void outputPersonProgram() {
            do {
                System.out.println("""
                    0. Exit
                    1.Person hinzufuegen
                    2.Person suchen 
                    3.Person loeschen
                    4.Personendaten aktualisieren
                    """);
                this.chooseProgram = inputController.controlNumber();
            } while (chooseProgram > 4);
        }

        public void outputPetProgram() {
            do {
                System.out.println("""
                    0. Exit
                    1.Haustier hinzufuegen
                    2.Haustier suchen
                    3.Haustier loeschen
                    4.Haustierdaten aktualisieren
                    """);
                chooseProgram = inputController.controlNumber();
            } while (chooseProgram > 4);
        }

//Household functions begin--------------------------------------------------------------------------------------------------------------------------------------------
    public void addHouseholdIntoDatabase() throws Exception{
        System.out.println("""
                --------Program Haushalt erstellen--------
                Schreib den Namen des Haushaltes in die Konsole rein.""");
        String householdName = this.scan.nextLine();
        int householdId = databaseManager.addHouseholdIntoDatabase(householdName);
        System.out.println("""
                Schreib nun die Adresse des Haushaltes rein.
                Nach dem folgenden Format:""");

        addAddressIntoDatabase(householdId);
        System.out.println("Postleitzahl, Ort, Strasse, Hausnummer, Tuernummer");
        String text = this.scan.nextLine();
    }

    public void addAddressIntoDatabase(int householdId) throws Exception{
//        Scanner scan = new Scanner(System.in);
        System.out.println("Postleitzahl, Ort, Strasse, Hausnummer, Tuernummer");
        String text = this.scan.nextLine();
        text = text.replace(" ", "");
        String[] addressArray = text.split(",");
        addressArray = exceptionManager.controlAddress(addressArray);
        databaseManager.addAddressIntoDatabase(householdId, addressArray);
    }
    public void showAllHouseholds(){
        System.out.println(" --------Program Alle Haushalte anzeigen--------");

    }

    public void showAllPersonsInHousehold(){

    }

    public void deleteHousehold(){

    }

    public void updateHouseholdData(){

    }
    //Household functions end--------------------------------------------------------------------------------------------------------------------------------------------

    //Person functions begin-------------------------------------------------------------------------------------------------------------------------------------
    public void addPersonIntoDatabase() throws Exception{
//            Scanner scan = new Scanner(System.in);

            int chooseProgram;
            String firstDataText;
            String[] personDataArray;

            String firstName;
            String lastName;
            java.sql.Date birthday;
            Gender gender;

            do {
                System.out.println("""
                    0. Exit
                    1. Vorname, Nachname, Geburtsdatum, Geschlecht""");
                chooseProgram = inputController.controlNumber();
            } while (chooseProgram > 1);

            if (chooseProgram == 0) {
                System.out.println("\nZurueck zum Hauptmenue\n");
            } else {
                System.out.println("""
                    Schreib die Daten in dieser Reihenfolge in die Konsole rein.
                    Vorname, Nachname, Geburtsdatum, Geschlecht""");

                firstDataText = this.scan.nextLine();
                firstDataText = firstDataText.replace(",", "");
                personDataArray = firstDataText.split(" ");


                firstName = exceptionManager.controlName(personDataArray[0]);
                lastName = exceptionManager.controlName(personDataArray[1]);
                birthday = exceptionManager.controlBirthdate(personDataArray[2]);

                int numberChooseGender;
                do {
                    System.out.println("""
                        Geschlecht
                        1. Maennlich
                        2. Weiblich""");
                    numberChooseGender = inputController.controlNumber();
                } while (numberChooseGender > 2 || numberChooseGender < 1);

                if (numberChooseGender > 0) {
                    gender = Arrays.stream(Gender.values()).toList().get(numberChooseGender - 1);
                }
                System.out.println("""
                        Zu welchem Haushalt soll die Person hinzugefuegt werden?
                        Schreib die household """);

            }

    }

    public void searchPerson(){

    }

    public void deletePerson(){

    }

    public void updatePersonData(){

    }
    //Person functions end-------------------------------------------------------------------------------------------------------------------------------------

    //Pet functions end-------------------------------------------------------------------------------------------------------------------------------------
    public void addPetIntoDatabase(){

    }

    public void searchPet(){

    }

    public void deletePetFromDatabase(){

    }

    public void updatePetData(){

    }
    //Pet functions end-------------------------------------------------------------------------------------------------------------------------------------

    }

