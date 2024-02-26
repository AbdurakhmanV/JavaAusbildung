package frontend;

import databaseManager.DatabaseConnection;
import databaseManager.MySQLDatabaseManager;
import domainLogic.*;
import domainLogic.DAO.*;
import domainLogic.exceptionManager.*;
import domainLogic.modelInterfaces.AddressImpl;
import domainLogic.modelInterfaces.HouseholdImpl;
import domainLogic.modelInterfaces.IAddressInterface;
import domainLogic.modelInterfaces.IHouseholdInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//This class "GraphicalUserInterface" will interact with the user. It is managing the input of the user and output of feedback information.
//With the final object "databaseManager" it transfers the data to the class  "MySQLDatabaseManager"
public class GraphicalUserInterface {


    //helping objects and variables begin----------------------------------------------------------------------------------------------------------------------------
    private final Scanner scan = new Scanner(System.in);

    MySQLDatabaseManager databaseManager = new MySQLDatabaseManager();

    private Printer printer = new Printer();

    private final InputControl inputController = new InputControl();
    private final ExceptionsHouseholdAdministration exceptionManager = new ExceptionsHouseholdAdministration();

//The model-interface objects(addressInterface, householdInterface) are needed to provide an interface with the domainLogic
//in order to create objects(models: Household, Address, Person, Pet)
    private final IAddressInterface addressInterface = new AddressImpl();
    private final IHouseholdInterface householdInterface = new HouseholdImpl();

//The database-interface objects(householdDatabaseManager, addressDatabaseManager, personDatabaseManager, petDatabaseManager)
//provide an interface with the databaseManager in order to declare sql statements(queries) and execute queries.
    private final IHouseholdDAO householdDatabaseManager = new HouseholdDAOImpl();
    private final IAddressDAO addressDatabaseManager = new AddressDAOImpl();
    private final IPersonDAO personDatabaseManager = new PersonDAOImpl();
    private final IPetDAO petDatabaseManager = new PetDAOImpl();

    //helping objects and variables end------------------------------------------------------------------------------------------------------------------------------------


    public GraphicalUserInterface() {

    }

    private int chooseProgram;

