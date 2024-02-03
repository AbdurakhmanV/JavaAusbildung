public class E08MultiplicationTables {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            System.out.println();
            System.out.println(i + ". Reihe:");
            for (int j = 1; j < 11; j++) {
                int result = i * j;
                System.out.println(i + " * " + j + " = " + result);
            }
        }
    }
}

//    Schreibe mit Hilfe von Schleifen das kleine 1 x 1 auf der Konsole aus. Deine Ausgabe sollte in etwa folgendermaßen aussehen: