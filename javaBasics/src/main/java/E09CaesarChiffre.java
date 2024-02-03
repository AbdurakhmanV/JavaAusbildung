import java.util.Random;
import java.util.Scanner;

public class E09CaesarChiffre {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random randomChooser = new Random();
        int encryption = randomChooser.nextInt(24) + 2;
        System.out.println("Verschluesellung um " + encryption + " Stellen.");
        System.out.println("Schreib einen Text hinein der verschluesselt werden soll.");

        String text = scan.nextLine();


        System.out.println(text);
        char[] caesar = text.toCharArray();
        System.out.println(caesar);
        System.out.println("Deine Schluesselzahl: " + encryption);

        for (int i = 0; i < caesar.length; i++) {
            if (caesar[i] == ' ') {
                caesar[i] = ' ';
            } else if (caesar[i] >= 'A' && caesar[i] <= 'Z' && (caesar[i] + encryption) > 'Z') {
                int dif = (caesar[i] + encryption) - 90;
                dif--;
                caesar[i] = (char) (65 + dif);
            } else if (caesar[i] >= 'a' && caesar[i] <= 'z' && (caesar[i] + encryption) > 'z') {
                int dif = (caesar[i] + encryption) - 122;
                dif--;
                caesar[i] = (char) (97 + dif);
            } else if (!(caesar[i] >= 'a' && caesar[i] <= 'z') && !(caesar[i] >= 'A' && caesar[i] <= 'Z')) {
                caesar[i] = caesar[i];
            } else {
                caesar[i] = (char) (caesar[i] + encryption);
            }
        }

        System.out.println("Verschluesselter Text: ");
        System.out.println(caesar);

        System.out.println("Soll der Text entschluesselt werden?");
        boolean access = approval();

        if (access) {
            System.out.println("Schreib die Schluesselzahl in die Console rein.");
            int key = scan.nextInt();
            for (int i = 0; i < caesar.length; i++) {
                if (caesar[i] == ' ') {
                    caesar[i] = ' ';
                } else if (caesar[i] >= 'A' && caesar[i] <= 'Z' && (caesar[i] - key) < 'A') {
                    int dif = (caesar[i] - 65);
                    dif = key - dif;
                    caesar[i] = (char) (90 - dif + 1);
                } else if (caesar[i] >= 'a' && caesar[i] <= 'z' && (caesar[i] - key) < 'a') {
                    int dif = (caesar[i] - 97);
                    dif = key - dif;
                    caesar[i] = (char) (122 - dif + 1);
                } else if (!(caesar[i] >= 'a' && caesar[i] <= 'z') && !(caesar[i] >= 'A' && caesar[i] <= 'Z')) {
                    caesar[i] = caesar[i];
                } else {
                    caesar[i] = (char) (caesar[i] - key);
                }
            }
            System.out.println(caesar);
        } else {
            System.out.println("Das Programm wurde beendet.");
        }
        scan.close();
    }

    public static boolean approval() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Antworte mit 'ja' oder 'nein'.");
        do {
            String approval = scanner.next();
            switch (approval) {
                case "ja" -> {
                    return true;
                }
                case "nein" -> {
                    return false;
                }
                default -> {
                    System.out.println("Antworte nur mit 'ja' oder 'nein'.");
                }
            }
        } while (true);
    }
}

//    Wir wissen ja, dass jedes Zeichen in Java einen eindeutigen Code hat. Genauergesagt ist die numerische Repräsentation eines Characters/Zeichens der Unicode.
//    Die Caesar Chiffre ist ein simpler Verschlüsselungsalgorithmus bei dem alle Buchstaben um einen bestimmten offset X verschoben werden, erreichst du das Z solltest du wieder beim A starten, Sonderzeichen werden wir jetzt einmal auslassen.
//    Mit einer Caesar Chiffre von 6 würde aus dem Text: