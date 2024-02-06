import java.util.*;

public class E01SetTheory {
    public static void main(String[] args) {

        List<Integer> a = new ArrayList<>(Arrays.asList(1, 3, 5));
        List<Integer> b = new ArrayList<>(Arrays.asList(3, 5, 7));
        List<Integer> c = new ArrayList<>(Arrays.asList(5, 7, 9));
        List<Integer> calculationList;

        calculationList = intersection(a, b);
        System.out.println(calculationList);

        calculationList = unionSet(a, unionSet(b, c));
        System.out.println(calculationList);

        calculationList = differenceQuantity(a, intersection(b, c));
        System.out.println(calculationList);

    }
    public static List<Integer> intersection(List<Integer> a, List<Integer> b){

        HashSet<Integer> hs = new HashSet<>(a);
        hs.retainAll(b);
        List<Integer> resultList=new ArrayList<>();
        resultList.addAll(hs);
        return  resultList;
    }
    public static List<Integer> unionSet(List<Integer> a, List<Integer> b){

        HashSet<Integer> hs = new HashSet<>(a);
        hs.addAll(b);
        List<Integer> resultList=new ArrayList<>();
        resultList.addAll(hs);
        return  resultList;
    }
    public static List<Integer> differenceQuantity(List<Integer> a, List<Integer> b){

        HashSet<Integer> hs = new HashSet<>(a);
        hs.addAll(a);
        hs.addAll(b);
        hs.removeAll(intersection(a,b));
        List<Integer> resultList=new ArrayList<>();
        resultList.addAll(hs);
        return  resultList;
    }
    public static List<Integer> insertionSortList(List<Integer> sortedList) {
        for (int i = 1; i < sortedList.size(); ++i) {
            int key = sortedList.get(i);
            int j = i - 1;
            while (j >= 0 && sortedList.get(j) > key) {
                sortedList.set(j + 1, sortedList.get(j));
                j--;
            }
            sortedList.set(j + 1, key);
        }
        return sortedList;
    }

}

//    Gegeben sind drei Zahlenmengen A, B, C.
//
//        A = { 1, 3, 5 }
//        B = { 3, 5, 7 }
//        C = { 5, 7, 9 }
//        Berechne möglichst effizient die Vereinigungsmenge von A ∪ B ∪ C sowie alle möglichen Schnittmengen und Differenzmengen der drei Zahlenmengen. Du kannst davon ausgehen, dass alle Mengen gleich groß sind.
//
//        Verwende dazu die bereits vorhandene Klasse "HashSet" und deren Methoden. Du sollst die Logik, wie sich z.B. eine Vereinigungsmenge bildet NICHT selbst programmieren. Die Klasse HashSet kann das schon.
//
//        Gestalte deine Methoden so, dass sie zur Berechnung der Ergebnismenge den selben return Typ haben wie die Parameter. Dadurch können die Methodenaufrufe beliebig oft geschachtelt werden.
//
//        z.B.: getUnionList(a, getIntersectionList(b,c)); // das sollte funktionieren und heißt A ∪ (B ∩ C)