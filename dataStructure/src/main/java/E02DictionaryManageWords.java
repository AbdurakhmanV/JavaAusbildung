
import java.util.InputMismatchException;
import java.util.Scanner;

public class E02DictionaryManageWords {

        public String germanText() {
            System.out.println("Zuerst das deutsche Wort.");
            return controlText();
        }

        public static String englishText() {
            System.out.println("Nun das englische Wort.");
            return controlText();
        }

        public static String controlText() {
            Scanner scan = new Scanner(System.in);
            for (; ; ) {
                try {
                    String preText = scan.next();
                    String text = preText.replace("ß", "ss");

                    char[] textArray = text.toCharArray();

                    for (int i = 0; i < textArray.length; i++) {
                        if (textArray[i] <= 9) {
                            throw new Exception();
                        } else if ((textArray[textArray.length - 1] > 9)) {
                            return text;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Du darfst keine Zahlen oder Sonderzeichen in ein Wort reinschreiben.");
                }
            }
        }

        public static int controlNumber() {
            Scanner scan = new Scanner(System.in);
            do {
                try {
                    int number = scan.nextInt();
                    if (number < 0) {
                        System.out.println("Du kannst nur positive Zahlen eingeben.");
                    } else {
                        return number;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Du kannst nur eine Nummer zur Auswahl eines Programmes eingeben.");
                }
            } while (true);
        }

        public static boolean approval() {
            System.out.println("Antworte mit 'ja' oder 'nein'.");
            Scanner scan = new Scanner(System.in);
            String approve = scan.next();
            while (true) {
                switch (approve) {
                    case "ja" -> {
                        return true;
                    }
                    case "nein" -> {
                        return false;
                    }
                    default -> System.out.println("Anworte nur mit 'ja' oder 'nein'.");
                }
            }
        }
    }
