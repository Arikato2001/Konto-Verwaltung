import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kontoverwaltung {
    private final List<Konto> konten = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void kontoAnlegen(String inhaber, double guthaben, String kontoTyp, double ueberziehungsRahmen) {
        if (kontoTyp.equals("Girokonto") && guthaben < 0) return;
        if (kontoTyp.equals("Sparkonto") && guthaben < 0) return;
        if (kontoTyp.equals("Kreditkonto") && guthaben >= 0) return;

        switch (kontoTyp) {
            case "Girokonto" -> konten.add(new Girokonto(inhaber, guthaben, ueberziehungsRahmen));
            case "Sparkonto" -> konten.add(new Sparkonto(inhaber, guthaben));
            case "Kreditkonto" -> konten.add(new Kreditkonto(inhaber, guthaben));
            default -> {
            }
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

        // Überprüfung: Betrag muss positiv sein
        if (betrag <= 0) {
            System.out.println("Überweisung fehlgeschlagen! Betrag muss positiv sein.");
            return;
        }

        // Überweisung durchführen
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
