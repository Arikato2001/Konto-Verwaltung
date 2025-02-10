import java.util.*;
public class Main {
    public static void main(String[] args) {
        Kontoverwaltung verwaltung = new Kontoverwaltung();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Konto anlegen\n2. Einzahlen\n3. Abheben\n4. Überweisen\n5. Kontoauszug\n6. Alle Konten Anzeigen\n0. Beenden");
            System.out.println("  - Nur Kreditkonten dürfen mit negativem Guthaben angelegt werden und Girokonten nur mit Überziehunsrahmen.");
            System.out.println("  - Sparkonten dürfen nur mit positivem Guthaben erstellt werden.");
            System.out.println("  - Bei Abhebungen oder Überweisungen wird der Überziehungsrahmen berücksichtigt.");
            int auswahl = scanner.nextInt();
            scanner.nextLine();

            switch (auswahl) {

                case 1 -> verwaltung.kontoAnlegen();
                case 2 -> verwaltung.einzahlen();
                case 3 -> verwaltung.abheben();
                case 4 -> verwaltung.ueberweisen();
                case 5 -> verwaltung.kontoauszug();
                case 6 -> verwaltung.alleKontenAnzeigen();
                case 0 -> System.exit(0);
                default -> System.out.println("Ungültige Auswahl!");
            }
        }
    }
}