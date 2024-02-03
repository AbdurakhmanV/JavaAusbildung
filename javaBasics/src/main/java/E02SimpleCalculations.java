public class E02SimpleCalculations {
    public static void main(String[] args) {
        int x = 233;
        int y = 14;
        System.out.println();
        System.out.println(x + "+" + y + " = " + (x + y));
        System.out.println(x + "-" + y + " = " + (x - y));
        System.out.println(x + "*" + y + " = " + (x * y));
        System.out.printf(x + "/" + y + " = %.2f", ((double) x / (double) y));
    }
}

//    Declare two numeric variables with arbitary values. Calculate their sum, difference,
//    product and quotient and print the calculation with the result on the console with System.out.println/System.out.printf.
//    Perform all calculations with whole numbers and fractional numbers.
//    Bonus: Try to limit the decimal places of your calculations with the fractional numbers.
//    Your output should look like this:
//    138 + 235 = 373
//    138 - 235 = -97