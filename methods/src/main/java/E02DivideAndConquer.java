public class E02DivideAndConquer {
    public static void main(String[] args) {
        int[] numbers = {3, 7, 1, 27, 6, 222, 2, 601, 12, 39};

        System.out.println(numbers.length);

        int maxValueOfArray = listMaxValueRecursion(numbers, 0, numbers.length-1);
        System.out.println("Der Maximalwert ist: " + maxValueOfArray);

        int[] numbersCopy = mergeSortAlgorithmRecursionArray(numbers, 0, numbers.length-1);
        System.out.println("sortierte Liste:");
        for (int i = 0; i < numbersCopy.length; i++) {
            System.out.print(numbersCopy[i] + " ");
        }
    }
    public static int listMaxValueRecursion(int[] numbers, int minPosition, int maxPosition) {

        if (minPosition == maxPosition) {
            return numbers[minPosition];
        }

        int middlePosition = (minPosition + maxPosition) / 2;


        int leftMax = listMaxValueRecursion(numbers, minPosition, middlePosition);
        int rightMax = listMaxValueRecursion(numbers, middlePosition + 1, maxPosition);


        return Math.max(leftMax, rightMax);

    }

    public static int[] mergeSortAlgorithmRecursionArray(int[] numbers, int minPosition, int maxPosition) {

        if (minPosition == maxPosition) {
            return new int[]{numbers[minPosition]};
        }

        int middlePosition = (minPosition + maxPosition) / 2;


        int[] leftArrayPart = mergeSortAlgorithmRecursionArray(numbers, minPosition, middlePosition);
        int[] rightArrayPart = mergeSortAlgorithmRecursionArray(numbers, middlePosition + 1, maxPosition);


        if(leftArrayPart.length==1 && rightArrayPart.length==1){
            int[] mergedArray=new int[2];
            mergedArray[0]= Math.min(leftArrayPart[0], rightArrayPart[0]);
            mergedArray[1]= Math.max(leftArrayPart[0], rightArrayPart[0]);
            return mergedArray;
        }

        return merge(leftArrayPart, rightArrayPart);
    }


    public static int[] merge(int[] leftArrayPart, int[] rightArrayPart) {

        int[] mergedArray = new int[leftArrayPart.length + rightArrayPart.length];

        int leftIndex = 0;
        int rightIndex = 0;
        int mergedArrayIndex = 0;

        while (leftIndex < leftArrayPart.length && rightIndex < rightArrayPart.length) {
            if (leftArrayPart[leftIndex] <= rightArrayPart[rightIndex]) {
                mergedArray[mergedArrayIndex] = leftArrayPart[leftIndex];
                leftIndex++;
            } else {
                mergedArray[mergedArrayIndex] = rightArrayPart[rightIndex];
                rightIndex++;
            }
            mergedArrayIndex++;
        }

        while (leftIndex < leftArrayPart.length) {
            mergedArray[mergedArrayIndex] = leftArrayPart[leftIndex];
            leftIndex++;
            mergedArrayIndex++;
        }

        while (rightIndex < rightArrayPart.length) {
            mergedArray[mergedArrayIndex] = rightArrayPart[rightIndex];
            rightIndex++;
            mergedArrayIndex++;
        }

        return mergedArray;
    }
}

//    Schreibe ein Programm, dass in einem Array von Zahlen das Maximum findet.
//    Da dieses Array sehr, sehr groß werden kann wollen wir das Divide & Conquer Prinzip anwenden.
//    Das Zahlenarray könnt ihr direkt anlegen, ihr müsst sie nicht von der Konsole einlesen.
//    Die Maximum Suche sollten wir mit dem Divide & Conquer Prinzip umsetzen.
//    Solange das Array 2 oder mehr Elemente behält, wird das Array in der Mitte geteilt und für beide Arrays das Maximum gesucht.
//    Löse das Problem mit Hilfe einer Rekursion.
