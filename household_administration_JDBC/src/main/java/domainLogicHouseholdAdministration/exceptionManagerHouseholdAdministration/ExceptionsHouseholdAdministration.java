package domainLogicHouseholdAdministration.exceptionManagerHouseholdAdministration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class ExceptionsHouseholdAdministration {
    private Scanner scan = new Scanner(System.in);

    public Scanner getScan(){
        return this.scan;
    }


    public ExceptionsHouseholdAdministration() {

    }

    public String controlName(String name) throws Exception {
        if (name.matches(".*\\d.*") || name.matches(".*[^a-zA-Z0-9\\s].*")) {
            throw new Exception("invalid person name exception\nDer Vornamen wurde falsch geschrieben.\nDer Vorname darf keine Zahl oder ein Sonderzeichen enthalten.\n");
        }
        return name;
    }

    public java.sql.Date controlBirthdate(String transferedBirthdate) throws Exception {
        java.sql.Date birthday;

        //only digits and a dot is allowed in a german date
        if (transferedBirthdate.matches(".*[^.\\d].*")) {
            throw new Exception("\ninvalid date exception\nEs darf nur ein Datum reingeschrieben werden");
        }
        //control the format of the birthday in german
        String[] germanBirthDateArray = transferedBirthdate.split("\\.");
        if ((germanBirthDateArray[0].length() != 2) || (germanBirthDateArray[1].length() != 2) || (germanBirthDateArray[2].length() != 4) || germanBirthDateArray.length > 3 || germanBirthDateArray.length == 0) {
            throw new Exception("\ninvalid date exception\nDas Geburtsdatum weist auf ein falschen Format an.\nTag, Monat, Jahr, z.B. 09.08.1999\n");
        } else {
            if (Integer.parseInt(germanBirthDateArray[1]) > 12 || Integer.parseInt(germanBirthDateArray[1]) <= 0 || Integer.parseInt(germanBirthDateArray[0]) > 31 || Integer.parseInt(germanBirthDateArray[0]) <= 0) {
                throw new Exception("\ninvalid date exception\nDieser Monat existiert nicht.\n");
            } else if ((Integer.parseInt(germanBirthDateArray[1]) < 8 && Integer.parseInt(germanBirthDateArray[1]) % 2 == 0 && Integer.parseInt(germanBirthDateArray[0]) > 30)) {
                throw new Exception("\ninvalid date exception\nDieser Tag gibt es in dem Monat nicht.\n");
            } else if ((Integer.parseInt(germanBirthDateArray[1]) >= 8 && Integer.parseInt(germanBirthDateArray[1]) % 2 != 0 && Integer.parseInt(germanBirthDateArray[0]) > 30)) {
                throw new Exception("\ninvalid date exception\nDieser Tag existiert in dem Monat nicht.\n");
            } else if ((Integer.parseInt(germanBirthDateArray[2]) % 4) != 0 && (Integer.parseInt(germanBirthDateArray[1]) == 2) && (Integer.parseInt(germanBirthDateArray[0]) > 28)) {
                throw new Exception("\ninvalid date exception\nDieses Jahr ist kein Schaltjahr!\n");
            }
        }


        //Change the format of the date into english
        String englishBirthdateAsString;
        String englishBirthdate;
        SimpleDateFormat germanDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat englishDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date germanBirthdate = germanDateFormat.parse(transferedBirthdate);
            englishBirthdateAsString = englishDateFormat.format(germanBirthdate);
            englishBirthdate = englishBirthdateAsString;
            englishBirthdateAsString = englishBirthdateAsString.replace("-", "");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //Does the input of the birthday makes sense. No future date, not to more than 150 years old.
        LocalDate dateToday = LocalDate.now();
        String dateTodayAsString = String.valueOf(dateToday);
        dateTodayAsString = dateTodayAsString.replace("-", "");
        int dateTodayAsNumber = Integer.parseInt(dateTodayAsString);
        int birthdateAsNumber = Integer.parseInt(englishBirthdateAsString);

        if (dateTodayAsNumber < birthdateAsNumber) {
            throw new Exception("\ninvalid date exception\nDas Geburtsdatum kann nicht in der Zukunft liegen.\n");
        } else if (((dateTodayAsNumber - birthdateAsNumber) / 10000) >= 150) {
            throw new Exception("\ninvalid date exception\nGeburtsdatum entspricht nicht der Lebenserwatung.\n");
        }

        java.util.Date preBirthDate;
        try {
            preBirthDate = englishDateFormat.parse(englishBirthdate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        birthday = new java.sql.Date(preBirthDate.getTime());
        return birthday;
    }

    public String[] controlAddress(String[] addressArray) throws Exception {
        if (addressArray.length > 5 || addressArray.length < 4) {
            throw new Exception("\ninvalid address format\nDas Format der Adresse wurde nicht befolgt.\n");
        }
        if (addressArray.length == 5 && !addressArray[4].matches(".*\\d.*")) {
            throw new NumberFormatException("\ninvalid address format\nDie Tuernummer darf nur eine Nummer sein.\n");
        }
        return addressArray;
    }

    public String[] searchFormat(String text, String entity) throws Exception{
        String[] textArray;
        if(!(text.contains(" "))){
            if(text.contains(",")){
                String a = entity.equals("PERSON")?"(z.B. Max, Mustermann, 14.09.1995, maennlich)\n":"(z.B. Hund, Jack, A011457)\n";
                throw new Exception("\nFormat beachten!\nNach jedem Suchkriterium muss ein Komma folgen.\nKein Leerzeichen, falls nur durch ein Suchkriterium gesucht wird.\n" + a);            }
        }
        if((text.contains(" "))){
            if(!(text.contains(","))){
                String a = entity.equals("PERSON")?"(z.B. Max, Mustermann, 14.09.1995, maennlich)\n":"(z.B. Hund, Jack, A011457)\n";
                throw new Exception("\nFormat beachten!\nNach jedem Suchkriterium muss ein Komma folgen.\nKein Leerzeichen, falls nur durch ein Suchkriterium gesucht wird.\n" + a);
            }
        }

        text = text.replace(" ", "");
        textArray = text.split(",");

        if(textArray.length < 1 || textArray.length > 4 && entity.equals("PERSON")){
            throw new Exception("\nBeachte das Format!\n(z.B. Max, Mustermann, 14.09.1995, maennlich)\n");
        }
        if(textArray.length < 1 || textArray.length > 4 && entity.equals("PET")){
            throw new Exception("\nBeachte das Format!\n(z.B. Hund, Jack, A011578)\n");
        }
        return textArray;
    }

    public String[] controlPersonNameText() throws Exception{
        String text;
        text = scan.nextLine();

        String[] textArray;
        if(!(text.contains(" "))){
            throw new Exception("\nFormat beachten!\nNach jedem Suchkriterium muss ein Komma folgen.\nKein Leerzeichen, falls nur durch ein Suchkriterium gesucht wird.\n(z.B. Max, Mustermann)\n");
        }

        if(!(text.contains(","))){
            throw new Exception("\nFormat beachten!\nNach jedem Suchkriterium muss ein Komma folgen.\nKein Leerzeichen, falls nur durch ein Suchkriterium gesucht wird.\n(z.B. Max, Mustermann)\n");
        }


        text = text.replace(" ", "");
        textArray = text.split(",");

        if(textArray.length != 2){
            throw new Exception("\nBeachte das Format!\n(z.B. Max, Mustermann)\n");
        }

        textArray[0] = controlName(textArray[0]);
        textArray[1] = controlName(textArray[1]);

        return textArray;
    }

    public String[] controlPetData(String text) throws Exception{
        String[] textArray;

        if(text.contains(",") && !(text.contains(" "))) {
            throw new Exception("\nFormat beachten!\nNach jedem Suchkriterium muss ein Komma und Leerzeichen folgen.\nKein Leerzeichen, falls nur durch ein Suchkriterium gesucht wird.\n(z.B. Jack, Hund, A11023)\n");
        }else if(!text.contains(",") && (text.contains(" "))){
            throw new Exception("\nFormat beachten!\nNach jedem Suchkriterium muss ein Komma und Leerzeichen folgen.\nKein Leerzeichen, falls nur durch ein Suchkriterium gesucht wird.\n(z.B. Jack, Hund, A11023)\n");
        }
        text = text.replace(" ", "");
        textArray = text.split(",");
        if(textArray.length != 3){
            throw new Exception("Format beachten!");
        }
        return textArray;
    }

    public String[] controlNumbersPersonAndHouseholdID(){
        while(true) {
            System.out.println("""
                                    \nSchreib die Personen-ID und Haushalts-ID in die Konsole rein. 
                                    z.B.(15, 3)\n""");
            String text = scan.nextLine();
            text = text.replace(" ", "");
            String[] textArray = text.split(",");

            if(textArray.length == 2){
                if(!(textArray[0].matches(".*[\\d].*")) && !(textArray[1].matches(".*[\\d].*"))){
                    return textArray;
                }
            }
        }
    }


}
