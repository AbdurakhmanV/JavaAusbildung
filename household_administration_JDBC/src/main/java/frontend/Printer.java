package frontend;

import domainLogic.Address;
import domainLogic.Household;
import domainLogic.Person;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

public class Printer <T> {
    private HashMap<String, String> hashMapNavigation = new HashMap<>();

    private HashMap<String, String> hashMapSubProgram = new HashMap<>();

    private HashMap<String, String> hashMapHeadLine = new HashMap<>();

    private HashMap<String, String> hashMapInstructionInputObjectId = new HashMap<>();

    private HashMap<String, String> hashMapInstructionInputObjectInformation= new HashMap<>();

    private HashMap<String, String> hashMapValidation = new HashMap<>();



    public void addInformationToAllHashmaps() {
        hashMapNavigation.put("MainNavigation", "\n--------Hauptmenu--------\n\n0.Exit\n1.Haushalt\n2.Person\n3.Haustier\n");
        hashMapNavigation.put("HouseholdNavigation", "\n--------Haushalt - Programm--------\n\n0.Exit\n1.Haushalt hinzufuegen\n" +
                "2.Alle Haushalte anzeigen\n3.Alle Personen eines Haushaltes anzeigen\n4.Alle Tiere eines Haushaltes anzeigen\n" +
                "5.Haushalt loeschen\n6.Haushaltsdaten aktualisieren\n");
        hashMapNavigation.put("PersonNavigation", "\n--------Person - Programm--------\n\n0.Exit\n1.Person hinzufuegen\n" +
                "2.Person suchen \n3.Person loeschen\n4.Personendaten aktualisieren\n");
        hashMapNavigation.put("PetNavigation", "\n--------Haustier - Programm--------\n\n0.Exit\n1.Haustier hinzufuegen\n" +
                "2.Haustier suchen\n3.Haustier loeschen\n4.Haustierdaten aktualisieren\n");


        hashMapSubProgram.put("createHousehold", "\n--------Program Haushalt erstellen--------\n");
        hashMapSubProgram.put("allHouseholds", "\n--------Program Alle Haushalte anzeigen--------\n");
        hashMapSubProgram.put("allPersonsInHousehold", "\n--------Program Alle Personen eines Haushaltes anzeigen--------\n");
        hashMapSubProgram.put("allPetsInHousehold", "\n--------Program Alle Haustiere eines Haushaltes anzeigen--------\n");
        hashMapSubProgram.put("deleteHousehold", "\n--------Program Haushalt loeschen--------\n");
        hashMapSubProgram.put("updateHousehold", "\n--------Program Haushaltsdaten aktualisieren--------\n");
        hashMapSubProgram.put("searchHouseholdOptions", "0.Exit\n1.Durch Haushaltsname suchen\n2.Durch Adresse suchen\n");
        hashMapSubProgram.put("updateHouseholdOptions", "\n0.Exit\n1.Haushaltsname abaendern\n2.Adresse aktualisieren\n");



        hashMapHeadLine.put("Household",String.format("%-30s%-30s", "Haushalt-ID", "Haushaltsname"));
        hashMapHeadLine.put("Address",String.format("%-30s%-30s%-30s%-30s%-30s%-30s",
                "Adresse-ID", "Postleitzahl", "Stadt", "Strasse", "Hausnummer", "Tuernummer"));
        hashMapHeadLine.put("Person",String.format("%-30s%-30s%-30s%-30s%-30s%-30s",
                "Person-ID", "Vorname", "Nachname", "Geburtsdatum", "Geschlecht", "Haushalt-ID"));
        hashMapHeadLine.put("Pet",String.format("%-30s%-30s%-30s%-30s",
                "Haustier-ID", "Tierart", "Name", "Person-ID"));



        hashMapInstructionInputObjectId.put("Household", "\nSchreib die Haushalt-ID des Haushaltes , der ausgewaehlt werden soll, rein.\n");
        hashMapInstructionInputObjectId.put("Address", "\nSchreib die Adresse-ID der Adresse , die ausgewaehlt werden soll, rein.\n");

        hashMapInstructionInputObjectInformation.put("Household", "\nSchreib den Namen des Haushaltes in die Konsole rein.\n");
        hashMapInstructionInputObjectInformation.put("Address", "\nSchreib nun die Adresse rein." +
                "\nBefolge dabei das nachfolgende Format:" +
                "\nPostleitzahl, Stadt, Strasse, Hausnummer, Tuernummer\n");

        hashMapValidation.put("addHousehold", "\n---Haushalt erfolgreich hinzugefueght---\n");
        hashMapValidation.put("deleteHousehold", "\n---Haushalt erfolgreich entfernt---\n");
        hashMapValidation.put("updateHousehold", "\n---Haushaltsdaten erfolgreich aktualisiert---\n");

    }



    public void printoutList(List<T> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
   public void printoutHouseholdsWithAddressFromLists(List<Household> householdList, List<Address> addressList){
       for (int i = 0; i < householdList.size(); i++) {
           System.out.println(householdList.get(i).toString() + addressList.get(i).toString());
       }
   }


    public HashMap<String, String> getHashMapNavigation() {
        return hashMapNavigation;
    }

    public HashMap<String, String> getHashMapSubProgram() {
        return hashMapSubProgram;
    }

    public HashMap<String, String> getHashMapHeadLine() {
        return hashMapHeadLine;
    }

    public HashMap<String, String> getHashMapInstructionInputObjectId() {
        return hashMapInstructionInputObjectId;
    }

    public HashMap<String, String> getHashMapInstructionInputObjectInformation() {
        return hashMapInstructionInputObjectInformation;
    }

    public HashMap<String, String> getHashMapValidation() {
        return hashMapValidation;
    }

}
