public class E07OachkatzlSchwoaf {
    public static void main(String[] args) {

        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print("OachkatzlSchwoaf! ");
            } else if (i % 3 == 0) {
                System.out.print("Oachkatzl! ");
            } else if (i % 5 == 0) {
                System.out.print("Schwoaf! ");
            } else {
                System.out.print(i + "! ");
            }
        }
    }
}

//    OachkatzlSchwoaf ist ein Spiel um Kindern Division näher zu bringen. Die Regeln sind einfach - Reih um wird beginnend bei der Zahl 1 nach oben gezählt. Ist die Zahl durch drei teilbar, darf die Zahl allerdings nicht genannt werden - man muss Oachkatzl sagen, ist die Zahl durch fünf teilbar muss Schwoaf gesagt werden. Und - ist die Zahl durch drei und fünf teilbar, muss OachkatzlSchwoaf gesagt werden.
//    Als Beispiel: “Eins! Zwei! Oachkatzl(3)! Vier! Schwoaf(5)! Oachkatzl(6)! Sieben! Acht! Oachkatzl(9)! Schwoaf(10)! Elf! Oachkatzl(12)! Dreizehn! Vierzehn! OachkatzlSchwoaf(15)! Sechzehn!”
//    Schreibe ein Programm, dass die Zahlen von 1 - 100 nach diesem Schema ausgibt. Du musst die Zahlen nicht in Wortform ausgeben, numerisch reicht.