import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class bingoCardGenerator {
    public static void main(String[] args) {

        List<Integer> bingoNumbers = new ArrayList<>();
        String bingoLetters = "BINGO";
        int randomNumber;
        Random randomChooser = new Random();

        int start = 1;
        System.out.println();
        for (int i = 0; i < 5; i++) {
            bingoNumbers.clear();
            System.out.print(bingoLetters.charAt(i) + ": ");
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 3) {
                    j++;
                }
                do {
                    randomNumber = randomChooser.nextInt(15) + start;
                } while (bingoNumbers.contains(randomNumber));
                bingoNumbers.add(randomNumber);
            }
            bingoNumbers = bubbleSortList(bingoNumbers);
            for (int listCopy : bingoNumbers) {
                System.out.print(listCopy + " ");
            }
            System.out.println();
            if (i == 0) {
                start = 16;
            } else {
                start += 15;
            }
        }

        List<Integer> bingoNumbers01 = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());
        List<Integer> bingoNumbers02 = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());
        List<Integer> bingoNumbers03 = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());


        //print out the time needed for the calculation with each sort algorithm
        System.out.println();
        System.out.println("insertion sort");
        long number01 = System.currentTimeMillis();
        bingoNumbers01 = insertionSortList(bingoNumbers01);
        long number02 = System.currentTimeMillis();
        long diff = number02 - number01;
        System.out.println(diff);
        System.out.println();

        System.out.println("bubble sort");
        number01 = System.currentTimeMillis();
        bingoNumbers02 = bubbleSortList(bingoNumbers01);
        number02 = System.currentTimeMillis();
        diff = number02 - number01;
        System.out.println(diff);
        System.out.println();

        System.out.println("selection sort");
        number01 = System.currentTimeMillis();
        bingoNumbers03 = selectionSortList(bingoNumbers01);
        number02 = System.currentTimeMillis();
        diff = number02 - number01;
        System.out.println(diff);
        System.out.println();

    }


    //insertion-sort-algorithm array
    public static int[] insertionSortArr(int... arr) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    //insertion-sort-algorithm list
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


    //bubble-sort-algorithm array
    public static int[] bubbleSortArray(int... arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    int a = arr[j];
                    arr[j] = arr[i];
                    arr[i] = a;
                }
            }
        }
        return arr;


    }

    //bubble-sort-algorithm list
    public static List<Integer> bubbleSortList(List<Integer> sortedList) {

        for (int i = 0; i < sortedList.size(); i++) {
            for (int j = 0; j < sortedList.size(); j++) {//oder einfach (sortedList.size()-1);
                if (j == sortedList.size() - 1) {
                    sortedList.set(j, sortedList.get(j));
                } else if (sortedList.get(j) > sortedList.get(j + 1)) {
                    int a = sortedList.get(j + 1);
                    sortedList.set(j + 1, sortedList.get(j));
                    sortedList.set(j, a);
                }
            }
        }
        return sortedList;
    }

    //selection-sort-algorithm list
    public static List<Integer> selectionSortList(List<Integer> sortedList) {
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int minValueAtIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(j) < sortedList.get(minValueAtIndex)) {
                    minValueAtIndex = j;
                }
            }
            int replacement = sortedList.get(minValueAtIndex);
            sortedList.set(minValueAtIndex, sortedList.get(i));
            sortedList.set(i, replacement);
        }
        return sortedList;
    }


    public static int[] selectionSortList(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minValueAtIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minValueAtIndex]) {
                    minValueAtIndex = j;
                }
            }
            if (minValueAtIndex != i) {
                int replacement = arr[minValueAtIndex];
                arr[minValueAtIndex] = arr[i];
                arr[i] = replacement;
            }
        }
        return arr;
    }

}
