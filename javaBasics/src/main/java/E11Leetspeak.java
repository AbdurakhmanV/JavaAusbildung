import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E11Leetspeak {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String text;


        List<Character> chosenLetters = new ArrayList<>(List.of('A', 'B', 'C', 'E', 'G', 'H', 'I', 'L', 'O', 'S', 'T', 'Z'));
        List<Character> leetLetters = new ArrayList<>(List.of('@', '8', '(', '3', '6', '#', '!', '1', '0', '$', '7', '2'));
        List<Character> leetText = new ArrayList<>();

        System.out.println("\nLeetspeak\nSchreib eine Text nur mit Grossbuchstaben in die Konsole rein.");
        text = scan.nextLine();
        char[] textComponents = text.toCharArray();

        System.out.println("Dein Text:");
        System.out.println(textComponents);

        for (char textComponent : textComponents) {
            if (chosenLetters.contains(textComponent)) {
                leetText.add(leetLetters.get(chosenLetters.indexOf(textComponent)));
            } else {
                leetText.add(textComponent);
            }
        }
        for (char leetSpeak : leetText) {
            System.out.print(leetSpeak);
        }
    }
}

//    Schreibe ein kleines Programm, welches einen String über die Konsole einliest.
//    Übersetzte den Text dann in Leetspeak - Du darfst dir hier eine beliebige Konfiguration verwenden,
//    falls dir keine einfällt hier ein Vorschlag:
//    Wenn du eine andere verwendest bitte in einer Markdown Datei deine Wahl angeben ;)
