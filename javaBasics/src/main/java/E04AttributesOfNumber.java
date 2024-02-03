import java.util.Locale;
import java.util.Scanner;

public class E04AttributesOfNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
//        scan.useLocale(Locale.ENGLISH);
        int luckyNumber = 20;
        System.out.println("Schreibe eine Zahl rein.");
        double number = scan.nextDouble();
        int numberCopy = (int) number;

        //number as CharArray; die Zahl as ein CharArray;
        String numberAsString = String.valueOf(number);
        char[] numberAsArray = numberAsString.toCharArray();

        //the number has not a comma?;  runde Zahl?;
        if ((number % 10) == (numberCopy % 10)) {
            System.out.println("Die Zahl ist rund");
            if (number % 2 == 0) {
                System.out.println("Die Zahl ist eine gerade Zahl.");
            } else {
                System.out.println("Die Zahl ist eine ungerade Zahl.");
            }
            if (number < 100 && number > 10) {//zwei Stellen?
                System.out.println("Die Zahl hat zwei Stellen.");
            } else {
                System.out.println("Die Zahl hat nicht zwei Stellen.");
            }
        } else {
            System.out.println("Die Zahl ist nicht rund.");
            System.out.println("Die Zahl ist eine Kommerzahl.");
            if (numberAsArray.length == 3 && !(numberAsArray[2] == 0)) {// two spots?; zwei Stellen?;
                System.out.println("Die Zahl hat zwei Stellen.");
            } else {
                System.out.println("Die Zahl hat nicht zwei Stellen.");
            }
        }
        //lucky number; Glückszahl;
        if (number == luckyNumber) {
            System.out.println("Das ist die Glueckszahl!");
        }
    }
}

//    Schreibe ein Programm, dass von der Konsole eine Zahl einliest und ausgibt:
//
//        ob es sich um eine runde Zahl handelt
//        ob die Zahl gerade ist
//        ob die Zahl deiner Glückszahl entspricht (denk dir hierfür einfach eine eigene Glückszahl aus und gib sie zu Beginn des Programms auf der Konsole aus)
//        ob die Zahl zweistellig ist
//        Tipp: Für die ersten beiden Punkte wirst du die Modulo Funktion brauchen.
