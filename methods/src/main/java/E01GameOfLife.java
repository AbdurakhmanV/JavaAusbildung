import java.util.Random;

public class E01GameOfLife {
    public static void main(String[] args) {
        String[][] map = null;
        map = initializeMapOrMapCopy(map);
        showMap(map);
        generationOfCells(map);
    }

    public static String[][] initializeMapOrMapCopy(String[][] map) {
        if (map == null) {
            Random randomChooser = new Random();
            map = new String[10][10];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    int number = randomChooser.nextInt(2);
                    map[i][j] = number > 0 ? "[ ] " : "[*] ";
                }
            }
            return map;
        }
        System.out.println("Kopie der Spielkarte wure erstellt.\nUntere Karte");
        String[][] mapCopy = new String[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                mapCopy[i][j] = map[i][j].equals("[ ] ") ? "[ ] " : "[*] ";
            }
        }
        return mapCopy;
    }

    public static void showMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (String copyMap : map[i]) {
                System.out.print(copyMap);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void generationOfCells(String map[][]) {
        String[][] mapCopy;
        int count = 0;
        mapCopy = initializeMapOrMapCopy(map);
        showMap(mapCopy);
        do {
            count++;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    int countAlive = 0;
                    for (int k = i - 1; k < i + 2 && k < map.length; k++) {
                        k = k < 0 ? k += 1 : k;
                        for (int l = j - 1; l < j + 2 && l < map[i].length; l++) {
                            l = l < 0 ? l += 1 : l;
                            countAlive = mapCopy[k][l].equals("[*] ") ? countAlive += 1 : countAlive;
                            switch (countAlive) {
                                case 3 -> map[i][j] = "[*] ";
                                default -> {
                                    if (map[i][j].equals("[*] ") && countAlive == 4) {
                                        map[i][j] = "[*] ";
                                    } else {
                                        map[i][j] = "[ ] ";
                                    }
                                }
                            }
                            if (mapCopy[i][j].equals("[ ] ") && countAlive >= 4) {
                                map[i][j] = "[ ] ";
                            }
                        }
                    }
                }
            }
            showMap(map);
            mapCopy = initializeMapOrMapCopy(map);
            showMap(mapCopy);
        } while (count != 19);
    }
}


//    Conways Game of Life ist ein beliebter Algorithmus der Automatentheorie. Dabei hat man ein beliebig großes Feld (e.g. 100 x 100 Zellen).
//    Jede der Zelle kann entweder lebendig oder tod sein.
//    Eine Zelle hat immer acht Nachbarzellen (Moor). Die Anfangspopulation wird zufällig gewählt,
//    danach entwickelt sich der Algorithmus in Generationen nach folgenden Regeln:
//
//        ist eine Zelle tot und hat genau 3 lebende Nachbarn, wird sie in der nächsten Generation geboren 👶
//        ist eine Zelle am Leben und hat weniger als 2 Nachbarn stirbt sie an Einsamkeit 😔
//        ist eine Zelle am Leben und hat 2 oder 3 Nachbarn, bleibt sie am Leben 🤝‍
//        ist eine Zelle am Leben und hat mehr als 3 Nachbarn, stirbt sie an Überbevölkerung 💀
//        Eine schöne Visualisierung des Algorithmus findet ihr auf https://bitstorm.org/gameoflife/.
//
//        Schreib einen Algorithmus der ein N X N Feld visualisert (z.B. # für lebende Zellen und Leerzeichen/'.' für tote Zellen) und Generationen durchlaufen lässt. Entweder X Generationen oder bis das Programm abgebrochen wird.