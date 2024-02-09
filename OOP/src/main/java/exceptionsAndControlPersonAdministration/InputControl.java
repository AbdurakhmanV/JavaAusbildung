package exceptionsAndControlPersonAdministration;

import java.util.Scanner;

public class InputControl {
    private Scanner scan = new Scanner(System.in);

    public Scanner getScan() {
        return this.scan;
    }

    public int controlNumber() {
        while(true) {
            if (!this.scan.hasNextInt()) {
                System.out.println("Waehle nur eine Zahl aus.");
                this.scan.next();
                this.scan.nextLine();
            } else {
                int numberChooseProgram = this.scan.nextInt();
                if (numberChooseProgram < 0) {
                    System.out.println("Du kannst nur eine positive Zahl eingeben.");
                } else {
                    return numberChooseProgram;
                }
            }
        }
    }



    public boolean approval(){
        for( ;;){
            Scanner scan = new Scanner(System.in);
            System.out.println("ja oder nein?");
            String answer = scan.next();
            if(answer.equals("ja") || answer.equals("Ja") || answer.equals("JA")){
                System.out.println();
                return true;
            } else if (answer.equals("nein") || answer.equals("Nein") || answer.equals("NEIN")) {
                System.out.println();
                return false;
            }

        }
    }

}
