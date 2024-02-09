package doubleLinkedList;

import java.util.Scanner;

public class MainDoubleLinkedList {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        DoubleLinkedList list = new DoubleLinkedList();

        E01Stack stackController = new E01Stack(list);
        E02Queue queueController = new E02Queue(list);
        int chooseProgram;

        do {
            System.out.println("""
                    0. Exit
                    1. Element hinzufuegen
                    2. Elment zu einem Index hinzufuegen
                    3  Element entfernen
                    4. Element nach Index ausgeben
                    5. Alle Elemente anzeigen
                    6. Listengroesse
                    7. Alle Elemente reverse
                                    
                    8.  push
                    9.  pop
                    10. peek
                    11. pop multiple elements
                                    
                    12. enqueue
                    13. dequeue
                    14. dequeue multiple elements""");

            do {
                chooseProgram = controlNumberInput();
                if (chooseProgram < 0 || chooseProgram > 14) {
                    System.out.println("Schreib nur eine Nummer der vorgegebenen Programmen ein.");
                }
            } while (chooseProgram < 0 || chooseProgram > 14);

            if (chooseProgram == 0) {
                System.out.println("Das Programm wurde beendet.");
            }
            if (chooseProgram == 1) {
                addProgram(list);
            }
            if (chooseProgram == 2) {
                addToIndexProgram(list);
            }
            if (chooseProgram == 3) {
                removeProgram(list);
            }
            if (chooseProgram == 4) {
                getProgram(list);
            }
            if (chooseProgram == 5) {
                allElementsProgram(list);
            }
            if (chooseProgram == 6) {
                sizeProgram(list);
            }
            if (chooseProgram == 7) {
                allElementsReverseProgram(list);
            }
            if (chooseProgram == 8) {
                pushProgram(stackController);
            }
            if (chooseProgram == 9) {
                popProgram(stackController);
            }
            if (chooseProgram == 10) {
                peekProgram(stackController);
            }
            if (chooseProgram == 11) {
                popMultipleElementsProgram(stackController);
            }


            if (chooseProgram == 12) {
                enqueueProgram(queueController);
            }
            if (chooseProgram == 13) {
                dequeueProgram(queueController);
            }
            if (chooseProgram == 14) {
                dequeueElementsProgram(queueController);
            }


        } while (chooseProgram != 0);

        scan.close();

    }

    public static void addProgram(DoubleLinkedList list) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreib das Element in die Konsole rein.");
        //String text = scan.next();
        int text = scan.nextInt();
        list.add(new Node(text));

    }

    public static void addToIndexProgram(DoubleLinkedList list) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreib den Index und das Element in die Konsole rein.");
        int index = scan.nextInt();
//        String text = scan.next();
        int text = scan.nextInt();
        list.add(index, new Node(text));

    }

    public static void removeProgram(DoubleLinkedList list) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreib den Index, von der das Element geloescht werden soll, in die Konsole rein.");
        int index = scan.nextInt();
        list.remove(index);

    }

    public static void getProgram(DoubleLinkedList list) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreib den Index ind die Konsole rein, aus dem das Element angezeigt werden soll.");
        int index = scan.nextInt();
        System.out.println(list.get(index));


    }

    public static void allElementsProgram(DoubleLinkedList list) {
        System.out.println();
        System.out.println(list.returnValues());
        System.out.println();
    }


    public static void sizeProgram(DoubleLinkedList list) {

        System.out.println("Die Listengroesse: " + list.size());

    }

    public static void allElementsReverseProgram(DoubleLinkedList list) {
        System.out.println();
        System.out.println(list.returnValuesReverse());
        System.out.println();
    }


    public static void pushProgram(E01Stack stackController) {
        System.out.println("Schreib das Element in die Konsole rein.");
        int number = controlNumberInput();
        stackController.push(number);

    }

    public static void popProgram(E01Stack stackController) {
        System.out.println("Letztes Element entfernen.");
        try {
            int number = stackController.pop();
            System.out.println(number + " wurde entfernt.");
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }
    }

    public static void peekProgram(E01Stack stackController) {

        try {
            System.out.println("Letztes Element " + stackController.peek());
        } catch (Exception e) {
            System.out.println("So viele Elemente gibt es nicht.");
        }
    }

    public static void popMultipleElementsProgram(E01Stack stackController) {
        System.out.println("Letzten Elemente entfernen.");
        System.out.println("Wie viele?");
        int number = controlNumberInput();
        try {
            int[] elements = stackController.pop(number);
            for (int listCopy : elements) {
                System.out.println(listCopy);
            }
            System.out.println("wurden entfernt");
        } catch (Exception e) {
            System.out.println("So viele Elemente gibt es nicht.");
        }
    }

    public static int controlNumberInput() {
        Scanner scan = new Scanner(System.in);
        int number;
        while (!(scan.hasNextInt())) {
            System.out.println("Waehle nur eine Zahl aus.");
            scan.nextLine();
        }
        number = scan.nextInt();
        return number;
    }

    public static void enqueueProgram(E02Queue queueController) {
        System.out.println("Schreib das Element in die Konsole rein.");
        int number = controlNumberInput();
        queueController.enqueue(number);

    }

    public static void dequeueProgram(E02Queue queueController) {
        System.out.println("Ersters Element entfernen.");
        try {
            int number = queueController.dequeue();
            System.out.println(number + " wurde entfernt.");
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }
    }

    public static void dequeueElementsProgram(E02Queue queueController) {
        System.out.println("Letzten Elemente entfernen.");
        System.out.println("Wie viele?");
        int amountOfElementsToRemove = controlNumberInput();
        try {
            int[] elements = queueController.dequeue(amountOfElementsToRemove);
            for (int listCopy : elements) {
                System.out.println(listCopy);
            }
            System.out.println("wurden entfernt");
        } catch (Exception e) {
            System.out.println("So viele Elemente gibt es nicht.");
        }
    }
}
