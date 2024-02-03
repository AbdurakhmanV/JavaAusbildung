public class E03WorkWithStrings {
    public static void main(String[] args) {
        String output = "Hello World!";
        System.out.println(output);
        System.out.println("String-Laenge: " + output.length());
        System.out.println("Grossschreibweise: " + output.toUpperCase());
        System.out.println("Kleinschreibweise: " + output.toLowerCase());
        output = output.replaceAll("World", "CodersBay");

        int count = 0;
        while (count < 15) {
            System.out.println(output);
            count++;
        }
    }
}
//    Create a variable of type String with “ Hello World! ” as its content (contains leading and trailing spaces).
//
//        Print the String and its length to the console.
//        Print the String with some variations:
//        all letters in uppercase
//        all letters in lowercase
//        replace “World” with “Codersbay”
//        without the leading spaces
//        Repeat the printed String 15 times seperated with linebreaks (don't copy the code 15 times 😉)
//        You might find the official documentation of the String class helpful.