import java.util.Scanner;

public class E06TranslatingGrades {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int grade;
        System.out.println("Schreibe deine Schulnote als Zahl in die Konsole rein.");
        grade = scan.nextInt();

        switch (grade) {
            case 1 -> System.out.println("Sehr gut");
            case 2 -> System.out.println("gut");
            case 3 -> System.out.println("befriedigend");
            case 4 -> System.out.println("genuegend");
            case 5 -> System.out.println("nicht genuegend");
            default -> System.out.println("In Oesterreich gibt es nur die Schulnoten 1 bis 5.");
        }
    }
}
//    Write a program that translates grades from its numerical form to a word representation.
//
//        Print "Very good" in case of a 1.
//        Print "Good" in case of a 2.
//        Print "Satisfactory" in case of a 3.
//        Print "Sufficient" in case of a 4.
//        Print "Not sufficient" in case of a 5.
