import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E02Dictionary {
    public static void main(String[] args) {
        HashMap<String, String> toGerman = new HashMap<>() {{
            put("desk", "Tisch");
            put("soccer", "Fussball");
            put("tree", "Baum");
            put("establishment", "Gruendung");
            put("tile", "Fliese");

        }};

        HashMap<String, String> toEnglish = new HashMap<>();
        toEnglish.put("Tisch", "desk");
        toEnglish.put("Fussball", "soccer");
        toEnglish.put("Baum", "tree");
        toEnglish.put("Gruendung", "establishment");
        toEnglish.put("Fliese", "tile");

        E02DictionaryManageWords dictionary = new E02DictionaryManageWords();
        int chooseProgram;


        System.out.println();
        System.out.println("Dieses Programm uebersetzt Woerter von Deutsch nach Englisch und umgekehrt.");
        do {
            System.out.println("""
                    Waehle ein Programm aus indem du die Nummer des Programmes eingibst.
                    0 beendet das Programm.
                                    
                    0. EXIT
                    1. Wort uebersetzen
                    2. Fuege eines neues Wort-Paar hinzu
                    3. Entferne ein Wort-Paar aus dem Woerterbuch
                    4. Alle Eintraege in dem Woerterbuch""");
            do {
                chooseProgram = dictionary.controlNumber();
                if (chooseProgram > 4) {
                    System.out.println("Waehle nur eine Nummer der Programmauswahl aus");
                }
            } while (chooseProgram > 4);

            if (chooseProgram == 0) {
                System.out.println("Das Programm wurde beendet.");
            }

            if (chooseProgram == 1) {
                translate(toEnglish, toGerman, dictionary);
            }
            if (chooseProgram == 2) {
                addWords(toEnglish, toGerman, dictionary);
            }

            if (chooseProgram == 3) {
                removeWords(toEnglish, toGerman, dictionary);
            }

            if (chooseProgram == 4) {
                listWords(toEnglish);
            }
        } while (chooseProgram != 0);

    }


    public static void translate(HashMap<String, String> toEnglish, HashMap<String, String> toGerman, E02DictionaryManageWords dictionary) {

        int amount;
        int counter = 0;
        System.out.println("Wort uebersetzten");
        do {
            System.out.println("Wie viele Woerter willst du uebersetzten.\nSchreib die Zahl in die Konsole rein.");
            amount = dictionary.controlNumber();
            while (counter < amount) {
                counter++;
                System.out.println("Schreib das Wort in die Konsole rein.");
                String text = dictionary.controlText();
                System.out.println(text);
                if (!(toEnglish.get(text) == null)) {
                    System.out.println("zu Englisch");
                    System.out.println(text + " = " + toEnglish.get(text));
                } else if (!(toGerman.get(text) == null)) {
                    System.out.println("zu Deutsch");
                    System.out.println(text + " = " + toGerman.get(text));

                } else {
                    System.out.println("Dieses Wort befindet sich nicht in diesem Woerterbuch.");
                }
            }
            System.out.println("Willst du weitere Woerter uebersetzten lassen?");
        } while (dictionary.approval());


    }

    public static void addWords(HashMap<String, String> toEnglish, HashMap<String, String> toGerman, E02DictionaryManageWords dictionary) {
        do {
            int amount;
            int counter = 0;
            System.out.println("Wie viele Woerter willst du hinzufuegen?\nSchreib die Zahl in die Konsole rein.");
            amount = dictionary.controlNumber();
            while (counter < amount) {
                counter++;
                String text01 = dictionary.germanText();
                String text02 = dictionary.englishText();
                toEnglish.put(text01, text02);
                toGerman.put(text02, text01);
            }

            System.out.println("Willst du weitere Woerter einfuegen?");
        } while (dictionary.approval());
    }

    public static void removeWords(HashMap<String, String> toEnglish, HashMap<String, String> toGerman, E02DictionaryManageWords dictionary) {
        do {
            int amount;
            int counter = 0;
            System.out.println("Wie viele Woerter willst du entfernen?\nSchreib die Zahl in die Konsole rein.");
            amount = dictionary.controlNumber();
            while (counter < amount) {
                counter++;
                String text01 = dictionary.germanText();
                String text02 = toEnglish.get(text01);
                toEnglish.remove(text01, toEnglish.get(text01));
                toGerman.remove(text02, text01);
            }

            System.out.println("Willst du weitere Woerter entfernen?");
        } while (dictionary.approval());
    }

    public static void listWords(HashMap<String, String> toEnglish) {
        System.out.println("Hier sind alle Woerter im Woerterbuch.");

        List<String> toEnglishCopy = new ArrayList<>();

        for (String toEnglishCopy01 : toEnglish.keySet()) {
            System.out.printf("%-15s = %15s\n", toEnglishCopy01, toEnglish.get(toEnglishCopy01));
            toEnglishCopy.add(String.format("%-15s = %15s", toEnglishCopy01, toEnglish.get(toEnglishCopy01)));
        }

        System.out.println();
        System.out.println();

        for (int i = 1; i < toEnglishCopy.size(); i++) {
            String key = toEnglishCopy.get(i);
            int j = i - 1;
            while (j >= 0 && toEnglishCopy.get(j).charAt(0) >= key.charAt(0)) {
                toEnglishCopy.set(j + 1, toEnglishCopy.get(j));
                j--;
            }
            toEnglishCopy.set(j + 1, key);
        }

        System.out.println();
        for (String toEnglishCopy01 : toEnglishCopy) {
            System.out.println(toEnglishCopy01);
        }
    }


}

//    Erstelle ein Programm welches ein Wörterbuch zum Übersetzen von Wörtern zwischen Englisch und Deutsch implementiert (bi-direktional). Es soll folgende Funktionalitäten umfassen:
//
//        Hinzufügen eines neuen Wort-Paares (Englisch und Deutsch)
//        Entfernen eines Wort-Paares aus dem Wörterbuch
//        Suchen der Übersetzung eines Wortes (in beide Richtungen)
//        Um diese Aufgabe zu lösen ist das Map Interface gut geeignet.
