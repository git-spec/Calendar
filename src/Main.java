import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // Eingabemöglichkeit für das Jahr des Kalenders
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter year: ");
        int year = sc.nextInt();
        sc.close();
        // Schaltjahr
        boolean isLeap = isLeapYear(year);
        // Anzahl der Tage im entsprechenden Monat
        int[] daysInMonth = {31, isLeap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31};
        // Monatsnamen
        String[] months = {
                "January",
                "February",
                "March",
                "April",
                "Mai",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };
        // Anzahl der Lücken
        int startDayOfMonth = getStartDayOfYear(year);
        // Ausgabe des Kalenders
        for(int i = 0; i < months.length; i++) {
            printCalendarForMonth(months[i], daysInMonth[i], startDayOfMonth);
            startDayOfMonth = (startDayOfMonth + daysInMonth[i]) % 7;
        }
    }
    /**
    * Berechnet das Schaltjahr.
    * @param year   Jahr
    * @return   Status eines Jahrs als Schaltjahr
    */
    public static  boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
    /**
     * Gibt die Anzahl der Lücken für nicht vorhandene Tage in der ersten Woche des Monats an.
     * @param year  Jahr
     * @return  Anzahl der Lücken in der ersten Woche des Monats
     */
    public static int getStartDayOfYear(int year) {
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        DayOfWeek dayOfWeek = firstDayOfYear.getDayOfWeek();
        return dayOfWeek.getValue() - 1;
    }
    /**
     * Druckt den Kalender aus.
     * @param month  Monat
     * @param daysInMonth   Anzahl der Tage im Monat
     * @param spaces    Anzahl der Lücken in der ersten Woche des Monats
     */
    public static void printCalendarForMonth(String month, int daysInMonth, int spaces) {
        System.out.println("\n" + month);
        System.out.println("Mo Tu We Th Fr Sa Su");
        // Lücken für nicht vorhandene Tage
        for(int i = 1; i <= spaces; i++) {
            System.out.print("   ");
        }
        // Tage des Monats pro Woche
        for(int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%2d ", day);
            // nächste Zeile nach sieben Tagen
            if ((day + spaces) % 7 == 0 || day == daysInMonth) {
                System.out.println();
            }
        }
    }
}
