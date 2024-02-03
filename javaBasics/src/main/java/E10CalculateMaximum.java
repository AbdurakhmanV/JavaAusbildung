import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class E10CalculateMaximum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                Dieses Programm berechnet das Maximum.
                Schreibe deine Zahlen in die Konsole rein.
                Durch 'q' wird die Zahleneingabe beendet.""");
        String input = "";
        List<Integer> numbers = new ArrayList<>();

        while (!(input.equals("q"))) {
            input = scan.next();
            if (!(input.equals("q"))) {
                numbers.add(Integer.parseInt(input));
            }
            System.out.println(numbers);
        }
        System.out.println("Das Maximum ist " + Collections.max(numbers));

    }
}

//    Provide a program that reads numbers from the console until it reads the letter 'q'. Calculate the maximum of all values and print it to the console.
