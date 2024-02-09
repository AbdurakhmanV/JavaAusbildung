package stackAndQueue;

import java.util.Scanner;

public class E02QueueMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        E02Queue list = new E02Queue();

        int chooseProgram;
        do {
            System.out.println("0. Exit\n1. enqueue\n2. size\n3. dequeue\n4. dequeue mehr als ein Element\n5. Alle Elemente anzeigen");

            do {
                chooseProgram = controlNumberInput();
                if (chooseProgram < 0 || chooseProgram > 5) {
                    System.out.println("Schreib nur eine Nummer der vorgegebenen Programmen ein.");
                }
            } while(chooseProgram < 0 || chooseProgram > 5);

            if (chooseProgram == 0) {
                System.out.println("Das Programm wurde beendet.");
            }

            if (chooseProgram == 1) {
                enqueueProgram(list);
            }

            if (chooseProgram == 2) {
                sizeProgram(list);
            }

            if (chooseProgram == 3) {
                dequeueProgram(list);
            }

            if (chooseProgram == 4) {
                dequeueElementsProgram(list);
            }

            if (chooseProgram == 5) {
                allElementsProgram(list);
            }
        } while(chooseProgram != 0);

        scan.close();
    }

    public static void enqueueProgram(E02Queue list) {
        new Scanner(System.in);
        System.out.println("Schreib das Element in die Konsole rein.");
        int number = controlNumberInput();
        list.enqueue(number);
    }

    public static void sizeProgram(E02Queue list) {
        System.out.println("Die Listengroesse: " + list.size());
    }

    public static void dequeueProgram(E02Queue list) {
        new Scanner(System.in);
        System.out.println("Letztes Element entfernen.");

        try {
            int number = list.dequeue();
            System.out.println("" + number + " wurde entfernt.");
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }

    }

    public static void dequeueElementsProgram(E02Queue list) {
        new Scanner(System.in);
        System.out.println("Letzten Elemente entfernen.");
        System.out.println("Wie viele?");
        int amountOfElementsToRemove = controlNumberInput();

        try {
            int[] elements = list.dequeue(amountOfElementsToRemove);

            for(int i = 0; i < elements.length; ++i) {
                int listCopy = elements[i];
                System.out.println(listCopy);
            }

            System.out.println("wurden entfernt");
        } catch (Exception e) {
            System.out.println("So viele Elemente gibt es nicht.");
        }

    }

    public static void allElementsProgram(E02Queue list) {
        System.out.println();
        System.out.println(list.returnValues());
        System.out.println();
    }

    public static int controlNumberInput() {
        Scanner scan = new Scanner(System.in);

        while(!scan.hasNextInt()) {
            System.out.println("Waehle nur eine Zahl aus.");
            scan.nextLine();
        }

        int number = scan.nextInt();
        return number;
    }
}
