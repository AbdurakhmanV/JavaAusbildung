import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E05WorktimeOrFreetime {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String time;

        List<Integer> workHours = new ArrayList<>();
        List<Integer> workMinutes = new ArrayList<>();

        for (int i = 8; i < 16; i++) {
            workHours.add(i);
        }

        for (int i = 0; i < 61; i++) {
            workMinutes.add(i);
        }
        System.out.println("Waehle eine Zeit aus. z.B: 09:35");

        time = scan.next();

        time = time.replaceFirst(":", "");
        char[] timeComponents = time.toCharArray();

        String hourComponent = "";
        hourComponent += timeComponents[0];
        hourComponent += timeComponents[1];

        String minutesComponent = "";
        minutesComponent += timeComponents[2];
        minutesComponent += timeComponents[3];

        int hour = Integer.parseInt(hourComponent);
        int minutes = Integer.parseInt(minutesComponent);

        if (hour == 15 && minutes > 30) {
            System.out.println("Um die Uhrzeit ist Freizeit.");
        } else if (hour == 12 && (minutes >= 0 && minutes <= 30)) {
            System.out.println("Da ist Mittagspause.");
        } else if (workHours.contains(hour) && workMinutes.contains(minutes)) {
            System.out.println("Um die Uhrzeit ist Arbeitszeit.");
        } else {
            System.out.println("Um die Uhrzeit ist Freizeit.");
        }

    }
}

//    In der CODERS.BAY arbeiten wir von 8:30 bis 15:30 Uhr. Schreibe ein Programm, dass eine Zahl von der Konsole einliest und ausgibt ob die Stunde in der Arbeitszeit liegt oder nicht.
//    Bonus: von 12:00 bis 12:30 Uhr ist immer Mittagspause, gib also in der Zeit aus, dass Mittag ist.