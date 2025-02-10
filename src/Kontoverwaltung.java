import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kontoverwaltung {
    private final List<Konto> konten = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void kontoAnlegen(){
        System.out.println("Kontoinhaber: ");
        String inhaber = scanner.nextLine();
        System.out.println("Startguthaben: ");
        double guthaben = scanner.nextDouble();
        scanner.nextLine(); // Eingabepuffer leeren
        System.out.println("Kontoart (1=Giro, 2=Spar, 3=Kredit): ");
        int typ = scanner.nextInt();
        scanner.nextLine();
        if (guthaben < 0 && typ != 3) {  // Nur bei Kreditkonten ist negatives Guthaben erlaubt
            System.out.println("Neue Konten dürfen nicht mit negativem Bestand angelegt werden, außer bei Kreditkonten.");
            return;
        }
        switch (typ){
            case 1 -> {
                System.out.println("Bitte Überziehungsrahmen eingeben: ");
                double rahmen = scanner.nextDouble();
                scanner.nextLine();
                konten.add(new Girokonto(inhaber, guthaben, rahmen ));
            }
            case 2 -> {
                konten.add(new Sparkonto(inhaber, guthaben));
            }
            case 3 -> {
                konten.add(new Kreditkonto(inhaber, guthaben));
            }
            default -> System.out.println("Ungültige Auswahl!");
        }
    }
    public void einzahlen() {
        Konto k = kontoFinden();
        if (k != null) {
            System.out.println("Betrag einzahlen: ");
            double betrag = scanner.nextDouble();
            k.einzahlen(betrag); // Ruft die Methode des jeweiligen Kontotyps auf!
        }
    }
    public void abheben() {
        Konto k = kontoFinden();
        if (k != null) {
            System.out.println("Betrag abheben: ");
            double betrag = scanner.nextDouble();
            k.abheben(betrag);
        }
    }
    public void ueberweisen() {
        System.out.println("Von Kontonummer: ");
        Konto sender = kontoFinden();
        if (sender == null) return;

        System.out.println("An Kontonummer: ");
        Konto empfaenger = kontoFinden();
        if (empfaenger == null) return;

        System.out.println("Betrag überweisen: ");
        double betrag = scanner.nextDouble();

        // 1. Überprüfung: Betrag muss positiv sein
        if (betrag <= 0) {
            System.out.println("Überweisung fehlgeschlagen! Betrag muss positiv sein.");
            return;
        }

        // 2. Überprüfung: Sender hat genug Guthaben oder darf überziehen
        double neuerKontostandSender = sender.getKontostand() - betrag;
        if (sender instanceof Girokonto girokonto && neuerKontostandSender < -girokonto.getUeberziehungsrahmen()) {
            System.out.println("Überweisung abgelehnt! Nicht genug Guthaben. Maximal überweisbar: " + (sender.getKontostand() + girokonto.getUeberziehungsrahmen()) + " EUR");
            return;
        } else if ((sender instanceof Sparkonto || sender instanceof Kreditkonto) && neuerKontostandSender < 0) {
            System.out.println("Überweisung abgelehnt! Sparkonto und Kreditkonto dürfen nicht ins Minus gehen.");
            return;
        }

        // 3. Überprüfung: Kreditkonto darf nicht positiv werden
        if ((sender instanceof Girokonto || sender instanceof Sparkonto) && empfaenger instanceof Kreditkonto && empfaenger.getKontostand() + betrag > 0) {
            System.out.println("Überweisung abgelehnt! Kreditkonto darf keinen positiven Bestand haben. Maximal erlaubt: " + (-empfaenger.getKontostand()) + " EUR");
            return;
        }

        // 4. Überprüfung: Sparkonto darf nicht direkt auf anderes Konto überweisen
        if (sender instanceof Sparkonto && empfaenger instanceof Girokonto) {
            System.out.println("Überweisung abgelehnt! Sparkonten dürfen keine Überweisungen auf Girokonten tätigen.");
            return;
        }

        // 5. Überweisung durchführen
        sender.ueberweisen(empfaenger, betrag);
    }
    public void kontoauszug() {
        Konto k = kontoFinden();
        if (k != null) k.kontoauszug();
    }
    private Konto kontoFinden() {
        System.out.println("Kontonummer: ");
        String nummer = scanner.next();
        for (Konto k : konten) {
            if (k.getKontonummer().equals(nummer)) return k;
        }
        System.out.println("Konto nicht gefunden!");
        return null;
    }
    public void alleKontenAnzeigen() {
        if (konten.isEmpty()) {
            System.out.println("Keine Konten vorhanden.");
        } else {
            System.out.println("Alle Konten:");
            for (Konto k : konten) {
                String kontoTyp;
                if (k instanceof Girokonto) {
                    kontoTyp = "Girokonto";
                } else if (k instanceof Sparkonto) {
                    kontoTyp = "Sparkonto";
                } else if (k instanceof Kreditkonto) {
                    kontoTyp = "Kreditkonto";
                } else {
                    kontoTyp = "Unbekannt";
                }
                System.out.println("Kontonummer: " + k.getKontonummer() +
                        ", Typ: " + kontoTyp +
                        ", Bestand: " + k.getKontostand());
            }
        }
    }
}
