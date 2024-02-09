package linkedList;

import java.util.Scanner;

public class MainLinkedList {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LinkedList list = begin();

        int chooseProgram;
        do{
            System.out.println("""
                0. Exit
                1. Element hinzufuegen
                2. Elment zu einem Index hinzufuegen
                3  Element entfernen
                4. Element nach Index ausgeben
                5. Alle Elemente anzeigen
                6. Listengroesse""");

            chooseProgram = scan.nextInt();

            if(chooseProgram==0){
                System.out.println("Das Programm wurde beendet.");
            }
            if(chooseProgram==1){
                addProgram(list);
            }
            if(chooseProgram==2){
                addToIndexProgram(list);

            }
            if(chooseProgram==3){
                removeProgram(list);
            }
            if(chooseProgram==4){
                getProgram(list);
            }
            if(chooseProgram==5){
                allElementsProgram(list);
            }
            if(chooseProgram==6){
                sizeProgram(list);
            }

        }while(chooseProgram!=0);

        scan.close();

    }
    public static LinkedList begin(){
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Fuege zwei Elemente in die Liste ein. Schreib diese in die Konsole rein.");
        String text01 = scan.next();
        String text02 = scan.next();




        return new LinkedList(new NodeLinkedList(text01), new NodeLinkedList(text02));
    }
    public static void addProgram(LinkedList list){
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreib das Element in die Konsole rein.");
        String text01 = scan.next();
        list.add(new NodeLinkedList(text01));

    }
    public static void addToIndexProgram(LinkedList list){
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreib den Index und das Element in die Konsole rein.");
        int index = scan.nextInt();
        String text = scan.next();
        list.add(index, new NodeLinkedList(text));

    }

    public static void removeProgram(LinkedList list){
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreib den Index, von der das Element geloescht werden soll, in die Konsole rein.");
        int index = scan.nextInt();
        list.remove(index);

    }
    public static void getProgram(LinkedList list){
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreib den Index in die Konsole rein, aus dem das Element angezeigt werden soll.");
        int index = scan.nextInt();
        System.out.println(list.get(index));


    }
    public static void allElementsProgram(LinkedList list){
        System.out.println();
        System.out.println(list.returnValues());
        System.out.println();
    }


    public static void sizeProgram(LinkedList list){

        System.out.println("Die Listengroesse: " + list.size());

    }
}
//