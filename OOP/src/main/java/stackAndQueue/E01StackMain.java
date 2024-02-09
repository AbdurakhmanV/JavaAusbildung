package stackAndQueue;

import java.util.Scanner;

public class E01StackMain{
    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    E01Stack list = new E01Stack();

    int chooseProgram;
    do{
        System.out.println("""
                0. Exit
                1. push
                2. size
                3. pop
                4. peek
                5. pop elements
                6. Alle Elemente anzeigen""");

        do {
            chooseProgram = controlNumberInput();
            if(chooseProgram<0 || chooseProgram>6){
                System.out.println("Schreib nur eine Nummer der vorgegebenen Programmen ein.");
            }
        }while(chooseProgram<0 || chooseProgram>6);

        if(chooseProgram==0){
            System.out.println("Das Programm wurde beendet.");
        }
        if(chooseProgram==1){
            pushProgram(list);
        }
        if(chooseProgram==2){
            sizeProgram(list);

        }
        if(chooseProgram==3){
            popProgram(list);
        }
        if(chooseProgram==4){
            peekProgram(list);
        }
        if(chooseProgram==5){
            popElementsProgram(list);
        }
        if(chooseProgram==6){
            allElementsProgram(list);
        }

    }while(chooseProgram!=0);

    scan.close();

}

        public static void pushProgram(E01Stack list){
            System.out.println("Schreib das Element in die Konsole rein.");
            int number = controlNumberInput();
            list.push(number);

        }
        public static void sizeProgram(E01Stack list){

            System.out.println("Die Listengroesse: " + list.size());

        }

        public static void popProgram(E01Stack list){
            System.out.println("Letztes Element entfernen.");
            try {
                int number = list.pop();
                System.out.println(number + " wurde entfernt.");
            }catch (Exception e){
                System.out.println("Die Liste ist leer.");
            }
        }
        public static void peekProgram(E01Stack list){

            try{
                System.out.println("Letztes Element " + list.peek());
            }catch(Exception e){
                System.out.println("So viele Elemente gibt es nicht.");
            }
        }
        public static void popElementsProgram(E01Stack list){
            System.out.println("Letzten Elemente entfernen.");
            System.out.println("Wie viele?");
            int number = controlNumberInput();
            try {
                int[] elements = list.pop(number);
                for(int listCopy: elements){
                    System.out.println(listCopy);
                }
                System.out.println("wurden entfernt");
            }catch (Exception e){
                System.out.println("So viele Elemente gibt es nicht.");
            }
        }

        public static void allElementsProgram(E01Stack list){
            System.out.println();
            System.out.println(list.returnValues());
            System.out.println();
        }

        public static int controlNumberInput(){
            Scanner scan = new Scanner(System.in);
            int number;
            while (!(scan.hasNextInt())) {
                System.out.println("Waehle nur eine Zahl aus.");
                scan.nextLine();
            }
            number = scan.nextInt();
            return number;
        }

//Dateneinträge
}