    public void startHouseholdAdministration() {
        databaseManager.startDatabaseManager();
        printer.addInformationToAllHashmaps();

        do {
            try {
                guiAvailablePrograms();
                switch (chooseProgram) {
                    case 1:
                        do {
                            guiHouseholdProgram();
                        } while (chooseProgram == -2);
                        break;
                    case 2:
                        do {
                            guiPersonProgram();
                        } while (chooseProgram == -2);
                        break;
                    case 3:
                        do {
                            guiPetProgram();
                        } while (chooseProgram == -2);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } while (chooseProgram != 0);

    }


    public void guiAvailablePrograms() {
        do {
            System.out.println(printer.getHashMapNavigation().get("MainNavigation"));
            chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 3);
    }

    public void guiHouseholdProgram() throws Exception {
        do {
            System.out.println(printer.getHashMapNavigation().get("HouseholdNavigation"));
            chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 6);

        switch (chooseProgram) {
            case 0 -> chooseProgram = -1;
            case 1 -> guiCreateAndAddHouseholdIntoDatabase();
            case 2 -> printOutAllHouseholdsWhitAddress();
            case 3 -> printoutAllPersonsInHousehold();
            case 4 -> printoutAllPetsInHouseholdProgram();
            case 5 -> guiDeleteHouseholdProgram();
            case 6 -> guiUpdateHousehold();
        }
    }

    public void guiPersonProgram() {
        do {
            System.out.println(printer.getHashMapNavigation().get("PersonNavigation"));
            this.chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 4);
        switch (chooseProgram) {
            case 0 -> chooseProgram = -1;
            case 1 -> addPersonIntoDatabase();
            case 2 -> searchPerson();
            case 3 -> deletePerson();
            case 4 -> updatePersonData();
        }
    }

    public void guiPetProgram() {
        do {
            System.out.println(printer.getHashMapNavigation().get("PetNavigation"));
            chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 4);
        switch (chooseProgram) {
            case 0 -> chooseProgram = -1;
            case 1 -> addPetIntoDatabase();
            case 2 -> searchPet();
            case 3 -> deletePetFromDatabase();
            case 4 -> updatePetData();
        }
    }

    public void guiCreateAndAddHouseholdIntoDatabase() throws Exception {

        System.out.println(printer.getHashMapSubProgram().get("createHousehold"));
        System.out.println(printer.getHashMapInstructionInputObjectInformation().get("Household"));

        String householdName = this.scan.nextLine();

        System.out.println(printer.getHashMapInstructionInputObjectInformation().get("Address"));

        String textAddress = ("0, " + this.scan.nextLine() + ", 0");

        addressDatabaseManager.checkIfAddressExistsInDatabase(addressInterface.createObjectByUsersTextInput(textAddress));
        Household household = householdInterface.createObjectByUsersTextInput(householdName);

        int householdId = householdDatabaseManager.addObjectIntoEntity(household);
        textAddress = textAddress.substring(0,textAddress.length()-1);
        textAddress = textAddress + householdId;
        addressDatabaseManager.addObjectIntoEntity(addressInterface.createObjectByUsersTextInput(textAddress));

        System.out.println(printer.getHashMapValidation().get("Household"));
    }



    public void printOutAllHouseholdsWhitAddress() throws Exception {
        System.out.println(printer.getHashMapSubProgram().get("allHouseholds"));

        System.out.print(printer.getHashMapHeadLine().get("Household"));
        System.out.print(printer.getHashMapHeadLine().get("Address"));
        System.out.println();

        List<Household> householdList = householdDatabaseManager.getAllHouseholdsFromDatabase();
        List<Address> addressList = new ArrayList<>();

        for (Household household : householdList) {
            addressList.add(addressDatabaseManager.getAddressByHouseholdIdFromDatabase(household.getHouseholdId()));
        }

        printer.printoutHouseholdsWithAddressFromLists(householdList, addressList);

    }


    public void printoutAllPersonsInHousehold() throws Exception{
        System.out.println(printer.getHashMapSubProgram().get("allPersonsInHousehold"));

        int householdId = guiSearchHouseholdProgram();

        if (chooseProgram != -2) {
           List<Person> personList = personDatabaseManager.getAllPersonsFromHousehold(householdId);

            System.out.print(printer.getHashMapHeadLine().get("Person"));
           System.out.println();
           printer.printoutList(personList);
        }
    }

    ;

    public int guiSearchHouseholdProgram() throws Exception {
        List<Household> householdList;

        do {
            System.out.println(printer.getHashMapSubProgram().get("searchHouseholdOptions"));

            chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 2);
        if (chooseProgram == 0) {
            chooseProgram = -2;
        } else if (chooseProgram == 1) {
            System.out.println(printer.getHashMapInstructionInputObjectInformation().get("Household"));

            String householdName = scan.nextLine();

            householdList = householdDatabaseManager.getHouseholdByHouseholdName(householdName);
            List<Address> addressList = new ArrayList<>();

            for (Household household : householdList) {
                addressList.add(addressDatabaseManager.getAddressByHouseholdIdFromDatabase(household.getHouseholdId()));
            }

            System.out.print(printer.getHashMapHeadLine().get("Household"));
            System.out.print(printer.getHashMapHeadLine().get("Address"));
            System.out.println();

            printer.printoutHouseholdsWithAddressFromLists(householdList, addressList);
            System.out.println(printer.getHashMapInstructionInputObjectId().get("Household"));

            return inputController.controlNumber();

        } else if (chooseProgram == 2) {
            System.out.println(printer.getHashMapInstructionInputObjectInformation().get("Address"));

            String textAddress = ("0, " + this.scan.nextLine() + ", 0");

            Address address = addressDatabaseManager.getAddressFromDatabase(addressInterface.createObjectByUsersTextInput(textAddress));
            householdList = householdDatabaseManager.getHouseholdByHouseholdAddress(address);

            System.out.print(printer.getHashMapHeadLine().get("Household"));
            System.out.println();
            printer.printoutList(householdList);

            return householdList.get(0).getHouseholdId();
        }
        return -1;
    }

    public void printoutAllPetsInHouseholdProgram() throws Exception {
        System.out.println(printer.getHashMapSubProgram().get("allPetsInHousehold"));


        int householdId = guiSearchHouseholdProgram();

        if (chooseProgram != -2) {
            List<Person> personList = personDatabaseManager.getAllPersonsFromHousehold(householdId);
            List<Pet> petList = new ArrayList<>();
            for (Person person: personList) {
                List<Pet> petListFromOnePerson = petDatabaseManager.getPetsByPersonId(person.getPersonId());
                for (Pet pet: petListFromOnePerson) {
                    petList.add(pet);
                }
            }

            System.out.print(printer.getHashMapHeadLine().get("Pet"));
            System.out.println();
            printer.printoutList(petList);
        }
    }

    public void guiDeleteHouseholdProgram() throws Exception{
        System.out.println(printer.getHashMapSubProgram().get("deleteHousehold"));

        int householdId = guiSearchHouseholdProgram();

        if (chooseProgram != -2) {
            householdDatabaseManager.deleteObjectFromDatabase(householdId);

            System.out.println(printer.getHashMapValidation().get("deleteHousehold"));
        }
    }

    public void guiUpdateHousehold() throws Exception{
        System.out.println(printer.getHashMapSubProgram().get("updateHousehold"));

        int householdId = guiSearchHouseholdProgram();

        if (chooseProgram != -2) {
            do {
                System.out.println(printer.getHashMapSubProgram().get("updateHouseholdOptions"));

                chooseProgram = inputController.controlNumber();
            } while (chooseProgram > 2);
            if (chooseProgram == 0) {
                chooseProgram = -2;
            }else if(chooseProgram==1){
                System.out.println(printer.getHashMapInstructionInputObjectInformation().get("Household"));

                String householdName = scan.nextLine();
                Household household = householdDatabaseManager.getHouseholdByHouseholdId(householdId);
                householdDatabaseManager.updateHouseholdName(household, householdName);
            }else if(chooseProgram==2){
                Address address = addressDatabaseManager.getAddressByHouseholdIdFromDatabase(householdId);
                System.out.println(printer.getHashMapInstructionInputObjectInformation().get("Address"));

                String textAddress = ("0, " + this.scan.nextLine() + ", 0");

                addressDatabaseManager.checkIfAddressExistsInDatabase(addressInterface.createObjectByUsersTextInput(textAddress));
                Address newAddress = addressInterface.createObjectByUsersTextInput(textAddress);
                addressDatabaseManager.updateAddress(address, newAddress);
            }

            System.out.println(printer.getHashMapValidation().get("updateHousehold"));
        }
    }


//  --------------------------------------------------------------------------------------------------------
//  --------------------------------------------------------------------------------------------------------
//  --------------------------------------------------------------------------------------------------------


//    public void showAllPetsInHouseholdProgram() throws Exception {
//        System.out.println("\n--------Program Alle Personen eines Haushaltes anzeigen--------\n");
//        searchHouseholdProgram();
//
//        if (chooseProgram != -2) {
//            interfaceFrontendAndDatabase.showAllPetsInHousehold();
//        }
//    }
//
//    public void deleteHouseholdProgram() throws Exception {
//        System.out.println("\n--------Program Haushalt loeschen--------\n");
//        searchHouseholdProgram();
//        if (chooseProgram != -2) {
//            interfaceFrontendAndDatabase.deleteHousehold();
//        }
//
//    }
//
//
//    public void updateHouseholdDataProgram() throws Exception {
//        System.out.println("\n--------Program Haushalt aktualisieren--------\n");
//        searchHouseholdProgram();
//        if (chooseProgram != -2) {
//            int householdId = interfaceFrontendAndDatabase.declaredHouseholdId();
//            decideHouseholdNameOrHouseholdAddress(householdId);
//        }
//    }
//
//    public void decideHouseholdNameOrHouseholdAddress(int householdId) throws Exception {
//        do {
//            System.out.println("""
//                    0.Exit
//                    1.Haushaltsname aendern
//                    2.Adresse aendern\n""");
//            chooseProgram = inputController.controlNumber();
//        } while (chooseProgram > 2);
//        if (chooseProgram == 0) {
//            chooseProgram = -2;
//        } else if (chooseProgram == 1) {
//            interfaceFrontendAndDatabase.updateHouseholdName(householdId);
//        } else if (chooseProgram == 2) {
//            interfaceFrontendAndDatabase.updateHouseholdAddress(householdId);
//        }
//    }
//
//    public void guiSearchHouseholdOptions() throws Exception {
//        do {
//            System.out.println("""
//                    0.Exit
//                    1.Durch Haushaltsname suchen
//                    2.Durch Adresse suchen\n""");
//            chooseProgram = inputController.controlNumber();
//        } while (chooseProgram > 2);
//        if (chooseProgram == 0) {
//            chooseProgram = -2;
//        } else if (chooseProgram == 1) {
//            interfaceFrontendAndDatabase.searchHouseholdByHouseHoldName();
//        } else if (chooseProgram == 2) {
//            interfaceFrontendAndDatabase.searchHouseholdByHouseholdAddress();
//        }
//    }
//    //Household functions end--------------------------------------------------------------------------------------------------------------------------------------------
//
//    //Person functions begin-------------------------------------------------------------------------------------------------------------------------------------
//    public void addPersonIntoDatabase() throws Exception {
//
//
//        int chooseProgram;
//        String firstDataText;
//        String[] personDataArray;
//
//        String firstName;
//        String lastName;
//        java.sql.Date birthday;
//        Gender gender;
//
//        do {
//            System.out.println("""
//                    0. Exit
//                    1. Vorname, Nachname, Geburtsdatum, Geschlecht""");
//            chooseProgram = inputController.controlNumber();
//        } while (chooseProgram > 1);
//
//        if (chooseProgram == 0) {
//            System.out.println("\nZurueck zum Hauptmenue\n");
//        } else {
//            System.out.println("""
//                    Schreib die Daten in dieser Reihenfolge in die Konsole rein.
//                    Vorname, Nachname, Geburtsdatum""");
//
//            firstDataText = this.scan.nextLine();
//            firstDataText = firstDataText.replace(",", "");
//            personDataArray = firstDataText.split(" ");
//
//            firstName = exceptionManager.controlName(personDataArray[0]);
//            lastName = exceptionManager.controlName(personDataArray[1]);
//            birthday = exceptionManager.controlBirthdate(personDataArray[2]);
//
//            int numberChooseGender;
//            do {
//                System.out.println("""
//                        Geschlecht
//                        1. Maennlich
//                        2. Weiblich""");
//                numberChooseGender = inputController.controlNumber();
//            } while (numberChooseGender > 2 || numberChooseGender < 1);
//
//            if (numberChooseGender > 0) {
//                gender = Arrays.stream(Gender.values()).toList().get(numberChooseGender - 1);
//            }
//            System.out.println("""
//                    Zu welchem Haushalt soll die Person hinzugefuegt werden?
//                    Schreib die household """);
//
//        }
//
//    }

    public void addPersonIntoDatabase() {

    }

    public void searchPerson() {

    }

    public void deletePerson() {

    }

    public void updatePersonData() {

    }
    //Person functions end-------------------------------------------------------------------------------------------------------------------------------------

    //Pet functions end-------------------------------------------------------------------------------------------------------------------------------------
    public void addPetIntoDatabase() {

    }

    public void searchPet() {

    }

    public void deletePetFromDatabase() {

    }

    public void updatePetData() {

    }
    //Pet functions end-------------------------------------------------------------------------------------------------------------------------------------

}

