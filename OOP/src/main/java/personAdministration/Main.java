package personAdministration;

import exceptionsAndControlPersonAdministration.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static ExceptionPersonAdministration exceptionManager = new ExceptionPersonAdministration();
    private static InputControl inputController = new InputControl();
    private static int chooseProgram;
    private static RunPersonAdministration runPersonAdministration = new RunPersonAdministration();

    private static List<Person> listPaLinz = new ArrayList<>();
    private static List<Person> listPaCodersBay = new ArrayList<>();
    private static List<Person> listPaMagistrateVienna = new ArrayList<>();
    private static Person createdPerson;


    public static void main(String[] args) {
        try {
            do {
                showFunctionsOfPersonAdministration();

                if (chooseProgram == 1) {
                    createdPerson = runPersonAdministration.createPerson();
                    if (createdPerson != null) {
                        createdPerson.toString();
                        showAddToListFunctions();
                        addPersonToList();
                    }
                } else if (chooseProgram == 2) {
                    showListPa(listPaLinz, listPaCodersBay, listPaMagistrateVienna);
                } else if (chooseProgram == 3) {
                    showAddToListFunctions();
                    removePersonFromList();
                } else if (chooseProgram == 4) {
                    System.out.println("Schreib den Vornamen der person rein.");
                    String firstName = scan.next();
                    System.out.println("Schreib den Nachnamen der person rein.");
                    String lastName = scan.next();
                    try {
                        Person person = searchPerson(listPaLinz, listPaCodersBay, listPaMagistrateVienna, firstName, lastName);
                        System.out.println("Die Person wurde gefunden.\n" + person);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
            } while (chooseProgram != 0);
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            System.out.println();
            System.out.println(e.getMessage());
        }

    }

    public static void showFunctionsOfPersonAdministration() {
        do {
            System.out.println("""
                    0. Exit
                    1. Person hinzufuegen
                    2. List anzeigen
                    3. Person entfernen
                    4. Person suchen""");
            chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 4);
    }

    public static void showAddToListFunctions() {
        System.out.println("Zu welche Liste soll die Person hinzugefueght werden?");
        do {
            System.out.println("""
                    Zur Liste hinzufügen
                    0.Exit
                    1.Linz
                    2.Codersbay
                    3.Magistrat Wien""");
            chooseProgram = inputController.controlNumber();
        } while (chooseProgram > 3);
    }

    public static void addPersonToList(){
        if (chooseProgram == 1) {
            listPaLinz = addToListPa(listPaLinz, createdPerson);
        } else if (chooseProgram == 2) {
            listPaCodersBay = addToListPa(listPaCodersBay, createdPerson);
        } else if (chooseProgram == 3) {
            listPaCodersBay = addToListPa(listPaMagistrateVienna, createdPerson);
        }
        if (chooseProgram == 0) {
            chooseProgram = -1;
        }
    }

    public static void removePersonFromList(){
        if (chooseProgram == 1) {
            listPaLinz = removeFromListPA(listPaLinz);
        } else if (chooseProgram == 2) {
            listPaCodersBay = removeFromListPA(listPaCodersBay);
        } else if (chooseProgram == 3) {
            listPaCodersBay = removeFromListPA(listPaMagistrateVienna);
        }
        if (chooseProgram == 0) {
            chooseProgram = -1;
        }
    }

    public static List<Person> addToListPa(List<Person> list, Person person) {
        list.add(person);
        return list;
    }

    public static void showListPa(List<Person> listPaLinz, List<Person> listPaCodersBay, List<Person> listPaMagistrateVienna) {
        Scanner scan = new Scanner(System.in);
        int chooseProgram;
        System.out.println("""
                0.Exit 
                1.Linz
                2.Codersbay
                3.Magistrat Wien""");

        chooseProgram = scan.nextInt();
        if (chooseProgram == 0) {
            System.out.println();
        }
        if (chooseProgram == 1) {
            for (Person listCopy : listPaLinz) {
                System.out.println(listCopy);
            }
        } else if (chooseProgram == 2) {
            for (Person listCopy : listPaCodersBay) {
                System.out.println(listCopy);
            }
        } else if (chooseProgram == 3) {
            for (Person listCopy : listPaMagistrateVienna) {
                System.out.println(listCopy);
            }
        }
    }

    public static List<Person> removeFromListPA(List<Person> list) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Waehle den Index aus");
        int index = scan.nextInt();
        if (index < 0 || index > list.size()) {
            System.out.println("Dieser Index existiert nicht.");
            return list;
        }
        list.remove(index);
        return list;
    }

    public static Person searchPerson(List<Person> listPaLinz, List<Person> listPaCodersBay, List<Person> listPaMagistrateVienna, String firstName, String lastName) throws NullPointerException {
        for (Person listCopy : listPaLinz) {
            if (listCopy.getFirstName().equals(firstName) && listCopy.getLastName().equals(lastName)) {
                return listCopy;
            }
        }
        for (Person listCopy : listPaCodersBay) {
            if (listCopy.getFirstName().equals(firstName) && listCopy.getLastName().equals(lastName)) {
                return listCopy;
            }
        }
        for (Person listCopy : listPaMagistrateVienna) {
            if (listCopy.getFirstName().equals(firstName) && listCopy.getLastName().equals(lastName)) {
                return listCopy;
            }
        }
        throw new NullPointerException("Diese Person gibt es in der Liste nicht.");
    }
}
