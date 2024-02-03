public class E01AboutMe {
    public static void main(String[] args) {
        int age = 26;
        String firstName = "Max";
        String lastName = "Mustermann";
        String gender = "male";
        String birthday = "29.04.1992";
        double averageGrade = 2.3;
        boolean married = false;

        System.out.println("""
                """ +
                "firstname:        " + firstName +
                "\nlastname:         " + lastName +
                "\nager:             " + age +
                "\nbirthdate:        " + birthday +
                "\ngender:           " + gender +
                "\naverage grade:    " + averageGrade +
                "\nmarried:          " + married + "\n");
    }
}
//    Declare variables to express your age, first name, gender, last name, birthday, average grade and whether you are married or not. Think which datatype is well suited for which variable.
//    Print all variables to the console with System.out.println
